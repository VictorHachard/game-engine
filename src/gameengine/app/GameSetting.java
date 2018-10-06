package gameengine.app;

/**
 * Class containing the information of the window.
 * @author Main
 *
 */
public class GameSetting {
	/**
	 * Width of the window.
	 */
	private double width;
	/**
	 * Height of the window.
	 */
	private double height;
	/**
	 * Name of the window.
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
