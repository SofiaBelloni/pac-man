package model;

/*
 *This class represent a wall (an obstacle) which cannot be exceeded
 */
public class Wall extends ImmobileEntityImpl {

    public Wall(final Pair<Integer, Integer> position) {
        super(position);
    }

    @Override
    public final boolean isEatable() {
        return false;
    }
}
