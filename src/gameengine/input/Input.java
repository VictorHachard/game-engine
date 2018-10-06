package gameengine.input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gameengine.input.exception.InputNameAlreadyExistException;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Input {
	private Scene scene;
	
	private List<WrapperEvent> lstEvent;
	private List<WrapperEvent> lstEventCurrent;
	
	
	
	public Input(Scene sc) {
		lstEvent = new ArrayList<>();
		lstEventCurrent = new ArrayList<>();
		scene = sc;
	}
	public void addEvent(UserEvent user, KeyCode code) {
		try {
			existEvent(user.getLibelle());
		}catch (InputNameAlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		lstEvent.add(new WrapperEvent(user, code));
	}
	public void existEvent(String eventName) throws InputNameAlreadyExistException {
		for (int i = 0; i < lstEvent.size(); i++) {
			if (lstEvent.get(i).getUserEvent().getLibelle().equals(eventName)) {
				throw new InputNameAlreadyExistException(String.format("l'évènement %s existe déjà", eventName));
			}
		}
	}
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
