package objects;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Door extends Entity {

    GamePanel gp;

    public OBJ_Door (GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Door";
        down1 = setUp("/objects/door01", gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 32;
        solidArea.width = gp.tileSize;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        type = EntityType.OBSTACLE;

        setDialogue();
    }

    public void setDialogue() {

        dialogues[0][0] = "You need a key to open this.";
    }

    public void interact() {

        startDialogue(this, 0);
    }
}
