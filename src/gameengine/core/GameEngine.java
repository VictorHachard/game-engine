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
     * Methode gérant les updates du jeu.
     */
	public void update() {
    	GameObject g = gameWorld.getLevel().getLstGameObject().get(0);
    	Point2D p = g.getPosition();
    	p = new Point2D(p.getX()+0.02,p.getY()+0.02);
    	g.at(p);
    	sceneManager.getCamera().update();
	}
	/**
	 * Méthode permettant de rendre la scène.
	 */
    public void render() {
    	sceneManager.drawScene(gameWorld);
    }
}
