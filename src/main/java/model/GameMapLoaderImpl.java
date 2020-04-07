package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GameMapLoaderImpl implements GameMapLoader {
    private final Integer xMapSize;
    private final Integer yMapSize;
    private final Set<Pair<Integer, Integer>> pills;
    private final Set<Pair<Integer, Integer>> walls;
    private final Set<Pair<Integer, Integer>> ghostsHouse;
    private final Set<Pair<Integer, Integer>> pacManStartPosition;

    public GameMapLoaderImpl(final String gameMapPath) throws IOException {
        this.pills = new HashSet<>();
        this.ghostsHouse = new HashSet<>();
        this.walls = new HashSet<>();
        this.pacManStartPosition = new HashSet<>();
        final InputStream in = ClassLoader.getSystemResourceAsStream(gameMapPath);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            int i = 0;
            int j = 0;
            String line = br.readLine();
            this.xMapSize = line.length();
            while (line != null) {
                for (char c : line.toCharArray()) {
                    switch (c) {
                    case '0':
                        this.addElement(this.pills, j, i);
                        break;
                    case '1':
                        this.addElement(this.walls, j, i);
                        break;
                    case '2':
                        this.addElement(this.ghostsHouse, j, i);
                        break;
                    case '3':
                        this.addElement(this.pacManStartPosition, j, i);
                        break;
                    default:
                        break;
                    }
                    j = j + 1;
                }
                i = i + 1;
                j = 0;
                line = br.readLine();
            }
            this.yMapSize = i;
            br.close();
        }
    }

    @Override
    public final Integer getxMapSize() {
        return xMapSize;
    }

    @Override
    public final Integer getyMapSize() {
        return yMapSize;
    }

    @Override
    public final Set<Pair<Integer, Integer>> getPills() {
        return this.pills;
    }

    @Override
    public final Set<Pair<Integer, Integer>> getWalls() {
        return this.walls;
    }

    @Override
    public final Set<Pair<Integer, Integer>> getGhostsHouse() {
        return this.ghostsHouse;
    }

    @Override
    public final Pair<Integer, Integer> getPacManStartPosition() {
        return this.pacManStartPosition.iterator().next();
    }

    private void addElement(final Set<Pair<Integer, Integer>> set, final int x, final int y) {
        set.add(new PairImpl<Integer, Integer>(x, y));
    }
}
