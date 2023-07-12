package object;

import entity.Entity;
import root.GamePanel;

public class OBJ_Heart extends Entity {

    public OBJ_Heart (GamePanel gp) {
        super(gp);
        name = "Heart";
        image1 = setUp("/objects/hud/heart_full");
        image2 = setUp("/objects/hud/heart_half");
        image3 = setUp("/objects/hud/heart_blank");

        int scaledSize = gp.tileSize * 4/5;
        image1 = uTool.scaleImage(image1, scaledSize, scaledSize);
        image2 = uTool.scaleImage(image2, scaledSize, scaledSize);
        image3 = uTool.scaleImage(image3, scaledSize, scaledSize);



    }
}
