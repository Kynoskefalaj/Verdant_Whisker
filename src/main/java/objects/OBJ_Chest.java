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

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You've opened the chest and find a " + loot.name +
                "!\n...But you cannot carry any more!";
        dialogues[1][0] = "You've opened the chest and find a " + loot.name +
                "!\nYou have obtained the " + loot.name + "!";
        dialogues[2][0] = "It's empty.";
    }

    public void interact() {

        if(opened == false) {
            gp.playSE(gp.se.unlockSE);

            if (gp.player.canObtainItem(loot) == false) {
                startDialogue(this, 0);
            }
            else {
                startDialogue(this, 1);
                down1 = image2;
                opened = true;
            }
        }
        else {
            startDialogue(this, 2);
        }
    }
}