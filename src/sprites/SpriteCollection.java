
/**
 * Name:Shiraz Oskar
 * ID:325184000
 */
package sprites;

import java.util.List;
import java.util.ArrayList;
import biuoop.DrawSurface;

/**
 * The SpriteCollection class is a container for managing multiple sprites in
 * the game.
 * It provides methods for adding sprites, updating them over time, and drawing
 * them on the screen.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructor for creating a new SpriteCollection.
     * Initializes the list to store sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Notifies all sprites in the collection that time has passed.
     * This method calls the `timePassed()` method on each sprite, allowing them to
     * update
     * their state (e.g., position, animation, etc.) based on the passage of time.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the given drawing surface.
     * This method calls the `drawOn(DrawSurface d)` method on each sprite,
     * rendering them on the screen.
     *
     * @param d the drawing surface on which the sprites will be drawn.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}