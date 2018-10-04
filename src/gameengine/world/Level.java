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
	public void addGameObject(GameObject g) {
		lstGameObject.add(g);
	}
	public void removeGameObject(GameObject g) {
		lstGameObject.remove(g);		
	}
	public List<GameObject> getLstGameObject() {
		return Collections.unmodifiableList(lstGameObject);
	}
	
}
