package monsters;

import entities.Creature;
import entities.EntityType;
import entities.Skeleton;
import objects.projectiles.OBJ_DarkEnergyBall;
import objects.projectiles.OBJ_Rock;
import root.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Random;

public class MON_SkeletonMage extends Skeleton implements Creature {

    GamePanel gp;
    boolean shooting = false;
    private int localScale;

    public MON_SkeletonMage(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = EntityType.MONSTER;
        name = "Skeleton Mage";
        localScale = gp.tileSize * 2;

        projectile = new OBJ_DarkEnergyBall(gp);

        getImage();
        getAttackImage();
        getDyingImage();

        setSounds();
        setDefaultValues();
    }

    @Override
    public void setDefaultValues() {
        speed = 2;
        maxLife = 50;
        life = maxLife;
        attack = 12;
        defense = 5;
        exp = 25;
        attackSpeed = 1;
    }

    @Override
    public void getImage() {

        walkRight[0] = setUp("/monsters/skeleton/mage/walking/" +
                "right/skeleton_mage_walk_right_1", localScale, localScale);
        walkRight[1] = setUp("/monsters/skeleton/mage/walking/" +
                "right/skeleton_mage_walk_right_2", localScale, localScale);
        walkRight[2] = setUp("/monsters/skeleton/mage/walking/" +
                "right/skeleton_mage_walk_right_3", localScale, localScale);
        walkRight[3] = setUp("/monsters/skeleton/mage/walking/" +
                "right/skeleton_mage_walk_right_4", localScale, localScale);
        walkRight[4] = setUp("/monsters/skeleton/mage/walking/" +
                "right/skeleton_mage_walk_right_5", localScale, localScale);
        walkRight[5] = setUp("/monsters/skeleton/mage/walking/" +
                "right/skeleton_mage_walk_right_6", localScale, localScale);

        walkLeft[0] = setUp("/monsters/skeleton/mage/walking/" +
                "left/skeleton_mage_walk_left_1", localScale, localScale);
        walkLeft[1] = setUp("/monsters/skeleton/mage/walking/" +
                "left/skeleton_mage_walk_left_2", localScale, localScale);
        walkLeft[2] = setUp("/monsters/skeleton/mage/walking/" +
                "left/skeleton_mage_walk_left_3", localScale, localScale);
        walkLeft[3] = setUp("/monsters/skeleton/mage/walking/" +
                "left/skeleton_mage_walk_left_4", localScale, localScale);
        walkLeft[4] = setUp("/monsters/skeleton/mage/walking/" +
                "left/skeleton_mage_walk_left_5", localScale, localScale);
        walkLeft[5] = setUp("/monsters/skeleton/mage/walking/" +
                "left/skeleton_mage_walk_left_6", localScale, localScale);

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

        attackRight[1-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_1", localScale, localScale);
        attackRight[2-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_2", localScale, localScale);
        attackRight[3-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_3", localScale, localScale);
        attackRight[4-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_4", localScale, localScale);
        attackRight[5-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_5", localScale, localScale);
        attackRight[6-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_6", localScale, localScale);
        attackRight[7-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_7", localScale, localScale);
        attackRight[8-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_8", localScale, localScale);
        attackRight[9-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_9", localScale, localScale);
        attackRight[10-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_10", localScale, localScale);
        attackRight[11-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_11", localScale, localScale);
        attackRight[12-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_12", localScale, localScale);
        attackRight[13-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_13", localScale, localScale);
        attackRight[14-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_14", localScale, localScale);
        attackRight[15-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_15", localScale, localScale);
        attackRight[16-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_16", localScale, localScale);
        attackRight[17-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_17", localScale, localScale);
        attackRight[18-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_18", localScale, localScale);
        attackRight[19-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_19", localScale, localScale);
        attackRight[20-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_20", localScale, localScale);
        attackRight[21-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/right/skeleton_mage_right_attack_21", localScale, localScale);


        attackLeft[1-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_1", localScale, localScale);
        attackLeft[2-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_2", localScale, localScale);
        attackLeft[3-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_3", localScale, localScale);
        attackLeft[4-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_4", localScale, localScale);
        attackLeft[5-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_5", localScale, localScale);
        attackLeft[6-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_6", localScale, localScale);
        attackLeft[7-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_7", localScale, localScale);
        attackLeft[8-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_8", localScale, localScale);
        attackLeft[9-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_9", localScale, localScale);
        attackLeft[10-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_10", localScale, localScale);
        attackLeft[11-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_11", localScale, localScale);
        attackLeft[12-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_12", localScale, localScale);
        attackLeft[13-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_13", localScale, localScale);
        attackLeft[14-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_14", localScale, localScale);
        attackLeft[15-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_15", localScale, localScale);
        attackLeft[16-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_16", localScale, localScale);
        attackLeft[17-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_17", localScale, localScale);
        attackLeft[18-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_18", localScale, localScale);
        attackLeft[19-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_19", localScale, localScale);
        attackLeft[21-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_21", localScale, localScale);
        attackLeft[20-1] = setUp("/monsters/skeleton/mage/combat/" +
                "attack/left/skeleton_mage_left_attack_20", localScale, localScale);
    }
    @Override
    public void getDyingImage() {

        dieRight[1-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_1", localScale, localScale);
        dieRight[2-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_2", localScale, localScale);
        dieRight[3-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_3", localScale, localScale);
        dieRight[4-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_4", localScale, localScale);
        dieRight[5-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_5", localScale, localScale);
        dieRight[6-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_6", localScale, localScale);
        dieRight[7-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_7", localScale, localScale);
        dieRight[8-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_8", localScale, localScale);
        dieRight[9-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_9", localScale, localScale);
        dieRight[10-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_10", localScale, localScale);
        dieRight[11-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_11", localScale, localScale);
        dieRight[12-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_12", localScale, localScale);
        dieRight[13-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_13", localScale, localScale);
        dieRight[14-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_14", localScale, localScale);
        dieRight[15-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_15", localScale, localScale);
        dieRight[16-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_16", localScale, localScale);
        dieRight[17-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_17", localScale, localScale);
        dieRight[18-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/right/skeleton_mage_dying_right_18", localScale, localScale);


        dieLeft[1-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_1", localScale, localScale);
        dieLeft[2-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_2", localScale, localScale);
        dieLeft[3-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_3", localScale, localScale);
        dieLeft[4-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_4", localScale, localScale);
        dieLeft[5-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_5", localScale, localScale);
        dieLeft[6-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_6", localScale, localScale);
        dieLeft[7-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_7", localScale, localScale);
        dieLeft[8-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_8", localScale, localScale);
        dieLeft[9-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_9", localScale, localScale);
        dieLeft[10-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_10", localScale, localScale);
        dieLeft[11-1]= setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_11", localScale, localScale);
        dieLeft[12-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_12", localScale, localScale);
        dieLeft[13-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_13", localScale, localScale);
        dieLeft[14-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_14", localScale, localScale);
        dieLeft[15-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_15", localScale, localScale);
        dieLeft[16-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_16", localScale, localScale);
        dieLeft[17-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_17", localScale, localScale);
        dieLeft[18-1] = setUp("/monsters/skeleton/mage/combat/" +
                "dying/left/skeleton_mage_dying_left_18", localScale, localScale);

    }
    @Override
    public void setAction() {

        actionLockCounter++;
        if (actionLockCounter > 90 && !attacking) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up a number from 0 to 100

            if (i <= 25) {direction = "up";}
            if (i > 25 && i <= 50) {direction = "down";}
            if (i > 50 && i <= 75) {direction = "left";}
            if (i > 75) {direction = "right";}
            actionLockCounter = 0;
        }

        int i = new Random().nextInt(100)+1;
        if (i > 99 && projectile.alive == false && shotAvailableCounter == 30 && shooting == false) {
            shooting = true;
        }
    }

    public void shoot() {
        projectile.set(worldX, worldY, direction, true, this);
        gp.projectilesList.add(projectile);
        shotAvailableCounter = 0;
    }

    public void attack() {
        attackSpriteCounter++;

        attacking = true;
        int v = 5;

        if(attackSpriteCounter <= v ) {spriteNum = 1;}
        if (attackSpriteCounter > v  && attackSpriteCounter <= v * 2) {spriteNum = 2;}
        if (attackSpriteCounter > v * 2 && attackSpriteCounter <= v * 3) {spriteNum = 3;}
        if (attackSpriteCounter > v * 3 && attackSpriteCounter <= v * 4) {spriteNum = 4;}
        if (attackSpriteCounter > v * 4 && attackSpriteCounter <= v * 5) {spriteNum = 5;}
        if (attackSpriteCounter > v * 5 && attackSpriteCounter <= v * 6) {spriteNum = 6;}
        if (attackSpriteCounter > v * 6 && attackSpriteCounter <= v * 7) {spriteNum = 7;}
        if (attackSpriteCounter > v * 7 && attackSpriteCounter <= v * 8) {spriteNum = 8;}
        if (attackSpriteCounter > v * 8 && attackSpriteCounter <= v * 9) {spriteNum = 9;}
        if (attackSpriteCounter > v * 9 && attackSpriteCounter <= v * 10) {spriteNum = 10;}
        if (attackSpriteCounter > v * 10 && attackSpriteCounter <= v * 11) {
            spriteNum = 11;
            shoot();
        }
        if (attackSpriteCounter > v * 11 && attackSpriteCounter <= v * 12) {spriteNum = 12;}
        if (attackSpriteCounter > v * 12 && attackSpriteCounter <= v * 13) {spriteNum = 13;}
        if (attackSpriteCounter > v * 13 && attackSpriteCounter <= v * 14) {spriteNum = 14;}
        if (attackSpriteCounter > v * 14 && attackSpriteCounter <= v * 15) {spriteNum = 15;}
        if (attackSpriteCounter > v * 15 && attackSpriteCounter <= v * 16) {spriteNum = 16;}
        if (attackSpriteCounter > v * 16 && attackSpriteCounter <= v * 17) {spriteNum = 17;}
        if (attackSpriteCounter > v * 17 && attackSpriteCounter <= v * 18) {spriteNum = 18;}
        if (attackSpriteCounter > v * 18 && attackSpriteCounter <= v * 19) {spriteNum = 19;}
        if (attackSpriteCounter > v * 19 && attackSpriteCounter <= v * 20) {spriteNum = 20;}
        if (attackSpriteCounter > v * 20 && attackSpriteCounter <= v * 21) {spriteNum = 21;}
        if (attackSpriteCounter > v * 21) {
            spriteNum = 1;
            attackSpriteCounter = 0;
            shooting = false;
            attacking = false;

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
        if(!collisionOn && !attacking) {

            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }

        // SPRITE COUNTER + SPRITE NUMBERS
        spriteCounter++;
            if (!attacking && spriteCounter > 15) {
                if (spriteNum < 6) {
                    spriteNum++;
                    spriteCounter = 0;
                } else {
                    spriteNum = 1;
                    spriteCounter = 0;
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

        if (shooting) {
            attack();
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
        int screenX = worldX - gp.player.worldX + gp.player.screenX - gp.tileSize/2;
        int screenY = worldY - gp.player.worldY + gp.player.screenY - gp.tileSize/2;

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
            else if (dying) {
                dyingAnimation(g2);
            } else {
            switch (direction) {
                case "left", "up": // LATER HAVE TO DO SOMETHING WITH UP AND DOWN DIRECTION
                    image = walkLeft[spriteNum - 1];
                    break;
                case "right", "down": // LATER HAVE TO DO SOMETHING WITH UP AND DOWN DIRECTION
                    image = walkRight[spriteNum - 1];
                    break;
                }
            }
        }

        // Monster HP Bar
        if (type == EntityType.MONSTER && hpBarOn == true) {

            double oneScale = (double)gp.tileSize / maxLife;
            double hpBarValue = oneScale * life;
            g2.setColor(new Color(35,35,35));
            g2.fillRect(screenX - 1 + gp.tileSize/2, screenY, gp.tileSize + 2, 12);
            g2.setColor(new Color(223, 29, 53));
            g2.fillRect(screenX + gp.tileSize/2, screenY + 1, (int)hpBarValue, 10);

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
        }
        else {
            g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
            changeAlpha(g2,1f);
        }
    }

    @Override
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int i = 8;

        changeAlpha(g2,1f);

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX - gp.tileSize/2;
        int screenY = worldY - gp.player.worldY + gp.player.screenY - gp.tileSize/2;

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

        g2.drawImage(image, screenX, screenY,null);
        changeAlpha(g2,1f);

        if (dyingCounter > i * 19) {
            alive = false;
        }
    }
}
