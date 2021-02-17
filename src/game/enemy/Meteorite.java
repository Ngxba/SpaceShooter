package game.enemy;

import game.coreGame.GameObject;
import lib.SpriteUtils;
import game.explosion.Explosion;
import renderer.Animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Meteorite extends Enemy{

    public Meteorite(float x, float y) {
        super(80, 80);
        this.position.setVector2D(x, y);
        this.createRenderer();
        this.hp = 35;
    }

    private void createRenderer() {
        ArrayList<BufferedImage> images = new ArrayList<>();
        images.add(SpriteUtils.loadImage("Asset/meteorite/meteorite-1.png"));
        images.add(SpriteUtils.loadImage("Asset/meteorite/meteorite-2.png"));
        images.add(SpriteUtils.loadImage("Asset/meteorite/meteorite-3.png"));
        images.add(SpriteUtils.loadImage("Asset/meteorite/meteorite-4.png"));
        images.add(SpriteUtils.loadImage("Asset/meteorite/meteorite-5.png"));
        images.add(SpriteUtils.loadImage("Asset/meteorite/meteorite-6.png"));
        images.add(SpriteUtils.loadImage("Asset/meteorite/meteorite-7.png"));
        images.add(SpriteUtils.loadImage("Asset/meteorite/meteorite-8.png"));
        images.add(SpriteUtils.loadImage("Asset/meteorite/meteorite-9.png"));
        images.add(SpriteUtils.loadImage("Asset/meteorite/meteorite-10.png"));
        images.add(SpriteUtils.loadImage("Asset/meteorite/meteorite-11.png"));
        images.add(SpriteUtils.loadImage("Asset/meteorite/meteorite-12.png"));


        this.renderer = new Animation(images, 5);
    }

    @Override
    public void run() {
        super.run();
        this.velocity.setVector2D(0, 2);
        this.limitPosition();

    }

    private void limitPosition() {
        if (this.position.y > 600) {
            this.destroy();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        Explosion explosion = new Explosion();
        explosion.position.setVector2D(this.position.x, this.position.y);
        GameObject.gameObjects.add(explosion);
    }
}
