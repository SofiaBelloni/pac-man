package model;

public interface LevelManager {

    boolean isGameInverted();

    void decLevelTime();

    void nextLevel();

    void incScores(int value);

    int getLevelDuration();

    int getInvertedGameDuration();

    int getScoresToInvertGame();

    int getScores();

    int getPartialScores();

    int getLevelNumber();

    int getLevelTime();

    int getInvertedGameTime();

}