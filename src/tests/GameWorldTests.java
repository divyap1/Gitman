package tests;

import model.GameWorld;
import model.entities.movableEntity.*;
import model.guiComponents.Inventory;
import org.junit.Test;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * The type Game world tests.
 *
 * @author Divya Patel - 300304450
 * @author Ellie Coyle - 300307071
 */
public class GameWorldTests {

    private static TestSuite suite = new TestSuite();
    private static GameWorld gameWorld = TestSuite.getGameWorld();

    /**
     * Test constructor adds player to game world
     */
    public GameWorldTests() {
        gameWorld.addPlayer(new Vector3f(10, 10, 10), 0);
    }

    // -------------------------------------------------
    // TEST INTERACTIONS WITH MOVABLE ENTITES
    // -------------------------------------------------

    /**
     * Test swipe card interact.
     */
    @Test
    public void testSwipeCardInteract() {
        SwipeCard e = (SwipeCard) getEntity("SwipeCard");
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new SwipeCard(null, null, 0, 0, 0, 0, 0, 0);
        }
        assertEquals(16, e.interact(gameWorld));
    }

    /**
     * Test read me interact.
     */
    @Test
    public void testReadMeInteract() {
        ReadMe e = (ReadMe) getEntity("ReadMe");
        gameWorld.getInventory().setStorageUsed(0);
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new ReadMe(null, null, 0, 0, 0, 0, 0, 0, "readme10");
        }
        assertEquals(13, e.interact(gameWorld));
    }

    /**
     * Test flash drive interact.
     */
    @Test
    public void testFlashDriveInteract() {
        FlashDrive e = (FlashDrive) getEntity("FlashDrive");
        gameWorld.getInventory().setStorageUsed(0);
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new FlashDrive(null, null, 0, 0, 0, 0, 0, 0, "extImg0");
        }
        assertEquals(13, e.interact(gameWorld));
    }

    /**
     * Test bug interact.
     */
    @Test
    public void testBugInteract() {
        Bug e = (Bug) getEntity("Bug");
        if (e == null) {
            e = new Bug(null, null, 0, 0, 0, 0, 0);
        }
        if (!gameWorld.canApplyPatch()) {
            assertEquals(-1, e.interact(gameWorld));
        } else {
            assertEquals(10, e.interact(gameWorld));
        }
    }

    /**
     * Test bug interact succcessful.
     */
    @Test
    public void testBugInteractSucccessful() {
        while (!gameWorld.canApplyPatch()) {
            Commit e = (Commit) getEntity("Commit");
            if (e == null) {
                e = new Commit(null, null, 0, 0, 0, 0, 0);
            }
            e.interact(gameWorld);
        }
        assertTrue(gameWorld.canApplyPatch());
        Bug e = (Bug) getEntity("Bug");
        if (e == null) {
            e = new Bug(null, null, 0, 0, 0, 0, 0);
        }
        assertEquals(10, e.interact(gameWorld));
    }

    /**
     * Test laptop interact.
     */
    @Test
    public void testLaptopInteract() {
        Laptop e = (Laptop) getEntity("Laptop");
        gameWorld.getInventory().setStorageUsed(0);
        if (e == null) {
            e = new Laptop(null, null, 0, 0, 0, 0, 0, 0, true);
        }
        // if have card, ensure successful interaction
        if (checkSwipeCard(e.getCardID())) {
            assertEquals(17, e.interact(gameWorld));
        } else {
            assertEquals(-1, e.interact(gameWorld));
        }
    }

    /**
     * Test commit interact.
     */
    @Test
    public void testCommitInteract() {
        Commit e = (Commit) getEntity("Commit");
        if (e == null) {
            e = new Commit(null, null, 0, 0, 0, 0, 0);
        }
        assertEquals(11, e.interact(gameWorld));
    }


    /**
     * Test swipe card can interact.
     */
    @Test
    public void testSwipeCardCanInteract() {
        SwipeCard e = (SwipeCard) getEntity("SwipeCard");
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new SwipeCard(null, null, 0, 0, 0, 0, 0, 0);
        }
        assertTrue(e.canInteract());
    }

    /**
     * Test read me can interact.
     */
    @Test
    public void testReadMeCanInteract() {
        ReadMe e = (ReadMe) getEntity("ReadMe");
        gameWorld.getInventory().setStorageUsed(0);
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new ReadMe(null, null, 0, 0, 0, 0, 0, 0, "readme10");
        }
        assertTrue(e.canInteract());
    }

    /**
     * Test flash drive can interact.
     */
    @Test
    public void testFlashDriveCanInteract() {
        FlashDrive e = (FlashDrive) getEntity("FlashDrive");
        gameWorld.getInventory().setStorageUsed(0);
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new FlashDrive(null, null, 0, 0, 0, 0, 0, 0, "extImg0");
        }
        assertTrue(e.canInteract());
    }

    /**
     * Test bug can interact.
     */
    @Test
    public void testBugCanInteract() {
        Bug e = (Bug) getEntity("Bug");
        if (e == null) {
            e = new Bug(null, null, 0, 0, 0, 0, 0);
        }
        assertTrue(e.canInteract());
    }

    /**
     * Test laptop can interact.
     */
    @Test
    public void testLaptopCanInteract() {
        Laptop e = (Laptop) getEntity("Laptop");
        gameWorld.getInventory().setStorageUsed(0);
        if (e == null) {
            e = new Laptop(null, null, 0, 0, 0, 0, 0, 0, true);
        }
        assertTrue(e.canInteract());
    }

    /**
     * Test commit can interact.
     */
    @Test
    public void testCommitCanInteract() {
        Commit e = (Commit) getEntity("Commit");
        if (e == null) {
            e = new Commit(null, null, 0, 0, 0, 0, 0);
        }
        assertTrue(e.canInteract());
    }

    // -----------------------------------------------------
    // TEST INVENTORY ADD AND DELETE
    // -----------------------------------------------------

    /**
     * Test add to inventory good 1.
     */
    @Test
    public void testAddToInventoryGood_1() {
        Inventory i = gameWorld.getInventory();
        ReadMe e = (ReadMe) getEntity("ReadMe");
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new ReadMe(null, null, 0, 0, 0, 0, 0, 0, "readme10");
        }
        int used = i.getStorageUsed();
        i.setStorageUsed(0);
        assertTrue(i.addItem(e));
        i.setStorageUsed(used);  //reset storage value

    }

    /**
     * Test add to inventory good 2.
     */
    @Test
    public void testAddToInventoryGood_2() {
        Inventory i = gameWorld.getInventory();
        FlashDrive e = (FlashDrive) getEntity("FlashDrive");
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new FlashDrive(null, null, 0, 0, 0, 0, 0, 0, "extImg0");
        }
        int used = i.getStorageUsed();
        i.setStorageUsed(0);
        assertTrue(i.addItem(e));
        i.setStorageUsed(used);  //reset storage value
    }

    /**
     * Test add to inventory bad 1.
     */
    @Test
    public void testAddToInventoryBad_1() {
        Inventory i = gameWorld.getInventory();
        FlashDrive e = (FlashDrive) getEntity("FlashDrive");
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new FlashDrive(null, null, 0, 0, 0, 0, 0, 0, "extImg0");
        }
        int used = i.getStorageUsed();
        i.setStorageUsed(Inventory.MAX_STORAGE_SIZE);
        assertFalse(i.addItem(e));
        i.setStorageUsed(used);  //reset storage value
    }

    /**
     * Test add to inventory bad 2.
     */
    @Test
    public void testAddToInventoryBad_2() {
        Inventory i = gameWorld.getInventory();
        ReadMe e = (ReadMe) getEntity("ReadMe");
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new ReadMe(null, null, 0, 0, 0, 0, 0, 0, "readme10");
        }
        int used = i.getStorageUsed();
        i.setStorageUsed(Inventory.MAX_STORAGE_SIZE);
        assertFalse(i.addItem(e));
        i.setStorageUsed(used);  //reset storage value
    }

    /**
     * Delete item from inventory 1.
     */
    @Test
    public void deleteItemFromInventory_1() {
        Inventory i = gameWorld.getInventory();
        if (i.getSelected() != null) {
            assertEquals(i.getSelected().getUID(), i.deleteItem(gameWorld));
        } else {
            assertEquals(-1, i.deleteItem(gameWorld));
        }
    }

    // ------------------------------------------------------
    // TEST GAME WORLD GAME STATE
    // ------------------------------------------------------

    /**
     * Test remove entity 1.
     */
    @Test
    public void testRemoveEntity_1() {
        SwipeCard e = (SwipeCard) getEntity("SwipeCard");
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new SwipeCard(null, null, 0, 0, 0, 0, 0, 0);
        }
        int uid = e.getUID();
        gameWorld.removeMovableEntity(e);
        assertFalse(gameWorld.getMoveableEntities().containsKey(uid));
    }

    /**
     * Test remove entity 2.
     */
    @Test
    public void testRemoveEntity_2() {
        FlashDrive e = (FlashDrive) getEntity("FlashDrive");
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new FlashDrive(null, null, 0, 0, 0, 0, 0, 0, "extImg0");
        }
        int uid = e.getUID();
        gameWorld.removeMovableEntity(e);
        assertFalse(gameWorld.getMoveableEntities().containsKey(uid));
    }

    /**
     * Test remove entity 3.
     */
    @Test
    public void testRemoveEntity_3() {
        Commit e = (Commit) getEntity("Commit");
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new Commit(null, null, 0, 0, 0, 0, 0);
        }
        int uid = e.getUID();
        gameWorld.removeMovableEntity(e);
        assertFalse(gameWorld.getMoveableEntities().containsKey(uid));
    }

    /**
     * Test remove entity 4.
     */
    @Test
    public void testRemoveEntity_4() {
        ReadMe e = (ReadMe) getEntity("ReadMe");
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new ReadMe(null, null, 0, 0, 0, 0, 0, 0, "readme10");
        }
        int uid = e.getUID();
        gameWorld.removeMovableEntity(e);
        assertFalse(gameWorld.getMoveableEntities().containsKey(uid));
    }

    /**
     * Test add commit.
     */
    @Test
    public void testAddCommit() {
        int size = gameWorld.getMoveableEntities().size();
        gameWorld.addCommit();
        assertEquals(size + 1, gameWorld.getMoveableEntities().size());
    }

    /**
     * Test add to inventory from game world 1.
     */
    @Test
    public void testAddToInventoryFromGameWorld_1() {
        Inventory i = gameWorld.getInventory();
        ReadMe e = (ReadMe) getEntity("ReadMe");
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new ReadMe(null, null, 0, 0, 0, 0, 0, 0, "readme10");
        }
        int used = i.getStorageUsed();
        i.setStorageUsed(0);
        assertTrue(gameWorld.addToInventory(e));
        i.setStorageUsed(used);  //reset storage value
    }

    /**
     * Test add to inventory from game world 2.
     */
    @Test
    public void testAddToInventoryFromGameWorld_2() {
        Inventory i = gameWorld.getInventory();
        FlashDrive e = (FlashDrive) getEntity("FlashDrive");
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new FlashDrive(null, null, 0, 0, 0, 0, 0, 0, "extImg0");
        }
        int used = i.getStorageUsed();
        i.setStorageUsed(Inventory.MAX_STORAGE_SIZE);
        assertFalse(gameWorld.addToInventory(e));
        i.setStorageUsed(used);  //reset storage value
    }

    /**
     * Test game world remove from inventory 1.
     */
    @Test
    public void testGameWorldRemoveFromInventory_1() {
        // add item first
        Inventory i = gameWorld.getInventory();
        ReadMe e = (ReadMe) getEntity("ReadMe");
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new ReadMe(null, null, 0, 0, 0, 0, 0, 0, "readme10");
        }
        int used = i.getStorageUsed();
        i.setStorageUsed(0);
        gameWorld.addToInventory(e);

        // now test remove from inventory method
        gameWorld.removeFromInventory(e);
        assertTrue(gameWorld.getMoveableEntities().containsKey(e.getUID()));
        i.setStorageUsed(used);  //reset storage value
    }

    /**
     * Test game world remove from inventory 2.
     */
    @Test
    public void testGameWorldRemoveFromInventory_2() {
        // add item first
        Inventory i = gameWorld.getInventory();
        FlashDrive e = (FlashDrive) getEntity("FlashDrive");
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new FlashDrive(null, null, 0, 0, 0, 0, 0, 0, "extImg0");
        }
        int used = i.getStorageUsed();
        i.setStorageUsed(0);
        gameWorld.addToInventory(e);

        // now test remove from inventory method
        gameWorld.removeFromInventory(e);
        assertTrue(gameWorld.getMoveableEntities().containsKey(e.getUID()));
        i.setStorageUsed(used);  //reset storage value
    }


    /**
     * Test add card.
     */
    @Test
    public void testAddCard() {
        SwipeCard e = (SwipeCard) getEntity("SwipeCard");
        // if can't find item in entities map, create dummy item
        if (e == null) {
            e = new SwipeCard(null, null, 0, 0, 0, 0, 0, 0);
        }
        gameWorld.addCard(e);
        assertTrue(gameWorld.getSwipeCards().contains(e));
    }

    @Test
    public void testIncrementPatch_1() {
        int prog = gameWorld.getProgress();
        gameWorld.incrementPatch();
        assertTrue(prog < gameWorld.getProgress());
    }

    @Test
    public void testIncrementPatch_2() {
        gameWorld.setProgress(GameWorld.MAX_PROGRESS);
        gameWorld.incrementPatch();
        assertTrue(gameWorld.canApplyPatch());
    }

    @Test
    public void testUpdateScore() {
        int oldScore = gameWorld.getScore();
        int addScore = 5;
        gameWorld.updateScore(addScore);
        assertEquals(oldScore + addScore, gameWorld.getScore());
    }

    @Test
    public void testTeleportToOffice() {
        gameWorld.teleportToOffice();
        assertFalse(gameWorld.isOutside());
    }

    @Test
    public void testTeleportToOutside() {
        gameWorld.teleportToOutside();
        assertTrue(gameWorld.isOutside());
    }


    // ------------------------------------------------------
    // PLAYER TESTS
    // ------------------------------------------------------

    /**
     * Test player can interact.
     */
    @Test
    public void testPlayerCanInteract() {
        Player p = gameWorld.getPlayer();
        assertFalse(p.canInteract());
    }

    /**
     * Test player interact.
     */
    @Test
    public void testPlayerInteract() {
        Player p = gameWorld.getPlayer();
        assertTrue(p.interact(gameWorld) == 15);
    }

    /**
     * Test player type.
     */
    @Test
    public void testPlayerType() {
        Player p = gameWorld.getPlayer();
        assertTrue(p.getType().equals("Player"));
    }

    // ------------------------------------------------------
    // HELPER METHODS
    // ------------------------------------------------------

    /**
     * Helper method to find a random item of given type in the game world
     * movable entities map
     *
     * @param type class name of movable entity to find
     * @return a movable entity that is in the game world that is of the
     * given type
     */
    private MovableEntity getEntity(String type) {
        Map<Integer, MovableEntity> movableEntities = gameWorld.getMoveableEntities();
        for (MovableEntity e : movableEntities.values()) {
            if (e.getType().equals(type)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Helper method to check if a swipe card with the matching
     * cardId has been collected
     *
     * @param cardID to match
     * @return true if swipe card collected, false otherwise
     */
    private boolean checkSwipeCard(int cardID) {
        ArrayList<SwipeCard> cards = gameWorld.getSwipeCards();
        for (SwipeCard s : cards) {
            if (s.matchID(cardID)) {
                return true;
            }
        }
        return false;
    }
}
