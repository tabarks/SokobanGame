package MainPackage;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * This Class will help me in generating sounds when states of the game change,
 * and it provides three sounds:
 * <ol type="1">
 * <li>Losing sound</li>
 * <li>Winning sound</li>
 * <li>Moving sound</li>
 * </ol>
 */
public class SoundObserver implements Observer {
    private GameModel gameModel;

    /**
     * The Constructor Method of SoundObserver Class, it accepts a game model object to set it.
     * @param ngameModel the initial GameModel
     */
    public SoundObserver(GameModel ngameModel) {
        gameModel = ngameModel;
    }

    /**
     * this method can accept a string with sound file name and play this file.
     * @param filename name of the file we want to play
     */
    public static void play(String filename) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    /**
     * when state of the game change, this method will catch the change and play a sound corresponding to this change.
     */
    @Override
    public void stateChanged() {
        if (gameModel.checkWin()) {
            play("sounds/beep.wav");
        } else if (gameModel.checkLose()) {
            play("sounds/gameOpener.wav");
        } else {
            play("sounds/movingsound.wav");
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
}
