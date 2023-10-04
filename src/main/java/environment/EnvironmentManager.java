package environment;

import root.GamePanel;

import java.awt.*;

public class EnvironmentManager {

    GamePanel gp;
    Lighting lighting;

    public EnvironmentManager (GamePanel gp) {
        this.gp = gp;
    }

    public void setup () {

        lighting = new Lighting(gp, 350);
    }

    public void draw(Graphics2D g2) {

        lighting.draw(g2);
    }
}
