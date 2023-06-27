package entity;
import root.GamePanel;
import root.KeyHandler;

public class Player {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
    }
}
