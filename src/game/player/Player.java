package game.player;

import game.background.BackgroundGameOver;
import game.coreGame.GameObject;
import game.coreGame.GameWindow;
import game.explosion.Explosion;
import lib.AudioUtils;
import lib.FrameCounter;
import lib.SpriteUtils;
import physics.BoxCollider;
import renderer.SingleImageRenderer;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;

public class Player extends GameObject {
    private BufferedImage image;
    private FrameCounter bulletCounter;
    private double angle;
    private int bulletLevel;
    int hp;
    public boolean haveShield;
    private Clip fireSound;

    public Player() {
        this.image = SpriteUtils.resize("Asset/Ships/spaceShips_001.png", 0.6f);
        this.position.setVector2D(200,200);
        this.renderer = new SingleImageRenderer(image);
        this.bulletCounter = new FrameCounter(5);
        this.boxCollider = new BoxCollider(this.position , 50,30);
        this.angle = 0;
        this.bulletLevel = 1;
        this.hp = 3;
        this.haveShield = false;
        GameObject.midLayer.add(this);
        this.fireSound = AudioUtils.loadSound("Asset/sounds/Laser_09.wav");
    }

    @Override
    public void run(){
        super.run();
        this.move();
        if (this.bulletCounter.run()) {
            if (GameWindow.isSpacePress) {
                AudioUtils.replay(this.fireSound);
                switch (this.bulletLevel) {
                    case 1:
                        this.fire1();
                        break;
                    case 2:
                        this.fire2();
                        break;
                    case 3:
                        this.fire3();
                        break;
                    case 4:
                        this.fire4();
                        break;
                    case 5:
                        this.fire5();
                        break;
                    case 6:
                        this.fire6();
                        break;
                }
            }
            this.bulletCounter.reset();
        }
        this.limitPosition();
        this.rotate();
    }



    private void limitPosition() {
        if (this.position.x <= this.image.getWidth() /2 ) {
            this.position.setVector2D((float) this.image.getWidth() / 2, this.position.y);
        }
        if (this.position.x > 505 - this.image.getWidth() / 2) {
            this.position.setVector2D(505 - (float) this.image.getWidth() /2, this.position.y);
        }
        if (this.position.y < this.image.getHeight() / 2) {
            this.position.setVector2D(this.position.x, (float) this.image.getHeight() / 2);
        }
        if (this.position.y > 505 - this.image.getHeight() / 2) {
            this.position.setVector2D(this.position.x, 505 - (float) this.image.getHeight() / 2);
        }
    }

    private void move() {
        float vx = 0;
        float vy = 0;
        float speed = 5;
        if (GameWindow.isUpPress) {
            vy--;
        }
        if (GameWindow.isDownPress) {
            vy++;
        }
        if (GameWindow.isRightPress) {
            if (this.angle < 30) {
                this.angle += 5;
            }
            vx++;
        }
        if (GameWindow.isLeftPress) {
            if (this.angle > -30) {
                this.angle -= 5;
            }
            vx--;
        }
        this.velocity.setVector2D(vx, vy);
        this.velocity.setLengthThis(speed);

        if (!GameWindow.isRightPress && !GameWindow.isLeftPress) {
            if (this.angle > 0) {
                this.angle -= 5;
            } else if (this.angle < 0) {
                this.angle +=5;
            }
        }
    }

    private void fire1() {
        PlayerBullet bullet = new PlayerBullet();
        bullet.position.setVector2D(this.position.x, this.position.y);
        bullet.velocity.setAngleThis(this.angle - 90);
        GameObject.gameObjects.add(bullet);

    }

    private void fire2() {
        PlayerBullet bullet1 = new PlayerBullet();
        PlayerBullet bullet2 = new PlayerBullet();
        bullet1.position.setVector2D(this.position.x - 4, this.position.y);
        bullet2.position.setVector2D(this.position.x + 4, this.position.y);
        bullet1.velocity.setAngleThis(this.angle - 90);
        bullet2.velocity.setAngleThis(this.angle - 90);
        GameObject.gameObjects.add(bullet1);
        GameObject.gameObjects.add(bullet2);

    }

