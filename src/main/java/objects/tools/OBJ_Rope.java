package objects.tools;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Rope extends Entity {

    public OBJ_Rope(GamePanel gp) {
        super(gp);
        name = "Rope";
        image1 = setUp("/objects/Rope", gp.tileSize, gp.tileSize);
        down1 = image1;
        price = 18;

        type = EntityType.TOOLS;
    }
}
