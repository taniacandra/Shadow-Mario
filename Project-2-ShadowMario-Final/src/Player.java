import bagel.util.Point;
/**
 * Represents a player character in a game that can move vertically and horizontally, jump, and interact with various game entities.
 * The player has capabilities such as jumping and collision detection with platforms, coins, enemies, end flag, power-ups, and enemy boss.
 */
public class Player extends GameEntity {

    private static final double INITIAL_JUMP_SPEED = -20;
    private static final double VERTICAL_DOWN_SPEED = 2;
    private static final double GRAVITY_EFFECT = 1;
    private double verticalSpeed = 0;
    private boolean isJumping = false;


    /**
     * Constructs a new Player with the specified image path and initial position.
     *
     * @param imagePath The path to the image file used for displaying the player.
     * @param position The initial position of the player.
     */
    public Player(String imagePath, Point position) {
        super(imagePath, position);
    }

    @Override
    public void update() {
        // applies gravity on the player
        if (isJumping) {
            this.position = new Point(position.x, position.y + verticalSpeed);
            verticalSpeed += GRAVITY_EFFECT;
        }

        // if player is on the platform and moving downward, reset variables
        if (isOnPlatform() && verticalSpeed > 0) {
            this.position = new Point(position.x, ShadowMario.PLAYER_POSITION.y);
            verticalSpeed = 0;
            isJumping = false;
        }
    }

    public void reset() {
        this.position = new Point(initialPosition.x, initialPosition.y);
    }


    public void jump() {
        if (!isJumping) {
            verticalSpeed = INITIAL_JUMP_SPEED;
            isJumping = true;
        }
    }

    private boolean isOnPlatform() {
        return position.y >= ShadowMario.PLATFORM_POSITION.y;
    }

    public void moveDown() {
        this.position = new Point(position.x, position.y + VERTICAL_DOWN_SPEED);
    }

    public boolean isOnFlyingPlatform(FlyingPlatform flyingPlatform) {

        double distanceX = Math.abs(this.position.x - flyingPlatform.position.x);
        double distanceY = Math.abs(this.position.y - flyingPlatform.position.y);

        return distanceX < ShadowMario.FLYING_PLATFORM_HALF_LENGTH &&
                distanceY <= ShadowMario.FLYING_PLATFORM_HALF_HEIGHT &&
                distanceY >= (ShadowMario.FLYING_PLATFORM_HALF_HEIGHT - 1);
    }

    public double getVerticalSpeed() {
        return this.verticalSpeed;
    }

    public void setVerticalSpeed(double speed) {
        this.verticalSpeed = speed;
    }

    public boolean setIsJumping(boolean isJumping) {
        return this.isJumping = isJumping;
    }

    // Other collision methods like coinCollide, enemyCollide, etc., follow a similar pattern:
    // Each method checks for a specific type of collision, adjusts the player's state if a collision occurs,
    // and returns a boolean indicating whether or not the collision happened.
    public boolean coinCollide(GameEntity entity) {
        double radius;
        double distance;

        distance = Math.sqrt(Math.pow(this.position.x - entity.position.x, 2) +
                Math.pow(this.position.y - entity.position.y, 2));
        radius = ShadowMario.COIN_RADIUS + ShadowMario.PLAYER_RADIUS;

        // no collision if don't collide with coin or coin is already collected
        if (!(entity instanceof Coin) || ((Coin) entity).isCoinCollected()) {
            return false;
        }

        // collect coin within the detection range
        if (distance <= radius) {
            ((Coin) entity).collect();
            return true;
        }
        return false;
    }
    public boolean enemyCollide(GameEntity entity) {
        double radius;
        double distance;
        distance = Math.sqrt(Math.pow(this.position.x - entity.position.x, 2) +
                Math.pow(this.position.y - entity.position.y, 2));
        radius = ShadowMario.ENEMY_RADIUS + ShadowMario.PLAYER_RADIUS;

        // no collision if don't collide with enemy or enemy is already collided
        if (!(entity instanceof Enemy) || ((Enemy) entity).isEnemyCollide()) {
            return false;
        }

        // collide with enemy within the detection range
        if (distance <= radius) {
            ((Enemy) entity).enemyCollide();
            return true;
        }
        return false;
    }
    public boolean endFlagCollide(GameEntity entity) {
        double radius;
        double distance;

        distance = Math.sqrt(Math.pow(this.position.x - entity.position.x, 2) +
                Math.pow(this.position.y - entity.position.y, 2));
        radius = ShadowMario.END_FLAG_RADIUS + ShadowMario.PLAYER_RADIUS;

        // no collision if don't collide with endFlag or endFlag is already collided
        if (!(entity instanceof EndFlag) || ((EndFlag) entity).isEndFlagCollide()) {
            return false;
        }

        // collide with endFlag within the detection range
        if (distance <= radius) {
            ((EndFlag) entity).endFlagCollide();
            return true;
        }
        return false;
    }

    public boolean doubleScoreCollide(GameEntity entity) {
        double radius;
        double distance;

        distance = Math.sqrt(Math.pow(this.position.x - entity.position.x, 2) +
                Math.pow(this.position.y - entity.position.y, 2));
        radius = ShadowMario.DOUBLE_SCORE_RADIUS + ShadowMario.DOUBLE_SCORE_RADIUS;

        // no collision if don't collide with doubleScore or doubleScore is already collected
        if (!(entity instanceof DoubleScore) || ((DoubleScore) entity).isDoubleScoreCollide()) {
            return false;
        }

        // collect doubleScore within the detection range
        if (distance <= radius) {
            ((DoubleScore) entity).collect();
            return true;
        }
        return false;
    }

    public boolean invincibleCollide(GameEntity entity) {
        double radius;
        double distance;

        distance = Math.sqrt(Math.pow(this.position.x - entity.position.x, 2) +
                Math.pow(this.position.y - entity.position.y, 2));
        radius = ShadowMario.INVINCIBLE_RADIUS + ShadowMario.INVINCIBLE_RADIUS;

        // no collision if don't collide with invincible or invincible is already collected
        if (!(entity instanceof Invincible) || ((Invincible) entity).isInvincibleCollide()) {
            return false;
        }

        // collect invincible within the detection range
        if (distance <= radius) {
            ((Invincible) entity).collect();
            return true;
        }
        return false;
    }
    public boolean enemyBossDetection(GameEntity entity) {
        double radius;
        double distance;

        distance = Math.sqrt(Math.pow(this.position.x - entity.position.x, 2) +
                Math.pow(this.position.y - entity.position.y, 2));
        radius = ShadowMario.ENEMY_BOSS_ACTIVATION_RADIUS + ShadowMario.ENEMY_BOSS_ACTIVATION_RADIUS;

        // no collision if don't collide with enemyBoss
        if (!(entity instanceof EnemyBoss) || ((EnemyBoss) entity).isEnemyBossCollide()) {
            return false;
        }

        // enemyBoss collide within the detection range
        if (distance <= radius) {
            ((EnemyBoss) entity).enemyBossCollide();
            return true;
        }
        return false;
    }
}