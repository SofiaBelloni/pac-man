package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * This class is used to manage the map of the game, with walls pills, etc.
 *
 */
public final class GameMapImpl implements GameMap {
    /**
     * This field defines the score of each pill.
     */
    public static final int PILL_POINTS = 100;
    private final Map<Pair<Integer, Integer>, ImmobileEntities> gameMap;
    private final int xMapSize;
    private final int yMapSize;

    private GameMapImpl(final int xMapSize, final int yMapSize, final Set<Pair<Integer, Integer>> walls,
            final Set<Pair<Integer, Integer>> pills, final Set<Pair<Integer, Integer>> ghostsHouse) {
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
        this.gameMap = new HashMap<>();
        walls.forEach(x -> this.gameMap.put(x, ImmobileEntities.WALL));
        pills.forEach(x -> this.gameMap.put(x, ImmobileEntities.PILL));
        ghostsHouse.forEach(x -> this.gameMap.put(x, ImmobileEntities.GHOSTS_HOUSE));
        for (int i = 0; i < xMapSize; i++) {
            for (int j = 0; j < yMapSize; j++) {
                this.gameMap.putIfAbsent(new Pair<Integer, Integer>(i, j), ImmobileEntities.FREE);
            }
        }
    }
    /**
     * This class uses builder pattern to build GameMapImpl objects.
     *
     */
    public static class Builder implements GameMapBuilder {
        private Optional<Integer> xMapSize = Optional.empty();
        private Optional<Integer> yMapSize = Optional.empty();
        private Optional<Set<Pair<Integer, Integer>>> pills = Optional.empty();
        private Optional<Set<Pair<Integer, Integer>>> walls = Optional.empty();
        private Optional<Set<Pair<Integer, Integer>>> ghostsHouse = Optional.empty();

        public Builder(final int xMapSize, final int yMapSize) {
            this.xMapSize = Optional.of(xMapSize);
            this.yMapSize = Optional.of(yMapSize);
        }

        @Override
        public final Builder walls(final Set<Pair<Integer, Integer>> walls) {
            this.walls = Optional.of(walls);
            return this;
        }

        @Override
        public final Builder pills(final Set<Pair<Integer, Integer>> pills) {
            this.pills = Optional.of(pills);
            return this;
        }

        @Override
        public final Builder ghostsHouse(final Set<Pair<Integer, Integer>> ghostsHouse) {
            this.ghostsHouse = Optional.of(ghostsHouse);
            return this;
        }

        @Override
        public final GameMapImpl build() {
            if (this.ghostsHouse.isEmpty() || this.pills.isEmpty() || this.walls.isEmpty()
                    || this.xMapSize.isEmpty() || this.yMapSize.isEmpty()) {
                throw new IllegalStateException();
            }
            return new GameMapImpl(this.xMapSize.get(), this.yMapSize.get(),
                    this.walls.get(), this.pills.get(), this.ghostsHouse.get());
        }
    }

    @Override
    public void removePill(final Pair<Integer, Integer> position) {
        this.gameMap.put(position, ImmobileEntities.FREE);
    }

    @Override
    public Set<Pair<Integer, Integer>> getWallsPositions() {
        Set<Pair<Integer, Integer>> walls = new HashSet<>();
        this.gameMap.entrySet().stream()
        .filter(x -> x.getValue().equals(ImmobileEntities.WALL))
        .forEach(x -> walls.add(x.getKey()));
        return walls;
    }

    @Override
    public Set<Pair<Integer, Integer>> getPillsPositions() {
        Set<Pair<Integer, Integer>> pills = new HashSet<>();
        this.gameMap.entrySet().stream()
        .filter(x -> x.getValue().equals(ImmobileEntities.PILL))
        .forEach(x -> pills.add(x.getKey()));
        return pills;
    }

    @Override
    public Set<Pair<Integer, Integer>> getGhostHousePosition() {
        Set<Pair<Integer, Integer>> ghostHouse = new HashSet<>();
        this.gameMap.entrySet().stream()
        .filter(x -> x.getValue().equals(ImmobileEntities.GHOSTS_HOUSE))
        .forEach(x -> ghostHouse.add(x.getKey()));
        return ghostHouse;
    }

    @Override
    public Set<Pair<Integer, Integer>> getNoWallsPositions() {
        Set<Pair<Integer, Integer>> noWalls = new HashSet<>();
        this.gameMap.entrySet().stream()
        .filter(x -> x.getValue().equals(ImmobileEntities.FREE))
        .forEach(x -> noWalls.add(x.getKey()));
        noWalls.addAll(this.getPillsPositions());
        return noWalls;
    }

    @Override
    public int getxMapSize() {
        return this.xMapSize;
    }

    @Override
    public int getyMapSize() {
        return this.yMapSize;
    }

    enum ImmobileEntities {
        WALL,
        PILL,
        FREE,
        GHOSTS_HOUSE
    }
}
