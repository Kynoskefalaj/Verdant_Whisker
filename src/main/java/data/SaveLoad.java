package data;

import entities.Entity;
import objects.*;
import objects.clothes.OBJ_Boots;
import objects.consumables.OBJ_Health_Potion;
import objects.consumables.OBJ_Mana_Potion;
import objects.consumables.OBJ_Stamina_Potion;
import objects.shields.OBJ_Paladin_Shield;
import objects.shields.OBJ_Wooden_Shield;
import objects.tools.OBJ_Pouch;
import objects.weapons.OBJ_Emerald_Scimitar;
import objects.weapons.OBJ_WoodcuttersAxe;
import objects.weapons.OBJ_Worn_Needle;
import root.GamePanel;

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
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
