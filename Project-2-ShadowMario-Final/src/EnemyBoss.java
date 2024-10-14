import bagel.util.Point;
/**
 * Represents an enemy boss in a game that can move horizontally and detect collisions.
 * This class handles the movement and collision state of the enemy boss.
 */
public class EnemyBoss extends GameEntity {
    private boolean isEnemyBossCollide = false;

    /**
     * Constructs a new EnemyBoss with the specified image path and initial position.
     *
     * @param imagePath The path to the image file used for displaying the enemy boss.
     * @param position The initial position of the enemy boss.
     */
    public EnemyBoss(String imagePath, Point position) {
        super(imagePath, position);
    }

    @Override
    public void moveLeft() { this.position = new Point(position.x + ShadowMario.ENEMY_BOSS_SPEED, position.y); }
    public void moveRight() {
        this.position = new Point(position.x - ShadowMario.ENEMY_BOSS_SPEED, position.y);
    }
    public void reset() {
        this.position = new Point(initialPosition.x, initialPosition.y);
        this.isEnemyBossCollide = false;
    }
    public void enemyBossCollide() {
        this.isEnemyBossCollide = true;
    }
    public boolean isEnemyBossCollide() {
        return isEnemyBossCollide;
    }
}