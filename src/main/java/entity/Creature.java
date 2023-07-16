package entity;

import java.awt.*;

public interface Creature {

    void setDefaultValues();
    void getImage();
    void setSounds();
    void update();
    void draw(Graphics2D g2);
}
