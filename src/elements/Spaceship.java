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
    private Sprite sprite;
    private boolean destroyed = false;


    public Spaceship(Game game){
        super(game);
        setX(BF_WIDTH/2);
        setY(BF_HEIGHT - WIDTH);
        sprite = getSprite(SPACESHIP_IMG);
    }

    @Override
    public boolean destroyed(){
        return destroyed;
    }

    @Override
    public void move(Constants.Direction direction){
        super.move(direction, 3);
    }

    public void shoot(){
        Bullet bullet = new Bullet(getGame(), getXCoord() + getWidth()/2, getYCoord());
        Game.addUiElement(bullet);
        bullet.move(UP);
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
