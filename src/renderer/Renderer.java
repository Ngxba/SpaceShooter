package renderer;

import game.coreGame.GameObject;

import java.awt.*;

public abstract class Renderer {
    abstract public void render(Graphics g , GameObject master);
}
