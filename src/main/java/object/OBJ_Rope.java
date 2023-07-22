package object;

import entity.Entity;
import root.GamePanel;

public class OBJ_Rope extends Entity {

    public OBJ_Rope(GamePanel gp) {
        super(gp);
        name = "Rope";
        image1 = setUp("/objects/Rope", gp.tileSize, gp.tileSize);
        down1 = image1;
    }
}
