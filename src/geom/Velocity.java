/**
 * Name:Shiraz Oskar
 * ID:325184000
 */
package geom;
/**
 * The Velocity class represents a 2D velocity with horizontal (dx) and vertical (dy) components.
 * It can be used to update the position of an object, like a ball, based on its movement vector.
 */
public class Velocity {

    private double dx;
    private double dy;

     /**
     * Constructs a new Velocity instance with the given horizontal and vertical components.
     *
     * @param dx the change in x-axis (horizontal movement)
     * @param dy the change in y-axis (vertical movement)
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a velocity vector from an angle (in degrees) and a speed.
     * The angle is measured clockwise from the positive X-axis.
     *
     * @param angle the direction of the velocity, in degrees
     * @param speed the magnitude (length) of the velocity
     * @return a Velocity object representing the movement in the given direction and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Returns the horizontal component of the velocity.
     *
     * @return dx, the change in x-axis
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * Returns the vertical component of the velocity.
     *
     * @return dy, the change in y-axis
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * Applies this velocity to a given point and returns the new point.
     * It simulates movement by translating the point by dx and dy.
     *
     * @param p the original point
     * @return a new Point moved by this velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

}