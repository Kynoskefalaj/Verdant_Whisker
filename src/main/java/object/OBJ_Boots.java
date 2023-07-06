package object;

import root.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Boots extends SuperObject {

    public OBJ_Boots (GamePanel gp) {
        name = "Boots";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/objects/boots01.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
