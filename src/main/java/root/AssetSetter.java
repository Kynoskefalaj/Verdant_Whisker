package root;

import entity.NPC_OldMan;
import monster.MON_BlueSlime;
import object.*;
import object.consumable.OBJ_Health_Potion;
import object.consumable.OBJ_Mana_Potion;
import object.consumable.OBJ_Stamina_Potion;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject () {

        int i = 0;
        gp.objects[i] = new OBJ_Key(gp);
        gp.objects[i].worldX = gp.tileSize * 25;
        gp.objects[i].worldY = gp.tileSize * 23;
        i++;
        gp.objects[i] = new OBJ_Sacred_Necklace(gp);
        gp.objects[i].worldX = gp.tileSize * 9;
        gp.objects[i].worldY = gp.tileSize * 7;
        i++;
        gp.objects[i] = new OBJ_Pouch(gp);
        gp.objects[i].worldX = gp.tileSize * 8;
        gp.objects[i].worldY = gp.tileSize * 32;
        i++;
        gp.objects[i] = new OBJ_Rope(gp);
        gp.objects[i].worldX = gp.tileSize * 32;
        gp.objects[i].worldY = gp.tileSize * 21;
        i++;
        gp.objects[i] = new OBJ_Emerald_Scimitar(gp);
        gp.objects[i].worldX = gp.tileSize * 39;
        gp.objects[i].worldY = gp.tileSize * 8;
        i++;

        gp.objects[i] = new OBJ_Paladin_Shield(gp);
        gp.objects[i].worldX = gp.tileSize * 36;
        gp.objects[i].worldY = gp.tileSize * 8;
        i++;

        gp.objects[i] = new OBJ_Health_Potion(gp);
        gp.objects[i].worldX = gp.tileSize * 23;
        gp.objects[i].worldY = gp.tileSize * 7;
        i++;

        gp.objects[i] = new OBJ_Stamina_Potion(gp);
        gp.objects[i].worldX = gp.tileSize * 10;
        gp.objects[i].worldY = gp.tileSize * 29;
        i++;

        gp.objects[i] = new OBJ_Mana_Potion(gp);
        gp.objects[i].worldX = gp.tileSize * 11;
        gp.objects[i].worldY = gp.tileSize * 34;
        i++;

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
