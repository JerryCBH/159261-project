import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.*;

/****************************************************************************************
 * The World class encapsulate the game world
 ****************************************************************************************/
public class World implements IWorld {
    public final int CELL_WIDTH = 10, CELL_HEIGHT = 10;
    public int GridRows, GridColumns;

    // Reference to the game.
    public HookshotHeroesGameEngine Engine;
    public GameImage GameImage;
    public GameAudio GameAudio;
    public GameOptions GameOptions;

    // List of objects in the game.
    public ArrayList<IWorldObject> Objects;

    // A list of animations to play.
    public ArrayList<AnimationRequest> AnimationRequests;

    // A FIFO queue for playing audios.
    public LinkedList<AudioRequest> AudioRequests;

    public World(int width, int height, HookshotHeroesGameEngine engine, GameImage gameImage, GameAudio gameAudio, GameOptions options){
        Engine = engine;
        GameImage = gameImage;
        GameAudio = gameAudio;
        GameOptions = options;

        // Level world grid
        // The map is divided into cells of 10px by 10px.
        // Snake can move 1 cell at a time.
        GridRows = height / CELL_HEIGHT;
        GridColumns = width / CELL_WIDTH;
        AnimationRequests = new ArrayList<>();
        AudioRequests = new LinkedList<>();
    }

    public IWorldObject[] GetObjects() {
        return Objects.toArray(new IWorldObject[Objects.size()]);
    }

    // Set the objects in this gaming world.
    public void SetObjects(IWorldObject[] objects){
        Objects = new ArrayList<>();
        Collections.addAll(Objects, objects);
    }

    public void RenderObjects(){
        for (IWorldObject object : Objects) {
            object.Render(Engine);
        }
    }

    public void HandleKeyEvents(KeyEvent event){
        var toCheck = MoveObjects(event);
        CheckCollision(toCheck);
    }

    // Advances movable objects by 1 grid at a time.
    // The new grid cell to move to will be checked for any collisions.
    private ArrayList<CollisionCheckInfo> MoveObjects(KeyEvent event){
        var toCheck = new ArrayList<CollisionCheckInfo>();
        // Advance each player in the world and gather the collision info.
        for (IWorldObject object : Objects) {
            var newCell = object.Move(event.getKeyCode());
            if (newCell != null) {
                AudioRequests.add(new AudioRequest(WorldObjectType.Player));
                toCheck.add(new CollisionCheckInfo(newCell, object));
            }
        }
        return toCheck;
    }

    // Check for collisions from a list of new grid cells objects are moving into.
    private void CheckCollision(ArrayList<CollisionCheckInfo> toCheck){
        // Go through each collected collision info and check if collision happened.
        for (CollisionCheckInfo collisionCheckInfo : toCheck) {
            // Check going over the boundaries.
            var toRemove = CheckBoundaryCollision(collisionCheckInfo.Cell, collisionCheckInfo.Source);
            if (toRemove == null) {
                // Check object / object collision.
                var info = CheckObjectCollision(collisionCheckInfo.Cell, collisionCheckInfo.Source);
                if (info != null) {
                    toRemove = info.Remove;
                    // Special case here as both collided objects need to be removed.
                    if (info.Remove != null && info.Remove.WhoAmI() == WorldObjectType.Player &&
                            info.Target.WhoAmI() == WorldObjectType.Mine) {
                        HandleCollision(info.Target);
                    }
                }
            }
            // Remove the collided object.
            HandleCollision(toRemove);
        }
    }

    // Given a new grid cell, check if there are any objects in the cell.
    // If collision detected, decide if the object needs to be removed from the game world.
    private CollisionCheckInfo CheckObjectCollision(GridCell newCell, IWorldObject object){
        CollisionCheckInfo info = null;
        IWorldObject collidedObject = null;
        for (IWorldObject iWorldObject : Objects) {
            // Get a list of cells occupied.
            var cells = iWorldObject.GetOccupiedCells();
            if (object.GetName().equals(iWorldObject.GetName()) && cells.length == 1) {
                continue;
            }
            for (int j = 0; j < cells.length; j++) {
                // Player boundary box
                if (newCell.Row <= cells[j].Row && cells[j].Row <= (newCell.Row + 3)
                        && newCell.Column <= cells[j].Column && cells[j].Column <= (newCell.Column + 3)) {
                    // Call each objects specific handle collision function.
                    collidedObject = object.HandleCollision(iWorldObject);
                    info = new CollisionCheckInfo(newCell, object, iWorldObject, collidedObject);
                    break;
                }
            }
            if (collidedObject != null) {
                break;
            }
        }
        return info;
    }

    // Check if the object has moved beyond the grid boundaries.
    private IWorldObject CheckBoundaryCollision(GridCell newCell, IWorldObject object){
        IWorldObject collidedObject = null;
        var offset = 2;
        if (newCell.Row < 0 || newCell.Row > GridRows - offset || newCell.Column < 0 || newCell.Column > GridColumns - offset){
            collidedObject = object;
        }
        return collidedObject;
    }

