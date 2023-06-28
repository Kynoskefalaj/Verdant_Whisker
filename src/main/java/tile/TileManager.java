package tile;

import root.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    public TileManager (GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];

        getTileImage();
    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/tiles/grass1.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/tiles/dirt1.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/tiles/water1.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
    }
}
