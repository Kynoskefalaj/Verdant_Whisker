package objects.clothes;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Green_Hat extends Entity {

    public OBJ_Green_Hat(GamePanel gp) {
        super(gp);
        name = "Green Hat";
        image1 = setUp("/objects/Green_Hat", gp.tileSize, gp.tileSize);
        down1 = image1;
        price = 120;

        type = EntityType.HELMET;
    }
}
