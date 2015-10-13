package tests;

import model.GameWorld;
import model.data.Data;
import model.data.Load;
import model.data.Save;
import model.entities.movableEntity.MovableEntity;
import org.junit.Test;
import org.lwjgl.util.vector.Vector3f;

import java.util.HashSet;

import static org.junit.Assert.assertTrue;

/**
 * Test suite for data tests
 */
public class DataTests {

    private static TestSuite suite = new TestSuite();
    private GameWorld gameWorld;
    private Data data;

    /**
     * Initialises the game world
     */
    private void initTestGame() {
        gameWorld = TestSuite.getGameWorld();
        Vector3f position = new Vector3f(10, 10, 10);
        gameWorld.addPlayer(position, 0);
        Save.saveGame(gameWorld);
        data = Load.loadGame();

    }

    @Test
    public void testProgramCompilation() throws Exception {
        initTestGame();

        assertTrue("isProgramCompiled comparison",
                GameWorld.isProgramCompiled() == data.isIsProgramCompiled());
    }

    @Test
    public void testIsOutside() throws Exception {
        initTestGame();

        assertTrue("isOutside comparison",
                GameWorld.isOutside() == data.isIsOutside());
    }

    @Test
    public void testProgressComparison() throws Exception {
        initTestGame();

        assertTrue("progress comparison",
                gameWorld.getProgress() == data.getProgress());
    }

    @Test
    public void testCanApplyPatch() throws Exception {
        initTestGame();

        assertTrue("canApplyPatch comparison",
                gameWorld.isCanApplyPatch() == data.isCanApplyPatch());
    }

    @Test
    public void testCommitIndex() throws Exception {
        initTestGame();

        assertTrue("commitIndex comparison",
                gameWorld.getCommitIndex() == data.getCommitIndex());
    }

    @Test
    public void testTimerComparison() {
        initTestGame();

        assertTrue("timer comparison", gameWorld.getTimer() == Load.loadGame().getTimer());
    }

    @Test
    public void testScoreComparison() {
        initTestGame();
        assertTrue("score comparison", gameWorld.getScore() == data.getScore());
    }

    @Test
    public void testCompareLaptopItems() {
        initTestGame();

        assertTrue("LaptopItems comparison", gameWorld.getInventory()
                .getItems().equals(data.getInventory()));
    }

    @Test
    public void testMovableEntitiesCollectionSize() {
        initTestGame();

        assertTrue("Collection the same size", data.getMovableEntities().size() ==
                gameWorld.getMoveableEntities().size());
    }

    @Test
    public void testCompareMovableEntities() {
        initTestGame();

        HashSet<Integer> uids = new HashSet<>();

        for (MovableEntity e : gameWorld.getMoveableEntities().values()) {
            uids.add(e.getUID());
        }

        for (MovableEntity m : data.getMovableEntities()) {
            assertTrue("Not contained" + m.getUID(), uids.contains(m.getUID()));
        }
    }

    @Test
    public void testCompareSwipeCards() {
        initTestGame();

        assertTrue(
                "SwipeCards comparison",
                gameWorld.getSwipeCards().equals(
                        data.getSwipeCards()));
    }
}
