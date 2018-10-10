package gameengine.ia;

import java.util.List;

import gameengine.entities.GameObject;

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
	public void realised() {
		
	}
}
