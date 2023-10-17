package controllertest;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import controller.RankingManager;
import controller.RankingManagerFileImpl;
import utils.Player;
/**
 * JUnit test for {@link RankingManagerFileImpl}.
 */
public class TestFileManager {

    private static final int MAX_SAVED_PLAYERS = 20;

    /**
     *Test for saving new players and reset the ranking.
     *
     */
    @Test
    public void testSaveAndReset() {
        final RankingManager manager = new RankingManagerFileImpl();
        List<Player> scoreList;

        //test reset ranking
        manager.reset();
        scoreList = manager.getAllPlayers();
        Assertions.assertEquals(Collections.EMPTY_LIST, scoreList);
        //test saving new players
        Player player1 = new Player("Luigi", 2, 20000);
        manager.savePlayer("Luigi", 2, 20000);
        Player player2 = new Player("Sofia", 4, 52000);
        manager.savePlayer("Sofia", 4, 52000);
        Player player3 = new Player("Gianni", 5, 51000);
        manager.savePlayer("Gianni", 5, 51000);
        scoreList = manager.getAllPlayers();
        //the ranking has to be sorted
        Assertions.assertEquals(List.of(player2, player3, player1), scoreList);
        //test reset ranking
        manager.reset();
        scoreList = manager.getAllPlayers();
        Assertions.assertEquals(Collections.EMPTY_LIST, scoreList);
    }
    /**
     * Test for the correct high score.
     *
     */
    @Test
    public void testHighScore() {
        final RankingManager manager = new RankingManagerFileImpl();
        List<Player> scoreList;

        //test reset ranking
        manager.reset();
        scoreList = manager.getAllPlayers();
        Assertions.assertEquals(Collections.EMPTY_LIST, scoreList);
        //test if the ranking is empty the maximum score is 0.
        Assertions.assertEquals(0, manager.getHighScore());
        manager.savePlayer("Luigi", 2, 20000);
        manager.savePlayer("Sofia", 4, 52000);
        manager.savePlayer("Gianni", 5, 51000);
        scoreList = manager.getAllPlayers();
      //test if the ranking is empty the maximum score is 0.
        Assertions.assertEquals(52000, manager.getHighScore());
    }

    /**
     * Test for saving at most MAX_SAVED_PLAYERS.
     * It must save the best results.
     *
     */
    @Test
    public void testSaveMoreThanMax() {
        final RankingManager manager = new RankingManagerFileImpl();
        List<Player> scoreList;

        //reset ranking
        manager.reset();
        //add more than MAX_SAVED_PLAYERS 
        manager.savePlayer("Luigi", 2, 20000);
        manager.savePlayer("Sofia", 4, 52000);
        manager.savePlayer("Gianni", 5, 51000);
        manager.savePlayer("Erica", 1, 100);
        manager.savePlayer("Sara", 3, 27000);
        manager.savePlayer("Filippo", 6, 61200);
        manager.savePlayer("Chiara", 4, 52000);
        manager.savePlayer("Luca", 3, 33000);
        manager.savePlayer("Matteo", 6, 59900);
        manager.savePlayer("Stefano", 2, 21000);
        manager.savePlayer("Monica", 1, 2100);
        manager.savePlayer("Guest", 3, 41000);
        manager.savePlayer("Guest", 2, 21000);
        manager.savePlayer("Guest", 3, 34500);
        manager.savePlayer("Laura", 5, 51000);
        manager.savePlayer("Riccardo", 2, 19200);
        manager.savePlayer("Mario", 2, 192000);
        manager.savePlayer("Franco", 3, 31000);
        manager.savePlayer("Marco", 2, 23000);
        manager.savePlayer("Sofia", 3, 39500);
        manager.savePlayer("Gianni", 5, 48500);
        manager.savePlayer("Guest", 1, 1200);
        scoreList = manager.getAllPlayers();
        //test if only the best scores were saved
        Assertions.assertEquals(MAX_SAVED_PLAYERS, scoreList.size());
        //and, as a consequence, that the worst score is not in the ranking.
        Assertions.assertFalse(scoreList.contains(new Player("Erica", 1, 100)));
    }

}
