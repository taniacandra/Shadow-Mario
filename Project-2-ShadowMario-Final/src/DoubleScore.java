import bagel.util.Point;
/**
 * Represents a Double Score power-up in a game that can move, be collected, and activated.
 * This power-up doubles the score for a certain period when activated.
 */
public class DoubleScore extends PowerUp {
    private boolean isDoubleScoreCollide = false;
    public boolean isActivated = false;

    /**
     * Constructs a new DoubleScore power-up with the specified image path and initial position.
     *
     * @param imagePath The path to the image file used for displaying the power-up.
     * @param position The initial position of the power-up.
     */
    public DoubleScore (String imagePath, Point position) {
        super(imagePath, position);
    }

    @Override
    public void moveLeft() {
        this.position = new Point(position.x + ShadowMario.DOUBLE_SCORE_SPEED, position.y);
    }
    public void moveRight() { this.position = new Point(position.x - ShadowMario.DOUBLE_SCORE_SPEED, position.y); }

    public void doubleScoreDisappear() { this.collide = true; }
    public boolean isDoubleScoreCollide() { return isDoubleScoreCollide; }
    public void collect() { this.isDoubleScoreCollide = true; }

    public void activate() {
        this.isActivated = true;
        this.activationFrameCount = ShadowMario.DOUBLE_SCORE_MAX_FRAMES;
    }
    public boolean isActivated() { return isActivated; }

}
