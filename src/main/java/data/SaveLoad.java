package data;

import entities.Entity;
import objects.*;
import objects.clothes.OBJ_Boots;
import objects.clothes.OBJ_Green_Hat;
import objects.clothes.OBJ_Green_Tunic;
import objects.consumables.OBJ_Health_Potion;
import objects.consumables.OBJ_Mana_Potion;
import objects.consumables.OBJ_Stamina_Potion;
import objects.hud.OBJ_Heart;
import objects.hud.OBJ_Mana_Crystal;
import objects.hud.OBJ_StaminaWheel;
import objects.jewellery.OBJ_Sacred_Necklace;
import objects.shields.OBJ_Paladin_Shield;
import objects.shields.OBJ_Wooden_Shield;
import objects.tools.OBJ_Leather_Backpack;
import objects.tools.OBJ_Pouch;
import objects.tools.OBJ_Rope;
import objects.weapons.OBJ_Emerald_Scimitar;
import objects.weapons.OBJ_WoodcuttersAxe;
import objects.weapons.OBJ_Worn_Needle;
import root.GamePanel;
import tile.big_obstacles.BO_BigHouse;

import java.io.*;

public class SaveLoad {

    GamePanel gp;

    public SaveLoad (GamePanel gp){
        this.gp = gp;
    }

    public Entity getObject(String itemName){

        Entity obj = null;

        switch (itemName) {
            case "Woodcutter's Axe": obj = new OBJ_WoodcuttersAxe(gp); break;
            case "Boots": obj = new OBJ_Boots(gp); break;
            case "Key": obj = new OBJ_Key(gp); break;
            case "Pouch": obj = new OBJ_Pouch(gp); break;
            case "Lantern": obj = new OBJ_Lantern(gp); break;
            case "Health Potion": obj = new OBJ_Health_Potion(gp); break;
            case "Mana Potion": obj = new OBJ_Mana_Potion(gp); break;
            case "Stamina Potion": obj = new OBJ_Stamina_Potion(gp); break;
            case "Wooden Shield": obj = new OBJ_Wooden_Shield(gp); break;
            case "Paladin Shield": obj = new OBJ_Paladin_Shield(gp); break;
            case "Worn Needle": obj = new OBJ_Worn_Needle(gp); break;
            case "Emerald Scimitar": obj = new OBJ_Emerald_Scimitar(gp); break;
            case "Tent": obj = new OBJ_Tent(gp); break;
            case "Door": obj = new OBJ_Door(gp); break;
            case "Chest": obj = new OBJ_Chest(gp); break;
            case "Green Tunic": obj = new OBJ_Green_Tunic(gp); break;
            case "Sacred Necklace": obj = new OBJ_Sacred_Necklace(gp); break;
            case "Leather Backpack": obj = new OBJ_Leather_Backpack(gp); break;
            case "Rope": obj = new OBJ_Rope(gp); break;
            case "Green Hat": obj = new OBJ_Green_Hat(gp); break;
            case "Bronze Coin": obj = new OBJ_Bronze_Coin(gp); break;
            case "Heart": obj = new OBJ_Heart(gp); break;
            case "Mana Crystal": obj = new OBJ_Mana_Crystal(gp); break;
            case "StaminaWheel": obj = new OBJ_StaminaWheel(gp); break;
            case "Big House": obj = new BO_BigHouse(gp); break;

        }
        return obj;
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxMana = gp.player.maxMana;
            ds.mana = gp.player.mana;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;

            // Player inventory
            for(int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amount);
            }
            // Player equipment
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldSlot();

            // Objects on map
            ds.mapObjectsNames = new String[gp.maxMap][gp.objects[1].length];
            ds.mapObjectWorldX = new int [gp.maxMap][gp.objects[1].length];
            ds.mapObjectWorldY = new int [gp.maxMap][gp.objects[1].length];
            ds.mapObjectLootNames = new String[gp.maxMap][gp.objects[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.objects[1].length];

            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {

                for (int i = 0; i < gp.objects[1].length; i++) {
                    if (gp.objects[mapNum][i] == null) {
                        ds.mapObjectsNames[mapNum][i] = "NA";
                    }
                    else {
                        ds.mapObjectsNames[mapNum][i] = gp.objects[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.objects[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.objects[mapNum][i].worldY;

                        if(gp.objects[mapNum][i].loot != null) {
                            ds.mapObjectLootNames[mapNum][i] = gp.objects[mapNum][i].loot.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.objects[mapNum][i].opened;
                    }
                }
            }

            //Write the DataStorage object
            oos.writeObject(ds);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            // Read the DataStorage object
            DataStorage ds = (DataStorage)ois.readObject();

            // Player stats
            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxMana = ds.maxMana;
            gp.player.mana = ds.mana;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.coin = ds.coin;

            // Player inventory
            gp.player.inventory.clear();
            for(int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory. add(getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
            }
            // Player equipment
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getAttackImage();

            // Objects on map
            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {

                for(int i = 0; i < gp.objects[1].length; i++) {

                    if (ds.mapObjectsNames[mapNum][i].equals("NA")) {
                        gp.objects[mapNum][i] = null;
                    }
                    else {
                        gp.objects[mapNum][i] = getObject(ds.mapObjectsNames[mapNum][i]);
                        gp.objects[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.objects[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        if (ds.mapObjectLootNames[mapNum][i] != null) {
                            gp.objects[mapNum][i].loot = getObject(ds.mapObjectLootNames[mapNum][i]);
                        }
                        gp.objects[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if (gp.objects[mapNum][i].opened == true) {
                            gp.objects[mapNum][i].down1 = gp.objects[mapNum][i].image2;
                        }
                    }
                }
            }
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
