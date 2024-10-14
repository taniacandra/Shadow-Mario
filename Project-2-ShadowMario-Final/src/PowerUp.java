import bagel.util.Point;
/**
 * Abstract class representing a general power-up in a game that can be activated and has effects on the game state.
 */

public abstract class PowerUp extends GameEntity{
    protected boolean isActivated = false;
    protected double activationFrameCount = 0;
    private static final double VERTICAL_SPEED = -10;
    private final double DISAPPEAR_MIN_Y = -10;
    protected boolean collide = false;

    /**
     * Constructs a new PowerUp object with the specified image path and initial position.
     *
     * @param imagePath The path to the image file used for displaying the power-up.
     * @param position The initial position of the power-up.
     */
    public PowerUp(String imagePath, Point position) {
        super(imagePath, position);
    }
    @Override
    public void update() {
        if (isActivated) {
            if (activationFrameCount > 0) {
                activationFrameCount--;
            } else {
                isActivated = false;
                collide = true; // Begin to disappear
            }
        }

        if (collide) {
            this.position = new Point(position.x, position.y + VERTICAL_SPEED);
            if (position.y < DISAPPEAR_MIN_Y) {
                collide = false; // Stop displaying when off-screen
            }
        }
    }

    public abstract void activate();

    public abstract void collect();

    public void reset() {
        this.position = new Point(initialPosition.x, initialPosition.y);
        this.collide = false;
        this.isActivated = false;
        this.activationFrameCount = 0;
    }

}
