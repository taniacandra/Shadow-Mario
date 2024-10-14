import bagel.util.Point;

/**
 * Represents a coin in a game that can move and disappear based on interactions.
 * The coin has the ability to move vertically, disappear after certain conditions are met,
 * and reset to its original state.
 */
public class Coin extends GameEntity{
    private static final double VERTICAL_SPEED = -10;
    private final double COIN_DISAPPEAR_MIN_Y = -10;
    private boolean collide = false;
    private boolean isCoinCollected = false;

    /**
     * Constructs a new Coin object with the specified image path and initial position.
     *
     * @param imagePath The path to the image file used for displaying the coin.
     * @param position The initial position of the coin.
     */
    public Coin (String imagePath, Point position) { super(imagePath, position); }

    @Override
    public void update() {
        if (collide) {
            this.position = new Point(position.x, position.y + VERTICAL_SPEED);
            if (position.y < COIN_DISAPPEAR_MIN_Y) {
                this.collide = false;
            }
        }
    }
    public void moveLeft() {
        this.position = new Point(position.x + ShadowMario.COIN_SPEED, position.y);
    }
    public void moveRight() { this.position = new Point(position.x - ShadowMario.COIN_SPEED, position.y); }
    public void reset() {
        this.position = new Point(initialPosition.x, initialPosition.y);
        this.collide = false;
        this.isCoinCollected = false;
    }
    public void coinDisappear() {
        this.collide = true;
    }
    public boolean isCoinCollected() {
        return isCoinCollected;
    }
    public void collect() { this.isCoinCollected = true; }
}


