package objects.tools;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key (GamePanel gp) {
        super(gp);
        name = "Key";
        image1 = setUp("/objects/key01", gp.tileSize, gp.tileSize);
        down1 = image1;
        description = "[" + name + "]" + "\nIt opens the door.";
        price = 50;

        type = EntityType.KEY;
    }
}
