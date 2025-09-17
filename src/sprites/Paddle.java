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
import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The Paddle class represents a paddle in the game.
 * It implements both Sprite and Collidable interfaces. The paddle can be moved
 * left or right,
 * and it handles collision with the ball by changing its velocity when hit.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rect;
    private Color color;
    static final double SPEED = 4;
    private biuoop.KeyboardSensor keyboard;

    /**
     * Constructor to initialize the paddle.
     *
     * @param rect     the rectangle that defines the paddle's position and size.
     * @param color    the color of the paddle.
     * @param keyboard the keyboard sensor to handle key presses.
     */
    public Paddle(Rectangle rect, Color color, biuoop.KeyboardSensor keyboard) {
        this.rect = rect;
        this.color = color;
        this.keyboard = keyboard;
    }

    /**
     * Moves the paddle left, wrapping it around to the right side if it goes
     * off-screen.
     */
    public void moveLeft() {
        double x = this.rect.getUpperLeft().getX();
        double newX = x - SPEED;
        if (newX < 0) {
            newX = 800 - rect.getWidth(); // Wrap to the right side of the screen.
        }
        Point newP = new Point(newX, this.rect.getUpperLeft().getY());
        this.rect.setUpperLeft(newP);
    }

    /**
     * Moves the paddle right, wrapping it around to the left side if it goes
     * off-screen.
     */
    public void moveRight() {
        double x = this.rect.getUpperLeft().getX();
        double newX = x + SPEED;
        if (newX > 800) {
            newX = 0; // Wrap to the left side of the screen.
        }
        Point newP = new Point(newX, this.rect.getUpperLeft().getY());
        this.rect.setUpperLeft(newP);
    }

    // Sprite Interface Methods
    /**
     * Updates the paddle's position based on keyboard input.
     * Moves the paddle left if the left arrow key is pressed,
     * and moves it right if the right arrow key is pressed.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the drawing surface to render the paddle.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(),
                (int) rect.getHeight());

    }

    // Collidable Interface Methods
    /**
     * Returns the rectangle representing the paddle's collision area.
     *
     * @return the rectangle representing the paddle's collision area.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Handles the collision of the ball with the paddle. If the ball hits the
     * paddle's
     * top, bottom, or sides, it reverses its velocity in the appropriate direction.
     *
     * @param collisionPoint  the point where the collision occurs.
     * @param currentVelocity the ball's current velocity.
     * @return the new velocity after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDX();
        double dy = currentVelocity.getDY();

        double pointX = collisionPoint.getX();
        double pointY = collisionPoint.getY();

        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();

        double width = this.rect.getWidth();
        double height = this.rect.getHeight();

        boolean hitX = Math.abs(pointX - x) < Math.abs(pointY - y)
                || Math.abs(pointX - (x + width)) < Math.abs(pointY - (y + height));
        boolean hitY = Math.abs(pointY - y) < Math.abs(pointX - x)
                || Math.abs(pointY - (y + height)) < Math.abs(pointX - (x + width));
        boolean both = Math.abs(pointX - x) == Math.abs(pointY - y);
        // Reverse velocity if collision is with the paddle's sides (hitX) or top/bottom
        // (hitY).
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
        return new Velocity(dx, dy);
    }

    /**
     * Adds this paddle to the game by adding it to both the sprite and collidable collections.
     *
     * @param g the game to which the paddle is added.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}