package game.item;

import game.coreGame.GameObject;
import lib.SpriteUtils;
import lib.Vector2D;
import game.player.Player;
import game.player.PlayerShield;
import physics.BoxCollider;
import renderer.SingleImageRenderer;

import java.awt.image.BufferedImage;

public class ShieldItem extends GameObject {
    private BufferedImage image;
    private BoxCollider hitBox;
    Player player;

    public ShieldItem() {
        if (GameObject.getPlayer() != null) this.player = GameObject.getPlayer();
        this.image = SpriteUtils.resize("Asset/items/Armor_Bonus.png", 35, 35);
        this.renderer = new SingleImageRenderer(this.image);
        this.boxCollider = new BoxCollider(this.position, 30, 30);
        this.hitBox = new BoxCollider(this.position, 100, 100);
        this.velocity.setVector2D(0, 2);
        GameObject.midLayer.add(this);

    }

    @Override
    public void run() {
        super.run();
        this.checkIntersect();
        this.limitPosition();
        this.goToPlayer();
    }

    private void limitPosition() {
        if (this.position.y > 650) {
            this.destroy();
        }
    }

    private void goToPlayer() {
        if (this.hitBox.intersects(this.player.boxCollider) && this.active && this.player.active && !this.player.haveShield) {
            Vector2D toPlayer = this.player.position.subtract(this.position.x, this.position.y);
            toPlayer.setLengthThis(5);
            this.velocity.setVector2D(toPlayer.x, toPlayer.y);
        } else {
            this.velocity.setVector2D(0, 2);
        }

    }

    private void checkIntersect() {
        if (this.boxCollider.intersects(this.player.boxCollider) && this.active && this.player.active && !this.player.haveShield) {
            this.destroy();
            GameObject.gameObjects.add(new PlayerShield());
        }
    }
}
