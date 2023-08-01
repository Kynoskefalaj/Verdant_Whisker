package tile.interactive;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class IT_DryBush extends InteractiveTile{

    GamePanel gp;

    public IT_DryBush(GamePanel gp) {
        super(gp);
        this.gp = gp;

        image1 = setUp("/tiles_interactive/bush_dry", gp.tileSize, gp.tileSize);
        down1 = image1;

        destructible = true;
    }

    public boolean isProperWeapon(Entity entity) {
        boolean isProperWeapon = false;

        if(entity.currentWeapon.type == EntityType.AXE) {
            isProperWeapon = true;
        }
        return isProperWeapon;
    }
}
