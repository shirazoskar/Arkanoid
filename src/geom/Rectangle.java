
/**
 * Name:Shiraz Oskar
 * ID:325184000
 */
package geom;
import java.util.List;
import java.util.ArrayList;

/**
 * The Rectangle class represents a rectangle defined by its upper-left corner,
 * width, and height. It provides methods to check intersections with lines,
 * retrieve properties such as dimensions and position, and update its position.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructor for creating a new rectangle with a given upper-left corner,
     * width, and height.
     *
     * @param upperLeft the upper-left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a list of points where the specified line intersects the rectangle's
     * sides.
     * If there are no intersection points, it returns an empty list.
     *
     * @param line the line to check for intersections with the rectangle.
     * @return a list of intersection points, or an empty list if no intersections.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();

        double leftX = upperLeft.getX();
        double topY = upperLeft.getY();

        double rightX = leftX + this.width;
        double bottomY = topY + this.height;

        Point ul = new Point(leftX, topY); // upper-left
        Point ur = new Point(rightX, topY); // upper-right
        Point ll = new Point(leftX, bottomY); // lower-left
        Point lr = new Point(rightX, bottomY); // lower-right

        // Define the four edges of the rectangle as lines.
        Line topLine = new Line(ul, ur);
        Line bottomLine = new Line(ll, lr);
        Line leftLine = new Line(ul, ll);
        Line rightLine = new Line(ur, lr);

        // Check if the line intersects each rectangle edge, and add the intersection
        // points if any.
        if (topLine.isIntersecting(line)) {
            points.add(topLine.intersectionWith(line));
        }
        if (bottomLine.isIntersecting(line)) {
            points.add(bottomLine.intersectionWith(line));
        }
        if (leftLine.isIntersecting(line)) {
            points.add(leftLine.intersectionWith(line));
        }
        if (rightLine.isIntersecting(line)) {
            points.add(rightLine.intersectionWith(line));
        }
        return points;

    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Sets the upper-left point of the rectangle to a new point.
     *
     * @param p the new upper-left point of the rectangle.
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }
}