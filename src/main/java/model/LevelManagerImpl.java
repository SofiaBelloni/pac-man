package model;

public class LevelManagerImpl implements LevelManager {
    private final int levelDuration;
    private final int invertedGameDuration;
    private final int scoresToInvertGame;
    private int scores;
    private int partialScores;
    private int levelNumber;
    private int levelTime;
    private int invertedGameTime;

    public LevelManagerImpl(final int levelDuration,
            final int invertedGameDuration,
            final int scoresToInvertGame) {
        this.levelDuration = levelDuration;
        this.invertedGameDuration = invertedGameDuration;
        this.scoresToInvertGame = scoresToInvertGame;
        this.scores = 0;
        this.levelNumber = 1;
        this.levelTime = levelDuration;
        this.invertedGameTime = 0;
    }

    @Override
    public final boolean isGameInverted() {
        return this.invertedGameTime > 0;
    }

    @Override
    public final void decLevelTime() {
        if (this.levelTime > 0) {
            this.levelTime = this.levelTime - 1;
        } else {
            throw new IllegalStateException();
        }
        if (this.invertedGameTime > 0) {
            this.invertedGameTime = this.invertedGameTime - 1;
        }
    }

    @Override
    public final void nextLevel() {
        this.partialScores = 0;
        this.levelNumber = this.levelNumber + 1;
        this.levelTime = this.levelDuration;
        this.invertedGameTime = 0;
    }

    @Override
    public final void incScores(final int value) {
        this.partialScores = this.partialScores + value;
        this.scores = this.scores + value;
        if (this.partialScores >= this.scoresToInvertGame) {
            this.setInvertedGame();
            this.partialScores = 0;
        }
    }

    private void setInvertedGame() {
        this.invertedGameTime = this.invertedGameDuration;
    }

    @Override
    public final int getInvertedGameTime() {
        return this.invertedGameTime;
    }

    @Override
    public final int getLevelDuration() {
        return this.levelDuration;
    }

    @Override
    public final int getInvertedGameDuration() {
        return this.invertedGameDuration;
    }

    @Override
    public final int getScoresToInvertGame() {
        return this.scoresToInvertGame;
    }

    @Override
    public final int getScores() {
        return this.scores;
    }

    @Override
    public final int getPartialScores() {
        return this.partialScores;
    }

    @Override
    public final int getLevelNumber() {
        return this.levelNumber;
    }

    @Override
    public final int getLevelTime() {
        return this.levelTime;
    }
}
