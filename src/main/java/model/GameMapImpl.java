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
    private final Map<PairImpl<Integer, Integer>, ImmobileEntities> gameMap;
    private final int xMapSize;
    private final int yMapSize;

    private GameMapImpl(final int xMapSize, final int yMapSize, final Set<PairImpl<Integer, Integer>> walls,
            final Set<PairImpl<Integer, Integer>> pills, final Set<PairImpl<Integer, Integer>> ghostsHouse) {
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
        this.gameMap = new HashMap<>();
        walls.forEach(x -> this.gameMap.put(x, ImmobileEntities.WALL));
        pills.forEach(x -> this.gameMap.put(x, ImmobileEntities.PILL));
        ghostsHouse.forEach(x -> this.gameMap.put(x, ImmobileEntities.GHOSTS_HOUSE));
        for (int i = 0; i < xMapSize; i++) {
            for (int j = 0; j < yMapSize; j++) {
                this.gameMap.putIfAbsent(new PairImpl<Integer, Integer>(i, j), ImmobileEntities.FREE);
            }
        }
    }
    /**
     * This class uses builder pattern to build GameMapImpl objects.
     *
     */
    public static class Builder implements GameMapBuilder {
        private final int xMapSize;
        private final int yMapSize;
        private Optional<Set<PairImpl<Integer, Integer>>> pills = Optional.empty();
        private Optional<Set<PairImpl<Integer, Integer>>> walls = Optional.empty();
        private Optional<Set<PairImpl<Integer, Integer>>> ghostsHouse = Optional.empty();

        public Builder(final int xMapSize, final int yMapSize) {
            this.xMapSize = xMapSize;
            this.yMapSize = yMapSize;
        }

        @Override
        public final Builder walls(final Set<PairImpl<Integer, Integer>> walls) {
            this.walls = Optional.of(walls);
            return this;
        }

        @Override
        public final Builder pills(final Set<PairImpl<Integer, Integer>> pills) {
            this.pills = Optional.of(pills);
            return this;
        }

        @Override
        public final Builder ghostsHouse(final Set<PairImpl<Integer, Integer>> ghostsHouse) {
            this.ghostsHouse = Optional.of(ghostsHouse);
            return this;
        }

        @Override
        public final GameMapImpl build() {
            if (this.ghostsHouse.isEmpty() || this.pills.isEmpty() || this.walls.isEmpty()) {
                throw new IllegalStateException();
            }
            return new GameMapImpl(this.xMapSize, this.yMapSize,
                    this.walls.get(), this.pills.get(), this.ghostsHouse.get());
        }
    }

    @Override
    public void removePill(final PairImpl<Integer, Integer> position) {
        this.gameMap.put(position, ImmobileEntities.FREE);
    }

    @Override
    public Set<PairImpl<Integer, Integer>> getWallsPositions() {
        final Set<PairImpl<Integer, Integer>> walls = new HashSet<>();
        this.gameMap.entrySet().stream()
        .filter(x -> x.getValue().equals(ImmobileEntities.WALL))
        .forEach(x -> walls.add(x.getKey()));
        return walls;
    }

    @Override
    public Set<PairImpl<Integer, Integer>> getPillsPositions() {
        final Set<PairImpl<Integer, Integer>> pills = new HashSet<>();
        this.gameMap.entrySet().stream()
        .filter(x -> x.getValue().equals(ImmobileEntities.PILL))
        .forEach(x -> pills.add(x.getKey()));
        return pills;
    }

    @Override
    public Set<PairImpl<Integer, Integer>> getGhostHousePosition() {
        final Set<PairImpl<Integer, Integer>> ghostHouse = new HashSet<>();
        this.gameMap.entrySet().stream()
        .filter(x -> x.getValue().equals(ImmobileEntities.GHOSTS_HOUSE))
        .forEach(x -> ghostHouse.add(x.getKey()));
        return ghostHouse;
    }

    @Override
    public Set<PairImpl<Integer, Integer>> getNoWallsPositions() {
        final Set<PairImpl<Integer, Integer>> noWalls = new HashSet<>();
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
