package renderer;

import lib.FrameCounter;
import game.coreGame.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation extends Renderer{
    ArrayList<BufferedImage> images;
    int currentImageIndex;
    FrameCounter nextImageCounter;
    boolean isOnce;

    public Animation(ArrayList<BufferedImage> images, int countMax) {
        this.images = images;
        this.currentImageIndex = 0;
        this.nextImageCounter = new FrameCounter(countMax);
    }

    public Animation(ArrayList<BufferedImage> images, boolean isOnce, int countMax) {
        this(images, countMax);
        this.isOnce = isOnce;
    }

    @Override
    public void render(Graphics g, GameObject master) {
        BufferedImage image = this.images.get(this.currentImageIndex);
        g.drawImage(image, (int) (master.position.x - image.getWidth() / master.anchor.x), (int) (master.position.y - image.getHeight() / master.anchor.y), null);

        if (this.nextImageCounter.run()) {
            this.currentImageIndex++;

            if (this.isOnce && this.currentImageIndex >= this.images.size()) {
                master.destroy();
            }
            if (this.currentImageIndex >= this.images.size()) {
                this.currentImageIndex = 0;
            }
            this.nextImageCounter.reset();
        }

    }
}
