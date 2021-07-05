package gameengine.input;

/**
 * Class representing an action to do when a key is pressed.
 * @author Main
 *
 */
public abstract class UserEvent {
	/**
	 * Describe the key action.
	 */
	private String libelle;
	private Boolean start = false;
	public abstract void onBegin();
	public abstract void onUpdate();
	public abstract void onEnd();
	
	public UserEvent(String libelle) {
		this.libelle = libelle;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Boolean isStart() {
		return start;
	}
	public void setStart(Boolean start) {
		this.start = start;
	}
	
}
