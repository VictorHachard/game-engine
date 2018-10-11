package gameengine.builder;

import gameengine.app.GameSetting;
import gameengine.ia.TaskManager;
import gameengine.input.Input;

public class Builder {

	public static void createSingleton() {
		GameSetting.createGameSetting();
		Input.createInput();
		TaskManager.createTaskManager();
	}
	public static void getGameSetting() {
		GameSetting.getGameSetting();
	}
	public static void getTaskManager() {
		TaskManager.getTaskManager();
	}
	public static void getInput() {
		Input.getInput();
	}

}
