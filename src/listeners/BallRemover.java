package listeners;
import game.Game;
import game.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * The {@code BallRemover} class is a {@link HitListener} that is responsible for
 * removing balls from the game when a specific block is hit (e.g., a "death region").
 * It also keeps track of the number of remaining balls using a {@link Counter}.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a {@code BallRemover} with the given game and counter.
     *
     * @param game           the game from which balls will be removed.
     * @param remainingBlocks a {@link Counter} tracking the number of balls remaining.
     */
    public BallRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This method is called whenever a block is hit.
     * In this context, it removes the hitting ball from the game (e.g., when it hits a "death block"),
     * and decreases the count of remaining balls.
     *
     * @param beingHit the {@link Block} that was hit.
     * @param hitter   the {@link Ball} that hit the block and should be removed.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.remainingBlocks.decrease(1);
    }
 }