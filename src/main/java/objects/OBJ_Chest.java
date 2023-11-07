package objects;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Chest extends Entity {

    GamePanel gp;

    public OBJ_Chest (GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Chest";
        image1 = setUp("/objects/chest02", gp.tileSize, gp.tileSize);
        image2 = setUp("/objects/chest02_opened_32x50", gp.tileSize, gp.tileSize);
        down1 = image1;

        type = EntityType.OBSTACLE;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void setLoot(Entity loot) {
        this.loot = loot;
    }

    public void interact() {
        gp.gameState = gp.dialogueState;

        if(opened == false) {
            gp.playSE(gp.se.unlockSE);

            StringBuilder sb = new StringBuilder();
            sb.append("You've opened the chest and find a " + loot.name + "!");

            if (gp.player.canObtainItem(loot) == false) {
                sb.append("\n...But you cannot carry any more!");
            }
            else {
                sb.append("\nYou have obtained the " + loot.name + "!");
                down1 = image2;
                opened = true;
            }
            gp.ui.currentDialogue = sb.toString();
        }
        else {
            gp.ui.currentDialogue = "It's empty.";
        }
    }
}