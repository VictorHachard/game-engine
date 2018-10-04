package gameengine.render;

import java.util.List;

import gameengine.app.GameSetting;
import gameengine.entities.GameObject;
import gameengine.world.GameWorld;
import gameengine.world.Level;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SceneManager {
	
	private Stage stage;
	private Scene scene;
	private Pane root;
	private GraphicsContext graphicContext;
	public SceneManager(GameSetting setting) {
		stage = new Stage();
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
		Level l = gameWorld.getLevel();
		List<GameObject> lstGameObject = l.getLstGameObject();
		for (GameObject gameObject : lstGameObject) {
			Render.drawGameObject(graphicContext, gameObject);
		}
	}

}
