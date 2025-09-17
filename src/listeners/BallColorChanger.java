package listeners;

import sprites.Ball;
import sprites.Block;

/**
 * A HitListener that changes the color of the ball
 * to the color of the block it hit, if the colors differ.
 */
public class BallColorChanger implements HitListener {

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Change ball color to block color only if they differ
        if (!beingHit.ballColorMatch(hitter)) {
            hitter.setColor(beingHit.getColor());
        }
    }
}
