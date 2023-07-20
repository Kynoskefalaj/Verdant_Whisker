package object;

import entity.Entity;
import root.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends Entity {

    public OBJ_Key (GamePanel gp) {
        super(gp);
        name = "Key";
        image1 = setUp("/objects/key01", gp.tileSize, gp.tileSize);
    }
}
