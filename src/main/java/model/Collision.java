package model;

import utils.Pair;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Collision {
    private Optional<Map<Integer, Pair<Integer, Integer>>> ghostsPositions = Optional.empty();
    private Optional<Map<Integer, Pair<Integer, Integer>>> oldGhostsPositions = Optional.empty();
    private Optional<Pair<Integer, Integer>> pacManPosition = Optional.empty();
    private Optional<Pair<Integer, Integer>> oldPacManPosition = Optional.empty();
    /**
     *
     * @param ghostsPositions
     * Sets the actual ghosts positions
     */
    public final void setGhostsPositions(final Map<Integer, Pair<Integer, Integer>> ghostsPositions) {
        if (this.ghostsPositions.isPresent()) {
            this.oldGhostsPositions = Optional.of(this.ghostsPositions.get());
        }
        this.ghostsPositions = Optional.of(ghostsPositions);
    }
    /**
     *
     * @param pacManPosition
     * Sets the actual Pac Man position
     */
    public final void setPacManPosition(final Pair<Integer, Integer> pacManPosition) {
        if (this.pacManPosition.isPresent()) {
            this.oldPacManPosition = Optional.of(this.pacManPosition.get());
        }
        this.pacManPosition = Optional.of(pacManPosition);
    }
    /**
     *
     * @return Actual ghosts positions
     */
    public final Map<Integer, Pair<Integer, Integer>> getGhostsPositions() {
        return this.ghostsPositions.get();
    }
    /**
     *
     * @return Old ghosts positions
     */
    public final Map<Integer, Pair<Integer, Integer>> getOldGhostsPositions() {
        return this.oldGhostsPositions.get();
    }
    /**
     *
     * @return Actual Pac Man position
     */
    public final Pair<Integer, Integer> getPacManPosition() {
        return this.pacManPosition.get();
    }
    /**
     *
     * @return old Pac Man position
     */
    public final Pair<Integer, Integer> getOldPacManPosition() {
        return this.oldPacManPosition.get();
    }
    /**
     *
     * @param pillsPositions
     * @return true il pillPositions contains Pac Man position, false otherwise
     */
    public final boolean checkPacManPillCollision(final Set<Pair<Integer, Integer>> pillsPositions) {
        return pillsPositions.contains(this.pacManPosition.get());
    }
    /**
     *
     * @return A set with the ID of the ghosts that collide with Pac Man
     */
    public final Set<Integer> checkPacManGhostsCollision() {
        Set<Integer> ghosts = new HashSet<>();
        this.ghostsPositions.get().keySet().forEach(x -> {
            if (this.ghostsPositions.get().get(x).equals(this.pacManPosition.get())) {
                ghosts.add(x);
            }
            this.oldGhostsPositions.get().keySet().forEach(y -> {
                if (x.equals(y) && this.ghostsPositions.get().get(x).equals(this.oldPacManPosition.get()) &&
                        this.oldGhostsPositions.get().get(y).equals(this.pacManPosition.get())) {
                    ghosts.add(x);
                }
            });
        });
        return ghosts;
    }
}
