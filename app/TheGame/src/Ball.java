import java.awt.*;
import java.util.ArrayList;

/****************************************************************************************
 * Ball
 ****************************************************************************************/
public class Ball implements IWorldObject{
    public static final double G = 0;
    private static final double LOSS = 1;
    private final String _name;
    private GridCell _cell;
    private int CELL_WIDTH, CELL_HEIGHT;
    public Ball(String name, int radius, int cellHeight, int cellWidth){
        _name = name;
        Radius = radius;
        CELL_WIDTH = cellWidth;
        CELL_HEIGHT = cellHeight;
    }
    public int Radius;
    public Vector2D Position;
    public Vector2D Velocity;
    public Vector2D Acceleration;
    public java.awt.Color Color;

    @Override
    public GridCell Move(int keyCode) {
        return null;
    }

    @Override
    public void Render(GameEngine engine) {
        engine.changeColor(Color);
        engine.drawSolidCircle(Position.X, Position.Y, Radius);
    }

    public GridCell[] GetOccupiedCells() {
        // Convert pixel X, Y to grid cell.
        var x = (int)Position.X / CELL_WIDTH;
        var y = (int)Position.Y / CELL_HEIGHT;

        //var xm = Position.X % CELL_WIDTH;
        //var ym = Position.Y % CELL_HEIGHT;

        var _cell = new GridCell(y, x);
        return new GridCell[] { _cell };
    }

    @Override
    public IWorldObject HandleCollision(IWorldObject object) {
        return null;
    }

    @Override
    public WorldObjectType WhoAmI() {
        return WorldObjectType.Ball;
    }

    @Override
    public String GetName() {
        return _name;
    }

    @Override
    public void SetGridCell(GridCell cell) {
        _cell = cell;
    }

    // Check ball collision.
    public static boolean CheckCollision(Ball ball1, Ball ball2){
        var diffX = ball1.Position.X - ball2.Position.X;
        var diffY = ball1.Position.Y - ball2.Position.Y;
        var dist = Math.sqrt(diffX * diffX + diffY * diffY);
        if(dist <= (ball1.Radius + ball2.Radius)){
            return true;
        }
        return false;
    }

    // Check snake collision
    public static boolean CheckCollision(Ball ball, IWorld world) {
        var result = false;
        for (IWorldObject object : world.GetObjects()) {
            if (object.WhoAmI() == WorldObjectType.Snake) {
                var cells = object.GetOccupiedCells();
                var newCell = ball.GetOccupiedCells()[0];
                for (int j = 0; j < cells.length; j++) {
                    if (newCell.Row == cells[j].Row && newCell.Column == cells[j].Column) {
                        // Call each objects specific handle collision function.
                        var collidedObject = object.HandleCollision(ball);
                        world.HandleCollision(collidedObject);
                        result = true;
                        break;
                    }
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    // Update position and velocity of ball.
    public static void UpdateBall(Ball ball, double dt, int width, int height){
        ball.Velocity.X += ball.Acceleration.X * dt;
        ball.Velocity.Y += ball.Acceleration.Y * dt;

        ball.Position.X += ball.Velocity.X * dt;
        ball.Position.Y += ball.Velocity.Y * dt;

        if (ball.Position.X - ball.Radius <= 0){
            ball.Position.X = ball.Radius;
            ball.Velocity.X *= -1;
            ball.Velocity.X *= LOSS;
        }

        if (ball.Position.Y - ball.Radius <= 0){
            ball.Position.Y = ball.Radius;
            ball.Velocity.Y *= -1;
            ball.Velocity.Y *= LOSS;
        }

        if (ball.Position.X + ball.Radius >= width){
            ball.Position.X = width - ball.Radius;
            ball.Velocity.X *= -1;
            ball.Velocity.X *= LOSS;
        }

        if (ball.Position.Y + ball.Radius >= height){
            ball.Position.Y = height - ball.Radius;
            ball.Velocity.Y *= -1;
            ball.Velocity.Y *= LOSS;
        }
    }
}