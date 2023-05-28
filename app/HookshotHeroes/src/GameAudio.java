/****************************************************************************************
 * This class loads the game audio clips.
 ****************************************************************************************/
public class GameAudio {
    public final GameEngine.AudioClip ExplosionAudio;
    public final GameEngine.AudioClip CrunchAudio;
    public final GameEngine.AudioClip WalkAudio;
    public final GameEngine.AudioClip HitAudio;
    public final GameEngine.AudioClip StepAudio;
    public final GameEngine.AudioClip WhipAudio;
    public final GameEngine.AudioClip CoinAudio;
    public final GameEngine.AudioClip MonsterDamageAudio;
    public final GameEngine.AudioClip SkeletonAudio;
    public final GameEngine.AudioClip FTAudio;
    public final GameEngine.AudioClip LidiaHealedAudio;
    public final GameEngine.AudioClip ShuraHealedAudio;
    public final GameEngine.AudioClip AvaHealedAudio;
    public GameEngine.AudioClip Theme;

    public GameAudio(HookshotHeroesGameEngine engine){
        Theme = engine.loadAudio("Atmosphere.wav");
        ExplosionAudio = engine.loadAudio("explosion.wav");
        CrunchAudio = engine.loadAudio("crunch.wav");
        WalkAudio = engine.loadAudio("walk.wav");
        HitAudio = engine.loadAudio("hit.wav");
        StepAudio = engine.loadAudio("step.wav");
        WhipAudio = engine.loadAudio("whip.wav");
        CoinAudio = engine.loadAudio("coin.wav");
        MonsterDamageAudio = engine.loadAudio("monsterdamage.wav");
        SkeletonAudio = engine.loadAudio("skeleton.wav");
        FTAudio = engine.loadAudio("ft.wav");
        LidiaHealedAudio = engine.loadAudio("t3-healed3.wav");
        ShuraHealedAudio = engine.loadAudio("t2-healed3.wav");
        AvaHealedAudio = engine.loadAudio("t3-healed3.wav");
    }

    public void ApplyTheme(HookshotHeroesGameEngine engine, String theme, float volume){
        if (!engine.GameOptions.EnableMusic){
            Theme = engine.loadAudio(theme);
        }
        else{
            engine.GameOptions.EnableMusic = false;
            engine.ToggleMusic();
            Theme = engine.loadAudio(theme);
            engine.GameOptions.EnableMusic = true;
            engine.ToggleMusic(volume);
        }
    }
}
