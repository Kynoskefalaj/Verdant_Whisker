package objects.jewellery;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Sacred_Necklace extends Entity {

    public OBJ_Sacred_Necklace(GamePanel gp) {
        super(gp);
        name = "Sacred Necklace";
        image1 = setUp("/objects/Sacred_Necklace", gp.tileSize, gp.tileSize);
        down1 = image1;

        type = EntityType.NECKLACE;
    }
}
