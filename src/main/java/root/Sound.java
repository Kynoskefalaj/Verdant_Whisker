package root;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public URL mainTheme, coinSE, powerUpSE, unlockSE, successSE, bumpSE,
            talkSE, enterGameSE, swordSlashSE, monsterDeath, hurtSE, exhaustedSE,
            gui1SE, gui2SE, gui3SE, gui4SE,
            mysterySE, cursorSE, explosionSE, projectileCastSE, evilLaughSE, hitBushSE,
            gameOverSE, stairsSE;
    public URL[] uiSounds;

    public Sound() {

        mainTheme = getClass().getResource("/sound/Cave.wav");
        coinSE = getClass().getResource("/sound/Coin 1.wav");
        powerUpSE = getClass().getResource("/sound/Power Up 1.wav");
        unlockSE = getClass().getResource("/sound/Unlock.wav");
        successSE = getClass().getResource("/sound/Success 1.wav");
        bumpSE = getClass().getResource("/sound/Bump.wav");

        // UI Sound Effects
        gui1SE = getClass().getResource("/sound/GUI Sound Effects_024.wav");
        gui2SE = getClass().getResource("/sound/GUI Sound Effects_025.wav");
        gui3SE = getClass().getResource("/sound/GUI Sound Effects_026.wav");
        gui4SE = getClass().getResource("/sound/GUI Sound Effects_004.wav");

        // Talk Sound Effect
        talkSE = getClass().getResource("/sound/Jump.wav");

        // Enter to the game
        enterGameSE = getClass().getResource("/sound/Miscellaneous.wav");

        // Sword Slash
        swordSlashSE = getClass().getResource("/sound/Sword_Slash.wav");

        monsterDeath = getClass().getResource("/sound/Water_Splash.wav");

        hurtSE = getClass().getResource("/sound/Hurt.wav");

        exhaustedSE = getClass().getResource("/sound/Cancel.wav");

        mysterySE = getClass().getResource("/sound/GUI Sound Effects_028.wav");

        explosionSE = getClass().getResource("/sound/Explosion.wav");

        projectileCastSE = getClass().getResource("/sound/Gun.wav");

        evilLaughSE = getClass().getResource("/sound/Evil_Laugh.wav");

        hitBushSE = getClass().getResource("/sound/cuttree.wav");

        gameOverSE = getClass().getResource("/sound/Game Over.wav");

        stairsSE = getClass().getResource("/sound/stairs.wav");

        cursorSE = gui1SE;

        uiSounds = new URL[]{gui1SE, gui2SE, gui3SE};

    }

    public void setFile(URL url) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(ais);

            //getting clip volume controller
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume(-12f);
            checkVolume();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void checkVolume() {

        switch (volumeScale) {
            case 0: volume = -80f; break;
            case 1: volume = -40f; break;
            case 2: volume = -22f; break;
            case 3: volume = -8f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
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
        if (volume < fc.getMinimum()) {
            volume = fc.getMinimum();
        }
        if (volume > fc.getMaximum()) {
            volume = fc.getMaximum();
        }

        fc.setValue(volume);
    }
}
