package entities;

import root.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Skeleton extends Entity {

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
