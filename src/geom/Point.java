/**.
*Name:Shiraz Oskar
*ID:325184000
*/
package geom;
/**.
 * Represents a point in a 2D space.
 */
public class Point {

    private final double x;
    private final double y;
    static final double EPSILON = 0.00001;

    /**
     * Constructs a new Point with the given coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point
     * @return the Euclidean distance between the two points
     */
    public double distance(Point other) {
        double distance = Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
        return distance;
    }

     /**
     * Checks if this point is equal to another point, considering a small margin of error.
     *
     * @param other the other point to compare
     * @return true if the points are approximately equal, false otherwise
     */
    public boolean equals(Point other) {
        if (this.x - other.getX() <= EPSILON && this.y - other.getY() <= EPSILON) {
            return true;
        }
        return false;
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

     /**
     * Returns the y-coordinate of this point.
     *
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }
 }
