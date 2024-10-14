import bagel.util.Point;
/**
 * Represents a flying platform in a game that can move horizontally with randomized patterns.
 * This platform can reverse its direction when reaching a specified displacement limit.
 */
public class FlyingPlatform extends GameEntity implements Randomisable {
    private int direction;
    private double maxDisplacement = ShadowMario.FLYING_PLATFORM_MAX_RANDOM_DISPLACEMENT;
    private int currDisplacement = 0;

    /**
     * Constructs a new FlyingPlatform with the specified image path and initial position.
     *
     * @param imagePath The path to the image file used for displaying the flying platform.
     * @param position The initial position of the flying platform.
     */
    public FlyingPlatform (String imagePath, Point position) {
        super(imagePath, position);
        initialiseRandomMovement();
    }

    @Override
    public void update() {
        updateRandomMovement();
    }
    public void moveRight() { this.position = new Point(position.x - ShadowMario.FLYING_PLATFORM_SPEED, position.y); }
    public void moveLeft() {
        this.position = new Point(position.x + ShadowMario.FLYING_PLATFORM_SPEED, position.y);
    }
    public void reset() {
        this.position = new Point(initialPosition.x, initialPosition.y);
    }

    public void initialiseRandomMovement() {
        direction = getRandomBoolean();
    }

    public void updateRandomMovement() {
        // Check if the enemy needs to reverse direction
        if (Math.abs(currDisplacement) >= maxDisplacement) {
            direction *= -1; // Reverse the direction
        }

        // Update position based on the current direction
        position = new Point(position.x + direction * ShadowMario.ENEMY_RANDOM_SPEED, position.y);
        currDisplacement += direction * ShadowMario.ENEMY_RANDOM_SPEED; // Update displacement
    }


}
