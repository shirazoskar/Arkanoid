/**
 * Name:Shiraz Oskar
 * ID:325184000
 */

package collisions;

import geom.Point;
import geom.Rectangle;
import geom.Velocity;
import sprites.Ball;

/**
 * The Collidable interface should be implemented by any object
 * that can be collided with in the game (e.g., blocks, walls).
 * It provides methods to:
 * - Get the shape of the object for collision detection.
 * - Handle what happens when another object hits it.
 */
public interface Collidable {

    /**
     * Returns the "collision shape" of the object — typically a rectangle
     * representing its physical boundaries in the game.
     *
     * @return the Rectangle that defines the collision area of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the specified point,
     * with a given current velocity. The object should compute and return
     * the new velocity of the hitting object after the collision (e.g., if
     * it bounces or changes direction).
     *
     * @param hitter          the ball that is hitting this collidable object.
     * @param collisionPoint  the point at which the collision occurred.
     * @param currentVelocity the velocity of the object before the collision.
     * @return the new velocity after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}