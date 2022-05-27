package GridThings;

import java.util.ArrayList;

/**
 * an abstract class that used to provide easy template of making a grid objects.
 */
public abstract class GridThing {
    private int i;
    private int j;

    /**
     * the Constructor method of GridThing Class.
     * @param ni the position in X direction.
     * @param nj the position in Y direction.
     */
    public GridThing(int ni, int nj) {
        i = ni;
        j = nj;
    }

    /**
     * used to get the position of a gridThing object.
     * @return the position in X direction.
     */
    public int getI() {
        return i;
    }

    /**
     * used to get the position of a gridThing object.
     * @return the position in Y direction.
     */
    public int getJ() {
        return j;
    }

    /**
     * set the gridThing to specific position.
     * @param ni the position in X direction.
     * @param nj the position in Y direction.
     */
    public void setPos(int ni, int nj) {
        i = ni;
        j = nj;
    }

    /**
     * move the object one step to right
     */
    public void goRight() {
        i++;
    }

    /**
     * move the object one step to left
     */
    public void goLeft() {
        i--;
    }

    /**
     * move the object one step to up
     */
    public void goUP() {
        j--;
    }

    /**
     * move the object one step to down
     */
    public void goDown() {
        j++;
    }

    /**
     * This method we define is used to check if there is any object in arrayList l at (ni, nj) position.
     * @param l the linkedList we make our check int it
     * @param ni ni the position in X direction.
     * @param nj ni the position in Y direction.
     * @return The object we find in this position if there is any.
     */
    public static GridThing listChecker(ArrayList<? extends GridThing> l, int ni, int nj) {
        for (GridThing g : l) {
            if (g.getI() == ni && g.getJ() == nj)
                return g;
        }
        return null;
    }
}
