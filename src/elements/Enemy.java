package elements;

import game.Game;
import main.Constants;

import java.awt.*;
import java.util.Random;

import static main.Constants.BF_HEIGHT;
import static main.Constants.BF_WIDTH;
import static main.Constants.Direction.*;

public class Enemy extends AbstractElement implements Runnable, GUIElement{

    public static final int WIDTH = 32;
    private static final int HEIGHT = 40;
    private long startDelay = 0; //sleep before start

    private boolean alive;
    private boolean moveZigzag;


    public Enemy(Game game, int x, int y){
        this(game, x, y, 1000);
    }

    public Enemy(Game game, int x, int y, long timeout, boolean moveZigzag){
        super(game);
        this.x = x;
        this.y = y;
        this.startDelay = timeout;
        this.alive = true;
        this.moveZigzag = moveZigzag;
        new Thread(this).start();
    }

    public Enemy(Game game, int x, int y, long timeout){
        this(game, x, y, timeout, false);
    }

    @Override
    public void run() {
        sleep(startDelay);
        if(moveZigzag) {
            moveZigzagWhileAlive();
        }else {
            while (alive){
                if(getYCoord() > BF_HEIGHT){
                    this.alive = false;
                }
                move(DOWN);
                destroySpaceIfHit();
            }
        }
    }

    private void moveZigzagWhileAlive(){
        //choose first dirrection randomlly
        Random rand = new Random();
        Constants.Direction direction = DIAGONAL_DOWN_RIGHT;
        if(rand.nextInt(10) > 5){
            direction = DIAGONAL_DOWN_LEFT;
        }

        while (alive){
            move(direction);
            if(direction == DIAGONAL_DOWN_RIGHT && (getXCoord() >= BF_WIDTH - getWidth())){
                direction = DIAGONAL_DOWN_LEFT;
                System.out.println("Direction changed to " + direction);
                destroySpaceIfHit();
            }
            if(direction == DIAGONAL_DOWN_LEFT && (getXCoord() <= 0)){
                direction = DIAGONAL_DOWN_RIGHT;
                System.out.println("Direction changed to " + direction);
                destroySpaceIfHit();
            }
        }
    }

    @Override
    public void move(Constants.Direction direction) {
        super.move(direction, 15);
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
