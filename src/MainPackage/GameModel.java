package MainPackage;

import GridThings.*;

import java.awt.*;
import java.util.ArrayList;

public abstract class GameModel {
    private final Dimension d;
    private final ArrayList<CrateBox> crateBoxes;
    private final ArrayList<BlankMarkedBox> blankMarkedBoxes;
    private final ArrayList<Wall> walls;
    private final Player player;
    private final ArrayList<Observer> observers;

    public GameModel(int dx, int dy) {
        d = new Dimension(dx, dy);
        crateBoxes = new ArrayList<>();
        blankMarkedBoxes = new ArrayList<>();
        walls = new ArrayList<>();
        player = new Player();
        observers = new ArrayList<>();
    }

    public boolean checkWin() {
        for (CrateBox c : crateBoxes) {
            if (!c.isMarked())
                return false;
        }
        return true;
    }

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

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public Dimension getD() {
        return d;
    }

    public ArrayList<CrateBox> getCrateBoxes() {
        return crateBoxes;
    }

    public ArrayList<BlankMarkedBox> getBlankMarkedBoxes() {
        return blankMarkedBoxes;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void addWall(int i, int j) {
        walls.add(new Wall(i, j));
    }

    public void setPlayerPos(int i, int j) {
        player.setPos(i, j);
    }

    public Player getPlayer() {
        return player;
    }

    private void updateThings() {
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

    public void addCrateBox(int i, int j) {
        crateBoxes.add(new CrateBox(i, j, false));
        updateThings();
    }

    public void addMarkedBox(int i, int j) {
        blankMarkedBoxes.add(new BlankMarkedBox(i, j));
        updateThings();
    }

    public void goRight() {
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

    public void goLeft() {
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

    public void goUP() {
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

    public void goDown() {
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
