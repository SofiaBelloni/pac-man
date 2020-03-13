package model;

public class GhostFactoryImpl implements GhostFactory {
    
    private Pair<Integer, Integer> pacManPosition;
    private Pair<Integer, Integer> initialPosition;


    @Override
    public Ghost blinky() {
        
       return new Ghost() {
           
           private Directions direction=Directions.UP;
           private Pair<Integer,Integer> position=initialPosition;
           
           
            @Override
            public void setPosition(Pair<Integer, Integer> position) {
               
            }
    
            @Override
            public void setDirection(Directions direction) {
                // TODO Auto-generated method stub
                
            }
    
            @Override
            public Directions getDirection() {
                
                return this.direction;
            }
    
            @Override
            public Pair<Integer, Integer> getPosition() {
                
                return this.position;
            }
    
            @Override
            public boolean isEatable() {
                
                return false;
            }
               
       };
    }

    @Override
    public Ghost inky() {
        // TODO Auto-generated method stub
        return new Ghost() {

            @Override
            public void setPosition(Pair<Integer, Integer> position) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void setDirection(Directions direction) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public Directions getDirection() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Pair<Integer, Integer> getPosition() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public boolean isEatable() {
               
                return false;
            }
            
        };
    }

    @Override
    public Ghost pinky() {
        // TODO Auto-generated method stub
        return new Ghost() {

            @Override
            public void setPosition(Pair<Integer, Integer> position) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void setDirection(Directions direction) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public Directions getDirection() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Pair<Integer, Integer> getPosition() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public boolean isEatable() {
               
                return false;
            }
            
        };
    }

    @Override
    public Ghost clyde() {
        // TODO Auto-generated method stub
        return new Ghost() {

            @Override
            public void setPosition(Pair<Integer, Integer> position) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void setDirection(Directions direction) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public Directions getDirection() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Pair<Integer, Integer> getPosition() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public boolean isEatable() {
            
                return false;
            }
            
        };
    }

   

}
