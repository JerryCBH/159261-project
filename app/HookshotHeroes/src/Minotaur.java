import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/****************************************************************************************
 * This class is the Minotaur class.
 ****************************************************************************************/
public class Minotaur implements IWorldObject {
    // Player health.
    public static final int MAX_LIFE = 10;

    // A list of cells occupied by the player.
    private final ArrayList<GridCell> _body;
    // Direction of player.
    private PlayerDirection _direction;
    // Skin.
    private final Skin _skin;
    // Keyboard control binding. Each player can have different binding.
    private final KeyBinding _keyBinding;
    // Occupied cells from level.
    public ArrayList<GridCell> LavaCells;
    public ArrayList<GridCell> WallCells;
    public ArrayList<GridCell> OccupiedCells;
    private final String _name;
    private GridCell _lastCell = null;
    // Number of lives
    private int _lives = 10;
    private int _spriteIndex = 0;
    private ObjectImage _image = null;
    private boolean _isGrappling = false;
    public LinkedList<AudioRequest> AudioRequests;
    public LinkedList<IWorldObject> EliminationRequests;
    public ArrayList<AnimationRequest> AnimationRequests;
    public IWorld World;
    private double _time = 0;

    public Minotaur(String name, GridCell startCell, Skin skin, KeyBinding keyBinding,
                  ArrayList<GridCell> wallCells, ArrayList<GridCell> lavaCells, ArrayList<GridCell> occupiedCells,
                  LinkedList<AudioRequest> audioRequests, LinkedList<IWorldObject> eliminationRequests, ArrayList<AnimationRequest> animationRequests,
                  IWorld world) {
        _name = name;
        _skin = skin;
        _direction = PlayerDirection.Right;
        _keyBinding = keyBinding;
        _body = new ArrayList<>();
        _body.add(new GridCell(startCell.Row, startCell.Column));
        _image = new ObjectImage(skin.DownSprites[0]);
        WallCells = wallCells;
        LavaCells = lavaCells;
        OccupiedCells = occupiedCells;
        AudioRequests = audioRequests;
        EliminationRequests = eliminationRequests;
        AnimationRequests = animationRequests;
        World = world;
    }

    // Move the player.
    // Use the direction of the player to decide the movement after key press.
    // Keyboard binding provides the key mappings.
    @Override
    public GridCell Move(int keyCode) {
        GridCell newCell = null;
        if (!_isGrappling) {
            if (_direction == PlayerDirection.Up) {
                if (keyCode == _keyBinding.Up) {
                    newCell = MoveUp();
                    _direction = PlayerDirection.Up;
                    IncrementSprite(_skin.UpSprites, false, false);
                } else if (keyCode == _keyBinding.Right) {
                    newCell = MoveRight();
                    _direction = PlayerDirection.Right;
                    IncrementSprite(_skin.RightSprites, true, false);
                } else if (keyCode == _keyBinding.Left) {
                    newCell = MoveLeft();
                    _direction = PlayerDirection.Left;
                    //IncrementSprite(_skin.LRSprites, true, true);
                    IncrementSprite(_skin.LeftSprites, true, false);
                } else if (keyCode == _keyBinding.Down) {
                    newCell = MoveDown();
                    _direction = PlayerDirection.Down;
                    IncrementSprite(_skin.DownSprites, true, false);
                }
            } else if (_direction == PlayerDirection.Left) {
                if (keyCode == _keyBinding.Left) {
                    newCell = MoveLeft();
                    _direction = PlayerDirection.Left;
                    //IncrementSprite(_skin.LRSprites, false, true);
                    IncrementSprite(_skin.LeftSprites, false, false);
                } else if (keyCode == _keyBinding.Up) {
                    newCell = MoveUp();
                    _direction = PlayerDirection.Up;
                    IncrementSprite(_skin.UpSprites, true, false);
                } else if (keyCode == _keyBinding.Down) {
                    newCell = MoveDown();
                    _direction = PlayerDirection.Down;
                    IncrementSprite(_skin.DownSprites, true, false);
                } else if (keyCode == _keyBinding.Right) {
                    newCell = MoveRight();
                    _direction = PlayerDirection.Right;
                    IncrementSprite(_skin.RightSprites, true, false);
                }
            } else if (_direction == PlayerDirection.Right) {
                if (keyCode == _keyBinding.Right) {
                    newCell = MoveRight();
                    _direction = PlayerDirection.Right;
                    IncrementSprite(_skin.RightSprites, false, false);
                } else if (keyCode == _keyBinding.Up) {
                    newCell = MoveUp();
                    _direction = PlayerDirection.Up;
                    IncrementSprite(_skin.UpSprites, true, false);
                } else if (keyCode == _keyBinding.Down) {
                    newCell = MoveDown();
                    _direction = PlayerDirection.Down;
                    IncrementSprite(_skin.DownSprites, true, false);
                } else if (keyCode == _keyBinding.Left) {
                    newCell = MoveLeft();
                    _direction = PlayerDirection.Left;
                    //IncrementSprite(_skin.LRSprites, true, true);
                    IncrementSprite(_skin.LeftSprites, true, false);
                }
            } else if (_direction == PlayerDirection.Down) {
                if (keyCode == _keyBinding.Down) {
                    newCell = MoveDown();
                    _direction = PlayerDirection.Down;
                    IncrementSprite(_skin.DownSprites, false, false);
                } else if (keyCode == _keyBinding.Right) {
                    newCell = MoveRight();
                    _direction = PlayerDirection.Right;
                    IncrementSprite(_skin.RightSprites, true, false);
                } else if (keyCode == _keyBinding.Left) {
                    newCell = MoveLeft();
                    _direction = PlayerDirection.Left;
                    //IncrementSprite(_skin.LRSprites, true, true);
                    IncrementSprite(_skin.LeftSprites, true, false);
                } else if (keyCode == _keyBinding.Up) {
                    newCell = MoveUp();
                    _direction = PlayerDirection.Up;
                    IncrementSprite(_skin.UpSprites, true, false);
                }
            }
        }
        return newCell;
    }

