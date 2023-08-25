package objects.shields;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Paladin_Shield extends Entity {

    public OBJ_Paladin_Shield(GamePanel gp) {
        super(gp);
        image1 = setUp("/objects/Paladin_Shield", gp.tileSize, gp.tileSize);
        down1 = image1;
        defenseValue = 11;
        name = "Paladin Shield";
        description = "[" + name + "]\n" +
                "Defence: " + defenseValue + " \n\n" +
                "Holy Shield!";
        price = 900;

        type = EntityType.SHIELD;
    }
}