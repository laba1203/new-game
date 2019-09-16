package elements;

import game.Game;
import main.Constants;

import java.awt.*;

import static main.Constants.BF_HEIGHT;
import static main.Constants.BF_WIDTH;
import static main.Constants.Direction.*;

public class Enemy extends AbstractElement implements Runnable, GUIElement{

    public static final int WIDTH = 32;
    private static final int HEIGHT = 40;
    private long startDelay = 0; //sleep before start

    private boolean alive = false;


    public Enemy(Game game, int x, int y){
        this(game, x, y, 1000);
    }

    public Enemy(Game game, int x, int y, long timeout){
        super(game);
        this.x = x;
        this.y = y;
        this.startDelay = timeout;
        this.alive = true;
        new Thread(this).start();
    }

    @Override
    public void run() {
        sleep(startDelay);
        while (alive){
            if(getYCoord() > BF_HEIGHT){
                this.alive = false;
            }
            move(DOWN);
            //TODO
//            if(getXCoord() <= BF_WIDTH - getXCoord() - 10){
//                move(DIAGONAL_DOWN_RIGHT);
//            }
//            if(getXCoord() <= 10) {
//                move(DIAGONAL_DOWN_LEFT);
//            }


            destroySpaceIfHit();
        }
    }

    @Override
    public void move(Constants.Direction direction) {
        super.move(direction, 20);
    }

    @Override
    public void draw(Graphics g) {
//        setGraphics(g);
//        sprite.draw(g, x, y);
        g.setColor(new Color(255, 180, 53));
        g.fillRect(x, y, WIDTH, HEIGHT);
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
    public void destroy(){
        this.alive = false;
        this.x = -100;
    }

    @Override
    public boolean destroyed() {
        return !alive;
    }

    private void destroySpaceIfHit(){
        Spaceship spaceship = getGame().getSpaceship();
        if(this.x >= spaceship.getXCoord()
                && this.x <= spaceship.getXCoord() + spaceship.getWidth()
                && this.y < spaceship.getYCoord() + spaceship.getHeight()
                && this.y > spaceship.getYCoord()
                ){
            spaceship.destroy();
        }
    }

}
