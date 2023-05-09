/****************************************************************************************
 * This is the mine class.
 ****************************************************************************************/
public class Mine implements IWorldObject{
    private final String _name;
    private GridCell _cell;
    private final Skin _skin;
    public Mine(String name, GridCell cell, Skin skin){
        _name = name;
        _cell = cell;
        _skin = skin;
    }
    @Override
    public GridCell Move(int keyCode) {
        return null;
    }

    @Override
    public void Render(GameEngine engine) {
        engine.drawImage(_skin.Body, _cell.Column * _skin.CellWidth + 5, _cell.Row * _skin.CellHeight + 5, 15, 15);
    }

    @Override
    public GridCell[] GetOccupiedCells() {
        return new GridCell[] { _cell };
    }

    @Override
    public IWorldObject HandleCollision(IWorldObject object) {
        return null;
    }

    @Override
    public WorldObjectType WhoAmI() {
        return WorldObjectType.Mine;
    }

    @Override
    public String GetName() {
        return _name;
    }

    public void SetGridCell(GridCell cell){
        _cell = cell;
    }
}