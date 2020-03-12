package model;

/**
 * this class implements a generic Entity that can't be moved.
 *
 */
public abstract class ImmobileEntityImpl implements Entity {
    private final Pair<Integer, Integer> position;
    public ImmobileEntityImpl(final Pair<Integer, Integer> position) {
        this.position = position;
    }

    @Override
    public final Pair<Integer, Integer> getPosition() {
        return this.position;
    }
}
