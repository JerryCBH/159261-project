import java.util.ArrayList;

public class LevelOne extends BaseLevel implements ILevel{
    public LevelOne (HookshotHeroesGameEngine engine, GameImage gameImage, GameOptions gameOptions){
        super(engine, gameImage, gameOptions);
    }

    @Override
    public void RenderLevel() {
        basicLevelEnvironment();

        // Draw Lava
        for (int x = 40; x < 560; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 240);
            drawLavaWithCollision(x, 280);
            drawLavaWithCollision(x, 320);
        }

        //Internal walls
        for (int x = 200; x < 400; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 160);
        }

        //Draw doors
        Engine.drawImage(GameImage.DoorGreyOpen, 280,0);
        doorCollision(280,0);
        Engine.drawImage(GameImage.DoorGreyClosed, 280,560);
        doorCollision(280,560);
    }

    @Override
    public ILevel GetNextLevel() {
        return new LevelTwo(Engine, GameImage, GameOptions);
    }

    @Override
    public ILevel GetPreviousLevel() {
        return null;
    }

    @Override
    public GridCell[] GetBottomStartingPos() {
        return new GridCell[]{new GridCell(50, 23), new GridCell(50, 33)};
    }

    @Override
    public GridCell[] GetTopStartingPos() {
        return new GridCell[]{new GridCell(5, 23), new GridCell(5, 33)};
    }

    @Override
    public GridCell GetExitGrid() {
        return new GridCell(0, 27);
    }

    @Override
    public GridCell GetEntryGrid() {
        return new GridCell(56, 27);
    }
}
