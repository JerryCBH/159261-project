import java.awt.*;

/****************************************************************************************
 * This class load the game's images.
 ****************************************************************************************/
public class GameImage {
    public final Image SnakeHead, SnakeDot, Apple, Health, Mine, Broccoli, Explosion, Player, Lidia, Shura, Minotaur, MinotaurWithAxe, Barrels, Cabbage, Bomb, Chests, Skeleton, FlyingTerror, Ava, Sarah;
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
    public Image[] MinotaurWithAxeUpSprites;
    public Image[] MinotaurWithAxeDownSprites;
    public Image[] MinotaurWithAxeLeftSprites;
    public Image[] MinotaurWithAxeRightSprites;
    public Image[] CoinSprites;
    public Image[] BombSprites;
    public Image[] SpecialChestSprites;
    public Image[] SkeletonLeftSprites;
    public Image[] SkeletonRightSprites;
    public Image[] SkeletonUpSprites;
    public Image[] SkeletonDownSprites;
    public Image[] FTLeftSprites;
    public Image[] FTRightSprites;
    public Image[] FTUpSprites;
    public Image[] FTDownSprites;
    public Image[] AvaLeftSprites;
    public Image[] AvaRightSprites;
    public Image[] AvaUpSprites;
    public Image[] AvaDownSprites;
    public Image[] SarahSprites;
    public static final int PLAYER_WIDTH = 48;
    public static final int PLAYER_HEIGHT = 56;
    public static final int LIDIA_WIDTH = 64;
    public static final int LIDIA_HEIGHT = 64;

    public static final int Minotaur_WIDTH = 48;
    public static final int Minotaur_HEIGHT = 64;
    public static final int MinotaurWithAxe_WIDTH = 45;
    public static final int MinotaurWithAxe_HEIGHT = 58;

    public static final int BARREL_HEIGHT = 32;
    public static final int BARREL_WIDTH = 32;
    public static final int BOMB_WIDTH = 20;
    public static final int BOMB_HEIGHT = 26;
    public static final int SKELETON_WIDTH = 64;
    public static final int SKELETON_HEIGHT = 64;
    public static final int FT_WIDTH = 128;
    public static final int FT_HEIGHT = 128;
    public static final int AVA_WIDTH = 100;
    public static final int AVA_HEIGHT = 100;
    public static final int SARAH_WIDTH = 27;
    public static final int SARAH_HEIGHT = 50;

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
        MinotaurWithAxe = engine.loadImage("minotaurWithAxe.png");
        Barrels = engine.loadImage("barrels.png");
        Bomb = engine.loadImage("bomb.png");
        Cabbage = engine.subImage(Barrels, 64, 32, BARREL_WIDTH, BARREL_HEIGHT);
        Chests = engine.loadImage("environment/chests.png");
        Skeleton = engine.loadImage("skeleton.png");
        FlyingTerror = engine.loadImage("flying_terror.png");
        Ava = engine.loadImage("avalon.png");
        Sarah = engine.loadImage("sarah.png");

