package root;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public BufferedImage scaleImage(BufferedImage original, int width, int height) {

        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }

    public void drawEnterDelimitedString (String text, int x, int y, int intersection, Graphics2D g2) {
        for (String line : text.split("\n")) { // creates an array with String objects delimited by \n
            g2.drawString(line, x, y);
            y += intersection;
        }
    }
}
