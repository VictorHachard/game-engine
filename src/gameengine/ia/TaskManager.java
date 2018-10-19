package gameengine.ia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gameengine.entities.GameObject;
import gameengine.ia.pathFinding.PathFinding;

public class TaskManager {
	private static TaskManager INSTANCE = null;
	private List<PathFinding> lstPathFinding;
	public static TaskManager getTaskManager() {
		return INSTANCE;
	}
	public static TaskManager createTaskManager() {
		INSTANCE = new TaskManager();
		return getTaskManager();
	}
	private List<Task> lstTask = new ArrayList<>();
	
	/**
	 * Add a task to be handle by the task manager.
	 * @param t The task handle.
	 */
	public void addTask(Task... t) {
		for (Task ts : t) {
			lstTask.add(ts);	
		}
	}
	/**
	 * Remove a task to the task manager.
	 * @param t The task to remove.
	 */
	public void removeTask(Task... t) {
		for (Task ts : t) {
			if (lstTask.contains(ts)) {
				lstTask.remove(ts);
			}
		}
	}
	/**
	 * Set the state of a task to PAUSE.
	 * @param t The task to set on PAUSE.
	 */
	public void pause(Task t) {
		t.setStat(StateTask.PAUSE);
	}
	/**
	 * Return a the list of task of a game object.
	 * @param g A game object.
	 * @return List<Task> Of the game object g.
	 */
	public List<Task> getLstTask(GameObject g){
		List<Task> lstTask = new ArrayList<>();
		for (Task task : lstTask) {
			if(task.getGameObject().equals(g)) {
				lstTask.add(task);				
			}
		}
		return lstTask;
	}
	/**
	 * update all the task in terms of the state of each task.
	 */
	public void updateTask() {
		for (Iterator<Task> iterator = lstTask.iterator(); iterator.hasNext();) {
			Task task = (Task) iterator.next();
			StateTask st = task.getStat();
			switch (st) {
			case PREBEGIN:
				break;
			case BEGIN:
				task.onBegin();
				break;
			case CURRENT:
				task.onUpdate();
				break;
			case PAUSE:
				break;
			case ENDING:
				task.onEnd();
				break;
			case END:
				iterator.remove();
				break;
			default:
				break;
			}
		}
	}
}

