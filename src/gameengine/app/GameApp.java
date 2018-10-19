package gameengine.app;

import gameengine.builder.Builder;
import gameengine.core.GameEngine;
import gameengine.core.LoaderResources;
import gameengine.ia.TaskManager;
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
	private SceneManager manager;
	private GameWorld gameWorld;
	private GameEngine gameEngine;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Builder.createSingleton();
		initSetting(GameSetting.getGameSetting());
		LoaderResources.getLoaderResources();
		initGameObject();
		initGameWorld();
		initLevel();
		Camera camera = new Camera();
		initCamera(camera);
		manager = Builder.createSceneManager(gameWorld);
		manager.setCamera(camera);
		initUI();
		Builder.createInput();
		initInput();
		Input.getInput().manageEvent();
		initGameEngine(TaskManager.getTaskManager());
		initCollision();
		initParticle();
		initTest();
		System.out.println("FIN INIT");
	}
	
	private void initGameEngine(TaskManager mt) {
		gameEngine = new GameEngine(gameWorld);
	}
	
	public void initTest() {}
	public void initLevel() {}
	public abstract void initCamera(Camera camera);
	public abstract void initParticle();
	public abstract void initInput();
	public abstract void initCollision();
	public abstract void initUI();
	public abstract void initSetting(GameSetting s);
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
		return SceneManager.getSceneManager();
	}

	public GameEngine getGameEngine() {
		return gameEngine;
	}

	public Input getInput() {
		return Input.getInput();
	}
	
}
