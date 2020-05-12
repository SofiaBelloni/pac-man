package model;

import utils.Pair;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CollisionImpl {
    private Map<Integer, Pair<Integer, Integer>> ghostsPositions;
    private Map<Integer, Pair<Integer, Integer>> oldGhostsPositions;
    private Pair<Integer, Integer> pacManPosition;
    private Pair<Integer, Integer> oldPacManPosition;

    public CollisionImpl() {
    }

    public void setGhostsPositions(Map<Integer, Pair<Integer, Integer>> ghostsPositions) {
        this.oldGhostsPositions = this.ghostsPositions;
        this.ghostsPositions = ghostsPositions;
    }

    public void setPacManPosition(Pair<Integer, Integer> pacManPosition) {
        this.oldPacManPosition = this.pacManPosition;
        this.pacManPosition = pacManPosition;
    }

    public Map<Integer, Pair<Integer, Integer>> getGhostsPositions() {
        return ghostsPositions;
    }

    public Map<Integer, Pair<Integer, Integer>> getOldGhostsPositions() {
        return oldGhostsPositions;
    }

    public Pair<Integer, Integer> getPacManPosition() {
        return pacManPosition;
    }

    public Pair<Integer, Integer> getOldPacManPosition() {
        return oldPacManPosition;
    }

    public boolean checkPacManPillCollision(Set<Pair<Integer, Integer>> pillsPositions){
        return pillsPositions.contains(this.pacManPosition);
    }

    public Set<Integer> checkPacManGhostsCollision(){
        Set<Integer> ghosts = new HashSet<>();
        this.ghostsPositions.keySet().forEach(x -> {
            if (this.ghostsPositions.get(x).equals(this.pacManPosition)) {
                ghosts.add(x);
            }
            this.oldGhostsPositions.keySet().forEach(y -> {
                if (x.equals(y) && this.ghostsPositions.get(x).equals(this.oldPacManPosition) &&
                        this.oldGhostsPositions.get(y).equals(this.pacManPosition)) {
                    ghosts.add(x);
                }
            });
        });
        return ghosts;
    }
}
