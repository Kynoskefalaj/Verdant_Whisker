package entities;
import objects.clothes.OBJ_Boots;
import objects.clothes.OBJ_Green_Hat;
import objects.clothes.OBJ_Green_Tunic;
import objects.jewellery.OBJ_Sacred_Necklace;
import objects.projectiles.OBJ_ArcaneMissile;
import objects.shields.OBJ_Wooden_Shield;
import objects.OBJ_Key;
import objects.tools.OBJ_Leather_Backpack;
import objects.tools.OBJ_Pouch;
import objects.tools.OBJ_Rope;
import objects.weapons.OBJ_Worn_Needle;
import root.GamePanel;
import root.KeyHandler;
import root.UtilityTool;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity implements Archery {

    KeyHandler keyH;
    UtilityTool uTool;

    public final int screenX;
    public final int screenY;
    int speedBoost = 0;
    public boolean attackCancelled = false;
    public boolean lightUpdated = false;

    int spriteSpeedModifier;
    int notificationCounter = 0;

    public Entity[] equipment = new Entity[9];


    public Player(GamePanel gp, KeyHandler keyH){
        super(gp); // CALLING THE SUPER CLASS CONSTRUCTOR

        this.keyH = keyH;
        this.uTool = new UtilityTool();

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        // SOLID AREA
        solidArea = new Rectangle();
        solidArea.x = (int)(10 * gp.scale);
        solidArea.y = (int)(16 * gp.scale);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = (int)(12 * gp.scale);
        solidArea.height = (int)(14 * gp.scale);

        type = EntityType.PLAYER;

        setDefaultValues();
        getImage();
        getAttackImage();
        getGuardImage();
        setItems();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        defaultSpeed = 3;
        speed = defaultSpeed;
        direction = "down";
        spriteSpeedModifier = 0;

        // PLAYER STATUS
        level = 1;
        maxLife = 6;
        maxStamina = 16;
        maxMana = 4;
        defense = 2;
        agility = 1;
        strength = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 40;
        currentWeapon = new OBJ_Worn_Needle(gp);
        currentShield = new OBJ_Wooden_Shield(gp);
        projectile = new OBJ_ArcaneMissile(gp);
        life = maxLife;
        stamina = maxStamina;
        mana = maxMana;
        attack = getAttack();
        defense = getDefense();
        attackSpeed = agility * 6;

        ammo = 10;
    }

    public void setDefaultPosition() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = "down";
    }

    public void restoreLifeAndMana() {
        life = maxLife;
        mana = maxMana;
        stamina = maxStamina;
        invincible = false;
    }


    public void setItems () {
        // RESET
        inventory.clear();

        // EQUIPMENT
        equipment[0] = new OBJ_Sacred_Necklace(gp);
        equipment[1] = new OBJ_Green_Hat(gp);
        equipment[2] = new OBJ_Leather_Backpack(gp);
        equipment[3] = currentShield;
        equipment[4] = new OBJ_Green_Tunic(gp);
        equipment[5] = currentWeapon;
        equipment[6] = new OBJ_Rope(gp);
        equipment[7] = new OBJ_Boots(gp);
        equipment[8] = new OBJ_Pouch(gp);

        // INVENTORY
        inventory.add(currentShield);
        inventory.add(currentWeapon);
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Boots(gp));
        inventory.add(new OBJ_Boots(gp));
        inventory.add(new OBJ_Pouch(gp));
        inventory.add(new OBJ_Pouch(gp));
        inventory.add(new OBJ_Pouch(gp));
    }
    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense() {
        return defense = agility * currentShield.defenseValue;
    }
    public void getImage() {
            up1 = setUp("/player/walk/VW_up", gp.tileSize, gp.tileSize);
            up2 = setUp("/player/walk/VW_up2", gp.tileSize, gp.tileSize);
            up3 = setUp("/player/walk/VW_up3", gp.tileSize, gp.tileSize);
            up4 = setUp("/player/walk/VW_up4", gp.tileSize, gp.tileSize);
            down1 = setUp("/player/walk/VW_down", gp.tileSize, gp.tileSize);
            down2 = setUp("/player/walk/VW_down2", gp.tileSize, gp.tileSize);
            down3 = setUp("/player/walk/VW_down3", gp.tileSize, gp.tileSize);
            down4 = setUp("/player/walk/VW_down4", gp.tileSize, gp.tileSize);
            left1 = setUp("/player/walk/VW_left", gp.tileSize, gp.tileSize);
            left2 = setUp("/player/walk/VW_left2", gp.tileSize, gp.tileSize);
            left3 = setUp("/player/walk/VW_left3", gp.tileSize, gp.tileSize);
            left4 = setUp("/player/walk/VW_left4", gp.tileSize, gp.tileSize);
            right1 = setUp("/player/walk/VW_right", gp.tileSize, gp.tileSize);
            right2 = setUp("/player/walk/VW_right2", gp.tileSize, gp.tileSize);
            right3 = setUp("/player/walk/VW_right3", gp.tileSize, gp.tileSize);
            right4 = setUp("/player/walk/VW_right4", gp.tileSize, gp.tileSize);
    }

    public void getSleepingImage(BufferedImage image) {
        up1 = image;
        up2 = image;
        up3 = image;
        up4 = image;
        down1 = image;
        down2 = image;
        down3 = image;
        down4 = image;
        left1 = image;
        left2 = image;
        left3 = image;
        left4 = image;
        right1 = image;
        right2 = image;
        right3 = image;
        right4 = image;
    }
    public void getAttackImage() {

        if (currentWeapon.type == EntityType.EMERALD_SWORD) {
            attackUp1 = setUp("/player/combat/emerald/Blink_up_emerald_smear_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setUp("/player/combat/emerald/Blink_up_emerald_smear_2", gp.tileSize, gp.tileSize * 2);
            attackUp3 = setUp("/player/combat/emerald/Blink_up_emerald_smear_3", gp.tileSize, gp.tileSize * 2);
            attackUp4 = setUp("/player/combat/emerald/Blink_up_emerald_smear_4", gp.tileSize, gp.tileSize * 2);
            attackUp5 = setUp("/player/combat/emerald/Blink_up_emerald_smear_5", gp.tileSize, gp.tileSize * 2);

            attackDown1 = setUp("/player/combat/emerald/Blink_down_emerald_smear_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setUp("/player/combat/emerald/Blink_down_emerald_smear_2", gp.tileSize, gp.tileSize * 2);
            attackDown3 = setUp("/player/combat/emerald/Blink_down_emerald_smear_3", gp.tileSize, gp.tileSize * 2);
            attackDown4 = setUp("/player/combat/emerald/Blink_down_emerald_smear_4", gp.tileSize, gp.tileSize * 2);
            attackDown5 = setUp("/player/combat/emerald/Blink_down_emerald_smear_5", gp.tileSize, gp.tileSize * 2);

            attackLeft1 = setUp("/player/combat/emerald/Blink_left_emerald_smear_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setUp("/player/combat/emerald/Blink_left_emerald_smear_2", gp.tileSize * 2, gp.tileSize);
            attackLeft3 = setUp("/player/combat/emerald/Blink_left_emerald_smear_3", gp.tileSize * 2, gp.tileSize);
            attackLeft4 = setUp("/player/combat/emerald/Blink_left_emerald_smear_4", gp.tileSize * 2, gp.tileSize);
            attackLeft5 = setUp("/player/combat/emerald/Blink_left_emerald_smear_5", gp.tileSize * 2, gp.tileSize);

            attackRight1 = setUp("/player/combat/emerald/Blink_right_emerald_smear_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setUp("/player/combat/emerald/Blink_right_emerald_smear_2", gp.tileSize * 2, gp.tileSize);
            attackRight3 = setUp("/player/combat/emerald/Blink_right_emerald_smear_3", gp.tileSize * 2, gp.tileSize);
            attackRight4 = setUp("/player/combat/emerald/Blink_right_emerald_smear_4", gp.tileSize * 2, gp.tileSize);
            attackRight5 = setUp("/player/combat/emerald/Blink_right_emerald_smear_5", gp.tileSize * 2, gp.tileSize);
        }
        if (currentWeapon.type == EntityType.SWORD) {
            attackUp1 = setUp("/player/combat/Blink_up_smear_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setUp("/player/combat/Blink_up_smear_2", gp.tileSize, gp.tileSize * 2);
            attackUp3 = setUp("/player/combat/Blink_up_smear_3", gp.tileSize, gp.tileSize * 2);
            attackUp4 = setUp("/player/combat/Blink_up_smear_4", gp.tileSize, gp.tileSize * 2);
            attackUp5 = setUp("/player/combat/Blink_up_smear_5", gp.tileSize, gp.tileSize * 2);

            attackDown1 = setUp("/player/combat/Blink_down_smear_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setUp("/player/combat/Blink_down_smear_2", gp.tileSize, gp.tileSize * 2);
            attackDown3 = setUp("/player/combat/Blink_down_smear_3", gp.tileSize, gp.tileSize * 2);
            attackDown4 = setUp("/player/combat/Blink_down_smear_4", gp.tileSize, gp.tileSize * 2);
            attackDown5 = setUp("/player/combat/Blink_down_smear_5", gp.tileSize, gp.tileSize * 2);

            attackLeft1 = setUp("/player/combat/Blink_left_smear_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setUp("/player/combat/Blink_left_smear_2", gp.tileSize * 2, gp.tileSize);
            attackLeft3 = setUp("/player/combat/Blink_left_smear_3", gp.tileSize * 2, gp.tileSize);
            attackLeft4 = setUp("/player/combat/Blink_left_smear_4", gp.tileSize * 2, gp.tileSize);
            attackLeft5 = setUp("/player/combat/Blink_left_smear_5", gp.tileSize * 2, gp.tileSize);

            attackRight1 = setUp("/player/combat/Blink_right_smear_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setUp("/player/combat/Blink_right_smear_2", gp.tileSize * 2, gp.tileSize);
            attackRight3 = setUp("/player/combat/Blink_right_smear_3", gp.tileSize * 2, gp.tileSize);
            attackRight4 = setUp("/player/combat/Blink_right_smear_4", gp.tileSize * 2, gp.tileSize);
            attackRight5 = setUp("/player/combat/Blink_right_smear_5", gp.tileSize * 2, gp.tileSize);
        }
    }
    public void getGuardImage() {
        guardDown1 = setUp("/player/combat/block/Blink_down_block_1", gp.tileSize, gp.tileSize);
        guardDown2 = setUp("/player/combat/block/Blink_down_block_2", gp.tileSize, gp.tileSize);
        guardDown3 = setUp("/player/combat/block/Blink_down_block_3", gp.tileSize, gp.tileSize);
        guardDown4 = setUp("/player/combat/block/Blink_down_block_4", gp.tileSize, gp.tileSize);

        guardUp1 = setUp("/player/combat/block/Blink_up_block_1", gp.tileSize, gp.tileSize);
        guardUp2 = setUp("/player/combat/block/Blink_up_block_2", gp.tileSize, gp.tileSize);
        guardUp3 = setUp("/player/combat/block/Blink_up_block_3", gp.tileSize, gp.tileSize);
        guardUp4 = setUp("/player/combat/block/Blink_up_block_4", gp.tileSize, gp.tileSize);

        guardLeft1 = setUp("/player/combat/block/Blink_left_block_1", gp.tileSize, gp.tileSize);
        guardLeft2 = setUp("/player/combat/block/Blink_left_block_3", gp.tileSize, gp.tileSize);
        guardLeft3 = setUp("/player/combat/block/Blink_left_block_3", gp.tileSize, gp.tileSize);
        guardLeft4 = setUp("/player/combat/block/Blink_left_block_4", gp.tileSize, gp.tileSize);

        guardRight1 = setUp("/player/combat/block/Blink_right_block_1", gp.tileSize, gp.tileSize);
        guardRight2 = setUp("/player/combat/block/Blink_right_block_2", gp.tileSize, gp.tileSize);
        guardRight3 = setUp("/player/combat/block/Blink_right_block_3", gp.tileSize, gp.tileSize);
        guardRight4 = setUp("/player/combat/block/Blink_right_block_4", gp.tileSize, gp.tileSize);
    }
    @Override
    public void update() {

        resetSpeed();

        if (keyH.spacePressed == false) {
            if (stamina >= maxStamina) {
                stamina = maxStamina;
            } else {
                stamina += agility * 0.012f;
            }
        }

        if (attacking == true) {
            attacking();
        }
        else if (keyH.qPressed == true) {
            guarding = true;
        }

        else if(keyH.upPressed == true || keyH.downPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {

            if (keyH.upPressed) {
                direction = "up";
            } if (keyH.downPressed) {
                direction = "down";
            } if (keyH.leftPressed) {
                direction = "left";
            } if (keyH.rightPressed) {
                direction = "right";
            }

            if (keyH.spacePressed) {
                if (stamina > 1){
                    speed *= 2;
                    spriteSpeedModifier = speed;
                }
                if (stamina <= 0) {
                    stamina = 0;
//                    gp.playSE(gp.sound.exhaustedSE);
                } else {
                    stamina -= 0.06f;
                }
            }

//            CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

//            CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

//            CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npcs); //Checks if any npc from array collided with player
            interactNPC(npcIndex);

//            CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monsters);
            contactMonster(monsterIndex);

//            CHECK INTERACTIVE TILE COLLISION
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);

//            CHECK EVENT
            gp.eHandler.checkEvent();

//            IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false && keyH.enterPressed == false) {

                switch (direction) {
                    case "up" -> worldY -= speed + speedBoost;
                    case "down" -> worldY += speed + speedBoost;
                    case "left" -> worldX -= speed + speedBoost;
                    case "right" -> worldX += speed + speedBoost;
                }
            }

            if (keyH.enterPressed == true && attackCancelled == false && stamina > 1.5) {
                gp.playSE(gp.se.swordSlashSE);
                attacking = true;
                stamina -= 1;
                spriteCounter = 0;
            }
            attackCancelled = false;
            keyH.enterPressed = false;

            gp.keyH.enterPressed = false;
            guarding = false;

            spriteCounter++;

            if (spriteCounter > 9 - spriteSpeedModifier) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else if (spriteNum == 4) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        // Guarding becomes false if you are not pressing any key
//        guarding = false;

        if (gp.keyH.controlPressed == true && projectile.alive == false &&
                shotAvailableCounter == 30 && projectile.haveResource(this) == true) {
            shoot();
        }

        // This needs to be outside of key if statement. Because it has to be updated even if player doesn't move.
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                transparent = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }

        if (gp.keyH.zeroPressed) {
            gp.asSetter.setMonster();
            gp.keyH.zeroPressed = false;
        }

        if (life > maxLife) {
            life = maxLife;
        }

        if (mana > maxMana) {
            mana = maxMana;
        }

        if (stamina > maxStamina) {
            stamina = maxStamina;
        }

        if (life <= 0) {
            gp.gameState = gp.gameOverState;
            //Bypass for bug related to hitting Enter while dying
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.playSE(gp.se.gameOverSE);
        }
    }
    public void attacking () {

        attackSpriteCounter++;

        if (attackSpriteCounter <= 18 / attackSpeed) {spriteNum = 1;}
        if (attackSpriteCounter > 18 / attackSpeed && attackSpriteCounter <= 54 / attackSpeed) {spriteNum = 2;}
        if (attackSpriteCounter > 54 / attackSpeed && attackSpriteCounter <= 108 / attackSpeed) {
            damageMonster(checkWhatsHit(), this, attack, currentWeapon.knockBackPower);
            spriteNum = 3;
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);
        }
        if (attackSpriteCounter > 108 / attackSpeed && attackSpriteCounter <= 144 / attackSpeed) {spriteNum = 4;}
        if (attackSpriteCounter > 144 / attackSpeed && attackSpriteCounter <= 162 / attackSpeed) {spriteNum = 5;}
        if (attackSpriteCounter > 162 / attackSpeed) { //default 162 / 6 = 27 (~0.5s)
            spriteNum = 1;
            attackSpriteCounter = 0;
            attacking = false;
        }
    }
    public void shoot() {
        // SET DEFAULT COORDINATES, DIRECTION AND CASTER
        projectile.set(worldX, worldY, direction, true, this);

        // SUBTRACT THE COST (MANA, AMMO, etc.)
        projectile.subtractResource(this);

        // CHECK VACANCY
        for(int i = 0; i < gp.projectile[i].length; i++) {
            if (gp.projectile[gp.currentMap][i] == null) {
                gp.projectile[gp.currentMap][i] = projectile;
                break;
            }
        }

        shotAvailableCounter = 0;

        gp.playSE(gp.se.projectileCastSE);
    }
    public void pickUpObject (int i) {

        if (i != 999) {

            // PICKUP ONLY ITEMS
            if (gp.objects[gp.currentMap][i].type == EntityType.PICKUP_ONLY) {

                gp.objects[gp.currentMap][i].use(this);
                gp.objects[gp.currentMap][i] = null;
            }
            else if (gp.objects[gp.currentMap][i].type == EntityType.NOT_PICKABLE) {
                // DO NOTHING
            }
            // OBSTACLE
            else if(gp.objects[gp.currentMap][i].type == EntityType.OBSTACLE) {
                if(keyH.enterPressed == true) {
                    attackCancelled = true;
                    gp.objects[gp.currentMap][i].interact();
                }
            }
            else {
                // INVENTORY ITEMS
                String text;

                if (canObtainItem(gp.objects[gp.currentMap][i]) == true) {
                    gp.playSE(gp.se.coinSE);
                    text = "Got a " + gp.objects[gp.currentMap][i].name + "!";
                    gp.objects[gp.currentMap][i] = null;
                    gp.ui.addMessage(text);
                }
                else {
                    notificationCounter++;
                    if (notificationCounter > gp.FPS/2) {
                        notificationCounter = 0;
                        text = "You cannot carry anymore!";
                        gp.ui.addMessage(text);
                    }

                }
            }
        }
    }
    public int checkWhatsHit () {
        // Save current worldX,worldY and solidArea
        int currentWorldX = worldX;
        int currentWorldY = worldY;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;

        // Adjust player's worldX/Y for the attackArea
        switch (direction) {
            case "up" -> worldY -= attackArea.height;
            case "down" -> worldY += attackArea.height;
            case "left" -> worldX -= attackArea.width;
            case "right" -> worldX += attackArea.width;
        }
        // attackArea becomes solidArea;
        solidArea.width = attackArea.width;
        solidArea.height = attackArea.height;

        // Check monster collision with weaponArea
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monsters);

        int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
        damageProjectile(projectileIndex);

        //Restore original data
        worldX = currentWorldX;
        worldY = currentWorldY;
        solidArea.width = solidAreaWidth;
        solidArea.height = solidAreaHeight;

        return monsterIndex;
    }
    public void interactNPC (int i) {

        if(gp.gameState == gp.playState && gp.keyH.enterPressed == true) {
            if (i != 999) {
                attackCancelled = true;
                gp.gameState = gp.dialogueState;
                gp.npcs[gp.currentMap][i].speak();
                gp.playSE(gp.se.talkSE);
            }
        }
    }
    public void contactMonster(int i) {

        if (i != 999){

            if (! invincible &&  ! gp.monsters[gp.currentMap][i].dying) {
                int damage; //statements below are for case when armour is bigger than AP
                if (defense >= gp.monsters[gp.currentMap][i].attack) {damage = 1;}
                else {damage = gp.monsters[gp.currentMap][i].attack - defense;}
                life -= damage;
                gp.playSE(gp.se.hurtSE);
                invincible = true;
                transparent = true;
            }
        }
    }
    public void damageMonster (int i, Entity attacker, int attack, int knockBackPower) {

        if (i != 999) {

            if (gp.monsters[gp.currentMap][i].invincible == false) {

                gp.playSE(gp.monsters[gp.currentMap][i].hitSound);

                if(knockBackPower > 0) {
                    setKnockBack(gp.monsters[gp.currentMap][i], attacker, knockBackPower);
                }

                int damage; //statements below are for case when armour is bigger than AP
                if (gp.monsters[gp.currentMap][i].defense >= attack) {damage = 0;}
                else {damage = attack - gp.monsters[gp.currentMap][i].defense;}

                gp.monsters[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " damage!");

                try {
                    generateParticle(gp.monsters[gp.currentMap][i], gp.monsters[gp.currentMap][i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                gp.monsters[gp.currentMap][i].invincible = true;
                gp.monsters[gp.currentMap][i].damageReaction();

                if (gp.monsters[gp.currentMap][i].life <= 0) {
                    gp.monsters[gp.currentMap][i].dying = true;
                    gp.playSE(gp.se.monsterDeath);
                    exp += gp.monsters[gp.currentMap][i].exp;
                    gp.ui.addMessage("Blink killed the " + gp.monsters[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("Exp " + gp.monsters[gp.currentMap][i].exp);
                    checkLevelUp();
                }
            }
        }
    }
    public void damageInteractiveTile (int i){

        if (i != 999 && gp.iTile[gp.currentMap][i].destructible &&
                gp.iTile[gp.currentMap][i].isProperWeapon(this)
                && !gp.iTile[gp.currentMap][i].invincible) {

            gp.playSE(gp.se.hitBushSE);
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;

            // GENERATE PARTICLE
            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);

            if (gp.iTile[gp.currentMap][i].life <= 0) {
                gp.asSetter.dropObject(gp.iTile[gp.currentMap][i].getDestroyedForm(),
                        gp.iTile[gp.currentMap][i].worldX, gp.iTile[gp.currentMap][i].worldY);
                gp.iTile[gp.currentMap][i] = null;
            }
        }
    }
    public void damageProjectile (int i) {
        if (i != 999) {
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
        }
    }
    public void checkLevelUp () {

        if (exp >= nextLevelExp) {

            level++;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 1;
            maxMana += 1;
            maxStamina += 2;
            life = maxLife;
            mana = maxMana;
            stamina = maxStamina;
            strength++;
            agility++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(gp.se.powerUpSE);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level " + level + "now!\n"
                    + "You feel stronger!";
        }
    }
    public void selectItem (Entity entity) {

        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

        if (itemIndex < inventory.size ()) {

            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == EntityType.SWORD ||
                    selectedItem.type == EntityType.AXE ||
                    selectedItem.type == EntityType.CLUB ||
                    selectedItem.type == EntityType.EMERALD_SWORD) {

                currentWeapon = selectedItem;
                attack = getAttack();
                getAttackImage();
            }

            if (selectedItem.type == EntityType.SHIELD) {
                currentShield = selectedItem;
                defense = getDefense();
            }

            if(selectedItem.type == EntityType.LIGHT) {
                if(currentLight == selectedItem) {
                    currentLight = null;
                }
                else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }

            if (selectedItem.type == EntityType.CONSUMABLE) {

                if (selectedItem.use(this) == true) {
                    if(selectedItem.amount > 1) {
                        selectedItem.amount--;
                    }
                    else {
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
    }
    public int searchItemInventory(String itemName) {

        int itemIndex = 999;

        for(int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }
    public boolean canObtainItem(Entity item) {
        boolean canObtain = false;

        // Check if item is stackable
        if(item.stackable == true) {
            int index = searchItemInventory(item.name);

            if(index != 999) {
                inventory.get (index).amount++;
                canObtain = true;
            }
            else { // New item, so need to check vacancy
                if(inventory.size() != maxInventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }
        else { // Not stackable
            if(inventory.size() != maxInventorySize) {
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
    }
    public void resetSpeed () {

        speed = 3 + speedBoost;
        spriteSpeedModifier = 0;
    }
    public void draw (Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up" :
                if (attacking == false) {
                    switch (spriteNum) {
                        case 1 -> image = up1;
                        case 2 -> image = up2;
                        case 3 -> image = up3;
                        case 4 -> image = up4;
                    }
                }
                if (attacking == true) {
                    tempScreenY -= gp.tileSize;
                    switch (spriteNum) {
                        case 1 -> image = attackUp1;
                        case 2 -> image = attackUp2;
                        case 3 -> image = attackUp3;
                        case 4 -> image = attackUp4;
                        case 5 -> image = attackUp5;
                    }
                }
                if (guarding == true) {
                    switch (spriteNum) {
                        case 1 -> image = guardUp1;
                        case 2 -> image = guardUp2;
                        case 3 -> image = guardUp3;
                        case 4 -> image = guardUp4;
                    }
                }
                break;
            case "down" :
                if (attacking == false) {
                    switch (spriteNum) {
                        case 1 -> image = down1;
                        case 2 -> image = down2;
                        case 3 -> image = down3;
                        case 4 -> image = down4;
                    }
                }
                if (attacking == true) {
                    switch (spriteNum) {
                        case 1 -> image = attackDown1;
                        case 2 -> image = attackDown2;
                        case 3 -> image = attackDown3;
                        case 4 -> image = attackDown4;
                        case 5 -> image = attackDown5;
                    }
                }
                if (guarding == true) {
                    switch (spriteNum) {
                        case 1 -> image = guardDown1;
                        case 2 -> image = guardDown2;
                        case 3 -> image = guardDown3;
                        case 4 -> image = guardDown4;
                    }
                }
                break;
            case "left" :
                if (attacking == false) {
                    switch (spriteNum) {
                        case 1 -> image = left1;
                        case 2 -> image = left2;
                        case 3 -> image = left3;
                        case 4 -> image = left4;
                    }
                }
                if (attacking == true) {
                    tempScreenX -= gp.tileSize;
                    switch (spriteNum) {
                        case 1 -> image = attackLeft1;
                        case 2 -> image = attackLeft2;
                        case 3 -> image = attackLeft3;
                        case 4 -> image = attackLeft4;
                        case 5 -> image = attackLeft5;
                    }
                }
                if (guarding == true) {
                    switch (spriteNum) {
                        case 1 -> image = guardLeft1;
                        case 2 -> image = guardLeft2;
                        case 3 -> image = guardLeft3;
                        case 4 -> image = guardLeft4;
                    }
                }
                break;
            case "right" :
                if (attacking == false) {
                    switch (spriteNum) {
                        case 1 -> image = right1;
                        case 2 -> image = right2;
                        case 3 -> image = right3;
                        case 4 -> image = right4;
                    }
                }
                if (attacking == true) {
                    switch (spriteNum) {
                        case 1 -> image = attackRight1;
                        case 2 -> image = attackRight2;
                        case 3 -> image = attackRight3;
                        case 4 -> image = attackRight4;
                        case 5 -> image = attackRight5;
                    }
                }
                if (guarding == true) {
                    switch (spriteNum) {
                        case 1 -> image = guardRight1;
                        case 2 -> image = guardRight2;
                        case 3 -> image = guardRight3;
                        case 4 -> image = guardRight4;
                    }
                }
                break;
        }

        if (transparent == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY,null);

        // Reset alpha

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.objects[1].length; i++) {
            if (gp.objects[gp.currentMap][i] != null) {
                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Get the object's solid area position
                gp.objects[gp.currentMap][i].solidArea.x = gp.objects[gp.currentMap][i].worldX
                        + gp.objects[gp.currentMap][i].solidArea.x;
                gp.objects[gp.currentMap][i].solidArea.y = gp.objects[gp.currentMap][i].worldY
                        + gp.objects[gp.currentMap][i].solidArea.y;

                switch (entity.direction) {
                    case "up" -> entity.solidArea.y -= entity.speed;
                    case "down" -> entity.solidArea.y += entity.speed;
                    case "left" -> entity.solidArea.x -= entity.speed;
                    case "right" -> entity.solidArea.x += entity.speed;
                }

                if (entity.solidArea.intersects(gp.objects[gp.currentMap][i].solidArea)) {
                    if (gp.objects[gp.currentMap][i].collision) {
                        entity.collisionOn = true;
                    }
                    if (player == true) {
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.objects[gp.currentMap][i].solidArea.x = gp.objects[gp.currentMap][i].solidAreaDefaultX;
                gp.objects[gp.currentMap][i].solidArea.y = gp.objects[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
