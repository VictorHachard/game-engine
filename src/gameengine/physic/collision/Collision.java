package gameengine.physic.collision;

import gameengine.entities.GameObject;

/**
 * 
 * @author Main
 *
 */
public class Collision {
	private GameObject gameObject1;
	private GameObject gameObject2;
	public Collision(GameObject gameObject1, GameObject gameObject2) {
		this.gameObject1 = gameObject1;
		this.gameObject2 = gameObject2;
	}
	public GameObject getGameObject1() {
		return gameObject1;
	}
	public void setGameObject1(GameObject gameObject1) {
		this.gameObject1 = gameObject1;
	}
	public GameObject getGameObject2() {
		return gameObject2;
	}
	public void setGameObject2(GameObject gameObject2) {
		this.gameObject2 = gameObject2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameObject1 == null) ? 0 : gameObject1.hashCode());
		result = prime * result + ((gameObject2 == null) ? 0 : gameObject2.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collision other = (Collision) obj;
		if (gameObject1 == null) {
			if (other.gameObject1 != null)
				return false;
		} else if (!gameObject1.equals(other.gameObject1))
			return false;
		if (gameObject2 == null) {
			if (other.gameObject2 != null)
				return false;
		} else if (!gameObject2.equals(other.gameObject2))
			return false;
		return true;
	}
	
	
	
}
