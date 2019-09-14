package game;

import elements.Enemy;
import elements.GUIElement;
import elements.Spaceship;
import main.Constants;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import static main.Constants.BF_WIDTH;
import static main.Constants.Direction.*;

public class Game extends Canvas implements Runnable{

    private boolean running;

    private Spaceship spaceship;

    private static Constants.Direction direction = NONE;

    private static ArrayList<GUIElement> guiElements = new ArrayList<>();



    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        long delta;

        init();

        while(running) {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            spaceship.move(direction);
            render();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void init(){
        spaceship = new Spaceship(this);
        guiElements.add(spaceship);
        guiElements.add(new Enemy(this, BF_WIDTH/2, 0));
        addKeyListener(new KeyInputHandler());

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(2);
            requestFocus();
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black); //выбрать цвет
        g.fillRect(0, 0, getWidth(), getHeight()); //заполнить прямоугольник

        for (GUIElement item: guiElements){
            item.draw(g);
        }

        g.dispose();
        bs.show();
    }

    public void start(){
        running = true;
        new Thread(this).start();
    }

    public static void addUiElement(GUIElement el){
        guiElements.add(el);
    }


    private class KeyInputHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                default:
                    System.out.println("Unknown key event.");
                    break;
                case KeyEvent.VK_LEFT:
                    direction = LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                    direction = RIGHT;
                    break;
                case KeyEvent.VK_UP:
                    direction = UP;
                    break;
                case KeyEvent.VK_DOWN:
                    direction = DOWN;
                    break;
                case KeyEvent.VK_SPACE:
                    spaceship.shoot();
                    break;
            }
            render();
        }

        @Override
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_LEFT
                    || e.getKeyCode() == KeyEvent.VK_RIGHT
                    || e.getKeyCode() == KeyEvent.VK_UP
                    || e.getKeyCode() == KeyEvent.VK_DOWN
                    || e.getKeyCode() == KeyEvent.VK_SPACE
                    ) {
                direction = NONE;
            }
            render();
        }


    }


}
