package root;

import entities.Entity;
import entities.NPC_OldMan;
import monsters.MON_BlueSlime;
import monsters.MON_SkeletonMage;
import objects.OBJ_Bronze_Coin;
import objects.consumables.OBJ_Health_Potion;
import objects.consumables.OBJ_Mana_Potion;
import objects.consumables.OBJ_Stamina_Potion;
import objects.hud.OBJ_Heart;
import objects.hud.OBJ_Mana_Crystal;
import objects.hud.OBJ_StaminaWheel;
import objects.jewellery.OBJ_Sacred_Necklace;
import objects.shields.OBJ_Paladin_Shield;
import objects.tools.OBJ_Pouch;
import objects.tools.OBJ_Rope;
import objects.weapons.OBJ_Emerald_Scimitar;

public class AssetSetter {
    GamePanel gp;
    int i = 0;
    int j = 0;

    int k = 0;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {

        putObject(new OBJ_Bronze_Coin(gp), 25, 23);
        putObject(new OBJ_Bronze_Coin(gp), 21, 21);
        putObject(new OBJ_Bronze_Coin(gp), 22, 23);
        putObject(new OBJ_Sacred_Necklace(gp), 9, 7);
        putObject(new OBJ_Pouch(gp), 8, 32);
        putObject(new OBJ_Rope(gp),32, 21);
        putObject(new OBJ_Emerald_Scimitar(gp), 39, 8);
        putObject(new OBJ_Paladin_Shield(gp), 36, 8);
        putObject(new OBJ_Health_Potion(gp), 23, 7);
        putObject(new OBJ_Stamina_Potion(gp), 10, 29);
        putObject(new OBJ_StaminaWheel(gp), 10, 28);
        putObject(new OBJ_Mana_Potion(gp), 11, 34);
        putObject(new OBJ_Heart(gp), 22, 27);
        putObject(new OBJ_Mana_Crystal(gp), 22, 31);
    }

    public void setMonster () {
        putMonster(new MON_BlueSlime(gp), 21, 38);
        putMonster(new MON_BlueSlime(gp), 23, 42);
        putMonster(new MON_BlueSlime(gp), 24, 37);
        putMonster(new MON_BlueSlime(gp), 34, 42);
        putMonster(new MON_BlueSlime(gp), 38, 42);
        putMonster(new MON_SkeletonMage(gp), 37, 40);
    }

    public void setNPC () {
        putNPC(new NPC_OldMan(gp), 21, 21);
    }

    public void putObject(Entity object, int colX, int rowY) {
        object.worldX = gp.tileSize * colX;
        object.worldY = gp.tileSize * rowY;
        gp.objects[i] = object;
        i++;
    }

    public void dropObject(Entity object, int worldX, int worldY) {
        object.worldX = worldX;
        object.worldY = worldY;
        gp.objects[i] = object;
        i++;
    }

    public void putMonster(Entity monster, int colX, int rowY) {
        monster.worldX = gp.tileSize * colX;
        monster.worldY = gp.tileSize * rowY;
        gp.monsters[j] = monster;
        j++;
    }

    public void putNPC(Entity npc, int colX, int rowY) {
        npc.worldX = gp.tileSize * colX;
        npc.worldY = gp.tileSize * rowY;
        gp.npcs[k] = npc;
        k++;
    }
}
