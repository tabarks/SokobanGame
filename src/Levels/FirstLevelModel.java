package Levels;
import MainPackage.*;

public class FirstLevelModel extends GameModel {
    public FirstLevelModel() {
        super(8, 9);

        setPlayerPos(2, 2);

        addCrateBox(3, 2);
        addCrateBox(4, 3);
        addCrateBox(4, 4);
        addCrateBox(1, 6);
        addCrateBox(3, 6);
        addCrateBox(4, 6);
        addCrateBox(5, 6);

        addMarkedBox(1, 2);
        addMarkedBox(5, 3);
        addMarkedBox(1, 4);
        addMarkedBox(4, 5);
        addMarkedBox(3, 6);
        addMarkedBox(6, 6);
        addMarkedBox(4, 7);

        for (int i = 0; i < 5; i++) {
            addWall(2+i, 0);
            addWall(6, 1+i);
        }
        for (int i = 0; i < 4; i++) {
            addWall(7, 5+i);
        }
        for (int i = 0; i < 8; i++) {
            addWall(0, 1+i);
        }
        for (int i = 0; i < 6; i++) {
            addWall(1+i, 8);
        }
        addWall(1, 1);
        addWall(2, 1);
        addWall(1, 3);
        addWall(2, 3);
        addWall(2, 4);
        addWall(3, 4);
        addWall(2, 5);
    }
}