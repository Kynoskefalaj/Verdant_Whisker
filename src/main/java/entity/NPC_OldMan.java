package entity;

import root.GamePanel;

public class NPC_OldMan extends Entity{

    public NPC_OldMan (GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
    }

    public void getImage() {
        up1 = setUp("/npc/oldMan_up");
        up2 = setUp("/npc/oldMan_up2");
        up3 = setUp("/npc/oldMan_up3");
        up4 = setUp("/npc/oldMan_up4");
        down1 = setUp("/npc/oldMan_down");
        down2 = setUp("/npc/oldMan_down2");
        down3 = setUp("/npc/oldMan_down3");
        down4 = setUp("/npc/oldMan_down4");
        left1 = setUp("/npc/oldMan_left");
        left2 = setUp("/npc/oldMan_left2");
        left3 = setUp("/npc/oldMan_left3");
        left4 = setUp("/npc/oldMan_left4");
        right1 = setUp("/npc/oldMan_right");
        right2 = setUp("/npc/oldMan_right2");
        right3 = setUp("/npc/oldMan_right3");
        right4 = setUp("/npc/oldMan_right4");
    }
}
