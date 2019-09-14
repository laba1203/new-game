package elements;

import main.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Spaceship {

    private static final String SPACESHIP_IMG = "spaceship.png";
    private int x = 0;
    private int y = 300;
    private Sprite sprite;


    public Spaceship(){
        sprite = getSprite(SPACESHIP_IMG);
    }

    public void move(Constants.Direction direction){
        switch (direction){
            default:
                return;
            case NONE:
                //do nothing;
                return;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
    }

    public Sprite getSprite(String path) {
        BufferedImage sourceImage = null;

        try {
            URL url = this.getClass().getClassLoader().getResource(path);
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sprite sprite = new Sprite(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()));

        return sprite;
    }

    public void draw(Graphics g){
        sprite.draw(g, x, y);
    }

}
