package MainPackage;

import Levels.FirstLevelModel;
import Levels.SecondLevelModel;
import Levels.ThirdLevelModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GameRunner implements Observer {
    private GameModel gameModel;
    private int levelNumber;
    private final ArrayList<Observer> observers;
    private final ArrayList<Class<? extends GameModel>> levelsClasses;

    public GameRunner(GameModel ngameModel) {
        levelNumber = 0;
        gameModel = ngameModel;

        observers = new ArrayList<>();

        levelsClasses = new ArrayList<>();
        levelsClasses.add(FirstLevelModel.class);
        levelsClasses.add(SecondLevelModel.class);
        levelsClasses.add(ThirdLevelModel.class);
    }

    public void addObserver(Observer o) {
        observers.add(o);
        gameModel.addObserver(o);
    }

    @Override
    public void stateChanged() {
        if (gameModel.checkWin()) {
            levelNumber = (levelNumber + 1) % levelsClasses.size();
            newGameModel();
        } else if (gameModel.checkLose()) {
            newGameModel();
        }
    }

    @Override
    public void setModel(GameModel model) {
        gameModel = model;
    }


    public  GameModel getModel () {
        return  gameModel;
    }


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

    public static void main(String[] args) {
        GameModel gameModel = new FirstLevelModel();

        GameRunner gameRunner = new GameRunner(gameModel);

        FileStrategy fileStrategy = new FileStrategy(new File("src/steps.txt"));

        Timer timer = new Timer(300, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameRunner.getModel().accept(fileStrategy);
           }
       });
        timer.start();

        GraphicalFrame frame = new GraphicalFrame(gameModel);
        gameRunner.addObserver(frame);

        PrintObserver printObserver = new PrintObserver(gameModel);
        gameRunner.addObserver(printObserver);

        SoundObserver soundObserver = new SoundObserver(gameModel);
        gameRunner.addObserver(soundObserver);

        gameRunner.addObserver(gameRunner);
    }
}
