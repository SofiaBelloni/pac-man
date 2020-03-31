package controller;

/**
 * 
 * A class that implements controller.
 *
 */
public class ControllerImpl implements Controller {
    private final GameModel model;
    private View view;
    
    /**
     * Constructor.
     * @param model
     *      the model reference
     * @param view
     *      the view reference
     */
    public ControllerImpl(final GameModel model, final View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void startGame() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pauseGame() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resumeGame() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void savePlayer(final int score) {
        // TODO Auto-generated method stub
        
    }


}
