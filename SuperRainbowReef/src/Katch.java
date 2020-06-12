import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Katch{

    private int x;
    private int y;
    private int width;
    private int height;
    private int lives;
    private boolean isDead;
    private Pop pop;
    private BufferedImage katchimg;
    private boolean LeftPressed;
    private boolean RightPressed;
    private boolean SpacePressed;
    private boolean EnterPressed;

    Katch(int x, int y, BufferedImage katchimg, Pop pop) {
        this.x = x;
        this.y = y;
        this.width = 80;
        this.height = 30;
        this.katchimg = katchimg;
        this.lives = 3;
        this.isDead = false;
        this.pop = pop;

    }
    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleSpacePressed() {
        this.SpacePressed = true;
    }

    void toggleEnterPressed() {
        this.EnterPressed = true;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleSpacePressed() {
        this.SpacePressed = false;
    }

    void unToggleEnterPressed() {
        this.EnterPressed = false;
    }

    public void update() {

        if (this.LeftPressed) {
            this.moveLeft();
        }
        if (this.RightPressed) {
            this.moveRight();
        }
        if (this.SpacePressed) {
            this.slowDownPop();
        }
        if (this.EnterPressed) {
            this.unstuck();
        }
    }

    private void moveLeft() {
        x = x - 5;
        checkBorder();
    }

    private void moveRight() {
        x = x + 5;
        checkBorder();
    }

    private void slowDownPop() {
        pop.slowDown();
    }

    private void unstuck() {
        pop.unstuck();
    }

    private void checkBorder() {
        if (x < 40) {
            x = 40;
        }
        if (x >= TRE.SCREEN_WIDTH - 120) {
            x = TRE.SCREEN_WIDTH - 120;
        }

    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }

    void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.katchimg, rotation, null);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
