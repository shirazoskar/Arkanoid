/**
*Name:Shiraz Oskar
*ID:325184000
*/
package geom;
import java.util.List;

/**
 * The Line class represents a line segment defined by two points.
 * It includes methods to calculate length, midpoint, intersection, and
 * equality.
 */
public class Line {

    private final Point start;
    private final Point end;
    static final double EPSILON = 0.00001;

    /**
     * Constructs a Line object given two Point objects.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a Line object given the coordinates of two points.
     *
     * @param x1 x-coordinate of the starting point
     * @param y1 y-coordinate of the starting point
     * @param x2 x-coordinate of the ending point
     * @param y2 y-coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line.
     *
     * @return the distance between the start and end points
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the midpoint of the line segment
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        Point newPoint = new Point(x, y);
        return newPoint;
    }

    /**
     * Returns the starting point of the line.
     *
     * @return the start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the ending point of the line.
     *
     * @return the end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Determines if this line intersects with another line.
     *
     * @param other another line to check intersection with
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double x3 = other.start.getX();
        double x4 = other.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        double y3 = other.start.getY();
        double y4 = other.end.getY();

        // Compute the determinant
        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        // If D == 0, the lines are parallel
        if (d == 0) {
            // Check if the lines are collinear
            if (areCollinear(this.start, this.end, other.start, other.end)) {
                // Check if they overlap
                if (isOverlapping(this, other)) {
                    return true;
                }
            }
        }
        if (intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * Determines if this line intersects with two other lines.
     *
     * @param other1 the first line
     * @param other2 the second line
     * @return true if both lines intersect with this line, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        if (!isIntersecting(other1) || !isIntersecting(other2)) {
            return false;
        }
        return true;
    }

    /**
     * Finds the intersection point between this line and another line.
     *
     * @param other another line to check intersection with
     * @return the intersection point if exists, otherwise null
     */
    public Point intersectionWith(Line other) {

        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double x3 = other.start.getX();
        double x4 = other.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        double y3 = other.start.getY();
        double y4 = other.end.getY();

        // Compute the determinant
        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        // If D == 0, the lines are parallel
        if (d == 0) {
            // Check if the lines are collinear
            if (areCollinear(this.start, this.end, other.start, other.end)) {
                // Check if they overlap
                if (isOverlapping(this, other)) {
                    return null;
                }
            }
            return null;
        }
        // Compute partial determinants
        double dx = (x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4);
        double dy = (x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4);

        // Find the intersection point
        double x = dx / d;
        double y = dy / d;

        // Check if the intersection point is within both segments
        if (isBetween(x1, y1, x2, y2, x, y) && isBetween(x3, y3, x4, y4, x, y)) {
            Point newPoint = new Point(x, y);
            return newPoint;
        }
        return null;
    }

    /**
     * /**
     * Checks if two line segments are collinear.
     *
     * @param p1 Start of first line.
     * @param p2 End of first line.
     * @param p3 Start of second line.
     * @param p4 End of second line.
     * @return true if the lines are collinear, false otherwise.
     */
    private boolean areCollinear(Point p1, Point p2, Point p3, Point p4) {
        return Math.abs((p2.getY() - p1.getY()) * (p3.getX() - p1.getX())
                - (p3.getY() - p1.getY()) * (p2.getX() - p1.getX())) < Point.EPSILON;
    }

    /**
     * Checks if two collinear line segments overlap.
     *
     * @param line1 The first line segment.
     * @param line2 The second line segment.
     * @return true if the segments overlap, false otherwise.
     */
    private boolean isOverlapping(Line line1, Line line2) {
        double x1 = (double) line1.start().getX();
        double y1 = (double) line1.start().getY();
        double x2 = (double) line1.end().getX();
        double y2 = (double) line1.end().getY();
        double x3 = (double) line2.start().getX();
        double y3 = (double) line2.start().getY();
        double x4 = (double) line2.end().getX();
        double y4 = (double) line2.end().getY();

        return isBetween(x1, y1, x2, y2, x3, y3)
                || isBetween(x1, y1, x2, y2, x4, y4)
                || isBetween(x3, y3, x4, y4, x1, y1)
                || isBetween(x3, y3, x4, y4, x2, y2);
    }

    /**
     * Checks if a point (x, y) lies within a given segment.
     *
     * @param x1 x-coordinate of the first endpoint
     * @param y1 y-coordinate of the first endpoint
     * @param x2 x-coordinate of the second endpoint
     * @param y2 y-coordinate of the second endpoint
     * @param x  x-coordinate of the point to check
     * @param y  y-coordinate of the point to check
     * @return true if the point is within the segment, false otherwise
     */
    private static boolean isBetween(double x1, double y1, double x2, double y2, double x, double y) {
        if ((Math.min(x1, x2) - EPSILON <= x && x <= Math.max(x1, x2) + EPSILON)
                && (Math.min(y1, y2) - EPSILON <= y && y <= Math.max(y1, y2) + EPSILON)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if two lines are equal (same start and end points, regardless of
     * order).
     *
     * @param other another line to compare
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)
                || this.start.equals(other.end) && this.end.equals(other.start)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the closest intersection point between this line and a given
     * rectangle,
     * relative to the start of the line.
     *
     * <p>
     * This method computes all intersection points between the line and the
     * rectangle,
     * and finds the one with the smallest distance to the line's start point.
     * </p>
     *
     * @param rect the rectangle to check for intersection points with this line
     * @return the closest intersection point to the start of the line,
     *         or {@code null} if there are no intersections
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> l = rect.intersectionPoints(this);
        if (l.isEmpty() || l == null) {
            return null;
        }
        Point closest = null;
        double minDis = Double.MAX_VALUE;
        for (Point point : l) {
            if (point != null && point.distance(this.start) < minDis) {
                minDis = point.distance(this.start);
                closest = point;
            }
        }
        return closest;
    }
}