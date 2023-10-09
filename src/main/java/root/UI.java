package root;

import entities.Entity;
import objects.hud.OBJ_Heart;
import objects.hud.OBJ_Mana_Crystal;
import objects.hud.OBJ_StaminaWheel;
import objects.OBJ_Bronze_Coin;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    BufferedImage heart_full, heart_half, heart_blank, coin;
    BufferedImage stWheel8, stWheel7, stWheel6, stWheel5, stWheel4, stWheel3, stWheel2, stWheel1, stWheel0;
    BufferedImage crystal_full, crystal_blank;

    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentDialogue = "";
    public int commandNum = 0;
    public int currentUiSE = 1;
    public int titleScreenState = 0;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    int counter = 0;
    public Entity npc;

    public Options_SubState subState = Options_SubState.TOP;



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
            heart_full = heart.image1;
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
            stWheel8 = staminaWheel.image1;

            Entity manaCrystal = new OBJ_Mana_Crystal(gp);
            crystal_full = manaCrystal.image1;
            crystal_blank = manaCrystal.image2;
            Entity bronzeCoin = new OBJ_Bronze_Coin(gp);
            coin = bronzeCoin.image1;

    }

    public void addMessage (String text) {

        message.add(text);
        messageCounter.add(0);
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
            drawPlayerLife_Mana();
            drawPlayerStamina();
            drawMessage();
            if (gp.keyH.F12Pressed) {
                drawCurrentCoordinates(g2);
            }
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
            drawPlayerLife_Mana();
            drawPlayerStamina();
        }
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife_Mana();
            drawPlayerStamina();
            drawDialogueScreen();
        }
        // CHARACTER STATE
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory(gp.player, Options_SubState.INVENTORY, true);
        }
        // OPTIONS STATE
        if (gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }
        // GAME OVER
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        // TRANSITION STATE
        if (gp.gameState == gp.transitionState) {
            drawTransition();
        }

        // TRADE STATE
        else if (gp.gameState == gp.tradeState) {
            drawTradeScreen();
        }
    }

    private  void drawGameOverScreen() {

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";
        // Shadow
        g2.setColor(Color.black);
        x = getXforScreenCenteredText(text);
        y = gp.tileSize * 5;
        g2.drawString(text, x, y);
        // Main
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforScreenCenteredText(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }

        //Back to the screen
        text = "Quit";
        x = getXforScreenCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum == 1  ) {
            g2.drawString(">", x - 40, y);
        }

    }

    private void drawPlayerLife_Mana() {

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

        // DRAW MAX MANA
        x = gp.tileSize / 2 - 5;
        y = (int) (gp.tileSize * 1.5);
        i = 0;

        while (i < gp.player.maxMana) {
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x+= 35;
        }

        // DRAW MANA
        x = gp.tileSize / 2 - 5;
        y = (int) (gp.tileSize * 1.5);
        i = 0;
        while (i < gp.player.mana) {
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += 35;
        }
    }

    public void drawPlayerStamina() {

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

    public void drawMessage () {

        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {

                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; //messageCounter++
                messageCounter.set(i, counter); //set the counter to the array
                messageY += 50;

                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawTitleScreen(){
        if (titleScreenState == 0) {
            g2.setColor(new Color (0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // MAIN TITLE
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96));
            String text = "VERDANT WHISKER";
            int x = getXforScreenCenteredText(text);
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
            x = getXforScreenCenteredText(text);
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
            x = getXforScreenCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - 40, y);
            }

            text = "LOAD GAME";
            x = getXforScreenCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - 40, y);
            }

            text = "EXIT";
            x = getXforScreenCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - 40, y);
            }
        }

        // CLASS CHOICE SCREEN
        else if (titleScreenState == 1) {
            g2.setColor(new Color (0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            // CLASS SELECTION SCREEN
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your class";
            int x = getXforScreenCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXforScreenCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - 40, y);
            }

            text = "Thief";
            x = getXforScreenCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - 40, y);
            }

            text = "Sorcerer";
            x = getXforScreenCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - 40, y);
            }

            text = "Back";
            x = getXforScreenCenteredText(text);
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
        int x = getXforScreenCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);

    }

    public void drawDialogueScreen () {

        //WINDOW
        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
        x += gp.tileSize/2;
        y += gp.tileSize;

        UtilityTool uTool = new UtilityTool();
        uTool.drawEnterDelimitedString(currentDialogue, x, y,40, g2);
    }

    public void drawCharacterScreen () {
        // CREATE A FRAME
        final int frameX = gp.tileSize - 2;
        final int frameY = gp.tileSize - 2;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10 + 4;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize * 2/3;
        final int lineHeight = 35; // Same like font size
        final int margin = 20;
        // NAMES
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight + margin;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Stamina", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Agility", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack Speed", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 34;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 34;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight;


        // VALUES
        int tailX = (frameX + frameWidth - 20);
        // Reset textY
        textY = frameY + gp.tileSize * 2/3;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight + margin;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.stamina + "/" + gp.player.maxStamina);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.agility);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attackSpeed);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;


        g2.drawImage(gp.player.currentWeapon.image1, tailX - gp.tileSize, textY - 14, null);
        textY += gp.tileSize;

        g2.drawImage(gp.player.currentShield.image1, tailX - gp.tileSize, textY - 14, null);

        drawEquipmentWindow(frameX, tailX);
    }

    public void drawEquipmentWindow (int topEdge, int rightEdge) {

        final int frameWidth = gp.tileSize * 4;
        final int frameHeight = gp.tileSize * 4;
        final int frameX = gp.screenWidth - frameWidth - gp.tileSize + 4;
        final int frameY = topEdge - 2;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        int offsetX = frameX + gp.tileSize * 3/8;
        int offsetY = frameY + gp.tileSize * 3/8;
        int subTileX = offsetX;
        int subTileY = offsetY;
        int i = 0;
        int j = 0;
        int index = 0;

        while (j < 3) {
            while (i < 3) {
                drawEmbeddedSlot(subTileX, subTileY, gp.tileSize, gp.tileSize);
                drawEquipment(g2, index, subTileX, subTileY);
                subTileX += gp.tileSize * 9/8;
                i++;
                index++;
            }
            i = 0;
            subTileX = offsetX;
            subTileY += gp.tileSize * 9/8;
            j++;
        }
    }

    public void drawInventory (Entity entity, Options_SubState subState, boolean cursor) {

        // INVENTORY FRAME INIT
        int frameWidth = 0;
        int frameHeight = 0;
        int frameX = 0;
        int frameY = 0;
        int slotCol = 0;
        int slotRow = 0;

        // DESCRIPTION FRAME INIT
        int dFrameX = 0;
        int dFrameY = 0;
        int dFrameWidth = 0;
        int dFrameHeight = 0;

        switch (subState) {
            case INVENTORY:
                frameWidth = gp.tileSize * 4;
                frameHeight = gp.tileSize * 6;
                frameX = gp.screenWidth - gp.tileSize - frameWidth + 4;
                frameY = gp.tileSize * 5 + 2;
                slotCol = playerSlotCol;
                slotRow = playerSlotRow;

                // DESCRIPTION FRAME
                dFrameX = gp.tileSize * 6 + 2;
                dFrameY = gp.tileSize * 7 + 2;
                dFrameWidth = gp.tileSize * 5;
                dFrameHeight = gp.tileSize * 4;
                break;
            case BUY, SELL:
                if(entity == gp.player) {
                    // PLAYER'S FRAME
                    frameWidth = gp.tileSize * 4;
                    frameHeight = gp.tileSize * 6;
                    frameX = gp.screenWidth - gp.tileSize/2 - frameWidth + 4;
                    frameY = gp.tileSize;
                    slotCol = playerSlotCol;
                    slotRow = playerSlotRow;
                } else {
                    // MERCHANT'S FRAME
                    frameWidth = gp.tileSize * 4;
                    frameHeight = gp.tileSize * 6;
                    frameX = gp.tileSize/2 + 4;
                    frameY = gp.tileSize;
                    slotCol = npcSlotCol;
                    slotRow = npcSlotRow;
                }
                // DESCRIPTION FRAME
                dFrameX = gp.tileSize * 9/2 + 4;
                dFrameY = frameY + gp.tileSize * 2;
                dFrameWidth = gp.tileSize * 5;
                dFrameHeight = gp.tileSize * 4;
                break;
        }


        // FRAME
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXstart = frameX + gp.tileSize * 3/8;
        final int slotYstart = frameY + gp.tileSize * 3/8;;
        int slotX = slotXstart;
        int slotY = slotYstart;

        // DRAW ITEMS
        for (int i = 0; i < entity.inventory.size(); i++) {

            // EQUIP CURSOR
            if (entity.inventory.get(i) == entity.currentWeapon ||
                    entity.inventory.get(i) == entity.currentShield ||
                    entity.inventory.get(i) == entity.currentLight) {
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(entity.inventory.get(i).image1, slotX, slotY, null);

            // DISPLAY THE AMOUNT
            if (entity == gp.player && entity.inventory.get(i).amount > 1) {
                g2.setFont(g2.getFont().deriveFont(32f));
                int amountX;
                int amountY;

                String s = "" + entity.inventory.get(i).amount;
                amountX = getXforAlignToRightText(s, slotX + 44);
                amountY = slotY + gp.tileSize;

                //SHADOW
                g2.setColor(new Color(60, 60, 60));
                g2.drawString(s, amountX, amountY);

                //NUMBER
                g2.setColor(Color.white);
                g2.drawString(s, amountX - 3, amountY - 3);
            }
            slotX += gp.tileSize * 9/8;

            if (i == 2 || i == 5 || i == 8 || i == 11 || i == 14) {
                slotY += gp.tileSize * 17/16;
                slotX = slotXstart;
            }
        }
        // CURSOR
        if (cursor == true) {

            int cursorX = slotXstart + (gp.tileSize * 9/8 * slotCol);
            int cursorY = slotYstart + (gp.tileSize * 17/16 * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            // DRAW CURSOR
            g2.setColor(new Color(25, 128, 165, 190));
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            //DRAW DESCRIPTION TEXT
            int textX = dFrameX + 20;
            int textY = dFrameY + 40;
            g2.setFont(g2.getFont().deriveFont(28F));

            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

            if (itemIndex < entity.inventory.size()) {
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
                gp.uTool.drawEnterDelimitedString(entity.inventory.get(itemIndex).description, textX, textY,
                        32, g2);
            }
        }
    }

    public void drawOptionsScreen() {
        g2.setColor(Color.white);
        g2.setFont((g2.getFont().deriveFont(32F)));

        // SUB WINDOW
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        int frameX = (gp.screenWidth - frameWidth) / 2;
        int frameY = (gp.screenHeight - frameHeight) / 2;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch(subState) {
            case TOP: options_top(frameX, frameY); break;
            case FULL_SCREEN: options_fullScreenNotification(frameX, frameY); break;
            case CONTROLS: options_control(frameX, frameY); break;
            case END_GAME: options_endGameConfirmation(frameY); break;
            case GRAPHICS: options_graphics(frameX, frameY); break;
            case PROPORTIONS: options_proportions(frameX, frameY); break;
        }

        gp.keyH.escapePressed = false;
        gp.keyH.enterPressed = false;
    }

    public void options_proportions (int frameX, int frameY) {
        int textX;
        int textY;

        // TITLE
        String text = "Proportions";
        textX = getXforScreenCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // 16 : 9
        text = "16 : 9";
        textX = getXforScreenCenteredText(text);
        textY += gp.tileSize * 2;
        g2.drawString(text, textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                gp.screenProportions = ScreenProportions.res16_9;
                subState = Options_SubState.FULL_SCREEN;
                commandNum = 0;
            }
        }

        int selectX = textX - gp.tileSize/2;
        if(gp.screenProportions == ScreenProportions.res16_9) {
            int selectY = textY - gp.tileSize/2;
            g2.setStroke(new BasicStroke(2));
            g2.drawRect(selectX, selectY, gp.tileSize*2, 42);
        }

        // 21 : 9
        text = "21 : 9";
        textX = getXforScreenCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                gp.screenProportions = ScreenProportions.res21_9;
                subState = Options_SubState.FULL_SCREEN;
                commandNum = 0;
            }
        }
        if(gp.screenProportions == ScreenProportions.res21_9) {
            int selectY = textY - gp.tileSize/2;
            g2.setStroke(new BasicStroke(2));
            g2.drawRect(selectX, selectY, gp.tileSize*2, 42);
        }

        // 3 : 2
        text = "3 : 2";
        textX = getXforScreenCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                gp.screenProportions = ScreenProportions.res3_2;
                subState = Options_SubState.FULL_SCREEN;
                commandNum = 0;
            }
        }
        if(gp.screenProportions == ScreenProportions.res3_2) {
            int selectY = textY - gp.tileSize/2;
            g2.setStroke(new BasicStroke(2));
            g2.drawRect(selectX, selectY, gp.tileSize*2, 42);
        }

        //BACK
        textY += gp.tileSize * 4;
        g2.drawString("Back", textX, textY);
        if(commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = Options_SubState.TOP;
                commandNum = 0;
            }
        }
    }

    public void options_graphics (int frameX, int frameY) {
        int textX;
        int textY;

        // TITLE
        String text = "Graphics";
        textX = getXforScreenCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Full Screen", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                if(!gp.fullScreenOn) {
                    gp.fullScreenOn = true;
                }
                else if (gp.fullScreenOn) {
                    gp.fullScreenOn = false;
                }
                subState = Options_SubState.FULL_SCREEN;
            }
        }

        textY += gp.tileSize;
        g2.drawString("Proportions", textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                subState = Options_SubState.PROPORTIONS;
            }
        }

        //BACK
        textY += gp.tileSize * 5;
        g2.drawString("Back", textX, textY);
        if(commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = Options_SubState.TOP;
                commandNum = 0;
            }
        }


        //FULL SCREEN CHECK BOX
        textX = frameX + (int)(gp.tileSize * 4.5);
        textY = frameY + (int)(gp.tileSize * 2.5);
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, gp.tileSize/2, gp.tileSize/2);
        if(gp.fullScreenOn == true) {
            g2.fillRect(textX, textY, gp.tileSize/2, gp.tileSize/2);
        }

    }

    public void options_top(int frameX, int frameY) {

        int textX;
        int textY;

        // TITLE
        String text = "Options";
        textX = getXforScreenCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Graphics", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = Options_SubState.GRAPHICS;
            }
        }

        //MUSIC
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }

        //SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if(commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }

        //CONTROL
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if(commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = Options_SubState.CONTROLS;
                commandNum = 0;
            }
        }

        //END GAME
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if(commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = Options_SubState.END_GAME;
                commandNum = 0;
            }
        }

        //BACK
        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 5) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
