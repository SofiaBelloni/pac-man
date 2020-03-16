package model;

import java.util.HashSet;
import java.util.Set;

public class GhostFactoryImpl implements GhostFactory {

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
