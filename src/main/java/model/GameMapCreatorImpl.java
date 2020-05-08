package model;

import utils.Pair;

import java.util.Set;

public abstract class GameMapCreatorImpl implements GameMapCreator {
    private Set<Pair<Integer, Integer>> ghostsHouse;
    private Set<Pair<Integer, Integer>> walls;
    private Set<Pair<Integer, Integer>> pills;
    private Pair<Integer, Integer> pacManStartPosition;
    private int xMapSize;
    private int yMapSize;

    @Override
    public Integer getxMapSize() {
        return this.xMapSize;
    }

    @Override
    public Integer getyMapSize() {
        return this.yMapSize;
    }

    @Override
    public Set<Pair<Integer, Integer>> getPills() {
        return this.pills;
    }

    @Override
    public Set<Pair<Integer, Integer>> getWalls() {
        return this.walls;
    }

    @Override
    public Set<Pair<Integer, Integer>> getGhostsHouse() {
        return this.ghostsHouse;
    }

    @Override
    public Pair<Integer, Integer> getPacManStartPosition() {
        return this.pacManStartPosition;
    }

    protected void setGhostsHouse(final Set<Pair<Integer, Integer>> ghostsHouse) {
        this.ghostsHouse = ghostsHouse;
    }

    protected void setWalls(final Set<Pair<Integer, Integer>> walls) {
        this.walls = walls;
    }

    protected void setPills(final Set<Pair<Integer, Integer>> pills) {
        this.pills = pills;
    }

    protected void setPacManStartPosition(final Pair<Integer, Integer> pacManStartPosition) {
        this.pacManStartPosition = pacManStartPosition;
    }

    protected void setxMapSize(final int xMapSize) {
        this.xMapSize = xMapSize;
    }

    protected void setyMapSize(final int yMapSize) {
        this.yMapSize = yMapSize;
    }
}
