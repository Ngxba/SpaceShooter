package game.player;

import game.Setting;
import game.coreGame.GameObject;
import lib.SpriteUtils;
import renderer.SingleImageRenderer;

import java.awt.image.BufferedImage;

public class PlayerHPBar extends GameObject {
    private BufferedImage hpImage1;
    private BufferedImage hpImage2;
    private BufferedImage hpImage3;
    private Player player;

    public PlayerHPBar() {
        if (GameObject.getPlayer() != null) this.player = GameObject.getPlayer();
        this.hpImage1 = SpriteUtils.loadImage("Asset/health bar/heart_bar-1.png");
        this.hpImage2 = SpriteUtils.loadImage("Asset/health bar/heart_bar-2.png");
        this.hpImage3 = SpriteUtils.loadImage("Asset/health bar/heart_bar-3.png");
        this.position.setVector2D(10, 10);
        GameObject.topLayer.add(this);
    }

    @Override
    public void run() {
        super.run();
        if (Setting.gameStarted) this.createRenderer();
    }

    private void createRenderer() {
        if (this.player.hp == 1) {
            this.renderer = new SingleImageRenderer(this.hpImage1);
            this.anchor.setVector2D(this.hpImage1.getWidth(), this.hpImage1.getHeight());
        } else if (this.player.hp == 2) {
            this.renderer = new SingleImageRenderer(this.hpImage2);
            this.anchor.setVector2D(this.hpImage1.getWidth(), this.hpImage2.getHeight());
        } else if (this.player.hp == 3) {
            this.renderer = new SingleImageRenderer(this.hpImage3);
            this.anchor.setVector2D(this.hpImage1.getWidth(), this.hpImage3.getHeight());
        } else {
            this.renderer = null;
        }
    }
}
