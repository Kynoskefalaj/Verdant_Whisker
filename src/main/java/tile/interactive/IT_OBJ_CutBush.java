package tile.interactive;

import entities.EntityType;
import root.GamePanel;

public class IT_OBJ_CutBush extends InteractiveTile{

    GamePanel gp;

    public IT_OBJ_CutBush(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = EntityType.NOT_PICKABLE;
        name = "Cut Bush";
        image1 = setUp("/objects/Cut_Bush", gp.tileSize, gp.tileSize);
        down1 = image1;
    }
}
