package Levels;
import MainPackage.*;

public class SecondLevelModel extends GameModel {
    /**
     * defining and setting the Second Level data.
     */
    public SecondLevelModel(){
        super(8, 8);
        setPlayerPos(2, 1);

        addCrateBox(3, 2);
        addCrateBox(2, 5);
        addCrateBox(5, 6);


        addWall(2, 3);
        addWall(2, 4);
        addWall(4, 3);
        addWall(4, 4);
        addWall(4, 1);
        addWall(5, 5);



        for (int i = 0; i < 6; i++) {
            addWall(1+i, 0);

        }
        for (int i = 0; i < 3; i++) {
            addWall(6, 1+i);

        }

        for (int i = 0; i < 4; i++) {
            addWall(7, 4+i);
        }
        for (int i = 0; i < 7; i++) {
            addWall(0, 1+i);
        }
        for (int i = 0; i < 6; i++) {
            addWall(1+i, 7);
        }


        for (int i = 0; i < 3; i++) {
            addWall(1, 1+i);
        }

        for (int i = 0; i < 4; i++) {
            addMarkedBox(1, 3+i);
        }
    }
}
