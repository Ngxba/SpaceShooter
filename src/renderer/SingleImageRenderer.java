package renderer;

import game.coreGame.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SingleImageRenderer extends Renderer {
    BufferedImage image;
    public SingleImageRenderer(BufferedImage image){
        this.image = image;
    }
    @Override
    public void render(Graphics g, GameObject master) {

        g.drawImage(this.image , (int) (master.position.x - this.image.getWidth() / (int) master.anchor.x), (int) (master.position.y - this.image.getHeight() / (int) master.anchor.y) , null );
    }
}
