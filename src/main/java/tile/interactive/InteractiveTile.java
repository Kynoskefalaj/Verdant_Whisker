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

    }

    public boolean isProperWeapon(Entity entity) {
        boolean isProperWeapon = false;
        return isProperWeapon;
    }
}