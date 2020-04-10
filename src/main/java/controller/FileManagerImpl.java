package controller;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for saving the players' ranking on file and for its recovery from file.
 */
public class FileManagerImpl implements FileManager {

    private static final String HOME = System.getProperty("user.home");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String DEFAULT_FILE = "PacManScore.json";

    private static final int MAX_SAVED_PLAYERS = 5;

    private final File file;
    private List<Player> scoreList;
    
    /**
     * Constructor.
     */
    public FileManagerImpl() {
        this.file = new File(HOME + SEPARATOR + DEFAULT_FILE);
        this.scoreList = new LinkedList<Player>(Collections.emptyList());
        this.readFile();
        this.scoreList.sort((a, b) -> a.compareTo(b));
    }

    @Override
    public int getHighScore() {
        if (!this.scoreList.isEmpty()) {
            return this.scoreList.get(0).getScore();
        } else {
            return 0;
        }
    }

    @Override
    public void savePlayer(final String name, final int level, final int score) {
        this.scoreList.add(new Player(name, level, score));
        this.scoreList.sort((a, b) -> a.compareTo(b));
        if (this.scoreList.size() > MAX_SAVED_PLAYERS) {
            this.scoreList.remove(this.scoreList.size() - 1);
        }
        this.writeFile();
    }

    @Override
    public List<Player> getAllPlayers() {
        return this.scoreList;
    }

    private void readFile() {
        // TODO Auto-generated method stub
    }
    
    private void writeFile() {
        // TODO Auto-generated method stub
    }
}
