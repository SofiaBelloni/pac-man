package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class GhostFactoryImpl implements GhostFactory {
    private final Map<Pair<Integer,Integer>, Integer> map;
    private final Set<Pair<Integer,Integer>> setWall;
    private final Map<Integer, Blinky> blinkies=new HashMap<>();
    private Inky inky;
    private int i = 0;
    
    public GhostFactoryImpl(Map<Pair<Integer,Integer>,Integer> map,Set<Pair<Integer,Integer>> setWall) {
        this.map=map;
        this.setWall=setWall;
    }

    @Override
    public Ghost create(Ghosts ghost) { 
        if (ghost.equals(Ghosts.BLINKY)) {
            i++;
            blinkies.put(i, new Blinky(this.map, this.setWall));
            return blinkies.get(i);
        }else if (ghost.equals(Ghosts.INKY)) {
            if (!blinkies.isEmpty()) {
                inky = new Inky(this.map, this.setWall, blinkies.get(i));
                blinkies.remove(0);
                return inky;
            } else {
                throw new IllegalStateException("Must create a Blinky ghost before");
            }
        }else if (ghost.equals(Ghosts.PINKY)) {
            return new Pinky(this.map, this.setWall);
        } else {
            return new Clyde(this.map, this.setWall);
        }
    }
}
