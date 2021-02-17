package game;

import game.coreGame.GameCanvas;
import game.coreGame.GameWindow;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        GameCanvas gameCanvas = new GameCanvas();
        gameWindow.add(gameCanvas);
        gameWindow.setPreferredSize(new Dimension(523,544));
        gameWindow.pack();
        gameCanvas.gameloop();
    }
}
