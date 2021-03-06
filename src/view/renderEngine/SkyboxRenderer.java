package view.renderEngine;

import model.GameWorld;
import model.entities.Camera;
import model.models.RawModel;
import model.shaders.skybox.SkyboxShader;
import model.toolbox.Loader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Delegate renderer to handle the rendering of the world's skybox
 * <p/>
 * A skybox is a cube of png images wrapped around the players position in the game world
 * to simulate the feeling of a sky.
 *
 * @author Marcel van Workum - 300313949
 */
public class SkyboxRenderer {

    /**
     * This has to be in a specific order to work as the {@link Loader#loadCubeMap(String[])}
     * method loads the textures very specifically
     * <p/>
     * order is: right -> left -> top -> bottom -> back ->front
     */
    private static final String[] TEXTURE_FILES = {"right", "left", "top", "bottom", "back", "front"};
    private static final String[] NIGHT_TEXTURE_FILES = {"nightRight", "nightLeft", "nightTop", "nightBottom",
            "nightBack", "nightFront"};

    private static final float SIZE = 500f;

    private RawModel cubeModel;
    private int texture;
    private int nightTexture;
    private SkyboxShader shader;

    /**
     * Constructor
     *
     * @param projectionMatrix 4x4 transformation matrix
     */
    public SkyboxRenderer(Matrix4f projectionMatrix) {

        // Loads pngs into VAO
        cubeModel = Loader.loadToVAO(VERTICES, 3);
        texture = Loader.loadCubeMap(TEXTURE_FILES);
        nightTexture = Loader.loadCubeMap(NIGHT_TEXTURE_FILES);

        // Creates the shader, loads the 4x4 and stops
        shader = new SkyboxShader();
        shader.start();
        shader.connectTextureUnits();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    /**
     * Renders the skybox around the camera
     *
     * @param camera Represents the center point of the skybox cube
     * @param r      the r
     * @param g      the g
     * @param b      the b
     */
    public void render(Camera camera, float r, float g, float b) {
        shader.start();
        shader.loadViewMatrix(camera);
        shader.loadFogColour(r, g, b);

        // need to bind VAO of cube
        GL30.glBindVertexArray(cubeModel.getVaoID());

        // positions are stored in VAO attrib 0 so need to enable it
        GL20.glEnableVertexAttribArray(0);

        // activate texture and bind it to the texture id
        bindTextures();

        // now actually draw the skybox
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, cubeModel.getVertexCount());

        // clean up bindings
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.stop();
    }

    /**
     * Binds the skybox textures and slowly rotates them/blends them together based on the game world time
     */
    private void bindTextures() {
        int texture1;
        int texture2;
        float blendFactor;

        // Night
        if (GameWorld.getWorldTime() >= 0 && GameWorld.getWorldTime() < 5000) {
            texture1 = nightTexture;
            texture2 = nightTexture;
            blendFactor = (GameWorld.getWorldTime()) / (5000);
        }

        // Early morning
        else if (GameWorld.getWorldTime() >= 5000 && GameWorld.getWorldTime() < 8000) {
            texture1 = nightTexture;
            texture2 = texture;
            blendFactor = (GameWorld.getWorldTime() - 5000) / (8000 - 5000);
        }

        // Daytime
        else if (GameWorld.getWorldTime() >= 8000 && GameWorld.getWorldTime() < 21000) {
            texture1 = texture;
            texture2 = texture;
            blendFactor = (GameWorld.getWorldTime() - 8000) / (21000 - 8000);
        }

        // Evening
        else {
            texture1 = texture;
            texture2 = nightTexture;
            blendFactor = (GameWorld.getWorldTime() - 21000) / (24000 - 21000);
        }

        // finally bind the textures
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texture1);
        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texture2);
        shader.loadBlendFactor(blendFactor);
    }

    // This is ugly, but essentially it's pointing to all
    // the corners of the cube, so the the direction vector
    // knows where to render each pixel of the skybox
    private static final float[] VERTICES = {
            -SIZE, SIZE, -SIZE,
            -SIZE, -SIZE, -SIZE,
            SIZE, -SIZE, -SIZE,
            SIZE, -SIZE, -SIZE,
            SIZE, SIZE, -SIZE,
            -SIZE, SIZE, -SIZE,

            -SIZE, -SIZE, SIZE,
            -SIZE, -SIZE, -SIZE,
            -SIZE, SIZE, -SIZE,
            -SIZE, SIZE, -SIZE,
            -SIZE, SIZE, SIZE,
            -SIZE, -SIZE, SIZE,

            SIZE, -SIZE, -SIZE,
            SIZE, -SIZE, SIZE,
            SIZE, SIZE, SIZE,
            SIZE, SIZE, SIZE,
            SIZE, SIZE, -SIZE,
            SIZE, -SIZE, -SIZE,

            -SIZE, -SIZE, SIZE,
            -SIZE, SIZE, SIZE,
            SIZE, SIZE, SIZE,
            SIZE, SIZE, SIZE,
            SIZE, -SIZE, SIZE,
            -SIZE, -SIZE, SIZE,

            -SIZE, SIZE, -SIZE,
            SIZE, SIZE, -SIZE,
            SIZE, SIZE, SIZE,
            SIZE, SIZE, SIZE,
            -SIZE, SIZE, SIZE,
            -SIZE, SIZE, -SIZE,

            -SIZE, -SIZE, -SIZE,
            -SIZE, -SIZE, SIZE,
            SIZE, -SIZE, -SIZE,
            SIZE, -SIZE, -SIZE,
            -SIZE, -SIZE, SIZE,
            SIZE, -SIZE, SIZE
    };
}