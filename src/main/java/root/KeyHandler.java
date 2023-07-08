package root;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, enterPressed;

    // DEBUG
    boolean checkDrawTime = false;

    public KeyHandler (GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            switch (code) {
                case KeyEvent.VK_W -> upPressed = true;
                case KeyEvent.VK_S -> downPressed = true;
                case KeyEvent.VK_A -> leftPressed = true;
                case KeyEvent.VK_D -> rightPressed = true;
                case KeyEvent.VK_SPACE -> spacePressed = true;
                case KeyEvent.VK_T -> {
                    if (checkDrawTime == false) {
                        checkDrawTime = true;
                    } else if (checkDrawTime == true) {
                        checkDrawTime = false;
                    }
                }
                case KeyEvent.VK_P -> {
                    if (gp.gameState == gp.playState) {
                        gp.gameState = gp.pauseState;
                    } else if (gp.gameState == gp.pauseState) {
                        gp.gameState = gp.playState;
                    }
                }
                case KeyEvent.VK_ENTER -> enterPressed = true;
            }
        }
        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }
        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W : upPressed = false; break;
            case KeyEvent.VK_S : downPressed = false; break;
            case KeyEvent.VK_A : leftPressed = false; break;
            case KeyEvent.VK_D : rightPressed = false; break;
            case KeyEvent.VK_SPACE : spacePressed = false; break;
        }
    }
}
