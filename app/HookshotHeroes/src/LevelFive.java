import java.util.ArrayList;

public class LevelFive extends BaseLevel implements ILevel {
    public LevelFive (HookshotHeroesGameEngine engine, GameImage gameImage, GameOptions gameOptions){
        super(engine, gameImage, gameOptions);
    }

    @Override
    public void RenderLevel() {
        basicLevelEnvironment();

        //Draw lava
        for (int y = 400; y < 520; y += environmentSpriteSize) {
            for (int x = 40; x < 300; x += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int y = 400; y < 520; y += environmentSpriteSize) {
            for (int x = 400; x < 520; x += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int y = 200; y < 320; y += environmentSpriteSize) {
            for (int x = 320; x < 400; x += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int y = 40; y < 120; y += environmentSpriteSize) {
            for (int x = 400; x < 520; x += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int x = 80; x < 200; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 200);
        }
        for (int y = 240; y < 360; y += environmentSpriteSize) {
            drawLavaWithCollision(280, y);
        }
        for (int x = 120; x < 280; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 360);
        }
        for (int x = 120; x < 240; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 520);
        }
        drawLavaWithCollision(40, 40);
        drawLavaWithCollision(80, 40);
        drawLavaWithCollision(120, 80);

        drawLavaWithCollision(240, 40);
        drawLavaWithCollision(240, 80);
        drawLavaWithCollision(280, 80);

        drawLavaWithCollision(400, 200);
        drawLavaWithCollision(400, 240);

        //Draw doors
        Engine.drawImage(GameImage.DoorGreyClosedSide, 0,80);
        doorCollision(0,80);
        Engine.drawImage(GameImage.DoorGreyOpen, 280,0);
        doorCollision(280,0);

        //Draw inner walls
        for (int x = 320; x < 520; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 120);
        }
        for (int x = 320; x < 440; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 160);
        }
        for (int x = 40; x < 280; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 160);
        }
        for (int x = 40; x < 280; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 240);
        }
        for (int x = 80; x < 280; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 320);
        }
        for (int y = 320; y < 560; y += environmentSpriteSize) {
            drawWallFrontWithCollision(320, y);
        }
        for (int y = 200; y < 400; y += environmentSpriteSize) {
            for (int x = 440; x < 520; x += environmentSpriteSize) {
                drawWallFrontWithCollision(x, y);
            }
        }
        for (int y = 80; y < 160; y += environmentSpriteSize) {
            drawWallFrontWithCollision(160, y);
        }
        drawWallFrontWithCollision(320, 80);
        drawWallFrontWithCollision(40, 520);

        drawWallFrontWithCollision(520, 440);

        drawWallFrontWithCollision(360, 520);

        drawWallFrontWithCollision(360, 320);
        drawWallFrontWithCollision(360, 360);
    }

    @Override
    public ILevel GetNextLevel() {
        return new LevelSix(Engine, GameImage, GameOptions);
    }

    @Override
    public ILevel GetPreviousLevel() {
        return new LevelFour(Engine, GameImage, GameOptions);
    }

    @Override
    public GridCell[] GetTopStartingPos() {
        return new GridCell[]{new GridCell(5, 23), new GridCell(5, 33)};
    }

    @Override
    public GridCell[] GetBottomStartingPos() {
        return new GridCell[]{new GridCell(50, 23), new GridCell(50, 33)};
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
    public LevelStartPos GetStartPos() {
        return super.StartPos;
    }

    @Override
    public void SetStartPos(LevelStartPos pos){
        super.StartPos = pos;
    }

    @Override
    public ArrayList<GridCell> GetOccupiedCells() {
        return OccupiedCells;
    }

    @Override
    public void SetLevelRendered(boolean flag) {
        IsLevelRendered = flag;
    }
}
