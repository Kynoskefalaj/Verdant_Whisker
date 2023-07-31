package objects.hud;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Heart extends Entity {

    GamePanel gp;

    public OBJ_Heart (GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Heart";
        image1 = setUp("/objects/hud/heart_full", gp.tileSize, gp.tileSize);
        image2 = setUp("/objects/hud/heart_half", gp.tileSize, gp.tileSize);
        image3 = setUp("/objects/hud/heart_blank", gp.tileSize, gp.tileSize);

        int scaledSize = gp.tileSize * 4/5;
        image1 = uTool.scaleImage(image1, scaledSize, scaledSize);
        image2 = uTool.scaleImage(image2, scaledSize, scaledSize);
        image3 = uTool.scaleImage(image3, scaledSize, scaledSize);

        type = EntityType.PICKUP_ONLY;
        value = 2;
        down1 = image1;
    }

    @Override
    public void use(Entity entity) {
        gp.playSE(gp.sound.powerUpSE);
        gp.ui.addMessage("Max life increased by: " + value);
        entity.maxLife += value;
        entity.life += value;
    }
}
