import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    // SCREEN SETTINGS
    final int originalTileSize = 32; //32x32 tile
    final double scale = 1.5;

    final int tileSize = (int) (originalTileSize * scale); //48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768 px
    final int screenHeight = tileSize * maxScreenRow; //576 px


    public GamePanel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); //enabling this will improve performance
    }
}
