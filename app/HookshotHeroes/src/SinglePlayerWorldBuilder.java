import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;

/****************************************************************************************
 * This class creates a single player game world.
 ****************************************************************************************/
public class SinglePlayerWorldBuilder extends BaseWorldBuilder implements IWorldBuilder{
    @Override
    public IWorld Build(HookshotHeroesGameEngine engine, GameImage gameImage, GameAudio gameAudio, GameOptions options,
                        ILevel level, ArrayList<Player> players) {

        var world = super.CreateWorld(engine, gameImage, gameAudio, options, level);
        var grid = level.GetStartPos() == LevelStartPos.Top ? level.GetTopStartingPos()[0] : level.GetBottomStartingPos()[0];

        Player player;
        if (players != null && players.size() > 0) {
            player = players.get(0);
            player.WallCells = level.GetWallCells();
            player.LavaCells = level.GetLavaCells();
            player.OccupiedCells = level.GetOccupiedCells();
            player.AudioRequests = world.AudioRequests;
            player.EliminationRequests = world.EliminationRequests;
            player.AnimationRequests = world.AnimationRequests;
            player.World = world;
            player.SetGridCell(grid);
        } else {
            player = new Player("Lidia", grid,
                    new Skin(world.GameImage.LidiaUpSprites, world.GameImage.LidiaLeftSprites, world.GameImage.LidiaRightSprites, world.GameImage.LidiaDownSprites, world.GameImage.Health, world.CELL_WIDTH, world.CELL_HEIGHT),
                    new KeyBinding(KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_X),
                    level.GetWallCells(), level.GetLavaCells(), level.GetOccupiedCells(), world.AudioRequests, world.EliminationRequests, world.AnimationRequests, world
            );
        }
        var objects = new ArrayList<IWorldObject>();
        objects.add(player);
        world.SetObjects(objects);

        super.AddObjects(world, WorldObjectType.Mine, 3);
        super.AddObjects(world, WorldObjectType.Cabbage, 2);
        super.AddObjects(world, WorldObjectType.Coin, 10);
        if (options.EnableBouncingBalls) {
            super.AddObjects(world, WorldObjectType.Ball, 5);
        }
        if (level instanceof LevelFour){
            super.AddObjects(world, WorldObjectType.Minotaur, 1);
        }
        return world;
    }
}
