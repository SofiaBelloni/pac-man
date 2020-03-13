package model;

public class GhostFactoryImpl implements GhostFactory {
    private Pair<Integer, Integer> pacManPosition;


    @Override
    public GhostAbstractImpl blinky() {
       return new GhostAbstractImpl() {

        @Override
        public void setPosition(final Pair<Integer, Integer> position) {
        }
       };
    }

    @Override
    public GhostAbstractImpl inky() {
        return new GhostAbstractImpl() {
            @Override
            public void setPosition(final Pair<Integer, Integer> position) {
            }
       };
    }

    @Override
    public GhostAbstractImpl pinky() {
        return new GhostAbstractImpl() {
            @Override
            public void setPosition(final Pair<Integer, Integer> position) {
            }
       };
    }

    @Override
    public GhostAbstractImpl clyde() {
        return new GhostAbstractImpl() {
            @Override
            public void setPosition(final Pair<Integer, Integer> position) {
            }
       };
    }


}
