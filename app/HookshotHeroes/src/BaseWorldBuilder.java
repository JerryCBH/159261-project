import java.awt.*;
import java.util.Collections;
import java.util.Random;

/****************************************************************************************
 * This class is the world builder base class.
 ****************************************************************************************/
public class BaseWorldBuilder {
    protected int startOffset = 5, endOffset = 10;

    public World CreateWorld(HookshotHeroesGameEngine engine, GameImage gameImage, GameAudio gameAudio, GameOptions options, ILevel level){

        return new World(options.Width, options.Height, engine, gameImage, gameAudio, options, level);
    }

    // Add objects to the world.
    public void AddObjects(World world, WorldObjectType type, int count) {
        for (int i = 0; i < count; i++) {
            if (type == WorldObjectType.Apple) {
                world.Objects.add(new Apple(java.util.UUID.randomUUID().toString(), GridCell.GetRandomCell(startOffset, world.GridRows - endOffset, startOffset, world.GridColumns - endOffset,
                        world.CurrentLevel.GetOccupiedCells()),
                        new Skin(world.GameImage.Apple, world.CELL_WIDTH, world.CELL_HEIGHT)));
            }
            if (type == WorldObjectType.Mine) {
                world.Objects.add(new Mine(java.util.UUID.randomUUID().toString(), GridCell.GetRandomCell(startOffset, world.GridRows - endOffset, startOffset, world.GridColumns - endOffset,
                        world.CurrentLevel.GetOccupiedCells()),
                        new Skin(world.GameImage.BombSprites, world.CELL_WIDTH, world.CELL_HEIGHT)));
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
                world.Objects.add(new Broccoli(java.util.UUID.randomUUID().toString(), GridCell.GetRandomCell(startOffset, world.GridRows - endOffset, startOffset, world.GridColumns - endOffset,
                        world.CurrentLevel.GetOccupiedCells()),
                        new Skin(world.GameImage.Broccoli, world.CELL_WIDTH, world.CELL_HEIGHT)));
            }
            if (type == WorldObjectType.Coin) {
                world.Objects.add(new Coin(java.util.UUID.randomUUID().toString(), GridCell.GetRandomCell(startOffset, world.GridRows - endOffset, startOffset, world.GridColumns - endOffset,
                        world.CurrentLevel.GetOccupiedCells()),
                        new Skin(world.GameImage.CoinSprites, world.CELL_WIDTH, world.CELL_HEIGHT)));
            }
            if (type == WorldObjectType.Cabbage) {
                world.Objects.add(new Cabbage(java.util.UUID.randomUUID().toString(), GridCell.GetRandomCell(startOffset, world.GridRows - endOffset, startOffset, world.GridColumns - endOffset,
                        world.CurrentLevel.GetOccupiedCells()),
                        new Skin(world.GameImage.Cabbage, world.CELL_WIDTH, world.CELL_HEIGHT)));
            }
        }
    }
}
