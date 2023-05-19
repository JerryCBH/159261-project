import java.awt.event.KeyEvent;

/****************************************************************************************
 * This class creates a single player game world.
 ****************************************************************************************/
public class SinglePlayerWorldBuilder extends BaseWorldBuilder implements IWorldBuilder{
    @Override
    public IWorld Build(HookshotHeroesGameEngine engine, GameImage gameImage, GameAudio gameAudio, GameOptions options, ILevel level) {
        var world = super.CreateWorld(engine, gameImage, gameAudio, options, level);
        var grid = level.GetStartPos() == LevelStartPos.Top? level.GetTopStartingPos()[0] : level.GetBottomStartingPos()[0];
        world.SetObjects(new IWorldObject[]
                {
                        new Player("Lidia", grid,
                                new Skin(world.GameImage.LidiaUpSprites, world.GameImage.LidiaLeftSprites, world.GameImage.LidiaRightSprites, world.GameImage.LidiaDownSprites, world.GameImage.Health, world.CELL_WIDTH, world.CELL_HEIGHT),
                                new KeyBinding(KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_X),
                                level.GetWallCells(), level.GetLavaCells(), level.GetOccupiedCells(), world.AudioRequests, world.EliminationRequests
                                )
                });
        super.AddObjects(world, WorldObjectType.Mine, 3);
        super.AddObjects(world, WorldObjectType.Broccoli, 2);
        if (options.EnableBouncingBalls) {
            super.AddObjects(world, WorldObjectType.Ball, 5);
        }
        return world;
    }
}
