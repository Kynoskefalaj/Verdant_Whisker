package entity;

import root.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Skeleton extends Entity {

//    protected BufferedImage left1, left2, left3, left4, left5, left6, left7, left8, left9, left10,
//            left11, left12, left13, left14, left15, left16, left17, left18, left19, left20,
//            left21;
//
//    protected BufferedImage right1, right2, right3, right4, right5, right6, right7, right8, right9, right10,
//            right11, right12, right13, right14, right15, right16, right17, right18, right19, right20,
//            right21;

    protected BufferedImage[] walkLeft = new BufferedImage[6];
    protected BufferedImage[] walkRight = new BufferedImage[6];

    protected BufferedImage[] attackLeft = new BufferedImage[21];
    protected BufferedImage[] attackRight = new BufferedImage[21];

    protected BufferedImage[] dieLeft = new BufferedImage[18];
    protected BufferedImage[] dieRight = new BufferedImage[18];

    public Skeleton(GamePanel gp) {
        super(gp);
    }

    public abstract void getAttackImage ();
    public abstract void getDyingImage ();
    public abstract void setAction ();
    public abstract void draw (Graphics2D g2);
    public abstract void dyingAnimation (Graphics2D g2);
}
