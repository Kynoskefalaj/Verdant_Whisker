package entities;

import root.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_OldMan extends Entity{

    public NPC_OldMan (GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        name = "Old Man";

        getImage();
        setDialogue();

        solidArea.x = (int)(10 * gp.scale);
        solidArea.y = (int)(16 * gp.scale);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = (int)(12 * gp.scale);
        solidArea.height = (int)(14 * gp.scale);

        type = EntityType.NPC;
    }

    public void getImage() {
        up1 = setUp("/npc/oldMan_up", gp.tileSize, gp.tileSize);
        up2 = setUp("/npc/oldMan_up2", gp.tileSize, gp.tileSize);
        up3 = setUp("/npc/oldMan_up3", gp.tileSize, gp.tileSize);
        up4 = setUp("/npc/oldMan_up4", gp.tileSize, gp.tileSize);
        down1 = setUp("/npc/oldMan_down", gp.tileSize, gp.tileSize);
        down2 = setUp("/npc/oldMan_down2", gp.tileSize, gp.tileSize);
        down3 = setUp("/npc/oldMan_down3", gp.tileSize, gp.tileSize);
        down4 = setUp("/npc/oldMan_down4", gp.tileSize, gp.tileSize);
        left1 = setUp("/npc/oldMan_left", gp.tileSize, gp.tileSize);
        left2 = setUp("/npc/oldMan_left2", gp.tileSize, gp.tileSize);
        left3 = setUp("/npc/oldMan_left3", gp.tileSize, gp.tileSize);
        left4 = setUp("/npc/oldMan_left4", gp.tileSize, gp.tileSize);
        right1 = setUp("/npc/oldMan_right", gp.tileSize, gp.tileSize);
        right2 = setUp("/npc/oldMan_right2", gp.tileSize, gp.tileSize);
        right3 = setUp("/npc/oldMan_right3", gp.tileSize, gp.tileSize);
        right4 = setUp("/npc/oldMan_right4", gp.tileSize, gp.tileSize);
    }

    public void setDialogue () {

        dialogues[0] =
                "Hello, lad.";
        dialogues[1] =
                "Oww... \n" +
                "I feel that...\n" +
                "You must be The One...";
        dialogues[2] =
                "Verdant Whisker!\n" +
                "Of course! That makes sense!\n" +
                "Finally! \n" +
                "I've been waiting for centuries!";
        dialogues[3] =
                "You are Blink!";
        dialogues[4] =
                "Great darkness is coming for us,\n" +
                "you have to stop it!";
        dialogues[5] =
                "\"Only the One with a pure heart, verdant whisker\n" +
                "and an emerald tail can find the sword of destiny...\n" +
                "Holding back The Calamity...\n" +
                "And bringing the light once again!\"";
        dialogues[6] =
                "You have to find The Sword...\n" +
                "Otherwise everything is lost!";
    }

    @Override
    public void setAction () {
        if (onPath == true && dialogueIndex == 6) {
//            int goalCol = 12;
//            int goalRow = 9;
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

            searchPath(goalCol, goalRow);
        } else {
            actionLockCounter++;
            if (actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1; // pick up a number from 0 to 100

                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75) {
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }
    }

    public void speak () {

        // Do this character specific stuff in future

        super.speak();

        onPath = true;
    }
}
