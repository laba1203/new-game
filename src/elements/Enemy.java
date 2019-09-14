package elements;

import game.Game;
import main.Constants;

import java.awt.*;

import static main.Constants.BF_HEIGHT;
import static main.Constants.Direction.DOWN;

public class Enemy extends AbstractElement implements Runnable, GUIElement{

    private static final int WIDTH = 32;
    private static final int HEIGHT = 40;
    private int x;
    private int y;
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
        move(DOWN);
    }

    @Override
    public void run() {
        sleep(startDelay);
        while (alive){
            flyDown();
            sleep(20);
        }
    }

    @Override
    public void move(Constants.Direction direction) {
        if(direction!= DOWN){
            System.out.println("Incorrect direction for the Enemy." + direction);
        }
        this.alive = true;
        new Thread(this).start();
    }

    private void flyDown(){
        if(y > BF_HEIGHT){
            this.alive = false;
        }
        y++;
        destroySpaceIfHit();
    }

    @Override
    public void draw(Graphics g) {
//        setGraphics(g);
//        sprite.draw(g, x, y);
        g.setColor(new Color(255, 180, 53));
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    @Override
    public int getXCoord() {
        return x;
    }

    @Override
    public int getYCoord() {
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
    public void destroy(){
        this.alive = false;
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
