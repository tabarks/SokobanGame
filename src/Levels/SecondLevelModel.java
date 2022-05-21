package Levels;
import MainPackage.*;

public class SecondLevelModel extends GameModel {
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



      /*  super(22, 11);

        setPlayerPos(3, 3);


        addCrateBox(21, 9);
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

        for (int i = 0; i < 19; i++) {
            addWall(2+i, 0);
            //addWall(5, 1+i);
        }
        for (int i = 0; i < 10; i++) {
            addWall(0, 1+i);
        }
        for (int i = 0; i < 20; i++) {
            addWall(1+i, 10);
        }
        for (int i = 0; i < 6; i++) {
            addWall(21, 5+i);
        }


        addWall(10, 0);
        addWall(2, 1);
        addWall(1, 3);
        addWall(2, 3);
        addWall(2, 4);
        addWall(3, 4);
        addWall(2, 5);
*/

    }
}
