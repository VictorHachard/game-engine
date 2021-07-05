package gameengine.world;

import gameengine.entities.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a level.
 * @author Main
 *
 */
public class Level {
	List<GameObject> lstGameObject;
	String nomLevel;
	private int width;
	private int height;
	public Level() {
		lstGameObject = new ArrayList<>();
	}
	public String getNomLevel() {
		return nomLevel;
	}
	public void setNomLevel(String nomLevel) {
		this.nomLevel = nomLevel;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * Add a game object to the array.
	 * @param g a Game(s) object(s).
	 */
	public void addGameObject(GameObject... g) {
		 for (GameObject gs : g) {
			 lstGameObject.add(gs);
		 }
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	/**
	 * Remove a game object from the array.
	 * @param g a Game(s) object(s).
	 */
	public void removeGameObject(GameObject... g) {
		for (GameObject gs : g) {
			 lstGameObject.remove(gs);
		 }		
	}
	/**
	 * Return the list of all the game object in a level.
	 * @return List<GameObject> All the game object in the level.
	 */
	public List<GameObject> getLstGameObject() {
		return lstGameObject;
	}
	
}
