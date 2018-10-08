package gameengine.render;

import java.util.List;

import gameengine.constante.Constante;
import gameengine.entities.GameObject;
import gameengine.particule.Particle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;

/**
 * Class who drawing all the world, particles, layout on the screen.
 * @author Main
 *
 */
public class Render {
	/**
	 * Draw a game object on the correct place.
	 * @param context GraphicsContext the canvas to draw on.
	 * @param gameObject GameObject the game object to draw.
	 * @param camera Camera the camera.
	 */
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
	
	/**
	 * Draw a list of particle on the correct place.
	 * @param context GraphicsContext the canvas to draw on.
	 * @param lstParticle List<Particle> the list of particle to draw.
	 * @param camera Camera the camera.
	 */
	public static void drawParticle(GraphicsContext context, List<Particle> lstParticle, Camera camera) {
		for(Particle p : lstParticle) {
			context.setGlobalAlpha(p.getLife());
			context.setGlobalBlendMode(p.getBlendMode());
			context.setFill(p.getColor());
			double zoom = camera.getZoom();
			double offsetX = (-camera.getPosition().getX()*Constante.GRID_SIZE-camera.getGameObjectBinded().getDimension().getWidth()/2)  *zoom +  camera.getGameSetting().getWidth()/2;
			double offsetY = (-camera.getPosition().getY()*Constante.GRID_SIZE-camera.getGameObjectBinded().getDimension().getHeight()/2) *zoom + camera.getGameSetting().getHeight()/2;
			double posX= (p.getPosition().getX()*Constante.GRID_SIZE+Constante.GRID_SIZE/2)*zoom+offsetX;
			double posY= (p.getPosition().getY()*Constante.GRID_SIZE+Constante.GRID_SIZE/2)*zoom+offsetY;
			context.fillOval(posX, posY, p.getRadius(), p.getRadius()*zoom);
			
		}
	}
	public static void drawLayout() {
		
	}
}
