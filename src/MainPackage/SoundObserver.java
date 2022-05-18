package MainPackage;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundObserver implements Observer {
    private GameModel gameModel;

    public SoundObserver(GameModel ngameModel) {
        gameModel = ngameModel;
    }

    public void play(String filename) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }


    @Override
    public void stateChanged() {
        if (gameModel.checkWin()) {
            //play("sounds/movingsound.wav");
        } else if (gameModel.checkLose()) {
            //play("sounds/gameOpener.wav");
        } else {
            //play("sounds/movingsound.wav");
        }
    }

    @Override
    public void setModel(GameModel model) {
        gameModel = model;
    }
}
