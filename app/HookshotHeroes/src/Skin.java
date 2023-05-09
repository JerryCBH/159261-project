import java.awt.*;

/****************************************************************************************
 * This class represents object skins.
 ****************************************************************************************/
public class Skin {
    public Image Head, Body, Health;
    public Image[] UpSprites, LRSprites, DownSprites;
    public int CellWidth, CellHeight;
    public Skin(Image body, int width, int height){
        Body = body;
        CellWidth = width;
        CellHeight = height;
    }
    public Skin(Image[] upSprites, Image[] lrSprites, Image[] downSprites, Image health, int width, int height){
        Health = health;
        CellWidth = width;
        CellHeight = height;
        UpSprites = upSprites;
        LRSprites = lrSprites;
        DownSprites = downSprites;
    }
}
