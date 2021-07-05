package gameengine.builder;

import gameengine.app.GameSetting;
import gameengine.ia.TaskManager;
import gameengine.input.Input;
import gameengine.render.SceneManager;
import gameengine.world.GameWorld;

public class Builder {

	public static void createSingleton() {
		GameSetting.createGameSetting();
		TaskManager.createTaskManager();
	}
	public static Input createInput() {
		return Input.createInput();
	}
	public static SceneManager createSceneManager(GameWorld world) {
		return SceneManager.createSceneManager(world);
	}
	/*public static void getSceneManager() {
		SceneManager.getSceneManager();
	}
	public static void getGameSetting() {
		GameSetting.getGameSetting();
	}
	public static void getTaskManager() {
		TaskManager.getTaskManager();
	}
	public static void getInput() {
		Input.getInput();
	}*/

}
