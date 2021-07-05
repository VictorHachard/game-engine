package gameengine.entities.builder;

import gameengine.entities.GameObject;
import gameengine.physic.Point2D;

public class BuilderGameObject {

	/*
	 * GameObject creator.
	 */
	public static GameObject createGameObject() {
		GameObject g = new GameObject();
		g.with(new Point2D(0.0,0.0));
		return g;
	}
	
}
