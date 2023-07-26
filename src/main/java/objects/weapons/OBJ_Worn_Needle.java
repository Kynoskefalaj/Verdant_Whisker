package objects.weapons;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Worn_Needle extends Entity {

    public OBJ_Worn_Needle(GamePanel gp) {
        super(gp);

        name = "Worn Needle";
        image1 = setUp("/objects/Worn_Needle", gp.tileSize, gp.tileSize);
        down1 = image1;
        attackValue = 3;
        description = "[" + name + "]\n" +
                "Attack: " + attackValue + " \n\n" +
                "First lesson: \n" +
                "Stick them with the pointy \n" +
                "end.";

        attackArea.width = (int)(22 * gp.scale);
        attackArea.height = (int)(22 * gp.scale);

        type = EntityType.SWORD;
    }
}
