import java.awt.event.KeyEvent;

/****************************************************************************************
 * Interface for game worlds
 ****************************************************************************************/
public interface IWorld {
    IWorldObject[] GetObjects();
    void SetObjects(IWorldObject[] objects);
    void RenderObjects();
    void HandleKeyEvents(KeyEvent event);
    int GetPlayerCount();
    void UpdateAnimationRequests(double dt);
    void PlayAnimation();
    void PlayAudio();
    void HandleCollision(IWorldObject collidedObject);
}