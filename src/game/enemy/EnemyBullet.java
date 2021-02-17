package game.enemy;

import lib.SpriteUtils;
import physics.BoxCollider;
import renderer.SingleImageRenderer;
import java.awt.image.BufferedImage;
public class EnemyBullet extends Enemy{
    private BufferedImage image;

    public EnemyBullet(int type) {
        super(3, 3);
        this.createRenderer(type);
        if (type != 4) this.isBullet = true;
        if (type == 4) this.boxCollider = new BoxCollider(this.position, 50 , 50);
    }

    private void createRenderer(int type) {
        if (type == 0) {
            this.image = SpriteUtils.loadImage("Asset/Lasers/tile000.png");
            this.renderer = new SingleImageRenderer(this.image);
        }
        if (type == 1) {
            this.image = SpriteUtils.loadImage("Asset/Lasers/2.png");
            this.renderer = new SingleImageRenderer(this.image);
        }
        if (type == 2) {
            this.image = SpriteUtils.loadImage("Asset/Lasers/tile001.png");
            this.renderer = new SingleImageRenderer(this.image);
        }
        if (type == 3) {
            this.image = SpriteUtils.loadImage("Asset/Lasers/3.png");
            this.renderer = new SingleImageRenderer(this.image);
        }
        if (type == 4) {
            this.image = SpriteUtils.loadImage("Asset/Lasers/image.png");
            this.renderer = new SingleImageRenderer(this.image);
        }
    }


    @Override
    public void run() {
        super.run();
        if (this.velocity.getAngle() != 90) this.rotate();
        this.limitPosition();
    }

    private void limitPosition() {
        if (this.position.x < -50) {
            this.destroy();
        }
        if (this.position.x > 600) {
            this.destroy();
        }
        if (this.position.y < -50) {
            this.destroy();
        }
        if (this.position.y > 600) {
            this.destroy();
        }
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
        this.active = false;
    }
}
