package controller;
/**
 * Class that is used to store the information of the players at the end of a game.
 */
public class Player {

    private final String name;
    private final int level;
    private final int score;

    /**
     * Constructor.
     * @param name
     *      the name of the player
     * @param level
     *      the level reached by the player.
     * @param score
     *      the score reached by the player.
     */
    public Player(final String name, final int level, final int score) {
        this.name = name;
        this.level = level;
        this.score = score;
    }
    /**
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }
    /**
     * @return the level reached by the player.
     */
    public int getLevel() {
        return level;
    }
    /**
     * @return the score reached by the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * This method compare the score of two players.
     * @param anotherPlayer
     *      the Player to compare.
     * @return 
     *      a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
     */
    public int compareTo(final Player anotherPlayer) {
        Integer anotherPlayerScore = anotherPlayer.score;
        Integer thisPlayerScore = this.score;
        return anotherPlayerScore.compareTo(thisPlayerScore);
    }

    @Override
    public String toString() {
        return "Player [name=" + name + ", level=" + level + ", score=" + score + "]";
    }

}
