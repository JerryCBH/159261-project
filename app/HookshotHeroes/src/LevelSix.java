import java.util.ArrayList;

public class LevelSix extends BaseLevel implements ILevel{
    public LevelSix (HookshotHeroesGameEngine engine, GameImage gameImage, GameOptions gameOptions){
        super(engine, gameImage, gameOptions);
    }
    @Override
    public void RenderLevel() {
        basicLevelEnvironment();

        //Draw lava
        for (int x = 80; x < 200; x += 40) {
            drawLavaWithCollision(x, 40);
        }
        for (int x = 200; x < 320; x += environmentSpriteSize) {
            for (int y = 80; y < 200; y += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int x = 400; x < 520; x += environmentSpriteSize) {
            for (int y = 200; y < 360; y += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int x = 200; x < 320; x += environmentSpriteSize) {
            for (int y = 400; y < 520; y += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int y = 240; y < 440; y += environmentSpriteSize) {
            drawLavaWithCollision(520, y);
        }
        for (int y = 200; y < 280; y += environmentSpriteSize) {
            drawLavaWithCollision(40, y);
        }
        for (int y = 320; y < 440; y += environmentSpriteSize) {
            drawLavaWithCollision(80, y);
        }
        for (int y = 400; y < 520; y += environmentSpriteSize) {
            drawLavaWithCollision(120, y);
        }
        for (int y = 400; y < 480; y += environmentSpriteSize) {
            drawLavaWithCollision(40, y);
        }
        for (int x = 160; x < 280; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 520);
        }
        drawLavaWithCollision(360, 320);

        //Draw internal walls
        for (int x = 80; x < 200; x += environmentSpriteSize) {
            for (int y = 200; y < 280; y += environmentSpriteSize) {
                drawWallFrontWithCollision(x, y);
            }
        }
        for (int x = 400; x < 520; x += environmentSpriteSize) {
            for (int y = 360; y < 520; y += environmentSpriteSize) {
                drawWallFrontWithCollision(x, y);
            }
        }
        for (int x = 40; x < 200; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 120);
        }
        for (int x = 200; x < 360; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 320);
        }
        for (int x = 280; x < 400; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 240);
        }
        for (int x = 40; x < 120; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 520);
        }
        for (int y = 280; y < 520; y += environmentSpriteSize) {
            drawWallFrontWithCollision(160, y);
        }
        for (int y = 400; y < 560; y += environmentSpriteSize) {
            drawWallFrontWithCollision(320, y);
        }
        for (int y = 80; y < 200; y += environmentSpriteSize) {
            drawWallFrontWithCollision(360, y);
        }
        for (int y = 40; y < 200; y += environmentSpriteSize) {
            drawWallFrontWithCollision(480, y);
        }
        drawWallFrontWithCollision(280, 40);
        drawWallFrontWithCollision(320, 120);
        drawWallFrontWithCollision(280, 200);
        drawWallFrontWithCollision(40, 320);
        drawWallFrontWithCollision(120, 280);
        drawWallFrontWithCollision(120, 320);
    }

    @Override
    public ILevel GetNextLevel() {
        return null;
    }

    @Override
    public ILevel GetPreviousLevel() {
        return new LevelFive(Engine, GameImage, GameOptions);
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
