package objects.shields;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Wooden_Shield extends Entity {


    public OBJ_Wooden_Shield(GamePanel gp) {
        super(gp);

        name = "Wooden Shield";
        image1 = setUp("/objects/Wooden_Shield", gp.tileSize, gp.tileSize);
        down1 = image1;
        defenseValue = 4;
        description = "[" + name + "]\n" +
                "Defence: " + defenseValue + " \n\n" +
                "An old shield.";

        type = EntityType.SHIELD;
    }
}
