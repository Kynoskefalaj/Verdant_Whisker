package tile.interactive;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

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

    @Override
    public void draw (Graphics2D g2) {

            BufferedImage image = null;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(down1, screenX, screenY, null);
            }
    }
}
