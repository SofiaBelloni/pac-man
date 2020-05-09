package view.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * This class represents the Sound Manager of the game.
 */
public class SoundManager {

    private static final String FOLDER = "sounds/";

    private boolean soundEnabled;
    private Map<Sound, Clip> clipMap;

    /**
     * This enumeration represents all the sounds of the game.
     */
    public enum Sound {
        /**
         * The sound for when the application starts.
         */
        START(FOLDER + ""),
        /**
         * The sound for when a button is pressed.
         */
        BUTTON(FOLDER + ""),
        /**
         * The sound for when a new game is started.
         */
        NEW_GAME(FOLDER + ""),
        /**
         * The sound for when the game is inverted.
         */
        GAME_INVERTED(FOLDER + ""),
        /**
         * The sound for when Pac-Man eats a ghost.
         */
        GHOST_EATEN(FOLDER + ""),
        /**
         * The sound for when Pac-Man is killed.
         */
        DEATH(FOLDER + ""),
        /**
         * The sound for when you lost a game.
         */
        GAME_OVER(FOLDER + "");

        private final String path;

        Sound(final String path) {
            this.path = path;
        }
    }

    /**
     * Constructor.
     */
    public SoundManager() {
        this.clipMap = new HashMap<>();
        this.soundEnabled = true;
    }

    /**
     * Enables sound if disabled, and vice versa.
     */
    public final void setSoundEnabled() {
        this.soundEnabled = !this.soundEnabled;
    }

    /**
     * Plays the chosen sound.
     * 
     * @param sound The {@link Sound} to reproduced.
     */
    public final void play(final Sound sound) {
        if (this.soundEnabled) {
            try {
                this.clipMap.putIfAbsent(sound, this.createClip(sound));
                this.clipMap.get(sound).start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Starts looping the chosen sound.
     * 
     * @param sound The {@link Sound} to reproduced.
     */
    public void playWithLoop(final Sound sound) {
        if (this.soundEnabled) {
            try {
                this.clipMap.putIfAbsent(sound, this.createClip(sound));
                this.clipMap.get(sound).loop(Clip.LOOP_CONTINUOUSLY);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stops the chosen sound.
     * 
     * @param sound The {@link Sound} to stop.
     */
    public void stopSound(final Sound sound) {
        if (this.clipMap.get(sound).isRunning()) {
            this.clipMap.get(sound).stop();
        }
    }

    private Clip createClip(final Sound sound) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        InputStream istream = this.getClass().getClassLoader()
                    .getResourceAsStream(sound.path);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(istream));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        return clip;
    }

}
