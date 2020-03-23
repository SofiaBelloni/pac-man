package model;

import java.util.Map;
import java.util.Set;


public class GhostFactoryImpl implements GhostFactory {
    private Map<Pair<Integer, Integer>, Integer> map;
    private Set<Pair<Integer, Integer>> setWall;

    public GhostFactoryImpl(final Map<Pair<Integer, Integer>, Integer> map, final Set<Pair<Integer, Integer>> setWall) {
        this.map = map;
        this.setWall = setWall;
    }

    @Override
    public Ghost create(final MobileEntities ghost) { 
        if (ghost.equals(MobileEntities.BLINKY)) {
            return new Blinky(this.map, this.setWall);
        } else if (ghost.equals(MobileEntities.INKY)) {
            return new Inky(this.map, this.setWall);
        } else if (ghost.equals(MobileEntities.PINKY)) {
            return new Pinky(this.map, this.setWall);
        } else {
            return new Clyde(this.map, this.setWall);
        }
    }
}
