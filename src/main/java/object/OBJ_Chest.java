package object;

import entity.Entity;
import root.GamePanel;

public class OBJ_Chest extends Entity {

    public OBJ_Chest (GamePanel gp) {
        super(gp);
        name = "Chest";
        down1 = setUp("/objects/chest01");
    }
}