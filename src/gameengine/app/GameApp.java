package gameengine.app;

import gameengine.core.GameEngine;
import gameengine.input.Input;
import gameengine.render.Camera;
import gameengine.render.SceneManager;
import gameengine.world.GameWorld;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class 
 */
public abstract class GameApp extends Application {
	private GameSetting setting;
	private SceneManager manager;
	private GameWorld gameWorld;
	private GameEngine gameEngine;
	private Input input;
	@Override
	public void start(Stage primaryStage) throws Exception {
		setting = new GameSetting();
		initSetting(setting);
		initGameObject();
		initGameWorld();
		initLevel();		
		Camera camera = new Camera(setting);
		initCamera(camera);
		manager = new SceneManager(setting,camera,gameWorld);
		initUI();
		enableInput();
		initGameEngine();
		initCollision();
		initParticle();
		initTest();
	}
	
	private void initGameEngine() {
		gameEngine = new GameEngine(manager, gameWorld);
	}
	
	public void initTest() {}
	public void initLevel() {}
	public void initCamera(Camera camera) {}
	public void initParticle() {}
	public void initInput() {}
	public void initCollision() {}
	public void initUI() {}
	public void enableInput() {
		input = new Input(manager.getScene());
		initInput();
		input.manageEvent();
	}
	/**
	 * Permet d'initialiser les différentes manère de créer un gameObject.
	 */
	public void initGameObject() {}
	
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

	public Input getInput() {
		return input;
	}
	public abstract void initSetting(GameSetting setting);
	
}
