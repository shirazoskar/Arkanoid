package listeners;

import game.Game;
import game.Counter;
import sprites.Block;
import sprites.Ball;

/**
 * The {@code BlockRemover} class is a {@link HitListener} that is responsible
 * for:
 * <ul>
 * <li>Removing blocks from the game when they are hit.</li>
 * <li>Decreasing the count of remaining blocks.</li>
 * </ul>
 * This listener should be added to blocks that need to be removed upon being
 * hit.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a {@code BlockRemover} with the given game and block counter.
     *
     * @param game            the game from which blocks should be removed.
     * @param remainingBlocks a {@link Counter} tracking the number of blocks
     *                        remaining in the game.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This method is called whenever a block is hit.
     * It removes the block from the game, removes this listener from the block,
     * and decreases the count of remaining blocks.
     *
     * @param beingHit the {@link Block} that was hit and should be removed.
     * @param hitter   the {@link Ball} that hit the block (not used in this
     *                 method).
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}