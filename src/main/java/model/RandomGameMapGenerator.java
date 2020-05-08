package model;

import utils.Pair;
import utils.PairImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomGameMapGenerator extends GameMapCreatorImpl{
    private static final int X_SIZE = 28;
    private static final int Y_SIZE = 31;
    private static final Pair<Integer, Integer> PAC_MAN_START_POSITION = new PairImpl<>(13, 17);

    public RandomGameMapGenerator() {
        super.setxMapSize(X_SIZE);
        super.setyMapSize(Y_SIZE);
        super.setPacManStartPosition(PAC_MAN_START_POSITION);
        super.setGhostsHouse(this.generateGhostHouse());
        super.setPills(this.generateFixedPills());
        Set<Pair<Integer, Integer>> tmp = this.generateRandomPills();
        super.setWalls(this.generateWalls());
    }

    private Set<Pair<Integer, Integer>> generateFixedPills(){
        final Set<Pair<Integer, Integer>> tmp = new HashSet<>();
        tmp.addAll(this.generateExternalFixedPills());
        tmp.addAll(this.generateInternalFixedPills());
        return tmp;
    }

    private Set<Pair<Integer, Integer>> generateInternalFixedPills (){
        final Set<Pair<Integer, Integer>> tmp = new HashSet<>();
        for (int i = 11; i <= 17; i++){
            tmp.add(new PairImpl<>(9, i));
            tmp.add(new PairImpl<>(18, i));
        }
        for (int i = 9; i <= 18; i++){
            tmp.add(new PairImpl<>(i, 11));
            tmp.add(new PairImpl<>(i, 17));
        }
        return tmp;
    }

    private Set<Pair<Integer, Integer>> generateExternalFixedPills (){
        final Set<Pair<Integer, Integer>> tmp = new HashSet<>();
        for (int i = 1; i < Y_SIZE - 1; i++){
            tmp.add(new PairImpl<>(1, i));
            tmp.add(new PairImpl<>(X_SIZE - 2, i));
        }
        for (int i = 1; i < X_SIZE - 1; i++){
            tmp.add(new PairImpl<>(i, 1));
            tmp.add(new PairImpl<>(i, Y_SIZE - 2));
        }
        return tmp;
    }

    private Set<Pair<Integer, Integer>> generateGhostHouse(){
        final Set<Pair<Integer, Integer>> tmp = new HashSet<>();
        for (int i = 12; i <= 15; i++){
            for (int j = 12; j <= 14; j++){
                tmp.add(new PairImpl<>(i, j));
            }
        }
        return tmp;
    }

    private Set<Pair<Integer, Integer>> generateWalls(){
        final Set<Pair<Integer, Integer>> tmpWalls = new HashSet<>();
        for (int i = 0; i < Y_SIZE; i++){
            for (int j = 0; j < X_SIZE; j++){
                Pair<Integer, Integer> tmp = new PairImpl<>(j, i);
                if (!super.getGhostsHouse().contains(tmp)
                        && !this.getPills().contains(tmp)
                        && !PAC_MAN_START_POSITION.equals(tmp)){
                    tmpWalls.add(tmp);
                }
            }
        }
        return tmpWalls;
    }

    private Set<Pair<Integer, Integer>> generateRandomPills(){
        final Set<Pair<Integer, Integer>> tmp = new HashSet<>();
        final List<Pair<Integer, Integer>> fixedPills = List.copyOf(this.generateInternalFixedPills());
        final Random r = new Random();
        final int numLines = ((r.nextInt()+1) % 6) + 5;
        for (int i = 0; i < numLines; i++){
            int index = r.nextInt(fixedPills.size());
            tmp.addAll(this.generateRandomPillsLine(fixedPills.get(index)));
        }
        return tmp;
    }

    private Set<Pair<Integer, Integer>> generateRandomPillsLine(final Pair<Integer, Integer> startPosition){
        final Set<Pair<Integer, Integer>> tmpSet = new HashSet<>();
        Pair<Integer, Integer> lastPosition = startPosition;
        do{
            Pair<Integer, Integer> tmpPosition = this.generateNextPosition(lastPosition);
            if (!super.getGhostsHouse().contains(tmpPosition)
                    && !tmpSet.contains(tmpPosition)
                    && !PAC_MAN_START_POSITION.equals(tmpPosition)){
                lastPosition = tmpPosition;
                tmpSet.add(lastPosition);
            }
        } while (!this.generateExternalFixedPills().contains(lastPosition));
        return tmpSet;
    }

    private Pair<Integer, Integer> generateNextPosition(final Pair<Integer, Integer> position){
//        final Random r = new Random();
//        final Pair<Integer, Integer> tmp;
//        if (r.nextBoolean()){
//            if (r.nextBoolean()){
//                tmp = new PairImpl<>(position.getX() + 1, position.getY());
//            }else {
//                tmp = new PairImpl<>(position.getX() - 1, position.getY());
//            }
//        } else {
//            if (r.nextBoolean()){
//                tmp = new PairImpl<>(position.getX(), position.getY() + 1);
//            }else {
//                tmp = new PairImpl<>(position.getX(), position.getY() - 1);
//            }
//        }
//        return tmp;
        return new PairImpl<>(position.getX()+1, position.getY());
    }
}
