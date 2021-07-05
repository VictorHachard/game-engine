package gameengine.input;

import gameengine.input.exception.InputNameAlreadyExistException;
import gameengine.render.SceneManager;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class that handle all inputs.
 * @author Main
 *
 */
public class Input {
	private static Input INSTANCE = null;
	public static Input getInput() {
		return INSTANCE;
	}
	public static Input createInput() {
		INSTANCE = new Input();
		return getInput();
	}
	
	private Scene scene;
	private List<WrapperEvent> lstEvent;
	private List<WrapperEvent> lstEventCurrent;
	
	public Input() {
		lstEvent = new ArrayList<>();
		lstEventCurrent = new ArrayList<>();
		this.scene = SceneManager.getSceneManager().getScene();
	}
	/**
	 * Add a UserEvent link to a keyCode in an list.
	 * @param user A UserEvent (String user).
	 * @param code A Keycode.
	 */
	public void addEvent(UserEvent user, KeyCode code) {
		try {
			existEvent(user.getLibelle());
		}catch (InputNameAlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		lstEvent.add(new WrapperEvent(user, code));
	}
	
	/**
	 * Check if a UserEvent is already in the list thanks to wording(libelle),
	 * If yes throw a exception (InputNameAlreadyExistException), if not do nothing.
	 * @param eventName A String wording(libelle).
	 * @throws InputNameAlreadyExistException
	 */
	public void existEvent(String eventName) throws InputNameAlreadyExistException {
		for (int i = 0; i < lstEvent.size(); i++) {
			if (lstEvent.get(i).getUserEvent().getLibelle().equals(eventName)) {
				throw new InputNameAlreadyExistException(String.format("l'�v�nement %s existe d�j�", eventName));
			}
		}
	}
	
	/**
	 * Listens to input from the keyboard, mouse and treats theme.
	 */
	public void manageEvent() {
		scene.setOnKeyPressed((event)->{
			for (WrapperEvent wrapperEvent : lstEvent) {
				if(event.getCode().equals(wrapperEvent.getKeyCode())) {
					if(!wrapperEvent.getUserEvent().isStart()) {
						wrapperEvent.getUserEvent().setStart(true);
						wrapperEvent.getUserEvent().onBegin();
						lstEventCurrent.add(wrapperEvent);
					}else {
						
						wrapperEvent.getUserEvent().onUpdate();
					}
				}
			}
		});
		scene.setOnKeyReleased(
				(event)-> {
				for (Iterator<WrapperEvent> iterator = lstEventCurrent.iterator(); iterator.hasNext();) {
					WrapperEvent wrapperEvent = (WrapperEvent) iterator.next();
					if(event.getCode().equals(wrapperEvent.getKeyCode())) {
						wrapperEvent.getUserEvent().onEnd();
						wrapperEvent.getUserEvent().setStart(false);
						iterator.remove();
					}
				}
		});
	}
}
