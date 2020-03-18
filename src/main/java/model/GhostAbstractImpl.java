package model;


public abstract class GhostAbstractImpl implements Ghost {

    protected Pair<Integer, Integer> position;

    @Override
    public final Pair<Integer, Integer> getPosition() {
       return this.position;
    }
    //metti i return
    public void upFirst( Pair<Integer, Integer> pacManPosition) {
        if (pacManPosition.getY() < position.getY()) {
            position = new Pair<Integer, Integer>(position.getX(), position.getY() - 1);
        }
        if (pacManPosition.getX() > position.getX()) {
            position = new Pair<Integer, Integer>(position.getX() + 1, position.getY());
        }
        if (pacManPosition.getY() > position.getY()) {
            position = new Pair<Integer,Integer>(position.getX(),position.getY()+1);
        }
        if (pacManPosition.getX() < position.getX()) {
            position = new Pair<Integer,Integer>(position.getX()-1,position.getY());
        }
    }
    
    public Pair<Integer, Integer> rightFirst(Pair<Integer, Integer> position, Pair<Integer, Integer> pacManPosition) {
        
        if(pacManPosition.getX()>position.getX()) {
            return position=new Pair<Integer,Integer>(position.getX()+1,position.getY());
        }
        if(pacManPosition.getY()>position.getY()) {
            return position=new Pair<Integer,Integer>(position.getX(),position.getY()+1);
        }
        if(pacManPosition.getX()<position.getX()) {
            return position=new Pair<Integer,Integer>(position.getX()-1,position.getY());
        }
        if(pacManPosition.getY()<position.getY()) {
            return position=new Pair<Integer,Integer>(position.getX(),position.getY()-1);
        }
        return null;
    }
    
    public Pair<Integer, Integer> downFirst(Pair<Integer, Integer> position, Pair<Integer, Integer> pacManPosition) {
        
        if(pacManPosition.getY()>position.getY()) {
            return position=new Pair<Integer,Integer>(position.getX(),position.getY()+1);
        }
        if(pacManPosition.getX()<position.getX()) {
            return position=new Pair<Integer,Integer>(position.getX()-1,position.getY());
        }
        if(pacManPosition.getY()<position.getY()) {
            return position=new Pair<Integer,Integer>(position.getX(),position.getY()-1);
        }
        if(pacManPosition.getX()>position.getX()) {
            return position=new Pair<Integer,Integer>(position.getX()+1,position.getY());
        }
        return null;
    }
    public Pair<Integer, Integer> leftFirst( Pair<Integer, Integer> pacManPosition) {
        if(pacManPosition.getX()<position.getX()) {
            return position=new Pair<Integer,Integer>(position.getX()-1,position.getY());
        }
        if(pacManPosition.getY()<position.getY()) {
            return position=new Pair<Integer,Integer>(position.getX(),position.getY()-1);
        }
        if(pacManPosition.getX()>position.getX()) {
            return position=new Pair<Integer,Integer>(position.getX()+1,position.getY());
        }
        if(pacManPosition.getY()>position.getY()) {
            return position=new Pair<Integer,Integer>(position.getX(),position.getY()+1);
        }
        return null;
    }
   
}

