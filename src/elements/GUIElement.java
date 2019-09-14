package elements;

import main.Constants;

import java.awt.*;

public interface GUIElement {

    void move(Constants.Direction direction);

    Sprite getSprite(String path);

    void draw(Graphics g);



}