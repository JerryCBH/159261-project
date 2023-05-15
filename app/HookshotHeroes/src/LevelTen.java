import java.util.ArrayList;

public class LevelTen extends BaseLevel implements ILevel{
    public LevelTen (HookshotHeroesGameEngine engine, GameImage gameImage, GameOptions gameOptions){
        super(engine, gameImage, gameOptions);
    }

    @Override
    public void RenderLevel() {
        basicLevelEnvironment();

        //Draw doors
        Engine.drawImage(GameImage.DoorGreyOpenSide, 560,280);
        doorCollision(560,280);

        //Draw chest
        Engine.drawImage(GameImage.ChestSide, 280, 280);
    }

    @Override
    public ILevel GetNextLevel() {
        return null;
    }

    @Override
    public ILevel GetPreviousLevel() {
        return new LevelNine(Engine, GameImage, GameOptions);
    }

    @Override
    public GridCell[] GetBottomStartingPos() {
        return new GridCell[]{new GridCell(25, 50), new GridCell(33, 50)};
    }

    @Override
    public GridCell[] GetTopStartingPos() {
        return new GridCell[]{new GridCell(5, 23), new GridCell(5, 33)};
    }

    @Override
    public GridCell GetExitGrid() {
        return new GridCell(27, 27);
    }

    @Override
    public GridCell GetEntryGrid() {
        return new GridCell(56, 27);
    }
}
