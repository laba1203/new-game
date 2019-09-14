package game;

import elements.Sprite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
@Deprecated
public class OldGame extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;
    private boolean running;
    public static int WIDTH = 1000; //ширина
    public static int HEIGHT = 900; //высота
    public static String NAME = "TUTORIAL 1";
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private static int x = 0;
    private static int y = 300;

//    public static Sprite hero;
    private Sprite spaceShip;

    public static void main(String[] args) {
        OldGame oldGame = new OldGame();
        oldGame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JFrame frame = new JFrame(OldGame.NAME);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //выход из приложения по нажатию клавиши ESC
        frame.setLayout(new BorderLayout());
        frame.add(oldGame, BorderLayout.CENTER); //добавляем холст на наш фрейм
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        oldGame.start();
    }

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        long delta;

        init();

        while(running) {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            update(delta);
            render();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void init() {
        spaceShip = getSprite("spaceship.png");
//        hero = getSprite("man.png");
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

        spaceShip.draw(g, x, y);

        g.dispose();
        bs.show();
    }

    public void update(long delta) {
        if(leftPressed){
            x--;
        }
        if (rightPressed){
            x++;
        }
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

    private class KeyInputHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                leftPressed = true;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                rightPressed = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                leftPressed = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                rightPressed = false;
            }
        }


    }


}
