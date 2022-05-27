package GridThings;

/**
 * this class will used to define a Wall object and its position.
 */
public class Wall extends GridThing{
    /**
     * Constructor method, accept the initial position of the wall.
     * @param ni the position in X direction.
     * @param nj the position in Y direction.
     */
    public Wall(int ni, int nj){
        super(ni, nj);
    }
}
