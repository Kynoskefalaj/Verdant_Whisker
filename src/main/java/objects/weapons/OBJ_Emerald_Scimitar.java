package objects.weapons;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Emerald_Scimitar extends Entity {

    public OBJ_Emerald_Scimitar(GamePanel gp) {
        super(gp);

        name = "Emerald Scimitar";
        image1 = setUp("/objects/Emerald_Scimitar", gp.tileSize, gp.tileSize);
        down1 = image1;
        attackValue = 11;
        price = 1500;

        attackArea.width = (int)(28 * gp.scale);
        attackArea.height = (int)(28 * gp.scale);

        description = "[" + name + "]\n" +
                "Attack: " + attackValue + " \n\n" +
                "A scimitar in Blink's favourite\n" +
                "colour.";

        type = EntityType.EMERALD_SWORD;
    }
}
