import java.awt.*;
import java.util.ArrayList;

/****************************************************************************************
 * This class is the Player class.
 ****************************************************************************************/
public class Player implements IWorldObject {
    // Player health.
    public static final int MAX_LIFE = 5;
    // A list of cells occupied by the player.
    private final ArrayList<GridCell> _body;
    // Direction of player.
    private PlayerDirection _direction;
    // Skin.
    private final Skin _skin;
    // Keyboard control binding. Each player can have different binding.
    private final KeyBinding _keyBinding;
    // Occupied cells from level.
    private final ArrayList<GridCell> _occupiedCells;
    private final String _name;
    private GridCell _lastCell = null;
    // Number of lives
    private int _lives = 3;
    private int _spriteIndex = 0;
    private ObjectImage _image = null;

    public Player(String name, GridCell startCell, Skin skin, KeyBinding keyBinding, ArrayList<GridCell> occupiedCells) {
        _name = name;
        _skin = skin;
        _direction = PlayerDirection.Right;
        _keyBinding = keyBinding;
        _body = new ArrayList<>();
        _body.add(new GridCell(startCell.Row, startCell.Column));
        _image = new ObjectImage(skin.LRSprites[0]);
        _occupiedCells = occupiedCells;
    }

    // Move the player.
    // Use the direction of the player to decide the movement after key press.
    // Keyboard binding provides the key mappings.
    @Override
    public GridCell Move(int keyCode) {
        GridCell newCell = null;
        if (_direction == PlayerDirection.Up) {
            if (keyCode == _keyBinding.Up) {
                newCell = MoveUp();
                _direction = PlayerDirection.Up;
                IncrementSprite(_skin.UpSprites, false, false);
            }
            else if (keyCode == _keyBinding.Right) {
                newCell = MoveRight();
                _direction = PlayerDirection.Right;
                IncrementSprite(_skin.LRSprites, true, false);
            }
            else if (keyCode == _keyBinding.Left) {
                newCell = MoveLeft();
                _direction = PlayerDirection.Left;
                IncrementSprite(_skin.LRSprites, true, true);
            }
            else if (keyCode == _keyBinding.Down) {
                newCell = MoveDown();
                _direction = PlayerDirection.Down;
                IncrementSprite(_skin.DownSprites, true, false);
            }
        }
        else if (_direction == PlayerDirection.Left){
            if (keyCode == _keyBinding.Left) {
                newCell = MoveLeft();
                _direction = PlayerDirection.Left;
                IncrementSprite(_skin.LRSprites, false, true);
            }
            else if (keyCode == _keyBinding.Up) {
                newCell = MoveUp();
                _direction = PlayerDirection.Up;
                IncrementSprite(_skin.UpSprites, true, false);
            }
            else if (keyCode == _keyBinding.Down) {
                newCell = MoveDown();
                _direction = PlayerDirection.Down;
                IncrementSprite(_skin.DownSprites, true, false);
            }
            else if (keyCode == _keyBinding.Right) {
                newCell = MoveRight();
                _direction = PlayerDirection.Right;
                IncrementSprite(_skin.LRSprites, true, false);
            }
        }
        else if (_direction == PlayerDirection.Right){
            if (keyCode == _keyBinding.Right) {
                newCell = MoveRight();
                _direction = PlayerDirection.Right;
                IncrementSprite(_skin.LRSprites, false, false);
            }
            else if (keyCode == _keyBinding.Up) {
                newCell = MoveUp();
                _direction = PlayerDirection.Up;
                IncrementSprite(_skin.UpSprites, true, false);
            }
            else if (keyCode == _keyBinding.Down) {
                newCell = MoveDown();
                _direction = PlayerDirection.Down;
                IncrementSprite(_skin.DownSprites, true, false);
            }
            else if (keyCode == _keyBinding.Left) {
                newCell = MoveLeft();
                _direction = PlayerDirection.Left;
                IncrementSprite(_skin.LRSprites, true, true);
            }
        }
        else if (_direction == PlayerDirection.Down){
            if (keyCode == _keyBinding.Down) {
                newCell = MoveDown();
                _direction = PlayerDirection.Down;
                IncrementSprite(_skin.DownSprites, false, false);
            }
            else if (keyCode == _keyBinding.Right) {
                newCell = MoveRight();
                _direction = PlayerDirection.Right;
                IncrementSprite(_skin.LRSprites, true, false);
            }
            else if (keyCode == _keyBinding.Left) {
                newCell = MoveLeft();
                _direction = PlayerDirection.Left;
                IncrementSprite(_skin.LRSprites, true, true);
            }
            else if (keyCode == _keyBinding.Up) {
                newCell = MoveUp();
                _direction = PlayerDirection.Up;
                IncrementSprite(_skin.UpSprites, true, false);
            }
        }
        return newCell;
    }

