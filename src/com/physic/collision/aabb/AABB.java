package gameengine.physic.collision.aabb;

import gameengine.constante.Constante;
import gameengine.physic.Dimension3D;
import javafx.geometry.Point3D;

/**
 * Class represents a hit box.
 * @author Main
 *
 */
public class AABB {
	private Point3D position;
	private Dimension3D dimension;
	public AABB(Point3D position, Dimension3D dimension) {
		super();
		this.position = position;
		this.dimension = dimension;
	}
	public Point3D getPosition() {
		return position;
	}
	public void setPosition(Point3D position) {
		this.position = position;
	}
	public Dimension3D getDimension() {
		return dimension;
	}
	public void setDimension(Dimension3D dimension) {
		this.dimension = dimension;
	}
	/**
	 * Verifying if the instance of the box is colliding with the given box.
	 * @param box A AABB box.
	 * @return true if the box is colliding, false otherwise.
	 */
	public boolean isCollide(AABB box) {
		double box1X = this.getPosition().getX()*Constante.GRID_SIZE;
		double box2X = box.getPosition().getX()*Constante.GRID_SIZE;
		double box1Y = this.getPosition().getY()*Constante.GRID_SIZE;
		double box2Y = box.getPosition().getY()*Constante.GRID_SIZE;
		double box1Z = this.getPosition().getZ()*Constante.GRID_SIZE;
		double box2Z = box.getPosition().getZ()*Constante.GRID_SIZE;
		// all Dimension must be 32
		double box1W = this.getDimension().getWidth();
		double box2W = box.getDimension().getWidth();
		double box1H = this.getDimension().getHeight();
		double box2H = box.getDimension().getHeight();
		double box1D = this.getDimension().getDepth();
		double box2D = box.getDimension().getDepth();
		return !((box2X >= box1X + box1W) || (box2X + box2W <= box1X) || (box2Y >= box1Y + box1H) || (box2Y + box2H <= box1Y) || (box2Z >= box1Z + box1D) || (box2Z + box2D <= box1Z));
	}
}
