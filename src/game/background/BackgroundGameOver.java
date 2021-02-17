package game.background;

import game.Setting;
import lib.AudioUtils;
import lib.FrameCounter;
import game.coreGame.GameObject;
import game.coreGame.GameWindow;
import lib.SpriteUtils;
import renderer.SingleImageRenderer;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;

public class BackgroundGameOver extends GameObject {
    private BufferedImage image;
    private FrameCounter activeCounter;
    private FrameCounter resetCounter;
    private Clip endSound;

    public BackgroundGameOver(boolean victory) {
        this.createRenderer(victory);
        this.createSound(victory);
        this.activeCounter = new FrameCounter(180);
        this.resetCounter = new FrameCounter(300);
        this.active = false;
        this.position.setVector2D(512 * 0.5f, 512 * 0.5f);
        GameObject.topLayer.add(this);
    }

    private void createSound(boolean victory) {
        if (victory) this.endSound = AudioUtils.loadSound("Asset/sounds/Jingle_Win_00.wav");
        else this.endSound = AudioUtils.loadSound("Asset/sounds/Jingle_Lose_00.wav");
    }

    private void createRenderer(boolean victory) {
        if (victory) {
            this.image = SpriteUtils.loadImage("Asset/Backgrounds/Victory.png"); // thay img win game
            this.renderer = new SingleImageRenderer(this.image);
        } else {
            this.image = SpriteUtils.loadImage("Asset/Backgrounds/Game-over.png"); // thay img thua game
            this.renderer = new SingleImageRenderer(this.image);
        }
    }

    @Override
    public void run() {
        super.run();
        if (this.activeCounter.run()) {
            this.active = true;
            Setting.endGame = true;
        }
        if (this.resetCounter.run() && GameWindow.isAnyKeyPress && this.active) {
            GameObject.resetGame();
            Setting.endGame = false;
            this.resetCounter.reset();
        }
        if (this.active) this.endSound.start();
    }

}
