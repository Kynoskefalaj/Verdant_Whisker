package object;

import entity.Entity;
import root.GamePanel;

public class OBJ_Boots extends Entity {

    public OBJ_Boots (GamePanel gp) {
        super(gp);

        name = "Boots";
        image1 = setUp("/objects/boots01", gp.tileSize, gp.tileSize);
        down1 = image1;
        description = "[" + name + "]" + "\nWhat a fancy colour.";
    }
}
