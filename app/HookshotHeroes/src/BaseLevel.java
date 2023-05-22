import java.awt.*;
import java.util.ArrayList;

public class BaseLevel {

    // Game references and options.
    public HookshotHeroesGameEngine Engine;
    public GameImage GameImage;
    public GameOptions GameOptions;

    // Flag for level rendering
    public boolean IsLevelRendered = false;
    int environmentSpriteSize = 40;
    public final int CELL_WIDTH = 10, CELL_HEIGHT = 10;

    public LevelStartPos StartPos = LevelStartPos.Bottom;
    public ArrayList<GridCell> WallCells;
    public ArrayList<GridCell> LavaCells;
    public ArrayList<GridCell> OccupiedCells;
    public ArrayList<Chest> Chests;

    public BaseLevel (HookshotHeroesGameEngine engine, GameImage gameImage, GameOptions gameOptions){
        Engine = engine;
        GameImage = gameImage;
        GameOptions = gameOptions;
        WallCells = new ArrayList<>();
        LavaCells = new ArrayList<>();
        OccupiedCells = new ArrayList<>();
        Chests = new ArrayList<>();
    }

    public void drawWallFrontWithCollision(int x, int y) {
        Engine.drawImage(GameImage.wallGreyFront, x, y);
        Engine.drawRectangle(x, y, 40, 40);

        //Add collision logic here
        AddWallCell(x, y);
    }

    public void wallSideCollision(int x, int y) {
        Engine.drawRectangle(x, y, 40, 40);

        //Add collision logic here
        AddWallCell(x, y);
    }

    public void doorCollision(int x, int y) {
        Engine.drawRectangle(x, y, 40, 40);
    }

    public void drawLavaWithCollision(int x, int y) {
        Engine.drawImage(GameImage.lava, x, y);
        Engine.changeColor(Color.pink);
        Engine.drawRectangle(x, y, 40, 40);
        Engine.changeColor(Color.BLACK);

        //Add collision logic here
        AddLavaCell(x, y);
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

    private void AddWallCell(int x, int y){
        if (!IsLevelRendered) {
           WallCells.add(new GridCell(y / CELL_WIDTH, x / CELL_HEIGHT));
           OccupiedCells.add(new GridCell(y / CELL_WIDTH, x / CELL_HEIGHT));
        }
    }

    private void AddLavaCell(int x, int y){
        if (!IsLevelRendered) {
            LavaCells.add(new GridCell(y / CELL_WIDTH, x / CELL_HEIGHT));
            OccupiedCells.add(new GridCell(y / CELL_WIDTH, x / CELL_HEIGHT));
        }
    }

    public LevelStartPos GetStartPos() {
        return StartPos;
    }

    public void SetStartPos(LevelStartPos pos){
        StartPos = pos;
    }

    public ArrayList<GridCell> GetWallCells() {
        return WallCells;
    }

    public ArrayList<GridCell> GetLavaCells() {
        return LavaCells;
    }

    public ArrayList<GridCell> GetOccupiedCells() {
        return OccupiedCells;
    }

    public void SetLevelRendered(boolean flag) {
        IsLevelRendered = flag;
    }

    public ArrayList<Chest> GetChests(){
        return Chests;
    }

    public void AddChest(int x, int y, boolean hasMessage, String message, boolean isTalkingChest){
        Chests.add(new Chest(new GridCell(y / CELL_WIDTH, x / CELL_HEIGHT), hasMessage, message, isTalkingChest));
    }

    public void ApplyLevelMusic(GameAudio gameAudio){
    }
}
