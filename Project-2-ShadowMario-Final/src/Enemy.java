import bagel.util.Point;

import java.util.Properties;

/**
 * Represents an enemy in the game. This class handles enemy movement,
 * including randomized movements, and tracks collisions with the player or other entities.
 *
 */

public class Enemy extends GameEntity implements Randomisable{
    private boolean isEnemyCollide = false;
    private int direction;
    private int maxDisplacement = ShadowMario.ENEMY_MAX_RANDOM_DISPLACEMENT;
    private int currDisplacement = 0;

    /**
     * Constructs a new Enemy with a specified image and initial position.
     * The enemy's random movement is also initialized upon creation.
     *
     * @param imagePath The path to the image file used for rendering the enemy.
     * @param position The initial position of the enemy.
     */

    public Enemy (String imagePath, Point position) {
        super(imagePath, position);
        initialiseRandomMovement();
    }

    @Override
    public void update() {
        updateRandomMovement();
    }
    public void moveLeft() {
        this.position = new Point(position.x + ShadowMario.ENEMY_SPEED, position.y);
    }
    public void moveRight() {
        this.position = new Point(position.x - ShadowMario.ENEMY_SPEED, position.y);
    }
    public void reset() {
        this.position = new Point(initialPosition.x, initialPosition.y);
        this.isEnemyCollide = false;
    }
    public boolean isEnemyCollide() { return isEnemyCollide; }
    public void enemyCollide() { this.isEnemyCollide = true; }

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
