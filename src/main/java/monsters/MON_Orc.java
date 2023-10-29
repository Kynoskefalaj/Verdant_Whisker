package monsters;

import entities.Entity;
import entities.EntityType;
import objects.OBJ_Bronze_Coin;
import objects.consumables.OBJ_Mana_Potion;
import root.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MON_Orc extends Entity {

    GamePanel gp;

    public MON_Orc(GamePanel gp) {
        super(gp);
        type = EntityType.MONSTER;
        this.gp = gp;
        name = "Orc";

        solidArea.x = (int)(4 * gp.scale);
        solidArea.y = (int)(4 * gp.scale);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = (int)(20 * gp.scale);
        solidArea.height = (int)(22 * gp.scale) + 9;
        attackArea.width = 48;
        attackArea.height = 48;

        getImage();
        getAttackImage();
        setSounds();
        setDefaultValues();
    }

    public void setSounds () {
        hitSound = getClass().getResource("/sound/Bump.wav");
        attackSound = null;
        deathSound = getClass().getResource("/sound/WaterSplash.wav");
    }

    public void setDefaultValues() {
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 28;
        life = maxLife;
        attack = 9;
        defense = 2;
        exp = 16;
    }
    public void getImage () {
        up1 = setUp("/monsters/orc/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setUp("/monsters/orc/orc_up_2", gp.tileSize, gp.tileSize);
        down1 = setUp("/monsters/orc/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("/monsters/orc/orc_down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("/monsters/orc/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("/monsters/orc/orc_left_2", gp.tileSize, gp.tileSize);
        right1 = setUp("/monsters/orc/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("/monsters/orc/orc_right_2", gp.tileSize, gp.tileSize);
    }
    public void getAttackImage() {
        attackUp1 = setUp("/monsters/orc/orc_attack_up_1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setUp("/monsters/orc/orc_attack_up_2", gp.tileSize, gp.tileSize*2);
        attackDown1 = setUp("/monsters/orc/orc_attack_down_1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setUp("/monsters/orc/orc_attack_down_2", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setUp("/monsters/orc/orc_attack_left_1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setUp("/monsters/orc/orc_attack_left_2", gp.tileSize*2, gp.tileSize);
        attackRight1 = setUp("/monsters/orc/orc_attack_right_1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setUp("/monsters/orc/orc_attack_right_2", gp.tileSize*2, gp.tileSize);
    }
    @Override
    public void setAction () {

        if (onPath == true) {

            // Check if it stops chasing
            checkStopChasingOrNot(gp.player, 15, 100);

            // Search the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

        } else {
            // Check if it starts chasing
            checkStartChasingOrNot(gp.player, 5,100);

            // Get a random direction
            getRandomDirection();
        }

        // Check if it attacks
        if (attacking == false) {
            checkAttackOrNot(20, gp.tileSize * 3, gp.tileSize);
        }

    }
    @Override
    public void damageReaction () {

        actionLockCounter = 0;

//        direction = gp.player.direction;

        onPath = true;
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
    public void update () {

        if (knockBack == true) {
            checkCollision();

            if(collisionOn == true) {
                // We need to cancel knockBack effect if it hits object
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if (collisionOn == false) {
                switch (knockBackDirection) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            knockBackCounter++;

            if (knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }
        else if (attacking == true) {
            attacking(10);
        }
        else {
            setAction();
            checkCollision();

            // IF COLLISION IS FALSE, ENTITY CAN MOVE
            if(collisionOn == false) {

                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else if (spriteNum == 4) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
    }
    @Override
    public void draw (Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        switch (direction) {
            case "up" :
                if (attacking == false) {
                    switch (spriteNum) {
                        case 1 -> image = up1;
                        case 2 -> image = up2;
                        case 3 -> image = up1;
                        case 4 -> image = up2;
                    }
                }
                if (attacking == true) {
                    screenY -= gp.tileSize;
                    switch (spriteNum) {
                        case 1 -> image = attackUp1;
                        case 2 -> image = attackUp2;
                        case 3 -> image = attackUp1;
                        case 4 -> image = attackUp2;
                    }
                } break;
            case "down" :
                if (attacking == false) {
                    switch (spriteNum) {
                        case 1 -> image = down1;
                        case 2 -> image = down2;
                        case 3 -> image = down1;
                        case 4 -> image = down2;
                    }
                }
                if (attacking == true) {
                    switch (spriteNum) {
                        case 1 -> image = attackDown1;
                        case 2 -> image = attackDown2;
                        case 3 -> image = attackDown1;
                        case 4 -> image = attackDown2;
                    }
                } break;
            case "left" :
                if (attacking == false) {
                    switch (spriteNum) {
                        case 1 -> image = left1;
                        case 2 -> image = left2;
                        case 3 -> image = left1;
                        case 4 -> image = left2;
                    }
                }
                if (attacking == true) {
                    screenX -= gp.tileSize;
                    switch (spriteNum) {
                        case 1 -> image = attackLeft1;
                        case 2 -> image = attackLeft2;
                        case 3 -> image = attackLeft1;
                        case 4 -> image = attackLeft2;
                    }
                } break;
            case "right" :
                if (attacking == false) {
                    switch (spriteNum) {
                        case 1 -> image = right1;
                        case 2 -> image = right2;
                        case 3 -> image = right1;
                        case 4 -> image = right2;
                    }
                }
                if (attacking == true) {
                    switch (spriteNum) {
                        case 1 -> image = attackRight1;
                        case 2 -> image = attackRight2;
                        case 3 -> image = attackRight1;
                        case 4 -> image = attackRight2;
                    }
                } break;
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
