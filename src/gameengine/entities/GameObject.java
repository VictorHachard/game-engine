package gameengine.entities;

import gameengine.entities.texture.Texture;
import gameengine.ia.ManagerTask;
import gameengine.ia.Task;
import gameengine.physic.Dimension3D;
import gameengine.physic.Point2D;
import gameengine.physic.collision.aabb.AABB;
import javafx.geometry.Point3D;

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
	private Double zIndex;
	/**
	 * The velocity and direction of movement of the object.
	 */
	private Point2D velocity;
	/**
	 * The dimension of the object.
	 */
	private Dimension3D dimension;
	/**
	 * The texture of the object.
	 */
	private Texture texture;
	/**
	 * The type/name of the object.
	 */
	private String type;
	/**
	 * The hit box of the object.
	 */
	private AABB hitbox;
	/**
	 * The pointer that link the object to the game.
	 */
	private Object object;
	
	public GameObject() {
	}
	public Double getzIndex() {
		return zIndex;
	}
	public Point2D getPosition() {
		return position;
	}
	public Dimension3D getDimension() {
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
	
	public AABB getHitbox() {
		return hitbox;
	}
	public void setHitbox(AABB hitbox) {
		this.hitbox = hitbox;
	}
	public Object getObject() {
		return object;
	}
	public GameObject addTask(Task task) {
		task.setGameObject(this);
		ManagerTask.getManagerTask().addTask(task);
		return this;
	}
	public GameObject with(Object object) {
		this.object = object;
		return this;
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
	 * @param d A Dimension3D (double width, double height, double depth).
	 * @return The GameObject.
	 */
	public GameObject with(Dimension3D d) {
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
	public GameObject with(Double z) {
		this.zIndex = z;
		return this;
	}
	/**
	 * Setter to set the hit box of the object.
	 * @param hitbox AABB.
	 * @return The GameObject.
	 */
	public GameObject with(AABB hitbox) {
		this.hitbox = hitbox;
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
		if(!(velocity.getX() == 0 && velocity.getY() == 0)) {
			this.position.add(velocity);
		}
		if(!(hitbox == null)) {
			this.hitbox.setPosition(new Point3D(this.position.getX(), this.position.getY(), zIndex));
		}
 	}

	
}