    // Move up. Reduce row.
    private GridCell MoveUp() {
        var newCell = new GridCell(_body.get(0).Row - 1, _body.get(0).Column);
        if (CanMoveTo(newCell)) {
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
        if (CanMoveTo(newCell)) {
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
        if (CanMoveTo(newCell)) {
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
        if (CanMoveTo(newCell)) {
            _body.set(0, newCell);
            return newCell;
        }
        else{
            return _body.get(0);
        }
    }

    // Check if we can move to this cell.
    private boolean CanMoveTo(GridCell newCell) {
        var offsetYL = 4;
        var offsetYU = 2;
        var offsetXL = 3;
        var offsetXU = 2;
        var result = true;
        if (_occupiedCells != null) {
            for (GridCell target : _occupiedCells) {
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

    // Render the snake by drawing skin images oon each occupied grid cell.
    @Override
    public void Render(GameEngine engine) {
        engine.changeColor(Color.white);
        DrawName(engine, _body.get(0));
        DrawHealth(engine, _body.get(0));
        engine.drawImage(_image.Image,
                _image.Reflect? (_body.get(0).Column * _skin.CellWidth + GameImage.PLAYER_WIDTH) : (_body.get(0).Column * _skin.CellWidth + 5),
                _body.get(0).Row * _skin.CellHeight + 5,
                (_image.Reflect? -1 : 1) * (GameImage.PLAYER_WIDTH - 5),
                GameImage.PLAYER_HEIGHT - 10);
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
        // If collided with an apple. Remove apple and increase length of snake.
        if (type == WorldObjectType.Apple) {
            if(_lives < MAX_LIFE) {
                _lives += 1;
            }
            toRemove = object;
        }
        // If collided with another snake (or itself).
        else if (type == WorldObjectType.Player) {
            toRemove = this;
        }
        // Collided with mine or bouncing ball, reduce health by 1.
        else if (type == WorldObjectType.Mine || type == WorldObjectType.Ball) {
            _lives -= 1;
            // No more health. The player is removed from the game.
            if (_lives <= 0) {
                toRemove = this;
            } else {
                if (object.WhoAmI() != WorldObjectType.Ball) {
                    toRemove = object;
                }
            }
        } else if (type == WorldObjectType.Broccoli) {
            if(_lives < MAX_LIFE) {
                _lives += 1;
            }
            toRemove = object;
        }
        return toRemove;
    }

    // Returns object's type.
    @Override
    public WorldObjectType WhoAmI() {
        return WorldObjectType.Player;
    }

    // Get object's name.
    @Override
    public String GetName() {
        return _name;
    }

    @Override
    public void SetGridCell(GridCell cell) {

    }

    // Draw player identifier.
    private void DrawName(GameEngine engine, GridCell cell){
        var offset = 0;
        engine.drawText(cell.Column * _skin.CellWidth, cell.Row * _skin.CellHeight + offset, _name, "Arial", 12);
    }

    // Draw health icons based on number of lives.
    private void DrawHealth(GameEngine engine, GridCell cell){
        var offsetV = -10;
        var offsetH = 50;
        for(int i = 0; i < _lives; i++){
            engine.drawImage(_skin.Health, cell.Column * _skin.CellWidth + i * 10 + offsetH, cell.Row * _skin.CellHeight + offsetV, 10, 10);
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
