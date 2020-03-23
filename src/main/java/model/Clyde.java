package model;

import java.util.Map;
import java.util.Set;

public class Clyde extends GhostAbstractImpl {

    public Clyde(final Map<Pair<Integer, Integer>, Integer> map, final Set<Pair<Integer, Integer>> setWall) {
        super(map, setWall);
        super.target = new Pair<>(0,0);
    }

    public Pair<Integer,Integer> targetPosition(PacMan pacMan){
         findPath(currentPosition, pacMan.getPosition());
        if (super.distance >= 8) {
            return pacMan.getPosition();
        } else {
            return super.target;
        }
    }

     public int nextPosition(PacMan pacMan) {
            if (super.isEatable()) {
                super.runAway();
            } else {
                 for (Pair<Integer, Integer> p:map.keySet()) {
                     map.put(p, 1000);
                 }
                   if (isRelaxed) {
                       isRelaxed = relax();
                   } else {
                       findPath(currentPosition, targetPosition(pacMan));
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
