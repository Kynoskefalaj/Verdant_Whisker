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
import objects.weapons.OBJ_WoodcuttersAxe;
import tile.interactive.IT_DryBush;
import tile.interactive.InteractiveTile;

public class AssetSetter {
    GamePanel gp;
    int j = 0;

    int k = 0;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        //Reset
        gp.objects = new Entity[50];

        //Put
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
        putObject(new OBJ_WoodcuttersAxe(gp), 22, 7);
    }

    public void setMonster () {
        //Reset
        gp.monsters = new Entity[20];
        //Put
        putMonster(new MON_BlueSlime(gp), 21, 38);
        putMonster(new MON_BlueSlime(gp), 23, 42);
        putMonster(new MON_BlueSlime(gp), 24, 37);
        putMonster(new MON_BlueSlime(gp), 34, 42);
        putMonster(new MON_BlueSlime(gp), 38, 42);
        putMonster(new MON_SkeletonMage(gp), 37, 40);
    }

    public void setNPC () {
        //Reset
        gp.npcs = new Entity[10];
        //Put
        putNPC(new NPC_OldMan(gp), 21, 21);
    }

    public void putObject(Entity object, int colX, int rowY) {
        int index = 0;
        for (int v = 0; v < gp.objects.length; v++) {
            if (gp.objects[v] == null) {
                index = v;
                break;
            }
        }
        object.worldX = gp.tileSize * colX;
        object.worldY = gp.tileSize * rowY;
        gp.objects[index] = object;
    }

    public void dropObject(Entity object, int worldX, int worldY) {
        int index = 0;
        for (int v = 0; v < gp.objects.length; v++) {
            if (gp.objects[v] == null) {
                index = v;
                break;
            }
        }
        object.worldX = worldX;
        object.worldY = worldY;
        gp.objects[index] = object;
    }

    public void putMonster(Entity monster, int colX, int rowY) {
        int index = 0;
        for (int v = 0; v < gp.monsters.length; v++) {
            if (gp.monsters[v] == null) {
                index = v;
                break;
            }
        }
        monster.worldX = gp.tileSize * colX;
        monster.worldY = gp.tileSize * rowY;
        gp.monsters[index] = monster;
    }

    public void putNPC(Entity npc, int colX, int rowY) {
        npc.worldX = gp.tileSize * colX;
        npc.worldY = gp.tileSize * rowY;
        gp.npcs[k] = npc;
        k++;
    }

    public void setInteractiveTile () {
        putInteractiveTile(new IT_DryBush(gp), 27, 7);
        putInteractiveTile(new IT_DryBush(gp), 28, 7);
        putInteractiveTile(new IT_DryBush(gp), 29, 7);
        putInteractiveTile(new IT_DryBush(gp), 30, 7);
        putInteractiveTile(new IT_DryBush(gp), 31, 7);
        putInteractiveTile(new IT_DryBush(gp), 32, 7);
        putInteractiveTile(new IT_DryBush(gp), 33, 7);
    }

    public void putInteractiveTile(InteractiveTile interactiveTile, int colX, int rowY) {
        int index = 0;
        for (int i = 0; i < gp.monsters.length; i++) {
            if (gp.iTile[i] == null) {
                index = i;
                break;
            }
        }
        interactiveTile.worldX = gp.tileSize * colX;
        interactiveTile.worldY = gp.tileSize * rowY;
        gp.iTile[index] = interactiveTile;
    }
}
