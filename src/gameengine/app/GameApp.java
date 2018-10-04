package gameengine.app;

import gameengine.render.SceneManager;
import gameengine.world.GameWorld;
import javafx.application.Application;
import javafx.stage.Stage;

public abstract class GameApp extends Application{
	private GameSetting setting;
	private SceneManager manager;
	private GameWorld gameWorld;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		setting = new GameSetting();
		initSetting(setting);
		manager = new SceneManager(setting);
		initGameWorld();
		initLevel();
		
		
		
		initTest();
	}
	
	public void initTest() {
		
	}
	
	public void initLevel() {
		
	}
	public void initGameWorld() {
		gameWorld = new GameWorld();
	}
	
	
	public GameWorld getGameWorld() {
		return gameWorld;
	}

	public SceneManager getManager() {
		return manager;
	}

	public abstract void initSetting(GameSetting setting);
	

	
}
