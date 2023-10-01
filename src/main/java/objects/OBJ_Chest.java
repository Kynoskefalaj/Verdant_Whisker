package objects;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Chest extends Entity {

    GamePanel gp;
    Entity loot;
    boolean opened = false;

    public OBJ_Chest (GamePanel gp, Entity loot) {
        super(gp);
        this.gp = gp;
        this.loot = loot;

        name = "Chest";
        image1 = setUp("/objects/chest01", gp.tileSize, gp.tileSize);
        image2 = setUp("objects/chest_opened", gp.tileSize, gp.tileSize);
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

    public void interact() {
        gp.gameState = gp.dialogueState;

        if(opened == false) {
            gp.playSE(gp.se.unlockSE);

            StringBuilder sb = new StringBuilder();
            sb.append("You've opened the chest and find a " + loot.name + "!");

            if (gp.player.inventory.size() == gp.player.maxInventorySize) {
                sb.append("\n...But you cannot carry any more!");
            }
            else {
                sb.append("\nYou have obtained the " + loot.name + "!");
                gp.player.inventory.add(loot);
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