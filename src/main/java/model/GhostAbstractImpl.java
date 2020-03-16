package model;

public abstract class GhostAbstractImpl implements MobileEntity {
    protected Pair<Integer,Integer> currentPosition;

    @Override
    public Pair<Integer, Integer> getPosition() {
       return this.currentPosition;
    }

    @Override
    public boolean isEatable() {
 
        return false;
    }

}
