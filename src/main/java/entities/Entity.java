package entities;

import root.GamePanel;
import root.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public abstract class Entity {

    GamePanel gp;
    public UtilityTool uTool = new UtilityTool();
    public BufferedImage image1, image2, image3, image4, image5, image6, image7, image8, image9;
    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2,
            left3, left4, right1, right2, right3, right4;
    public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4, attackUp5,
            attackDown1, attackDown2, attackDown3, attackDown4, attackDown5,
            attackLeft1, attackLeft2, attackLeft3, attackLeft4, attackLeft5,
            attackRight1, attackRight2, attackRight3, attackRight4, attackRight5;
    public Rectangle solidArea;
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String[] dialogues = new String[20];
    public Entity attacker;

    public URL hitSound, attackSound, deathSound, healSound;

    // STATE
    public int worldX, worldY ;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public String knockBackDirection;

    // COUNTER
    public int spriteCounter = 0;
    public int attackSpriteCounter = 0;
    public int actionLockCounter = 0;
    public int shotAvailableCounter = 0;
    public int invincibleCounter;
    public int dyingCounter = 0;
    public int hpBarCounter= 0;
    public int knockBackCounter = 0;

    //CHARACTER ATTRIBUTES
    public String name;
    public int defaultSpeed;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int dexterity;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public float maxStamina;
    public float stamina;
    public int agility;
    public int strength;
    public int attackSpeed;
    public int attack;
    public int defense = 0;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;
    public Projectile projectile;

//    ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 15;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public double margin;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;

    // TYPE
public EntityType type;

    public Entity (GamePanel gp) {
        this.gp = gp;
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
    }
    public int getLeftX() {
        return worldX + solidArea.x;
    }
    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }
    public int getTopY() {
        return worldY + solidArea.y;
    }
    public int getBottomY() {
        return worldY + solidArea.y + solidArea.height;
    }
    public int getCol() {
        return (worldX + solidArea.x)/gp.tileSize;
    }
    public int getRow() {
        return (worldY + solidArea.y)/gp.tileSize;
    }
    public int getXdistance (Entity target) {
        int xDistance = Math.abs(worldX - target.worldX);
        return xDistance;
    }
    public int getYdistance (Entity target) {
        int yDistance = Math.abs(worldY - target.worldY);
        return yDistance;
    }
    public int getTileDistance (Entity target) {
        int tileDistance = (getXdistance(target) + getYdistance(target)) / gp.tileSize;
        return tileDistance;
    }
    public int getGoalCol (Entity target) {
        int goalCol = (target.worldX + target.solidArea.x) / gp.tileSize;
        return goalCol;
    }
    public int getGoalRow (Entity target) {
        int goalRow = (target.worldY + target.solidArea.y) / gp.tileSize;
        return goalRow;
    }
    public void setAction () { }
    public void damageReaction () {}
    public void speak () {
        if(dialogues[dialogueIndex] == null) {
            dialogueIndex -= 1;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }
    public void interact() {}
    public boolean use (Entity entity) { return false;}
    public void checkDrop() {}
    public void dropItem(Entity droppedItem) {
        gp.asSetter.dropObject(droppedItem, worldX, worldY);
    }
    public Color getParticleColor() {
        Color color = null;
        return color;
    }
    public int getParticleSize() {
        int size = 0;
        return size;
    }
    public int getParticleSpeed() {
        int speed = 0;
        return speed;
    }
    public int getParticleMaxLife() {
        int maxLife = 0 ;
        return maxLife;
    }
    public void generateParticle(Entity generator, Entity target) {
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle pl1 = new Particle(gp, generator, color, size, speed, maxLife, -2, -1);
        Particle pl2 = new Particle(gp, generator, color, size, speed, maxLife, 2, -1);
        Particle pl3 = new Particle(gp, generator, color, size, speed, maxLife, -2, 1);
        Particle pl4 = new Particle(gp, generator, color, size, speed, maxLife, 2, 1);
        gp.particlesList.add(pl1);
        gp.particlesList.add(pl2);
        gp.particlesList.add(pl3);
        gp.particlesList.add(pl4);
    }
    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npcs);
        gp.cChecker.checkEntity(this, gp.monsters);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == EntityType.MONSTER && contactPlayer == true) {
            attackPlayer(attack);
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
            attacking();
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
    public void checkAttackOrNot(int rate, int straight, int horizontal) {
        boolean targetInRange = false;
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);

        switch (direction) {
            case "up":
                if (gp.player.worldY < worldY && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "down":
                if (gp.player.worldY > worldY && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "left":
                if (gp.player.worldY < worldY && xDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "right":
                if (gp.player.worldY > worldY && xDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;
        }
        if (targetInRange == true) {
            // Check if it initiates an attack
            int i = new Random().nextInt(rate);
            if(i == 0) {
                attacking = true;
                spriteNum = 1;
                attackSpriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }
    public void checkShootOrNot (int rate, int shotInterval) {
        int i = new Random().nextInt(rate);
        if (i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval) {
            projectile.set(worldX, worldY, direction, true, this);

            //CHECK VACANCY
            for (int ii = 0; ii < gp.projectile[1].length; ii++){
                if(gp.projectile[gp.currentMap][ii] == null) {
                    gp.projectile[gp.currentMap][ii] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }
    public void checkStartChasingOrNot (Entity target, int distance, int rate) {

        if (getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = true;
            }
        }
    }
    public void checkStopChasingOrNot (Entity target, int distance, int rate) {

        if (getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = false;
            }
        }
    }
    public void getRandomDirection() {
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
    public void attacking () {

        attackSpriteCounter++;

        if (attackSpriteCounter <= 40) {
            spriteNum = 1;
        }
        if (attackSpriteCounter > 5 && attackSpriteCounter <= 85) {
            spriteNum = 2;

            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea
            switch (direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }
            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if (type == EntityType.MONSTER) {
                if (gp.cChecker.checkPlayer(this) == true) {
                    attackPlayer(attack);
                }
            } else { // Player
                // Check monster collision with the updated worldX, worldY and solidArea
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monsters);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }


            // After checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (attackSpriteCounter > 85) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void attacking (int speed) {

        attackSpriteCounter++;

        if (attackSpriteCounter <= 4 * speed) {
            spriteNum = 1;
        }
        if (attackSpriteCounter > 5 && attackSpriteCounter <= 8 * speed) {
            spriteNum = 2;

            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea
            switch (direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }
            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if (type == EntityType.MONSTER) {
                if (gp.cChecker.checkPlayer(this) == true) {
                    attackPlayer(attack);
                }
            } else { // Player
                // Check monster collision with the updated worldX, worldY and solidArea
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monsters);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }


            // After checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (attackSpriteCounter > 85) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }


//        if (attackSpriteCounter <= 5 / attackSpeed) {spriteNum = 1;}
//        if (attackSpriteCounter > 18 / attackSpeed && attackSpriteCounter <= 54 / attackSpeed) {spriteNum = 2;}
//        if (attackSpriteCounter > 54 / attackSpeed && attackSpriteCounter <= 108 / attackSpeed) {
//            damageMonster(checkWhatsHit(), this, attack, currentWeapon.knockBackPower);
//            spriteNum = 3;
//            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
//            damageInteractiveTile(iTileIndex);
//        }
//        if (attackSpriteCounter > 108 / attackSpeed && attackSpriteCounter <= 144 / attackSpeed) {spriteNum = 4;}
//        if (attackSpriteCounter > 144 / attackSpeed && attackSpriteCounter <= 162 / attackSpeed) {spriteNum = 5;}
//        if (attackSpriteCounter > 162 / attackSpeed) { //default 162 / 6 = 27 (~0.5s)
//            spriteNum = 1;
//            attackSpriteCounter = 0;
//            attacking = false;
//        }
//    }
    public void attackPlayer (int attack) {
        if(gp.player.invincible == false) {
            int damage; //statements below are for case when armour is bigger than AP
            if (gp.player.defense >= attack) {damage = 0;}
            else {damage = attack - gp.player.defense;}
            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }
    public void setKnockBack(Entity target, Entity attacker, int knockBackPower){

        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }
    public void draw (Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            int tempScreenX = screenX;
            int tempScreenY = screenY;

            switch (direction) {
                case "up" :
                    if (attacking == false) {
                        switch (spriteNum) {
                            case 1 -> image = up1;
                            case 2 -> image = up2;
                            case 3 -> image = up3;
                            case 4 -> image = up4;
                        }
                    }
                    if (attacking == true) {
                        tempScreenY -= gp.tileSize;
                        switch (spriteNum) {
                            case 1 -> image = attackUp1;
                            case 2 -> image = attackUp2;
                            case 3 -> image = attackUp3;
                            case 4 -> image = attackUp4;
                            case 5 -> image = attackUp5;
                        }
                    } break;
                case "down" :
                    if (attacking == false) {
                        switch (spriteNum) {
                            case 1 -> image = down1;
                            case 2 -> image = down2;
                            case 3 -> image = down3;
                            case 4 -> image = down4;
                        }
                    }
                    if (attacking == true) {
                        switch (spriteNum) {
                            case 1 -> image = attackDown1;
                            case 2 -> image = attackDown2;
                            case 3 -> image = attackDown3;
                            case 4 -> image = attackDown4;
                            case 5 -> image = attackDown5;
                        }
                    } break;
                case "left" :
                    if (attacking == false) {
                        switch (spriteNum) {
                            case 1 -> image = left1;
                            case 2 -> image = left2;
                            case 3 -> image = left3;
                            case 4 -> image = left4;
                        }
                    }
                    if (attacking == true) {
                        tempScreenX -= gp.tileSize;
                        switch (spriteNum) {
                            case 1 -> image = attackLeft1;
                            case 2 -> image = attackLeft2;
                            case 3 -> image = attackLeft3;
                            case 4 -> image = attackLeft4;
                            case 5 -> image = attackLeft5;
                        }
                    } break;
                case "right" :
                    if (attacking == false) {
                        switch (spriteNum) {
                            case 1 -> image = right1;
                            case 2 -> image = right2;
                            case 3 -> image = right3;
                            case 4 -> image = right4;
                        }
                    }
                    if (attacking == true) {
                        switch (spriteNum) {
                            case 1 -> image = attackRight1;
                            case 2 -> image = attackRight2;
                            case 3 -> image = attackRight3;
                            case 4 -> image = attackRight4;
                            case 5 -> image = attackRight5;
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

            g2.drawImage(image, tempScreenX, tempScreenY,null);

            changeAlpha(g2,1f);
        }
    }
    public void dyingAnimation(Graphics2D g2) {

     dyingCounter++;

     int i = 5;

     if (dyingCounter <= i) {changeAlpha(g2, 0f);}
     if (dyingCounter > i && dyingCounter <= i * 2) {changeAlpha(g2, 1f);}
     if (dyingCounter > i * 2 && dyingCounter <= i * 3) {changeAlpha(g2, 0f);}
     if (dyingCounter > i * 3 && dyingCounter <= i * 4) {changeAlpha(g2, 1f);}
     if (dyingCounter > i * 4 && dyingCounter <= i * 5) {changeAlpha(g2, 0f);}
     if (dyingCounter > i * 5 && dyingCounter <= i * 6) {changeAlpha(g2, 1f);}
     if (dyingCounter > i * 6 && dyingCounter <= i * 7) {changeAlpha(g2, 0f);}
     if (dyingCounter > i * 7 && dyingCounter <= i * 8) {changeAlpha(g2, 1f);}
     if (dyingCounter > i * 8) {
         alive = false;
    }

    }
    public void changeAlpha (Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setUp (String imagePath, int width, int height) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    (imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void searchPath (int goalCol, int goalRow) {

        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gp.pFinder.search() == true) {
            // Next worldX & worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            //Entity's solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if(enTopY >nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            }
            else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                //left or right
                if(enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX) {
                // up or left
                direction = "up";
                checkCollision();
                if(collisionOn == true) {
                    direction = "left";
                }
            }
            else if (enTopY > nextY && enLeftX < nextX) {
                //up or right
                direction = "up";
                checkCollision();
                if(collisionOn == true) {
                    direction = "right";
                }
            }
            else if (enTopY < nextY && enLeftX > nextX) {
                // down or left
                direction = "down";
                checkCollision();
                if(collisionOn == true) {
                    direction = "left";
                }
            }
            else if (enTopY < nextY && enLeftX < nextX) {
                // down or right
                direction = "down";
                checkCollision();
                if(collisionOn == true) {
                    direction = "right";
                }
            }

            // If reaches the goal, stop the search
//            int nextCol = gp.pFinder.pathList.get(0).col;
//            int nextRow = gp.pFinder.pathList.get(0).row;
//            if(nextCol == goalCol && nextRow == goalRow) {
//                onPath = false;
//            }
        }
    }

    public int getDetected (Entity user, Entity target[][], String targetName) {

        int index = 999;

        // Check the surrounding object
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch(user.direction) {
            case "up": nextWorldY = user.getTopY() - 10; break;
            case "down": nextWorldY = user.getBottomY() + 10; break;
            case "left": nextWorldX = user.getLeftX() - 10; break;
            case "right": nextWorldY = user.getRightX() + 10; break;
        }
        int col = nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;

        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {
                if (target[gp.currentMap][i].getCol() == col &&
                    target[gp.currentMap][i].getRow() == row &&
                    target[gp.currentMap][i].name.equals(targetName)) {

                    index = i;
                    break;
                }
            }
        }
        return index;
    }
}
