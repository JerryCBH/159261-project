import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.IOException;

/***********************************************
 * =============================================
 * HookshotHeroesEngine
 * The main entry point of the HookshotHeroes game.
 */
public class HookshotHeroesGameEngine extends GameEngine {

    private final int _width = 1000, _height = 700, _fps = 60;
    private final GameImage _gameImage;
    private final GameAudio _gameAudio;
    private IWorld _world;
    private Boolean _pause = false;
    private final JMenuBar _menuBar;
    public GameOptions GameOptions;

    public static void main(String[] args){
        // Create the game engine.
        var theGame = new HookshotHeroesGameEngine();
        createGame(theGame, theGame._fps);
    }

    public HookshotHeroesGameEngine(){
        // Create Game Image to handle all the sprite images.
        _gameImage = new GameImage(this);
        // Create Game Audio to handle all the sound clips.
        _gameAudio = new GameAudio(this);
        // Load default game options.
        GameOptions = new GameOptions();
        GameOptions.Width = _width;
        GameOptions.Height = _height;

        // Build the default menu bar.
        var _menuBarBuilder = new DefaultMenuBarBuilder();
        _menuBar = _menuBarBuilder.BuildMenuBar(this);
    }

    // Initialize the game world based on game options.
    // Game options can be loaded from disk.
    public void InitializeWorld(GameOptions options){
        if(options.SinglePlayerMode){
            _world = new SinglePlayerWorldBuilder().Build(this, _gameImage, _gameAudio, options);
        }
        else {
            _world = new DoublePlayerWorldBuilder().Build(this, _gameImage, _gameAudio, options);
        }
    }

    @Override
    public void init(){
        setWindowSize(_width, _height);
        mFrame.setTitle("Hookshot Heroes");
        mFrame.setResizable(false);
        mFrame.setJMenuBar(_menuBar);
        // Start playing theme song.
        ToggleMusic();
        // Create the game world.
        InitializeWorld(GameOptions);
    }

    @Override
    public void update(double dt) {
        if (!_pause){
            // Handle animations and sprites.
            _world.UpdateAnimationRequests(dt);
        }
    }

    @Override
    public void paintComponent() {
        changeBackgroundColor(Color.darkGray);
        clearBackground(_width, _height);

        // Render objects, play animations or audios.
        _world.RenderObjects();
        _world.PlayAnimation();
        _world.PlayAudio();
    }

    @Override
    public void keyPressed(KeyEvent event){
        if (event.getKeyCode() == KeyEvent.VK_P){
            TogglePause();
        }
        if (!_pause) {
            // Handle key press events.
            _world.HandleKeyEvents(event);
        }
    }

    // Pause the game engine during message popups.
    public void PauseEngine(){
        _pause = true;
    }

    // Resumes the game engine.
    public void ResumeEngine(){
        _pause = false;
    }

    // Get the current frame for animation.
    public int GetFrameIndex(double time, double scale, int totalImages) {
        return (int) Math.floor(((time % scale) / scale) * totalImages);
    }

    // Turn the background music on/off.
    public void ToggleMusic(){
        if(GameOptions.EnableMusic){
            startAudioLoop(_gameAudio.Theme, GameOptions.MasterVolume);
        }
        else{
            stopAudioLoop(_gameAudio.Theme);
        }
    }

    // Toggle pause.
    public void TogglePause(){
        if(_pause){
            ResumeEngine();
        }
        else{
            PauseEngine();
        }
    }

    // Load image with stream - works for IDE and Jar.
    @Override
    public Image loadImage(String filename) {
        try {
            var stream = getClass().getResourceAsStream(filename);

            if (stream != null) {
                return ImageIO.read(stream);
            }
        } catch (IOException e) {
            // Show Error Message
            System.out.println("Error: could not load image " + filename);
            System.exit(1);
        }

        // Return null
        return null;
    }

    // Load audio from stream.
    @Override
    public AudioClip loadAudio(String filename) {
        try {
            // Open File
            var stream = getClass().getResourceAsStream(filename);

            if (stream != null) {
                // Open Audio Input Stream
                AudioInputStream audio = AudioSystem.getAudioInputStream(new BufferedInputStream(stream));

                // Return Audio Clip
                return new AudioClip(audio);
            }
        } catch (Exception e) {
            // Catch Exception
            System.out.println("Error: cannot open Audio File " + filename + "\n" + e.getMessage());
        }

        // Return Null
        return null;
    }
}