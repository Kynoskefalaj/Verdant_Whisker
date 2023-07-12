package root;

import entity.NPC_OldMan;
import monster.MON_BlueSlime;
import object.OBJ_Door;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject () {

    }
    public void setNPC () {
        gp.npcs[0] = new NPC_OldMan(gp);
        gp.npcs[0].worldX = gp.tileSize*21;
        gp.npcs[0].worldY = gp.tileSize*21;
    }

    public void setMonster () {
        gp.monsters[0] = new MON_BlueSlime(gp);
        gp.monsters[0].worldX = gp.tileSize * 23;
        gp.monsters[0].worldY = gp.tileSize * 36;

        gp.monsters[1] = new MON_BlueSlime(gp);
        gp.monsters[1].worldX = gp.tileSize * 23;
        gp.monsters[1].worldY = gp.tileSize * 37;

    }
}
