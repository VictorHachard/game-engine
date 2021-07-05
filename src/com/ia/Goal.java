package gameengine.ia;

import gameengine.entities.GameObject;

import java.util.List;

/**
 * Class representing a goal, a list of task to achive a goal.
 * @author Main
 *
 */
public class Goal {
	String libelle;
	List<Task> lstTask;
	List<Condition> lstCondition;
	GameObject gameObject;
	public Goal(String libelle, List<Task> lstTask, GameObject gameObject) {
		this.libelle = libelle;
		this.lstTask = lstTask;
		this.gameObject = gameObject;
	}
	/**
	 * 
	 */
	public void realised() {
		
	}
}
