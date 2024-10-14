import bagel.util.Point;
/**
 * Represents an Invincible power-up in a game that can move, be collected, and activated.
 * This power-up grants the player invincibility for a certain period when activated.
 */

public class Invincible extends PowerUp{
    private boolean isInvincibleCollide = false;
    public boolean isActivated = false;

    /**
     * Constructs a new Invincible power-up with the specified image path and initial position.
     *
     * @param imagePath The path to the image file used for displaying the power-up.
     * @param position The initial position of the power-up.
     */
    public Invincible (String imagePath, Point position) {
        super(imagePath, position);
    }

    @Override
    public void moveLeft() {
        this.position = new Point(position.x + ShadowMario.INVINCIBLE_SPEED, position.y);
    }
    public void moveRight() { this.position = new Point(position.x - ShadowMario.INVINCIBLE_SPEED, position.y); }

    public void invincibleDisappear() {
        this.collide = true;
    }
    public boolean isInvincibleCollide() { return isInvincibleCollide; }
    public void collect()  { this.isInvincibleCollide = true; }

    public void activate() {
        this.isActivated = true;
        this.activationFrameCount = ShadowMario.DOUBLE_SCORE_MAX_FRAMES;
    }
    public boolean isActivated() { return isActivated; }
}
