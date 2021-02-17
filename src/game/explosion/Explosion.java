package game.explosion;

import game.coreGame.GameObject;
import lib.AudioUtils;
import lib.SpriteUtils;
import renderer.Animation;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Explosion extends GameObject {
    private Clip explodeSound;
    public Explosion() {
        this.createRenderer();
        this.position.setVector2D(100, 100);
        GameObject.midLayer.add(this);
        this.explodeSound = AudioUtils.loadSound("Asset/sounds/explosion01.wav");
        this.explodeSound.start();

    }

    private void createRenderer() {
        ArrayList<BufferedImage> images = new ArrayList<>();
        images.add(SpriteUtils.loadImage("Asset/explosion/enemy-exposion/image (0).png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/enemy-exposion/image (1).png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/enemy-exposion/image (2).png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/enemy-exposion/image (3).png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/enemy-exposion/image (4).png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/enemy-exposion/image (5).png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/enemy-exposion/image (6).png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/enemy-exposion/image (7).png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/enemy-exposion/image (8).png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/enemy-exposion/image (9).png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/enemy-exposion/image (10).png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/enemy-exposion/image (11).png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/enemy-exposion/image (12).png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/enemy-exposion/image (13).png"));


        this.renderer = new Animation(images,true,1);
    }

}
