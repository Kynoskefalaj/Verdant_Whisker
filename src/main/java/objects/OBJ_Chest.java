package objects;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Chest extends Entity {

    public OBJ_Chest (GamePanel gp) {
        super(gp);
        name = "Chest";
        down1 = setUp("/objects/chest01", gp.tileSize, gp.tileSize);
        type = EntityType.OBSTACLE;
    }
}