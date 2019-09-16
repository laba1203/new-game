package elements;

import main.Constants;

import java.awt.*;

public interface GUIElement {

    void move(Constants.Direction direction);

    Sprite getSprite(String path);

    void draw(Graphics g);

    int getXCoord();

    void setX(int x);

    void setY(int y);

    int getYCoord();

    int getHeight();

    int getWidth();

    void destroy();

    boolean destroyed();







}
