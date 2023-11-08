package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {

    // Player stats
    int level;
    int maxLife;
    int life;
    int maxMana;
    int mana;
    int strength;
    int dexterity;
    int exp;
    int nextLevelExp;
    int coin;

    //Player Inventory
    ArrayList<String > itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();
    int currentWeaponSlot;
    int currentShieldSlot;

    //Objects on map
    String mapObjectsNames[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];
    String mapObjectLootNames[][];
    boolean mapObjectOpened[][];
}
