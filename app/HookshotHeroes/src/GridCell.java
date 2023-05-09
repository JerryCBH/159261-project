import java.util.Random;

/****************************************************************************************
 * This class encapsulates the game grid cell.
 ****************************************************************************************/

public class GridCell {
    public int Row;
    public int Column;
    public GridCell(int row, int column){
        Row = row;
        Column = column;
    }

    // Generates a random grid cell.
    public static GridCell GetRandomCell(int rLower, int rUpper, int cLower, int cUpper){
        var r = new Random();
        var row = r.nextInt(rUpper - rLower) + rLower;
        var col = r.nextInt(cUpper - cLower) + cLower;
        return new GridCell(row, col);
    }
}
