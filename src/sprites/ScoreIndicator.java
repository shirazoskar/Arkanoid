package sprites;

import game.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The {@code ScoreIndicator} class is a Sprite that displays the current score on the screen.
 * It uses a {@link Counter} object to track and display the score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

     /**
     * Constructs a ScoreIndicator with the given score counter.
     *
     * @param score the {@link Counter} object that holds the current score value.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draws the score on the given {@link DrawSurface}.
     *
     * @param d the DrawSurface on which to draw the score.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(350, 18, "Score: " + this.score.getValue(), 16);
    }

    /**
     * Notifies the sprite that time has passed.
     * In this implementation, this method does nothing since the score display does not need to update on its own.
     */
    @Override
    public void timePassed() {

    }
}