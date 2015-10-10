package model.factories;

import model.entities.movableEntity.LaptopItem;
import model.guiComponents.Inventory;
import model.textures.GuiTexture;
import model.toolbox.Loader;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Factory Game for creating Gui Components
 *
 * @author Marcel van Workum
 * @author Ellie
 * @author Divya
 */
public class GuiFactory {

	private static final String GUI_PATH = "gui/";
	private static final String ITEM_PATH = "itemImages/";
	
	private final Loader loader;
	private GuiTexture inventoryScreen;
	private GuiTexture interactMessage;
	private GuiTexture infoPanel;
	private GuiTexture lostScreen;
	private GuiTexture codeCompiled;

	/**
	 * Create the Gui factory passing in the object loader
	 *
	 * @param loader
	 *            Object loader
	 */
	public GuiFactory(Loader loader) {
		this.loader = loader;

		loadImages();
	}

	private void loadImages() {
		inventoryScreen = makeGuiTexture("blankInventoryScreen", new Vector2f(0f, 0f), new Vector2f(0.8f, 1f));
		interactMessage = makeGuiTexture("pressEToInteract", new Vector2f(0f, -0.3f), new Vector2f(0.5f, 0.5f));
		infoPanel = makeGuiTexture("topLeftCornerGUI", new Vector2f(-0.6875f, 0.8f), new Vector2f(0.4f, 0.4f));
		lostScreen = makeGuiTexture("youLostScreen", new Vector2f(0f, 0f), new Vector2f(1f, 1f));
		codeCompiled = makeGuiTexture("codeCompiledMessage", new Vector2f(0f, 0f), new Vector2f(1f, 1f));
	}



	/**
	 * Creates a {@link GuiTexture} with the specified texture, position and
	 * scale.
	 *
	 * @param textureName
	 *            Texture
	 * @param position
	 *            Position on the display x(0-1) y(0-1)
	 * @param scale
	 *            scale of the texture on display x(0-1) y(0-1)
	 *
	 * @return The GuiTexture created
	 */

	public GuiTexture makeGuiTexture(String textureName, Vector2f position, Vector2f scale) {
		return new GuiTexture(loader.loadTexture(GUI_PATH + textureName), position, scale);
	}
	
	public GuiTexture makeItemTexture(String textureName, Vector2f position, Vector2f scale) {
		return new GuiTexture(loader.loadTexture(ITEM_PATH + textureName), position, scale);
	}

	public ArrayList<GuiTexture> makeInventory(Inventory inventory) {
		ArrayList<GuiTexture> inventoryImages = new ArrayList<GuiTexture>();
		inventoryImages.add(inventoryScreen);
		if (inventory.getLaptopDisplay() != null) {
			LaptopItem[][] items = inventory.getLaptopDisplay();
			for(int x = 0; x < items.length; x++){
				for(int y = 0; y < items[0].length; y++){
					if(items[x][y] != null){
						float xPos = Inventory.START_X + y*Inventory.ICON_SCALE.getX()*2f;
						float yPos = Inventory.START_Y - x*Inventory.ICON_SCALE.getY()*1.5f;
						Vector2f pos = new Vector2f(xPos, yPos);
						GuiTexture img = makeItemTexture(items[x][y].getName(), pos, Inventory.ICON_SCALE);
						inventoryImages.add(img);
						
						// highlight selected image
						if(items[x][y] == inventory.getSelected()){
							GuiTexture select = makeItemTexture("selectBox", pos, Inventory.ICON_SCALE);
							inventoryImages.add(select);
						}
					}
				}
			}
		}

		return inventoryImages;

	}
	
	public List<GuiTexture> makeLostScreen(){
		List<GuiTexture> lostScreens = new ArrayList<GuiTexture>();
		lostScreens.add(lostScreen);
		return lostScreens;
	}

	public GuiTexture getProgress(int progress) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public GuiTexture getScore(int score){
		return null;
	}

	public List<GuiTexture> makePopUpInteract(Vector3f position) {
		List<GuiTexture> message = new ArrayList<GuiTexture>();
		message.add(interactMessage);
		return message;

	}

	public List<GuiTexture> getInfoPanel() {
		List<GuiTexture> infoPanels = new ArrayList<GuiTexture>();
		infoPanels.add(infoPanel);
		return infoPanels;
	}
	
	public List<GuiTexture> getCodeCompiledMessage(){
		List<GuiTexture> ccMessage = new ArrayList<GuiTexture>();
		ccMessage.add(codeCompiled);
		return ccMessage;
	}
}
