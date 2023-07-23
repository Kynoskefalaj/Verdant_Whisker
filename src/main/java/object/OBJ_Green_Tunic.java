package object;

import entity.Entity;
import entity.EntityType;
import root.GamePanel;

public class OBJ_Green_Tunic extends Entity {

    public OBJ_Green_Tunic(GamePanel gp) {
        super(gp);
        name = "Green Tunic";
        image1 = setUp("/objects/Green_Tunic", gp.tileSize, gp.tileSize);
        down1 = image1;

        type = EntityType.ARMOR;
    }
}
