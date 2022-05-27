package Levels;

import MainPackage.GameModel;

public class ThirdLevelModel extends GameModel {
    /**
     * defining and setting the Third Level data.
     */
    public ThirdLevelModel(){
        super(8, 8);
        setPlayerPos(3, 1);

        addWall(2, 7);
        addWall(2, 6);
        addWall(2, 5);
        addWall(1, 5);

        addWall(5, 1);
        addWall(6, 1);
        addWall(3, 2);
        addWall(4, 5);

        addCrateBox(4, 2);
        addCrateBox(4, 4);
        addCrateBox(3, 4);
        addCrateBox(2, 3);


        addMarkedBox(2, 3);
        addMarkedBox(3, 3);
        addMarkedBox(5, 3);
        addMarkedBox(5, 5);


        for (int i = 0; i < 6; i++) {
            addWall(1+i, 0);
        }
        for (int i = 0; i < 4; i++) {
            addWall(0, 2+i);
        }
        for (int i = 0; i < 4; i++) {
            addWall(2+i, 7);
        }
        for (int i = 0; i < 5; i++) {
            addWall(7, 1+i);
        }
        for (int i = 0; i < 2; i++) {
            addWall(1, 1+i);
        }
        for (int i = 0; i < 4; i++) {
            addWall(6, 4+i);
        }


    }

}
