package entities;

import objects.consumables.OBJ_Health_Potion;
import objects.consumables.OBJ_Mana_Potion;
import objects.consumables.OBJ_Stamina_Potion;
import root.GamePanel;

public class NPC_Merchant extends Entity{

    public NPC_Merchant (GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        name = "Merchant";

        getImage();
        setDialogue();
        setItems();

        solidArea.x = (int)(10 * gp.scale);
        solidArea.y = (int)(16 * gp.scale);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = (int)(12 * gp.scale);
        solidArea.height = (int)(14 * gp.scale);

        type = EntityType.NPC;
    }

    public void getImage() {
        image1 = setUp("/npc/merchant_down_1", gp.tileSize*3/4, gp.tileSize*3/4);
        image2 = setUp("/npc/merchant_down_2", gp.tileSize*3/4, gp.tileSize*3/4);

        up1 = image1;
        up2 = image2;
        up3 = image1; 
        up4 = image2;
        down1 = image1;
        down2 = image2;
        down3 = image1;
        down4 = image2;
        left1 = image1;
        left2 = image2;
        left3 = image1;
        left4 = image2;
        right1 = image1;
        right2 = image2;
        right3 = image1;
        right4 = image2;
    }

    public void setDialogue() {

        dialogues[0] =
                "Greetings fella!\n" +
                        "I have some valuable items.\n" +
                        "Wanna trade?";
    }

    public void setItems() {
        inventory.add(new OBJ_Health_Potion(gp));
        inventory.add(new OBJ_Health_Potion(gp));
        inventory.add(new OBJ_Health_Potion(gp));
        inventory.add(new OBJ_Health_Potion(gp));
        inventory.add(new OBJ_Mana_Potion(gp));
        inventory.add(new OBJ_Mana_Potion(gp));
        inventory.add(new OBJ_Mana_Potion(gp));
        inventory.add(new OBJ_Mana_Potion(gp));
        inventory.add(new OBJ_Mana_Potion(gp));
        inventory.add(new OBJ_Stamina_Potion(gp));
        inventory.add(new OBJ_Stamina_Potion(gp));
    }

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

}
