package GridThings;

/**
 * this class will used to define a Crate Box object and its position, and it also help us to know if this crate box is marked or not
 */
public class CrateBox extends GridThing{
    private boolean marked;

    /**
     * the Constructor method of CrateBox class, accept the initial position and the marked state of it.
     * @param ni the position in X direction.
     * @param nj the position in Y direction.
     * @param nmarked marked the initial state of the crate box.
     */
    public CrateBox(int ni, int nj, boolean nmarked){
        super(ni, nj);
        marked = nmarked;
    }

    /**
     * used to check if crate box is marked
     * @return a boolean value describe the state of the crate box.
     */
    public boolean isMarked() {
        return marked;
    }

    /**
     * used to change the marked state of a create box
     * @param marked the new state
     */
    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
