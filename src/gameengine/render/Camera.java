package gameengine.render;

import gameengine.app.GameSetting;
import gameengine.entities.GameObject;
import gameengine.physic.Point2D;

/**
 * Class representing a camera.
 * @author Main
 *
 */
public class Camera {
	private Point2D position;
	private GameObject gameObjectBinded;
	private double zoom = 1;
	public Camera(Point2D pos){
		position = pos;
	}
	public Camera(){
		position = new Point2D(0.0,0.0);
	}
	public Point2D getPosition() {
		return position;
	}
	public void setPosition(Point2D position) {
		this.position = position;
	}
	public GameObject getGameObjectBinded() {
		return gameObjectBinded;
	}
	public void setGameObjectBinded(GameObject gameObjectBinded) {
		this.gameObjectBinded = gameObjectBinded;
	}
	public double getZoom() {
		return zoom;
	}
	public void setZoom(double zoom) {
		this.zoom = zoom;
	}
	/**
	 * Update the positions of the camera.
	 */
	public void update() {
		if(gameObjectBinded != null) {
			this.position.setX(gameObjectBinded.getPosition().getX());
			this.position.setY(gameObjectBinded.getPosition().getY());			
		}
	}
	
	
	
}
