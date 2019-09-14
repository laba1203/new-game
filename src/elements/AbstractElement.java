package elements;

import game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

abstract class AbstractElement {
    private int x = 0;
    private int y = 0;
    private Sprite sprite;
    private static boolean gameRendering = false;
//    private Graphics g;
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

//    protected void setGraphics(Graphics g){
//        this.g = g;
//    }

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

    protected void render(){
        try {
            waitRenderingCompleted();
            gameRendering = true;
            game.render();
        }finally {
            gameRendering = false;
        }
    }

}