//                subState = Options_SubState.TOP;
                gp.gameState = gp.playState;
                commandNum = 0;
                gp.keyH.escapePressed = false;
            }
        }

        textX = frameX + (int)(gp.tileSize * 4.5);
        textY = frameY + (int)(gp.tileSize * 2.5);

        //MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24); // 120 / 5 = 24
        int volumeUnitWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeUnitWidth, 24);

        //SE
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeUnitWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeUnitWidth, 24);

        gp.config.saveConfig();
    }

    public void options_fullScreenNotification(int frameX, int frameY) {

        int textX;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "The change will take \neffect after restarting \nthe game";

        for(String line: currentDialogue.split("\n")) {
            textX = getXforScreenCenteredText(line);
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // BACK
        textY += frameY + gp.tileSize * 3;

        String text = "Back";
        textX = getXforScreenCenteredText(text);
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = Options_SubState.TOP;
            }
        }
    }

    public void options_control(int frameX, int frameY) {

        int textX;
        int textY;

        // TITLE
        String text = "Control";
        textX = getXforScreenCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Confirm/Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Shoot/Cast", textX, textY); textY += gp.tileSize;
        g2.drawString("Character screen", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Options", textX, textY); textY += gp.tileSize;

        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WSAD", textX, textY); textY += gp.tileSize;
        g2.drawString("Enter", textX, textY); textY += gp.tileSize;
        g2.drawString("Ctrl", textX, textY); textY += gp.tileSize;
        g2.drawString("C", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY); textY += gp.tileSize;

        // BACK
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = Options_SubState.TOP;
                commandNum = 3;
            }
        }

    }

    public void options_endGameConfirmation(int  frameY) {

        int textX;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "Quit the game and \nreturn to the title screen?";

        for (String line: currentDialogue.split("\n")) {
            textX = getXforScreenCenteredText(line);
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // YES
        String text = "Yes";
        textX = getXforScreenCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed) {
                subState = Options_SubState.TOP;
                titleScreenState = 0;
                drawTitleScreen();
                gp.gameState = gp.titleState;
            }
        }

        // NO
        text = "No";
        textX = getXforScreenCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed) {
                subState = Options_SubState.TOP;
                commandNum = 4;
            }
        }
    }

    public void drawTransition() {
        counter++;

        g2.setColor(new Color(0,0,0,counter * 5));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        if(counter == 50) {
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.prevEventX = gp.player.worldX;
            gp.eHandler.prevEventY = gp.player.worldY;
        }

    }

    public int getItemIndexOnSlot (int slotCol, int slotRow) {
        return slotCol + (slotRow * 3);
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

    public void drawEmbeddedSlot (int x, int y, int width, int height) {
        Color c = new Color(0,0, 0, 100);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 10, 10);

        c = new Color(255, 255, 255); //GRAY
        g2.setColor(c);
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(x, y, width, height, 10, 10);
    }

    public void drawEquipment (Graphics2D g2, int index, int x, int y) {
        if (gp.player.equipment[index] != null) {
            g2.drawImage(gp.player.equipment[index].image1, x, y, null);
        }
    }

    public int getXforScreenCenteredText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }

    public int getXforCenteredText (String text, int widthToCenter) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return widthToCenter/2 - length/2;
    }

    public int getYforCenteredText (String text, int heightToCenter) {
        int height = (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight();
        return heightToCenter/2 + height/2;
    }

    public int getXforAlignToRightText (String text, int tailX) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }

    public void setCurrentSE () { // Resets sound effect state to 0
        if (currentUiSE % 3 == 0) {
            currentUiSE = 1;
        } else {
            currentUiSE++;
        }
    }

    public void drawCurrentCoordinates (Graphics2D g2) {

        int fontSize = 38;
        int startX = gp.tileSize / 2;
        int startY = gp.screenHeight - (fontSize*4 + 6);


        String strCoordinates = "X Col: " + gp.player.worldX / gp.tileSize + "\n" +
                "Y Row: " + gp.player.worldY / gp.tileSize + "\n" +
                "World X: " + gp.player.worldX + "\n" +
                "World Y: " + gp.player.worldY;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, fontSize));
        g2.setColor(Color.BLACK);

        gp.uTool.drawEnterDelimitedString(strCoordinates, startX, startY,
                fontSize + 2, g2);

        g2.setColor(Color.WHITE);
        gp.uTool.drawEnterDelimitedString(strCoordinates, startX - 2, startY - 2,
                fontSize + 2, g2);
    }

    public void drawTradeScreen() {

        switch(subState) {
            case MERCH_SELECT: trade_select(); break;
            case BUY: trade_buy(); break;
            case SELL: trade_sell(); break;
        }
        gp.keyH.enterPressed = false;
    }

    public void trade_select() {

        if (gp.gameState == gp.tradeState) {
            drawDialogueScreen();

            // DRAW WINDOW
            int width = gp.tileSize * 5/2;
            int height = gp.tileSize * 7/2;
            int x = gp.screenWidth - width - gp.tileSize * 3;
            int y = gp.tileSize * 9/2;
            drawSubWindow(x, y, width, height);

            // DRAW TEXT
            y += gp.tileSize * 7/8;
            String text = "Buy";
            int centeredX = x + getXforCenteredText(text, width);
            if (commandNum == 0) {
                g2.drawString(">", x + 24, y);
                if(gp.keyH.enterPressed) {
                    subState = Options_SubState.BUY;
                }
            }

            g2.drawString(text, centeredX, y);
            y += gp.tileSize;
            text = "Sell";
            centeredX = x + getXforCenteredText(text, width);
            if (commandNum == 1) {
                g2.drawString(">", x + 24, y);
                if(gp.keyH.enterPressed) {
                    subState = Options_SubState.SELL;
                }
            }
            g2.drawString(text, centeredX, y);

            if (gp.keyH.escapePressed) {
                gp.gameState = gp.playState;
            }

            y += gp.tileSize;
            text = "Leave";
            centeredX = x + getXforCenteredText(text, width);
            g2.drawString(text, centeredX, y);
            if (commandNum == 2) {
                g2.drawString(">", x + 24, y);
                if(gp.keyH.enterPressed) {
                    commandNum = 0;
                    gp.gameState = gp.dialogueState;
                    subState = Options_SubState.TOP;

                    currentDialogue = "Come again, hehehe...";
                }
            }
        }
    }

    public void trade_buy() {

        // DRAW PLAYER INVENTORY
        drawInventory(gp.player, Options_SubState.BUY,false);

        //DRAW NPC INVENTORY
        drawInventory(npc, Options_SubState.BUY, true);

        //DRAW HINT WINDOW
        int x = (int)(gp.tileSize * 4.5 + 4);
        int y = gp.tileSize;
        int width = gp.tileSize * 5;
        int height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        String text = "[ESC] Back";
        g2.drawString(text,x + getXforCenteredText(text, width), y + getYforCenteredText(text, height) - 2);

        //DRAW PLAYER'S COINS WINDOW
        x = (int)(gp.tileSize * 11.5 + 4);
//        y = gp.tileSize * 9;
        width = gp.tileSize * 5;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        text = "Your coin: " + gp.player.coin;
        g2.drawString(text,x + getXforCenteredText(text, width), y + getYforCenteredText(text, height) - 2);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if (itemIndex < npc.inventory.size()) {

            x = (int)(gp.tileSize * 7 + 4 - 10);
            y = (int)(gp.tileSize * 6 - 10);
            width = (int)(gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 15, y + 15, 32, 32, null);

            int price = (int)(npc.inventory.get(itemIndex).price * gp.ui.npc.margin);
            text = "" + price;
            x = getXforAlignToRightText(text, (int)(gp.tileSize * 9));
            g2.drawString(text, x, y + 40);

            //BUY AN ITEM
            if (gp.keyH.enterPressed == true) {
                if (npc.inventory.get(itemIndex).price > gp.player.coin) {
                    subState = Options_SubState.TOP;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You need more coins to buy that!";
                    drawDialogueScreen();
                }
                else if (gp.player.canObtainItem(npc.inventory.get(itemIndex)) == false) {
                    subState = Options_SubState.TOP;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You cannot carry any more!";
                } else {
                    gp.player.coin -= price;
                    npc.coin += price;
//                    gp.player.inventory.add(npc.inventory.get(itemIndex));
                    npc.inventory.remove(npc.inventory.get(itemIndex));
                }
            }
        }
    }

    public void trade_sell() {

        // DRAW PLAYER INVENTORY
        drawInventory(gp.player, Options_SubState.SELL, true);

        // DRAW MERCHANT INVENTORY
        drawInventory(npc, Options_SubState.SELL, false);

        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        //DRAW HINT WINDOW
        x = (int)(gp.tileSize * 4.5 + 4);
        y = gp.tileSize;
        width = gp.tileSize * 5;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        String text = "[ESC] Back";
        g2.drawString(text,x + getXforCenteredText(text, width), y + getYforCenteredText(text, height) - 2);

        //DRAW PLAYER'S COINS WINDOW
        x = (int)(gp.tileSize * 11.5 + 4);
//        y = gp.tileSize * 9;
        width = gp.tileSize * 5;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        text = "Your coin: " + gp.player.coin;
        g2.drawString(text,x + getXforCenteredText(text, width), y + getYforCenteredText(text, height) - 2);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if (itemIndex < gp.player.inventory.size()) {

            x = (int)(gp.tileSize * 7 + 4 - 10);
            y = (int)(gp.tileSize * 6 - 10);
            width = (int)(gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 15, y + 15, 32, 32, null);

            int price = (int)(gp.player.inventory.get(itemIndex).price * (1 - (gp.ui.npc.margin - 1)));
            text = "" + price;
            x = getXforAlignToRightText(text, (int)(gp.tileSize * 9));
            g2.drawString(text, x, y + 40);

            //SELL AN ITEM
            if (gp.keyH.enterPressed == true) {

                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
                    gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
                    commandNum = 0;
                    subState = Options_SubState.MERCH_SELECT;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You cannot sell an equipped item.";
                }
                else if (gp.player.inventory.get(itemIndex).price > npc.coin) {
                    subState = Options_SubState.TOP;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "Merchant doesn't have that much money!";
                    drawDialogueScreen();
                }
                else if (npc.inventory.size() == npc.maxInventorySize) {
                    subState = Options_SubState.TOP;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "Merchant cannot carry any more!";
                } else {
                    if (gp.player.inventory.get(itemIndex).amount > 1) {
                        gp.player.inventory.get(itemIndex).amount--;
                        npc.inventory.add(gp.player.inventory.get(itemIndex));
                    }
                    else {
                        npc.inventory.add(gp.player.inventory.get(itemIndex));
                        gp.player.inventory.remove(gp.player.inventory.get(itemIndex));
                    }
                    npc.coin -= price;
                    gp.player.coin += price;

                }
            }
        }
    }
}