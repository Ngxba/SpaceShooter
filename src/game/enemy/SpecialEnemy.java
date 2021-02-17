package game.enemy;

import lib.FrameCounter;
import game.coreGame.GameObject;
import lib.SpriteUtils;
import lib.Vector2D;
import game.explosion.Explosion;
import renderer.SingleImageRenderer;

import java.awt.image.BufferedImage;

public class SpecialEnemy extends Enemy{
    private BufferedImage image;
    private Vector2D toPlayer;
    private FrameCounter fireCounter;
    private double angle;

    public SpecialEnemy(){
        super(85, 75);
        this.image = SpriteUtils.loadImage("Asset/Ships/spaceShips_006.png");
        this.renderer = new SingleImageRenderer(image);
        this.position.setVector2D(250, -50);
        this.velocity.setVector2D(-1.5f, 1);
        this.angle = 0;
        this.hp = 100;
        this.fireCounter = new FrameCounter(30);
        this.isBoss = true;
    }

    @Override
    public void run(){
        super.run();
        this.toPlayer = this.player.position.subtract(this.position.x, this.position.y).setLengthThis(60);
        if (this.angle < this.toPlayer.getAngle()) this.angle += (this.toPlayer.getAngle() - this.angle) / 30;
        else if (this.angle > this.toPlayer.getAngle()) this.angle -= (this.angle - this.toPlayer.getAngle()) / 30;
        this.path();
    }

    private void fire() {
        EnemyBullet bullet1 = new EnemyBullet(0);
        EnemyBullet bullet2 = new EnemyBullet(0);
        EnemyBullet bullet3 = new EnemyBullet(0);
        bullet1.position.setVector2D(this.position.x + this.toPlayer.x , this.position.y + this.toPlayer.y );
        bullet2.position.setVector2D(this.position.x + this.toPlayer.x , this.position.y + this.toPlayer.y );
        bullet3.position.setVector2D(this.position.x + this.toPlayer.x , this.position.y + this.toPlayer.y );
        bullet1.velocity = this.toPlayer.setLength(3).setAngle(-this.angle + 90 + 30);
        bullet2.velocity = this.toPlayer.setLength(3);
        bullet3.velocity = this.toPlayer.setLength(3).setAngle(-this.angle + 90 - 30);
        GameObject.gameObjects.add(bullet1);
        GameObject.gameObjects.add(bullet2);
        GameObject.gameObjects.add(bullet3);
    }

    private void path() {
        if (this.position.y >= 80) {
            this.rotate();
            if (this.fireCounter.run() && this.player.active) {
                this.fire();
                this.fireCounter.reset();
            }
            if (this.position.x <= 50) {
                this.velocity.setVector2D(1, 0);
            } else if (this.position.x >= 460) {
                this.velocity.setVector2D(-1, 0);
            }
        }
    }

    private void rotate() {
        if (this.toPlayer.y >= 0) {
            BufferedImage rotate = SpriteUtils.rotateImageByDegrees(this.image, -this.angle);
            this.renderer = new SingleImageRenderer(rotate);
        } else {
            BufferedImage rotate = SpriteUtils.rotateImageByDegrees(this.image, -this.angle + 180);
            this.renderer = new SingleImageRenderer(rotate);
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
