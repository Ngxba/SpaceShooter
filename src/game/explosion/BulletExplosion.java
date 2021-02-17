package game.explosion;

import game.coreGame.GameObject;
import lib.SpriteUtils;
import renderer.Animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BulletExplosion extends GameObject {

    public BulletExplosion() {
        this.createRenderer();
        this.position.setVector2D(100, 100);
        GameObject.midLayer.add(this);

    }

    private void createRenderer() {
        ArrayList<BufferedImage> images = new ArrayList<>();
        images.add(SpriteUtils.loadImage("Asset/explosion/bullet-explosion/row-1-col-1.png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/bullet-explosion/row-1-col-2.png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/bullet-explosion/row-1-col-3.png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/bullet-explosion/row-1-col-4.png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/bullet-explosion/row-1-col-5.png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/bullet-explosion/row-1-col-6.png"));
        images.add(SpriteUtils.loadImage("Asset/explosion/bullet-explosion/row-1-col-7.png"));


        this.renderer = new Animation(images, true, 1);

    }
}


