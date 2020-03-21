package model;

import java.util.Map;
import java.util.Set;

public class Blinky extends GhostAbstractImpl {
    public Blinky(final Map<Pair<Integer, Integer>, Integer> map, final Set<Pair<Integer, Integer>> setWall) {
        super(map, setWall);
        super.target = new Pair<>(14,14);
    }
}
