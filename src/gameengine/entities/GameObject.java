package gameengine.entities;

import gameengine.entities.texture.Texture;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Classe representant un object dans le monde.
 * @author Main
 *
 */
public class GameObject {
	private Point2D position;
	private Dimension2D dimension;
	private Texture texture;
	private String type;
	
	public Point2D getPosition() {
		return position;
	}
	public Dimension2D getDimension() {
		return dimension;
	}
	public Texture getTexture() {
		return texture;
	}
	public String getType() {
		return type;
	}
	
	/**
	 * Setter to set the position of the object.
	 * @param pos A Point2D (double x, double y)
	 * @return The GameObject
	 */
	public GameObject at(Point2D pos) {
		position = pos;
		return this;
	}
	
	/**
	 * Setter to set the dimension of the object.
	 * @param pos A Dimension2D (double width, double height)
	 * @return The GameObject
	 */
	public GameObject with(Dimension2D d) {
		dimension = d;
		return this;
	}
	
	/**
	 * Setter to set the texture of the object.
	 * @param t A Texture
	 * @return The GameObject
	 */
	public GameObject with(Texture t) {
		texture = t;
		return this;
	}
	
	/**
	 * Setter to set the type of the object.
	 * @param ty A name (String)
	 * @return The GameObject
	 */
	public GameObject is(String ty) {
		type = ty;
		return this;
	}

	
}
