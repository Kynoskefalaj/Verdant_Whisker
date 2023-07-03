package entity;
import root.GamePanel;
import root.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    int speedBoost = 0;

    int spriteSpeedModifier;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

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
        speed = 5;
        direction = "down";
        spriteSpeedModifier = 0;
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_up.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_up2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_up3.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_up4.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_down.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_down2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_down3.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_down4.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_left.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_left2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_left3.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_left4.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_right.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_right2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_right3.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_right4.png")));

        } catch(IOException e) {
            e.printStackTrace();
        }
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
                speed = 9;
                spriteSpeedModifier = 4;
            }

//            CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

//            CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

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

            String objectName = gp.obj[i].name;

            switch (objectName) {
            case "Key" :
                hasKey++;
                gp.obj[i] = null;
                System.out.println("Key: " + hasKey);
                break;
            case "Door" :
                if(hasKey > 0) {
                    gp.obj[i] = null;
                    hasKey--;
                }
                System.out.println("Key: " + hasKey);
                break;
            case "Boots" :
                speedBoost += 1;
                gp.obj[i] = null;
                break;
            }

        }
    }


    public void resetSpeed() {
        speed = 5 + speedBoost;
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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
