package controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Directions;
import model.GameModel;
import model.Ghosts;
import utils.Pair;
import view.View;

public class DataUpdater {

    private GameModel model;
    private View view;

    /**
     * Constructor.
     * @param model
     *      the model reference
     * @param view
     *      the view reference
     */
    public DataUpdater(GameModel model, View view) {
        this.model = model;
        this.view = view;
    }

    public void updateModel() {
        this.model.moveEntitiesNextPosition();
    }
    
    public void render() {
        this.view.render();
    }

    /**
     * @return true if the game is ended, false otherwise
     */
    public boolean isGameEnded() {
        return this.model.isGameEnded();
    }
    /**
     * @return the X position of Pac-Man
     */
    public int getPacManXPosition() {
        return this.model.getPacManPosition().getX();
    }
    /**
     * @return the Y position of Pac-Man
     */
    public int getPacManYPosition() {
        return this.model.getPacManPosition().getY();
    }
    /**
     * @return the Pacman direction.
     */
    public Directions getPacManDirection() {
        return this.model.getPacManDirection();
    }
    /**
     * @return a value between 0 and 1 that indicates the level-timer progress. 
     */
    public double getLevelTimePercentage() {
        return this.model.getLevelTime() / 60; //TODO 
    }
    /**
     * @return the scores of the current game
     */
    public int getCurrentScore() {
        return this.model.getScores();
    }
    /**
     * @return the level number
     */
    public int getLevel() {
        return this.model.getLevelNumber();
    }
    /**
     * @return the remaining lives of Pac-Man
     */
    public int getLives() {
        return this.model.getPacManLives();
    }
    /**
     * @return a Set containing the positions of the pills;
     */
    public Set<Pair<Integer, Integer>> getPillsPositions() {
        return this.model.getPillsPositions();
    }
    /**
     * @return a Set containing the wall's positions;
     */
    public Set<Pair<Integer, Integer>> getWallsPositions(){
        return this.model.getWallsPositions();
    }
    /**
     * return a Set containing all the ghosts positions.
     */
    public void getGhostsPositions() {
        Map<Ghosts, List<Pair<Integer, Integer>>> ghostsPositions = this.model.getGhostsPositions();
        // TODO passare da Ghost a Entity

    }
    /**
     * @return the size of the map on the x-axis
     */
    public int getxMapSize() {
        return this.model.getxMapSize();
    }
    /**
     * @return the size of the map on the y-axis
     */
    public int getyMapSize() {
        return this.model.getyMapSize();
    }
}
