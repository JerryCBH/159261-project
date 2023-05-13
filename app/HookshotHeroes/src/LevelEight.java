import java.util.ArrayList;

public class LevelEight extends BaseLevel implements ILevel{
    public LevelEight (HookshotHeroesGameEngine engine, GameImage gameImage, GameOptions gameOptions){
        super(engine, gameImage, gameOptions);
    }

    @Override
    public void RenderLevel() {
        basicLevelEnvironment();

        //Draw Lava
        for (int y = 80; y < 160; y += environmentSpriteSize) {
            for (int x = 240; x < 360; x += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int x = 200; x < 280; x += environmentSpriteSize) {
            for (int y = 240; y < 360; y += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int x = 440; x < 520; x += environmentSpriteSize) {
            for (int y = 320; y < 440; y += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int x = 480; x < 560; x += environmentSpriteSize) {
            for (int y = 400; y < 520; y += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int y = 360; y < 480; y += environmentSpriteSize) {
            drawLavaWithCollision(40, y);
        }
        for (int y = 200; y < 320; y += environmentSpriteSize) {
            drawLavaWithCollision(360, y);
        }
        for (int x = 160; x < 280; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 480);
        }
        for (int x = 120; x < 240; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 200);
        }
        drawLavaWithCollision(400, 320);

        //Draw internal walls
        for (int y = 40; y < 200; y += environmentSpriteSize) {
            for (int x = 40; x < 160; x += environmentSpriteSize) {
                drawWallFrontWithCollision(x, y);
            }
        }
        for (int y = 40; y < 280; y += environmentSpriteSize) {
            for (int x = 400; x < 480; x += environmentSpriteSize) {
                drawWallFrontWithCollision(x, y);
            }
        }
        for (int y = 200; y < 480; y += environmentSpriteSize) {
            for (int x = 280; x < 360; x += environmentSpriteSize) {
                drawWallFrontWithCollision(x, y);
            }
        }
        for (int x = 80; x < 280; x += environmentSpriteSize) {
            for (int y = 400; y < 480; y += environmentSpriteSize) {
                drawWallFrontWithCollision(x, y);
            }
        }
        for (int y = 40; y < 320; y += environmentSpriteSize) {
            drawWallFrontWithCollision(520, y);
        }
        for (int y = 360; y < 560; y += environmentSpriteSize) {
            drawWallFrontWithCollision(360, y);
        }
        for (int y = 240; y < 400; y += environmentSpriteSize) {
            drawWallFrontWithCollision(120, y);
        }
        for (int y = 200; y < 280; y += environmentSpriteSize) {
            drawWallFrontWithCollision(40, y);
        }
        for (int x = 160; x < 240; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 160);
        }
        for (int x = 160; x < 280; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 520);
        }
        for (int x = 360; x < 480; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 480);
        }
        drawWallFrontWithCollision(40, 320);
        drawWallFrontWithCollision(80, 480);
        drawWallFrontWithCollision(320, 40);
        drawWallFrontWithCollision(360, 40);
        drawWallFrontWithCollision(320, 160);
        drawWallFrontWithCollision(400, 280);

        //Draw doors
        Engine.drawImage(GameImage.DoorGreyOpen, 480,0);
        doorCollision(480,0);
        Engine.drawImage(GameImage.DoorGreyOpenSide, 280,0);
        doorCollision(280,0);
        Engine.drawImage(GameImage.DoorGreyClosed, 280,560);
        doorCollision(280,560);
    }

    @Override
    public ILevel GetNextLevel() {
        return  new LevelNine(Engine, GameImage, GameOptions);
    }

    @Override
    public ILevel GetPreviousLevel() {
        return new LevelSeven(Engine, GameImage, GameOptions);
    }

    @Override
    public GridCell[] GetBottomStartingPos() {
        return new GridCell[]{new GridCell(50, 27), new GridCell(50, 37)};
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
