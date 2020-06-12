/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KatchControl implements KeyListener {

    private Katch katch;
    private final int right;
    private final int left;
    private final int space;
    private final int enter;

    public KatchControl(Katch katch, int left, int right, int space, int enter) {
        this.katch = katch;
        this.left = left;
        this.right = right;
        this.space = space;
        this.enter = enter;

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == left) {
            this.katch.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.katch.toggleRightPressed();
        }
        if (keyPressed == space) {
            this.katch.toggleSpacePressed();
        }
        if (keyPressed == enter) {
            this.katch.toggleEnterPressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();

        if (keyReleased  == left) {
            this.katch.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.katch.unToggleRightPressed();
        }
        if (keyReleased == space) {
            this.katch.unToggleSpacePressed();
        }
        if (keyReleased == enter) {
            this.katch.unToggleEnterPressed();
        }
    }
}
