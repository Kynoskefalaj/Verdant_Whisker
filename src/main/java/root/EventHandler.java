package root;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler (GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = gp.tileSize / 2 - 8;
        eventRect.y = gp.tileSize / 2 - 8;
        eventRect.width = 16;
        eventRect.height = 16;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent () {

        if (hit(27, 16, "right")) {damagePit(gp.dialogueState);}
        if (hit(23, 7, "up")) {healingPool(gp.dialogueState);}
        if (hit(21, 7, "left")) {teleport(gp.dialogueState, 37, 10);}

    }

    public boolean hit (int eventCol, int eventRow, String reqDirection) {

        boolean hit = false;

        gp.player.solidArea.x += gp.player.worldX;
        gp.player.solidArea.y += gp.player.worldY;
        eventRect.x += eventCol * gp.tileSize;
        eventRect.y += eventRow * gp.tileSize;

        if (gp.player.solidArea.intersects(eventRect)) { // contentEquals(String) is used to compare two Strings values
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        // RESET after checking
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void damagePit (int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.life--;
    }

    public void healingPool (int gameState) {

        if (gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink the water. \n Your health has been recovered!";
            gp.player.life = gp.player.maxLife;
        }
    }

    public void teleport (int gameState, int destCol, int destRow) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport!";
        gp.player.worldX = gp.tileSize * destCol;
        gp.player.worldY = gp.tileSize * destRow;
    }
}
