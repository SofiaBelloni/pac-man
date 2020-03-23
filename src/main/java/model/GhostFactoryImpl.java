package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class GhostFactoryImpl implements GhostFactory {
    private Map<Pair<Integer, Integer>, Integer> map;
    private Set<Pair<Integer, Integer>> setWall;
    private Map<Integer, Blinky> blinkies = new HashMap<>();
    private Inky inky;
    private int i;

    public GhostFactoryImpl(final Map<Pair<Integer, Integer>, Integer> map, final Set<Pair<Integer, Integer>> setWall) {
        this.map = map;
        this.setWall = setWall;
    }

    @Override
    public Ghost create(Ghosts ghost) {  
        if (ghost.equals(Ghosts.BLINKY)) {
            i++;
            blinkies.put(i, new Blinky(this.map, this.setWall));
            return blinkies.get(i);
        } else if (ghost.equals(Ghosts.INKY)) {
            if (!blinkies.isEmpty()) {
                inky = new Inky(this.map, this.setWall, blinkies.get(0));
                blinkies.remove(0);
                return inky;
            } else {
                return null;
            }
        } else if (ghost.equals(Ghosts.PINKY)) {
            return new Pinky(this.map, this.setWall);
        } else {
            return new Clyde(this.map, this.setWall);
        }
    }
}
