package gameengine.render;

import gameengine.entities.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Render {
	public static void drawGameObject(GraphicsContext context,GameObject gameObject) {
		context.setStroke(Color.BLACK);
		context.strokeRect(gameObject.getPosition().getX()*gameObject.getDimension().getWidth(),
						 gameObject.getPosition().getY()*gameObject.getDimension().getHeight(), 
						 gameObject.getDimension().getWidth(), 
						 gameObject.getDimension().getHeight());
	}
}
