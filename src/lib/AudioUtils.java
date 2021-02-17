package lib;

import javax.sound.sampled.*;
import java.io.File;

public class AudioUtils {

    /**
     * For playing sound effect: wav
     * @param audioUrl
     * @return
     */
    public static Clip loadSound(String audioUrl) {
        File soundFile = new File(audioUrl);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }


    public static void replay(Clip clip) {
        clip.setFramePosition(0);
        clip.start();

    }

    public static void replay(Clip clip, int framePosition) {
        clip.setFramePosition(framePosition);
        clip.start();

    }


}
