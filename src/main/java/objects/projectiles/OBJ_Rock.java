package objects.projectiles;

import entities.Projectile;
import root.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Rock extends Projectile {

    GamePanel gp;

    public OBJ_Rock(GamePanel gp) {
        super(gp);

        this.gp = gp;

        name = "Rock";
        speed = 4;
        maxLife = 160;
        life = maxLife;
        attack = 7;
        useCost = 2;
        alive = false;
        getImage();

    }

    public void getImage () {

        up1 = setUp("/projectile/rock/rock_down_1", gp.tileSize, gp.tileSize);
    }

    public void draw (Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            image = up1;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize/2, gp.tileSize/2, null);

        changeAlpha(g2, 1f);
    }
}
