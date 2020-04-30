package controller;
/**
 * Class that is used to store the information of a player at the end of a game.
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
    public int compareByScore(final Player anotherPlayer) {
        return Integer.compare(anotherPlayer.score, this.score);
    }

    /**
     * This method compare the level of two players.
     * @param anotherPlayer
     *      the Player to compare.
     * @return 
     *      a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
     */
    public int compareByLevel(final Player anotherPlayer) {
        return Integer.compare(anotherPlayer.level, this.level);
    }

    @Override
    public String toString() {
        return "Player [name=" + name + ", level=" + level + ", score=" + score + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + level;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + score;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Player other = (Player) obj;
        if (level != other.level) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (score != other.score) {
            return false;
        }
        return true;
    }

}
