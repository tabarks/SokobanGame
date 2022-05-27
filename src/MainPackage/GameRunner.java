package MainPackage;

import Levels.FirstLevelModel;
import Levels.SecondLevelModel;
import Levels.ThirdLevelModel;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * <h2>The main task of this class is:</h2>
 * <ol>
 * <li>Switching between levels</li>
 * <li>Set the Observers</li>
 * <li>Run the Game</li>
 * </ol>
 */
public class GameRunner implements Observer {
    private GameModel gameModel;
    private int levelNumber;
    private final ArrayList<Observer> observers;
    private final ArrayList<Class<? extends GameModel>> levelsClasses;
    private Timer timer;

    /**
     * Constructor Method of GameRunner Class.
     * @param ngameModel the first game model that game runner will use.
     */
    public GameRunner(GameModel ngameModel, String fileName) {
        FileStrategy fileStrategy = new FileStrategy(new File(fileName));
        timer = new Timer(100, e -> getModel().accept(fileStrategy));

        levelNumber = 0;
        gameModel = ngameModel;

        observers = new ArrayList<>();

        levelsClasses = new ArrayList<>();
        levelsClasses.add(FirstLevelModel.class);
        levelsClasses.add(SecondLevelModel.class);
        levelsClasses.add(ThirdLevelModel.class);
    }

    /**
     * add a new Observer to gameRunner and gameModel we use right now
     * @param o the new Observer object
     */
    public void addObserver(Observer o) {
        observers.add(o);
        gameModel.addObserver(o);
    }

    /**
     * when state of the gameModel is changed, GameRunner will check for win and lose states to update it.
     */
    @Override
    public void stateChanged() {
        if (gameModel.checkWin()) {
            levelNumber = (levelNumber + 1) % levelsClasses.size();
            newGameModel();
        } else if (gameModel.checkLose()) {
            newGameModel();
        }
    }

    /**
     * When the model is changed, this Method used to set the new Model in the Observer.
     * @param model the new Model
     */
    @Override
    public void setModel(GameModel model) {
        gameModel = model;
    }

    /**
     * This method is used to get the current GameModel
     * @return the current GameModel we use
     */
    public GameModel getModel() {
        return gameModel;
    }

    /**
     * Make a new instance of the current level class.
     */
    private void newGameModel() {
        try {
            GameModel model = levelsClasses.get(levelNumber).getDeclaredConstructor().newInstance();
            changeModel(model);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method will add the Observers we have to a new GameModel Object.
     * @param model the new game model we will use.
     */
    public void changeModel(GameModel model){
        for (Observer o : observers) {
            o.setModel(model);
            model.addObserver(o);
            o.stateChanged();
        }
    }

    public void startFileStrategyStart(){
        timer.start();
    }

    public void stopFileStrategyStart(){
        timer.stop();
    }

    /**
     * The main method, used to connect all class and then getting a running game
     * @param args no use.
     */
    public static void main(String[] args) {
        GameModel gameModel = new FirstLevelModel();

        GameRunner gameRunner = new GameRunner(gameModel, "src/steps.txt");

        GraphicalFrame frame = new GraphicalFrame(gameModel, gameRunner);
        gameRunner.addObserver(frame);

        PrintObserver printObserver = new PrintObserver(gameModel);
        gameRunner.addObserver(printObserver);

        SoundObserver soundObserver = new SoundObserver(gameModel);
        gameRunner.addObserver(soundObserver);

        gameRunner.addObserver(gameRunner);
    }
}
