/****************************************************************************************
 * This class loads the game audio clips.
 ****************************************************************************************/
public class GameAudio {
    public final GameEngine.AudioClip ExplosionAudio;
    public final GameEngine.AudioClip CrunchAudio;
    public final GameEngine.AudioClip Theme;
    public final GameEngine.AudioClip WalkAudio;
    public final GameEngine.AudioClip HitAudio;
    public final GameEngine.AudioClip StepAudio;
    public final GameEngine.AudioClip WhipAudio;

    public GameAudio(HookshotHeroesGameEngine engine){
        Theme = engine.loadAudio("awesomeness.wav");
        ExplosionAudio = engine.loadAudio("explosion.wav");
        CrunchAudio = engine.loadAudio("crunch.wav");
        WalkAudio = engine.loadAudio("walk.wav");
        HitAudio = engine.loadAudio("hit.wav");
        StepAudio = engine.loadAudio("step.wav");
        WhipAudio = engine.loadAudio("whip.wav");
    }
}
