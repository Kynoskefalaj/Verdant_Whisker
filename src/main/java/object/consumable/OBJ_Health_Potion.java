package object.consumable;

import entity.Entity;
import entity.EntityType;
import root.GamePanel;

public class OBJ_Health_Potion extends Entity {

    GamePanel gp;
    int value = 5;

    public OBJ_Health_Potion(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = EntityType.CONSUMABLE;
        name = "Health Potion";

        description = "";
        image1 = setUp("/objects/consumables/Health_Potion", gp.tileSize, gp.tileSize);
        down1 = image1;

    }

    @Override
    public void use (Entity entity) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\n" +
                "Your life has been recovered by " + value + ".";
        entity.life += value;
        if(entity.life > entity.maxLife) {
            entity.life = entity.maxLife;
        }
        gp.playSE(gp.sound.powerUpSE);
    }
}
