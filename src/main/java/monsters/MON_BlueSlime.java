package monsters;

import entities.Creature;
import entities.Entity;
import entities.EntityType;
import entities.GeneratesParticles;
import objects.OBJ_Bronze_Coin;
import objects.consumables.OBJ_Health_Potion;
import objects.consumables.OBJ_Mana_Potion;
import objects.projectiles.OBJ_DarkEnergyBall;
import objects.projectiles.OBJ_Rock;
import root.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MON_BlueSlime extends Entity implements Creature, GeneratesParticles {

    GamePanel gp;

    public MON_BlueSlime(GamePanel gp) {
        super(gp);
        type = EntityType.MONSTER;
        this.gp = gp;
        name = "Blue Slime";

        projectile = new OBJ_Rock(gp);

        solidArea.x = (int)(7 * gp.scale);
        solidArea.y = (int)(11 * gp.scale);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = (int)(18 * gp.scale);
        solidArea.height = (int)(10 * gp.scale);

        getImage();
        setSounds();
        setDefaultValues();
    }

    public void setSounds () {
        hitSound = getClass().getResource("/sound/Bump.wav");
        attackSound = null;
        deathSound = getClass().getResource("/sound/WaterSplash.wav");
    }
    @Override
    public void setDefaultValues() {
        speed = 1;
        maxLife = 14;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
    }
    public void getImage () {
        image1 = setUp("/monsters/Slime1", gp.tileSize, gp.tileSize);
        image2 = setUp("/monsters/Slime2", gp.tileSize, gp.tileSize);
        image3 = setUp("/monsters/Slime3", gp.tileSize, gp.tileSize);
        image4 = setUp("/monsters/Slime4", gp.tileSize, gp.tileSize);
        image5 = setUp("/monsters/Slime5", gp.tileSize, gp.tileSize);
        image6 = setUp("/monsters/Slime6", gp.tileSize, gp.tileSize);
        image7 = setUp("/monsters/Slime7", gp.tileSize, gp.tileSize);

        // Below is setting every direction's images <compressed>
        up1 = image1; up2 = image3; up3 = image5; up4 = image7; down1 = image1;
        down2 = image3; down3 = image5; down4 = image7; left1 = image1;
        left2 = image3; left3 = image5; left4 = image7; right1 = image1;
        right2 = image3; right3 = image5; right4 = image7;
    }
    @Override
    public void setAction () {

        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up a number from 0 to 100

            if (i <= 25) {direction = "up";}
            if (i > 25 && i <= 50) {direction = "down";}
            if (i > 50 && i <= 75) {direction = "left";}
            if (i > 75) {direction = "right";}
            actionLockCounter = 0;
        }
        int i = new Random().nextInt(100)+1;
        if (i > 99 && projectile.alive == false && shotAvailableCounter == 30) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectilesList.add(projectile);
            shotAvailableCounter = 0;
        }
    }
    @Override
    public void damageReaction () {

        actionLockCounter = 0;

        direction = gp.player.direction;
    }
    public void checkDrop() {
        // CAST A DIE
        int i = new Random().nextInt(100) + 1;

        // SET THE MONSTER DROP
        if (i < 50) {
            dropItem(new OBJ_Bronze_Coin(gp));
        }
        if (i >= 50 && i < 75) {
//            dropItem(null);
        }
        if (i >=90 && i < 100) {
            dropItem(new OBJ_Mana_Potion(gp));
        }
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
                    if (spriteNum == 1) {
                        image = image1;
                    } else if (spriteNum == 2) {
                        image = image2;
                    } else if (spriteNum == 3) {
                        image = image3;
                    } else if (spriteNum == 4) {
                        image = image4;
                    } else if (spriteNum == 5) {
                        image = image5;
                    } else if (spriteNum == 6) {
                        image = image6;
                    } else if (spriteNum == 7) {
                        image = image7;
                    }
                }

        // Monster HP Bar
        if (type == EntityType.MONSTER && hpBarOn == true) {

            double oneScale = (double)gp.tileSize / maxLife;
            double hpBarValue = oneScale * life;
            g2.setColor(new Color(35,35,35));
            g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
            g2.setColor(new Color(223, 29, 53));
            g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

            hpBarCounter++;

            if(hpBarCounter > 600) {
                hpBarCounter = 0;
                hpBarOn = false;
            }
        }

        if (invincible == true) {
            hpBarOn = true;
            hpBarCounter = 0;
            changeAlpha(g2,0.4f);
        }
        if (dying == true) {
            dyingAnimation(g2);
        }

        g2.drawImage(image, screenX, screenY,null);

        changeAlpha(g2,1f);
    }
    @Override
    public Color getParticleColor() {
        Color color = new Color(49, 97, 185);
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
        int maxLife = 12 ;
        return maxLife;
    }
}

