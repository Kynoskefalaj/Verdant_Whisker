package object;

import entity.Entity;
import root.GamePanel;

public class OBJ_StaminaWheel extends Entity {

    public OBJ_StaminaWheel(GamePanel gp) {
        super(gp);
        name = "StaminaWheel";

        image = setUp("/objects/hud/stamina_wheel_full");
        image2 = setUp("/objects/hud/stamina_wheel_7_8");
        image3 = setUp("/objects/hud/stamina_wheel_6_8");
        image4 = setUp("/objects/hud/stamina_wheel_5_8");
        image5 = setUp("/objects/hud/stamina_wheel_4_8");
        image6 = setUp("/objects/hud/stamina_wheel_3_8");
        image7 = setUp("/objects/hud/stamina_wheel_2_8");
        image8 = setUp("/objects/hud/stamina_wheel_1_8");
        image9 = setUp("/objects/hud/stamina_wheel_blank");

        int scaledSize = gp.tileSize * 3/4;
        image = uTool.scaleImage(image, scaledSize, scaledSize);
        image2 = uTool.scaleImage(image2, scaledSize, scaledSize);
        image3 = uTool.scaleImage(image3, scaledSize, scaledSize);
        image4 = uTool.scaleImage(image4, scaledSize, scaledSize);
        image5 = uTool.scaleImage(image5, scaledSize, scaledSize);
        image6 = uTool.scaleImage(image6, scaledSize, scaledSize);
        image7 = uTool.scaleImage(image7, scaledSize, scaledSize);
        image8 = uTool.scaleImage(image8, scaledSize, scaledSize);
        image9 = uTool.scaleImage(image9, scaledSize, scaledSize);

    }
}