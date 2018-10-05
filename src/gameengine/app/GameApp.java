package gameengine.app;

import gameengine.core.GameEngine;
import gameengine.render.Camera;
import gameengine.render.SceneManager;
import gameengine.world.GameWorld;
import javafx.application.Application;
import javafx.stage.Stage;

public abstract class GameApp extends Application{
	private GameSetting setting;
	private SceneManager manager;
	private GameWorld gameWorld;
	private GameEngine gameEngine;
	@Override
	public void start(Stage primaryStage) throws Exception {
		setting = new GameSetting();
		initSetting(setting);
		initGameWorld();
		initLevel();
		
		
		Camera camera = new Camera(setting);
		initCamera(camera);
		manager = new SceneManager(setting,camera);



		initGameEngine();
		initTest();
	}
	
	private void initGameEngine() {
		gameEngine = new GameEngine(manager, gameWorld);
	}
	
	public void initTest() {}
	public void initLevel() {}
	public void initCamera(Camera camera) {}
	/**
	 * Create a game world.
	 */
	public void initGameWorld() {
		gameWorld = new GameWorld();
	}
	
	public GameWorld getGameWorld() {
		return gameWorld;
	}

	public SceneManager getManager() {
		return manager;
	}

	public GameEngine getGameEngine() {
		return gameEngine;
	}
	
	public abstract void initSetting(GameSetting setting);
	

	
}
