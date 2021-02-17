package game.enemy;

import lib.FrameCounter;
import game.coreGame.GameObject;
import lib.SpriteUtils;
import game.explosion.Explosion;
import renderer.SingleImageRenderer;

import java.awt.image.BufferedImage;

public class NormalEnemy extends Enemy{
    private FrameCounter fireCounter;
    private int type;

    public NormalEnemy(int type, float x, float y) {
        super(55, 50);
        this.type = type;
        this.createRenderer();
        this.velocity.setVector2D(0 ,1);
        this.position.setVector2D(x, y);
        this.fireCounter = new FrameCounter(120);
        this.hp = 10;
    }

    private void createRenderer() {
        if (this.type == 1) {
            BufferedImage image = SpriteUtils.resize("Asset/Enemies/enemyGreen2.png", 0.6f);
            this.renderer = new SingleImageRenderer(image);
        }
        if (this.type == 2) {
            BufferedImage image = SpriteUtils.resize("Asset/Enemies/enemyRed3.png", 0.6f);
            this.renderer = new SingleImageRenderer(image);
        }
    }

    @Override
    public void run() {

        super.run();
        if (this.fireCounter.run()) {
            this.fire();
            this.fireCounter.reset();
        }
        this.limitPosition();
    }

    private void limitPosition() {
        if (this.position.y > 600) {
            this.destroy();
        }
    }

    private void fire() {
        EnemyBullet bullet1 = new EnemyBullet(this.type);
        EnemyBullet bullet2 = new EnemyBullet(this.type);
        EnemyBullet bullet3 = new EnemyBullet(this.type);
        bullet1.position.setVector2D(this.position.x, this.position.y + 10);
        bullet2.position.setVector2D(this.position.x, this.position.y + 10);
        bullet3.position.setVector2D(this.position.x, this.position.y + 10);
        bullet1.velocity = this.velocity.setLength(3).setAngle(60);
        bullet2.velocity = this.velocity.setLength(3);
        bullet3.velocity = this.velocity.setLength(3).setAngle(120);
        GameObject.gameObjects.add(bullet1);
        GameObject.gameObjects.add(bullet2);
        GameObject.gameObjects.add(bullet3);
    }

    @Override
    public void destroy() {
        super.destroy();
        Explosion explosion = new Explosion();
        explosion.position.setVector2D(this.position.x, this.position.y);
        GameObject.gameObjects.add(explosion);
    }
}
