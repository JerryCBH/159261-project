import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

/****************************************************************************************
 * This class load the game's images.
 ****************************************************************************************/
public class GameImage {
    public final Image SnakeHead, SnakeDot, Apple, Health, Mine, Broccoli, Explosion;
    public Image[] ExplosionSprites;
    public GameImage(TheGameEngine engine){
        SnakeHead = engine.loadImage("head.png");
        SnakeDot = engine.loadImage("dot.png");
        Apple = engine.loadImage("apple.png");
        Health = engine.loadImage("heart.png");
        Mine = engine.loadImage("mine.png");
        Broccoli = engine.loadImage("broccoli.png");
        Explosion = engine.loadImage("explosion.png");
        LoadExplosionSpriteSheet(engine);
    }

    // Load from sprite sheet.
    private void LoadExplosionSpriteSheet(TheGameEngine engine){
        ExplosionSprites = new Image[50];
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                ExplosionSprites[j + 10 * i] = engine.subImage(Explosion, j*100, i*100, 100, 100);
            }
        }
    }
}