        LoadExplosionSpriteSheet(engine);
        LoadPlayerSpriteSheet(engine);
        LoadLidiaSpriteSheet(engine);
        LoadShuraSpriteSheet(engine);
        LoadCoinSpriteSheet(engine);
        LoadBombSpriteSheet(engine);
        LoadMinotaurSpriteSheet(engine);
        LoadMinotaurWithAxeSpriteSheet(engine);
        LoadSpecialChestSpriteSheet(engine);
        LoadSkeletonSpriteSheet(engine);
        LoadFTSpriteSheet(engine);
        LoadAvaSpriteSheet(engine);
        LoadSarahSpriteSheet(engine);

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
    private void LoadMinotaurWithAxeSpriteSheet(HookshotHeroesGameEngine engine) {
        MinotaurWithAxeDownSprites = new Image[2];
        for (int j = 0; j < 2; j ++) {
            MinotaurWithAxeDownSprites[j] = engine.subImage(MinotaurWithAxe, j* MinotaurWithAxe_WIDTH, 0, MinotaurWithAxe_WIDTH, MinotaurWithAxe_HEIGHT);
        }
        MinotaurWithAxeLeftSprites = new Image[2];
        for (int j = 0; j < 2; j ++) {
            MinotaurWithAxeLeftSprites[j] = engine.subImage(MinotaurWithAxe, j*MinotaurWithAxe_WIDTH, MinotaurWithAxe_HEIGHT*2, MinotaurWithAxe_WIDTH, MinotaurWithAxe_HEIGHT);
        }
        MinotaurWithAxeRightSprites = new Image[2];
        for (int j = 0; j < 2; j ++) {
            MinotaurWithAxeRightSprites[j] = engine.subImage(MinotaurWithAxe, j*MinotaurWithAxe_WIDTH, MinotaurWithAxe_HEIGHT, MinotaurWithAxe_WIDTH, MinotaurWithAxe_HEIGHT);
        }
        MinotaurWithAxeUpSprites = new Image[2];
        for (int j = 0; j < 2; j ++) {
            MinotaurWithAxeUpSprites[j] = engine.subImage(MinotaurWithAxe, j*MinotaurWithAxe_WIDTH, MinotaurWithAxe_HEIGHT*3, MinotaurWithAxe_WIDTH, MinotaurWithAxe_HEIGHT);
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

    private void LoadSarahSpriteSheet(HookshotHeroesGameEngine engine){
        SarahSprites = new Image[16];
        for (int j = 0; j < 16; j++) {
            SarahSprites[j] = engine.subImage(Sarah, j*SARAH_WIDTH, 0, SARAH_WIDTH, SARAH_HEIGHT);
        }
    }

    private void LoadSpecialChestSpriteSheet(HookshotHeroesGameEngine engine){
        SpecialChestSprites = new Image[2];
        SpecialChestSprites[0] = engine.subImage(Chests, 291, 67, 25, 25);
        SpecialChestSprites[1] = engine.subImage(Chests, 291, 95, 25, 29);
    }

    private void LoadSkeletonSpriteSheet(HookshotHeroesGameEngine engine){
        SkeletonUpSprites = new Image[9];
        for (int j = 0; j < 9; j++) {
            SkeletonUpSprites[j] = engine.subImage(Skeleton, j*SKELETON_WIDTH, SKELETON_HEIGHT*8, SKELETON_WIDTH, SKELETON_HEIGHT);
        }
        SkeletonRightSprites = new Image[9];
        for (int j = 0; j < 9; j++) {
            SkeletonRightSprites[j] = engine.subImage(Skeleton, j*SKELETON_WIDTH, SKELETON_HEIGHT*11, SKELETON_WIDTH, SKELETON_HEIGHT);
        }
        SkeletonDownSprites = new Image[9];
        for (int j = 0; j < 9; j++) {
            SkeletonDownSprites[j] = engine.subImage(Skeleton, j*SKELETON_WIDTH, SKELETON_HEIGHT*10, SKELETON_WIDTH, SKELETON_HEIGHT);
        }
        SkeletonLeftSprites = new Image[9];
        for (int j = 0; j < 9; j++) {
            SkeletonLeftSprites[j] = engine.subImage(Skeleton, j*SKELETON_WIDTH, SKELETON_HEIGHT*9, SKELETON_WIDTH, SKELETON_HEIGHT);
        }
    }

    private void LoadFTSpriteSheet(HookshotHeroesGameEngine engine){
        FTUpSprites = new Image[10];
        for (int j = 0; j < 10; j++) {
            FTUpSprites[j] = engine.subImage(FlyingTerror, j*FT_WIDTH, FT_HEIGHT*2, FT_WIDTH, FT_HEIGHT);
        }
        FTRightSprites = new Image[10];
        for (int j = 0; j < 10; j++) {
            FTRightSprites[j] = engine.subImage(FlyingTerror, j*FT_WIDTH, FT_HEIGHT*4, FT_WIDTH, FT_HEIGHT);
        }
        FTDownSprites = new Image[10];
        for (int j = 0; j < 10; j++) {
            FTDownSprites[j] = engine.subImage(FlyingTerror, j*FT_WIDTH, FT_HEIGHT*6, FT_WIDTH, FT_HEIGHT);
        }
        FTLeftSprites = new Image[10];
        for (int j = 0; j < 10; j++) {
            FTLeftSprites[j] = engine.subImage(FlyingTerror, j*FT_WIDTH, 0, FT_WIDTH, FT_HEIGHT);
        }
    }

    private void LoadAvaSpriteSheet(HookshotHeroesGameEngine engine){
        AvaUpSprites = new Image[7];
        for (int j = 0; j < 7; j++) {
            AvaUpSprites[j] = engine.subImage(Ava, j*AVA_WIDTH, AVA_HEIGHT, AVA_WIDTH, AVA_HEIGHT);
        }
        AvaRightSprites = new Image[7];
        for (int j = 0; j < 7; j++) {
            AvaRightSprites[j] = engine.subImage(Ava, j*AVA_WIDTH, AVA_HEIGHT*2, AVA_WIDTH, AVA_HEIGHT);
        }
        AvaDownSprites = new Image[7];
        for (int j = 0; j < 7; j++) {
            AvaDownSprites[j] = engine.subImage(Ava, j*AVA_WIDTH, 0, AVA_WIDTH, AVA_HEIGHT);
        }
        AvaLeftSprites = new Image[7];
        for (int j = 0; j < 7; j++) {
            AvaLeftSprites[j] = engine.subImage(Ava, j*AVA_WIDTH, AVA_HEIGHT*3, AVA_WIDTH, AVA_HEIGHT);
        }
    }
}
