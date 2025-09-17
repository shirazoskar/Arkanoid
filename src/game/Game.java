/**
 * Name:Shiraz Oskar
 * ID:325184000
 */
package game;

import sprites.Ball;
import sprites.Block;
import sprites.SpriteCollection;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.Paddle;
import collisions.Collidable;
import geom.Point;
import geom.Rectangle;
import geom.Velocity;
import listeners.BallColorChanger;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.HitListener;
import listeners.ScoreTrackingListener;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * The Game class manages the overall game logic and mechanics.
 * It is responsible for managing sprites, collidables, and the game
 * environment.
 * The class handles the game initialization, running the animation loop, and
 * updating all game objects.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.KeyboardSensor keyboard;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBall;
    private Counter score;

    /**
     * Constructor initializes a new game with a fresh environment and sprite
     * collection.
     */
    public Game() {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.remainingBlocks = new Counter();
        this.remainingBall = new Counter();
        this.score = new Counter();
    }

    /**
     * Constructor initializes a new game with the provided GUI.
     * It also initializes the keyboard sensor.
     *
     * @param gui the graphical user interface of the game.
     */
    public Game(GUI gui) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.keyboard = gui.getKeyboardSensor();
        this.gui = gui;
        this.remainingBlocks = new Counter();
        this.remainingBall = new Counter();
        this.score = new Counter();
    }

    /**
     * Adds a collidable object to the game's environment.
     *
     * @param c the collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the game's collection of sprites.
     *
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes the game by creating and adding all the necessary elements
     * (balls, paddle, walls, blocks).
     * The elements are added to the game environment and sprite collection.
     */
    public void initialize() {
        HitListener colorChanger = new BallColorChanger();
        this.remainingBlocks = new Counter();
        this.remainingBall = new Counter();
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, remainingBall);

        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        this.addSprite(scoreIndicator);

        // ---- 1) Balls ----
        Ball b1 = new Ball(200, 200, 4, Color.BLACK);
        Ball b2 = new Ball(300, 200, 4, Color.BLACK);
        Ball b3 = new Ball(400, 200, 4, Color.BLACK);
        remainingBall.increase(3);
        b1.setVelocity(Velocity.fromAngleAndSpeed(340, 4));
        b2.setVelocity(Velocity.fromAngleAndSpeed(40, 4));
        b3.setVelocity(Velocity.fromAngleAndSpeed(70, 4));
        b1.setGameEnvirnment(this.environment);
        b2.setGameEnvirnment(this.environment);
        b3.setGameEnvirnment(this.environment);
        b1.addToGame(this);
        b2.addToGame(this);
        b3.addToGame(this);

        // ---- 2) Paddle ----
        Point paddleP = new Point(400, 560);
        Rectangle paddleR = new Rectangle(paddleP, 80, 20);
        Paddle paddle = new Paddle(paddleR, Color.CYAN, this.keyboard);
        paddle.addToGame(this);

        // ---- 3) Walls and corner----
        Block topWall = new Block(0, 20, 900, 10, Color.GRAY);
        Block leftWall = new Block(0, 20, 10, 700, Color.GRAY);
        Block rightWall = new Block(790, 20, 10, 700, Color.GRAY);

        Block topWallC = new Block(0, 20, 10, 10, Color.GRAY);
        Block bottomWallC = new Block(0, 590, 10, 10, Color.GRAY);
        Block leftWallC = new Block(0, 20, 10, 10, Color.GRAY);
        Block rightWallC = new Block(790, 20, 10, 10, Color.GRAY);

        topWallC.addToGame(this);
        bottomWallC.addToGame(this);
        leftWallC.addToGame(this);
        rightWallC.addToGame(this);

        topWall.addToGame(this);
        leftWall.addToGame(this);
        rightWall.addToGame(this);

        Block deathBlock = new Block(0, 590, 900, 10, Color.DARK_GRAY);
        deathBlock.addHitListener(new BallRemover(this, remainingBall));
        deathBlock.addToGame(this);

        // ---- 4) Pattern of blocks ----
        Color[] colors = {
                Color.BLUE, // row 0
                Color.CYAN, // row 1
                Color.GREEN, // row 2
                Color.YELLOW, // row 3
                Color.ORANGE, // row 4
                Color.PINK // row 5
        };

        int blockW = 45;
        int blockH = 20;

        int startX = 250;
        int startY = 100;

        // Create a pattern of blocks arranged in rows with decreasing count.
        for (int i = 0; i < colors.length; i++) {
            int count = 12 - i;
            int offsetX = i * blockW;
            int y = startY + i * blockH;
            for (int j = 0; j < count; j++) {
                int x = startX + 10 + offsetX + j * blockW;
                Block block = new Block(x, y, blockW, blockH, colors[i]);
                block.addToGame(this);

                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
                block.addHitListener(colorChanger);

                this.remainingBlocks.increase(1);
            }
        }
    }

    /**
     * Starts the animation loop for the game.
     * This loop continuously updates and draws the game state, and handles timing
     * for frame rate.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        this.initialize(); // Initialize game elements.

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (this.remainingBlocks.getValue() > 0 && this.remainingBall.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100);
            System.out.println("You Win!\n"
                    + "Your score is: " + this.score.getValue());
        }
        if (this.remainingBall.getValue() == 0) {
            System.out.println("Game Over.\n"
                    + "Your score is: " + this.score.getValue());
        }
        gui.close();
    }

    /**
     * Removes the given collidable from the game environment.
     *
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes the given sprite from the game.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * The main method to start the game.
     * <p>
     * This method creates a GUI window with a title and dimensions,
     * initializes the game with that GUI, and starts the animation loop.
     * </p>
     *
     * @param args the command line arguments (not used here).
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Game", 800, 600);
        Game game = new Game(gui);
        game.run();
    }
}