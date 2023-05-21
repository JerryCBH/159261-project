public class Chest {
    private final GridCell _cell;
    public Boolean IsOpened = false;
    public Chest(GridCell cell){
        _cell = cell;
    }
    public GridCell GetCell(){
        return _cell;
    }
}
