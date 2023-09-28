package objects;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Door extends Entity {

    GamePanel gp;

    public OBJ_Door (GamePanel gp) {
        super(gp);
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
    }

    public void interact() {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You need a key to open this.";
    }
}
