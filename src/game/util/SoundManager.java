package game.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.File;

public class SoundManager {
    private Clip clip;

    public SoundManager(Clip clip) {
        this.clip = clip;
    }

    public void stop() {
        this.clip.stop();
    }

    public void play(boolean loop) {
        this.clip.setFramePosition(0);
        this.clip.start();
        this.clip.loop(loop ? Clip.LOOP_CONTINUOUSLY : Clip.LOOP_CONTINUOUSLY);
    }

    public void setSound(Clip sound) {
        this.clip = sound;
    }

    public static void play(Clip clip) {
        clip.setFramePosition(0);
        clip.start();
    }

    public static Clip getClip(String filename) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream sample = AudioSystem.getAudioInputStream(new File(filename));
            clip.open(sample);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clip;
    }
}
