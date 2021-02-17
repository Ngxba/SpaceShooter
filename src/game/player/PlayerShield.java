package game.player;

import lib.FrameCounter;
import game.coreGame.GameObject;
import lib.SpriteUtils;
import physics.BoxCollider;
import renderer.Animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerShield extends GameObject {
    private Player shieldObject;
    private FrameCounter counter;

    public PlayerShield() {
        if (GameObject.getPlayer() != null) this.shieldObject = GameObject.getPlayer();
        this.position.setVector2D(this.shieldObject.position.x, this.shieldObject.position.y);
        this.shieldObject.haveShield = true;
        this.counter = new FrameCounter(180);
        this.createRenderer();
        this.boxCollider = new BoxCollider(this.position, 90, 90);
        GameObject.midLayer.add(this);
    }

    private void createRenderer() {
        ArrayList<BufferedImage> images = new ArrayList<>();
        images.add(SpriteUtils.loadImage("Asset/shield/image (0).png"));
        images.add(SpriteUtils.loadImage("Asset/shield/image (1).png"));
        images.add(SpriteUtils.loadImage("Asset/shield/image (2).png"));
        images.add(SpriteUtils.loadImage("Asset/shield/image (3).png"));
        images.add(SpriteUtils.loadImage("Asset/shield/image (4).png"));
        images.add(SpriteUtils.loadImage("Asset/shield/image (5).png"));
        images.add(SpriteUtils.loadImage("Asset/shield/image (6).png"));
        images.add(SpriteUtils.loadImage("Asset/shield/image (7).png"));
        images.add(SpriteUtils.loadImage("Asset/shield/image (8).png"));
        images.add(SpriteUtils.loadImage("Asset/shield/image (9).png"));
        images.add(SpriteUtils.loadImage("Asset/shield/image (10).png"));

        this.renderer = new Animation(images, 5);
    }

    @Override
    public void run() {
        super.run();
        this.position.setVector2D(this.shieldObject.position.x, this.shieldObject.position.y);
        if (this.counter.run()) {
            this.destroy();
            this.shieldObject.haveShield = false;
            this.counter.reset();
        }
        this.checkIntersect();
    }

    private void checkIntersect() {
        for (int i = 0; i < GameObject.allEnemies.size(); i ++) {
            if (this.boxCollider.intersects(GameObject.allEnemies.get(i).boxCollider) && this.active && GameObject.allEnemies.get(i).active && !GameObject.allEnemies.get(i).isBoss) {
                GameObject.allEnemies.get(i).destroy();
            }
        }
    }
}
