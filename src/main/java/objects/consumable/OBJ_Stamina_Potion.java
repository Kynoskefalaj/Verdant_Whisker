package objects.consumable;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Stamina_Potion extends Entity {

    GamePanel gp;
    int recoveryValue = 16;
    String recoveryType = "stamina";

    public OBJ_Stamina_Potion(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = EntityType.CONSUMABLE;
        name = "Stamina Potion";

        description = String.format("[%s]\nRecovers %d %s.", name, recoveryValue, recoveryType);
        image1 = setUp("/objects/consumables/Stamina_Potion", gp.tileSize, gp.tileSize);
        down1 = image1;

    }

    @Override
    public void use (Entity entity) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\n" +
                "Your " + recoveryType + " has been recovered by " + recoveryValue + ".";
        entity.stamina += recoveryValue;
        if(entity.stamina > entity.maxStamina) {
            entity.stamina = entity.maxStamina;
        }
        gp.playSE(gp.sound.powerUpSE);
    }
}
