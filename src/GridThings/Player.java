package GridThings;

/**
 * this class will used to define a player object and its position.
 */
public class Player extends GridThing{
    /**
     * first Constructor to set thr position as 0, 0
     */
    public Player(){
        super(0, 0);
    }

    /**
     * second Constructor to set the position in a specific one.
     * @param ni the position in X direction.
     * @param nj the position in Y direction.
     */
    public Player(int ni, int nj){
        super(ni, nj);
    }
}
