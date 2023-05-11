import java.awt.*;
//From Here

public class Levels extends GameEngine {
    int height = 600;
    int width = 600;
    int environmentSpriteSize = 40;
    Image DoorGreyClosed = loadImage("app/HookshotHeroes/resources/environment/DoorGreyClosed.png");
    Image DoorGreyOpen = loadImage("app/HookshotHeroes/resources/environment/DoorGreyOpen.png");
    Image floor = loadImage("app/HookshotHeroes/resources/environment/floor.png");
    Image lava = loadImage("app/HookshotHeroes/resources/environment/lava.png");
    Image wallGreyLeftSide = loadImage("app/HookshotHeroes/resources/environment/wallGreyLeftSide.png");
    Image wallGreyRightSide = loadImage("app/HookshotHeroes/resources/environment/wallGreyRightSide.png");
    Image wallGreyFront = loadImage("app/HookshotHeroes/resources/environment/wallGreyFront.png");
    Image DoorGreyOpenSide = loadImage("app/HookshotHeroes/resources/environment/DoorGreyOpenSide.png");
    Image DoorGreyClosedSide = loadImage("app/HookshotHeroes/resources/environment/DoorGreyClosedSide.png");

    public void drawWallFrontWithCollision(int x, int y) {
        drawImage(wallGreyFront, x, y);
        changeColor(Color.red); //Just to see visually
        drawRectangle(x, y, 40, 40);

        //Add collision logic here
    }

    public void wallSideCollision(int x, int y) {
        changeColor(Color.red); // Just to see visually
        drawRectangle(x, y, 40, 40);

        //Add collision logic here
    }

    public void doorCollision(int x, int y) {
        changeColor(Color.green); // Just to see visually
        drawRectangle(x, y, 40, 40);

        //Add collision logic here
    }

    public void drawLavaWithCollision(int x, int y) {
        drawImage(lava, x, y);
        changeColor(Color.pink); // Just to see visually
        drawRectangle(x, y, 40, 40);

        //Add collision logic here
    }

    public void basicLevelEnvironment() {
        //Floor
        for (int y = 0; y < width; y += 100) {
            for (int x = 0; x < height; x += 100) {
                drawImage(floor, x, y);
            }
        }

        for (int x = 0; x < 600; x += environmentSpriteSize) {
            // Draw wall sprite on the top side
            drawWallFrontWithCollision(x, 0);

            // Draw wall sprite on the bottom side
            drawWallFrontWithCollision(x, 560);
        }
        for (int y = 0; y < 560; y += environmentSpriteSize) {
            // Draw wall sprite on the left side
            drawImage(wallGreyLeftSide, 0, y);
            wallSideCollision(0, y);

            // Draw wall sprite on the right side
            drawImage(wallGreyRightSide, 560, y);
            wallSideCollision(560, y);
        }
    }

    public static void main(String args[]) {
        Levels game = new Levels();
        createGame(game, 30);

    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void paintComponent() {
        setWindowSize(width,height);
        clearBackground(width(), height());
        levelOne();
        //levelTwo();
        //levelThree();
        //levelFour();
        //levelFive();
        //levelSix();
        //levelSeven();
        //levelEight();
        //levelNine();
        //levelTen();
    }

    public void levelOne() {

        basicLevelEnvironment();

        // Draw Lava
        for (int x = 40; x < 560; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 240);
            drawLavaWithCollision(x, 280);
            drawLavaWithCollision(x, 320);
        }

        //Internal walls
        for (int x = 200; x < 400; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 160);
        }

