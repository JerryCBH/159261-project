import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StartMenu{
    private JFrame frame;
    private JPanel panel;
    private JButton easyModeButton;
    private JButton normalModeButton;
    private JButton missionModeButton;
    private JButton helpButton;
    private JButton quitButton;
    private Image mainImage;
    private Image gameTitle;

    public StartMenu() {
        mainImage = loadImage("MainImage.png");
        gameTitle = loadImage("GameTitle.png");
    }

    public void show() {
        // UI Menu
        frame = new JFrame("Hookshot Heroes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Create a panel with a BorderLayout
        panel = new JPanel(new BorderLayout());

        // Create a JLabel with the mainImage as the background
        JLabel backgroundImageLabel = new JLabel(new ImageIcon(mainImage));
        panel.add(backgroundImageLabel, BorderLayout.CENTER);

        // Create a sub-panel with a GridLayout for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));

        easyModeButton = new JButton("Single Player Game");
        normalModeButton = new JButton("Double Player Game");
        missionModeButton = new JButton("Quest Game");
        helpButton = new JButton("Help");
        quitButton = new JButton("Quit");

        easyModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start single player mode game
                var options = new GameOptions();
                options.SinglePlayerMode = true;

                var theGame = new HookshotHeroesGameEngine(options);
                theGame.createGame(theGame, 120);
                frame.dispose(); // Close the start menu window;
            }
        });

        normalModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start the 2 player mode game
                // Start single player mode game
                var options = new GameOptions();
                options.SinglePlayerMode = false;
                options.DoublePlayerMode = true;

                var theGame = new HookshotHeroesGameEngine(options);
                theGame.createGame(theGame, 120);
                frame.dispose(); // Close the start menu window
            }
        });

        missionModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start single player mission game
                var options = new GameOptions();
                options.SinglePlayerMode = true;
                options.DoublePlayerMode = false;
                options.MissionMode = true;

                var theGame = new HookshotHeroesGameEngine(options);
                theGame.createGame(theGame, 120);
                frame.dispose(); // Close the start menu window
            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Open the help.txt file
                    File helpFile = new File("../../app/HookshotHeroes/resources/help.txt");
                    Desktop.getDesktop().open(helpFile);
                } catch (IOException ex) {
                    // Handle any exceptions
                    ex.printStackTrace();
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quit the application
                System.exit(0);
            }
        });

        // Add the buttons to the sub-panel
        buttonPanel.add(easyModeButton);
        buttonPanel.add(normalModeButton);
        buttonPanel.add(missionModeButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(quitButton);

        // Add the sub-panel to the main panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Create a JLabel with the gameTitle image
        JLabel gameTitleLabel = new JLabel(new ImageIcon(gameTitle));
        panel.add(gameTitleLabel, BorderLayout.NORTH);

        // Set the main panel as the content pane of the frame
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

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

    public static void main(String[] args) {
        StartMenu startMenu = new StartMenu();
        startMenu.show();
    }
}