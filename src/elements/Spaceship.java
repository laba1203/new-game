package elements;

import game.Game;
import main.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static main.Constants.BF_HEIGHT;
import static main.Constants.BF_WIDTH;
import static main.Constants.Direction.UP;

public class Spaceship extends AbstractElement implements GUIElement{

    private static final int WIDTH = 64;
    private static final int HEIGHT = 64;
    private static final String SPACESHIP_IMG = "spaceship.png";
    private int x = BF_WIDTH/2;
    private int y = BF_HEIGHT - WIDTH;
    private Sprite sprite;
    private Graphics g;


    public Spaceship(Game game){
        super(game);
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

    public void shoot(){
        Bullet bullet = new Bullet(getGame(), getX() + WIDTH/2, getY());
        Game.addUiElement(bullet);
        bullet.move(UP);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void draw(Graphics g){
//        setGraphics(g);
//        sprite.draw(g, x, y);
        g.setColor(new Color(40, 250, 0));
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

}
