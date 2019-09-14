package game;

import elements.Spaceship;
import main.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import static main.Constants.Direction.*;

public class Game extends Canvas implements Runnable{

    private boolean running;

    private Spaceship spaceship;

    private static Constants.Direction direction = NONE;




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
        spaceship = new Spaceship();
        addKeyListener(new KeyInputHandler());

    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(2);
            requestFocus();
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black); //выбрать цвет
        g.fillRect(0, 0, getWidth(), getHeight()); //заполнить прямоугольник

        spaceship.draw(g);

        g.dispose();
        bs.show();
    }

    public void start(){
        running = true;
        new Thread(this).start();
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
            }
//            if(e.getKeyCode() == KeyEvent.VK_LEFT){
//                direction = LEFT;
//            }
//            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//                direction = RIGHT;
//            }

        }

        @Override
        public void keyReleased(KeyEvent e){
//            if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT){
                direction = NONE;
//            }
        }


    }


}
