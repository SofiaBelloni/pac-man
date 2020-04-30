package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

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
        this.scoreList = new LinkedList<>(Collections.emptyList());
        try {
            if (!this.file.createNewFile()) {
                this.read();
                this.scoreList.sort((a, b) -> a.compareByScore(b));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        this.scoreList.sort((a, b) -> a.compareByScore(b));
        if (this.scoreList.size() > MAX_SAVED_PLAYERS) {
            this.scoreList.remove(this.scoreList.size() - 1);
        }
        try {
            this.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Player> getAllPlayers() {
        return this.scoreList;
    }

    private void read() throws IOException {
        Gson gson = new Gson();
        InputStream istream = new FileInputStream(this.file);
        JsonReader reader = new JsonReader(new InputStreamReader(istream, "UTF-8"));
        reader.beginArray();
        while (reader.hasNext()) {
            this.scoreList.add(gson.fromJson(reader, Player.class));
        }
        reader.endArray();
        reader.close();
    }

    private void write() throws IOException {
        Gson gson = new Gson();
        OutputStream ostream = new FileOutputStream(this.file);
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(ostream, "UTF-8"));
        writer.setIndent("  ");
        writer.beginArray();
        this.scoreList.forEach((p) -> gson.toJson(p, Player.class, writer));
        writer.endArray();
        writer.close();
    }

}
