package MainPackage;

import GridThings.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * <h2>This Class will curry and store the state of the game and the position and state of every object in it.</h2>
 * <ol><h3>Also it will provide some methods to help us in:</h3>
 * <li>Checking winning and losing state.</li>
 * <li>Accept a Strategy object to control game.</li>
 * <li>Add Observers to tracking the state of the game.</li>
 * </ol>
 */
public abstract class GameModel {
    private final Dimension d;
    private final ArrayList<CrateBox> crateBoxes;
    private final ArrayList<BlankMarkedBox> blankMarkedBoxes;
    private final ArrayList<Wall> walls;
    private final Player player;
    private final ArrayList<Observer> observers;

    public static final int DO_NOTHING = 0;
    public static final int GO_RIGHT = 1;
    public static final int GO_LEFT = 2;
    public static final int GO_UP = 3;
    public static final int GO_DOWN = 4;

    /**
     * The Constructor Method of GameModel Class, it sets dimensions and init the variables we have int the class.
     * @param dx the x dimensions (how many blocks we have in x direction)
     * @param dy the y dimensions (how many blocks we have in y direction)
     */
    public GameModel(int dx, int dy) {
        d = new Dimension(dx, dy);
        crateBoxes = new ArrayList<>();
        blankMarkedBoxes = new ArrayList<>();
        walls = new ArrayList<>();
        player = new Player();
        observers = new ArrayList<>();
    }

    /**
     * used tp checking if we win the game or yet, by checking that every crate box is a marked one.
     * @return the state of winning
     */
    public boolean checkWin() {
        for (CrateBox c : crateBoxes) {
            if (!c.isMarked())
                return false;
        }
        return true;
    }

    /**
     * check if we lose the game by checking if there is any crate box with no ability to move.
     * @return the state of losing
     */
    public boolean checkLose() {
        for (CrateBox c : crateBoxes) {
            boolean canMoveRL = true, canMoveUD = true;
            if (!c.isMarked()) {
                if (c.getI() == getD().width - 1 || GridThing.listChecker(walls, c.getI() + 1, c.getJ()) != null)
                    canMoveRL = false;
                if (c.getI() == 0 || GridThing.listChecker(walls, c.getI() - 1, c.getJ()) != null)
                    canMoveRL = false;
                if (c.getJ() == getD().height - 1 || GridThing.listChecker(walls, c.getI(), c.getJ() + 1) != null)
                    canMoveUD = false;
                if (c.getJ() == 0 || GridThing.listChecker(walls, c.getI(), c.getJ() - 1) != null)
                    canMoveUD = false;
            }
            if (!canMoveRL && !canMoveUD) {
                return true;
            }
        }
        return false;
    }

    /**
     * adds a new Observer to the observer list
     * @param o the new Observer
     */
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * get the Dimension of the game.
     * @return Dimension of the game
     */
    public Dimension getD() {
        return d;
    }

    /**
     * help as to get a ArrayList of CrateBoxes we have
     * @return the ArrayList ot CrateBoxes.
     */
    public ArrayList<CrateBox> getCrateBoxes() {
        return crateBoxes;
    }

    /**
     * help as to get a ArrayList of BlankMarkedBoxes we have
     * @return the ArrayList ot BlankMarkedBoxes.
     */
    public ArrayList<BlankMarkedBox> getBlankMarkedBoxes() {
        return blankMarkedBoxes;
    }

    /**
     * help as to get a ArrayList of Walls we have
     * @return the ArrayList ot Walls.
     */
    public ArrayList<Wall> getWalls() {
        return walls;
    }

    /**
     * add a new Wall to specific position.
     * @param i the position in X direction.
     * @param j the position in Y direction.
     */
    public void addWall(int i, int j) {
        walls.add(new Wall(i, j));
    }

    /**
     * set the player to specific position.
     * @param i the position in X direction.
     * @param j the position in Y direction.
     */
    public void setPlayerPos(int i, int j) {
        player.setPos(i, j);
    }

