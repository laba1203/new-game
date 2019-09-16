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

abstract class AbstractElement implements GUIElement{
    int x;
    int y;
    private Sprite sprite;
    private static boolean gameRendering = false;
    private Game game;

    AbstractElement(Game game){
        this.game = game;
    }

    protected Graphics getGraphics(){
        return game.getGraphics();
    }

    protected Game getGame(){
        return this.game;
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

    protected void sleep(long timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitRenderingCompleted(){
        while (gameRendering){
            sleep(1);
        }
    }

    @Deprecated
    protected void render(){
        try {
            waitRenderingCompleted();
            gameRendering = true;
            game.render();
        }finally {
            gameRendering = false;
        }
    }

    public void move(Constants.Direction direction, int timeout){
        int x = getXCoord();
        int y = getYCoord();
        if(this.destroyed()){
            System.out.println("Your object was destroyed.");
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
                setX(x-1);
                break;
            case RIGHT:
                if(x > BF_WIDTH - this.getWidth()){
                    return;
                }
                setX(x+1);
                break;
            case UP:
                if(y <= 0){
                    return;
                }
                setY(y-1);
                break;
            case DOWN:
                if(y > BF_HEIGHT - getHeight()){
                    return;
                }
                setY(y+1);
                break;
        }
        sleep(timeout);
    }

    @Override
    public int getXCoord() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getYCoord() {
        return y;
    }


}
