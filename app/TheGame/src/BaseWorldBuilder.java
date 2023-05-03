import java.awt.*;
import java.util.Random;

/****************************************************************************************
 * This class is the world builder base class.
 ****************************************************************************************/
public class BaseWorldBuilder {
    protected int startOffset = 5, endOffset = 5;

    public World CreateWorld(TheGameEngine engine, GameImage gameImage, GameAudio gameAudio, GameOptions options){

        return new World(options.Width, options.Height, engine, gameImage, gameAudio, options);
    }

    // Add objects to the world.
    public void AddObjects(World world, WorldObjectType type, int count) {
        for (int i = 0; i < count; i++) {
            if (type == WorldObjectType.Apple) {
                world.Objects.add(new Apple("Apple", GridCell.GetRandomCell(startOffset, world.GridRows - endOffset, startOffset, world.GridColumns - endOffset),
                        new Skin(null, world.GameImage.Apple, null, world.CELL_WIDTH, world.CELL_HEIGHT)));
            }
            if (type == WorldObjectType.Mine) {
                world.Objects.add(new Mine("Mine", GridCell.GetRandomCell(startOffset, world.GridRows - endOffset, startOffset, world.GridColumns - endOffset),
                        new Skin(null, world.GameImage.Mine, null, world.CELL_WIDTH, world.CELL_HEIGHT)));
            }
            if (type == WorldObjectType.Ball) {
                var startX = 10;
                var startY = 10;
                var radius = 10;
                var r = new Random();
                var ball = new Ball("Ball" + i, radius, world.CELL_WIDTH, world.CELL_HEIGHT);
                ball.Position = new Vector2D(startX + radius*3*i, startY);
                ball.Velocity = new Vector2D(r.nextDouble(500), r.nextDouble(500));
                ball.Acceleration = new Vector2D(0, Ball.G);
                ball.Color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
                world.Objects.add(ball);
            }
            if (type == WorldObjectType.Broccoli) {
                world.Objects.add(new Broccoli("Broccoli", GridCell.GetRandomCell(startOffset, world.GridRows - endOffset, startOffset, world.GridColumns - endOffset),
                        new Skin(null, world.GameImage.Broccoli, null, world.CELL_WIDTH, world.CELL_HEIGHT)));
            }
        }
    }
}
