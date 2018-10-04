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
	
	public GameObject at(Point2D pos) {
		position = pos;
		return this;
	}
	public GameObject with(Dimension2D d) {
		dimension = d;
		return this;
	}
	public GameObject with(Texture t) {
		texture = t;
		return this;
	}

	
}
