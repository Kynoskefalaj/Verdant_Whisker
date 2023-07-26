package objects.tools;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Pouch extends Entity {

    public OBJ_Pouch(GamePanel gp) {
        super(gp);
        name = "Pouch";
        image1 = setUp("/objects/Pouch", gp.tileSize, gp.tileSize);
        down1 = image1;
        description = "[" + name + "]" + "\nSix additional slots.";

        type = EntityType.CONTAINER;
    }
}
