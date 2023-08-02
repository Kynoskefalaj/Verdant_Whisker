package tile.interactive;

import entities.Entity;
import root.GamePanel;

public class InteractiveTile extends Entity {

    GamePanel gp;
    public boolean destructible;

    public InteractiveTile(GamePanel gp) {
        super(gp);
        this.gp = gp;
    }

    public void update () {

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 15) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public InteractiveTile getDestroyedForm () {
        InteractiveTile tile = null;
        return tile;
    }

    public boolean isProperWeapon(Entity entity) {
        boolean isProperWeapon = false;
        return isProperWeapon;
    }
}
