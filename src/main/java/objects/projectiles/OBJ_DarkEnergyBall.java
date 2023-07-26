package objects.projectiles;

import entities.Entity;
import entities.Projectile;
import root.GamePanel;

public class OBJ_DarkEnergyBall extends Projectile {

    GamePanel gp;

    public OBJ_DarkEnergyBall(GamePanel gp) {
        super(gp);

        this.gp = gp;

        name = "Dark Energy Ball";
        speed = 8;
        maxLife = 120;
        life = maxLife;
        attack = 12;
        useCost = 2;
        alive = false;
        getImage();

    }

    public void getImage () {
        up1 = setUp("/projectile/dark_energy_ball/up/deb_up_1", gp.tileSize, gp.tileSize);
        up2 = setUp("/projectile/dark_energy_ball/up/deb_up_2", gp.tileSize, gp.tileSize);
        up3 = setUp("/projectile/dark_energy_ball/up/deb_up_3", gp.tileSize, gp.tileSize);
        up4 = setUp("/projectile/dark_energy_ball/up/deb_up_4", gp.tileSize, gp.tileSize);
        up5 = setUp("/projectile/dark_energy_ball/up/deb_up_5", gp.tileSize, gp.tileSize);
        up6 = setUp("/projectile/dark_energy_ball/up/deb_up_6", gp.tileSize, gp.tileSize);
        up7 = setUp("/projectile/dark_energy_ball/up/deb_up_7", gp.tileSize, gp.tileSize);
        up8 = setUp("/projectile/dark_energy_ball/up/deb_up_8", gp.tileSize, gp.tileSize);
        up9 = setUp("/projectile/dark_energy_ball/up/deb_up_9", gp.tileSize, gp.tileSize);
        up10 = setUp("/projectile/dark_energy_ball/up/deb_up_10", gp.tileSize, gp.tileSize);
        up11 = setUp("/projectile/dark_energy_ball/up/deb_up_11", gp.tileSize, gp.tileSize);
        up12 = setUp("/projectile/dark_energy_ball/up/deb_up_12", gp.tileSize, gp.tileSize);
        up13 = setUp("/projectile/dark_energy_ball/up/deb_up_13", gp.tileSize, gp.tileSize);
        up14 = setUp("/projectile/dark_energy_ball/up/deb_up_14", gp.tileSize, gp.tileSize);
        up15 = setUp("/projectile/dark_energy_ball/up/deb_up_15", gp.tileSize, gp.tileSize);
        up16 = setUp("/projectile/dark_energy_ball/up/deb_up_16", gp.tileSize, gp.tileSize);
        up17 = setUp("/projectile/dark_energy_ball/up/deb_up_17", gp.tileSize, gp.tileSize);
        up18 = setUp("/projectile/dark_energy_ball/up/deb_up_18", gp.tileSize, gp.tileSize);
        up19 = setUp("/projectile/dark_energy_ball/up/deb_up_19", gp.tileSize, gp.tileSize);
        up20 = setUp("/projectile/dark_energy_ball/up/deb_up_20", gp.tileSize, gp.tileSize);
        up21 = setUp("/projectile/dark_energy_ball/up/deb_up_21", gp.tileSize, gp.tileSize);
        up22 = setUp("/projectile/dark_energy_ball/up/deb_up_22", gp.tileSize, gp.tileSize);
        up23 = setUp("/projectile/dark_energy_ball/up/deb_up_23", gp.tileSize, gp.tileSize);
        up24 = setUp("/projectile/dark_energy_ball/up/deb_up_24", gp.tileSize, gp.tileSize);
        up25 = setUp("/projectile/dark_energy_ball/up/deb_up_25", gp.tileSize, gp.tileSize);
        up26 = setUp("/projectile/dark_energy_ball/up/deb_up_26", gp.tileSize, gp.tileSize);
        up27 = setUp("/projectile/dark_energy_ball/up/deb_up_27", gp.tileSize, gp.tileSize);
        up28 = setUp("/projectile/dark_energy_ball/up/deb_up_28", gp.tileSize, gp.tileSize);
        up29 = setUp("/projectile/dark_energy_ball/up/deb_up_29", gp.tileSize, gp.tileSize);
        up30 = setUp("/projectile/dark_energy_ball/up/deb_up_30", gp.tileSize, gp.tileSize);

        down1 = setUp("/projectile/dark_energy_ball/down/deb_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("/projectile/dark_energy_ball/down/deb_down_2", gp.tileSize, gp.tileSize);
        down3 = setUp("/projectile/dark_energy_ball/down/deb_down_3", gp.tileSize, gp.tileSize);
        down4 = setUp("/projectile/dark_energy_ball/down/deb_down_4", gp.tileSize, gp.tileSize);
        down5 = setUp("/projectile/dark_energy_ball/down/deb_down_5", gp.tileSize, gp.tileSize);
        down6 = setUp("/projectile/dark_energy_ball/down/deb_down_6", gp.tileSize, gp.tileSize);
        down7 = setUp("/projectile/dark_energy_ball/down/deb_down_7", gp.tileSize, gp.tileSize);
        down8 = setUp("/projectile/dark_energy_ball/down/deb_down_8", gp.tileSize, gp.tileSize);
        down9 = setUp("/projectile/dark_energy_ball/down/deb_down_9", gp.tileSize, gp.tileSize);
        down10 = setUp("/projectile/dark_energy_ball/down/deb_down_10", gp.tileSize, gp.tileSize);
        down11 = setUp("/projectile/dark_energy_ball/down/deb_down_11", gp.tileSize, gp.tileSize);
        down12 = setUp("/projectile/dark_energy_ball/down/deb_down_12", gp.tileSize, gp.tileSize);
        down13 = setUp("/projectile/dark_energy_ball/down/deb_down_13", gp.tileSize, gp.tileSize);
        down14 = setUp("/projectile/dark_energy_ball/down/deb_down_14", gp.tileSize, gp.tileSize);
        down15 = setUp("/projectile/dark_energy_ball/down/deb_down_15", gp.tileSize, gp.tileSize);
        down16 = setUp("/projectile/dark_energy_ball/down/deb_down_16", gp.tileSize, gp.tileSize);
        down17 = setUp("/projectile/dark_energy_ball/down/deb_down_17", gp.tileSize, gp.tileSize);
        down18 = setUp("/projectile/dark_energy_ball/down/deb_down_18", gp.tileSize, gp.tileSize);
        down19 = setUp("/projectile/dark_energy_ball/down/deb_down_19", gp.tileSize, gp.tileSize);
        down20 = setUp("/projectile/dark_energy_ball/down/deb_down_20", gp.tileSize, gp.tileSize);
        down21 = setUp("/projectile/dark_energy_ball/down/deb_down_21", gp.tileSize, gp.tileSize);
        down22 = setUp("/projectile/dark_energy_ball/down/deb_down_22", gp.tileSize, gp.tileSize);
        down23 = setUp("/projectile/dark_energy_ball/down/deb_down_23", gp.tileSize, gp.tileSize);
        down24 = setUp("/projectile/dark_energy_ball/down/deb_down_24", gp.tileSize, gp.tileSize);
        down25 = setUp("/projectile/dark_energy_ball/down/deb_down_25", gp.tileSize, gp.tileSize);
        down26 = setUp("/projectile/dark_energy_ball/down/deb_down_26", gp.tileSize, gp.tileSize);
        down27 = setUp("/projectile/dark_energy_ball/down/deb_down_27", gp.tileSize, gp.tileSize);
        down28 = setUp("/projectile/dark_energy_ball/down/deb_down_28", gp.tileSize, gp.tileSize);
        down29 = setUp("/projectile/dark_energy_ball/down/deb_down_29", gp.tileSize, gp.tileSize);
        down30 = setUp("/projectile/dark_energy_ball/down/deb_down_30", gp.tileSize, gp.tileSize);

        left1 = setUp("/projectile/dark_energy_ball/left/deb_left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("/projectile/dark_energy_ball/left/deb_left_2", gp.tileSize, gp.tileSize);
        left3 = setUp("/projectile/dark_energy_ball/left/deb_left_3", gp.tileSize, gp.tileSize);
        left4 = setUp("/projectile/dark_energy_ball/left/deb_left_4", gp.tileSize, gp.tileSize);
        left5 = setUp("/projectile/dark_energy_ball/left/deb_left_5", gp.tileSize, gp.tileSize);
        left6 = setUp("/projectile/dark_energy_ball/left/deb_left_6", gp.tileSize, gp.tileSize);
        left7 = setUp("/projectile/dark_energy_ball/left/deb_left_7", gp.tileSize, gp.tileSize);
        left8 = setUp("/projectile/dark_energy_ball/left/deb_left_8", gp.tileSize, gp.tileSize);
        left9 = setUp("/projectile/dark_energy_ball/left/deb_left_9", gp.tileSize, gp.tileSize);
        left10 = setUp("/projectile/dark_energy_ball/left/deb_left_10", gp.tileSize, gp.tileSize);
        left11 = setUp("/projectile/dark_energy_ball/left/deb_left_11", gp.tileSize, gp.tileSize);
        left12 = setUp("/projectile/dark_energy_ball/left/deb_left_12", gp.tileSize, gp.tileSize);
        left13 = setUp("/projectile/dark_energy_ball/left/deb_left_13", gp.tileSize, gp.tileSize);
        left14 = setUp("/projectile/dark_energy_ball/left/deb_left_14", gp.tileSize, gp.tileSize);
        left15 = setUp("/projectile/dark_energy_ball/left/deb_left_15", gp.tileSize, gp.tileSize);
        left16 = setUp("/projectile/dark_energy_ball/left/deb_left_16", gp.tileSize, gp.tileSize);
        left17 = setUp("/projectile/dark_energy_ball/left/deb_left_17", gp.tileSize, gp.tileSize);
        left18 = setUp("/projectile/dark_energy_ball/left/deb_left_18", gp.tileSize, gp.tileSize);
        left19 = setUp("/projectile/dark_energy_ball/left/deb_left_19", gp.tileSize, gp.tileSize);
        left20 = setUp("/projectile/dark_energy_ball/left/deb_left_20", gp.tileSize, gp.tileSize);
        left21 = setUp("/projectile/dark_energy_ball/left/deb_left_21", gp.tileSize, gp.tileSize);
        left22 = setUp("/projectile/dark_energy_ball/left/deb_left_22", gp.tileSize, gp.tileSize);
        left23 = setUp("/projectile/dark_energy_ball/left/deb_left_23", gp.tileSize, gp.tileSize);
        left24 = setUp("/projectile/dark_energy_ball/left/deb_left_24", gp.tileSize, gp.tileSize);
        left25 = setUp("/projectile/dark_energy_ball/left/deb_left_25", gp.tileSize, gp.tileSize);
        left26 = setUp("/projectile/dark_energy_ball/left/deb_left_26", gp.tileSize, gp.tileSize);
        left27 = setUp("/projectile/dark_energy_ball/left/deb_left_27", gp.tileSize, gp.tileSize);
        left28 = setUp("/projectile/dark_energy_ball/left/deb_left_28", gp.tileSize, gp.tileSize);
        left29 = setUp("/projectile/dark_energy_ball/left/deb_left_29", gp.tileSize, gp.tileSize);
        left30 = setUp("/projectile/dark_energy_ball/left/deb_left_30", gp.tileSize, gp.tileSize);

        right1 = setUp("/projectile/dark_energy_ball/right/deb_right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("/projectile/dark_energy_ball/right/deb_right_2", gp.tileSize, gp.tileSize);
        right3 = setUp("/projectile/dark_energy_ball/right/deb_right_3", gp.tileSize, gp.tileSize);
        right4 = setUp("/projectile/dark_energy_ball/right/deb_right_4", gp.tileSize, gp.tileSize);
        right5 = setUp("/projectile/dark_energy_ball/right/deb_right_5", gp.tileSize, gp.tileSize);
        right6 = setUp("/projectile/dark_energy_ball/right/deb_right_6", gp.tileSize, gp.tileSize);
        right7 = setUp("/projectile/dark_energy_ball/right/deb_right_7", gp.tileSize, gp.tileSize);
        right8 = setUp("/projectile/dark_energy_ball/right/deb_right_8", gp.tileSize, gp.tileSize);
        right9 = setUp("/projectile/dark_energy_ball/right/deb_right_9", gp.tileSize, gp.tileSize);
        right10 = setUp("/projectile/dark_energy_ball/right/deb_right_10", gp.tileSize, gp.tileSize);
        right11 = setUp("/projectile/dark_energy_ball/right/deb_right_11", gp.tileSize, gp.tileSize);
        right12 = setUp("/projectile/dark_energy_ball/right/deb_right_12", gp.tileSize, gp.tileSize);
        right13 = setUp("/projectile/dark_energy_ball/right/deb_right_13", gp.tileSize, gp.tileSize);
        right14 = setUp("/projectile/dark_energy_ball/right/deb_right_14", gp.tileSize, gp.tileSize);
        right15 = setUp("/projectile/dark_energy_ball/right/deb_right_15", gp.tileSize, gp.tileSize);
        right16 = setUp("/projectile/dark_energy_ball/right/deb_right_16", gp.tileSize, gp.tileSize);
        right17 = setUp("/projectile/dark_energy_ball/right/deb_right_17", gp.tileSize, gp.tileSize);
        right18 = setUp("/projectile/dark_energy_ball/right/deb_right_18", gp.tileSize, gp.tileSize);
        right19 = setUp("/projectile/dark_energy_ball/right/deb_right_19", gp.tileSize, gp.tileSize);
        right20 = setUp("/projectile/dark_energy_ball/right/deb_right_20", gp.tileSize, gp.tileSize);
        right21 = setUp("/projectile/dark_energy_ball/right/deb_right_21", gp.tileSize, gp.tileSize);
        right22 = setUp("/projectile/dark_energy_ball/right/deb_right_22", gp.tileSize, gp.tileSize);
        right23 = setUp("/projectile/dark_energy_ball/right/deb_right_23", gp.tileSize, gp.tileSize);
        right24 = setUp("/projectile/dark_energy_ball/right/deb_right_24", gp.tileSize, gp.tileSize);
        right25 = setUp("/projectile/dark_energy_ball/right/deb_right_25", gp.tileSize, gp.tileSize);
        right26 = setUp("/projectile/dark_energy_ball/right/deb_right_26", gp.tileSize, gp.tileSize);
        right27 = setUp("/projectile/dark_energy_ball/right/deb_right_27", gp.tileSize, gp.tileSize);
        right28 = setUp("/projectile/dark_energy_ball/right/deb_right_28", gp.tileSize, gp.tileSize);
        right29 = setUp("/projectile/dark_energy_ball/right/deb_right_29", gp.tileSize, gp.tileSize);
        right30 = setUp("/projectile/dark_energy_ball/right/deb_right_30", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource (Entity caster) {

        boolean haveResource = false;
        if (caster.mana >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }

    public void subtractResource (Entity caster) {
        caster.mana -= useCost;
    }
}
