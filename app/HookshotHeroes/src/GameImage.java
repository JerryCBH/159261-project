import java.awt.*;

/****************************************************************************************
 * This class load the game's images.
 ****************************************************************************************/
public class GameImage {
    public final Image SnakeHead, SnakeDot, Apple, Health, Mine, Broccoli, Explosion, Player, Lidia, Shura, Minotaur, Barrels, Cabbage, Bomb, Chests;
    public final Image DoorGreyClosed, DoorGreyOpen, floor, lava, wallGreyLeftSide, wallGreyRightSide, wallGreyFront, DoorGreyOpenSide, DoorGreyClosedSide, DoorGreyClosedLeftSide, DoorGreyClosedRightSide, DoorGreyOpenLeftSide, ChestFront, ChestSide, ChestBack;
    public Image[] ExplosionSprites;
    public Image[] PlayerLeftRightSprites;
    public Image[] PlayerUpSprites;
    public Image[] PlayerDownSprites;
    public Image[] LidiaLeftSprites;
    public Image[] LidiaRightSprites;
    public Image[] LidiaUpSprites;
    public Image[] LidiaDownSprites;
    public Image[] ShuraLeftSprites;
    public Image[] ShuraRightSprites;
    public Image[] ShuraUpSprites;
    public Image[] ShuraDownSprites;
    public Image[] MinotaurUpSprites;
    public Image[] MinotaurDownSprites;
    public Image[] MinotaurLeftSprites;
    public Image[] MinotaurRightSprites;
    public Image[] CoinSprites;
    public Image[] BombSprites;
    public Image[] SpecialChestSprites;
    public static final int PLAYER_WIDTH = 48;
    public static final int PLAYER_HEIGHT = 56;
    public static final int LIDIA_WIDTH = 64;
    public static final int LIDIA_HEIGHT = 64;
    public static final int Minotaur_WIDTH = 48;
    public static final int Minotaur_HEIGHT = 64;
    public static final int BARREL_HEIGHT = 32;
    public static final int BARREL_WIDTH = 32;
    public static final int BOMB_WIDTH = 20;
    public static final int BOMB_HEIGHT = 26;
    public GameImage(HookshotHeroesGameEngine engine){
        SnakeHead = engine.loadImage("head.png");
        SnakeDot = engine.loadImage("dot.png");
        Apple = engine.loadImage("apple.png");
        Health = engine.loadImage("heart.png");
        Mine = engine.loadImage("mine.png");
        Broccoli = engine.loadImage("broccoli.png");
        Explosion = engine.loadImage("explosion.png");
        Player = engine.loadImage("player.png");
        Lidia = engine.loadImage("lidia.png");
        Shura = engine.loadImage("shura.png");
        Minotaur = engine.loadImage("minotaur.png");
        Barrels = engine.loadImage("barrels.png");
        Bomb = engine.loadImage("bomb.png");
        Cabbage = engine.subImage(Barrels, 64, 32, BARREL_WIDTH, BARREL_HEIGHT);
        Chests = engine.loadImage("environment/chests.png");
        LoadExplosionSpriteSheet(engine);
        LoadPlayerSpriteSheet(engine);
        LoadLidiaSpriteSheet(engine);
        LoadShuraSpriteSheet(engine);
        LoadCoinSpriteSheet(engine);
        LoadBombSpriteSheet(engine);
        LoadMinotaurSpriteSheet(engine);
        LoadSpecialChestSpriteSheet(engine);

        // Load environment images.
        DoorGreyClosed = engine.loadImage("environment/DoorGreyClosed.png");
        DoorGreyOpen = engine.loadImage("environment/DoorGreyOpen.png");
        DoorGreyClosedLeftSide = engine.loadImage("environment/DoorGreyClosedLeftSide.png");
        DoorGreyClosedRightSide = engine.loadImage("environment/DoorGreyClosedRightSide.png");
        DoorGreyOpenLeftSide = engine.loadImage("environment/DoorGreyOpenLeftSide.png");
        floor = engine.loadImage("environment/floor.png");
        lava = engine.loadImage("environment/lava.png");
        wallGreyLeftSide = engine.loadImage("environment/wallGreyLeftSide.png");
        wallGreyRightSide = engine.loadImage("environment/wallGreyRightSide.png");
        wallGreyFront = engine.loadImage("environment/wallGreyFront.png");
        DoorGreyOpenSide = engine.loadImage("environment/DoorGreyOpenSide.png");
        DoorGreyClosedSide = engine.loadImage("environment/DoorGreyClosedSide.png");
        ChestFront = engine.loadImage("environment/ChestFront.png");
        ChestBack = engine.loadImage("environment/ChestBack.png");
        ChestSide = engine.loadImage("environment/ChestSide.png");
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

    private void LoadLidiaSpriteSheet(HookshotHeroesGameEngine engine){
        LidiaUpSprites = new Image[9];
        for (int j = 0; j < 9; j++) {
            LidiaUpSprites[j] = engine.subImage(Lidia, j*LIDIA_WIDTH, 0, LIDIA_WIDTH, LIDIA_HEIGHT);
        }
        LidiaLeftSprites = new Image[9];
        for (int j = 0; j < 9; j++) {
            LidiaLeftSprites[j] = engine.subImage(Lidia, j*LIDIA_WIDTH, LIDIA_HEIGHT, LIDIA_WIDTH, LIDIA_HEIGHT);
        }
        LidiaDownSprites = new Image[9];
        for (int j = 0; j < 9; j++) {
            LidiaDownSprites[j] = engine.subImage(Lidia, j*LIDIA_WIDTH, LIDIA_HEIGHT*2, LIDIA_WIDTH, LIDIA_HEIGHT);
        }
        LidiaRightSprites = new Image[9];
        for (int j = 0; j < 9; j++) {
            LidiaRightSprites[j] = engine.subImage(Lidia, j*LIDIA_WIDTH, LIDIA_HEIGHT*3, LIDIA_WIDTH, LIDIA_HEIGHT);
        }
    }

    private void LoadShuraSpriteSheet(HookshotHeroesGameEngine engine){
        ShuraUpSprites = new Image[9];
        for (int j = 0; j < 9; j++) {
            ShuraUpSprites[j] = engine.subImage(Shura, j*LIDIA_WIDTH, 0, LIDIA_WIDTH, LIDIA_HEIGHT);
        }
        ShuraLeftSprites = new Image[9];
        for (int j = 0; j < 9; j++) {
            ShuraLeftSprites[j] = engine.subImage(Shura, j*LIDIA_WIDTH, LIDIA_HEIGHT, LIDIA_WIDTH, LIDIA_HEIGHT);
        }
        ShuraDownSprites = new Image[9];
        for (int j = 0; j < 9; j++) {
            ShuraDownSprites[j] = engine.subImage(Shura, j*LIDIA_WIDTH, LIDIA_HEIGHT*2, LIDIA_WIDTH, LIDIA_HEIGHT);
        }
        ShuraRightSprites = new Image[9];
        for (int j = 0; j < 9; j++) {
            ShuraRightSprites[j] = engine.subImage(Shura, j*LIDIA_WIDTH, LIDIA_HEIGHT*3, LIDIA_WIDTH, LIDIA_HEIGHT);
        }
    }

    private void LoadMinotaurSpriteSheet(HookshotHeroesGameEngine engine) {
        MinotaurDownSprites = new Image[3];
        for (int j = 0; j < 3; j ++) {
            MinotaurDownSprites[j] = engine.subImage(Minotaur, j*Minotaur_WIDTH, 0, Minotaur_WIDTH, Minotaur_HEIGHT);
        }
        MinotaurLeftSprites = new Image[3];
        for (int j = 0; j < 3; j ++) {
            MinotaurLeftSprites[j] = engine.subImage(Minotaur, j*Minotaur_WIDTH, Minotaur_HEIGHT, Minotaur_WIDTH, Minotaur_HEIGHT);
        }
        MinotaurRightSprites = new Image[3];
        for (int j = 0; j < 3; j ++) {
            MinotaurRightSprites[j] = engine.subImage(Minotaur, j*Minotaur_WIDTH, Minotaur_HEIGHT*2, Minotaur_WIDTH, Minotaur_HEIGHT);
        }
        MinotaurUpSprites = new Image[3];
        for (int j = 0; j < 3; j ++) {
            MinotaurUpSprites[j] = engine.subImage(Minotaur, j*Minotaur_WIDTH, Minotaur_HEIGHT*3, Minotaur_WIDTH, Minotaur_HEIGHT);
        }
    }

    private void LoadCoinSpriteSheet(HookshotHeroesGameEngine engine){
        CoinSprites = new Image[9];
        for (int j = 1; j <= 9; j++) {
            CoinSprites[j - 1] = engine.loadImage("goldCoin" + j + ".png");
        }
    }

    private void LoadBombSpriteSheet(HookshotHeroesGameEngine engine){
        BombSprites = new Image[4];
        for (int j = 0; j < 4; j++) {
            BombSprites[j] = engine.subImage(Bomb, j*BOMB_WIDTH, 0, BOMB_WIDTH, BOMB_HEIGHT);
        }
    }

    private void LoadSpecialChestSpriteSheet(HookshotHeroesGameEngine engine){
        SpecialChestSprites = new Image[2];
        SpecialChestSprites[0] = engine.subImage(Chests, 291, 67, 25, 25);
        SpecialChestSprites[1] = engine.subImage(Chests, 291, 95, 25, 29);
    }
}
