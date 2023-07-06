package object;

import root.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Door extends SuperObject{

    public OBJ_Door (GamePanel gp) {
        name = "Door";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/objects/door01.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }
}
