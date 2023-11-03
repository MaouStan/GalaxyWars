package game.util;

import javax.sound.sampled.*;

import java.io.File;

public class SoundManager {
    private Clip clip;
    private static float volume = 100.0f; // volume in decibels percent

    public SoundManager(Clip clip) {
        this.clip = clip;
    }

    public void stop() {
        this.clip.stop();
    }

    public void play(boolean loop) {
        setVolumeToSound(this.clip);
        this.clip.setFramePosition(0);
        this.clip.start();
        this.clip.loop(loop ? Clip.LOOP_CONTINUOUSLY : 0);
    }

    public void setSound(Clip sound) {
        this.clip = sound;
    }

    public static void play(Clip clip) {
        setVolumeToSound(clip);
        clip.setFramePosition(0);
        clip.start();
    }

    private static void setVolumeToSound(Clip clip) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(volume / 100.0) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }

    public static void setVolumeNumber(float volume) {
        SoundManager.volume = volume;
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