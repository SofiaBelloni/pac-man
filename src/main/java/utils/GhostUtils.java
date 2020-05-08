package utils;

import javafx.scene.image.ImageView;
import model.Directions;
import model.Ghosts;

public class GhostUtils {

    private ImageView ghostImage;
    private Pair<Integer, Integer> oldGhostPosition;
    private Pair<Integer, Integer> ghostPosition;
    private Ghosts ghostName;
    private Directions direction;

    public GhostUtils(final ImageView ghostImage, final Pair<Integer, Integer> ghostPosition,
             final Ghosts ghostName, final Directions direction) {
        this.ghostImage = ghostImage;
        this.ghostPosition = ghostPosition;
        this.ghostName = ghostName;
        this.direction = direction;
    }

    public final Directions getGhostDirection() {
        return this.direction;
    }

    public final void setGhostDirection(final Directions direction) {
        this.direction = direction;
    }

    public final ImageView getGhostImageView() {
        return this.ghostImage;
    }

    public final void setGhostImageView(final ImageView ghostImage) {
        this.ghostImage = ghostImage;
    }
    public final Pair<Integer, Integer> getGhostOldPosition() {
        return this.oldGhostPosition;
    }

    public final Pair<Integer, Integer> getGhostPosition() {
        return this.ghostPosition;
    }

    public final void setGhostPosition(final Pair<Integer, Integer> ghostPosition) {
        this.oldGhostPosition = this.ghostPosition;
        this.ghostPosition = ghostPosition;
    }

    public final Ghosts getGhostName() {
        return this.ghostName;
    }

    public final void setOldLevel() {
        this.ghostName = Ghosts.OLDLEVEL;
    }
}
