package gameengine.render;

import java.util.List;

import gameengine.app.GameSetting;
import gameengine.entities.GameObject;
import gameengine.world.GameWorld;
import gameengine.world.Level;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author Main
 *
 */
public class SceneManager {
	private Stage stage;
	private Scene scene;
	private Pane root;
	private GraphicsContext graphicContext; //L'affichage du monde.
	private Camera camera;
	public SceneManager(GameSetting setting,Camera camera) {
		stage = new Stage();
		this.camera = camera;
		root = new Pane();
		scene = new Scene(root, setting.getWidth(), setting.getHeight());
		Canvas c = new Canvas(setting.getWidth(), setting.getHeight());
		graphicContext = c.getGraphicsContext2D();
		root.getChildren().add(c);
		stage.setTitle(setting.getTitle());
		stage.setScene(scene);
		stage.show();
	}
	
	public void drawScene(GameWorld gameWorld) {
		graphicContext.clearRect(0, 0, scene.getWidth(), scene.getHeight());
		Level l = gameWorld.getLevel();
		List<GameObject> lstGameObject = l.getLstGameObject();
		for (GameObject gameObject : lstGameObject) {
			Render.drawGameObject(graphicContext, gameObject,camera);
		}
	}
	
	/**
	 * Add a layout to the root.
	 * @param p a Pane(s).
	 */
	public void addLayout(Node... p) {
		 for (Node ps : p) {
			 root.getChildren().add(ps);
		 }
	}
	
	/**
	 * Remove a layout to the root.
	 * @param p a Pane(s).
	 */
	public void removeLayout(Node... p) {
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
