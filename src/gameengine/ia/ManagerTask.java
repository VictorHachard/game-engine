package gameengine.ia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gameengine.entities.GameObject;

public class ManagerTask {
	List<Task> lstTask = new ArrayList<>();
	
	/**
	 * P = T1 
	 * p = T2
	 * p2 = T3
	 * p3 = T1
	 * P  = TF
	 * 
	 * 
	 */
	
	
	private static ManagerTask INSTANCE =null;
	
	public static ManagerTask getManagerTask() {
		if (INSTANCE == null) {
			return new ManagerTask();
		} else {
			return INSTANCE;
		}
	}
	
	public void addTask(Task t) {
		lstTask.add(t);
	}
	
	public void pause(Task t) {
		t.setStat(StateTask.PAUSE);
	}
	public List<Task> getLstTask(GameObject g){
		List<Task> lstTask = new ArrayList<>();
		for (Task task : lstTask) {
			if(task.getGameObject().equals(g)) {
				lstTask.add(task);				
			}
		}
		return lstTask;
	}
	
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
