package gameengine.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import gameengine.entities.GameObject;

public class Level {
	List<GameObject> lstGameObject;
	String nomLevel;
	public Level() {
		
		lstGameObject = new ArrayList<>();
	}
	public String getNomLevel() {
		return nomLevel;
	}
	public void setNomLevel(String nomLevel) {
		this.nomLevel = nomLevel;
	}
	
	/**
	 * Add a game object to the array.
	 * @param Game object
	 */
	public void addGameObject(GameObject ... g) {
		 for (GameObject gs : g) {
			 lstGameObject.add(gs);
		 }
	}
	
	/**
	 * Remove a game object from the array.
	 * @param g
	 */
	public void removeGameObject(GameObject ... g) {
		for (GameObject gs : g) {
			 lstGameObject.remove(gs);
		 }		
	}
	
	public List<GameObject> getLstGameObject() {
		return Collections.unmodifiableList(lstGameObject);
	}
	
}
