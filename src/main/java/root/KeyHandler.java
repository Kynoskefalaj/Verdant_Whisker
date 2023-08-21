package root;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, enterPressed,
            escapePressed, zeroPressed, F12Pressed, controlPressed;

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

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            titleState(code);
        }

        // PLAY STATE
        else if (gp.gameState == gp.playState) {
            playState(code);
        }

        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }

        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }

        // CHARACTER STATE
        else if (gp.gameState == gp.characterState) {
            characterState(code);
        }

        // OPTIONS STATE
        else if (gp.gameState == gp.optionsState) {
            optionsState(code);
        }

        // GAME OVER STATE
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }

        // TRADE STATE
        else if (gp.gameState == gp.tradeState) {
            tradeState(code);
        }
    }

    public void tradeState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (gp.ui.subState == Options_SubState.SELECT) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
                gp.playSE(gp.se.gui4SE);
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
                gp.playSE(gp.se.gui4SE);
            }
        }

    }

    public void titleState (int code) {

        if (gp.ui.titleScreenState == 0) {
            switch (code) {
                case KeyEvent.VK_W, KeyEvent.VK_UP :
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
                    gp.playSE(gp.se.uiSounds[gp.ui.currentUiSE - 1]);
                    gp.ui.setCurrentSE();
                    break;
                case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                    gp.playSE(gp.se.uiSounds[gp.ui.currentUiSE - 1]);
                    gp.ui.setCurrentSE();
                    break;
                case KeyEvent.VK_ENTER :
                    switch (gp.ui.commandNum) {
                        case 0 :
//                            gp.gameState = gp.playState;
                            gp.ui.titleScreenState = 1;
                            gp.playSE(gp.se.gui4SE);
                            break;
                        case 1 :
                            gp.playSE(gp.se.gui4SE);
                            break;
                        case 2 :
                            gp.playSE(gp.se.gui4SE);
                            System.exit(0);
                            break;
                    }
            }
        }
        else if (gp.ui.titleScreenState == 1) {
            switch (code) {
                case KeyEvent.VK_W, KeyEvent.VK_UP:
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                    gp.playSE(gp.se.uiSounds[gp.ui.currentUiSE - 1]);
                    gp.ui.setCurrentSE();
                    break;
                case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                    gp.playSE(gp.se.uiSounds[gp.ui.currentUiSE - 1]);
                    gp.ui.setCurrentSE();
                    break;
                case KeyEvent.VK_ENTER :
                    switch (gp.ui.commandNum) {
                        case 0 :
                            System.out.println("Do some fighter specific stuff!");
                            gp.gameState = gp.playState;
                            gp.playSE(gp.se.enterGameSE);
                            try {
                                Thread.sleep(1); //3100 is optimal
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
//                            gp.stopMusic();
                            break;
                        case 1 :
                            System.out.println("Do some thief specific stuff!");
                            gp.gameState = gp.playState;
                            gp.playSE(gp.se.enterGameSE);
                            try {
                                Thread.sleep(3100); //3100 is optimal
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                            gp.stopMusic();
                            break;
                        case 2 :
                            System.out.println("Do some sorcerer specific stuff!");
                            gp.gameState = gp.playState;
                            gp.playSE(gp.se.enterGameSE);
                            try {
                                Thread.sleep(3100); //3100 is optimal
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                            gp.stopMusic();
                            break;
                        case 3 :
                            gp.ui.titleScreenState = 0;
                            break;
                    }
            }
        }
    }

    public void playState (int code) {

        switch (code) {
            case KeyEvent.VK_W -> upPressed = true;
            case KeyEvent.VK_S -> downPressed = true;
            case KeyEvent.VK_A -> leftPressed = true;
            case KeyEvent.VK_D -> rightPressed = true;
            case KeyEvent.VK_C -> gp.gameState = gp.characterState;
            case KeyEvent.VK_SPACE -> spacePressed = true;
            case KeyEvent.VK_ENTER -> enterPressed = true;
            case KeyEvent.VK_0 -> zeroPressed = true;
            case KeyEvent.VK_F12 -> F12Pressed = true;
            case KeyEvent.VK_CONTROL -> controlPressed = true;
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
            case KeyEvent.VK_ESCAPE -> {
                escapePressed = true;
                gp.gameState = gp.optionsState;
            }
        }
    }

    public void optionsState (int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case TOP: maxCommandNum = 5; break;
            case END_GAME: maxCommandNum = 1; break;
            case GRAPHICS: maxCommandNum = 2; break;
            case PROPORTIONS: maxCommandNum = 3; break;
        }

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            gp.playSE(gp.se.gui1SE);
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            gp.playSE(gp.se.gui1SE);
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.subState == Options_SubState.TOP) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(gp.se.gui2SE);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    gp.playSE(gp.se.gui2SE);
                }
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.subState == Options_SubState.TOP) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(gp.se.gui4SE);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                    gp.playSE(gp.se.gui4SE);
                }
            }
        }
    }

    public void pauseState (int code) {

        if (code == KeyEvent.VK_P || code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState (int code) {

        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }

    public void characterState (int code) {

        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
            gp.playSE(gp.se.cursorSE);
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
            gp.playSE(gp.se.cursorSE);
        }

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if (gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
                gp.playSE(gp.se.cursorSE);
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if (gp.ui.slotRow != 4) {
                gp.ui.slotRow++;
                gp.playSE(gp.se.cursorSE);
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.slotCol != 0) {
                gp.ui.slotCol--;
                gp.playSE(gp.se.cursorSE);
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.slotCol != 2) {
                gp.ui.slotCol++;
                gp.playSE(gp.se.cursorSE);
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
    }

    private void gameOverState(int code) {
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
            gp.playSE(gp.se.gui3SE);
        }

        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(gp.se.gui3SE);
        }
        if(code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.retry();
                gp.playMusic(gp.music.mainTheme);
            }
            if (gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.ui.titleScreenState = 0;
                gp.restore();
                gp.playMusic(gp.music.mainTheme);
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
            case KeyEvent.VK_CONTROL : controlPressed = false; break;
        }
    }
}
