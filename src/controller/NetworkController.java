package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import model.Network.Server;
import model.entities.movableEntity.Player;
import model.models.TexturedModel;

/**
 * Controller class to handle the delegations between the Model and View
 * package.
 *
 * Deals with Network logic
 *
 * @author Marcel van Workum
 */
public class NetworkController extends Thread {

	private GameController gameController;

	private ServerSocket serverSocket;
	private Server server;

	private boolean running;

	public NetworkController(GameController gameController) throws IOException {
		this.gameController = gameController;
		this.serverSocket = new ServerSocket(32768);
		this.running = true;
	}

	public void run() {
		while (running) {
			Socket socket = null;

			try {
				//System.out.println("Accepting...");
				socket = serverSocket.accept();
				server = new Server(socket, gameController);
				server.sendGameInfo();
				gameController.addPlayerServer();
				server.start();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void end() {
		running = false;
	}

}
