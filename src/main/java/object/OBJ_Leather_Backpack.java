package object;

import entity.Entity;
import root.GamePanel;

public class OBJ_Leather_Backpack extends Entity {

    public OBJ_Leather_Backpack(GamePanel gp) {
        super(gp);
        name = "Leather Backpack";
        image1 = setUp("/objects/Leather_Backpack", gp.tileSize, gp.tileSize);
    }
}
