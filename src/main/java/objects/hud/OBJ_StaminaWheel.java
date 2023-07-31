package objects.hud;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_StaminaWheel extends Entity {
    GamePanel gp;

    public OBJ_StaminaWheel(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "StaminaWheel";

        image1 = setUp("/objects/hud/stamina_wheel_full", gp.tileSize, gp.tileSize);
        image2 = setUp("/objects/hud/stamina_wheel_7_8", gp.tileSize, gp.tileSize);
        image3 = setUp("/objects/hud/stamina_wheel_6_8", gp.tileSize, gp.tileSize);
        image4 = setUp("/objects/hud/stamina_wheel_5_8", gp.tileSize, gp.tileSize);
        image5 = setUp("/objects/hud/stamina_wheel_4_8", gp.tileSize, gp.tileSize);
        image6 = setUp("/objects/hud/stamina_wheel_3_8", gp.tileSize, gp.tileSize);
        image7 = setUp("/objects/hud/stamina_wheel_2_8", gp.tileSize, gp.tileSize);
        image8 = setUp("/objects/hud/stamina_wheel_1_8", gp.tileSize, gp.tileSize);
        image9 = setUp("/objects/hud/stamina_wheel_blank", gp.tileSize, gp.tileSize);

        int scaledSize = gp.tileSize * 3/4;
        image1 = uTool.scaleImage(image1, scaledSize, scaledSize);
        image2 = uTool.scaleImage(image2, scaledSize, scaledSize);
        image3 = uTool.scaleImage(image3, scaledSize, scaledSize);
        image4 = uTool.scaleImage(image4, scaledSize, scaledSize);
        image5 = uTool.scaleImage(image5, scaledSize, scaledSize);
        image6 = uTool.scaleImage(image6, scaledSize, scaledSize);
        image7 = uTool.scaleImage(image7, scaledSize, scaledSize);
        image8 = uTool.scaleImage(image8, scaledSize, scaledSize);
        image9 = uTool.scaleImage(image9, scaledSize, scaledSize);

        type = EntityType.PICKUP_ONLY;
        value = 4;
        down1 = image1;
    }

    @Override
    public void use(Entity entity) {
        gp.playSE(gp.sound.powerUpSE);
        gp.ui.addMessage("Max stamina increased by: " + value);
        entity.maxStamina += value;
        entity.stamina += value;
    }
}
