package MainPackage;

import Levels.FirstLevelModel;
import Levels.SecondLevelModel;

import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GameRunner implements Observer {
    private GameModel gameModel;
    private GraphicalFrame frame;
    private PrintObserver printObserver;
    private int levelNumber;
    private final ArrayList<Class<?>> levelsClasses;

    public GameRunner() {
        levelNumber = 0;
        levelsClasses = new ArrayList<>();
        levelsClasses.add(FirstLevelModel.class);
        levelsClasses.add(SecondLevelModel.class);
        try {
            gameModel = (GameModel) levelsClasses.get(levelNumber).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        frame = new GraphicalFrame(gameModel);
        printObserver = new PrintObserver(gameModel);
        gameModel.addObserver(this);
        gameModel.setStrategy(frame);
        gameModel.addObserver(frame);
        gameModel.addObserver(printObserver);
    }

    @Override
    public void stateChanged() {
        if (gameModel.checkWin()) {
            levelNumber++;
            if (levelNumber >= levelsClasses.size())
                return;
            try {
                gameModel = (GameModel) levelsClasses.get(levelNumber).newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            frame = new GraphicalFrame(gameModel);
            printObserver.setModel(gameModel);
            gameModel.addObserver(this);
            gameModel.setStrategy(frame);
            gameModel.addObserver(frame);
            gameModel.addObserver(printObserver);
        } else if (gameModel.checkLose()) {
            try {
                gameModel = (GameModel) levelsClasses.get(levelNumber).newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            frame.setModel(gameModel);

            printObserver.setModel(gameModel);
            gameModel.addObserver(this);
            gameModel.addObserver(frame);
            gameModel.addObserver(printObserver);
        }
    }

    public static void main(String[] args) {
        GameRunner gameRunner = new GameRunner();
    }
}
