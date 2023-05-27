import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/****************************************************************************************
 * Builder for the default menu bar.
 ****************************************************************************************/
public class DefaultMenuBarBuilder implements IMenuBarBuilder{

    // Create the menu bar and its items.
    // Add event handlers for each menu bar items.
    @Override
    public JMenuBar BuildMenuBar(HookshotHeroesGameEngine engine) {
        var options = new GameOptions();
        options.EnableMusic = engine.GameOptions.EnableMusic;
        options.DoublePlayerMode = engine.GameOptions.DoublePlayerMode;
        options.SinglePlayerMode = engine.GameOptions.SinglePlayerMode;
        options.EnableBouncingBalls = engine.GameOptions.EnableBouncingBalls;

        var menuBar = new JMenuBar();
        var menuFile = new JMenu("File");
        var menuOptions = new JMenu("Options");

        var singleGameButton = new JRadioButton("Single Player");
        singleGameButton.setActionCommand("Single");
        var doubleGameButton = new JRadioButton("Double Player");
        doubleGameButton.setActionCommand("Double");
        SetButtons(engine.GameOptions, singleGameButton, doubleGameButton);
        var group = new ButtonGroup();
        group.add(singleGameButton);
        group.add(doubleGameButton);
        singleGameButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigHandler(e, options);
            }
        });
        doubleGameButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigHandler(e, options);
            }
        });

        var enableMusic = new JCheckBox("Enable Music");
        enableMusic.setActionCommand("Music");
        SetCheckBox(engine.GameOptions.EnableMusic, enableMusic);
        enableMusic.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                options.EnableMusic = enableMusic.isSelected();
            }
        });

        var enableBalls = new JCheckBox("Enable Bouncing Balls");
        enableBalls.setActionCommand("Balls");
        SetCheckBox(engine.GameOptions.EnableBouncingBalls, enableBalls);
        enableBalls.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                options.EnableBouncingBalls = enableBalls.isSelected();
            }
        });

        var  radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(singleGameButton);
        radioPanel.add(doubleGameButton);
        radioPanel.add(enableMusic);
        //radioPanel.add(enableBalls);

        var configPanel = new JPanel();
        configPanel.add(radioPanel, BorderLayout.LINE_START);

        var menuOptionsConfig = new JMenuItem(new AbstractAction("Game Configuration") {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.PauseEngine();
                var result = JOptionPane.showConfirmDialog(null, configPanel,"Game Configuration",JOptionPane.OK_CANCEL_OPTION);
                if (result == 0){
                    // Check for UI changes.
                    if(options.SinglePlayerMode != engine.GameOptions.SinglePlayerMode ||
                            options.DoublePlayerMode != engine.GameOptions.DoublePlayerMode ||
                            options.EnableBouncingBalls != engine.GameOptions.EnableBouncingBalls) {
                        engine.GameOptions.SinglePlayerMode = options.SinglePlayerMode;
                        engine.GameOptions.DoublePlayerMode = options.DoublePlayerMode;
                        engine.GameOptions.EnableBouncingBalls = options.EnableBouncingBalls;
                        engine.InitializeWorld(engine.GameOptions);
                    }
                    if(options.EnableMusic != engine.GameOptions.EnableMusic) {
                        engine.GameOptions.EnableMusic = options.EnableMusic;
                        engine.ToggleMusic();
                    }
                }
                else{
                    options.SinglePlayerMode = engine.GameOptions.SinglePlayerMode;
                    options.DoublePlayerMode = engine.GameOptions.DoublePlayerMode;
                    options.EnableMusic = engine.GameOptions.EnableMusic;
                    options.EnableBouncingBalls = engine.GameOptions.EnableBouncingBalls;
                    SetButtons(engine.GameOptions, singleGameButton, doubleGameButton);
                    SetCheckBox(engine.GameOptions.EnableMusic, enableMusic);
                    SetCheckBox(engine.GameOptions.EnableBouncingBalls, enableBalls);
                }
                engine.ResumeEngine();
            }
        });
        var menuHelp = new JMenu("Help");
        var menuFileNewGame = new JMenuItem(new AbstractAction("New Game") {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.InitializeWorld(engine.GameOptions);
            }
        });
        var menuPauseGame = new JMenuItem(new AbstractAction("Pause / Resume (P)") {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.TogglePause();
            }
        });
        var menuFileExit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        var menuHelpHow = new JMenuItem(new AbstractAction("How to play") {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.PauseEngine();
                JOptionPane.showMessageDialog(engine.mFrame, """
                        Welcome to Hookshot Heroes!
                                                
                        In a realm shrouded in mystery, Lidia, Shura, brave and determined heroines,
                        embarks on a perilous journey. Drawn by the allure of hidden treasures
                        and ancient secrets, they fearlessly enter the dungeons. Empowered by the
                        legendary "hookshot," a grappling hook that fused to her arm, Lidia defies
                        danger and navigates treacherous terrain. With each triumph over enemies
                        and the acquisition of precious loot, she inches closer to the ultimate prize.
                        Driven by unwavering bravery, Lidia's quest for glory unfolds as she unravels
                        the depths of the dungeons, leaving an indelible mark upon the annals of
                        Eldoria's history.
                                                
                        Instructions:
                                                
                        Player One: Use 'W', 'S', 'D' and 'A' to move Linda around the dungeon.
                        Use 'X' to fire the 'hookshot'
                                                
                        Player Two: Use the arrow keys to move Shura around the dungeon
                        Use '.' to fire the 'hookshot'
                            The hookshot can be used to:
                                - jump over the lava
                                - to collect items
                                - destroy bombs
                                - attack enemies
                            Be careful though, as the hookshot only has a limited range.
                                                
                        Collect coins to increase your score
                        Open treasurers to boost your score
                        Collect plant baskets for extra lives
                        Collision with bombs, results in a loss of life
                        Landing in the lava, results in a loss of life
                                                
                        Single Player Mode:
                                                
                        Navigate the dungeons solo, collecting as many coins and treasure, as quickly as
                        you can to complete the game with the best score.
                                                
                        Double Player Mode:
                                                
                        You and a friend will compete against each other to see who can get the highest
                        score. Pick between collecting coins or opening chests or getting to the exit
                        the fastest. The first player to the exit will end the level for both players.
                        Level knowledge, speed and tactics, will help you secure the win.
                                                
                        Quest Mode:
                                                
                        Help Ava escape the Dungeon. This can be played either in Single or Double player.
                        Ava will follow you and your friend. Watch over and guide her through the dungeons.
                                                
                        Game Options:
                                                
                        - Change from Single Player Mode to Double Player Mode
                        - Enable / Disable music
                                                
                        """);
                engine.ResumeEngine();
            }
        });
        var menuHelpAbout = new JMenuItem(new AbstractAction("About") {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.PauseEngine();
                JOptionPane.showMessageDialog(engine.mFrame, """
                        Icons, terrain sprites and sound effects: https://opengameart.org/
                        
                        Lidia, Shura and characters sprite models:
                        https://opengameart.org/content/lpc-heroine
                        https://opengameart.org/content/lpc-heroine-2
                        https://opengameart.org/content/whispers-of-avalon-archer-sprite
                        https://opengameart.org/content/edited-and-extended-24x32-character-pack
                        https://opengameart.org/content/sara-2-0
                        
                        Theme Music:
                        https://soundcloud.com/video-background-music/scarey-atmospheres-chapter-3
                        https://opengameart.org/content/lava-area-theme
                        https://opengameart.org/content/pretty-maiden-medievalfantasy-game-cheerful-opening
                        
                        MIT License
                        
                        Copyright (c) 2023 Jerry Hsiung
                        
                        Permission is hereby granted, free of charge, to any person obtaining a copy
                        of this software and associated documentation files (the "Software"), to deal
                        in the Software without restriction, including without limitation the rights
                        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
                        copies of the Software, and to permit persons to whom the Software is
                        furnished to do so, subject to the following conditions:

                        The above copyright notice and this permission notice shall be included in all
                        copies or substantial portions of the Software.

                        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
                        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
                        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
                        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
                        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
                        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
                        SOFTWARE.
                        """);
                engine.ResumeEngine();
            }
        });

        menuBar.add(menuFile);
        menuBar.add(menuOptions);
        menuBar.add(menuHelp);
        menuFile.add(menuPauseGame);
        menuFile.add(menuFileNewGame);
        menuFile.add(menuFileExit);
        menuOptions.add(menuOptionsConfig);
        menuHelp.add(menuHelpHow);
        menuHelp.add(menuHelpAbout);

        return menuBar;
    }

    // Copy data from UI to game options.
    private void ConfigHandler(ActionEvent e, GameOptions options){
        if(e.getActionCommand().equals("Single")){
            options.SinglePlayerMode = true;
            options.DoublePlayerMode = false;
        }
        else if(e.getActionCommand().equals("Double")){
            options.SinglePlayerMode = false;
            options.DoublePlayerMode = true;
        }
    }

    // Copy data from game options to UI for radio buttons.
    private void SetButtons(GameOptions options, JRadioButton singleGameButton, JRadioButton doubleGameButton){
        if(options.SinglePlayerMode) {
            singleGameButton.setSelected(true);
        }
        else {
            doubleGameButton.setSelected(true);
        }
    }

    // Copy data from game options to check box.
    private void SetCheckBox(boolean state, JCheckBox checkBox){
        checkBox.setSelected(state);
    }
}
