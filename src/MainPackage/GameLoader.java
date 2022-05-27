package MainPackage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GameLoader extends GameModel{
    public GameLoader(String fileName){
        super(0, 0);
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(fileName));
            String[] s = (String[]) in.readObject();
            in.close();

            setD(new Dimension(s[0].length(), s.length));
            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s[0].length(); j++) {
                    switch (s[i].charAt(j)){
                        case '@':
                            addMarkedBox(j, i);
                            break;
                        case 'O':
                            addCrateBox(j, i);
                            addMarkedBox(j, i);
                            break;
                        case 'X':
                            addCrateBox(j, i);
                            break;
                        case '#':
                            addWall(j, i);
                            break;
                        case 'P':
                            setPlayerPos(j, i);
                        case 'p':
                            setPlayerPos(j, i);
                            addMarkedBox(j, i);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
