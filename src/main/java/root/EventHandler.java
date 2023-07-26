package root;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][];

    int prevEventX, prevEventY;
    boolean canTouchEvent = true;

    public EventHandler (GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = gp.tileSize / 2 - 8;
            eventRect[col][row].y = gp.tileSize / 2 - 8;
            eventRect[col][row].width = 16;
            eventRect[col][row].height = 16;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent () {

        // Check if the player is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - prevEventX);
        int yDistance = Math.abs(gp.player.worldY - prevEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if(canTouchEvent == true) {
            if (hit(27, 16, "right")) {damagePit(27, 16, gp.dialogueState);}
            if (hit(23, 19, "any")) {damagePit(23, 19, gp.dialogueState);}
        }

        if (hit(23, 7, "up")) {healingPool(23, 12, gp.dialogueState);}
        if (hit(21, 7, "left")) {teleport(gp.dialogueState, 37, 10);}

    }

    public boolean hit (int col, int row, String reqDirection) {

        boolean hit = false;

        gp.player.solidArea.x += gp.player.worldX;
        gp.player.solidArea.y += gp.player.worldY;
        eventRect[col][row].x += col * gp.tileSize;
        eventRect[col][row].y += row * gp.tileSize;

        if (gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) { // contentEquals(String) is used to compare two Strings values
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                prevEventX = gp.player.worldX;
                prevEventY = gp.player.worldY;
            }
        }

        // RESET after checking
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damagePit (int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.playSE(gp.sound.exhaustedSE);
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.life--;
//        eventRect[col][row].eventDone = true; //then this event will occur one time
        canTouchEvent = false;
    }

    public void healingPool (int col, int row, int gameState) {

        if (gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.player.attackCancelled = true;
            gp.playSE(gp.sound.successSE);
            gp.ui.currentDialogue = "You drink the water. \n Your health and mana have been recovered!";
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.asSetter.setMonster();
        }
    }

    public void teleport (int gameState, int destCol, int destRow) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport!";
        gp.player.worldX = gp.tileSize * destCol;
        gp.player.worldY = gp.tileSize * destRow;
    }
}
