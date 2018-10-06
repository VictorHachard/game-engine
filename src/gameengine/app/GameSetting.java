package gameengine.app;

import javafx.scene.layout.StackPane;

/**
 * Class containing the information of the window.
 * @author Main
 *
 */
public class GameSetting {
	/**
	 * Largeur de la fenêtre.
	 */
	private double width;
	/**
	 * Hauteur de la fenêtre.
	 */
	private double height;
	/**
	 * Nom de la fenêtre.
	 */
	private String title;
	
	public double getWidth() {
		return width;
	}
	public void setWidth(double widht) {
		this.width = widht;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
