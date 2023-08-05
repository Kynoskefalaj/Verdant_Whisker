package tile.interactive;

import entities.Entity;
import entities.EntityType;
import entities.GeneratesParticles;
import root.GamePanel;

import java.awt.*;

public class IT_DryBush extends InteractiveTile implements GeneratesParticles {

    GamePanel gp;

    public IT_DryBush(GamePanel gp) {
        super(gp);
        this.gp = gp;

        image1 = setUp("/tiles_interactive/bush_dry", gp.tileSize, gp.tileSize);
        down1 = image1;

        life = 3;

        destructible = true;
    }

    @Override
    public InteractiveTile getDestroyedForm () {
        InteractiveTile tile = new IT_OBJ_CutBush(gp);
        return tile;
    }

    @Override
    public boolean isProperWeapon(Entity entity) {
        boolean isProperWeapon = false;

        if(entity.currentWeapon.type == EntityType.AXE) {
            isProperWeapon = true;
        }
        return isProperWeapon;
    }

    @Override
    public Color getParticleColor() {
        Color color = new Color(59, 84, 35);
        return color;
    }

    @Override
    public int getParticleSize() {
        int size = 6; // 6 pixels;
        return size;
    }

    @Override
    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }

    @Override
    public int getParticleMaxLife() {
        int maxLife = 20 ;
        return maxLife;
    }
}
