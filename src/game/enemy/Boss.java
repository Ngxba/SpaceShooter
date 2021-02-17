package game.enemy;

import game.Setting;
import lib.FrameCounter;
import game.coreGame.GameObject;
import lib.SpriteUtils;
import game.background.BackgroundGameOver;
import game.explosion.Explosion;
import renderer.SingleImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Boss extends Enemy{
    private BufferedImage image;
    private FrameCounter fireCounter1;
    private FrameCounter fireCounter2;
    private FrameCounter fireCounter1Delay;
    private FrameCounter fireCounter2Delay;
    private FrameCounter switchBulletCounter;
    private int bullet1;
    private int bullet2;
    private int bulletType;

    public Boss() {
        super(190, 150);
        this.image = SpriteUtils.resize("Asset/Enemies/shieldship.png", 1.7f);
        this.renderer = new SingleImageRenderer(this.image);
        this.position.setVector2D(250, -200);
        this.velocity.setVector2D(0 , 1);
        this.fireCounter1 = new FrameCounter(15);
        this.fireCounter2 = new FrameCounter(10);
        this.fireCounter1Delay = new FrameCounter(60);
        this.fireCounter2Delay = new FrameCounter(55);
        this.switchBulletCounter = new FrameCounter(600);
        this.bullet1 = 0;
        this.bullet2 = 0;
        this.bulletType = 1;
        this.hp = 500;
        this.isBoss = true;
    }

    @Override
    public void run() {
        super.run();
        this.path();
    }

    private void path() {
        if (this.position.y >= 120) {
            switch (this.bulletType) {
                case 1:
                    this.fire1();
                    break;
                case 2:
                    this.fire2();
                    break;
            }
            if (this.velocity.y >0) this.velocity.setVector2D(2, 0);
            if (this.position.x < 100) {
                this.velocity.setVector2D(2, 0);
            } else if (this.position.x > 400) {
                this.velocity.setVector2D(-2, 0);
            }
        }
    }

    private void fire1() {
        if (this.fireCounter1.run() && this.bullet1 < 10) {
            EnemyBullet bullet = new EnemyBullet(4);
            if (this.velocity.x > 0) bullet.position.setVector2D(this.position.x + 60, this.position.y + 60);
            if (this.velocity.x < 0) bullet.position.setVector2D(this.position.x - 60, this.position.y + 60);
            bullet.velocity.setVector2D(0, 2);
            GameObject.gameObjects.add(bullet);
            this.bullet1 ++;
            this.fireCounter1.reset();
        }
        if (this.bullet1 == 10) {
            if (this.fireCounter1Delay.run()) {
                this.bullet1 = 0;
                this.fireCounter1Delay.reset();
            }
        }
        if (this.switchBulletCounter.run() && this.bullet1 == 0) {
            this.bulletType = 2;
            this.switchBulletCounter.reset();
        }

    }

    private void fire2() {
        this.velocity.setVector2D(0, 0);
        if (this.fireCounter2.run() && this.bullet2 < 50) {
            EnemyBullet bullet1 = new EnemyBullet(3);
            EnemyBullet bullet2 = new EnemyBullet(3);
            bullet1.position.setVector2D(this.position.x + 10, this.position.y + 70);
            bullet2.position.setVector2D(this.position.x - 10, this.position.y + 70);
            bullet1.velocity.setVector2D(0 ,2).setAngleThis( (Math.random() * ((180 - 0) + 1)) + 0);
            bullet2.velocity.setVector2D(0 ,2).setAngleThis( (Math.random() * ((180 - 0) + 1)) + 0);
            GameObject.gameObjects.add(bullet1);
            GameObject.gameObjects.add(bullet2);
            this.bullet2 ++;
            this.fireCounter2.reset();
        }
        if (this.bullet2 == 50) {
            this.velocity.setVector2D(2, 0);
            if (this.fireCounter2Delay.run()) {
                this.bullet2 = 0;
                this.bulletType = 1;
                this.fireCounter2Delay.reset();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        Explosion explosion = new Explosion();
        explosion.position.setVector2D(this.position.x, this.position.y);
        GameObject.gameObjects.add(explosion);
        GameObject.gameObjects.add(new BackgroundGameOver(true));
        Setting.bossDied = true;

    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.RED);
        if (this.renderer !=null) g.fillRect((int) this.position.x + 5 - this.image.getWidth() / 2, (int) this.position.y - 110, (int) (this.hp * 0.4f), 10);

    }
}
