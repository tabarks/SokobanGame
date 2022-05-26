package TestFiles;

import GridThings.CrateBox;
import Levels.FirstLevelModel;
import MainPackage.GameModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    GameModel gameModel;

    /**
     * <h1>setup method before every test.</h1>
     */
    @Before
    public void startTest(){
         gameModel = new FirstLevelModel();
    }

    /**
     * <h1>check that player can move by applying a strategy</h1>
     */
    @Test
    public void playerMovementTest(){
        assertEquals(2, gameModel.getPlayer().getI());
        assertEquals(2, gameModel.getPlayer().getJ());
        gameModel.accept(() -> GameModel.GO_RIGHT);
        assertEquals(3, gameModel.getPlayer().getI());
        assertEquals(2, gameModel.getPlayer().getJ());
    }

    /**
     * <h1>check that player can't move if there is a wall in front of him</h1>
     */
    @Test
    public void wallBlockingTest(){
        assertEquals(2, gameModel.getPlayer().getI());
        assertEquals(2, gameModel.getPlayer().getJ());
        gameModel.accept(() -> GameModel.GO_UP);
        assertEquals(2, gameModel.getPlayer().getI());
        assertEquals(2, gameModel.getPlayer().getJ());
        gameModel.accept(() -> GameModel.GO_DOWN);
        assertEquals(2, gameModel.getPlayer().getI());
        assertEquals(2, gameModel.getPlayer().getJ());
    }

    /**
     * <h1>check losing when a crate unmarked crate box can't move any more</h1>
     */
    @Test
    public void loseTest(){
        assertFalse(gameModel.checkLose());
        int[] moves = {GameModel.GO_RIGHT, GameModel.GO_UP, GameModel.GO_RIGHT, GameModel.GO_RIGHT, GameModel.GO_DOWN, GameModel.GO_DOWN, GameModel.GO_LEFT};
        for (int move : moves) {
            gameModel.accept(() -> move);
        }
        assertTrue(gameModel.checkLose());
    }

    /**
     * <h1>to make a marking test we need to:</h1>
     * <ol type="1">
     *   <li>move the crate box to right position</li>
     *   <li>check that crate box is unmarked</li>
     *   <li>push the crate box to marked blank position</li>
     *   <li>check that box is marked right now</li>
     * </ol>
     */
    @Test
    public void markingTest(){
        int[] moves = {GameModel.GO_RIGHT, GameModel.GO_RIGHT, GameModel.GO_UP, GameModel.GO_RIGHT};
        for (int move : moves) {
            gameModel.accept(() -> move);
        }
        CrateBox c = null;
        for(CrateBox crateBox : gameModel.getCrateBoxes()) {
            if (crateBox.getI() == 5 && crateBox.getJ() == 2)
                c = crateBox;
        }
        if(c!=null) {
            assertFalse(c.isMarked());
            gameModel.accept(() -> GameModel.GO_DOWN);
            assertTrue(c.isMarked());
        } else {
            fail("can't find the crate box");
        }
    }
}
