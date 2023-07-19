package object;

import entity.Entity;
import root.GamePanel;

public class OBJ_Green_Tunic extends Entity {

    public OBJ_Green_Tunic(GamePanel gp) {
        super(gp);
        name = "Green Tunic";
        image1 = setUp("/objects/Green_Tunic", gp.tileSize, gp.tileSize);
    }
}