    private void fire3() {
        PlayerBullet bullet1 = new PlayerBullet();
        PlayerBullet bullet2 = new PlayerBullet();
        PlayerBullet bullet3 = new PlayerBullet();
        bullet1.position.setVector2D(this.position.x - 10, this.position.y);
        bullet2.position.setVector2D(this.position.x , this.position.y);
        bullet3.position.setVector2D(this.position.x + 10, this.position.y);
        bullet1.velocity.setAngleThis(this.angle - 90);
        bullet2.velocity.setAngleThis(this.angle - 90);
        bullet3.velocity.setAngleThis(this.angle - 90);
        GameObject.gameObjects.add(bullet1);
        GameObject.gameObjects.add(bullet2);
        GameObject.gameObjects.add(bullet3);

    }

    private void fire4() {
        PlayerBullet bullet1 = new PlayerBullet();
        PlayerBullet bullet2 = new PlayerBullet();
        PlayerBullet bullet3 = new PlayerBullet();
        bullet1.position.setVector2D(this.position.x - 9, this.position.y);
        bullet2.position.setVector2D(this.position.x , this.position.y);
        bullet3.position.setVector2D(this.position.x + 9, this.position.y);
        bullet1.velocity.setAngleThis(this.angle - 10 - 90);
        bullet2.velocity.setAngleThis(this.angle - 90);
        bullet3.velocity.setAngleThis(this.angle + 10 - 90);
        GameObject.gameObjects.add(bullet1);
        GameObject.gameObjects.add(bullet2);
        GameObject.gameObjects.add(bullet3);

    }

    private void fire5() {
        PlayerBullet bullet1 = new PlayerBullet();
        PlayerBullet bullet2 = new PlayerBullet();
        PlayerBullet bullet3 = new PlayerBullet();
        PlayerBullet bullet4 = new PlayerBullet();
        bullet1.position.setVector2D(this.position.x - 10, this.position.y);
        bullet2.position.setVector2D(this.position.x - 4, this.position.y);
        bullet3.position.setVector2D(this.position.x + 4, this.position.y);
        bullet4.position.setVector2D(this.position.x + 10, this.position.y);
        bullet1.velocity.setAngleThis(this.angle - 10 - 90);
        bullet2.velocity.setAngleThis(this.angle - 90);
        bullet3.velocity.setAngleThis(this.angle - 90);
        bullet4.velocity.setAngleThis(this.angle + 10 - 90);
        GameObject.gameObjects.add(bullet1);
        GameObject.gameObjects.add(bullet2);
        GameObject.gameObjects.add(bullet3);
        GameObject.gameObjects.add(bullet4);

    }

    private void fire6() {
        PlayerBullet bullet1 = new PlayerBullet();
        PlayerBullet bullet2 = new PlayerBullet();
        PlayerBullet bullet3 = new PlayerBullet();
        PlayerBullet bullet4 = new PlayerBullet();
        PlayerBullet bullet5 = new PlayerBullet();
        bullet1.position.setVector2D(this.position.x - 15, this.position.y);
        bullet2.position.setVector2D(this.position.x - 10, this.position.y);
        bullet3.position.setVector2D(this.position.x , this.position.y);
        bullet4.position.setVector2D(this.position.x + 10, this.position.y);
        bullet5.position.setVector2D(this.position.x + 15, this.position.y);
        bullet1.velocity.setAngleThis(this.angle - 10 - 90);
        bullet2.velocity.setAngleThis(this.angle - 90);
        bullet3.velocity.setAngleThis(this.angle - 90);
        bullet4.velocity.setAngleThis(this.angle - 90);
        bullet5.velocity.setAngleThis(this.angle + 10 - 90);
        GameObject.gameObjects.add(bullet1);
        GameObject.gameObjects.add(bullet2);
        GameObject.gameObjects.add(bullet3);
        GameObject.gameObjects.add(bullet4);
        GameObject.gameObjects.add(bullet5);

    }


    private void rotate() {
        BufferedImage rotate = SpriteUtils.rotateImageByDegrees(this.image, this.angle);
        this.renderer = new SingleImageRenderer(rotate);
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp <= 0) {
            this.hp = 0;
            this.destroy();
        }
        if (this.hp > 3) this.hp = 3;
    }

    public void upgradeBullet(int level){
        if (this.bulletLevel <= 6 && this.bulletLevel >= 1) {
            this.bulletLevel += level;
        }
        if (this.bulletLevel > 6) this.bulletLevel = 6;
        if (this.bulletLevel < 1) this.bulletLevel = 1;
    }

    @Override
    public void destroy() {
        super.destroy();
        this.hp = 0;
        Explosion explosion = new Explosion();
        explosion.position.setVector2D(this.position.x, this.position.y);
        GameObject.gameObjects.add(explosion);
        GameObject.gameObjects.add(new BackgroundGameOver(false));
    }
}
