package gameengine.physic;

import javafx.geometry.Point3D;

/**
 * Class representing a 2D point with x and y.
 * @author Main
 *
 */
public class Point2D{
	private Double x;
	private Double y;

	public Point2D(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
	
    public Point2D() {
		// TODO Auto-generated constructor stub
	}
    public void setPoint2D(Point2D p) {
    	this.x = p.getX();
    	this.y = p.getY();
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

	/**
     * Computes the distance between this point and point {@code (x1, y1)}.
     *
     * @param x1 the x coordinate of other point
     * @param y1 the y coordinate of other point
     * @return the distance between this point and point {@code (x1, y1)}.
     */
    public double distance(double x1, double y1) {
        double a = getX() - x1;
        double b = getY() - y1;
        return Math.sqrt(a * a + b * b);
    }

    /**
     * Computes the distance between this point and the specified {@code point}.
     *
     * @param point the other point
     * @return the distance between this point and the specified {@code point}.
     * @throws NullPointerException if the specified {@code point} is null
     */
    public double distance(Point2D point) {
        return distance(point.getX(), point.getY());
    }

    public void add(Point2D p) {
		this.x += p.getX();
		this.y += p.getY();
	}
	public void add(Double x, Double y) {
		this.x += x;
		this.y += y;
	}
	public void subtract(Point2D p) {
		this.x -= p.getX();
		this.y -= p.getY();
	}
	public void subtract(Double x, Double y) {
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

   
    /**
     * Normalizes the relative magnitude vector represented by this instance.
     * Returns a vector with the same direction and magnitude equal to 1.
     * If this is a zero vector, a zero vector is returned.
     * @return the normalized vector represented by a {@code Point2D} instance
     * @since JavaFX 8.0
     */
    public Point2D normalize() {
        final double mag = magnitude();

        if (mag == 0.0) {
            return new Point2D(0.0, 0.0);
        }

        return new Point2D(
                getX() / mag,
                getY() / mag);
    }
    
    /**
     * Returns a point which lies in the middle between this point and the
     * specified coordinates.
     * @param x the X coordinate of the second endpoint
     * @param y the Y coordinate of the second endpoint
     * @return the point in the middle
     * @since JavaFX 8.0
     */
    public Point2D midpoint(double x, double y) {
        return new Point2D(
                x + (getX() - x) / 2.0,
                y + (getY() - y) / 2.0);
    }

    /**
     * Returns a point which lies in the middle between this point and the
     * specified point.
     * @param point the other endpoint
     * @return the point in the middle
     * @throws NullPointerException if the specified {@code point} is null
     * @since JavaFX 8.0
     */
    public Point2D midpoint(Point2D point) {
        return midpoint(point.getX(), point.getY());
    }

    /**
     * Computes the angle (in degrees) between the vector represented
     * by this point and the specified vector.
     * @param x the X magnitude of the other vector
     * @param y the Y magnitude of the other vector
     * @return the angle between the two vectors measured in degrees
     * @since JavaFX 8.0
     */
    public double angle(double x, double y) {
        final double ax = getX();
        final double ay = getY();

        final double delta = (ax * x + ay * y) / Math.sqrt(
                (ax * ax + ay * ay) * (x * x + y * y));

        if (delta > 1.0) {
            return 0.0;
        }
        if (delta < -1.0) {
            return 180.0;
        }

        return Math.toDegrees(Math.acos(delta));
    }

    /**
     * Computes the angle (in degrees) between the vector represented
     * by this point and the vector represented by the specified point.
     * @param point the other vector
     * @return the angle between the two vectors measured in degrees,
     *         {@code NaN} if any of the two vectors is a zero vector
     * @throws NullPointerException if the specified {@code point} is null
     * @since JavaFX 8.0
     */
    public double angle(Point2D point) {
        return angle(point.getX(), point.getY());
    }

    /**
     * Computes the angle (in degrees) between the three points with this point
     * as a vertex.
     * @param p1 one point
     * @param p2 other point
     * @return angle between the vectors (this, p1) and (this, p2) measured
     *         in degrees, {@code NaN} if the three points are not different
     *         from one another
     * @throws NullPointerException if {@code p1} or {@code p2} is null
     * @since JavaFX 8.0
     */
    public double angle(Point2D p1, Point2D p2) {
        final double x = getX();
        final double y = getY();

        final double ax = p1.getX() - x;
        final double ay = p1.getY() - y;
        final double bx = p2.getX() - x;
        final double by = p2.getY() - y;

        final double delta = (ax * bx + ay * by) / Math.sqrt(
                (ax * ax + ay * ay) * (bx * bx + by * by));

        if (delta > 1.0) {
            return 0.0;
        }
        if (delta < -1.0) {
            return 180.0;
        }

        return Math.toDegrees(Math.acos(delta));
    }

    /**
     * Computes magnitude (length) of the relative magnitude vector represented
     * by this instance.
     * @return magnitude of the vector
     * @since JavaFX 8.0
     */
    public double magnitude() {
        final double x = getX();
        final double y = getY();

        return Math.sqrt(x * x + y * y);
    }

    /**
     * Computes dot (scalar) product of the vector represented by this instance
     * and the specified vector.
     * @param x the X magnitude of the other vector
     * @param y the Y magnitude of the other vector
     * @return the dot product of the two vectors
     * @since JavaFX 8.0
     */
    public double dotProduct(double x, double y) {
        return getX() * x + getY() * y;
    }

    /**
     * Computes dot (scalar) product of the vector represented by this instance
     * and the specified vector.
     * @param vector the other vector
     * @return the dot product of the two vectors
     * @throws NullPointerException if the specified {@code vector} is null
     * @since JavaFX 8.0
     */
    public double dotProduct(Point2D vector) {
        return dotProduct(vector.getX(), vector.getY());
    }

    /**
     * Computes cross product of the vector represented by this instance
     * and the specified vector.
     * @param x the X magnitude of the other vector
     * @param y the Y magnitude of the other vector
     * @return the cross product of the two vectors
     * @since JavaFX 8.0
     */
    public Point3D crossProduct(double x, double y) {
        final double ax = getX();
        final double ay = getY();

        return new Point3D(
                0, 0, ax * y - ay * x);
    }

    /**
     * Computes cross product of the vector represented by this instance
     * and the specified vector.
     * @param vector the other vector
     * @return the cross product of the two vectors
     * @throws NullPointerException if the specified {@code vector} is null
     * @since JavaFX 8.0
     */
    public Point3D crossProduct(Point2D vector) {
        return crossProduct(vector.getX(), vector.getY());
    }

    public Point2D copy() {
    	return new Point2D(x,y);
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

	/**
     * Returns a string representation of this {@code Point2D}.
     * This method is intended to be used only for informational purposes.
     * The content and format of the returned string might vary between
     * implementations.
     * The returned string might be empty but cannot be {@code null}.
     */
    @Override public String toString() {
        return "Point2D [x = " + getX() + ", y = " + getY() + "]";
    }
}
