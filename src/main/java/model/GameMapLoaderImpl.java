package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        this.xMapSize = this.getLineSize(gameMapPath);
        this.yMapSize = this.getNumLines(gameMapPath);
        final List<List<Character>> charList = this.fileToCharList(gameMapPath);
        this.fillSets(charList);
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

    private int getNumLines(final String path) throws IOException {
        final InputStream in = ClassLoader.getSystemResourceAsStream(path);
        final BufferedReader br = new BufferedReader(new InputStreamReader(in));
        final int value = Math.toIntExact(br.lines().count());
        br.close();
        in.close();
        return value;
    }

    private int getLineSize(final String path) throws IOException {
        final InputStream in = ClassLoader.getSystemResourceAsStream(path);
        final BufferedReader br = new BufferedReader(new InputStreamReader(in));
        final String line = br.readLine();
        int value = 0;
        if (line != null) {
            value = line.length();
        }
        br.close();
        in.close();
        return value;
    }

    private List<List<Character>> fileToCharList(final String path) throws IOException {
        final InputStream in = ClassLoader.getSystemResourceAsStream(path);
        final BufferedReader br = new BufferedReader(new InputStreamReader(in));
        final List<List<Character>> charList = new ArrayList<>();
        String s = br.readLine();
        while (s != null) {
            final List<Character> tmp = new ArrayList<>();
            s.chars().forEach(c -> {
                tmp.add((char) c);
            });
            charList.add(tmp);
            s = br.readLine();
        }
        br.close();
        in.close();
        return charList;
    }

    private void fillSets(final List<List<Character>> charList) {
      for (int i = 0; i < charList.size(); i++) {
          for (int j = 0; j < charList.get(i).size(); j++) {
              switch (charList.get(i).get(j)) {
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
                  //Must throw an exception (?)
                  break;
              }
          }
      }
   }
}
