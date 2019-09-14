package elements;

import main.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static main.Constants.BF_HEIGHT;
import static main.Constants.BF_WIDTH;

public class Spaceship {

    private static final int WIDTH = 64;
    private static final int HEIGHT = 64;
    private static final String SPACESHIP_IMG = "spaceship.png";
    private int x = 0;
    private int y = 0;
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
                if(x <= 0){
                    return;
                }
                x--;
                break;
            case RIGHT:
                if(x > BF_WIDTH - WIDTH){
                    return;
                }
                x++;
                break;
            case UP:
                if(y <= 0){
                    return;
                }
                y--;
                break;
            case DOWN:
                if(y > BF_HEIGHT - HEIGHT){
                    return;
                }
                y++;
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
//        sprite.draw(g, x, y);
        g.setColor(new Color(255, 0, 0));
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

}
