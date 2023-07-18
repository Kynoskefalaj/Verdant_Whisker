package object;

import entity.Entity;
import root.GamePanel;

public class OBJ_Worn_Needle extends Entity {

    public OBJ_Worn_Needle(GamePanel gp) {
        super(gp);

        name = "Worn Needle";
        image1 = setUp("/objects/Worn_Needle", gp.tileSize, gp.tileSize);
        attackValue = 1;

    }
}
