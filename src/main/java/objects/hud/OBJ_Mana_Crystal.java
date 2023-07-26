package objects.hud;

import entities.Entity;
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

    }
}
