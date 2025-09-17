/**
*Name:Shiraz Oskar
*ID:325184000
*/

package sprites;

import game.Game;
import game.GameEnvironment;
import geom.Point;
import geom.Line;
import geom.Velocity;
import collisions.CollisionInfo;
import biuoop.DrawSurface;

/**
 * The Ball class represents a ball with a center point, radius, color,
 * velocity, and movement boundaries. It can be drawn on a DrawSurface and
 * move within the defined boundaries, bouncing off the edges.
 */
public class Ball implements Sprite {

    private Point center;
    private final int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment environment;
    static final double EPSILON = 0.00001;

    /**
     * Constructs a Ball with a given center, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Constructs a Ball with a given center (x, y), radius, and color.
     *
     * @param x     the x-coordinate of the center
     * @param y     the y-coordinate of the center
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * Sets the game environment in which the ball moves.
     *
     * @param environment the GameEnvironment containing the collidables.
     */
    public void setGameEnvirnment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Returns the x-coordinate of the center.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y-coordinate of the center.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius (size) of the ball.
     *
     * @return the radius
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Returns the center point of the ball.
     *
     * @return the center point
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param d the surface to draw the ball on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * Notifies the sprite that time has passed,
     * so it can update its state (e.g., move).
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets the velocity of the ball using dx and dy.
     *
     * @param dx the horizontal change
     * @param dy the vertical change
     */
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        this.v = v;
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Sets the center of the ball to a new point.
     *
     * @param p the new center point
     */
    public void setCenter(Point p) {
        this.center = p;
    }

    /**
     * Sets the color of this object.
     *
     * @param color the new color to set.
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * Adds this sprite to the game's sprite collection.
     *
     * @param game the game to add this sprite to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * Moves the ball one step, updating its position based on its velocity.
     * If the ball hits a boundary, it bounces off by inverting the direction
     * of movement (velocity) accordingly.
     */
    public void moveOneStep() {

        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        CollisionInfo collisionInfo = environment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            this.setVelocity(
                    collisionInfo.collisionObject()
                            .hit(this, collisionInfo.collisionPoint(), this.getVelocity()));
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * Removes this object from the specified game.
     *
     * @param game the Game instance from which this object should be removed.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}
