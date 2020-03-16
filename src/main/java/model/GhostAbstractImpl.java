package model;

public abstract class GhostAbstractImpl implements MobileEntity {
    protected Pair<Integer,Integer> currentPosition;

    @Override
    public Pair<Integer, Integer> getPosition() {
       return this.currentPosition;
public abstract class GhostAbstractImpl implements Ghost {

    protected Pair<Integer, Integer> position;

    @Override
    public final Pair<Integer, Integer> getPosition() {
       return this.position;
    }
    @Override
    public final void setPosition(final Pair<Integer, Integer> position) {
        this.position = position;
    }
}

