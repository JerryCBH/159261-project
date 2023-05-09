/****************************************************************************************
 * This class loads the game audio clips.
 ****************************************************************************************/
public class GameAudio {
    public final GameEngine.AudioClip ExplosionAudio;
    public final GameEngine.AudioClip CrunchAudio;
    public final GameEngine.AudioClip Theme;
    public final GameEngine.AudioClip WalkAudio;
    public final GameEngine.AudioClip HitAudio;

    public GameAudio(TheGameEngine engine){
        Theme = engine.loadAudio("awesomeness.wav");
        ExplosionAudio = engine.loadAudio("explosion.wav");
        CrunchAudio = engine.loadAudio("crunch.wav");
        WalkAudio = engine.loadAudio("walk.wav");
        HitAudio = engine.loadAudio("hit.wav");
    }
}