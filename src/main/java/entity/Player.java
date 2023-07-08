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

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
        spriteSpeedModifier = 0;
    }

    public void getPlayerImage() {
            up1 = setUp("/player/VW_up");
            up2 = setUp("/player/VW_up2");
            up3 = setUp("/player/VW_up3");
            up4 = setUp("/player/VW_up4");
            down1 = setUp("/player/VW_down");
            down2 = setUp("/player/VW_down2");
            down3 = setUp("/player/VW_down3");
            down4 = setUp("/player/VW_down4");
            left1 = setUp("/player/VW_left");
            left2 = setUp("/player/VW_left2");
            left3 = setUp("/player/VW_left3");
            left4 = setUp("/player/VW_left4");
            right1 = setUp("/player/VW_right");
            right2 = setUp("/player/VW_right2");
            right3 = setUp("/player/VW_right3");
            right4 = setUp("/player/VW_right4");
    }

    public void update() {
        resetSpeed();

        if(keyH.upPressed == true || keyH.downPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true) {

//            if statements provide possibility to move diagonally
//            else if statements would provide moving in grid
            if (keyH.upPressed) {
                direction = "up";
            } if (keyH.downPressed) {
                direction = "down";
            } if (keyH.leftPressed) {
                direction = "left";
            } if (keyH.rightPressed) {
                direction = "right";
            } if (keyH.spacePressed) {
                speed = 6;
                spriteSpeedModifier = 4;
            }

//            CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

//            CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

//            CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc); //Checks if any npc from array collided with player
            interactNPC(npcIndex);
//            IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false) {

                switch (direction) {
                    case "up" -> worldY -= speed + speedBoost;
                    case "down" -> worldY += speed + speedBoost;
                    case "left" -> worldX -= speed + speedBoost;
                    case "right" -> worldX += speed + speedBoost;
                }
            }

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
    }

    public void pickUpObject(int i) {

        if (i != 999) {
        }
    }

    public void interactNPC (int i) {

        if (i != 999) {
            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyH.enterPressed = false;
    }


    public void resetSpeed() {
        speed = 4 + speedBoost;
        spriteSpeedModifier = 0;
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up" :
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                } else if (spriteNum == 3) {
                    image = up3;
                } else if (spriteNum == 4) {
                    image = up4;
                } break;
            case "down" :
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                } else if (spriteNum == 3) {
                    image = down3;
                } else if (spriteNum == 4) {
                    image = down4;
                } break;
            case "left" :
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                } else if (spriteNum == 3) {
                    image = left3;
                } else if (spriteNum == 4) {
                    image = left4;
                } break;
            case "right" :
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                } else if (spriteNum == 3) {
                    image = right3;
                } else if (spriteNum == 4) {
                    image = right4;
                } break;
        }

        g2.drawImage(image, screenX, screenY,null);
    }
}
