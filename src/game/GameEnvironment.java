/**
 * Name:Shiraz Oskar
 * ID:325184000
 */
package game;

import collisions.CollisionInfo;
import collisions.Collidable;
import geom.Line;
import geom.Rectangle;
import geom.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class manages all the collidables in the game.
 * It is responsible for handling the objects that can potentially collide with
 * others.
 * It provides methods to add collidables and to find the closest collision for
 * a moving object.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructor that initializes an empty list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Removes the specified collidable object from the game environment.
     *
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Assumes an object is moving along the trajectory (a line).
     * If the object will not collide with any of the collidables in the
     * environment,
     * the method returns null. Otherwise, it returns the information about the
     * closest collision
     * that is going to occur based on the trajectory.
     *
     * @param trajectory the trajectory line representing the object's movement.
     * @return the closest collision information, or null if no collision occurs.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo info = null;

        // Initialize the minimum distance as the largest possible value.
        double minDis = Double.MAX_VALUE;

        // Loop through all collidables to check for intersections.
        for (Collidable collidable : collidables) {
            Rectangle rect = collidable.getCollisionRectangle();
            // Find the closest intersection point
            Point closePoint = trajectory.closestIntersectionToStartOfLine(rect);
            // If there is an intersection
            if (closePoint != null) {
                double distance = closePoint.distance(trajectory.start());
                if (distance < minDis) {
                    minDis = distance;
                    info = new CollisionInfo(closePoint, collidable);
                }
            }
        }
        // Return the collision information, or null if no collision occurred.
        return info;
    }

}