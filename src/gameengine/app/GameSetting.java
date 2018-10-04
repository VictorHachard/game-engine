package gameengine.app;

import javafx.scene.layout.StackPane;

/**
 * Contient toute la configuration du jeu
 * @author Main
 *
 */
public class GameSetting {
	/**
	 * Largeur de la fen�tre.
	 */
	private double width;
	/**
	 * Hauteur de la fen�tre.
	 */
	private double height;
	/**
	 * Hauteur de la fen�tre.
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
