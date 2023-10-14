package objects;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Tent extends Entity {

    GamePanel gp;

    public OBJ_Tent (GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = EntityType.CONSUMABLE;
        name = "Tent";
        image1 = setUp("/objects/tent", gp.tileSize, gp.tileSize);
        down1 = image1;
        description = "[Tent]\nYou can sleep until next morning.";
        price = 300;
        stackable = true;
    }

    public boolean use (Entity entity) {

        gp.gameState = gp.sleepState;
        gp.playSE(gp.se.sleepSE);
        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        gp.player.getSleepingImage(down1);
        return true;
    }
}
