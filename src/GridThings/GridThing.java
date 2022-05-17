package GridThings;

import java.util.ArrayList;

public abstract class GridThing {
    private int i;
    private int j;

    public GridThing(int ni, int nj) {
        i = ni;
        j = nj;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setPos(int ni, int nj) {
        i = ni;
        j = nj;
    }

    public void goRight() {
        i++;
    }

    public void goLeft() {
        i--;
    }

    public void goUP() {
        j--;
    }

    public void goDown() {
        j++;
    }

    public static GridThing listChecker(ArrayList<? extends GridThing> l, int ni, int nj) {
        for (GridThing g : l) {
            if (g.getI() == ni && g.getJ() == nj)
                return g;
        }
        return null;
    }
}
