package objects;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Key extends Entity {

    GamePanel gp;

    public OBJ_Key (GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Key";
        image1 = setUp("/objects/key01", gp.tileSize, gp.tileSize);
        down1 = image1;
        description = "[" + name + "]" + "\nIt opens the door.";
        price = 50;
        stackable = true;

        type = EntityType.CONSUMABLE;

        setDialogue();
    }

    public void setDialogue() {

        dialogues[0][0] = "You use the " + name + " and open the door";

        dialogues[1][0] = "What are you doing?";
    }

    public boolean use (Entity entity) {

        int objIndex = getDetected(entity, gp.objects, "Door");

        if (objIndex != 999) {
            startDialogue(this, 0);
            gp.playSE(gp.se.unlockSE);
            gp.objects[gp.currentMap][objIndex] = null;
            return true;
        }
        else {
            startDialogue(this, 1);
            return false;
        }
    }
}
