package objects.hud;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Mana_Crystal extends Entity {

    GamePanel gp;

    public OBJ_Mana_Crystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Mana Crystal";
        image1 = setUp("/objects/hud/manaCrystal_full", gp.tileSize, gp.tileSize);
        image2 = setUp("/objects/hud/manaCrystal_blank", gp.tileSize, gp.tileSize);

        int scaledSize = gp.tileSize * 4/5;
        image1 = uTool.scaleImage(image1, scaledSize, scaledSize);
        image2 = uTool.scaleImage(image2, scaledSize, scaledSize);

        type = EntityType.PICKUP_ONLY;
        value = 1;
        down1 = image1;
    }

    @Override
    public boolean use(Entity entity) {
        gp.playSE(gp.music.powerUpSE);
        gp.ui.addMessage("Max mana increased by: " + value);
        entity.maxMana += value;
        entity.mana += value;
        return true;
    }
}
