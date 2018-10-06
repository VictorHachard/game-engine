package gameengine.core;

import java.util.function.Consumer;

import gameengine.core.gameloop.VariableSteps;
import gameengine.entities.GameObject;
import gameengine.physic.Point2D;
import gameengine.render.SceneManager;
import gameengine.world.GameWorld;

public class GameEngine {
	private  VariableSteps v;
	private SceneManager sceneManager;
	private GameWorld gameWorld;
	public GameEngine(SceneManager sc , GameWorld gw) {
		sceneManager = sc;
		gameWorld = gw;
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
     * Methode g�rant les updates du jeu.
     */
	public void update() {
		gameWorld.getLevel().getLstGameObject().forEach(GameObject::update);
    	sceneManager.getCamera().update();
	}
	/**
	 * M�thode permettant de rendre la sc�ne.
	 */
    public void render() {
    	sceneManager.drawScene(gameWorld);
    }
}