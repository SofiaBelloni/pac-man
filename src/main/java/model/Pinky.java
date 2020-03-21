package model;

import java.util.Map;
import java.util.Set;

public class Pinky extends GhostAbstractImpl {

    public Pinky(final Map<Pair<Integer, Integer>, Integer> map, final Set<Pair<Integer, Integer>> setWall) {
        super(map, setWall);
        super.target = new Pair<>(0, 0);
    }

}