        //Draw doors
        drawImage(DoorGreyOpen, 280,0);
        doorCollision(280,0);
        drawImage(DoorGreyClosed, 280,560);
        doorCollision(280,560);

    }

    public void levelTwo() {
        basicLevelEnvironment();

        // Draw internal walls
        for (int x = 40; x < 440; x += environmentSpriteSize) {
            drawImage(wallGreyFront, x, 160);
            drawWallFrontWithCollision(x, 160);
        }
        for (int x = 80; x < 160; x += environmentSpriteSize) {
            drawImage(wallGreyFront, x, 240);
            drawWallFrontWithCollision(x, 240);
        }
        for (int x = 360; x < 440; x += environmentSpriteSize) {
            drawImage(wallGreyFront, x, 200);
            drawWallFrontWithCollision(x, 200);
            drawImage(wallGreyFront, x, 240);
            drawWallFrontWithCollision(x, 240);
        }

        // Draw Lava
        for (int x = 40; x < 560; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 320);
            drawLavaWithCollision(x, 360);
            drawLavaWithCollision(x, 400);
        }
        for (int x = 360; x < 560; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 440);
            drawLavaWithCollision(x, 480);
            drawLavaWithCollision(x, 520);
        }
        for (int x = 200; x < 320; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 200);
            drawLavaWithCollision(x, 240);
            drawLavaWithCollision(x, 280);
        }

        //Draw doors
        drawImage(DoorGreyOpen, 280,0);
        doorCollision(280,0);
        drawImage(DoorGreyClosed, 280,560);
        doorCollision(280,560);
    }

    public void levelThree() {
        basicLevelEnvironment();

        // Draw internal walls
        for (int x = 240; x < 480; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 120);
        }
        for (int y = 40; y < 120; y += environmentSpriteSize) {;
            drawWallFrontWithCollision(240, y);
        }
        for (int y = 400; y < 560; y += environmentSpriteSize) {
            drawWallFrontWithCollision(240, y);
        }
        for (int x = 240; x < 480; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 400);
        }
        for (int x = 320; x < 600; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 240);
        }
        for (int y = 320; y <= 360; y += 40) {
            for (int x = 20; x <= 40; x += 20) {
                drawWallFrontWithCollision(x, y);
            }
        }
        drawWallFrontWithCollision(320, 280);
        drawWallFrontWithCollision(80, 520);

        // Draw Lava
        for (int y = 40; y < 320; y += environmentSpriteSize) {
            for (int x = 40; x < 220; x += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int y = 320; y < 560; y += environmentSpriteSize) {
            for (int x = 120; x < 220; x += 20) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int x = 40; x <= 40; x += environmentSpriteSize) {
            for (int y = 400; y <= 520; y += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int x = 400; x <= 480; x += environmentSpriteSize) {
            for (int y = 280; y <= 360; y += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int x = 520; x <= 520; x += environmentSpriteSize) {
            for (int y = 320; y <= 400; y += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int y = 400; y <= 440; y += environmentSpriteSize) {
            drawLavaWithCollision(80, y);
        }
        for (int y = 80; y <= 120; y += environmentSpriteSize) {
            for (int x = 480; x <= 540; x += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        drawLavaWithCollision(480, 400);

        //Draw doors
        drawImage(DoorGreyOpen, 280,0);
        doorCollision(280,0);
        drawImage(DoorGreyClosed, 280,560);
        doorCollision(280,560);
    }

    public void levelFour() {
        basicLevelEnvironment();

        //Draw doors
        drawImage(DoorGreyOpen, 280,0);
        doorCollision(280,0);
        drawImage(DoorGreyOpenSide, 560,280);
        doorCollision(560,280);
        drawImage(DoorGreyClosed, 280,560);
        doorCollision(280,560);
    }

    public void levelFive() {
        basicLevelEnvironment();

        //Draw lava
        for (int y = 400; y < 520; y += environmentSpriteSize) {
            for (int x = 40; x < 300; x += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int y = 400; y < 520; y += environmentSpriteSize) {
            for (int x = 400; x < 520; x += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int y = 200; y < 320; y += environmentSpriteSize) {
            for (int x = 320; x < 400; x += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int y = 40; y < 120; y += environmentSpriteSize) {
            for (int x = 400; x < 520; x += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int x = 80; x < 200; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 200);
        }
        for (int y = 240; y < 360; y += environmentSpriteSize) {
            drawLavaWithCollision(280, y);
        }
        for (int x = 120; x < 280; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 360);
        }
        for (int x = 120; x < 240; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 520);
        }
        drawLavaWithCollision(40, 40);
        drawLavaWithCollision(80, 40);
        drawLavaWithCollision(120, 80);

        drawLavaWithCollision(240, 40);
        drawLavaWithCollision(240, 80);
        drawLavaWithCollision(280, 80);

        drawLavaWithCollision(400, 200);
        drawLavaWithCollision(400, 240);

        //Draw doors
        drawImage(DoorGreyClosedSide, 0,80);
        doorCollision(0,80);
        drawImage(DoorGreyOpen, 280,0);
        doorCollision(280,0);

        //Draw inner walls
        for (int x = 320; x < 520; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 120);
        }
        for (int x = 320; x < 440; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 160);
        }
        for (int x = 40; x < 280; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 160);
        }
        for (int x = 40; x < 280; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 240);
        }
        for (int x = 80; x < 280; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 320);
        }
        for (int y = 320; y < 560; y += environmentSpriteSize) {
            drawWallFrontWithCollision(320, y);
        }
        for (int y = 200; y < 400; y += environmentSpriteSize) {
            for (int x = 440; x < 520; x += environmentSpriteSize) {
                drawWallFrontWithCollision(x, y);
            }
        }
        for (int y = 80; y < 160; y += environmentSpriteSize) {
            drawWallFrontWithCollision(160, y);
        }
        drawWallFrontWithCollision(320, 80);
        drawWallFrontWithCollision(40, 520);

        drawWallFrontWithCollision(520, 440);

        drawWallFrontWithCollision(360, 520);

        drawWallFrontWithCollision(360, 320);
        drawWallFrontWithCollision(360, 360);
    }

    public void levelSix() {
        basicLevelEnvironment();

        //Draw lava
        for (int x = 80; x < 200; x += 40) {
            drawLavaWithCollision(x, 40);
        }
        for (int x = 200; x < 320; x += environmentSpriteSize) {
            for (int y = 80; y < 200; y += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int x = 400; x < 520; x += environmentSpriteSize) {
            for (int y = 200; y < 360; y += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int x = 200; x < 320; x += environmentSpriteSize) {
            for (int y = 400; y < 520; y += environmentSpriteSize) {
                drawLavaWithCollision(x, y);
            }
        }
        for (int y = 240; y < 440; y += environmentSpriteSize) {
            drawLavaWithCollision(520, y);
        }
        for (int y = 200; y < 280; y += environmentSpriteSize) {
            drawLavaWithCollision(40, y);
        }
        for (int y = 320; y < 440; y += environmentSpriteSize) {
            drawLavaWithCollision(80, y);
        }
        for (int y = 400; y < 520; y += environmentSpriteSize) {
            drawLavaWithCollision(120, y);
        }
        for (int y = 400; y < 480; y += environmentSpriteSize) {
            drawLavaWithCollision(40, y);
        }
        for (int x = 160; x < 280; x += environmentSpriteSize) {
            drawLavaWithCollision(x, 520);
        }
        drawLavaWithCollision(360, 320);

        //Draw internal walls
        for (int x = 80; x < 200; x += environmentSpriteSize) {
            for (int y = 200; y < 280; y += environmentSpriteSize) {
                drawWallFrontWithCollision(x, y);
            }
        }
        for (int x = 400; x < 520; x += environmentSpriteSize) {
            for (int y = 360; y < 520; y += environmentSpriteSize) {
                drawWallFrontWithCollision(x, y);
            }
        }
        for (int x = 40; x < 200; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 120);
        }
        for (int x = 200; x < 360; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 320);
        }
        for (int x = 280; x < 400; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 240);
        }
        for (int x = 40; x < 120; x += environmentSpriteSize) {
            drawWallFrontWithCollision(x, 520);
        }
        for (int y = 280; y < 520; y += environmentSpriteSize) {
            drawWallFrontWithCollision(160, y);
        }
        for (int y = 400; y < 560; y += environmentSpriteSize) {
            drawWallFrontWithCollision(320, y);
        }
        for (int y = 80; y < 200; y += environmentSpriteSize) {
            drawWallFrontWithCollision(360, y);
        }
        for (int y = 40; y < 200; y += environmentSpriteSize) {
            drawWallFrontWithCollision(480, y);
        }
        drawWallFrontWithCollision(280, 40);
        drawWallFrontWithCollision(320, 120);
        drawWallFrontWithCollision(280, 200);
        drawWallFrontWithCollision(40, 320);
        drawWallFrontWithCollision(120, 280);
        drawWallFrontWithCollision(120, 320);
    }
}

