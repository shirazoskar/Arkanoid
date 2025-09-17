package listeners;
import sprites.Ball;
import sprites.Block;

/**
 * The {@code HitListener} interface should be implemented by any class that wants to be notified
 * of hit events occurring in the game (i.e., when a ball hits a block).
 */
public interface HitListener {

    /**
     * This method is called whenever the {@code beingHit} block is hit.
     * The object that is hit notifies its listeners by calling this method.
     *
     * @param beingHit the {@link Block} that was hit.
     * @param hitter   the {@link Ball} that caused the hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
 }