import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Pop{


    private int x;
    private int y;
    private int vx;
    private int vy;
    private int width;
    private int height;
    private int angle;
    private int lives;
    private int numberOfSpeedPowerUps;
    private long lastShotTime;
    private long currentTime;
    private long timeSinceLastShot;
    private boolean show;
    private boolean isDead;
    private double R = 3;

    private BufferedImage img;

    Pop(int x, int y, int vx, int vy, int angle, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.width = 35;
        this.height = 35;
        this.img = img;
        this.angle = angle;
        this.lives = 3;
        this.numberOfSpeedPowerUps = 3;
        this.show = true;
        this.isDead = false;

    }

    public void update() {

        this.moveForwards();
    }

    private void moveForwards() {
            vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
            vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
            x += vx;
            y += vy;
            checkBorder();
    }

    private void checkBorder() {

        // If it hits border, bounce back in, if it goes below certain y = death, restart
        if (x < 40) {
            collision();
        }
        if (x >= TRE.SCREEN_WIDTH - 75) {
            collision();
        }
        if (y < 40) {
            collision();
        }
        if (y >= TRE.SCREEN_HEIGHT - 75) {
            x = 623;
            y = 865;
            angle = 315;
            lives--;
            R = 3;

            if (lives < 0) {
                isDead = true;
            }
        }
    }

    // True = dead, False = alive
    public boolean getStatus() {
        return isDead;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }


    void drawImage(Graphics g) {
        if (show) {
            AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
            rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(this.img, rotation, null);
        }

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

    public int getLives() {
        return lives;
    }

    public int getNumberOfSpeedPowerUps() {
        return numberOfSpeedPowerUps;
    }

    public double getSpeed() {
        return R;
    }

    public void collision() {

        // This code wasn't playing nicely with walls, so just ended up increasing angle by 90, worked better but not perfect
//        if (vx == 0 || vy == 0) {
//            angle = angle + 180;
//        } else if (vx > 0 && vy < 0) {
//            angle = angle - 90;
//        } else if (vx > 0 && vy > 0) {
//            angle = angle + 90;
//        } else if (vx < 0 && vy < 0) {
//            angle = angle + 90;
//        } else if (vx < 0 && vy > 0) {
//            angle = angle - 90;
//        }
        angle = angle + 90;

    }

    public void KatchCollision() {

        // Regular collision + increase in speed
        if (vx > 0) {
            angle = angle - 90;
        }
        else if (vx < 0) {
            angle = angle + 90;
        }
        R = R + 0.3;
    }

    public void lifePowerUp() {
        lives = lives + 1;
    }

    public void gotSpeedPowerUp() {
        numberOfSpeedPowerUps++;
    }

    public void slowDown() {

        // Resets Pop speed to starting speed (3)
        if (numberOfSpeedPowerUps > 0) {

            currentTime = System.nanoTime();
            timeSinceLastShot = (currentTime - lastShotTime) / 1000000;

            if (timeSinceLastShot > 50) {
                R = 3;
                numberOfSpeedPowerUps--;
            }

            lastShotTime = System.nanoTime();

        }
    }

    public void unstuck() {

        // Random angle so can't be abused
        Random rand = new Random();
        angle = angle + rand.nextInt(360);
    }

}
