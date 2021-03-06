package model.entities.movableEntity;

import model.GameWorld;
import model.entities.Entity;
import model.models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

/**
 * This class represents a movable entity in the game. This is an
 * entity that's position can change throughout the course of the game,
 * and that can be interacted with by the player.
 *
 * @author Divya Patel - 300304450
 */
public abstract class MovableEntity extends Entity {

    /**
     * The constant GRAVITY.
     */
    protected static final float GRAVITY = -50;
    private final int UID; // for networking

    /**
     * Instantiates a MovableEntity.
     *
     * @param model    the model
     * @param position of entity
     * @param rotX     x rotation of entity
     * @param rotY     y rotation of entity
     * @param rotZ     z rotation of entity
     * @param scale    size of entity
     * @param id       unique id for networking
     */
    public MovableEntity(TexturedModel model, Vector3f position, float rotX,
                         float rotY, float rotZ, float scale, int id) {
        super(model, position, rotX, rotY, rotZ, scale);
        this.UID = id;
    }

    /**
     * Instantiates a MovableEntity.
     *
     * @param model        the model
     * @param position     of entity
     * @param rotX         x rotation of entity
     * @param rotY         y rotation of entity
     * @param rotZ         z rotation of entity
     * @param scale        size of entity
     * @param textureIndex index for atlassing
     * @param id           unique id for networking
     */
    public MovableEntity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale,
                         int textureIndex, int id) {
        super(model, position, rotX, rotY, rotZ, scale, textureIndex);
        this.UID = id;
    }

    /**
     * Gets uid.
     *
     * @return ID number of the item
     */
    public int getUID() {
        return this.UID;
    }

    /**
     * All movable entities may be able to be interacted
     * with. This method will carry out the relevant
     * interactions with the player, and update
     * the game state (score and progress) accordingly.
     *
     * @param game game
     * @return int int
     */
    public abstract int interact(GameWorld game);

    /**
     * Can interact boolean.
     *
     * @return true if entity can be interacted with
     */
    public abstract boolean canInteract();

    // ---------------------------------------------------------
    // Getters for SAVING:
    //
    // May be related specifically to certain implementations
    // however needed here for save and load information
    // ---------------------------------------------------------

    /**
     * Gets name.
     *
     * @return name of image files
     */
    public String getName() {
        return null;
    }

    /**
     * Gets type.
     *
     * @return name of the class
     */
    public abstract String getType();

    /**
     * Gets card id.
     *
     * @return id card for laptop items
     */
    public int getCardID() {
        return -1;
    }

    /**
     * Gets card num.
     *
     * @return card number of swipe cards (the cards Id)
     */
    public int getCardNum() {
        return -1;
    }

    /**
     * Gets has code.
     *
     * @return true if a laptop has code
     */
    public boolean getHasCode() {
        return false;
    }
}
