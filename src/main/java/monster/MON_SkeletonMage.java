package monster;

import entity.Creature;
import entity.EntityType;
import entity.Skeleton;
import root.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Random;

public class MON_SkeletonMage extends Skeleton implements Creature {

    GamePanel gp;

    public MON_SkeletonMage(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = EntityType.MONSTER;
        name = "Skeleton Mage";

        getImage();
        getAttackImage();
        getDyingImage();

        setSounds();
        setDefaultValues();
    }

    @Override
    public void setDefaultValues() {
        speed = 5;
        maxLife = 50;
        life = maxLife;
        attack = 12;
        defense = 5;
        exp = 25;
    }

    @Override
    public void getImage() {

        walkRight[0] = setUp("/monster/skeleton/mage/walking/" +
                "right/skeleton_mage_walk_right_1", gp.tileSize*2, gp.tileSize*2);
        walkRight[1] = setUp("/monster/skeleton/mage/walking/" +
                "right/skeleton_mage_walk_right_2", gp.tileSize*2, gp.tileSize*2);
        walkRight[2] = setUp("/monster/skeleton/mage/walking/" +
                "right/skeleton_mage_walk_right_3", gp.tileSize*2, gp.tileSize*2);
        walkRight[3] = setUp("/monster/skeleton/mage/walking/" +
                "right/skeleton_mage_walk_right_4", gp.tileSize*2, gp.tileSize*2);
        walkRight[4] = setUp("/monster/skeleton/mage/walking/" +
                "right/skeleton_mage_walk_right_5", gp.tileSize*2, gp.tileSize*2);
        walkRight[5] = setUp("/monster/skeleton/mage/walking/" +
                "right/skeleton_mage_walk_right_6", gp.tileSize*2, gp.tileSize*2);

        walkLeft[0] = setUp("/monster/skeleton/mage/walking/" +
                "left/skeleton_mage_walk_left_1", gp.tileSize*2, gp.tileSize*2);
        walkLeft[1] = setUp("/monster/skeleton/mage/walking/" +
                "left/skeleton_mage_walk_left_2", gp.tileSize*2, gp.tileSize*2);
        walkLeft[2] = setUp("/monster/skeleton/mage/walking/" +
                "left/skeleton_mage_walk_left_3", gp.tileSize*2, gp.tileSize*2);
        walkLeft[3] = setUp("/monster/skeleton/mage/walking/" +
                "left/skeleton_mage_walk_left_4", gp.tileSize*2, gp.tileSize*2);
        walkLeft[4] = setUp("/monster/skeleton/mage/walking/" +
                "left/skeleton_mage_walk_left_5", gp.tileSize*2, gp.tileSize*2);
        walkLeft[5] = setUp("/monster/skeleton/mage/walking/" +
                "left/skeleton_mage_walk_left_6", gp.tileSize*2, gp.tileSize*2);

        down1 = walkRight[0]; // IN CASE OF SPAWN
    }
    @Override
    public void setSounds() {
        hitSound = getClass().getResource("/sound/Bump.wav");
        attackSound = getClass().getResource("/sound/Explosion.wav");
        deathSound = gp.sound.evilLaughSE;
    }
    @Override
    public void getAttackImage() {

        attackRight[1-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_1", gp.tileSize, gp.tileSize);
        attackRight[2-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_2", gp.tileSize, gp.tileSize);
        attackRight[3-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_3", gp.tileSize, gp.tileSize);
        attackRight[4-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_4", gp.tileSize, gp.tileSize);
        attackRight[5-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_5", gp.tileSize, gp.tileSize);
        attackRight[6-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_6", gp.tileSize, gp.tileSize);
        attackRight[7-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_7", gp.tileSize, gp.tileSize);
        attackRight[8-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_8", gp.tileSize, gp.tileSize);
        attackRight[9-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_9", gp.tileSize, gp.tileSize);
        attackRight[10-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_10", gp.tileSize, gp.tileSize);
        attackRight[11-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_11", gp.tileSize, gp.tileSize);
        attackRight[12-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_12", gp.tileSize, gp.tileSize);
        attackRight[13-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_13", gp.tileSize, gp.tileSize);
        attackRight[14-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_14", gp.tileSize, gp.tileSize);
        attackRight[15-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_15", gp.tileSize, gp.tileSize);
        attackRight[16-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_16", gp.tileSize, gp.tileSize);
        attackRight[17-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_17", gp.tileSize, gp.tileSize);
        attackRight[18-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_18", gp.tileSize, gp.tileSize);
        attackRight[19-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_19", gp.tileSize, gp.tileSize);
        attackRight[20-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_20", gp.tileSize, gp.tileSize);
        attackRight[21-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_21", gp.tileSize, gp.tileSize);


        attackLeft[1-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_1", gp.tileSize, gp.tileSize);
        attackLeft[2-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_2", gp.tileSize, gp.tileSize);
        attackLeft[3-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_3", gp.tileSize, gp.tileSize);
        attackLeft[4-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_4", gp.tileSize, gp.tileSize);
        attackLeft[5-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_5", gp.tileSize, gp.tileSize);
        attackLeft[6-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_6", gp.tileSize, gp.tileSize);
        attackLeft[7-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_7", gp.tileSize, gp.tileSize);
        attackLeft[8-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_8", gp.tileSize, gp.tileSize);
        attackLeft[9-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_9", gp.tileSize, gp.tileSize);
        attackLeft[10-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_10", gp.tileSize, gp.tileSize);
        attackLeft[11-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_11", gp.tileSize, gp.tileSize);
        attackLeft[12-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_12", gp.tileSize, gp.tileSize);
        attackLeft[13-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_13", gp.tileSize, gp.tileSize);
        attackLeft[14-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_14", gp.tileSize, gp.tileSize);
        attackLeft[15-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_15", gp.tileSize, gp.tileSize);
        attackLeft[16-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_16", gp.tileSize, gp.tileSize);
        attackLeft[17-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_17", gp.tileSize, gp.tileSize);
        attackLeft[18-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_18", gp.tileSize, gp.tileSize);
        attackLeft[19-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_19", gp.tileSize, gp.tileSize);
        attackLeft[21-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_21", gp.tileSize, gp.tileSize);
        attackLeft[20-1] = setUp("/monster/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_20", gp.tileSize, gp.tileSize);
    }
    @Override
    public void getDyingImage() {

        dieRight[1-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_1", gp.tileSize, gp.tileSize);
        dieRight[2-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_2", gp.tileSize, gp.tileSize);
        dieRight[3-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_3", gp.tileSize, gp.tileSize);
        dieRight[4-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_4", gp.tileSize, gp.tileSize);
        dieRight[5-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_5", gp.tileSize, gp.tileSize);
        dieRight[6-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_6", gp.tileSize, gp.tileSize);
        dieRight[7-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_7", gp.tileSize, gp.tileSize);
        dieRight[8-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_8", gp.tileSize, gp.tileSize);
        dieRight[9-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_9", gp.tileSize, gp.tileSize);
        dieRight[10-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_10", gp.tileSize, gp.tileSize);
        dieRight[11-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_11", gp.tileSize, gp.tileSize);
        dieRight[12-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_12", gp.tileSize, gp.tileSize);
        dieRight[13-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_13", gp.tileSize, gp.tileSize);
        dieRight[14-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_14", gp.tileSize, gp.tileSize);
        dieRight[15-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_15", gp.tileSize, gp.tileSize);
        dieRight[16-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_16", gp.tileSize, gp.tileSize);
        dieRight[17-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_17", gp.tileSize, gp.tileSize);
        dieRight[18-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_18", gp.tileSize, gp.tileSize);


        dieLeft[1-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_1", gp.tileSize, gp.tileSize);
        dieLeft[2-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_2", gp.tileSize, gp.tileSize);
        dieLeft[3-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_3", gp.tileSize, gp.tileSize);
        dieLeft[4-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_4", gp.tileSize, gp.tileSize);
        dieLeft[5-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_5", gp.tileSize, gp.tileSize);
        dieLeft[6-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_6", gp.tileSize, gp.tileSize);
        dieLeft[7-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_7", gp.tileSize, gp.tileSize);
        dieLeft[8-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_8", gp.tileSize, gp.tileSize);
        dieLeft[9-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_9", gp.tileSize, gp.tileSize);
        dieLeft[10-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_10", gp.tileSize, gp.tileSize);
        dieLeft[11-1]= setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_11", gp.tileSize, gp.tileSize);
        dieLeft[12-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_12", gp.tileSize, gp.tileSize);
        dieLeft[13-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_13", gp.tileSize, gp.tileSize);
        dieLeft[14-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_14", gp.tileSize, gp.tileSize);
        dieLeft[15-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_15", gp.tileSize, gp.tileSize);
        dieLeft[16-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_16", gp.tileSize, gp.tileSize);
        dieLeft[17-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_17", gp.tileSize, gp.tileSize);
        dieLeft[18-1] = setUp("/monster/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_18", gp.tileSize, gp.tileSize);

    }
    @Override
    public void setAction() {

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
    public void damageReaction () {

        actionLockCounter = 0;

        direction = gp.player.direction;
    }

    @Override
    public void update () {

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npcs);
        gp.cChecker.checkEntity(this, gp.monsters);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == EntityType.MONSTER && contactPlayer == true) {
            attackPlayer(attack);
        }

        // IF COLLISION IS FALSE, ENTITY CAN MOVE
        if(!collisionOn) {

            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }


        // SPRITE COUNTER + SPRITE NUMBERS
        spriteCounter++;

        if (attacking) {
            if (spriteCounter > 20) {
                if (spriteNum < 21) {
                    spriteNum++;
                    spriteCounter = 0;
                } else {
                    spriteNum = 1;
                    spriteCounter = 0;
                }
            }
        } else {
            if (spriteCounter > 20) {
                if (spriteNum < 6) {
                    spriteNum++;
                    spriteCounter = 0;
                } else {
                    spriteNum = 1;
                    spriteCounter = 0;
                }
            }
        }

        if (invincible) {
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

    public void attackPlayer (int attack) {
        if(!gp.player.invincible) {
            int damage; //statements below are for case when armour is bigger than AP
            if (gp.player.defense >= attack) {damage = 0;}
            else {damage = attack - gp.player.defense;}
            gp.player.life -= damage;
            gp.player.invincible = true;
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

            if (attacking) {
                switch (direction) {
                    case "left", "up": // LATER HAVE TO DO SOMETHING WITH UP AND DOWN DIRECTION
                        image = attackLeft[spriteNum - 1];
                        break;
                    case "right", "down": // LATER HAVE TO DO SOMETHING WITH UP AND DOWN DIRECTION
                        image = attackRight[spriteNum - 1];
                        break;
                }
            }
            if (dying) {
                dyingAnimation(g2);
            }
            switch (direction) {
                case "left", "up": // LATER HAVE TO DO SOMETHING WITH UP AND DOWN DIRECTION
                    image = walkLeft[spriteNum - 1];
                    break;
                case "right", "down": // LATER HAVE TO DO SOMETHING WITH UP AND DOWN DIRECTION
                    image = walkRight[spriteNum - 1];
                    break;
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

        // Suppress HP Bar after a while
        if (invincible) {
            hpBarOn = true;
            hpBarCounter = 0;
            changeAlpha(g2,0.4f);
        }

        if (dying) {
            if (dyingCounter == 1) {
                gp.playSE(deathSound);
            }
            dyingAnimation(g2);
        } else {
            g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
            changeAlpha(g2,1f);
        }
    }

    @Override
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int i = 15;

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (Objects.equals(direction, "left") || Objects.equals(direction, "up")) {
            if (dyingCounter <= i) {image = dieLeft[0];}
            if (dyingCounter > i && dyingCounter <= i * 2) {image = dieLeft[1];}
            if (dyingCounter > i * 2 && dyingCounter <= i * 3) {image = dieLeft[2];}
            if (dyingCounter > i * 3 && dyingCounter <= i * 4) {image = dieLeft[3];}
            if (dyingCounter > i * 4 && dyingCounter <= i * 5) {image = dieLeft[4];}
            if (dyingCounter > i * 5 && dyingCounter <= i * 6) {image = dieLeft[5];}
            if (dyingCounter > i * 6 && dyingCounter <= i * 7) {image = dieLeft[6];}
            if (dyingCounter > i * 7 && dyingCounter <= i * 8) {image = dieLeft[7];}
            if (dyingCounter > i * 8 && dyingCounter <= i * 9) {image = dieLeft[8];}
            if (dyingCounter > i * 9 && dyingCounter <= i * 10) {image = dieLeft[9];}
            if (dyingCounter > i * 10 && dyingCounter <= i * 11) {image = dieLeft[10];}
            if (dyingCounter > i * 11 && dyingCounter <= i * 12) {image = dieLeft[11];}
            if (dyingCounter > i * 12 && dyingCounter <= i * 13) {image = dieLeft[12];}
            if (dyingCounter > i * 13 && dyingCounter <= i * 14) {image = dieLeft[13];}
            if (dyingCounter > i * 14 && dyingCounter <= i * 15) {image = dieLeft[14];}
            if (dyingCounter > i * 15 && dyingCounter <= i * 16) {image = dieLeft[15];}
            if (dyingCounter > i * 16 && dyingCounter <= i * 17) {image = dieLeft[16];}
            if (dyingCounter > i * 17 && dyingCounter <= i * 18) {image = dieLeft[17];}
        }

        if (Objects.equals(direction, "right") || Objects.equals(direction, "down")) {
            if (dyingCounter <= i) {image = dieLeft[0];}
            if (dyingCounter > i && dyingCounter <= i * 2) {image = dieRight[1];}
            if (dyingCounter > i * 2 && dyingCounter <= i * 3) {image = dieRight[2];}
            if (dyingCounter > i * 3 && dyingCounter <= i * 4) {image = dieRight[3];}
            if (dyingCounter > i * 4 && dyingCounter <= i * 5) {image = dieRight[4];}
            if (dyingCounter > i * 5 && dyingCounter <= i * 6) {image = dieRight[5];}
            if (dyingCounter > i * 6 && dyingCounter <= i * 7) {image = dieRight[6];}
            if (dyingCounter > i * 7 && dyingCounter <= i * 8) {image = dieRight[7];}
            if (dyingCounter > i * 8 && dyingCounter <= i * 9) {image = dieRight[8];}
            if (dyingCounter > i * 9 && dyingCounter <= i * 10) {image = dieRight[9];}
            if (dyingCounter > i * 10 && dyingCounter <= i * 11) {image = dieRight[10];}
            if (dyingCounter > i * 11 && dyingCounter <= i * 12) {image = dieRight[11];}
            if (dyingCounter > i * 12 && dyingCounter <= i * 13) {image = dieRight[12];}
            if (dyingCounter > i * 13 && dyingCounter <= i * 14) {image = dieRight[13];}
            if (dyingCounter > i * 14 && dyingCounter <= i * 15) {image = dieRight[14];}
            if (dyingCounter > i * 15 && dyingCounter <= i * 16) {image = dieRight[15];}
            if (dyingCounter > i * 16 && dyingCounter <= i * 17) {image = dieRight[16];}
            if (dyingCounter > i * 17 && dyingCounter <= i * 18) {image = dieRight[17];}
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
        changeAlpha(g2,1f);

        if (dyingCounter > i * 19) {
            alive = false;
        }
    }
}
