package root;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_StaminaWheel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    BufferedImage heart_full, heart_half, heart_blank;
    BufferedImage stWheel8, stWheel7, stWheel6, stWheel5, stWheel4, stWheel3, stWheel2, stWheel1, stWheel0;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int currentUiSE = 6;
    public int titleScreenState = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
            // CREATE HUD OBJECT
            Entity heart = new OBJ_Heart(gp);
            heart_full = heart.image;
            heart_half = heart.image2;
            heart_blank = heart.image3;

            Entity staminaWheel = new OBJ_StaminaWheel(gp);
            stWheel0 = staminaWheel.image9;
            stWheel1 = staminaWheel.image8;
            stWheel2 = staminaWheel.image7;
            stWheel3 = staminaWheel.image6;
            stWheel4 = staminaWheel.image5;
            stWheel5 = staminaWheel.image4;
            stWheel6 = staminaWheel.image3;
            stWheel7 = staminaWheel.image2;
            stWheel8 = staminaWheel.image;

    }

    public void showMessage (String text) {
        message = text;
        messageOn = true;
    }
    public void draw (Graphics2D g2) {
        this.g2 = g2; // We do it to use g2 in other methods in this class

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawPlayerStamina();
            //do play stuff later
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
            drawPlayerLife();
            drawPlayerStamina();
        }
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
            drawPlayerLife();
            drawPlayerStamina();
        }
    }

    private void drawPlayerLife() {

//        gp.player.life = 3;

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // DRAW MAX LIFE
        while(i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize /2;
        }

        // RESET
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        // DRAW CURRENT LIFE
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize /2;
        }
    }

    public void drawPlayerStamina() {
//        gp.player.stamina = 0;

        int x = gp.screenWidth - gp.tileSize - 10;
        int y = gp.tileSize / 2;
        int i = 0;

        // DRAW MAX STAMINA
        while(i < gp.player.maxStamina / 8) {
            g2.drawImage(stWheel0, x, y, null);
            i++;
            x += gp.tileSize /2;
        }

        // RESET
        x = gp.screenWidth - gp.tileSize - 10;
        y = gp.tileSize / 2;
        i = 0;

        // DRAW CURRENT STAMINA
        while (i < gp.player.stamina) {
            g2.drawImage(stWheel1, x, y, null);
            i++;
            if (i < gp.player.stamina) {
                g2.drawImage(stWheel2, x, y, null);
            }
            i++;
            if (i < gp.player.stamina) {
                g2.drawImage(stWheel3, x, y, null);
            }
            i++;
            if (i < gp.player.stamina) {
                g2.drawImage(stWheel4, x, y, null);
            }
            i++;
            if (i < gp.player.stamina) {
                g2.drawImage(stWheel5, x, y, null);
            }
            i++;
            if (i < gp.player.stamina) {
                g2.drawImage(stWheel6, x, y, null);
            }
            i++;
            if (i < gp.player.stamina) {
                g2.drawImage(stWheel7, x, y, null);
            }
            i++;
            if (i < gp.player.stamina) {
                g2.drawImage(stWheel8, x, y, null);
            }
            i++;
            x -= gp.tileSize * 4/5;
        }
    }

    public void drawTitleScreen(){
        if (titleScreenState == 0) {
            g2.setColor(new Color (0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // MAIN TITLE
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96));
            String text = "VERDANT WHISKER";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;

            // SHADOW
            g2.setColor(new Color (28, 128, 75));
            g2.drawString(text, x+5, y+5);
            // MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // SUBTITLE
            text = "The tale of Emerald Tail";
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,62));
            x = getXforCenteredText(text);
            y += 76;

            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // BLINK IMAGE
            x = gp.screenWidth / 2;
            y += gp.tileSize * 2;
            g2.drawImage(gp.player.down1, x - gp.tileSize, y - gp.tileSize,
                    gp.tileSize * 2, gp.tileSize * 2, null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - 40, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - 40, y);
            }

            text = "EXIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - 40, y);
            }
        }

        else if (titleScreenState == 1) {
            // CLASS SELECTION SCREEN
//            g2.setColor(Color.BLACK);
//            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your class";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - 40, y);
            }

            text = "Thief";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - 40, y);
            }

            text = "Sorcerer";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - 40, y);
            }

            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - 40, y);
            }

        }

    }

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);

    }

    public void drawDialogueScreen () {

        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
        x += gp.tileSize/2;
        y += gp.tileSize;

        UtilityTool uTool = new UtilityTool();
        uTool.drawEnterDelimitedString(currentDialogue, x, y,40, g2);
    }

    public void drawSubWindow (int x, int y, int width, int height) {
        Color c = new Color(0,0,0, 220);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255); //WHITE
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 28, 28);
    }

    public int getXforCenteredText (String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public void setCurrentSE () { // Resets sound effect state to 0
        if (currentUiSE % 8 == 0) {
            currentUiSE = 6;
        } else {
            currentUiSE++;
        }
    }
}
