package model;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Pinky extends GhostAbstractImpl {

    Set<Pair<Integer, Integer>> setWall;
    
    public Pinky(Set<Pair<Integer, Integer>> setWall, Integer xMap, Integer yMap) {
        super(setWall, xMap, yMap);
        super.target=new Pair<>(0, yMap);
        super.c = new BravePinkyBehaviour(setWall, xMap, yMap);
        super.initialPosition = new Pair<>(7,6);
    }
        
     public int nextPosition(PacMan pacMan) {
            if (isEatable()) {
                f.runAway(currentPosition, dir);
                currentPosition = f.getNewPosition();
                dir = f.getNewDirection();
            } else {
                   if (isRelaxed) {
                       c.relax(currentPosition, target, dir); 
                        currentPosition = c.getNewPosition();
                        dir = c.getNewDirection();
                        if (currentPosition.equals(target)) {
                            isRelaxed = false;
                        }
                   } else {
                      c.chase(currentPosition, pacMan, dir, Optional.empty());
                      currentPosition = c.getNewPosition();
                      dir = c.getNewDirection();
                   }
            }
            if (currentPosition.equals(pacMan.getPosition())) {
                  return 1;
            } else {
                  return 0;
            }
        }

}
