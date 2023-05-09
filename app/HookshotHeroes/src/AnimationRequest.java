/****************************************************************************************
 * Class for storing animation requests.
 ****************************************************************************************/
public class AnimationRequest {
    public GridCell Cell;
    public double time = 0;
    // Type of animation is linked to object type and its sprites.
    public WorldObjectType Type;
    // Time to play the animation.
    public double SecondsToPlay;
    public AnimationRequest(WorldObjectType type, GridCell cell, double seconds){
        Type = type;
        Cell = cell;
        SecondsToPlay = seconds;
    }
}
