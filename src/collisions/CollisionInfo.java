/**
 * Name:Shiraz Oskar
 * ID:325184000
 */

package collisions;
import geom.Point;

/**
 * The CollisionInfo class stores information about a collision that occurred
 * between a moving object and a collidable object in the game.
 * It contains the point where the collision occurred and the object involved in the collision.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor to initialize the collision information.
     *
     * @param collisionPoint the point where the collision occurred.
     * @param collisionObject the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Gets the point at which the collision occurred.
     *
     * @return the point where the collision happened.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Gets the collidable object involved in the collision.
     *
     * @return the collidable object that was hit.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
 }