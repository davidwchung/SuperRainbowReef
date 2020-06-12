import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class drawObject{

    private BufferedImage img;
    private ArrayList<Rectangle> drawables;

    drawObject(BufferedImage img, ArrayList<Rectangle> drawables) {

        this.img = img;
        this.drawables = drawables;

    }

    void drawImage(Graphics g) {

        for (int i = 0; i < drawables.size(); i++) {

            Graphics2D g2d = (Graphics2D) g;
            int x = (int) drawables.get(i).getX();
            int y = (int) drawables.get(i).getY();
            g2d.drawImage(this.img, x, y, null);
        }
    }

}
