package objects.consumable;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Health_Potion extends Entity {

    GamePanel gp;
    int recoveryValue = 5;
    String recoveryType = "health";

    public OBJ_Health_Potion(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = EntityType.CONSUMABLE;
        name = "Health Potion";

        description = String.format("[%s]\nRecovers %d %s.", name, recoveryValue, recoveryType);
        image1 = setUp("/objects/consumables/Health_Potion", gp.tileSize, gp.tileSize);
        down1 = image1;

    }

    @Override
    public void use (Entity entity) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\n" +
                "Your " + recoveryType + " has been recovered by " + recoveryValue + ".";
        entity.life += recoveryValue;
        if(entity.life > entity.maxLife) {
            entity.life = entity.maxLife;
        }
        gp.playSE(gp.sound.powerUpSE);
    }
}
