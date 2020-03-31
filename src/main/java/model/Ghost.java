package model;

public interface Ghost extends Entity {

    boolean isEatable();
    
    int nextPosition(PacMan pacMan);
    
    void setEatable(boolean eatable);
    
    void returnHome();
}
