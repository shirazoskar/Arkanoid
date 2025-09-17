package listeners;

import game.Counter;
import sprites.Block;
import sprites.Ball;

/**
 * The {@code ScoreTrackingListener} class is a {@link HitListener} that tracks
 * the player's score.
 *
 * <p>Each time a block is hit by a ball with a different color, this listener adds
 * points to the score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a {@code ScoreTrackingListener} with a given score counter.
     *
     * @param currentScore the {@link Counter} object that keeps track of the
     * current score.
     */
    public ScoreTrackingListener(Counter currentScore) {
        this.currentScore = currentScore;
    }

    /**
     * This method is called whenever a block is hit.
     * If the ball's color does not match the block's color, the score is increased
     * by 5 points.
     *
     * @param beingHit the {@link Block} that was hit.
     * @param hitter   the {@link Ball} that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.ballColorMatch(hitter)) {
            this.currentScore.increase(5);
        }
    }
}