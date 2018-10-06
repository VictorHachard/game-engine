package gameengine.physic;

/**
 * Class representing a point with x and y.
 * @author Main
 *
 */
public class Point2D {
	private Double x;
	private Double y;
	
	public Point2D() {}
	public Point2D(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
	public void add(Point2D p) {
		this.x += p.getX();
		this.y += p.getY();
	}
	public void add(Double x, Double y) {
		this.x += x;
		this.y += y;
	}
	public void sub(Point2D p) {
		this.x -= p.getX();
		this.y -= p.getY();
	}
	public void sub(Double x, Double y) {
		this.x -= x;
		this.y -= y;
	}
	public void mul(Point2D p) {
		this.x *= p.getX();
		this.y *= p.getY();
	}
	public void mul(Double x, Double y) {
		this.x *= x;
		this.y *= y;
	}
	public void div(Point2D p) {
		this.x /= p.getX();
		this.y /= p.getY();
	}
	public void div(Double x, Double y) {
		this.x /= x;
		this.y /= y;
	}
	public void setXY(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Point2D [x=" + x + ", y=" + y + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point2D other = (Point2D) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	} 
}
