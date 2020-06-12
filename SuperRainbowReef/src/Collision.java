import java.awt.*;
import java.util.ArrayList;

public class Collision {

    private int score;
    private int[][] map;
    private int bigLegsLeft;
    private boolean gameOver;

    private ArrayList<Rectangle> blocks0 = new ArrayList<>();
    private ArrayList<Rectangle> blocks1 = new ArrayList<>();
    private ArrayList<Rectangle> blocks2 = new ArrayList<>();
    private ArrayList<Rectangle> blocks3 = new ArrayList<>();
    private ArrayList<Rectangle> blocks4 = new ArrayList<>();
    private ArrayList<Rectangle> blocks5 = new ArrayList<>();
    private ArrayList<Rectangle> blocks6 = new ArrayList<>();
    private ArrayList<Rectangle> walls = new ArrayList<>();
    private ArrayList<Rectangle> biglegs = new ArrayList<>();
    private ArrayList<Rectangle> lifePowerUp = new ArrayList<>();
    private ArrayList<Rectangle> speedPowerUp = new ArrayList<>();

    public Collision (TileLayer layer) {

        score = 0;
        map = layer.getMap();
        bigLegsLeft = 0;

        // Search map for blocks/walls/big legs/powerups
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[0].length - 1; j++) {
                if (map[i][j] == 800) {
                    blocks0.add(new Rectangle(j * 40, i * 40, 80,40));
                }
                else if (map[i][j] == 801) {
                    blocks1.add(new Rectangle(j * 40, i * 40, 80,40));
                }
                else if (map[i][j] == 802) {
                    blocks2.add(new Rectangle(j * 40, i * 40, 80,40));
                }
                else if (map[i][j] == 803) {
                    blocks3.add(new Rectangle(j * 40, i * 40, 80,40));
                }
                else if (map[i][j] == 804) {
                    blocks4.add(new Rectangle(j * 40, i * 40, 80,40));
                }
                else if (map[i][j] == 805) {
                    blocks5.add(new Rectangle(j * 40, i * 40, 80,40));
                }
                else if (map[i][j] == 806) {
                    blocks6.add(new Rectangle(j * 40, i * 40, 80,40));
                }
                else if (map[i][j] == 807) {
                    walls.add(new Rectangle(j * 40, i * 40, 40,40));
                }
                else if (map[i][j] == 808) {
                    biglegs.add(new Rectangle(j * 40, i * 40, 80,80));
                    bigLegsLeft++;
                }
                else if (map[i][j] == 809) {
                    lifePowerUp.add(new Rectangle(j * 40, i * 40, 80,40));
                }
                else if (map[i][j] == 810) {
                    speedPowerUp.add(new Rectangle(j * 40, i * 40, 80,40));
                }
            }
        }
    }

    public void KatchVsPop(Katch katch, Pop pop) {

        Rectangle katch_box = new Rectangle(katch.getX(), katch.getY(), katch.getWidth(), katch.getHeight());
        Rectangle pop_box = new Rectangle(pop.getX(), pop.getY(), pop.getWidth(), pop.getHeight());

        if (katch_box.intersects(pop_box)) {
            pop.KatchCollision();
        }

    }

    public void BlocksVsPop(Pop pop) {

        Rectangle pop_box = new Rectangle(pop.getX(), pop.getY(), pop.getWidth(), pop.getHeight());

        for (int i = 0; i < blocks0.size(); i ++) {
            Rectangle block_box = new Rectangle((int)blocks0.get(i).getX(), (int)blocks0.get(i).getY(), 80, 40);

            if (pop_box.intersects(block_box)) {
                pop.collision();
                score = score + 10;
                blocks0.remove(i);
            }
        }

        for (int i = 0; i < blocks1.size(); i ++) {
            Rectangle block_box = new Rectangle((int)blocks1.get(i).getX(), (int)blocks1.get(i).getY(), 80, 40);

            if (pop_box.intersects(block_box)) {
                pop.collision();
                score = score + 20;
                blocks1.remove(i);
            }
        }

        for (int i = 0; i < blocks2.size(); i ++) {
            Rectangle block_box = new Rectangle((int)blocks2.get(i).getX(), (int)blocks2.get(i).getY(), 80, 40);

            if (pop_box.intersects(block_box)) {
                pop.collision();
                score = score + 30;
                blocks2.remove(i);
            }
        }

        for (int i = 0; i < blocks3.size(); i ++) {
            Rectangle block_box = new Rectangle((int)blocks3.get(i).getX(), (int)blocks3.get(i).getY(), 80, 40);

            if (pop_box.intersects(block_box)) {
                pop.collision();
                score = score + 40;
                blocks3.remove(i);
            }
        }

        for (int i = 0; i < blocks4.size(); i ++) {
            Rectangle block_box = new Rectangle((int)blocks4.get(i).getX(), (int)blocks4.get(i).getY(), 80, 40);

            if (pop_box.intersects(block_box)) {
                pop.collision();
                score = score + 50;
                blocks4.remove(i);
            }
        }

        for (int i = 0; i < blocks5.size(); i ++) {
            Rectangle block_box = new Rectangle((int)blocks5.get(i).getX(), (int)blocks5.get(i).getY(), 80, 40);

            if (pop_box.intersects(block_box)) {
                pop.collision();
                score = score + 60;
                blocks5.remove(i);
            }
        }

        for (int i = 0; i < blocks6.size(); i ++) {
            Rectangle block_box = new Rectangle((int)blocks6.get(i).getX(), (int)blocks6.get(i).getY(), 80, 40);

            if (pop_box.intersects(block_box)) {
                pop.collision();
                score = score + 70;
                blocks6.remove(i);
            }
        }

    }

    public void WallsVsPop(Pop pop) {

        Rectangle pop_box = new Rectangle(pop.getX(), pop.getY(), pop.getWidth(), pop.getHeight());

        for (int i = 0; i < walls.size(); i ++) {
            Rectangle wall_box = new Rectangle((int)walls.get(i).getX(), (int)walls.get(i).getY(), 40, 40);

            if (pop_box.intersects(wall_box)) {
                pop.collision();
            }
        }
    }

    public void BigLegVsPop(Pop pop) {

        Rectangle pop_box = new Rectangle(pop.getX(), pop.getY(), pop.getWidth(), pop.getHeight());

        for (int i = 0; i < biglegs.size(); i ++) {
            Rectangle wall_box = new Rectangle((int)biglegs.get(i).getX(), (int)biglegs.get(i).getY(), 80, 40);

            if (pop_box.intersects(wall_box)) {
                pop.collision();
                score = score + 500;
                biglegs.remove(i);
                bigLegsLeft--;
            }
        }

        if (bigLegsLeft <= 0) {
            gameOver = true;
        }
    }

    public void LifePowerUpVsPop(Pop pop) {

        Rectangle pop_box = new Rectangle(pop.getX(), pop.getY(), pop.getWidth(), pop.getHeight());

        for (int i = 0; i < lifePowerUp.size(); i ++) {
            Rectangle wall_box = new Rectangle((int)lifePowerUp.get(i).getX(), (int)lifePowerUp.get(i).getY(), 80, 40);

            if (pop_box.intersects(wall_box)) {
                pop.collision();
                pop.lifePowerUp();
                lifePowerUp.remove(i);
            }
        }
    }

    public void SpeedPowerUpVsPop(Pop pop) {

        Rectangle pop_box = new Rectangle(pop.getX(), pop.getY(), pop.getWidth(), pop.getHeight());

        for (int i = 0; i < speedPowerUp.size(); i ++) {
            Rectangle wall_box = new Rectangle((int)speedPowerUp.get(i).getX(), (int)speedPowerUp.get(i).getY(), 80, 40);

            if (pop_box.intersects(wall_box)) {
                pop.collision();
                pop.gotSpeedPowerUp();
                speedPowerUp.remove(i);
            }
        }
    }

    // True = dead, False = alive
    public boolean gameOver() {
        return gameOver;
    }

    public ArrayList<Rectangle> getBlocks0() {
        return blocks0;
    }
    public ArrayList<Rectangle> getBlocks1() {
        return blocks1;
    }
    public ArrayList<Rectangle> getBlocks2() {
        return blocks2;
    }
    public ArrayList<Rectangle> getBlocks3() {
        return blocks3;
    }
    public ArrayList<Rectangle> getBlocks4() {
        return blocks4;
    }
    public ArrayList<Rectangle> getBlocks5() {
        return blocks5;
    }
    public ArrayList<Rectangle> getBlocks6() {
        return blocks6;
    }
    public ArrayList<Rectangle> getWalls() {
        return walls;
    }
    public ArrayList<Rectangle> getBiglegs() {
        return biglegs;
    }
    public ArrayList<Rectangle> getLifePowerUp() {
        return lifePowerUp;
    }

    public ArrayList<Rectangle> getSpeedPowerUp() {
        return speedPowerUp;
    }

    public int getScore() {
        return score;
    }
}
