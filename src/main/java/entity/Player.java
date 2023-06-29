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

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 6;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_front.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_front_step.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_rear.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/player/VW_rear_step.png")));
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

        if(keyH.upPressed == true || keyH.downPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true) {

//            if statements provide possibility to move diagonally
//            else if statements would provide moving in grid
            if (keyH.upPressed) {
                direction = "up";
                worldY -= speed;
            } if (keyH.downPressed) {
                direction = "down";
                worldY += speed;
            } if (keyH.leftPressed) {
                direction = "left";
                worldX -= speed;
            } if (keyH.rightPressed) {
                direction = "right";
                worldX += speed;
            }
            spriteCounter++;
            if (spriteCounter > 8) {
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
                    image = up1;
                } else if (spriteNum == 4) {
                    image = up2;
                } break;
            case "down" :
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                } else if (spriteNum == 3) {
                    image = down1;
                } else if (spriteNum == 4) {
                    image = down2;
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
