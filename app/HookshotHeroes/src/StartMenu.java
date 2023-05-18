import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StartMenu extends GameEngine {
    private JFrame frame;
    private JPanel panel;
    private JButton easyModeButton;
    private JButton normalModeButton;
    private JButton helpButton;
    private JButton quitButton;
    private Image mainImage;
    private Image gameTitle;

    public StartMenu(HookshotHeroesGameEngine engine) {
        mainImage = engine.loadImage("MainImage.png");
        gameTitle = engine.loadImage("GameTitle.png");
    }

    public void show() {
        // UI Menu
        frame = new JFrame("Hookshot Heroes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 600); // Increased the height to accommodate the buttons
        frame.setLocationRelativeTo(null);

        // Create a panel with a BorderLayout
        panel = new JPanel(new BorderLayout());

        // Create a JLabel with the mainImage as the background
        JLabel backgroundImageLabel = new JLabel(new ImageIcon(mainImage));
        panel.add(backgroundImageLabel, BorderLayout.CENTER);

        // Create a sub-panel with a GridLayout for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));

        easyModeButton = new JButton("1 Player");
        normalModeButton = new JButton("2 Player");
        helpButton = new JButton("Help");
        quitButton = new JButton("Quit");

        easyModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start the normal mode game
                var theGame = new HookshotHeroesGameEngine();
                createGame(theGame, 120);
                frame.dispose(); // Close the start menu window;
                mFrame.dispose();
            }
        });

        normalModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start the 2 player mode game
                frame.dispose(); // Close the start menu window
                mFrame.dispose();
            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Open the help.txt file
                    File helpFile = new File("docs/help.txt");
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

    public static void main(String[] args) {
        HookshotHeroesGameEngine engine = new HookshotHeroesGameEngine();
        StartMenu startMenu = new StartMenu(engine);
        startMenu.show();
    }

    @Override
    public void update(double dt) {
        // Implementation of the update method

    }

    @Override
    public void paintComponent() {
        // Implementation of the paintComponent method
    }
}