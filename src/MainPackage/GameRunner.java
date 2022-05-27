package MainPackage;

import Levels.FirstLevelModel;
import Levels.SecondLevelModel;
import Levels.ThirdLevelModel;
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

    /**
     * Constructor Method of GameRunner Class.
     * @param ngameModel the first game model that game runner will use.
     */
    public GameRunner(GameModel ngameModel) {
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
     * Make a new instance of the current level class, then add the Observers we have to this new GameModel Object.
     */
    private void newGameModel() {
        try {
            gameModel = levelsClasses.get(levelNumber).getDeclaredConstructor().newInstance();
            for (Observer o : observers) {
                o.setModel(gameModel);
                gameModel.addObserver(o);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method, used to connect all class and then getting a running game
     * @param args no use.
     */
    public static void main(String[] args) {
        GameModel gameModel = new FirstLevelModel();

        GameRunner gameRunner = new GameRunner(gameModel);

        FileStrategy fileStrategy = new FileStrategy(new File("src/steps.txt"));

        //Timer timer = new Timer(100, e -> gameRunner.getModel().accept(fileStrategy));
        //timer.start();

        GraphicalFrame frame = new GraphicalFrame(gameModel);
        gameRunner.addObserver(frame);

        PrintObserver printObserver = new PrintObserver(gameModel);
        gameRunner.addObserver(printObserver);

        SoundObserver soundObserver = new SoundObserver(gameModel);
        gameRunner.addObserver(soundObserver);

        gameRunner.addObserver(gameRunner);
    }
}
