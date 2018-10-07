package gameengine.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import gameengine.core.gameloop.VariableSteps;
import gameengine.entities.GameObject;
import gameengine.physic.collision.Collision;
import gameengine.physic.collision.CollisionManager;
import gameengine.render.SceneManager;
import gameengine.world.GameWorld;

public class GameEngine {
	private  VariableSteps v;
	private SceneManager sceneManager;
	private GameWorld gameWorld;
	private CollisionManager cm;
	public GameEngine(SceneManager sc , GameWorld gw) {
		sceneManager = sc;
		gameWorld = gw;
		cm = new CollisionManager();
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
		gameWorld.getLevel().getLstGameObject().forEach(GameObject::update);
		sceneManager.getParticuleEngine().update();
    	sceneManager.getCamera().update();
    	managerCollision();
	}
	
	public void managerCollision() {
		List<GameObject> lstGa = gameWorld.getLevel().getLstGameObject();
		List<Collision> lstActualCollision = new ArrayList<>();
		for (GameObject gameObject1 : lstGa) {
			for (GameObject gameObject2 : lstGa) {
				if(gameObject1 != gameObject2 && gameObject1.getHitbox() != null && gameObject2.getHitbox() != null && gameObject1.getzIndex() >0 && gameObject2.getzIndex()>0) {
					if(gameObject1.getHitbox().isCollide(gameObject2.getHitbox())) {
						Collision c =  new Collision(gameObject1, gameObject2);
						lstActualCollision.add(c);
					}
				}
			}
		}
		cm.manageCollision(lstActualCollision);
		
	}
	
	public CollisionManager getCm() {
		return cm;
	}

	/**
	 * Méthode permettant de rendre la scène.
	 */
    public void render() {
    	sceneManager.drawScene();
    }
}
