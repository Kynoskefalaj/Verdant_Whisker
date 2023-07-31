package objects;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Bronze_Coin extends Entity {

    GamePanel gp;

    public OBJ_Bronze_Coin(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = EntityType.PICKUP_ONLY;
        name = "Bronze Coin";
        image1 = setUp("/objects/Bronze_Coin", gp.tileSize, gp.tileSize);
        down1 = image1;
        value = 1;
    }

    @Override
    public void use (Entity entity) {

        gp.playSE(gp.sound.coinSE);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coin += value;
    }
}
