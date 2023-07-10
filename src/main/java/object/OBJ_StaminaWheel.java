package object;

import root.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_StaminaWheel extends SuperObject{
    GamePanel gp;

    public OBJ_StaminaWheel(GamePanel gp) {

        this.gp = gp;
        name = "StaminaWheel";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/objects/hud/stamina_wheel_full.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/objects/hud/stamina_wheel_7_8.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/objects/hud/stamina_wheel_6_8.png")));
            image4 = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/objects/hud/stamina_wheel_5_8.png")));
            image5 = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/objects/hud/stamina_wheel_4_8.png")));
            image6 = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/objects/hud/stamina_wheel_3_8.png")));
            image7 = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/objects/hud/stamina_wheel_2_8.png")));
            image8 = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/objects/hud/stamina_wheel_1_8.png")));
            image9 = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/objects/hud/stamina_wheel_blank.png")));

            image = uTool.scaleImage(image, gp.tileSize *2/3, gp.tileSize *2/3);
            image2 = uTool.scaleImage(image2, gp.tileSize *2/3, gp.tileSize *2/3);
            image3 = uTool.scaleImage(image3, gp.tileSize *2/3, gp.tileSize *2/3);
            image4 = uTool.scaleImage(image4, gp.tileSize *2/3, gp.tileSize *2/3);
            image5 = uTool.scaleImage(image5, gp.tileSize *2/3, gp.tileSize *2/3);
            image6 = uTool.scaleImage(image6, gp.tileSize *2/3, gp.tileSize *2/3);
            image7 = uTool.scaleImage(image7, gp.tileSize *2/3, gp.tileSize *2/3);
            image8 = uTool.scaleImage(image8, gp.tileSize *2/3, gp.tileSize *2/3);
            image9 = uTool.scaleImage(image9, gp.tileSize *2/3, gp.tileSize *2/3);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
