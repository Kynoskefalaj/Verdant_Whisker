package objects.weapons;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_WoodcuttersAxe extends Entity {

    GamePanel gp;

    public OBJ_WoodcuttersAxe(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Woodcutter's Axe";

        image1 = setUp("/objects/Woodcutters_Axe", gp.tileSize, gp.tileSize);
        down1 = image1;
        attackValue = 5;
        price = 370;

        attackArea.width = (int)(18 * gp.scale);
        attackArea.height = (int)(18 * gp.scale);

        description = "[" + name + "]\n" +
                "Attack: " + attackValue + " \n\n" +
                "All that bushes around are\n" +
                "unbelievably strong...\n" +
                "But that axe should\n" +
                "overcome it.";

        type = EntityType.AXE;
    }
}
