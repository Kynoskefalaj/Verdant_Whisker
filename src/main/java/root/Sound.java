package root;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];
    FloatControl volumeControl;

    public Sound() {

        soundURL[0] = getClass().getResource("/sound/Cave.wav");
        soundURL[1] = getClass().getResource("/sound/Coin 1.wav");
        soundURL[2] = getClass().getResource("/sound/Power Up 1.wav");
        soundURL[3] = getClass().getResource("/sound/Unlock.wav");
        soundURL[4] = getClass().getResource("/sound/Success 1.wav");
        soundURL[5] = getClass().getResource("/sound/Bump.wav");

        // UI Sound Effects
        soundURL[6] = getClass().getResource("/sound/GUI Sound Effects_024.wav");
        soundURL[7] = getClass().getResource("/sound/GUI Sound Effects_025.wav");
        soundURL[8] = getClass().getResource("/sound/GUI Sound Effects_026.wav");

        // Talk Sound Effect
        soundURL[9] = getClass().getResource("/sound/Jump.wav");

        // Enter to the game
        soundURL[10] = getClass().getResource("/sound/Miscellaneous.wav");

    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

            //getting clip volume controller
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume(-30f);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void play() {

        clip.start();
    }
    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {

        clip.stop();
    }

    public void setVolume(float volume) {
        if (volume < volumeControl.getMinimum()) {
            volume = volumeControl.getMinimum();
        }
        if (volume > volumeControl.getMaximum()) {
            volume = volumeControl.getMaximum();
        }

        volumeControl.setValue(volume);
    }
}
