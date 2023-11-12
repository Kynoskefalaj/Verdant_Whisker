package root;

import entities.Entity;

public class EventHandler{

    GamePanel gp;
    EventRect eventRect[][][];
    Entity eventMaster;

    int prevEventX, prevEventY;
    boolean canTouchEvent = true;

    int tempMap, tempCol, tempRow;

    public EventHandler (GamePanel gp) {
        this.gp = gp;

        eventMaster = new Entity(gp);

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;

        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {

            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = gp.tileSize / 2 - 8;
            eventRect[map][col][row].y = gp.tileSize / 2 - 8;
            eventRect[map][col][row].width = 16;
            eventRect[map][col][row].height = 16;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }

        setDialogue();
    }

    public void setDialogue() {

        eventMaster.dialogues[0][0] = "You fall into a pit!";
        eventMaster.dialogues[1][0] = "You drink the water. \n Your health and mana have been recovered.\n" +
                "The progress has been saved.";
        eventMaster.dialogues[2][0] = "Teleport!";
    }

    public void checkEvent () {

        // Check if the player is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - prevEventX);
        int yDistance = Math.abs(gp.player.worldY - prevEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if(canTouchEvent == true) {
            if (hit(0,27, 16, "right")) {damagePit(gp.dialogueState);}
            else if (hit(0,23, 19, "any")) {damagePit(gp.dialogueState);}
            else if (hit(0,23, 12, "up")) {healingPool(gp.dialogueState);}
            else if (hit(0,20, 12, "left")) {teleport(gp.dialogueState, 37, 10);}
            else if (hit(0,10,39,"any")) {transition(1, 11, 13);}
            else if (hit(1,11,13,"any")) {transition(0, 10, 39);}
            else if (hit(1, 12, 9, "up")) {speak(gp.npcs[1][0]);}
        }

    }

    public boolean hit (int map, int col, int row, String reqDirection) {

        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x += gp.player.worldX;
            gp.player.solidArea.y += gp.player.worldY;
            eventRect[map][col][row].x += col * gp.tileSize;
            eventRect[map][col][row].y += row * gp.tileSize;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) &&
                    eventRect[map][col][row].eventDone == false) { // contentEquals(String) is used to compare two Strings values
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    prevEventX = gp.player.worldX;
                    prevEventY = gp.player.worldY;
                }
            }

            // RESET after checking
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void damagePit (int gameState) {
        gp.gameState = gameState;
        gp.playSE(gp.se.exhaustedSE);
        eventMaster.startDialogue(eventMaster, 0);
        gp.player.life--;
//        eventRect[col][row].eventDone = true; //then this event will occur one time
        canTouchEvent = false;
    }

    public void healingPool (int gameState) {

        if (gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.player.attackCancelled = true;
            gp.playSE(gp.se.successSE);
            eventMaster.startDialogue(eventMaster, 1);
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.asSetter.setMonster();
            gp.saveLoad.save();
        }
    }

    public void teleport (int gameState, int destCol, int destRow) {
        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster, 2);
        gp.player.worldX = gp.tileSize * destCol;
        gp.player.worldY = gp.tileSize * destRow;
    }

    public void transition (int map, int col, int row) {

        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;

        canTouchEvent = false;

        gp.playSE(gp.se.stairsSE);
    }

    public void speak(Entity entity) {

        if(gp.keyH.enterPressed) {
            gp.gameState = gp.dialogueState;
            gp.player.attackCancelled = true;
            entity.speak();
        }
    }
}
