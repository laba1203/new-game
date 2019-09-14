package elements;

import game.Game;
import main.Constants;

import java.awt.*;
import java.util.ConcurrentModificationException;

import static main.Constants.BF_HEIGHT;
import static main.Constants.Direction.DOWN;

public class Enemy extends AbstractElement implements Runnable, GUIElement{

    private static final int WIDTH = 32;
    private static final int HEIGHT = 40;
    private int x;
    private int y;

    private boolean alive = false;

    public Enemy(Game game, int x, int y){
        super(game);
        this.x = x;
        this.y = y;
        sleep(1000);
        move(DOWN);
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
        try {
            render();
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
            render();
        }
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
    public void run() {
        while (alive){
            flyDown();
            sleep(5);
        }
    }

    public void destroy(){

    }

}
