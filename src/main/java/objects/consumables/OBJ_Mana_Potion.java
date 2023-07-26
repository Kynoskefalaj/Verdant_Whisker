package objects.consumables;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Mana_Potion extends Entity {

    GamePanel gp;
    int recoveryValue = 5;
    String recoveryType = "mana";

    public OBJ_Mana_Potion(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = EntityType.CONSUMABLE;
        name = "Health Potion";

        description = String.format("[%s]\nRecovers %d %s.", name, recoveryValue, recoveryType);
        image1 = setUp("/objects/consumables/Mana_Potion", gp.tileSize, gp.tileSize);
        down1 = image1;
    }

    @Override
    public void use (Entity entity) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\n" +
                "Your " + recoveryType + " has been recovered by " + recoveryValue + ".";
        entity.mana += recoveryValue;
        if(entity.mana > entity.maxMana) {
            entity.mana = entity.maxMana;
        }
        gp.playSE(gp.sound.powerUpSE);
    }
}
