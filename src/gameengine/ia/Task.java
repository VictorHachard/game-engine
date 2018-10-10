package gameengine.ia;

import gameengine.entities.GameObject;
import gameengine.world.GameWorld;
					
public abstract class Task {
	String libelle;
	StateTask stat;
	GameObject gameObject;
	GameWorld world;
	
	public StateTask getStat() {
		return stat;
	}

	public void setStat(StateTask stat) {
		this.stat = stat;
	}

	public GameObject getGameObject() {
		return gameObject;
	}

	public void setGameObject(GameObject g) {
		this.gameObject = g;
	}

	public GameWorld getWorld() {
		return world;
	}

	public void setWorld(GameWorld world) {
		this.world = world;
	}

	public Task(GameWorld w) {
		this.world = w;
	}

	public abstract void onBegin();
	public abstract void onUpdate();
	public abstract void onEnd();
}
