package gameengine.render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import gameengine.app.GameSetting;
import gameengine.constante.Constante;
import gameengine.entities.GameObject;
import gameengine.particule.Emitter;
import gameengine.particule.ParticuleEngine;
import gameengine.physic.Point2D;
import gameengine.world.GameWorld;
import gameengine.world.Level;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Class that handle the display on the screen.
 * @author Main
 *
 */
public class SceneManager {
	private Stage stage;
	private Scene scene;
	private Pane root;
	private GraphicsContext graphicContext; //L'affichage du monde.
	private Camera camera;
	List<GameObject> lstGameObjectToRender;
	private GameSetting setting;
	private ParticuleEngine particuleEngine;
	private GameWorld gameWorld;
	public SceneManager(GameSetting setting,Camera camera,GameWorld world) {
		gameWorld = world;
		this.setting = setting;
		stage = new Stage();
		this.camera = camera;
		particuleEngine = new ParticuleEngine(world.getLevel());
		lstGameObjectToRender = new ArrayList<>();
		root = new Pane();
		scene = new Scene(root, setting.getWidth(), setting.getHeight());
		Canvas c = new Canvas(setting.getWidth(), setting.getHeight());
		graphicContext = c.getGraphicsContext2D();
		root.getChildren().add(c);
		stage.setTitle(setting.getTitle());
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Draw the game object, particle, layout.
	 */
	public void drawScene() {
		Level l = gameWorld.getLevel();
		initListToRender(l);
		sort(lstGameObjectToRender);
		graphicContext.clearRect(0, 0, scene.getWidth(), scene.getHeight());
		for (GameObject gameObject : lstGameObjectToRender) {
			if(gameObject instanceof Emitter) {
				Render.drawParticle(graphicContext, ((Emitter) gameObject).getLstParticule(), camera);
			} else {
				Render.drawGameObject(graphicContext, gameObject,camera);				
			}

		}
	}

	public ParticuleEngine getParticuleEngine() {
		return particuleEngine;
	}

	/**
	 * Create a list of object to render (if there positions math where the camera is positioning).
	 * @param l The level to render.
	 */
	private void initListToRender(Level l) {
		lstGameObjectToRender.clear();
		List<GameObject> lstGameObject = l.getLstGameObject();
		Point2D gc = camera.getGameObjectBinded().getPosition();
		double xStart = gc.getX()-(setting.getWidth()/(2*Constante.GRID_SIZE))-1;
		double xEnd = gc.getX()+(setting.getWidth()/(2*Constante.GRID_SIZE))+1;
		double yStart = gc.getY()-(setting.getHeight()/(2*Constante.GRID_SIZE))-1;
		double yEnd = gc.getY()+(setting.getHeight()/(2*Constante.GRID_SIZE))+1;
		for (GameObject gameObject : lstGameObject) {
			if (gameObject.getPosition().getX() > xStart && gameObject.getPosition().getX() < xEnd && 
					gameObject.getPosition().getY() > yStart && gameObject.getPosition().getY() < yEnd) {
						lstGameObjectToRender.add(gameObject);
			}
		}
		
	}
	
	/**
	 * Sort the list to render (ZIndex and the y axis).
	 * @param l The list of game object to sort.
	 */
	private void sort(List<GameObject> l) {
        Collections.sort(l, new Comparator<GameObject>() {
        	@Override
        	public int compare(GameObject o1, GameObject o2) {
        		if(o1.getzIndex()<o2.getzIndex()) {
        			return -1;
        		}
        		if(o1.getzIndex().equals(o2.getzIndex())) {
        			if(o1.getPosition().getY()+o1.getDimension().getHeight()<o2.getPosition().getY()+o2.getDimension().getHeight()) {
        				return -1;
        			} else if(o1.getPosition().getY()+o1.getDimension().getHeight()>o2.getPosition().getY()+o2.getDimension().getHeight()) {
        				return 1;
        			}
        			return 0;
        		}
        		return 1;
        	}
		});
	}

	/**
	 * Add a layout to the root.
	 * @param p a Node(s).
	 */
	public void addUI(Node... p) {
		 for (Node ps : p) {
			 root.getChildren().add(ps);
		 }
	}
	/**
	 * Remove a layout to the root.
	 * @param p a Node(s).
	 */
	public void removeUI(Node... p) {
		for (Node ps : p) {
			root.getChildren().remove(ps);
		 }		
	}
	public Camera getCamera() {
		return camera;
	}
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
}