    // Move up. Reduce row.
    private GridCell MoveUp() {
        var newCell = new GridCell(_body.get(0).Row - 1, _body.get(0).Column);
        if (CanMoveTo(newCell, OccupiedCells)) {
            _body.set(0, newCell);
            return newCell;
        }
        else{
            return _body.get(0);
        }
    }

    // Move left. Reduce column.
    private GridCell MoveLeft(){
        var newCell = new GridCell(_body.get(0).Row, _body.get(0).Column - 1);
        if (CanMoveTo(newCell, OccupiedCells)) {
            _body.set(0, newCell);
            return newCell;
        }
        else{
            return _body.get(0);
        }
    }

    // Move right. Increase column.
    private GridCell MoveRight(){
        var newCell = new GridCell(_body.get(0).Row, _body.get(0).Column + 1);
        if (CanMoveTo(newCell, OccupiedCells)) {
            _body.set(0, newCell);
            return newCell;
        }
        else{
            return _body.get(0);
        }
    }

    // Move down. Increase row.
    private GridCell MoveDown(){
        var newCell = new GridCell(_body.get(0).Row + 1, _body.get(0).Column);
        if (CanMoveTo(newCell, OccupiedCells)) {
            _body.set(0, newCell);
            return newCell;
        }
        else{
            return _body.get(0);
        }
    }

    // Check if we can move to this cell.
    private boolean CanMoveTo(GridCell newCell, ArrayList<GridCell> occupiedCells) {
        var offsetYL = 4;
        var offsetYU = 0;
        var offsetXL = 3;
        var offsetXU = 1;
        var result = true;
        if (occupiedCells != null) {
            for (GridCell target : occupiedCells) {
                if ((target.Row - offsetYL <= newCell.Row && newCell.Row <= target.Row + offsetYU)
                        && (target.Column - offsetXL <= newCell.Column && newCell.Column <= target.Column + offsetXU)
                ) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    // Render the player.
    @Override
    public void Render(GameEngine engine) {
        engine.changeColor(Color.white);
        DrawName(engine, _body.get(0));
        DrawHealth(engine, _body.get(0));
        engine.drawImage(_image.Image,
                _image.Reflect? (_body.get(0).Column * _skin.CellWidth + GameImage.Minotaur_WIDTH) : (_body.get(0).Column * _skin.CellWidth + 5),
                _body.get(0).Row * _skin.CellHeight + 5,
                (_image.Reflect? -1 : 1) * (GameImage.Minotaur_WIDTH),
                GameImage.Minotaur_HEIGHT);
    }

    @Override
    public void Update(Double dt) {
        if (_time > 0.5){
            _time = 0;
            Random r = new Random();
            var nextMove = r.nextInt(1, 5);
            if (nextMove == 1) {
                Move(KeyEvent.VK_UP);
            }
            else if (nextMove == 2) {
                Move(KeyEvent.VK_RIGHT);
            }
            else if (nextMove == 3) {
                Move(KeyEvent.VK_DOWN);
            }
            else if (nextMove == 4) {
                Move(KeyEvent.VK_LEFT);
            }
        }
        else{
            _time += dt;
        }
    }

    // Check grapple collisions with enemies.
    public void CheckObjectCollision(GridCell currentCell) {

    }

    // Get All cells occupied by the snake.
    @Override
    public GridCell[] GetOccupiedCells() {
        return _body.toArray(new GridCell[0]);
    }

    // Handles snake collision.
    @Override
    public IWorldObject HandleCollision(IWorldObject object) {
        IWorldObject toRemove = null;
        var type = object.WhoAmI();

        // If collided with another player (or itself).
        if (type == WorldObjectType.Player) {
            //toRemove = this;
        }

        return toRemove;
    }

    // Returns object's type.
    @Override
    public WorldObjectType WhoAmI() {
        return WorldObjectType.Minotaur;
    }

    // Get object's name.
    @Override
    public String GetName() {
        return _name;
    }

    @Override
    public void SetGridCell(GridCell cell) {
        _body.set(0, cell);
    }

    @Override
    public void HandleDamage() {
        //AudioRequests.add(new AudioRequest(WorldObjectType.Minotaur));
        _lives -= 1;
        // No more health. The player is removed from the game.
        if (_lives <= 0) {
            EliminationRequests.push(this);
        }
    }

    // Draw player identifier.
    private void DrawName(GameEngine engine, GridCell cell){
        var offset = 0;
        engine.drawText(cell.Column * _skin.CellWidth, cell.Row * _skin.CellHeight + offset, _name, "Arial", 12);
    }

    // Draw health icons based on number of lives.
    private void DrawHealth(GameEngine engine, GridCell cell){
        var offsetV = -10;
        var offsetH = 35;
        for(int i = 0; i < _lives; i++){
            engine.drawImage(_skin.Health, cell.Column * _skin.CellWidth + i * 10, cell.Row * _skin.CellHeight, 10, 10);
        }
    }

    // Get next sprite image.
    private void IncrementSprite(Image[] sprites, boolean reset, boolean reflect) {
        _spriteIndex++;
        if (_spriteIndex == sprites.length || reset) {
            _spriteIndex = 0;
        }
        _image.Image = sprites[_spriteIndex];
        _image.Reflect = reflect;
    }
}

