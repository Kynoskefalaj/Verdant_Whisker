package objects.consumables;

import entities.Entity;
import entities.EntityType;
import root.GamePanel;

public class OBJ_Stamina_Potion extends Entity {

    GamePanel gp;
    String recoveryType = "stamina";

    public OBJ_Stamina_Potion(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = EntityType.CONSUMABLE;
        name = "Stamina Potion";
        value = 16;
        price = 4;
        stackable = true;

        description = String.format("[%s]\nRecovers %d %s.", name, value, recoveryType);
        image1 = setUp("/objects/consumables/Stamina_Potion", gp.tileSize, gp.tileSize);
        down1 = image1;

        setDialogue();
    }

    public void setDialogue() {

        dialogues[0][0] = "You drink the " + name + "!\n" +
                "Your " + recoveryType + " has been recovered by " + value + ".";
    }

    @Override
    public boolean use (Entity entity) {

        startDialogue(this, 0);
        entity.stamina += value;
        if(entity.stamina > entity.maxStamina) {
            entity.stamina = entity.maxStamina;
        }
        gp.playSE(gp.music.powerUpSE);
        return true;
    }
}
