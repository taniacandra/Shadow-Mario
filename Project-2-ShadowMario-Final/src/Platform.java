import bagel.util.Point;

import java.util.Properties;
/**
 * Represents a platform in a game that can only move horizontally within a defined boundary.
 */
public class Platform extends GameEntity{

    private static final int MAX_X_COORDINATE = 3000;

    /**
     * Constructs a new Platform with the specified image path and initial position.
     *
     * @param imagePath The path to the image file used for displaying the platform.
     * @param position The initial position of the platform.
     */
    public Platform (String imagePath, Point position) {
        super(imagePath, position);
    }

    @Override
    public void moveRight() {
        if (position.x < MAX_X_COORDINATE) {
            this.position = new Point(position.x - ShadowMario.PLATFORM_SPEED, position.y);
        }
    }

    public void reset() {
        this.position = new Point(initialPosition.x, initialPosition.y);
    }
}
