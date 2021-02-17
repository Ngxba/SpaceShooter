package game.background;

import game.coreGame.GameObject;
import lib.SpriteUtils;
import renderer.SingleImageRenderer;

import java.awt.image.BufferedImage;

public class Background_back extends GameObject {
    private BufferedImage image;

    public Background_back() {
        this.image = SpriteUtils.loadImage("Asset/Backgrounds/bg_back.png");
        this.position.setVector2D(0, -512);
        this.renderer = new SingleImageRenderer(image);
        this.anchor.setVector2D(this.image.getWidth(), this.image.getHeight());
        GameObject.botLayer.add(this);
    }

    public void run() {
        if (this.position.y < 0) {
            this.position.y += 1;
        } else {
            this.position.y = -512;
        }

    }
}




