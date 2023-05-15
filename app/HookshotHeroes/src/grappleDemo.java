import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class grappleDemo extends GameEngine {
    Image player;
    Image chain;
    Image hook;

    // Walls
    Image floor;
    Image leftWall;
    Image rightWall;
    Image topWall;
    Image bottomWall;
    Image wallCornerLeft;
    Image wallCornerRight;
    Image door;
    Image doorFrame;
    Image cliff;

    LevelGen level;
    ArrayList<ArrayList<ArrayList<Integer>>> levelMap;

    int playerPosX, playerPosY, grapplePosX, grapplePosY, levelNum, degrees;
    boolean[][] collision;
    boolean grapple;
    char key;

    public static void main(String[] args) {
        createGame(new grappleDemo());
    }

    public void init() {
        // Load Images
        player = loadImage("src/resources/testCharacter.png");
        chain = loadImage("src/resources/testchain.png");
        hook = loadImage("src/resources/testChainPoint.png");

        floor = loadImage("src/resources/floor.png");
        leftWall = loadImage("src/resources/wall_rightside.png");
        rightWall = loadImage("src/resources/wall_leftside.png");
        topWall = loadImage("src/resources/wall_top.png");
        bottomWall = loadImage("src/resources/wall_bottom.png");
        wallCornerLeft = loadImage("src/resources/wall_corner_bottomleftside.png");
        wallCornerRight = loadImage("src/resources/wall_corner_bottomrightside.png");
        door = loadImage("src/resources/door.png");
        doorFrame = subImage(door, 0, 0, 10, 10);
        cliff = loadImage("src/resources/cliff.png");

        // Set Player Position
        playerPosX = 300;
        playerPosY = 300;
        grapplePosX = playerPosX;
        grapplePosY = playerPosY;
        key = 'w';
        degrees = 0;

        collision = new boolean[1200][1200];

        grapple = false;

        level = new LevelGen();
        levelMap = level.getLevelMap();
        levelNum = 0;

        // Set window size of game
        setWindowSize(1200, 1200);
    }

    public void keyPressed(KeyEvent event) {
        if (!grapple) {
            if (event.getKeyCode() == KeyEvent.VK_X) {
                grapplePosX = playerPosX;
                grapplePosY = playerPosY;
                grapple = true;
            }
            if ((event.getKeyCode() == KeyEvent.VK_UP) && !collision[playerPosX][playerPosY - 20]) {
                playerPosY -= 20;
                degrees = 0;
                key = 'w';
            }
            if ((event.getKeyCode() == KeyEvent.VK_DOWN) && !collision[playerPosX][playerPosY + 20]) {
                playerPosY += 20;
                degrees = 180;
                key = 'd';
            }
            if ((event.getKeyCode() == KeyEvent.VK_LEFT) && !collision[playerPosX - 20][playerPosY]) {
                playerPosX -= 20;
                degrees = 270;
                key = 'l';
            }
            if ((event.getKeyCode() == KeyEvent.VK_RIGHT) && !collision[playerPosX + 20][playerPosY]) {
                playerPosX += 20;
                degrees = 90;
                key = 'r';
            }
        }
    }

    public void update(double dt) {
        if (grapple) {
            if (Objects.equals(key, 'w')) {
                if (collision[playerPosX][grapplePosY - 20] && !collision[playerPosX][playerPosY - 20]) {
                    playerPosY -= 20;
                    if (playerPosY == grapplePosY) {
                        grapple = false;
                    }
                } else if (grapplePosY != playerPosY - 80 && !collision[playerPosX][playerPosY - 20]) {
                    grapplePosY -= 20;
                } else {
                    grapple = false;
                }
            }
            if (Objects.equals(key, 'd')) {
                if (collision[playerPosX][grapplePosY + 20] && !collision[playerPosX][playerPosY + 20]) {
                    playerPosY += 20;
                    if (playerPosY == grapplePosY) {
                        grapple = false;
                    }
                } else if (grapplePosY != playerPosY + 80 && !collision[playerPosX][playerPosY + 20]) {
                    grapplePosY += 20;
                } else {
                    grapple = false;
                }
            }
            if (Objects.equals(key, 'l')) {
                if (collision[grapplePosX - 20][playerPosY] && !collision[playerPosX - 20][playerPosY]) {
                    playerPosX -= 20;
                    if (playerPosX == grapplePosX) {
                        grapple = false;
                    }
                } else if (grapplePosX != playerPosX - 80 && !collision[playerPosX - 20][playerPosY]) {
                    grapplePosX -= 20;
                } else {
                    grapple = false;
                }
            }
            if (Objects.equals(key, 'r')) {
                if (collision[grapplePosX + 20][playerPosY] && !collision[playerPosX + 20][playerPosY]) {
                    playerPosX += 20;
                    if (playerPosX == grapplePosX) {
                        grapple = false;
                    }
                } else if (grapplePosX != playerPosX + 80 && !collision[playerPosX + 20][playerPosY]) {
                    grapplePosX += 20;
                } else {
                    grapple = false;
                }
            }
        }
    }

    public Image rotateImage(Image image, double rad) {
        BufferedImage img = (BufferedImage) image;
        double locX = (double) img.getWidth() / 2;
        double locY = (double) img.getHeight() / 2;

        AffineTransform tx = AffineTransform.getRotateInstance(rad, locX, locY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return op.filter(img, null);
    }

    public void paintComponent() {
        changeBackgroundColor(black);
        clearBackground(1200, 1200);
        scale(3, 3);

        int col = 0;
        for (int i = 0; i < levelMap.get(levelNum).size(); i++) {
            int row = 0;
            for (int r = 0; r < levelMap.get(levelNum).get(i).size(); r++) {
                switch (levelMap.get(levelNum).get(i).get(r)) {
                    case 1 -> drawImage(floor, row, col, 20, 20);
                    case 2 -> {
                        drawImage(leftWall, row, col, 20, 20);
                        collision[row][col] = true;
                    }
                    case 3 -> {
                        drawImage(rightWall, row, col, 20, 20);
                        collision[row][col] = true;
                    }
                    case 4 -> {
                        drawImage(topWall, row, col, 20, 20);
                        collision[row][col] = true;
                    }
                    case 5 -> {
                        drawImage(bottomWall, row, col, 20, 20);
                        collision[row][col] = true;
                    }
                    case 6 -> {
                        drawImage(wallCornerLeft, row, col, 20, 20);
                        collision[row][col] = true;
                    }
                    case 7 -> {
                        drawImage(wallCornerRight, row, col, 20, 20);
                        collision[row][col] = true;
                    }
                    case 8 -> {
                        drawImage(doorFrame, row, col, 20, 20);
                        collision[row][col] = true;
                    }
                    case 9 -> drawImage(cliff, row, col, 20, 20);
                }
                row += 20;
            }
            col += 20;
        }

        if(grapple) {
            changeColor(black);
            drawLine(playerPosX, playerPosY, grapplePosX, grapplePosY, 2);
        }

        drawImage(rotateImage(player, Math.toRadians(degrees)), playerPosX, playerPosY, 20, 20);
    }
}