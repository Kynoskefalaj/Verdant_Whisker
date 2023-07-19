package object;

import entity.Entity;
import root.GamePanel;

public class OBJ_Wooden_Shield extends Entity {


    public OBJ_Wooden_Shield(GamePanel gp) {
        super(gp);

        name = "Wooden Shield";
        image1 = setUp("/objects/Wooden_Shield", gp.tileSize, gp.tileSize);
        defenseValue = 4;

    }
}