    /**
     * get the Player object we use in the game
     * @return the player object.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * <h2>this Method is doing two things:</h2>
     * <ol type="1">
     * <li>check if there is any crate box in a marked area or not</li>
     * <li>used to notify the Observer that state of the game was changed by calling the stateChange Method.</li>
     * </ol>
     */
    protected void updateThings() {
        for (CrateBox c : crateBoxes) {
            boolean marked = false;
            for (BlankMarkedBox s : blankMarkedBoxes) {
                if (s.getI() == c.getI() && s.getJ() == c.getJ()) {
                    marked = true;
                    break;
                }
            }
            c.setMarked(marked);
        }
        for (Observer o : observers)
            o.stateChanged();
    }
    /**
     * add a new CrateBox to specific position.
     * @param i the position in X direction.
     * @param j the position in Y direction.
     */
    public void addCrateBox(int i, int j) {
        crateBoxes.add(new CrateBox(i, j, false));
        updateThings();
    }

    /**
     * add a new MarkedBox to specific position.
     * @param i the position in X direction.
     * @param j the position in Y direction.
     */
    public void addMarkedBox(int i, int j) {
        blankMarkedBoxes.add(new BlankMarkedBox(i, j));
        updateThings();
    }

    /**
     * this method will use to control the game by passing a strategy object witch will have the moveType method that can used to get the move we have to apply.
     * @param strategy thr strategy that we will get the move type from it.
     */
    public void accept(Strategy strategy) {
        int mvt = strategy.moveType();
        if (mvt == GO_RIGHT)
            goRight();
        else if (mvt == GO_LEFT)
            goLeft();
        else if (mvt == GO_UP)
            goUP();
        else if (mvt == GO_DOWN)
            goDown();
    }

    /**
     * move the player one step to right if he can do that.
     */
    private void goRight() {
        if (player.getI() + 1 >= d.width)
            return;
        if (GridThing.listChecker(walls, player.getI() + 1, player.getJ()) != null) {
            return;
        }
        GridThing c = GridThing.listChecker(crateBoxes, player.getI() + 1, player.getJ());
        if (c != null) {
            if (c.getI() + 1 == d.width
                    || GridThing.listChecker(crateBoxes, player.getI() + 2, player.getJ()) != null
                    || GridThing.listChecker(walls, player.getI() + 2, player.getJ()) != null) {
                return;
            }
            c.goRight();
        }
        player.goRight();
        updateThings();
    }

    /**
     * move the player one step to left if he can do that.
     */
    private void goLeft() {
        if (player.getI() - 1 < 0)
            return;
        if (GridThing.listChecker(walls, player.getI() - 1, player.getJ()) != null) {
            return;
        }
        GridThing c = GridThing.listChecker(crateBoxes, player.getI() - 1, player.getJ());
        if (c != null) {
            if (c.getI() - 1 < 0
                    || GridThing.listChecker(crateBoxes, player.getI() - 2, player.getJ()) != null
                    || GridThing.listChecker(walls, player.getI() - 2, player.getJ()) != null) {
                return;
            }
            c.goLeft();
        }
        player.goLeft();
        updateThings();
    }

    /**
     * move the player one step to up if he can do that.
     */
    private void goUP() {
        if (player.getJ() - 1 < 0)
            return;
        if (GridThing.listChecker(walls, player.getI(), player.getJ() - 1) != null) {
            return;
        }
        GridThing c = GridThing.listChecker(crateBoxes, player.getI(), player.getJ() - 1);
        if (c != null) {
            if (c.getJ() - 1 < 0
                    || GridThing.listChecker(crateBoxes, player.getI(), player.getJ() - 2) != null
                    || GridThing.listChecker(walls, player.getI(), player.getJ() - 2) != null) {
                return;
            }
            c.goUP();
        }
        player.goUP();
        updateThings();
    }

    /**
     * move the player one step to down if he can do that.
     */
    private void goDown() {
        if (player.getJ() + 1 >= d.height)
            return;
        if (GridThing.listChecker(walls, player.getI(), player.getJ() + 1) != null) {
            return;
        }
        GridThing c = GridThing.listChecker(crateBoxes, player.getI(), player.getJ() + 1);
        if (c != null) {
            if (c.getJ() + 1 == d.height
                    || GridThing.listChecker(crateBoxes, player.getI(), player.getJ() + 2) != null
                    || GridThing.listChecker(walls, player.getI(), player.getJ() + 2) != null) {
                return;
            }
            c.goDown();
        }
        player.goDown();
        updateThings();
    }
}
