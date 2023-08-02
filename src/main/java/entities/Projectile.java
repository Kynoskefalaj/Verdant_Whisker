package entities;

import root.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Projectile extends Entity{

    Entity caster;

    protected BufferedImage up1, up2, up3, up4, up5, up6, up7, up8, up9, up10,
            up11, up12, up13, up14, up15, up16, up17, up18, up19, up20,
            up21, up22, up23, up24, up25, up26, up27, up28, up29, up30;

    protected BufferedImage down1, down2, down3, down4, down5, down6, down7, down8, down9, down10,
            down11, down12, down13, down14, down15, down16, down17, down18, down19, down20,
            down21, down22, down23, down24, down25, down26, down27, down28, down29, down30;

    protected BufferedImage left1, left2, left3, left4, left5, left6, left7, left8, left9, left10,
            left11, left12, left13, left14, left15, left16, left17, left18, left19, left20,
            left21, left22, left23, left24, left25, left26, left27, left28, left29, left30;

    protected BufferedImage right1, right2, right3, right4, right5, right6, right7, right8, right9, right10,
            right11, right12, right13, right14, right15, right16, right17, right18, right19, right20,
            right21, right22, right23, right24, right25, right26, right27, right28, right29, right30;

    public Projectile(GamePanel gp) {
        super(gp);

    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity caster) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.caster = caster;
        this.life = this.maxLife;

        solidArea = new Rectangle();
        solidArea.x = (int)(11 * gp.scale);
        solidArea.y = (int)(11 * gp.scale);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = (int)(10 * gp.scale);
        solidArea.height = (int)(10 * gp.scale);
    }

    public void update() {
        collisionOn = false;
        gp.cChecker.checkTile(this);

        if (caster == gp.player) {
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monsters);
            if (monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex, attack);
                gp.playSE(gp.sound.explosionSE);
                alive = false;
            }
        }
        if (caster != gp.player) {
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if(gp.player.invincible == false && contactPlayer == true) {
                attackPlayer(attack);
                alive = false;
            }
        }

        if (collisionOn == false) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }

        life--;

        if (life <= 0 || collisionOn) {
            alive = false;
        }

        spriteCounter++;
        if (spriteCounter > 1) {
            if (spriteNum < 30) {
                spriteNum++;
            }
            else if (spriteNum == 30) {
                spriteNum = 1;
            }
            spriteCounter = 0;
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
                    else if (spriteNum == 5) {image = up5;}
                    else if (spriteNum == 6) {image = up6;}
                    else if (spriteNum == 7) {image = up7;}
                    else if (spriteNum == 8) {image = up8;}
                    else if (spriteNum == 9) {image = up9;}
                    else if (spriteNum == 10) {image = up10;}
                    else if (spriteNum == 11) {image = up11;}
                    else if (spriteNum == 12) {image = up12;}
                    else if (spriteNum == 13) {image = up13;}
                    else if (spriteNum == 14) {image = up14;}
                    else if (spriteNum == 15) {image = up15;}
                    else if (spriteNum == 16) {image = up16;}
                    else if (spriteNum == 17) {image = up17;}
                    else if (spriteNum == 18) {image = up18;}
                    else if (spriteNum == 19) {image = up19;}
                    else if (spriteNum == 20) {image = up20;}
                    else if (spriteNum == 21) {image = up21;}
                    else if (spriteNum == 22) {image = up22;}
                    else if (spriteNum == 23) {image = up23;}
                    else if (spriteNum == 24) {image = up24;}
                    else if (spriteNum == 25) {image = up25;}
                    else if (spriteNum == 26) {image = up26;}
                    else if (spriteNum == 27) {image = up27;}
                    else if (spriteNum == 28) {image = up28;}
                    else if (spriteNum == 29) {image = up29;}
                    else if (spriteNum == 30) {image = up30;}
                    break;
                case "down":
                    if (spriteNum == 1) {image = down1;}
                    else if (spriteNum == 2) {image = down2;}
                    else if (spriteNum == 3) {image = down4;}
                    else if (spriteNum == 4) {image = down5;}
                    else if (spriteNum == 5) {image = down6;}
                    else if (spriteNum == 6) {image = down7;}
                    else if (spriteNum == 7) {image = down8;}
                    else if (spriteNum == 8) {image = down9;}
                    else if (spriteNum == 9) {image = down10;}
                    else if (spriteNum == 10) {image = down11;}
                    else if (spriteNum == 11) {image = down12;}
                    else if (spriteNum == 12) {image = down13;}
                    else if (spriteNum == 13) {image = down14;}
                    else if (spriteNum == 14) {image = down15;}
                    else if (spriteNum == 15) {image = down16;}
                    else if (spriteNum == 16) {image = down17;}
                    else if (spriteNum == 17) {image = down18;}
                    else if (spriteNum == 18) {image = down19;}
                    else if (spriteNum == 19) {image = down20;}
                    else if (spriteNum == 20) {image = down21;}
                    else if (spriteNum == 21) {image = down22;}
                    else if (spriteNum == 22) {image = down23;}
                    else if (spriteNum == 23) {image = down24;}
                    else if (spriteNum == 24) {image = down25;}
                    else if (spriteNum == 25) {image = down26;}
                    else if (spriteNum == 26) {image = down27;}
                    else if (spriteNum == 27) {image = down28;}
                    else if (spriteNum == 28) {image = down29;}
                    else if (spriteNum == 29) {image = down30;}
                    else if (spriteNum == 30) {image = down30;}
                    break;
                case "left":
                    if (spriteNum == 1) {image = left1;}
                    else if (spriteNum == 2) {image = left2;}
                    else if (spriteNum == 3) {image = left3;}
                    else if (spriteNum == 4) {image = left4;}
                    else if (spriteNum == 5) {image = left5;}
                    else if (spriteNum == 6) {image = left6;}
                    else if (spriteNum == 7) {image = left7;}
                    else if (spriteNum == 8) {image = left8;}
                    else if (spriteNum == 9) {image = left9;}
                    else if (spriteNum == 10) {image = left10;}
                    else if (spriteNum == 11) {image = left11;}
                    else if (spriteNum == 12) {image = left12;}
                    else if (spriteNum == 13) {image = left13;}
                    else if (spriteNum == 14) {image = left14;}
                    else if (spriteNum == 15) {image = left15;}
                    else if (spriteNum == 16) {image = left16;}
                    else if (spriteNum == 17) {image = left17;}
                    else if (spriteNum == 18) {image = left18;}
                    else if (spriteNum == 19) {image = left19;}
                    else if (spriteNum == 20) {image = left20;}
                    else if (spriteNum == 21) {image = left21;}
                    else if (spriteNum == 22) {image = left22;}
                    else if (spriteNum == 23) {image = left23;}
                    else if (spriteNum == 24) {image = left24;}
                    else if (spriteNum == 25) {image = left25;}
                    else if (spriteNum == 26) {image = left26;}
                    else if (spriteNum == 27) {image = left27;}
                    else if (spriteNum == 28) {image = left28;}
                    else if (spriteNum == 29) {image = left29;}
                    else if (spriteNum == 30) {image = left30;}
                    break;
                case "right":
                    if (spriteNum == 1) {image = right1;}
                    else if (spriteNum == 2) {image = right2;}
                    else if (spriteNum == 3) {image = right3;}
                    else if (spriteNum == 4) {image = right4;}
                    else if (spriteNum == 5) {image = right5;}
                    else if (spriteNum == 6) {image = right6;}
                    else if (spriteNum == 7) {image = right7;}
                    else if (spriteNum == 8) {image = right8;}
                    else if (spriteNum == 9) {image = right9;}
                    else if (spriteNum == 10) {image = right10;}
                    else if (spriteNum == 11) {image = right11;}
                    else if (spriteNum == 12) {image = right12;}
                    else if (spriteNum == 13) {image = right13;}
                    else if (spriteNum == 14) {image = right14;}
                    else if (spriteNum == 15) {image = right15;}
                    else if (spriteNum == 16) {image = right16;}
                    else if (spriteNum == 17) {image = right17;}
                    else if (spriteNum == 18) {image = right18;}
                    else if (spriteNum == 19) {image = right19;}
                    else if (spriteNum == 20) {image = right20;}
                    else if (spriteNum == 21) {image = right21;}
                    else if (spriteNum == 22) {image = right22;}
                    else if (spriteNum == 23) {image = right23;}
                    else if (spriteNum == 24) {image = right24;}
                    else if (spriteNum == 25) {image = right25;}
                    else if (spriteNum == 26) {image = right26;}
                    else if (spriteNum == 27) {image = right27;}
                    else if (spriteNum == 28) {image = right28;}
                    else if (spriteNum == 29) {image = right29;}
                    else if (spriteNum == 30) {image = right30;}
                    break;
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            changeAlpha(g2,1f);
        }
    }

    public boolean haveResource (Entity caster) {

        boolean haveResource = false;
        return haveResource;
    }

    public abstract void subtractResource (Entity caster);
}
