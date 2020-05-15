package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.Pair;
import utils.PairImpl;
/*
 * This class is used to load game maps from files
 */
public class GameMapLoaderImpl implements GameMapLoader {
    private static final String GAME_MAP_PATH = "game_maps/";
    private final Integer xMapSize;
    private final Integer yMapSize;
    private final Set<Pair<Integer, Integer>> pills;
    private final Set<Pair<Integer, Integer>> walls;
    private final Set<Pair<Integer, Integer>> ghostsHouse;
    private final Set<Pair<Integer, Integer>> pacManStartPosition;

    public GameMapLoaderImpl(final String mapName) throws IOException {
        this.pills = new HashSet<>();
        this.ghostsHouse = new HashSet<>();
        this.walls = new HashSet<>();
        this.pacManStartPosition = new HashSet<>();
        this.xMapSize = this.getLineSize(this.readMapFile(mapName));
        this.yMapSize = this.getNumLines(this.readMapFile(mapName));
        final List<List<Character>> charList = this.fileToCharList(this.readMapFile(mapName));
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

    private int getNumLines(final BufferedReader br) throws IOException {
        final int value = Math.toIntExact(br.lines().count());
        br.close();
        return value;
    }

    private int getLineSize(final BufferedReader br) throws IOException {
        final String line = br.readLine();
        int value = 0;
        if (line != null) {
            value = line.length();
        }
        br.close();
        return value;
    }

    private List<List<Character>> fileToCharList(final BufferedReader br) throws IOException {
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
                  //TODO
                  break;
              }
          }
      }
   }

    private BufferedReader readMapFile(final String mapName) {
        return new BufferedReader(
                new InputStreamReader(
                        ClassLoader.getSystemResourceAsStream(GAME_MAP_PATH + mapName)));
    }
}
