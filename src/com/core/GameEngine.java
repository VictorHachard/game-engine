package gameengine.core;

import gameengine.app.DebugBox;
import gameengine.core.gameloop.VariableSteps;
import gameengine.entities.GameObject;
import gameengine.ia.TaskManager;
import gameengine.physic.collision.CollisionManager;
import gameengine.render.SceneManager;
import gameengine.world.GameWorld;

import java.util.function.Consumer;

public class GameEngine {
	private  VariableSteps v;
	private GameWorld gameWorld;
	private CollisionManager cm;
	public GameEngine(GameWorld gw) {
		gameWorld = gw;
		cm = new CollisionManager(gameWorld);
        Consumer<Float> updater = secondsElapsed -> update();
        Runnable renderer = () -> render();
        Consumer<Integer> fpsReporter = fps -> fpsReporter(fps);
        v = new VariableSteps(updater, renderer, fpsReporter);
        v.start();
	}
	
    private void fpsReporter(Integer fps) {
    	if (DebugBox.getGameSetting() != null) {
    		DebugBox.getGameSetting().setFPS(fps.toString());
    	}
    	System.out.println(fps);
	}

    public void start() {
    	v.start();
    }
    public void stop() {
    	v.stop();
    }
    /**
     * Methode g�rant les updates du jeu.
     */
	public void update() {
		TaskManager.getTaskManager().updateTask();
		
    	cm.update();
		gameWorld.getLevel().getLstGameObject().forEach(GameObject::update);
		SceneManager.getSceneManager().getParticuleEngine().update();
		SceneManager.getSceneManager().getCamera().update();

	}
	
	public CollisionManager getCm() {
		return cm;
	}

	/**
	 * M�thode permettant de rendre la sc�ne.
	 */
    public void render() {
    	SceneManager.getSceneManager().drawScene();
    }
}
