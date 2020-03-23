package model;

import java.util.Map;
import java.util.Set;

public class Blinky extends GhostAbstractImpl {
    public Blinky(final Map<Pair<Integer, Integer>, Integer> map, final Set<Pair<Integer, Integer>> setWall) {
        super(map, setWall);
        super.target = new Pair<>(MAX, MAX);
    }

    public Pair<Integer,Integer> targetPosition(PacMan pacMan){
        return pacMan.getPosition();

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
