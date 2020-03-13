package model;

public abstract class GhostAbstractImpl implements MobileEntity {
    protected Pair<Integer, Integer> position;

    @Override
    public Pair<Integer, Integer> getPosition() {
       return this.position;
    }

    @Override
    public boolean isEatable() {
 
        return false;
    }

}
