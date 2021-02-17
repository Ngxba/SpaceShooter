package game.enemy;

import game.coreGame.GameObject;
import lib.SpriteUtils;
import game.explosion.Explosion;
import renderer.SingleImageRenderer;

import java.awt.image.BufferedImage;

public class Obstacle extends Enemy {
    private BufferedImage image;
    private double angle;
    private double addAngle;

    public Obstacle(){
        super(55, 50);
        this.image = SpriteUtils.resize("Asset/Enemies/enemyBlack1.png", 0.6f);
        this.position.setVector2D(250, -30);
        this.renderer = new SingleImageRenderer(image);
        this.velocity.setVector2D(3, 0);
        this.angle = 0;
        this.addAngle = 0;
        this.hp = 15;
    }

    @Override
    public void run(){
        super.run();
        this.path();
        this.rotate();

    }

    private void path() {

        if (this.position.x >= 60 && this.position.x <= 450) {
            this.addAngle = 0;
        } else {
            if (this.angle == 0) {
                this.addAngle = 5;
            }
            if (this.angle == 180) {
                this.addAngle = -5;
            }
        }

        this.angle += this.addAngle;
        this.velocity.setAngleThis(this.angle);

    }

    private void rotate() {
        if (this.velocity.y >= 0) {
            BufferedImage rotate = SpriteUtils.rotateImageByDegrees(this.image, -this.velocity.getAngle());
            this.renderer = new SingleImageRenderer(rotate);
        } else {
            BufferedImage rotate = SpriteUtils.rotateImageByDegrees(this.image, -this.velocity.getAngle() + 180);
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
