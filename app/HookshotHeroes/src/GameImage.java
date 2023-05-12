import java.awt.*;

/****************************************************************************************
 * This class load the game's images.
 ****************************************************************************************/
public class GameImage {
    public final Image SnakeHead, SnakeDot, Apple, Health, Mine, Broccoli, Explosion, Player;
    public final Image DoorGreyClosed, DoorGreyOpen, floor, lava, wallGreyLeftSide, wallGreyRightSide, wallGreyFront, DoorGreyOpenSide, DoorGreyClosedSide;
    public Image[] ExplosionSprites;
    public Image[] PlayerLeftRightSprites;
    public Image[] PlayerUpSprites;
    public Image[] PlayerDownSprites;
    public static final int PLAYER_WIDTH = 48;
    public static final int PLAYER_HEIGHT = 56;
    public GameImage(HookshotHeroesGameEngine engine){
        SnakeHead = engine.loadImage("head.png");
        SnakeDot = engine.loadImage("dot.png");
        Apple = engine.loadImage("apple.png");
        Health = engine.loadImage("heart.png");
        Mine = engine.loadImage("mine.png");
        Broccoli = engine.loadImage("broccoli.png");
        Explosion = engine.loadImage("explosion.png");
        Player = engine.loadImage("player.png");
        LoadExplosionSpriteSheet(engine);
        LoadPlayerSpriteSheet(engine);

        // Load environment images.
        DoorGreyClosed = engine.loadImage("./environment/DoorGreyClosed.png");
        DoorGreyOpen = engine.loadImage("./environment/DoorGreyOpen.png");
        floor = engine.loadImage("./environment/floor.png");
        lava = engine.loadImage("./environment/lava.png");
        wallGreyLeftSide = engine.loadImage("./environment/wallGreyLeftSide.png");
        wallGreyRightSide = engine.loadImage("./environment/wallGreyRightSide.png");
        wallGreyFront = engine.loadImage("./environment/wallGreyFront.png");
        DoorGreyOpenSide = engine.loadImage("./environment/DoorGreyOpenSide.png");
        DoorGreyClosedSide = engine.loadImage("./environment/DoorGreyClosedSide.png");
    }

    // Load from sprite sheet.
    private void LoadExplosionSpriteSheet(HookshotHeroesGameEngine engine){
        ExplosionSprites = new Image[50];
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                ExplosionSprites[j + 10 * i] = engine.subImage(Explosion, j*100, i*100, 100, 100);
            }
        }
    }

    // Load from sprite sheet.
    private void LoadPlayerSpriteSheet(HookshotHeroesGameEngine engine){
        PlayerLeftRightSprites = new Image[3];
        for (int j = 0; j < 3; j++) {
            PlayerLeftRightSprites[j] = engine.subImage(Player, j*PLAYER_WIDTH, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        }
        PlayerDownSprites = new Image[3];
        for (int j = 0; j < 3; j++) {
            PlayerDownSprites[j] = engine.subImage(Player, j*PLAYER_WIDTH, PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
        }
        PlayerUpSprites = new Image[3];
        for (int j = 0; j < 3; j++) {
            PlayerUpSprites[j] = engine.subImage(Player, j*PLAYER_WIDTH, PLAYER_HEIGHT*2, PLAYER_WIDTH, PLAYER_HEIGHT);
        }
    }
}
