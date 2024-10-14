import bagel.util.Point;

public class Fireball extends GameEntity{

    private double damagedValue = ShadowMario.FIREBALL_DAMAGE_SIZE;
    private double speed = ShadowMario.FIREBALL_SPEED;
    private Point direction; // This should be a normalized vector indicating movement direction
    private boolean isFireballCollide= false;


    public Fireball(String imagePath, Point position) {
        super(imagePath, position);
        //this.direction = direction; // Assuming normalize() adjusts the vector to unit length
    }

    @Override
    public void update() {
        // Move the fireball in its direction each frame
        this.position = new Point(position.x + direction.x * speed, position.y + direction.y * speed);

    }
    public void moveLeft() {
        this.position = new Point(position.x + ShadowMario.FIREBALL_SPEED, position.y);
    }
    public void moveRight() {
        this.position = new Point(position.x - ShadowMario.FIREBALL_SPEED, position.y);
    }

    public void reset() {
        this.position = new Point(initialPosition.x, initialPosition.y);
        this.isFireballCollide = false;
    }
    public void fireballCollide() {
        this.isFireballCollide = true;
    }
    public boolean isFireballCollide() {
        return isFireballCollide;
    }

}
