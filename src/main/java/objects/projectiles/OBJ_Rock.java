package objects.projectiles;

import entities.Entity;
import entities.GeneratesParticles;
import entities.Projectile;
import root.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Rock extends Projectile implements GeneratesParticles {

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
        int screenX = worldX - gp.player.worldX + gp.player.screenX + gp.tileSize/4;
        int screenY = worldY - gp.player.worldY + gp.player.screenY + gp.tileSize/4;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            image = up1;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize/2, gp.tileSize/2, null);

        changeAlpha(g2, 1f);
    }

    public boolean haveResource (Entity caster) {

        boolean haveResource = false;
        if (caster.ammo >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }

    public void subtractResource (Entity caster) {
        caster.ammo -= useCost;
    }

    @Override
    public Color getParticleColor() {
        Color color = new Color(53, 47, 47);
        return color;
    }

    @Override
    public int getParticleSize() {
        int size = 10;
        return size;
    }

    @Override
    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }

    @Override
    public int getParticleMaxLife() {
        int maxLife = 13 ;
        return maxLife;
    }
}
