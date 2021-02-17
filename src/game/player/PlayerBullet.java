package game.player;

import game.coreGame.GameObject;
import lib.SpriteUtils;
import game.explosion.BulletExplosion;
import physics.BoxCollider;
import renderer.SingleImageRenderer;

import java.awt.image.BufferedImage;

public class PlayerBullet extends GameObject {
    private BufferedImage image;

    public PlayerBullet() {
        this.image = SpriteUtils.resize("Asset/Lasers/1.png", 0.8f);
        this.renderer = new SingleImageRenderer(this.image);
        this.position.setVector2D(0,0);
        this.boxCollider = new BoxCollider(this.position , 5,5);
        this.velocity.setVector2D(0, -10);
        GameObject.midLayer.add(this);

    }

    @Override
    public void run() {
        super.run();
        this.limitPosition();
        this.checkIntersect();
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

    private void checkIntersect() {
        for (int i = 0; i < GameObject.allEnemies.size(); i++){
            if(GameObject.allEnemies.get(i).boxCollider.intersects(this.boxCollider) && this.active && GameObject.allEnemies.get(i).active && !GameObject.allEnemies.get(i).isBullet){
                GameObject.allEnemies.get(i).takeDamage(1);
                this.destroy();
                BulletExplosion explosion = new BulletExplosion();
                explosion.position.setVector2D(this.position.x, this.position.y);
                GameObject.gameObjects.add(explosion);
            }
        }
    }


}
