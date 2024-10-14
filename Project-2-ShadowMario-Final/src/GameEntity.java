import bagel.Image;
import bagel.util.Point;

import java.util.Properties;
/**
 * An abstract class represents an entity in the game with an image and position.
 * It serves as a base class for all game entities that have a drawable image and can move.
 */
public abstract class GameEntity {
    protected Image image;
    protected Point position;
    protected Point initialPosition;

    /**
     * Constructs a new GameEntity with the specified image path and position.
     *
     * @param imagePath the path to the image file for this entity
     * @param position the initial position of the entity
     */
    public GameEntity(String imagePath, Point position) {
        this.image = new Image(imagePath);
        this.position = new Point(position.x, position.y);
        this.initialPosition = new Point(position.x, position.y);
    }
    public void update() {}
    public void moveLeft() {}
    public void moveRight() {}
    public void draw() {
        image.drawFromTopLeft(position.x - image.getWidth() / 2.0, position.y - image.getHeight() / 2.0);
    }
    public void reset() {} //change to abstract again

}

