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
import java.util.Random;

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
        generateEnemies();

        while(running) {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            spaceship.move(direction);
            render();
            running = !spaceship.destroyed();
        }

        System.out.println("<<<<-----  Game Over  ------>>>>>>");
    }

    public ArrayList<GUIElement> getGuiElements(){
        return guiElements;
    }

    public Spaceship getSpaceship(){
        return spaceship;
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

    private void generateEnemies(){
        int y = -100;
        long timeout = 6000;
        for(int i = 0; i < 6; i++){
            Random rand = new Random();
            int x = rand.nextInt(BF_WIDTH);
            addUiElement(new Enemy(this, x, y, timeout));
            if(timeout < 1000){
                timeout = 4000;
            }
            timeout = timeout - 1000;
        }
        System.out.println("Enemies were generated.");
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
