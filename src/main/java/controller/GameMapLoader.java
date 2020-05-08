package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.GameMapCreatorImpl;
import utils.Pair;
import utils.PairImpl;

public class GameMapLoader extends GameMapCreatorImpl {
    private static final String GAME_MAP_PATH = "game_maps/";

    public GameMapLoader(final String mapName) throws IOException {
        super.setxMapSize(this.getLineSize(this.readMapFile(mapName)));
        super.setyMapSize(this.getNumLines(this.readMapFile(mapName)));
        super.setGhostsHouse(new HashSet<>());
        super.setPills(new HashSet<>());
        super.setWalls(new HashSet<>());
        final List<List<Character>> charList = this.fileToCharList(this.readMapFile(mapName));
        this.fillSets(charList);
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
                  this.addElement(super.getPills(), j, i);
                  break;
              case '1':
                  this.addElement(super.getWalls(), j, i);
                  break;
              case '2':
                  this.addElement(super.getGhostsHouse(), j, i);
                  break;
              case '3':
                  super.setPacManStartPosition(new PairImpl<>(j, i));
                  break;
              default:
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
