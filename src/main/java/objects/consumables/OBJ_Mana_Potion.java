package objects.consumables;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Mana_Potion extends Entity {

    GamePanel gp;
    String recoveryType = "mana";

    public OBJ_Mana_Potion(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = EntityType.CONSUMABLE;
        name = "Health Potion";
        value = 5;

        description = String.format("[%s]\nRecovers %d %s.", name, value, recoveryType);
        image1 = setUp("/objects/consumables/Mana_Potion", gp.tileSize, gp.tileSize);
        down1 = image1;
    }

    @Override
    public void use (Entity entity) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\n" +
                "Your " + recoveryType + " has been recovered by " + value + ".";
        entity.mana += value;
        if(entity.mana > entity.maxMana) {
            entity.mana = entity.maxMana;
        }
        gp.playSE(gp.music.powerUpSE);
    }
}
