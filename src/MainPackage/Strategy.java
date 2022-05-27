package MainPackage;

/**
 * This interface help us to create multiple ways of controlling.
 */
public interface Strategy {
    /**
     * This method have to be implement in every strategy class to be able to get the movement we need to apply on the game.
     * @return the movement we want to apply as integer number
     */
    int moveType();
}
