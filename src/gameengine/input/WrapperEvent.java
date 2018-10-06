package gameengine.input;

import javafx.scene.input.KeyCode;

/**
 * Class assembling a key with a user event.
 * @author Main
 *
 */
public class WrapperEvent {
	private UserEvent userEvent;
	private KeyCode keyCode;
	
	public WrapperEvent(UserEvent userEvent, KeyCode keyCode) {
		this.userEvent = userEvent;
		this.keyCode = keyCode;
	}
	
	public UserEvent getUserEvent() {
		return userEvent;
	}
	public void setUserEvent(UserEvent userEvent) {
		this.userEvent = userEvent;
	}
	public KeyCode getKeyCode() {
		return keyCode;
	}
	public void setKeyCode(KeyCode keyCode) {
		this.keyCode = keyCode;
	}
	
}
