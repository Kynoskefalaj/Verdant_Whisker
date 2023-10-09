package objects;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Lantern extends Entity {

    GamePanel gp;

    public OBJ_Lantern(GamePanel gp) {
        super(gp);

        type = EntityType.LIGHT;
        name = "Lantern";
        image1 = setUp("/objects/lantern", gp.tileSize, gp.tileSize);
        down1 = image1;

        description = "[Lantern]\nIlluminates your\nsurroundings.";
        price = 200;
        lightRadius = 550;
    }
}
