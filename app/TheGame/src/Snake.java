import java.awt.*;
import java.util.ArrayList;

/****************************************************************************************
 * This class is the snake class.
 ****************************************************************************************/
public class Snake implements IWorldObject {
    // Size limits of the snake.
    public static final int BIRTH_SIZE = 3;
    public static final int MAX_SIZE = 20;
    public static final int MAX_LIFE = 5;
    // A list of cells occupied by the snake. This will be rendered at each grid cell.
    private final ArrayList<GridCell> _body;
    // Direction of the snake's head.
    private SnakeDirection _direction;
    // Skin.
    private final Skin _skin;
    // Keyboard control binding. Each player can have different binding.
    private final KeyBinding _keyBinding;
    private final String _name;
    private GridCell _lastCell = null;
    // Number of lives
    private int _lives = 3;

    public Snake(String name, GridCell startCell, Skin skin, KeyBinding keyBinding) {
        _name = name;
        _skin = skin;
        _direction = SnakeDirection.Up;
        _keyBinding = keyBinding;
        _body = new ArrayList<>();
        // Create default size.
        for(int i = 0; i < BIRTH_SIZE; i++){
            _body.add(new GridCell(startCell.Row + i, startCell.Column));
        }
    }

    // Move the snake body.
    // Each cell must be the same as the previous cell in that position.
    private GridCell Shift(GridCell newCell){
        for(int i=0;i<_body.size();i++){
            var current = _body.get(i);
            _body.set(i, newCell);
            newCell = current;
        }
        return newCell;
    }

    // Move the snake.
    // Use the direction of the snake's head to decide the movement after key press.
    // Keyboard binding provides the key mappings.
    @Override
    public GridCell Move(int keyCode) {
        GridCell newCell = null;
        if (_direction == SnakeDirection.Up) {
            if (keyCode == _keyBinding.Up) {
                newCell = MoveUp();
                _direction = SnakeDirection.Up;
            }
            else if (keyCode == _keyBinding.Right) {
                newCell = MoveRight();
                _direction = SnakeDirection.Right;
            }
            else if (keyCode == _keyBinding.Left) {
                newCell = MoveLeft();
                _direction = SnakeDirection.Left;
            }
        }
        else if (_direction == SnakeDirection.Left){
            if (keyCode == _keyBinding.Left) {
                newCell = MoveLeft();
                _direction = SnakeDirection.Left;
            }
            else if (keyCode == _keyBinding.Up) {
                newCell = MoveUp();
                _direction = SnakeDirection.Up;
            }
            else if (keyCode == _keyBinding.Down) {
                newCell = MoveDown();
                _direction = SnakeDirection.Down;
            }
        }
        else if (_direction == SnakeDirection.Right){
            if (keyCode == _keyBinding.Right) {
                newCell = MoveRight();
                _direction = SnakeDirection.Right;
            }
            else if (keyCode == _keyBinding.Up) {
                newCell = MoveUp();
                _direction = SnakeDirection.Up;
            }
            else if (keyCode == _keyBinding.Down) {
                newCell = MoveDown();
                _direction = SnakeDirection.Down;
            }
        }
        else if (_direction == SnakeDirection.Down){
            if (keyCode == _keyBinding.Down) {
                newCell = MoveDown();
                _direction = SnakeDirection.Down;
            }
            else if (keyCode == _keyBinding.Right) {
                newCell = MoveRight();
                _direction = SnakeDirection.Right;
            }
            else if (keyCode == _keyBinding.Left) {
                newCell = MoveLeft();
                _direction = SnakeDirection.Left;
            }
        }
        return newCell;
    }

    // Move up. Reduce row.
    private GridCell MoveUp(){
        var newCell = new GridCell(_body.get(0).Row - 1, _body.get(0).Column);
        _lastCell = Shift(newCell);
        return newCell;
    }

    // Move left. Reduce column.
    private GridCell MoveLeft(){
        var newCell = new GridCell(_body.get(0).Row, _body.get(0).Column - 1);
        _lastCell = Shift(newCell);
        return newCell;
    }

    // Move right. Increase column.
    private GridCell MoveRight(){
        var newCell = new GridCell(_body.get(0).Row, _body.get(0).Column + 1);
        _lastCell = Shift(newCell);
        return newCell;
    }

    // Move down. Increase row.
    private GridCell MoveDown(){
        var newCell = new GridCell(_body.get(0).Row + 1, _body.get(0).Column);
        _lastCell = Shift(newCell);
        return newCell;
    }

    // Render the snake by drawing skin images oon each occupied grid cell.
    @Override
    public void Render(GameEngine engine) {
        engine.changeColor(Color.white);
        for(int i = 0; i < _body.size(); i++){
            var image = _skin.Body;
            if(i==0) {
                // Draw head.
                DrawName(engine, _body.get(i));
                // Draw heart health.
                DrawHealth(engine, _body.get(i));
                image = _skin.Head;
            }
            engine.drawImage(image, _body.get(i).Column * _skin.CellWidth + 5, _body.get(i).Row * _skin.CellHeight + 5);
        }
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
            if (_body.size() < MAX_SIZE) {
                _body.add(_lastCell);
            }
            toRemove = object;
        }
        // If collided with another snake (or itself).
        else if (type == WorldObjectType.Snake) {
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
        return WorldObjectType.Snake;
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
        if(_direction == SnakeDirection.Down){
            offset = _skin.CellHeight * 3;
        }
        engine.drawText(cell.Column * _skin.CellWidth, cell.Row * _skin.CellHeight + offset, _name, "Arial", 12);
    }

    // Draw health icons based on number of lives.
    private void DrawHealth(GameEngine engine, GridCell cell){
        var offsetV = _skin.CellHeight * 2;
        var offsetH = 50;
        if(_direction != SnakeDirection.Down){
            offsetV = -10;
        }
        for(int i = 0; i < _lives; i++){
            engine.drawImage(_skin.Health, cell.Column * _skin.CellWidth + i * 10 + offsetH, cell.Row * _skin.CellHeight + offsetV, 10, 10);
        }
    }
}
