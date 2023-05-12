import java.util.ArrayList;

public class LevelSeven extends BaseLevel implements ILevel{
    public LevelSeven (HookshotHeroesGameEngine engine, GameImage gameImage, GameOptions gameOptions){
        super(engine, gameImage, gameOptions);
    }

    @Override
    public void RenderLevel() {
        basicLevelEnvironment();

        //Draw lava
        for (int x = 120; x < 240; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 80);
        }
        for (int x = 120; x < 240; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 160);
        }
        for (int x = 120; x < 240; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 360);
        }
        for (int x = 360; x < 480; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 400);
        }
        for (int x = 320; x < 440; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 520);
        }
        for (int x = 480; x < 560; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 160);
        }
        for (int y = 280; y < 360; y += environmentSpriteSize) {
            drawLavaWithCollision(480, y);
        }
        for (int y = 480; y < 560; y += environmentSpriteSize) {
            drawLavaWithCollision(80, y);
        }
        for (int x = 280; x < 560; x += environmentSpriteSize) {
            for (int y = 80; y < 160; y += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int y = 200; y < 320; y += environmentSpriteSize) {
            for (int x = 40; x < 120; x += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int y = 320; y < 440; y += environmentSpriteSize) {
            for (int x = 400; x < 480; x += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        drawLavaWithCollision(200, 480);
        drawLavaWithCollision(480, 440);
        drawLavaWithCollision(440, 280);
        drawLavaWithCollision(160, 480);

        //Draw internal walls
        for (int y = 200; y < 360; y += environmentSpriteSize) {
            for (int x = 120; x < 240; x += environmentSpriteSize) {
                drawWallFrontWithCollision(x, y);
            }
        }
        for (int y = 480; y < 560; y += environmentSpriteSize) {
            for (int x = 480; x < 560; x += environmentSpriteSize) {
                drawWallFrontWithCollision(x, y);
            }
        }
        for (int y = 400; y < 520; y += environmentSpriteSize) {
            for (int x = 200; x < 360; x += environmentSpriteSize) {
                drawWallFrontWithCollision(x, y);
            }
        }
        for (int x = 120; x < 280; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 40);
        }
        for (int x = 40; x < 240; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 120);
        }
        for (int x = 280; x < 480; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 160);
        }
        for (int x = 360; x < 560; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 240);
        }
        for (int x = 40; x < 200; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 400);
        }
        for (int x = 360; x < 440; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 480);
        }
        for (int x = 480; x < 560; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 400);
        }
        for (int x = 240; x < 400; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 320);
        }
        for (int y = 480; y < 560; y += environmentSpriteSize) {
            drawWallFrontWithCollision(120, y);
        }
        for (int y = 200; y < 280; y += environmentSpriteSize) {
            drawWallFrontWithCollision(280, y);
        }
        drawWallFrontWithCollision(40, 80);
        drawWallFrontWithCollision(40, 360);
        drawWallFrontWithCollision(360, 280);
        drawWallFrontWithCollision(360, 440);

        //Draw doors
        Engine.drawImage(GameImage.DoorGreyOpen, 280,0);
        doorCollision(280,0);
        Engine.drawImage(GameImage.DoorGreyClosed, 280,560);
        doorCollision(280,560);
    }

    @Override
    public ILevel GetNextLevel() {
        return new LevelEight(Engine, GameImage, GameOptions);
    }

    @Override
    public ILevel GetPreviousLevel() {
        return new LevelSix(Engine, GameImage, GameOptions);
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
