package entities;

import java.awt.*;

public interface Creature {

    void setDefaultValues();
    void getImage();
    void setSounds();
    void checkDrop();
    void update();
    void draw(Graphics2D g2);
}
