package root;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font arial_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage (String text) {

        message = text;
        messageOn = true;
    }
    public void draw (Graphics2D g2) {

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, 10, 0, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, gp.tileSize/2 + 55, 55);

        // MESSAGE
        if (messageOn == true) {

            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize, gp.tileSize * 5);

            messageCounter++;
            System.out.println("" + messageCounter);
            if(messageCounter >  120) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}
