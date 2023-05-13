import java.util.ArrayList;
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

    // Generates a random grid cell not occupied.
    public static GridCell GetRandomCell(int rLower, int rUpper, int cLower, int cUpper, ArrayList<GridCell> occupiedCells) {
        var r = new Random();
        var isEmpty = true;
        int row = 0, col = 0;
        do {
            row = r.nextInt(rUpper - rLower) + rLower;
            col = r.nextInt(cUpper - cLower) + cLower;
            for (GridCell grid : occupiedCells) {
                if (grid.Row == row && grid.Column == col) {
                    isEmpty = false;
                    break;
                }
            }
        } while (!isEmpty);

        return new GridCell(row, col);
    }
}
