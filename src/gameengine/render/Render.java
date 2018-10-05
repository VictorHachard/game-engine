package gameengine.render;

import gameengine.constante.Constante;
import gameengine.entities.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Render {
	public static void drawGameObject(GraphicsContext context, GameObject gameObject,Camera camera) {
		
		if(gameObject.getTexture() == null) {
			context.setFill(Color.BLACK);
		}else {
			context.setFill(gameObject.getTexture().getImagePattern());
		}
		double zoom = camera.getZoom();
		double offsetX = (-camera.getPosition().getX()*Constante.GRID_SIZE-camera.getGameObjectBinded().getDimension().getWidth()/2)  *zoom +  camera.getGameSetting().getWidth()/2;
		double offsetY = (-camera.getPosition().getY()*Constante.GRID_SIZE-camera.getGameObjectBinded().getDimension().getHeight()/2) *zoom + camera.getGameSetting().getHeight()/2;
		double posX= (gameObject.getPosition().getX()*gameObject.getDimension().getWidth())*zoom+offsetX;
		double posY= (gameObject.getPosition().getY()*gameObject.getDimension().getHeight())*zoom+offsetY;
		context.fillRect(posX,posY,
						 gameObject.getDimension().getWidth()*zoom, 
						 gameObject.getDimension().getHeight()*zoom);
	}
	
	public static void drawLayout() {
		
	}
}
