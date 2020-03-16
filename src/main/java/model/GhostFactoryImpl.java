package model;

<<<<<<< HEAD
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
=======
public class GhostFactoryImpl implements GhostFactory {

    @Override
    public final Ghost blinky() {
        // TODO Auto-generated method stub
        return null;
>>>>>>> fe301ce107eb10424f09caf9437a0e11390a0279
    }

    @Override
    public final Ghost inky() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Ghost pinky() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Ghost clyde() {
        // TODO Auto-generated method stub
        return null;
    }
}
