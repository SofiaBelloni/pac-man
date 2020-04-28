package view.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to iterate all the textures of the PacMan entity, cycling from the first to the last.
 */
public class PacManTextureIterator implements EntityTextureIterator {

    private List<String> imageAdress = new ArrayList<>();
    private int imageToReturn;

    public PacManTextureIterator() {
        this.imageAdress.add("textures/pac_man/pac_man0.png");
        this.imageAdress.add("textures/pac_man/pac_man1.png");
        this.imageAdress.add("textures/pac_man/pac_man2.png");
        this.imageAdress.add("textures/pac_man/pac_man3.png");
        this.imageAdress.add("textures/pac_man/pac_man2.png");
        this.imageAdress.add("textures/pac_man/pac_man1.png");
        this.imageToReturn = 0;
    }




    /**
     *{@inheritDoc}
     */
    @Override
    public String nextImage() {
        if (this.imageToReturn == this.imageAdress.size()) {
            this.imageToReturn = 0;
        }
        int index = this.imageToReturn;
        this.imageToReturn++;
        return this.imageAdress.get(index);
    }

}
