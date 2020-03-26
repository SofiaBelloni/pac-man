package model;

import java.util.Map;
import java.util.Set;

public class Clyde extends GhostAbstractImpl {

    public Clyde(final Map<Pair<Integer, Integer>, Integer> map, final Set<Pair<Integer, Integer>> setWall) {
        super(map, setWall);
        super.target = new Pair<>(0,0);
    }

    public Pair<Integer,Integer> targetPosition(PacMan pacMan){
        if (currentPosition.getX()>pacMan.getPosition().getX() - 4 && currentPosition.getX()<pacMan.getPosition().getX() + 4 
            && currentPosition.getY()>pacMan.getPosition().getY() - 4 && currentPosition.getY()<pacMan.getPosition().getY() + 4 ) {
            return super.target;
        } else {
            return pacMan.getPosition();
        }
    }

     public int nextPosition(PacMan pacMan) {
            if (super.isEatable()) {
                super.runAway();
            } else {
                super.min=100000;
                 for (Pair<Integer, Integer> p:map.keySet()) {
                     map.put(p, 1000);
                 }
                   if (isRelaxed) {
                       isRelaxed = relax();
                   } else {
                       findPath(currentPosition, targetPosition(pacMan), super.dir);
                       move(targetPosition(pacMan));
                   }
            }
            if (currentPosition.equals(pacMan.getPosition())) {
                  return 1;
            } else {
                  return 0;
            }

        }
}
