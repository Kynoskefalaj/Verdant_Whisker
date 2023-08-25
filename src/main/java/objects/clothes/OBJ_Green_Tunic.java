package objects.clothes;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Green_Tunic extends Entity {

    public OBJ_Green_Tunic(GamePanel gp) {
        super(gp);
        name = "Green Tunic";
        image1 = setUp("/objects/Green_Tunic", gp.tileSize, gp.tileSize);
        down1 = image1;
        price = 380;

        type = EntityType.ARMOR;
    }
}
