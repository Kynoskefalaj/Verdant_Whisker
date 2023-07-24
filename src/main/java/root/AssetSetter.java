package root;

import entity.Entity;
import entity.NPC_OldMan;
import monster.MON_BlueSlime;
import monster.MON_SkeletonMage;
import object.*;
import object.consumable.OBJ_Health_Potion;
import object.consumable.OBJ_Mana_Potion;
import object.consumable.OBJ_Stamina_Potion;

public class AssetSetter {
    GamePanel gp;
    int i = 0;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {

        gp.objects[i] = putObject(new OBJ_Key(gp), 25, 23);
        gp.objects[i] = putObject(new OBJ_Sacred_Necklace(gp), 9, 7);
        gp.objects[i] = putObject(new OBJ_Pouch(gp), 8, 32);
        gp.objects[i] = putObject(new OBJ_Rope(gp),32, 21);
        gp.objects[i] = putObject(new OBJ_Emerald_Scimitar(gp), 39, 8);
        gp.objects[i] = putObject(new OBJ_Paladin_Shield(gp), 36, 8);
        gp.objects[i] = putObject(new OBJ_Health_Potion(gp), 23, 7);
        gp.objects[i] = putObject(new OBJ_Stamina_Potion(gp), 10, 29);
        gp.objects[i] = putObject(new OBJ_Mana_Potion(gp), 11, 34);
    }

    public Entity putObject(Entity object, int colX, int rowY) {
        object.worldX = gp.tileSize * colX;
        object.worldY = gp.tileSize * rowY;
        i++;
        return object;
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

        gp.monsters[i] = new MON_SkeletonMage(gp);
        gp.monsters[i].worldX = gp.tileSize * 37;
        gp.monsters[i].worldY = gp.tileSize * 40;
        i++;
    }
}
