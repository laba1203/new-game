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
    private boolean destroyed = false;

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
            destroyIfHit();
            if(destroyed){
                break;
            }
            sleep(5);
        }

    }

    private void flyUp(){
        if(getYCoord() < - HEIGHT){
            this.shoot = false;
        }
        y--;
//        try {
//            render();
//        }catch (ConcurrentModificationException e){
//            e.printStackTrace();
//            render();
//        }
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
        this.destroyed = true;
        System.out.println("Bullet was destroyed.");
    }

    @Override
    public void draw(Graphics g) {
//        setGraphics(g);
//        sprite.draw(g, x, y);
        g.setColor(new Color(255, 0, 0));
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    private void destroyIfHit(){
        for (GUIElement element : getGame().getGuiElements()) {
            if(element instanceof Enemy) {
                if (this.y < (element.getYCoord() + element.getHeight())
                        && this.x >= element.getXCoord()
                        && this.x <= element.getXCoord() + element.getWidth()
                        ) {
                    element.destroy();
                    this.destroy();
                }
            }
        }
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


}
