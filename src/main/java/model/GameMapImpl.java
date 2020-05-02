package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import utils.Pair;

/**
 * This class is used to manage the map of the game, with walls pills, etc.
 *
 */
public final class GameMapImpl implements GameMap {
    private static final int DEFAULT_PILL_SCORE = 100;
    private final int pillScore;
    private final Map<TileType, Set<Pair<Integer, Integer>>> gameMap;
    private final int xMapSize;
    private final int yMapSize;
    private final Set<Pair<Integer, Integer>> initialPillsPostion;

    private GameMapImpl(final int xMapSize,
            final int yMapSize,
            final int pillPoints,
            final Set<Pair<Integer, Integer>> walls,
            final Set<Pair<Integer, Integer>> pills,
            final Set<Pair<Integer, Integer>> ghostsHouse,
            final Pair<Integer, Integer> pacManStartPosition) {
        this.pillScore = pillPoints;
        this.xMapSize = xMapSize;
        this.yMapSize = yMapSize;
        this.initialPillsPostion = pills;
        this.gameMap = new HashMap<>();
        this.gameMap.put(TileType.PACMAN_START, Set.of(pacManStartPosition));
        this.gameMap.put(TileType.WALL, walls);
        this.gameMap.put(TileType.PILL, pills);
        this.gameMap.put(TileType.GHOSTS_HOUSE, ghostsHouse);
        this.gameMap.put(TileType.FREE, new HashSet<>());
    }
    /**
     * This class uses builder pattern to build GameMapImpl objects.
     *
     */
    public static class Builder implements GameMapBuilder {

        private Optional<Integer> xMapSize = Optional.empty();
        private Optional<Integer> yMapSize = Optional.empty();
        private Optional<Integer> pillScore = Optional.empty();
        private Optional<Set<Pair<Integer, Integer>>> pills = Optional.empty();
        private Optional<Set<Pair<Integer, Integer>>> walls = Optional.empty();
        private Optional<Set<Pair<Integer, Integer>>> ghostsHouse = Optional.empty();
        private Optional<Pair<Integer, Integer>> pacManStartPosition = Optional.empty();

        @Override
        public final Builder mapSize(final int xMapSize, final int yMapSize) {
            this.xMapSize = Optional.of(xMapSize);
            this.yMapSize = Optional.of(yMapSize);
            return this;
        }

        @Override
        public final Builder pillScore(final int pillScore) {
            this.pillScore = Optional.of(pillScore);
            return this;
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
        public final Builder pacManStartPosition(final Pair<Integer, Integer> position) {
            this.pacManStartPosition = Optional.of(position);
            return this;
        }

        @Override
        public final GameMapImpl build() {
            if (this.ghostsHouse.isEmpty()
                    || this.pills.isEmpty()
                    || this.walls.isEmpty()
                    || this.xMapSize.isEmpty()
                    || this.yMapSize.isEmpty()
                    || this.pacManStartPosition.isEmpty()) {
                throw new IllegalStateException();
            }
            if (this.pillScore.isEmpty()) {
                this.pillScore = Optional.of(DEFAULT_PILL_SCORE);
            }
            return new GameMapImpl(this.xMapSize.get(),
                    this.yMapSize.get(),
                    this.pillScore.get(),
                    this.walls.get(),
                    this.pills.get(),
                    this.ghostsHouse.get(),
                    this.pacManStartPosition.get());
        }
    }

    @Override
    public void removePill(final Pair<Integer, Integer> position) {
        this.gameMap.get(TileType.PILL).remove(position);
        this.gameMap.get(TileType.FREE).add(position);
    }

    @Override
    public Set<Pair<Integer, Integer>> getWallsPositions() {
        return this.gameMap.get(TileType.WALL);
    }

    @Override
    public Set<Pair<Integer, Integer>> getPillsPositions() {
        return this.gameMap.get(TileType.PILL);
    }

    @Override
    public Set<Pair<Integer, Integer>> getGhostHousePosition() {
        return this.gameMap.get(TileType.PACMAN_START);
    }

    @Override
    public Set<Pair<Integer, Integer>> getNoWallsPositions() {
        final Set<Pair<Integer, Integer>> freePositions = new HashSet<>();
        freePositions.addAll(this.getPillsPositions());
        freePositions.addAll(this.gameMap.getOrDefault(TileType.FREE, new HashSet<>()));
        return freePositions;
    }

    @Override
    public int getxMapSize() {
        return this.xMapSize;
    }

    @Override
    public int getyMapSize() {
        return this.yMapSize;
    }

    @Override
    public int getPillScore() {
        return this.pillScore;
    }

    @Override
    public boolean isPill(final Pair<Integer, Integer> position) {
        return this.getPillsPositions().contains(position);
    }

    @Override
    public Pair<Integer, Integer> getPacManStartPosition() {
        return this.gameMap.get(TileType.PACMAN_START).iterator().next();
    }

    @Override
    public void restorePills() {
        this.gameMap.get(TileType.FREE).removeAll(this.initialPillsPostion);
        this.gameMap.put(TileType.PILL, Set.copyOf(this.initialPillsPostion));
    }

    enum TileType {
        WALL,
        PILL,
        GHOSTS_HOUSE,
        PACMAN_START,
        FREE
    }
}
