package entity;
import root.GamePanel;
import root.KeyHandler;
import root.UtilityTool;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    KeyHandler keyH;
    UtilityTool uTool;

    public final int screenX;
    public final int screenY;
    int speedBoost = 0;

    int spriteSpeedModifier;


    public Player(GamePanel gp, KeyHandler keyH){
        super(gp); // CALLING THE SUPER CLASS CONSTRUCTOR

        this.keyH = keyH;
        this.uTool = new UtilityTool();

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        solidArea = new Rectangle();
        solidArea.x = (int)(10 * gp.scale);
        solidArea.y = (int)(16 * gp.scale);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = (int)(12 * gp.scale);
        solidArea.height = (int)(14 * gp.scale);

        attackArea.width = (int)(22 * gp.scale);
        attackArea.height = (int)(22 * gp.scale);

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 3;
        direction = "down";
        spriteSpeedModifier = 0;

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;
        maxStamina = 16;
        stamina = maxStamina;
        defense = 2;
        agility = 3;
        attackSpeed = agility * 2;
        strength = 3;
        attack = strength;
    }

    public void getPlayerImage() {
            up1 = setUp("/player/walk/VW_up", gp.tileSize, gp.tileSize);
            up2 = setUp("/player/walk/VW_up2", gp.tileSize, gp.tileSize);
            up3 = setUp("/player/walk/VW_up3", gp.tileSize, gp.tileSize);
            up4 = setUp("/player/walk/VW_up4", gp.tileSize, gp.tileSize);
            down1 = setUp("/player/walk/VW_down", gp.tileSize, gp.tileSize);
            down2 = setUp("/player/walk/VW_down2", gp.tileSize, gp.tileSize);
            down3 = setUp("/player/walk/VW_down3", gp.tileSize, gp.tileSize);
            down4 = setUp("/player/walk/VW_down4", gp.tileSize, gp.tileSize);
            left1 = setUp("/player/walk/VW_left", gp.tileSize, gp.tileSize);
            left2 = setUp("/player/walk/VW_left2", gp.tileSize, gp.tileSize);
            left3 = setUp("/player/walk/VW_left3", gp.tileSize, gp.tileSize);
            left4 = setUp("/player/walk/VW_left4", gp.tileSize, gp.tileSize);
            right1 = setUp("/player/walk/VW_right", gp.tileSize, gp.tileSize);
            right2 = setUp("/player/walk/VW_right2", gp.tileSize, gp.tileSize);
            right3 = setUp("/player/walk/VW_right3", gp.tileSize, gp.tileSize);
            right4 = setUp("/player/walk/VW_right4", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage () {
        attackUp1 = setUp("/player/combat/Blink_up_smear_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setUp("/player/combat/Blink_up_smear_2", gp.tileSize, gp.tileSize * 2);
        attackUp3 = setUp("/player/combat/Blink_up_smear_3", gp.tileSize, gp.tileSize * 2);
        attackUp4 = setUp("/player/combat/Blink_up_smear_4", gp.tileSize, gp.tileSize * 2);
        attackUp5 = setUp("/player/combat/Blink_up_smear_5", gp.tileSize, gp.tileSize * 2);

        attackDown1 = setUp("/player/combat/Blink_down_smear_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setUp("/player/combat/Blink_down_smear_2", gp.tileSize, gp.tileSize * 2);
        attackDown3 = setUp("/player/combat/Blink_down_smear_3", gp.tileSize, gp.tileSize * 2);
        attackDown4 = setUp("/player/combat/Blink_down_smear_4", gp.tileSize, gp.tileSize * 2);
        attackDown5 = setUp("/player/combat/Blink_down_smear_5", gp.tileSize, gp.tileSize * 2);

        attackLeft1 = setUp("/player/combat/Blink_left_smear_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setUp("/player/combat/Blink_left_smear_2", gp.tileSize * 2, gp.tileSize);
        attackLeft3 = setUp("/player/combat/Blink_left_smear_3", gp.tileSize * 2, gp.tileSize);
        attackLeft4 = setUp("/player/combat/Blink_left_smear_4", gp.tileSize * 2, gp.tileSize);
        attackLeft5 = setUp("/player/combat/Blink_left_smear_5", gp.tileSize * 2, gp.tileSize);

        attackRight1 = setUp("/player/combat/Blink_right_smear_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setUp("/player/combat/Blink_right_smear_2", gp.tileSize * 2, gp.tileSize);
        attackRight3 = setUp("/player/combat/Blink_right_smear_3", gp.tileSize * 2, gp.tileSize);
        attackRight4 = setUp("/player/combat/Blink_right_smear_4", gp.tileSize * 2, gp.tileSize);
        attackRight5 = setUp("/player/combat/Blink_right_smear_5", gp.tileSize * 2, gp.tileSize);
    }

    @Override
    public void update() {
        resetSpeed();

        if (keyH.spacePressed == false) {
            if (stamina >= maxStamina) {
                stamina = maxStamina;
            } else {
                stamina += agility * 0.005f;
            }
        }

        if (attacking == true) {
            attacking();
        }

        else if(keyH.upPressed == true || keyH.downPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {

            if (keyH.upPressed) {
                direction = "up";
            } if (keyH.downPressed) {
                direction = "down";
            } if (keyH.leftPressed) {
                direction = "left";
            } if (keyH.rightPressed) {
                direction = "right";
            }

            if (keyH.spacePressed) {
                if (stamina > 1){
                    speed *= 2;
                    spriteSpeedModifier = speed;
                }
                if (stamina <= 0) {
                    stamina = 0;
//                    gp.playSE(gp.sound.exhaustedSE);
                } else {
                    stamina -= 0.06f;
                }
            }

//            CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

//            CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

//            CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npcs); //Checks if any npc from array collided with player
            interactNPC(npcIndex);

//            CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monsters);
            contactMonster(monsterIndex);

//            CHECK EVENT
            gp.eHandler.checkEvent();

//            IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false && keyH.enterPressed == false) {

                switch (direction) {
                    case "up" -> worldY -= speed + speedBoost;
                    case "down" -> worldY += speed + speedBoost;
                    case "left" -> worldX -= speed + speedBoost;
                    case "right" -> worldX += speed + speedBoost;
                }
            }

            gp.keyH.enterPressed = false;

            spriteCounter++;
//            System.out.println("Speed:" + speed + "speedBoost: " + speedBoost);
            if (spriteCounter > 9 - spriteSpeedModifier) {
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
        // This needs to be outside of key if statement. Because it has to be updated even if player doesn't move.
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking () {
        attackSpriteCounter++;

        if(attackSpriteCounter <= 18 / attackSpeed) {spriteNum = 1;}
        if (attackSpriteCounter > 18 / attackSpeed && attackSpriteCounter <= 54 / attackSpeed) {spriteNum = 2;}
        if (attackSpriteCounter > 54 / attackSpeed && attackSpriteCounter <= 108 / attackSpeed) {
            damageMonster(checkWhatsHit());
            spriteNum = 3;
        }
        if (attackSpriteCounter > 108 / attackSpeed && attackSpriteCounter <= 144 / attackSpeed) {spriteNum = 4;}
        if (attackSpriteCounter > 144 / attackSpeed && attackSpriteCounter <= 162 / attackSpeed) {spriteNum = 5;}
        if (attackSpriteCounter > 162 / attackSpeed) { //default 162 / 6 = 27 (~0.5s)
            spriteNum = 1;
            attackSpriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject (int i) {

        if (i != 999) {
        }
    }

    public int checkWhatsHit () {
        // Save current worldX,worldY and solidArea
        int currentWorldX = worldX;
        int currentWorldY = worldY;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;

        // Adjust player's worldX/Y for the attackArea
        switch (direction) {
            case "up" -> worldY -= attackArea.height;
            case "down" -> worldY += attackArea.height;
            case "left" -> worldX -= attackArea.width;
            case "right" -> worldX += attackArea.width;
        }
        // attackArea becomes solidArea;
        solidArea.width = attackArea.width;
        solidArea.height = attackArea.height;

        // Check monster collision with weaponArea
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monsters);

        //Restore original area
        worldX = currentWorldX;
        worldY = currentWorldY;
        solidArea.width = solidAreaWidth;
        solidArea.height = solidAreaHeight;

        return monsterIndex;
    }

    public void interactNPC (int i) {

        if(gp.keyH.enterPressed == true) {
            if (i != 999) {
                gp.gameState = gp.dialogueState;
                gp.npcs[i].speak();
                gp.playSE(gp.sound.talkSE);
            } else {
                if (stamina >= 2) {
                    attacking = true;
                    stamina -= 2;
                    gp.playSE(gp.sound.swordSlashSE);
                }
            }
        }
    }

    public void contactMonster(int i) {

        if (i != 999){

            if (invincible == false) {
                int damage; //statements below are for case when armour is bigger than AP
                if (defense >= attack) {damage = 0;}
                else {damage = gp.monsters[i].attack - defense;}
                life -= damage;
                gp.playSE(gp.sound.hurtSE);
                invincible = true;
            }
        }
    }

    public void damageMonster (int i) {

        if (i != 999) {

            if (gp.monsters[i].invincible == false) {
                gp.playSE(gp.monsters[i].hitSound);
                int damage; //statements below are for case when armour is bigger than AP
                if (gp.monsters[i].defense >= attack) {damage = 0;}
                else {damage = attack - gp.monsters[i].defense;}

                gp.monsters[i].life -= damage;
                gp.monsters[i].invincible = true;
                gp.monsters[i].damageReaction();

                if (gp.monsters[i].life <= 0) {
                    gp.monsters[i].dying = true;
                    gp.playSE(gp.sound.monsterDeath);
                }
            }
        } else {
            System.out.println("Miss!");
        }
    }

    public void resetSpeed() {
        speed = 3 + speedBoost;
        spriteSpeedModifier = 0;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
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

        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY,null);

        // Reset alpha

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // DEBUG
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible: " + invincibleCounter, 100, 100);
    }
}
