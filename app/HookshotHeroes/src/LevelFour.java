public class LevelFour extends BaseLevel implements ILevel{
    public boolean DoorEnterTop = true;
    public boolean DoorEnterRight = true;
    public LevelFour (HookshotHeroesGameEngine engine, GameImage gameImage, GameOptions gameOptions){
        super(engine, gameImage, gameOptions);
    }
    @Override
    public void RenderLevel() {
        basicLevelEnvironment();

        //Draw doors
        if (Minotaur.IsDead == true) {
            Engine.drawImage(GameImage.DoorGreyOpen, 280, 0);
            doorCollision(280, 0);
            Engine.drawImage(GameImage.DoorGreyOpenSide, 560, 280);
            doorCollision(560, 280);
        } else {
            Engine.drawImage(GameImage.DoorGreyClosed, 280, 0);
            Engine.drawImage(GameImage.DoorGreyClosedRightSide, 560, 280);
        }
        Engine.drawImage(GameImage.DoorGreyClosed, 280,560);
        doorCollision(280,560);
    }

    @Override
    public ILevel GetNextLevel() {
        if (DoorEnterTop == true) {
            return new LevelSix(Engine, GameImage, GameOptions);
        } else if (DoorEnterRight == true) {
            return new LevelFive(Engine, GameImage, GameOptions);
        } else {
            // Return null or the appropriate fallback level if no specific condition is met.
            return null;
        }
    }


    @Override
    public ILevel GetPreviousLevel() {
        return new LevelThree(Engine, GameImage, GameOptions);
    }

    @Override
    public GridCell[] GetTopStartingPos() {
        return new GridCell[]{new GridCell(5, 23), new GridCell(5, 33)};
    }

    @Override
    public GridCell[] GetBottomStartingPos() {
        return new GridCell[]{new GridCell(50, 27), new GridCell(50, 33)};
    }

    @Override
    public GridCell GetExitGrid() {
        return new GridCell(0, 27);
    }

    @Override
    public GridCell GetEntryGrid() {
        return new GridCell(56, 27);
    }

    @Override
    public String GetLevelName() {
        return "Level 4";
    }

    @Override
    public void ApplyLevelMusic(GameAudio gameAudio){
        gameAudio.ApplyTheme(Engine, "lava.wav", GameOptions.MasterVolume);
    }
}
