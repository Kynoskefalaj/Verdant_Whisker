package monster;

import entity.Creature;
import entity.Entity;
import root.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MON_BlueSlime extends Entity implements Creature {

    GamePanel gp;

    public MON_BlueSlime(GamePanel gp) {
        super(gp);
        type = 2;
        this.gp = gp;
        name = "Blue Slime";

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
    }

    public void getImage () {
        image1 = setUp("/monster/Slime1", gp.tileSize, gp.tileSize);
        image2 = setUp("/monster/Slime2", gp.tileSize, gp.tileSize);
        image3 = setUp("/monster/Slime3", gp.tileSize, gp.tileSize);
        image4 = setUp("/monster/Slime4", gp.tileSize, gp.tileSize);
        image5 = setUp("/monster/Slime5", gp.tileSize, gp.tileSize);
        image6 = setUp("/monster/Slime6", gp.tileSize, gp.tileSize);
        image7 = setUp("/monster/Slime7", gp.tileSize, gp.tileSize);

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

        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        if (dying == true) {
            dyingAnimation(g2);
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}

