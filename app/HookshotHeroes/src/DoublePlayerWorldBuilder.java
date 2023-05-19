import java.awt.event.KeyEvent;
import java.util.ArrayList;

/****************************************************************************************
 * This class creates a double player game world.
 ****************************************************************************************/
public class DoublePlayerWorldBuilder extends BaseWorldBuilder implements IWorldBuilder{
    @Override
    public IWorld Build(HookshotHeroesGameEngine engine, GameImage gameImage, GameAudio gameAudio, GameOptions options,
                        ILevel level, ArrayList<Player> players) {

        var world = super.CreateWorld(engine, gameImage, gameAudio, options, level);
        var grids = level.GetStartPos() == LevelStartPos.Top ? level.GetTopStartingPos() : level.GetBottomStartingPos();

        if (players != null && players.size() > 1) {
            for (var p : players) {
                p.WallCells = level.GetWallCells();
                p.LavaCells = level.GetLavaCells();
                p.OccupiedCells = level.GetOccupiedCells();
                p.AudioRequests = world.AudioRequests;
                p.EliminationRequests = world.EliminationRequests;
            }
            players.get(0).SetGridCell(grids[0]);
            players.get(1).SetGridCell(grids[1]);
            world.SetObjects(new IWorldObject[]
                    {
                            players.get(0),
                            players.get(1)
                    });
        } else {
            world.SetObjects(new IWorldObject[]
                    {
                            new Player("Lidia", grids[0],
                                    new Skin(world.GameImage.LidiaUpSprites, world.GameImage.LidiaLeftSprites, world.GameImage.LidiaRightSprites, world.GameImage.LidiaDownSprites, world.GameImage.Health, world.CELL_WIDTH, world.CELL_HEIGHT),
                                    new KeyBinding(KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_X),
                                    level.GetWallCells(), level.GetLavaCells(), level.GetOccupiedCells(), world.AudioRequests, world.EliminationRequests
                            ),
                            new Player("Shura", grids[1],
                                    new Skin(world.GameImage.ShuraUpSprites, world.GameImage.ShuraLeftSprites, world.GameImage.ShuraRightSprites, world.GameImage.ShuraDownSprites, world.GameImage.Health, world.CELL_WIDTH, world.CELL_HEIGHT),
                                    new KeyBinding(KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_PERIOD),
                                    level.GetWallCells(), level.GetLavaCells(), level.GetOccupiedCells(), world.AudioRequests, world.EliminationRequests
                            )
                    });
        }

        super.AddObjects(world, WorldObjectType.Mine, 5);
        super.AddObjects(world, WorldObjectType.Broccoli, 3);
        if (options.EnableBouncingBalls) {
            super.AddObjects(world, WorldObjectType.Ball, 5);
        }
        return world;
    }
}
