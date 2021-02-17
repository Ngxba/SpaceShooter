package game.enemy;

import game.Setting;
import game.coreGame.GameObject;
import game.item.BulletItem;
import game.item.HPItem;
import game.item.ShieldItem;
import game.player.Player;
import physics.BoxCollider;

public class Enemy extends GameObject {
    int hp;
    Player player;
    public boolean isBoss;
    public boolean isBullet;

    public Enemy(int width, int height) {
        this.hp = 100;
        if (GameObject.getPlayer() != null) this.player = GameObject.getPlayer();
        this.boxCollider = new BoxCollider(this.position, width, height);
        this.isBoss = false;
        this.isBullet = false;
        GameObject.allEnemies.add(this);
        GameObject.midLayer.add(this);
    }

    @Override
    public void run() {
        super.run();
        this.checkIntersect();
    }

    private void checkIntersect() {
        if (this.boxCollider.intersects(this.player.boxCollider) && this.active && this.player.active && !Setting.bossDied) {
            if (!this.isBoss) this.destroy();
            if (!this.player.haveShield) {
                this.player.upgradeBullet(-1);
                this.player.takeDamage(1);
                if (this.isBoss) this.player.destroy();
            }

        }
    }

    @Override
    public void destroy() {
        super.destroy();
        this.spawnItem();

    }

    private void spawnItem() {
        int random = (int) (Math.random() * ((10 - 1) + 1)) + 1;
        if (random <= 2) {
            HPItem hpItem = new HPItem();
            hpItem.position.setVector2D(this.position.x, this.position.y);
            GameObject.gameObjects.add(hpItem);
        } else if (random <= 4) {
            BulletItem bulletItem = new BulletItem();
            bulletItem.position.setVector2D(this.position.x, this.position.y);
            GameObject.gameObjects.add(bulletItem);
        } else if (random <= 6 && !this.player.haveShield) {
            ShieldItem shieldItem = new ShieldItem();
            shieldItem.position.setVector2D(this.position.x, this.position.y);
            GameObject.gameObjects.add(shieldItem);
        }
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp <= 0) {
            this.destroy();
        }
    }


}
