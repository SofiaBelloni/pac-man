package model;

public interface LevelManager {
    /**
     * @return true if the game is inverted (PacMan can eat ghosts), false otherwise
     */
    boolean isGameInverted();
    /*
     * decrease the level time
     * @throws IllegalStateException if the level time is 0
     */
    void decLevelTime();
    /*
     * increase the level
     */
    void nextLevel();
    /**
     * @param value increments scores and partial scores adding the input value
     */
    void incScores(int value);
    /**
     * @return the duration (in seconds) of the level
     */
    int getLevelDuration();
    /**
     * @return the duration (in seconds) of the inverted game
     */
    int getInvertedGameDuration();
    /**
     * @return necessary scores to invert game
     */
    int getScoresToInvertGame();
    /**
     * @return actual scores
     */
    int getScores();
    /**
     * @return partial scores of the level
     */
    int getPartialScores();
    /**
     * @return the level number
     */
    int getLevelNumber();
    /**
     * @return the actual level time (in seconds)
     */
    int getLevelTime();
    /**
     * @return the actual inverted game time (in seconds), if the game is not inverted returns 0
     */
    int getInvertedGameTime();

}
