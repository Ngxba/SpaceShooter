package game.background;

import game.Setting;
import game.coreGame.GameObject;
import game.coreGame.GameWindow;
import lib.AudioUtils;
import lib.SpriteUtils;
import renderer.SingleImageRenderer;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;

public class BackgroundGamePause extends GameObject {
    private BufferedImage image;
    private Clip backgroundSound;
    private boolean isPlayed;
    private int currentSoundFrame;

    public BackgroundGamePause() {
        this.image = SpriteUtils.loadImage("Asset/Backgrounds/backgroundPause.png");
        this.position.setVector2D(0, -15);
        this.anchor.setVector2D(this.image.getWidth(), this.image.getHeight());
        GameObject.topLayer.add(this);
        this.backgroundSound = AudioUtils.loadSound("Asset/sounds/background_sound.wav");
        this.currentSoundFrame = 0;
    }

    public void run() {
        this.playSound();
        if(GameWindow.isEscapePress && Setting.gameStarted) {
            this.backgroundSound.stop();
            this.renderer = new SingleImageRenderer(image);
            Setting.gamePaused = true;
        }
        else if(Setting.gamePaused && GameWindow.isAnyKeyPress) {
            AudioUtils.replay(this.backgroundSound, this.currentSoundFrame);
            this.renderer = null;
            Setting.gamePaused = false;
        }
    }

    private void playSound() {
        if (!isPlayed && Setting.gameStarted) {
            AudioUtils.replay(this.backgroundSound);
            this.isPlayed = true;
        }
        this.currentSoundFrame = this.backgroundSound.getFramePosition();

        if (this.backgroundSound.getFramePosition() == this.backgroundSound.getFrameLength()) {
            this.isPlayed = false;
        }

        if (Setting.endGame) this.backgroundSound.stop();
    }
}
