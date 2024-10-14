import bagel.util.Point;

import java.util.Properties;

/**
 * Represents an end flag in a game that can move and trigger a collision event.
 * The end flag moves horizontally and has behavior when it collides with another object.
 */
public class EndFlag extends GameEntity {
    private boolean isEndFlagCollide = false;

    /**
     * Constructs a new EndFlag object with the specified image path and initial position.
     *
     * @param imagePath The path to the image file used for displaying the end flag.
     * @param position The initial position of the end flag.
     */
    public EndFlag (String imagePath, Point position) {
        super(imagePath, position);
    }

    @Override
    public void moveLeft() {
        this.position = new Point(position.x + ShadowMario.END_FLAG_SPEED, position.y);
    }
    public void moveRight() {
        this.position = new Point(position.x - ShadowMario.END_FLAG_SPEED, position.y);
    }
    public void reset() {
        this.position = new Point(initialPosition.x, initialPosition.y);
        this.isEndFlagCollide = false;
    }
    public void endFlagCollide() {
        this.isEndFlagCollide = true;
    }
    public boolean isEndFlagCollide() {
        return isEndFlagCollide;
    }
}
