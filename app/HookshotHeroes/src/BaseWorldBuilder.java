import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/****************************************************************************************
 * This class is the world builder base class.
 ****************************************************************************************/
public class BaseWorldBuilder {
    protected int startOffset = 5, endOffset = 10;

    public World CreateWorld(HookshotHeroesGameEngine engine, GameImage gameImage, GameAudio gameAudio, GameOptions options, ILevel level) {

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
                ball.Position = new Vector2D(startX + radius * 3 * i, startY);
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
            if (type == WorldObjectType.Minotaur) {
                world.Objects.add(new Minotaur("Minotaur", new GridCell(25, 25),
                        new Skin(world.GameImage.MinotaurUpSprites, world.GameImage.MinotaurLeftSprites, world.GameImage.MinotaurRightSprites, world.GameImage.MinotaurDownSprites, world.GameImage.Health, world.CELL_WIDTH, world.CELL_HEIGHT),
                        new KeyBinding(KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_PERIOD),
                        world.CurrentLevel.GetWallCells(), world.CurrentLevel.GetLavaCells(), world.CurrentLevel.GetOccupiedCells(), world.AudioRequests, world.EliminationRequests, world.AnimationRequests, world
                ));
            }
            if (type == WorldObjectType.GhostWizard) {
                world.Objects.add(new Minotaur("Ghost Wizard", new GridCell(25, 25),
                        new Skin(world.GameImage.MinotaurWithAxeUpSprites, world.GameImage.MinotaurWithAxeLeftSprites, world.GameImage.MinotaurWithAxeRightSprites, world.GameImage.MinotaurWithAxeDownSprites, world.GameImage.Health, world.CELL_WIDTH, world.CELL_HEIGHT),
                        new KeyBinding(KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_PERIOD),
                        world.CurrentLevel.GetWallCells(), world.CurrentLevel.GetLavaCells(), world.CurrentLevel.GetOccupiedCells(), world.AudioRequests, world.EliminationRequests, world.AnimationRequests, world
                ));
            }
            if (type == WorldObjectType.Skeleton) {
                // Can move across lava grids.
                world.Objects.add(new Skeleton("Lava Guard", world.CurrentLevel.GetNextLevelInfo()[0].Exit,
                        new Skin(world.GameImage.SkeletonUpSprites, world.GameImage.SkeletonLeftSprites, world.GameImage.SkeletonRightSprites, world.GameImage.SkeletonDownSprites, world.GameImage.Health, world.CELL_WIDTH, world.CELL_HEIGHT),
                        new KeyBinding(KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_PERIOD),
                        world.CurrentLevel.GetWallCells(), new ArrayList<>(), world.CurrentLevel.GetWallCells(), world.AudioRequests, world.EliminationRequests, world.AnimationRequests, world
                ));
            }
            if (type == WorldObjectType.FlyingTerror) {
                // Can move across lava grids.
                world.Objects.add(new FlyingTerror("Flying Terror", world.CurrentLevel.GetNextLevelInfo()[0].Exit,
                        new Skin(world.GameImage.FTUpSprites, world.GameImage.FTLeftSprites, world.GameImage.FTRightSprites, world.GameImage.FTDownSprites, world.GameImage.Health, world.CELL_WIDTH, world.CELL_HEIGHT),
                        new KeyBinding(KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_PERIOD),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), world.AudioRequests, world.EliminationRequests, world.AnimationRequests, world
                ));
            }
            if (type == WorldObjectType.NPC) {
                // Can move across lava grids.
                world.Objects.add(new AIPlayer("Ava", world.CurrentLevel.GetBottomStartingPos()[0],
                        new Skin(world.GameImage.ShuraUpSprites, world.GameImage.ShuraLeftSprites, world.GameImage.ShuraRightSprites, world.GameImage.ShuraDownSprites, world.GameImage.Health, world.CELL_WIDTH, world.CELL_HEIGHT),
                        new KeyBinding(KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_PERIOD),
                        world.CurrentLevel.GetWallCells(), world.CurrentLevel.GetLavaCells(), world.CurrentLevel.GetOccupiedCells(), world.AudioRequests, world.EliminationRequests, world.AnimationRequests, world
                ));
            }
        }
    }
}
