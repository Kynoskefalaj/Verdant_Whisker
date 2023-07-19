package object;

import entity.Entity;
import root.GamePanel;

public class OBJ_Green_Hat extends Entity {

    public OBJ_Green_Hat(GamePanel gp) {
        super(gp);
        name = "Green Hat";
        image1 = setUp("/objects/Green_Hat", gp.tileSize, gp.tileSize);
    }
}
