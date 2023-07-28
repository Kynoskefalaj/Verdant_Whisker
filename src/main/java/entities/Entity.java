package entities;

import root.GamePanel;
import root.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

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

    // COUNTER
    public int spriteCounter = 0;
    public int attackSpriteCounter = 0;
    public int actionLockCounter = 0;
    public int shotAvailableCounter = 0;
    public int invincibleCounter;
    public int dyingCounter = 0;
    public int hpBarCounter= 0;

    //CHARACTER ATTRIBUTES
    public String name;
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
    public Projectile projectile;

//    ITEM ATTRIBUTES
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;

    // TYPE
public EntityType type;

    public Entity (GamePanel gp) {
        this.gp = gp;
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
    }

    public void setAction () { }

    public void damageReaction () {};

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

    public void use (Entity entity) {}

    public void update () {
//        System.out.println("SpriteCounter: " + spriteCounter);

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

    public void attackPlayer (int attack) {
        if(gp.player.invincible == false) {
            int damage; //statements below are for case when armour is bigger than AP
            if (gp.player.defense >= attack) {damage = 0;}
            else {damage = attack - gp.player.defense;}
            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }

    public void draw (Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {image = up1;}
                    else if (spriteNum == 2) {image = up2;}
                    else if (spriteNum == 3) {image = up3;}
                    else if (spriteNum == 4) {image = up4;}
                    break;
                case "down":
                    if (spriteNum == 1) {image = down1;}
                    else if (spriteNum == 2) {image = down2;}
                    else if (spriteNum == 3) {image = down3;}
                    else if (spriteNum == 4) {image = down4;}
                    break;
                case "left":
                    if (spriteNum == 1) {image = left1;}
                    else if (spriteNum == 2) {image = left2;}
                    else if (spriteNum == 3) {image = left3;}
                    else if (spriteNum == 4) {image = left4;}
                    break;
                case "right":
                    if (spriteNum == 1) {image = right1;}
                    else if (spriteNum == 2) {image = right2;}
                    else if (spriteNum == 3) {image = right3;}
                    else if (spriteNum == 4) {image = right4;}
                    break;
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
}
