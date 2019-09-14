package main;

import game.Game;

import javax.swing.*;
import java.awt.*;

import static main.Constants.HEIGHT;
import static main.Constants.NAME;
import static main.Constants.WIDTH;

public class Main {


    public static void main(String[] args) {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JFrame frame = new JFrame(NAME);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //выход из приложения по нажатию клавиши ESC
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER); //добавляем холст на наш фрейм
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        game.start();
    }

}
