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

import utils.Player;

/**
 * Class for saving the players' ranking on file and for its recovery from file.
 */
public class RankingManagerFileImpl implements RankingManager {

    private static final String HOME = System.getProperty("user.home");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String DEFAULT_DIR = ".PacMan";
    private static final String DEFAULT_FILE = "PacManScore.json";

    private static final int MAX_SAVED_PLAYERS = 20;

    private final File file;
    private final List<Player> scoreList;

    /**
     * Constructor.
     */
    public RankingManagerFileImpl() {
        this.scoreList = new LinkedList<>(Collections.emptyList());
        final File directory = new File(HOME + SEPARATOR + DEFAULT_DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }
        this.file = new File(HOME + SEPARATOR + DEFAULT_DIR + SEPARATOR + DEFAULT_FILE);
        try {
            if (!this.file.createNewFile() && this.file.length() != 0) {
                this.read();
                this.scoreList.sort((a, b) -> a.compareByScore(b));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final int getHighScore() {
        if (!this.scoreList.isEmpty()) {
            return this.scoreList.get(0).getScore();
        } else {
            return 0;
        }
    }

    @Override
    public final void savePlayer(final String name, final int level, final int score) {
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
    public final List<Player> getAllPlayers() {
        return this.scoreList;
    }

    @Override
    public final void reset() {
        this.scoreList.clear();
        try {
            final OutputStream ostream = new FileOutputStream(this.file);
            final JsonWriter writer = new JsonWriter(new OutputStreamWriter(ostream, "UTF-8"));
            writer.jsonValue("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() throws IOException {
        final Gson gson = new Gson();
        final InputStream istream = new FileInputStream(this.file);
        final JsonReader reader = new JsonReader(new InputStreamReader(istream, "UTF-8"));
        reader.beginArray();
        while (reader.hasNext()) {
            this.scoreList.add(gson.fromJson(reader, Player.class));
        }
        reader.endArray();
        reader.close();
        istream.close();
    }

    private void write() throws IOException {
        final Gson gson = new Gson();
        final OutputStream ostream = new FileOutputStream(this.file);
        final JsonWriter writer = new JsonWriter(new OutputStreamWriter(ostream, "UTF-8"));
        writer.setIndent("  ");
        writer.beginArray();
        this.scoreList.forEach((p) -> gson.toJson(p, Player.class, writer));
        writer.endArray();
        writer.close();
        ostream.close();
    }

}
