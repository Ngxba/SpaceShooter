package game.background;

import game.Setting;
import game.coreGame.GameObject;
import game.coreGame.GameWindow;
import game.enemy.AddObstacle;
import game.player.Player;
import game.player.PlayerHPBar;
import lib.FrameCounter;
import lib.SpriteUtils;
import renderer.SingleImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundLoading extends GameObject {
    private BufferedImage image;
    private BufferedImage loadingBar;
    private FrameCounter loadingCounter;
    private int barWidth;
    private boolean objectsAdded;

    public BackgroundLoading() {
        this.image = SpriteUtils.loadImage("Asset/Backgrounds/loading.png");
        this.loadingBar = SpriteUtils.loadImage("Asset/Backgrounds/PreloadingBar.png");
        this.renderer = new SingleImageRenderer(this.image);
        this.position.setVector2D(512 * 0.5f, 512 * 0.5f);
        this.loadingCounter = new FrameCounter(2);
        this.barWidth = 0;
        this.objectsAdded = false;
        GameObject.gameObjects.add(new Background_fore());
        GameObject.topLayer.add(this);
    }

    @Override
    public void run() {
        super.run();
        this.loading();
        this.addObject();
    }

    private void addObject() {
        if (!this.objectsAdded) {
            GameObject.gameObjects.add(new Background_back());
            GameObject.gameObjects.add(new Background_front());
            GameObject.gameObjects.add(new Player());
            GameObject.gameObjects.add(new PlayerHPBar());
            GameObject.gameObjects.add(new AddObstacle());
            GameObject.gameObjects.add(new BackgroundGamePause());
            this.objectsAdded = true;
        }
    }

    private void loading() {
        if (this.barWidth < this.loadingBar.getWidth() && this.loadingCounter.run()) {
            this.barWidth += 10;
            this.loadingCounter.reset();
        } else if (this.barWidth >= this.loadingBar.getWidth()){
            this.destroy();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        Setting.gameStarted = true;
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.drawImage(this.loadingBar, 106, 296, this.barWidth, this.loadingBar.getHeight(), null);
    }
}
