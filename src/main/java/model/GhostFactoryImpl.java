package model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class GhostFactoryImpl implements GhostFactory {
    @Override
    public final Ghost blinky() {
        return new GhostAbstractImpl() {
            @Override
            public boolean isEatable() {
                return false;
            }
            //mi serve la posizione di pacMan in input in nextposition
            @Override
            public void nextPosition() {
                if(!isEatable()) {
                    Random r=new Random();
                    if (!this.getPosition().equals(targetPosition)) {
                        switch(r.nextInt(4)) { 
                        case 0:
                        upFirst(targetPosition);
                        break;
                        case 1:
                        rightFirst(targetPosition);
                        break;
                        case 2:
                        downFirst(targetPosition);
                        break;
                        case 3:
                        leftFirst(targetPosition);
                        break;
                        }
                } else {
                    
                }
            }
        };
    }

    @Override
    public final Ghost inky() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Ghost pinky() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Ghost clyde() {
        // TODO Auto-generated method stub
        return null;
    }
}
