package gameengine.physic.collision;

import gameengine.entities.GameObject;

/**
 * 
 * @author Main
 *
 */
public abstract class HandleCollision {
	private String typeGameObject1;
	private String typeGameObject2;
	
	public HandleCollision(String typeGameObject1, String typeGameObject2) {
		this.typeGameObject1 = typeGameObject1;
		this.typeGameObject2 = typeGameObject2;
	}
	public String getTypeGameObject1() {
		return typeGameObject1;
	}
	public void setTypeGameObject1(String typeGameObject1) {
		this.typeGameObject1 = typeGameObject1;
	}
	public String getTypeGameObject2() {
		return typeGameObject2;
	}
	public void setTypeGameObject2(String typeGameObject2) {
		this.typeGameObject2 = typeGameObject2;
	}
	public abstract void onCollisionBegin(GameObject g1, GameObject g2);
	public abstract void onCollision(GameObject g1 , GameObject g2);
	public abstract void onCollisionEnd(GameObject g1,GameObject g2);
}
