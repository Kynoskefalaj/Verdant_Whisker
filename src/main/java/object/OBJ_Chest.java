package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Chest extends SuperObject{

    public OBJ_Chest () {
        name = "chest";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/objects/chest01.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}