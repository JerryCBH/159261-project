import java.awt.*;

/****************************************************************************************
 * This class represents object skins.
 ****************************************************************************************/
public class Skin {
    public Image Head, Body, Health;
    public int CellWidth, CellHeight;
    public Skin(Image head, Image body, Image health, int width, int height){
        Head = head;
        Body = body;
        Health = health;
        CellWidth = width;
        CellHeight = height;
    }
}
