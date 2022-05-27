package MainPackage;

import GridThings.BlankMarkedBox;
import GridThings.CrateBox;
import GridThings.Wall;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This Class is using Serialization to save game model in files.
 * but before it convert the state of the game to array of strings.
 * then it saves this array.
 */
public class GameSaver {

    /**
     * this method can save the state of game model in a file
     * @param model the model we want to save.
     * @param fileName the file we want to save the game in.
     */
    public static void save(GameModel model, String fileName){
        StringBuilder[] s = new StringBuilder[model.getD().height];
        for (int j = 0; j < model.getD().height; j++) {
            s[j] = new StringBuilder();
        }
        for (int i = 0; i < model.getD().height; i++) {
            for (int j = 0; j < model.getD().width; j++) {
                s[i].append(".");
            }
        }
        for(BlankMarkedBox b : model.getBlankMarkedBoxes()){
            s[b.getJ()].setCharAt(b.getI(), '@');
        }
        for(CrateBox c : model.getCrateBoxes()){
            if (c.isMarked())
                s[c.getJ()].setCharAt(c.getI(), 'O');
            else
                s[c.getJ()].setCharAt(c.getI(), 'X');
        }

        for(Wall wall : model.getWalls()){
            s[wall.getJ()].setCharAt(wall.getI(), '#');
        }
        if(s[model.getPlayer().getJ()].charAt(model.getPlayer().getI())=='.')
            s[model.getPlayer().getJ()].setCharAt(model.getPlayer().getI(), 'P');
        else
            s[model.getPlayer().getJ()].setCharAt(model.getPlayer().getI(), 'p');

        String[] s1 = new String[model.getD().height];
        for (int i = 0; i < s.length; i++) {
            s1[i] = s[i].toString();
        }

        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(fileName));
            out.writeObject(s1);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
