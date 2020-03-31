package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class GhostFactoryImpl implements GhostFactory {
    private final Set<Pair<Integer,Integer>> setWall;
    private final int xMap;
    private final int yMap;
    private Blinky blinky;
    private Inky inky;
    private List<Blinky> listOfBlinky;

    public GhostFactoryImpl(Set<Pair<Integer,Integer>> setWall, Integer xMap, Integer yMap) {
        this.setWall = setWall;
        this.xMap = xMap;
        this.yMap = yMap;
        this.listOfBlinky = new LinkedList<>();
    }
    
    @Override
    public Ghost create(Ghosts ghost) { 
        if (ghost.equals(Ghosts.BLINKY)) {
            blinky = new Blinky(setWall, xMap, yMap);
            listOfBlinky.add(blinky);
            return blinky;
        } else if (ghost.equals(Ghosts.INKY)) {
            if (!listOfBlinky.isEmpty()) {
                inky = new Inky(setWall, xMap, yMap, listOfBlinky.get(0));
                listOfBlinky.remove(0);
                return inky;
            } else {
                throw new IllegalStateException("Must create Blinky before Inky");
            }
        } else if (ghost.equals(Ghosts.PINKY)) {
            return new Pinky(setWall, xMap, yMap);
        } else {
            return new Clyde(setWall, xMap, yMap);
        }
    }
}
