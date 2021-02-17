package game.coreGame;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JFrame {
    public static boolean isUpPress;
    public static boolean isDownPress;
    public static boolean isLeftPress;
    public static boolean isRightPress;
    public static boolean isSpacePress;
    public static boolean isEscapePress;
    public static boolean isAnyKeyPress;

    public GameWindow(){
        this.setVisible(true);
        this.setTitle("Advanced Java");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                isAnyKeyPress = true;
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    isUpPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    isRightPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    isDownPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    isLeftPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    isSpacePress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    isEscapePress = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                isAnyKeyPress = false;
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    isUpPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    isRightPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    isDownPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    isLeftPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    isSpacePress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    isEscapePress = false;
                }
            }

        });
    }
}
