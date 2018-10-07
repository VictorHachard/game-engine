package gameengine.render;

import java.util.List;

import gameengine.constante.Constante;
import gameengine.entities.GameObject;
import gameengine.particule.Particle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;

public class Render {
	public static void drawGameObject(GraphicsContext context, GameObject gameObject,Camera camera) {
		context.setGlobalBlendMode(BlendMode.SRC_OVER);
		if(gameObject.getTexture() == null) {
			context.setFill(Color.BLACK);
		}else {
			context.setFill(gameObject.getTexture().getImagePattern());
		}
		double zoom = camera.getZoom();
		double offsetX = (-camera.getPosition().getX()*Constante.GRID_SIZE-camera.getGameObjectBinded().getDimension().getWidth()/2)  *zoom +  camera.getGameSetting().getWidth()/2;
		double offsetY = (-camera.getPosition().getY()*Constante.GRID_SIZE-camera.getGameObjectBinded().getDimension().getHeight()/2) *zoom + camera.getGameSetting().getHeight()/2;
		double posX= (gameObject.getPosition().getX()*Constante.GRID_SIZE)*zoom+offsetX;
		double posY= (gameObject.getPosition().getY()*Constante.GRID_SIZE)*zoom+offsetY;
		context.fillRect(posX,posY,
						 gameObject.getDimension().getWidth()*zoom, 
						 gameObject.getDimension().getHeight()*zoom);
	}
	
	public static void drawParticle(GraphicsContext context,List<Particle> lstParticle, Camera camera) {
		for(Particle p : lstParticle) {
			context.setGlobalAlpha(p.getLife());
			context.setGlobalBlendMode(p.getBlendMode());
			context.setFill(p.getColor());
			double zoom = camera.getZoom();
			double offsetX = (-camera.getPosition().getX()*Constante.GRID_SIZE-camera.getGameObjectBinded().getDimension().getWidth()/2)  *zoom +  camera.getGameSetting().getWidth()/2;
			double offsetY = (-camera.getPosition().getY()*Constante.GRID_SIZE-camera.getGameObjectBinded().getDimension().getHeight()/2) *zoom + camera.getGameSetting().getHeight()/2;
			double posX= (p.getPosition().getX()*Constante.GRID_SIZE)*zoom+offsetX;
			double posY= (p.getPosition().getY()*Constante.GRID_SIZE)*zoom+offsetY;
			context.fillOval(posX, posY, p.getRadius(), p.getRadius()*zoom);
			
		}
	}
	public static void drawLayout() {
		
	}
}
