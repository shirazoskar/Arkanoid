/**
 * Name:Shiraz Oskar
 * ID:325184000
 */
package sprites;
import biuoop.DrawSurface;

/**
 * The Sprite interface represents an object that can be drawn on the screen and updated over time.
 * A sprite is a moving or static object in the game that can be rendered and updated during the game loop.
 */
public interface Sprite {

    /**
     * Draws the sprite on the given drawing surface.
     * This method is called to render the sprite on the screen.
     *
     * @param d the drawing surface on which the sprite will be drawn.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     * This method is called during the game loop to update the sprite's state (e.g., position, animation).
     */
    void timePassed();
 }