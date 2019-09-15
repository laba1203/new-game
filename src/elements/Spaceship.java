package elements;

import game.Game;
import main.Constants;

import java.awt.*;

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
    private boolean destroyed = false;


    public Spaceship(Game game){
        super(game);
        sprite = getSprite(SPACESHIP_IMG);
    }

    public boolean destroyed(){
        return destroyed;
    }

    public void move(Constants.Direction direction){
        if(destroyed){
            System.out.println("Your spaceship was destroyed.");
            return;
        }
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
        sleep(3);
    }

    public void shoot(){
        Bullet bullet = new Bullet(getGame(), getXCoord() + WIDTH/2, getYCoord());
        Game.addUiElement(bullet);
        bullet.move(UP);
    }

    @Override
    public int getXCoord(){
        return x;
    }

    @Override
    public int getYCoord(){
        return y;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public void destroy() {
        //TODO: Complete
        this.destroyed = true;
    }

    public void draw(Graphics g){
//        setGraphics(g);
//        sprite.draw(g, x, y);
        g.setColor(new Color(40, 250, 0));
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

}