    // Handle the aftermath of the collisions.
    // Prompt message popup if game over.
    // Decide if we need to play sound effects after collision.
    public void HandleCollision(IWorldObject collidedObject){
        var offset = 5;
        if (collidedObject != null) {
            var type = collidedObject.WhoAmI();
            if (type == WorldObjectType.Player) {
                // Player game over.
                Engine.PauseEngine();
                JOptionPane.showMessageDialog(Engine.mFrame, collidedObject.GetName() + " eliminated!!!");
                Engine.ResumeEngine();
                RemoveObject(collidedObject);
                // Game over - no players left.
                if (GetPlayerCount() == 0) {
                    JOptionPane.showMessageDialog(Engine.mFrame, "Game Over!!!");
                    // Restart new game.
                    Engine.InitializeWorld(Engine.GameOptions);
                }
            }
            else if (type == WorldObjectType.Apple) {
                // Play eat apple sound effects.
                AudioRequests.add(new AudioRequest(WorldObjectType.Apple));
                // Spawn new random apple.
                collidedObject.SetGridCell(GridCell.GetRandomCell(offset, GridRows - offset, offset, GridColumns - offset));
            }
            else if (type == WorldObjectType.Mine) {
                // Play explosion sound effects.
                AnimationRequests.add(new AnimationRequest(type, collidedObject.GetOccupiedCells()[0], 10));
                AudioRequests.add(new AudioRequest(WorldObjectType.Mine));
                // Spawn new random mine.
                collidedObject.SetGridCell(GridCell.GetRandomCell(offset, GridRows - offset, offset, GridColumns - offset));
            }
            else if (type == WorldObjectType.Broccoli) {
                // Play explosion sound effects.
                AudioRequests.add(new AudioRequest(WorldObjectType.Apple));
                // Spawn new random broccoli.
                collidedObject.SetGridCell(GridCell.GetRandomCell(offset, GridRows - offset, offset, GridColumns - offset));
            }
        }
    }

    // Remove object from the gaming world.
    private void RemoveObject(IWorldObject toRemove){
        if(toRemove != null) {
            Objects.removeIf(iWorldObject -> iWorldObject.GetName().equals(toRemove.GetName()));
        }
    }

    // Remove played animations.
    private void RemoveAnimationRequests() {
        Iterator<AnimationRequest> itr = AnimationRequests.iterator();
        while (itr.hasNext()) {
            var request = (AnimationRequest)itr.next();
            // If we have finished playing most of the track.
            if (request.time > request.SecondsToPlay * 2 / 3)
                itr.remove();
        }
    }

    // Get the number of snakes.
    public int GetPlayerCount(){
        var count = 0;
        for (IWorldObject object : Objects) {
            if (object.WhoAmI() == WorldObjectType.Player) {
                count++;
            }
        }
        return count;
    }

    // Update each animation request on the amount of time played.
    public void UpdateAnimationRequests(double dt){
        for (AnimationRequest request : AnimationRequests) {
            request.time += dt;
        }
        UpdateBalls(dt);
    }

    // Play the animation based on object type.
    public void PlayAnimation(){
        for (AnimationRequest request : AnimationRequests) {
            // Only support explosion animation.
            if (request.Type == WorldObjectType.Mine) {
                var idx = Engine.GetFrameIndex(request.time, request.SecondsToPlay, GameImage.ExplosionSprites.length);
                Engine.drawImage(GameImage.ExplosionSprites[idx], request.Cell.Column * CELL_HEIGHT - 10, request.Cell.Row * CELL_WIDTH - 30, 50, 50);
            }
        }

        // Clean up
        RemoveAnimationRequests();
    }

    // Play audio effects.
    public void PlayAudio(){
        if (AudioRequests.size() > 0) {
            var request = AudioRequests.pop();
            if (request.Type == WorldObjectType.Mine) {
                // Explosion sound.
                Engine.playAudio(GameAudio.ExplosionAudio, GameOptions.SoundEffectsVolume);
            }
            else if (request.Type == WorldObjectType.Apple) {
                // Crunch sound.
                Engine.playAudio(GameAudio.CrunchAudio, GameOptions.SoundEffectsVolume);
            }
            else if (request.Type == WorldObjectType.Player) {
                // Snake
                Engine.playAudio(GameAudio.WalkAudio, GameOptions.MovementVolume);
            }
            else if (request.Type == WorldObjectType.Ball) {
                // Snake
                Engine.playAudio(GameAudio.HitAudio, GameOptions.SoundEffectsVolume);
            }
        }
    }

    // Update the balls.
    public void UpdateBalls(double dt) {
        for(int i = 0; i < Objects.size(); i++) {
            var object = Objects.get(i);
            if (object.WhoAmI() == WorldObjectType.Ball) {
                var ball = (Ball) object;
                Ball.UpdateBall(ball, dt, CELL_HEIGHT * GridColumns, CELL_WIDTH * GridRows);
                for (int j = i + 1; j < Objects.size(); j++) {
                    var object2 = Objects.get(j);
                    if (object2.WhoAmI() == WorldObjectType.Ball) {
                        var ball2 = (Ball) object2;
                        if (Ball.CheckCollision(ball, ball2)) {
                            var tempX = ball.Velocity.X;
                            var tempY = ball.Velocity.Y;
                            ball.Velocity.X = ball2.Velocity.X;
                            ball.Velocity.Y = ball2.Velocity.Y;
                            ball2.Velocity.X = tempX;
                            ball2.Velocity.Y = tempY;
                        }
                    }
                }
                var result = Ball.CheckCollision(ball, this);
                if (result) {
                    ball.Velocity.X *= -1;
                    ball.Velocity.Y *= -1;
                    AudioRequests.add(new AudioRequest(WorldObjectType.Ball));
                }
            }
        }
    }
}
