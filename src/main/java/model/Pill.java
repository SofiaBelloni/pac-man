package model;

/*
 *This class represents one of the pills that can be eaten by Pac-Man
 */
public class Pill extends ImmobileEntityImpl {
    /*
     * value of the pill to be added to the score
     */
    public static final int POINTS = 10;

    public Pill(final Pair<Integer, Integer> position) {
        super(position);
    }

    @Override
    public final boolean isEatable() {
        return true;
    }

}
