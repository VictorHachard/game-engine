package gameengine.entities;

import gameengine.entities.texture.Texture;
import gameengine.physic.Point2D;
import javafx.geometry.Dimension2D;


/**
 * Class representing an object in the world.
 * @author Main
 *
 */
public class GameObject {
	/**
	 * The position of the object.
	 */
	private Point2D position;
	/**
	 * The index of the object.
	 */
	private Integer zIndex;
	/**
	 * The velocity and direction of movement of the object.
	 */
	private Point2D velocity;
	/**
	 * The dimension of the object.
	 */
	private Dimension2D dimension;
	/**
	 * The texture of the object.
	 */
	private Texture texture;
	/**
	 * The type/name of the object.
	 */
	private String type;
	
	/**
	 * Correspond à la reference au lien dans l'objet du jeu.
	 */
	private Object object;
	public Integer getzIndex() {
		return zIndex;
	}
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
	public Point2D getVelocity() {
		return velocity;
	}
	
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	/**
	 * Setter to set the position of the object.
	 * @param pos A Point2D (double x, double y).
	 * @return The GameObject.
	 */
	public GameObject at(Point2D pos) {
		position = pos;
		return this;
	}
	
	/**
	 * Setter to set the dimension of the object.
	 * @param d A Dimension2D (double width, double height).
	 * @return The GameObject.
	 */
	public GameObject with(Dimension2D d) {
		dimension = d;
		return this;
	}
	
	/**
	 * Setter to set the texture of the object.
	 * @param t A Texture.
	 * @return The GameObject.
	 */
	public GameObject with(Texture t) {
		texture = t;
		return this;
	}
	
	/**
	 * Setter to set the velocity of the object.
	 * @param v A Point2D (double x, double y).
	 * @return The GameObject.
	 */
	public GameObject with(Point2D v) {
		velocity = v;
		return this;
	}
	
	/**
	 * Setter to set the z index of the object.
	 * @param v A Integer.
	 * @return The GameObject.
	 */
	public GameObject with(Integer z) {
		this.zIndex = z;
		return this;
	}

	
	/**
	 * Setter to set the type of the object.
	 * @param ty A name (String).
	 * @return The GameObject.
	 */
	public GameObject is(String type) {
		this.type = type;
		return this;
	}
	
	/**
	 * Update the object (moving on it's own).
	 */
	public void update() {	
		if(velocity.getX() == 0 && velocity.getY() == 0) {
			return;
		}
		this.position.add(velocity);
 	}

	
}
