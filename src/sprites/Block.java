/**
 * Name:Shiraz Oskar
 * ID:325184000
 */
package sprites;

import collisions.Collidable;
import game.Game;
import geom.Point;
import geom.Rectangle;
import geom.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Block class represents a rectangle block in the game.
 * A block can be collided with and can be drawn on the screen.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private double x;
    private double y;
    private double width;
    private double height;
    private java.awt.Color color;
    private List<HitListener> hitListeners;
    static final double EPSILON = 0.00001;

    /**
     * Constructs a block with given position, size, and color.
     *
     * @param x      the x-coordinate of the upper-left corner.
     * @param y      the y-coordinate of the upper-left corner.
     * @param width  the width of the block.
     * @param height the height of the block.
     * @param color  the color of the block.
     */
    public Block(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the block on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.x, (int) this.y,
                (int) this.width, (int) this.height);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.x, (int) this.y,
                (int) width, (int) height);
    }

    /**
     * Notifies the block that time has passed.
     * Currently does nothing for static blocks.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adds the block to the game as a sprite and as a collidable object.
     *
     * @param game the game to add the block to.
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * Returns the collision rectangle of the block.
     *
     * @return a Rectangle representing the block's shape.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(new Point(x, y), width, height);
    }

    /**
     * Returns the color rectangle of the block.
     *
     * @return a color of the block.
     */
    public Color getColor() {
        return this.color;
    }

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
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDX();
        double dy = currentVelocity.getDY();

        double pointX = collisionPoint.getX();
        double pointY = collisionPoint.getY();

        // Check if the collision is on the left or right side
        boolean hitX = Math.abs(pointX - this.x) < Math.abs(pointY - this.y)
                || Math.abs(pointX - (this.x + width)) < Math.abs(pointY - (this.y
                        + height));
        // Check if the collision is on the top or bottom side
        boolean hitY = Math.abs(pointY - this.y) < Math.abs(pointX - this.x)
                || Math.abs(pointY - (this.y + height)) < Math.abs(pointX - (this.x
                        + width));
        boolean both = Math.abs(pointX - this.x) == Math.abs(pointY - this.y);

        if (hitX) {
            dx = -dx;
        }
        if (hitY) {
            dy = -dy;
        }
        if (both) {
            dx = -dx;
            dy = -dy;
        }

        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }

        return new Velocity(dx, dy);
    }

    /**
     * Checks whether the color of the specified ball matches this block color.
     *
     * @param ball the Ball to compare color with.
     * @return true if the colors match, false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }

    /**
     * Removes this block from the specified game.
     * It removes the block both from the list of collidables and sprites.
     *
     * @param game the Game from which this block should be removed.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Notifies all registered hit listeners that a hit has occurred.
     *
     * @param hitter the Ball that hit this block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Adds a HitListener to the list of listeners that will be notified of hit
     * events.
     *
     * @param hl the HitListener to be added.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a HitListener from the list of listeners.
     *
     * @param hl the HitListener to be removed.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

}