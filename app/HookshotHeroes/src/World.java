import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.GeneralPath;
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

    // Elimination list
    public LinkedList<IWorldObject> EliminationRequests;

    // A FIFO queue for playing audios.
    public LinkedList<AudioRequest> AudioRequests;

    // Environment Levels.
    public ILevel CurrentLevel;

    public World(int width, int height, HookshotHeroesGameEngine engine, GameImage gameImage, GameAudio gameAudio, GameOptions options, ILevel level){
        Engine = engine;
        GameImage = gameImage;
        GameAudio = gameAudio;
        GameOptions = options;

        // Level world grid
        // The map is divided into cells of 10px by 10px.
        // Player can move 1 cell at a time.
        GridRows = height / CELL_HEIGHT;
        GridColumns = width / CELL_WIDTH;
        AnimationRequests = new ArrayList<>();
        AudioRequests = new LinkedList<>();
        EliminationRequests = new LinkedList<>();

        CurrentLevel = level;
    }

    public IWorldObject[] GetObjects() {
        return Objects.toArray(new IWorldObject[Objects.size()]);
    }

    // Set the objects in this gaming world.
    public void SetObjects(IWorldObject[] objects){
        Objects = new ArrayList<>();
        Collections.addAll(Objects, objects);
    }

    public void RenderLevel() {
        CurrentLevel.RenderLevel();
        CurrentLevel.SetLevelRendered(true);
    }

    public void RenderObjects(){
        for (IWorldObject object : Objects) {
            object.Render(Engine);
        }
    }

    @Override
    public void UpdateObjects(double dt) {
        for (IWorldObject object : Objects) {
            object.Update(dt);
        }
        if (EliminationRequests.size() > 0) {
            var player = EliminationRequests.pop();
            HandleElimination(player);
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
            // Check exit grid
            if (CheckGrid(collisionCheckInfo.Cell, CurrentLevel.GetExitGrid())){
                ((Player)collisionCheckInfo.Source).Score += Player.PLAYER_LEVEL_SCORE;
                var nextLevel = CurrentLevel.GetNextLevel();
                if (nextLevel != null){
                    Engine.InitializeLevel(Engine.GameOptions, nextLevel, GetPlayers());
                }
                else{
                    Engine.PauseEngine();
                    JOptionPane.showMessageDialog(Engine.mFrame, "YOU WIN!!!");
                    // Restart new game.
                    Engine.InitializeWorld(Engine.GameOptions);
                    Engine.ResumeEngine();
                }
                return;
            }
            // Check entry grid
            if (CheckGrid(collisionCheckInfo.Cell, CurrentLevel.GetEntryGrid())){
                var prevLevel = CurrentLevel.GetPreviousLevel();
                if (prevLevel != null){
                    prevLevel.SetStartPos(LevelStartPos.Top);
                    Engine.InitializeLevel(Engine.GameOptions, prevLevel, GetPlayers());
                }
                return;
            }
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

    private boolean CheckGrid(GridCell newCell, GridCell target){
        var offsetY = 3;
        var offsetX = 3;
        var result = false;
        if ((target.Row - offsetY <= newCell.Row && newCell.Row <= target.Row + offsetY)
        && (target.Column - offsetX <= newCell.Column && newCell.Column <= target.Column + offsetX)
        ){
            result = true;
        }
        return result;
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
                HandleElimination(collidedObject);
            }
            else if (type == WorldObjectType.Apple || type == WorldObjectType.Broccoli || type == WorldObjectType.Cabbage) {
                // Play eat apple sound effects.
                AudioRequests.add(new AudioRequest(WorldObjectType.Apple));
                // Spawn new random apple.
                //collidedObject.SetGridCell(GridCell.GetRandomCell(offset, GridRows - offset, offset, GridColumns - offset));
                RemoveObject(collidedObject);
            }
            else if (type == WorldObjectType.Mine) {
                // Play explosion sound effects.
                AnimationRequests.add(new AnimationRequest(type, collidedObject.GetOccupiedCells()[0], 10));
                AudioRequests.add(new AudioRequest(WorldObjectType.Mine));
                // Spawn new random mine.
                //collidedObject.SetGridCell(GridCell.GetRandomCell(offset, GridRows - offset, offset, GridColumns - offset));
                RemoveObject(collidedObject);
            }
            else if (type == WorldObjectType.Coin) {
                // Play explosion sound effects.
                AudioRequests.add(new AudioRequest(WorldObjectType.Coin));
                RemoveObject(collidedObject);
            }
        }
    }

    public void HandleElimination(IWorldObject player) {
        if (player.WhoAmI() == WorldObjectType.Player) {
            // Player game over.
            Engine.PauseEngine();
            JOptionPane.showMessageDialog(Engine.mFrame, player.GetName() + " eliminated!!!");
            Engine.ResumeEngine();
            RemoveObject(player);
            // Game over - no players left.
            if (GetPlayerCount() == 0) {
                JOptionPane.showMessageDialog(Engine.mFrame, "Game Over!!!");
                // Restart new game.
                Engine.InitializeWorld(Engine.GameOptions);
            }
        }
        if (player.WhoAmI() == WorldObjectType.Mine || player.WhoAmI() == WorldObjectType.Cabbage
                || player.WhoAmI() == WorldObjectType.Coin) {
            RemoveObject(player);
        }
    }

    @Override
    public ILevel GetLevel() {
        return CurrentLevel;
    }

    // Remove object from the gaming world.
    @Override
    public void RemoveObject(IWorldObject toRemove) {
        if (toRemove != null) {
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

    // Get the number of players.
    public int GetPlayerCount(){
        var count = 0;
        for (IWorldObject object : Objects) {
            if (object.WhoAmI() == WorldObjectType.Player) {
                count++;
            }
        }
        return count;
    }

    // Get the players.
    public ArrayList<Player> GetPlayers() {
        var players = new ArrayList<Player>();
        for (IWorldObject object : Objects) {
            if (object.WhoAmI() == WorldObjectType.Player) {
                players.add((Player) object);
            }
        }
        return players;
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
            if (request.Type == WorldObjectType.SpeechBubble) {
                DrawSpeechBubble(Engine, request.Player.GetOccupiedCells()[0], request.Text);
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
                Engine.playAudio(GameAudio.WalkAudio, GameOptions.MovementVolume);
            }
            else if (request.Type == WorldObjectType.Ball) {
                Engine.playAudio(GameAudio.HitAudio, GameOptions.SoundEffectsVolume);
            }
            else if (request.Type == WorldObjectType.Grapple) {
                Engine.playAudio(GameAudio.WhipAudio, GameOptions.SoundEffectsVolume);
            }
            else if (request.Type == WorldObjectType.Coin) {
                Engine.playAudio(GameAudio.CoinAudio, GameOptions.MovementVolume);
            }
        }
    }

    public void DrawSpeechBubble(GameEngine engine, GridCell cell, String text){
        var offsetX = 50;
        var offsetY = 0;
        engine.saveCurrentTransform();
        var graphics2D = engine.mGraphics;
        graphics2D.translate(cell.Column * CELL_WIDTH + offsetX, cell.Row * CELL_HEIGHT + offsetY);
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHints(qualityHints);
        graphics2D.setPaint(new Color(80, 150, 180));
        int width = 5 * text.length() + 10;
        int height = 25;
        GeneralPath path = new GeneralPath();
        path.moveTo(5, 10);
        path.curveTo(5, 10, 7, 5, 0, 0);
        path.curveTo(0, 0, 12, 0, 12, 5);
        path.curveTo(12, 5, 12, 0, 20, 0);
        path.lineTo(width - 10, 0);
        path.curveTo(width - 10, 0, width, 0, width, 10);
        path.lineTo(width, height - 10);
        path.curveTo(width, height - 10, width, height, width - 10, height);
        path.lineTo(15, height);
        path.curveTo(15, height, 5, height, 5, height - 10);
        path.lineTo(5, 15);
        path.closePath();
        graphics2D.fill(path);
        engine.changeColor(Color.white);
        engine.drawText(10, 15, text, "Arial", 10);
        engine.restoreLastTransform();
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
