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

        int i = 0;
        gp.monsters[i] = new MON_BlueSlime(gp);
        gp.monsters[i].worldX = gp.tileSize * 21;
        gp.monsters[i].worldY = gp.tileSize * 38;
        i++;
        gp.monsters[i] = new MON_BlueSlime(gp);
        gp.monsters[i].worldX = gp.tileSize * 23;
        gp.monsters[i].worldY = gp.tileSize * 42;
        i++;
        gp.monsters[i] = new MON_BlueSlime(gp);
        gp.monsters[i].worldX = gp.tileSize * 24;
        gp.monsters[i].worldY = gp.tileSize * 37;
        i++;
        gp.monsters[i] = new MON_BlueSlime(gp);
        gp.monsters[i].worldX = gp.tileSize * 34;
        gp.monsters[i].worldY = gp.tileSize * 42;
        i++;
        gp.monsters[i] = new MON_BlueSlime(gp);
        gp.monsters[i].worldX = gp.tileSize * 38;
        gp.monsters[i].worldY = gp.tileSize * 42;
        i++;
        gp.monsters[i] = new MON_BlueSlime(gp);
        gp.monsters[i].worldX = gp.tileSize * 32;
        gp.monsters[i].worldY = gp.tileSize * 42;
        i++;
        gp.monsters[i] = new MON_BlueSlime(gp);
        gp.monsters[i].worldX = gp.tileSize * 33;
        gp.monsters[i].worldY = gp.tileSize * 39;
        i++;
        gp.monsters[i] = new MON_BlueSlime(gp);
        gp.monsters[i].worldX = gp.tileSize * 36;
        gp.monsters[i].worldY = gp.tileSize * 40;
        i++;
        gp.monsters[i] = new MON_BlueSlime(gp);
        gp.monsters[i].worldX = gp.tileSize * 37;
        gp.monsters[i].worldY = gp.tileSize * 41;
        i++;
        gp.monsters[i] = new MON_BlueSlime(gp);
        gp.monsters[i].worldX = gp.tileSize * 37;
        gp.monsters[i].worldY = gp.tileSize * 42;
        i++;
    }
}
