package gameengine.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import gameengine.core.gameloop.VariableSteps;
import gameengine.entities.GameObject;
import gameengine.ia.TaskManager;
import gameengine.physic.collision.Collision;
import gameengine.physic.collision.CollisionManager;
import gameengine.render.SceneManager;
import gameengine.world.GameWorld;

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
    	System.out.println(fps);
	}

    public void start() {
    	v.start();
    }
    public void stop() {
    	v.stop();
    }
    /**
     * Methode gérant les updates du jeu.
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
	 * Méthode permettant de rendre la scène.
	 */
    public void render() {
    	SceneManager.getSceneManager().drawScene();
    }
}
