package elements;

import game.Game;
import main.Constants;

import java.awt.*;
import java.util.ConcurrentModificationException;

import static main.Constants.Direction.UP;

public class Bullet extends AbstractElement implements GUIElement, Runnable {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private int x;
    private int y;
    private boolean shoot = false;

    Bullet(Game game, int x, int y){
        super(game);
//        setCoordinates(x, y);
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        while (shoot){
            flyUp();
            sleep(5);
        }

    }

    private void flyUp(){
        if(getYCoord() < - HEIGHT){
            this.shoot = false;
        }
        y--;
        try {
            render();
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
            render();
        }
    }

    @Override
    public void move(Constants.Direction direction) {
        if(direction != UP){
            System.out.println("Unknown direction for bullet: " + direction);
        }
        this.shoot = true;
        new Thread(this).start();
    }

    public void destroy(){
        System.out.println("Bullet was destroyed.");
        //TODO
    }

    @Override
    public void draw(Graphics g) {
//        setGraphics(g);
//        sprite.draw(g, x, y);
        g.setColor(new Color(255, 0, 0));
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


}
