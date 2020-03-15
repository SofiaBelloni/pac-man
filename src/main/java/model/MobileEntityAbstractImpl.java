package model;

/**
 * this class implements a generic Entity that can be moved.
 *
 */
public abstract class MobileEntityAbstractImpl implements MobileEntity {

    private Pair<Integer, Integer> position;

    public MobileEntityAbstractImpl(final Pair<Integer, Integer> position) {
        this.position = position;
    }

    /**
     * @return the position of the MobileEntity
     */
    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    /**
     * @return true if the entity is eatable, false if not
     */
    @Override
    public abstract boolean isEatable();

    /**
     * set the position of the MobileEntity.
     */
    @Override
    public void setPosition(final Pair<Integer, Integer> position) {
        this.position = position;
    }

}
