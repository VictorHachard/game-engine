package gameengine.input;

/**
 * Class representing an action.
 * @author Main
 *
 */
public abstract class UserEvent {
	private String libelle;
	private Boolean start = false; // de base false uniuqement pour les primitifs
	public abstract void onBegin();
	public abstract void onUpdate();
	public abstract void onEnd();
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
		System.out.println(start);
		this.start = start;
	}
	
	
}
