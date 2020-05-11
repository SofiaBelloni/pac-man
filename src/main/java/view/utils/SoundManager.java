package view.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * This class represents the Sound Manager of the game.
 * This is a SINGLETON: to get the SounManager instance call SoundManager.getSoundManager().
 */
public class SoundManager {

    private static final String FOLDER = "sounds/";
    private static final String EXTENSION = ".wav";

    private boolean soundEnabled;
    private Map<Sound, Clip> clipMap;

    private static class CreateSingleton {
        private static final SoundManager SOUNDMANAGER_INSTANCE = new SoundManager();
    }

    /**
     * This enumeration represents all the sounds of the game.
     */
    public enum Sound {
        /**
         * The sound for when the application starts.
         */
        INTRO(FOLDER + "pacman_beginning" + EXTENSION),
        /**
         * The sound for when a button is pressed.
         */
        BUTTON(FOLDER + "button" + EXTENSION),
        /**
         * The sound for when a new game is started.
         */
        NEW_GAME(FOLDER + "pacman_intermission" + EXTENSION),
        /**
         * The base sound of the game.
         */
        GAME(FOLDER + "base_sound" + EXTENSION),
        /**
         * The sound for the initial countdown.
         */
        COUNTDOWN(FOLDER + "countdown" + EXTENSION),
        /**
         * The sound for when the game is inverted.
         */
        GAME_INVERTED(FOLDER + "" + EXTENSION),
        /**
         * The sound for when Pac-Man eats a ghost or vice versa.
         */
        EATEN(FOLDER + "eaten" + EXTENSION),
        /**
         * The sound for when you lost a game.
         */
        GAME_OVER(FOLDER + "game_over" + EXTENSION);

        private final String path;

        Sound(final String path) {
            this.path = path;
        }
    }

    //private constructor
    private SoundManager() {
        this.clipMap = new HashMap<>();
        this.soundEnabled = true;
    }

    /**
     * @return The SoundManager
     */
    public static SoundManager getSoundManager() {
        return CreateSingleton.SOUNDMANAGER_INSTANCE;
    }

    /**
     * Enables sound if disabled, and vice versa.
     */
    public void setSoundEnabled() {
        this.soundEnabled = !this.soundEnabled;
    }

    /**
     * Plays the chosen sound.
     * 
     * @param sound The {@link Sound} to reproduced.
     */
    public void play(final Sound sound) {
        if (this.soundEnabled) {
            try {
                this.clipMap.put(sound, this.createClip(sound.path));
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
                this.clipMap.put(sound, this.createClip(sound.path));
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

    /**
     * Stops all the sounds.
     */
    public void stopAll() {
        for (final Map.Entry<Sound, Clip> entry : this.clipMap.entrySet()) {
            if (entry.getValue().isRunning()) {
                entry.getValue().stop();
            }
        }
        this.clipMap.clear();
    }

    private Clip createClip(final String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        final InputStream istream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(istream));
        final Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        audioInputStream.close();
        istream.close();
        return clip;
    }

}
