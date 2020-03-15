package model;


public class PacManImpl extends MobileEntityAbstractImpl implements PacMan {

    //Magari da fare Optional che quando è vuoto sta fermo, oppure aggiungere STOP alle Directions
    private Directions currentDirection; 

    public PacManImpl(final Pair<Integer, Integer> position) {
        super(position);
        this.currentDirection = Directions.RIGHT;
    }

    @Override
    public void setDirection(final Directions direction) {
        this.currentDirection = direction;
    }

    @Override
    public Directions getDirection() {
        return this.currentDirection;
    }

    @Override
    public void nextPosition() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isEatable() {
        return false;
    }



}
