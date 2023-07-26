package object;

import entity.Projectile;
import root.GamePanel;

public class OBJ_Rock extends Projectile {

    GamePanel gp;

    public OBJ_Rock(GamePanel gp) {
        super(gp);

        this.gp = gp;

        name = "Rock";
        speed = 2;
        maxLife = 80;
        life = maxLife;
        attack = 7;
        useCost = 2;
        alive = false;
        getImage();

    }

    public void getImage () {

        up1 = setUp("/projectile/rock/rock_down_1", gp.tileSize, gp.tileSize);
        up2 = up1;
        up3 = up1;
        up4 = up1;
        down1 = up1;
        down2 = up1;
        down3 = up1;
        down4 = up1;
        right1 = up1;
        right2 = up1;
        right3 = up1;
        right4 = up1;
        left1 = up1;
        left2 = up1;
        left3 = up1;
        left4 = up1;
    }
}
