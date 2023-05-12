import java.awt.*;
import java.util.ArrayList;

public class BaseLevel {
    public HookshotHeroesGameEngine Engine;
    public GameImage GameImage;
    public GameOptions GameOptions;

    // Flag for level rendering
    public boolean IsLevelRendered = false;
    int environmentSpriteSize = 40;
    public final int CELL_WIDTH = 10, CELL_HEIGHT = 10;

    public LevelStartPos StartPos = LevelStartPos.Bottom;
    public ArrayList<GridCell> OccupiedCells;

    public BaseLevel (HookshotHeroesGameEngine engine, GameImage gameImage, GameOptions gameOptions){
        Engine = engine;
        GameImage = gameImage;
        GameOptions = gameOptions;
        OccupiedCells = new ArrayList<>();
    }

    public void drawWallFrontWithCollision(int x, int y) {
        Engine.drawImage(GameImage.wallGreyFront, x, y);
        Engine.changeColor(Color.red); //Just to see visually
        Engine.drawRectangle(x, y, 40, 40);

        //Add collision logic here
        AddOccupiedCell(x, y);
    }

    public void wallSideCollision(int x, int y) {
        Engine.changeColor(Color.red); // Just to see visually
        Engine.drawRectangle(x, y, 40, 40);

        //Add collision logic here
        AddOccupiedCell(x, y);
    }

    public void doorCollision(int x, int y) {
        Engine.changeColor(Color.green); // Just to see visually
        Engine.drawRectangle(x, y, 40, 40);

        //Add collision logic here
    }

    public void drawLavaWithCollision(int x, int y) {
        Engine.drawImage(GameImage.lava, x, y);
        Engine.changeColor(Color.pink); // Just to see visually
        Engine.drawRectangle(x, y, 40, 40);

        //Add collision logic here
        AddOccupiedCell(x, y);
    }

    public void basicLevelEnvironment() {
        //Floor
        for (int y = 0; y < GameOptions.Width; y += 100) {
            for (int x = 0; x < GameOptions.Height; x += 100) {
                Engine.drawImage(GameImage.floor, x, y);
            }
        }

        for (int x = 0; x < GameOptions.Width; x += environmentSpriteSize) {
            // Draw wall sprite on the top side
            drawWallFrontWithCollision(x, 0);

            // Draw wall sprite on the bottom side
            drawWallFrontWithCollision(x, GameOptions.Width - environmentSpriteSize);
        }
        for (int y = 0; y < GameOptions.Width - environmentSpriteSize; y += environmentSpriteSize) {
            // Draw wall sprite on the left side
            Engine.drawImage(GameImage.wallGreyLeftSide, 0, y);
            wallSideCollision(0, y);

            // Draw wall sprite on the right side
            Engine.drawImage(GameImage.wallGreyRightSide, GameOptions.Width - environmentSpriteSize, y);
            wallSideCollision(GameOptions.Width - environmentSpriteSize, y);
        }
    }

    private void AddOccupiedCell(int x, int y){
        if (!IsLevelRendered) {
           //OccupiedCells.add(new GridCell(y / CELL_WIDTH, x / CELL_HEIGHT));
        }
    }
}
