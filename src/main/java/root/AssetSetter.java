package root;

import entity.NPC_OldMan;
import object.OBJ_Door;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject () {

        gp.objects[0] = new OBJ_Door(gp);
        gp.objects[0].worldX = gp.tileSize * 21;
        gp.objects[0].worldY = gp.tileSize * 22;

        gp.objects[1] = new OBJ_Door(gp);
        gp.objects[1].worldX = gp.tileSize * 23;
        gp.objects[1].worldY = gp.tileSize * 25;


    }
    public void setNPC () {
        gp.npcs[0] = new NPC_OldMan(gp);
        gp.npcs[0].worldX = gp.tileSize*21;
        gp.npcs[0].worldY = gp.tileSize*21;
    }
}
