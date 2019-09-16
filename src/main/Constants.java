package main;

public class Constants {
    public static int BF_WIDTH = 800; //ширина
    public static int BF_HEIGHT = 800; //высота
    public static String BF_NAME = "SPACE BATTLE";

    public enum Direction{
        NONE,
        LEFT,
        RIGHT,
        UP,
        DOWN,
        DIAGONAL_DOWN_RIGHT,
        DIAGONAL_DOWN_LEFT
    }

}
