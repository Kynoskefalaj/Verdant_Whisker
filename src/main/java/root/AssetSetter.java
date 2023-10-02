package root;

import entities.Entity;
import entities.NPC_Merchant;
import entities.NPC_OldMan;
import monsters.MON_BlueSlime;
import monsters.MON_SkeletonMage;
import objects.OBJ_Bronze_Coin;
import objects.OBJ_Chest;
import objects.OBJ_Door;
import objects.OBJ_Key;
import objects.clothes.OBJ_Green_Hat;
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
import tile.big_obstacles.BO_BigHouse;
import tile.interactive.InteractiveTile;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        //Reset
        gp.objects = new Entity[gp.maxMap][50];

        //Put
        putObject(new OBJ_Bronze_Coin(gp),0, 25, 23);
        putObject(new OBJ_Bronze_Coin(gp),0, 21, 21);
        putObject(new OBJ_Bronze_Coin(gp),0, 22, 23);
        putObject(new OBJ_Sacred_Necklace(gp),0, 11, 8);
        putObject(new OBJ_Pouch(gp),0, 10, 32);
        putObject(new OBJ_Rope(gp),0,33, 20);
        putObject(new OBJ_Emerald_Scimitar(gp),0, 39, 8);
        putObject(new OBJ_Paladin_Shield(gp),0, 36, 8);
        putObject(new OBJ_Health_Potion(gp),0, 23, 7);
        putObject(new OBJ_Stamina_Potion(gp),0, 10, 29);
        putObject(new OBJ_StaminaWheel(gp),0, 10, 28);
        putObject(new OBJ_Mana_Potion(gp),0, 11, 34);
        putObject(new OBJ_Heart(gp),0, 22, 27);
        putObject(new OBJ_Mana_Crystal(gp),0, 22, 31);
        putObject(new OBJ_WoodcuttersAxe(gp),0,22, 7);

        putObject(new BO_BigHouse(gp),0,9, 34);
        gp.objects[0][15].worldY += 2;

        putObject(new OBJ_Door(gp),0,33, 7);
        putObject(new OBJ_Door(gp),0,14, 28);
        putObject(new OBJ_Door(gp),0,12, 12);

        putObject(new OBJ_Key(gp),0, 21, 22);

        putObject(new OBJ_Chest(gp, new OBJ_Green_Hat(gp)),0, 13, 31);
    }

    public void setMonster () {
        //Reset
        gp.monsters = new Entity[gp.maxMap][20];

        //Put
        putMonster(new MON_BlueSlime(gp),0, 21, 38);
        putMonster(new MON_BlueSlime(gp),0, 23, 42);
        putMonster(new MON_BlueSlime(gp),0, 24, 37);
        putMonster(new MON_BlueSlime(gp),0, 34, 42);
        putMonster(new MON_BlueSlime(gp),0, 38, 42);
        putMonster(new MON_SkeletonMage(gp),0, 37, 40);
    }

    public void setInteractiveTile () {
        putInteractiveTile(new IT_DryBush(gp),0, 27, 12);
        putInteractiveTile(new IT_DryBush(gp),0, 28, 12);
        putInteractiveTile(new IT_DryBush(gp),0, 29, 12);
        putInteractiveTile(new IT_DryBush(gp),0, 30, 12);
        putInteractiveTile(new IT_DryBush(gp),0, 31, 12);
        putInteractiveTile(new IT_DryBush(gp),0, 32, 12);
        putInteractiveTile(new IT_DryBush(gp),0, 33, 12);

        putInteractiveTile(new IT_DryBush(gp),0, 30, 21);
        putInteractiveTile(new IT_DryBush(gp),0, 31, 21);
        putInteractiveTile(new IT_DryBush(gp),0, 32, 21);
    }

    public void setBigObstacles () {

    }

    public void putBigObstacles () {

    }

    public void setNPC () {
        //Reset
        gp.npcs = new Entity[gp.maxMap][10];

        //Put
        putNPC(new NPC_OldMan(gp),0,21,21);
        putNPC(new NPC_Merchant(gp),1,12,7);
    }

    public void putObject(Entity object, int mapNum, int colX, int rowY) {

        int index = 0;
        for (int v = 0; v < gp.objects[1].length; v++) {
            if (gp.objects[mapNum][v] == null) {
                index = v;
                break;
            }
        }
        object.worldX = gp.tileSize * colX;
        object.worldY = gp.tileSize * rowY;
        gp.objects[mapNum][index] = object;
    }

    public void dropObject(Entity object, int worldX, int worldY) {

        int index = 0;
        for (int v = 0; v < gp.objects[1].length; v++) {
            if (gp.objects[gp.currentMap][v] == null) {
                index = v;
                break;
            }
        }
        object.worldX = worldX;
        object.worldY = worldY;
        gp.objects[gp.currentMap][index] = object;
    }

    public void putMonster(Entity monster, int mapNum, int colX, int rowY) {

        int index = 0;
        for (int v = 0; v < gp.monsters[1].length; v++) {
            if (gp.monsters[mapNum][v] == null) {
                index = v;
                break;
            }
        }
        monster.worldX = gp.tileSize * colX;
        monster.worldY = gp.tileSize * rowY;
        gp.monsters[mapNum][index] = monster;
    }

    public void putInteractiveTile(InteractiveTile interactiveTile, int mapNum, int colX, int rowY) {

        int index = 0;
        for (int i = 0; i < gp.iTile[1].length; i++) {
            if (gp.iTile[mapNum][i] == null) {
                index = i;
                break;
            }
        }
        interactiveTile.worldX = gp.tileSize * colX;
        interactiveTile.worldY = gp.tileSize * rowY;
        gp.iTile[mapNum][index] = interactiveTile;
    }

    public void putNPC(Entity npc, int mapNum, int colX, int rowY) {

        boolean firstlyAdded = true;
        npc.worldX = gp.tileSize * colX;
        npc.worldY = gp.tileSize * rowY;
        for (int i = 0; i < gp.npcs[1].length; i++) {
            if (gp.npcs[mapNum][i] == null && firstlyAdded) {
                gp.npcs[mapNum][i] = npc;
                firstlyAdded = false;
            }
        }
    }
}
