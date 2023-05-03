import java.awt.event.KeyEvent;

/****************************************************************************************
 * This class creates a double player game world.
 ****************************************************************************************/
public class DoublePlayerWorldBuilder extends BaseWorldBuilder implements IWorldBuilder{
    @Override
    public IWorld Build(TheGameEngine engine, GameImage gameImage, GameAudio gameAudio, GameOptions options) {
        var world = super.CreateWorld(engine, gameImage, gameAudio, options);
        world.SetObjects(new IWorldObject[]
                {
                        new Snake("Player A", GridCell.GetRandomCell(startOffset, world.GridRows - endOffset, startOffset, world.GridColumns - endOffset),
                                new Skin(world.GameImage.SnakeHead, world.GameImage.SnakeDot, world.GameImage.Health, world.CELL_WIDTH, world.CELL_HEIGHT),
                                new KeyBinding(KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_S)),
                        new Snake("Player B", GridCell.GetRandomCell(startOffset, world.GridRows - endOffset, startOffset, world.GridColumns - endOffset),
                                new Skin(world.GameImage.SnakeHead, world.GameImage.SnakeDot, world.GameImage.Health, world.CELL_WIDTH, world.CELL_HEIGHT),
                                new KeyBinding(KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN))
                });
        super.AddObjects(world, WorldObjectType.Apple, 5);
        super.AddObjects(world, WorldObjectType.Mine, 5);
        super.AddObjects(world, WorldObjectType.Broccoli, 2);
        if (options.EnableBouncingBalls) {
            super.AddObjects(world, WorldObjectType.Ball, 5);
        }
        return world;
    }
}
