package game.coreGame;

import game.background.BackgroundLoading;
import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {

    public GameCanvas() {
        this.setBackground(Color.WHITE);
        GameObject.gameObjects.add(new BackgroundLoading());

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(0, 0, 512, 512);

        GameObject.renderAll(g);
    }

    public void renderALL() {

        this.repaint(); // ~ paint()
    }

    public void runALL() {

        GameObject.runAll();
    }



    public void gameloop() {

        long lastTimeRun = 0;
        while (true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTimeRun > 1000 / 60) {
                runALL(); // logic game
                renderALL(); // hien thi game
                lastTimeRun = currentTime;
            }
        }
    }
}
