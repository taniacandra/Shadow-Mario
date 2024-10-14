import java.util.Random;
/**
 * Interface defining for game entities that handle randomized movement behavior.
 * Entities implementing this interface can initialize and update their movement.
 */
public interface Randomisable {
    Random rand = new Random();

    void initialiseRandomMovement();
    void updateRandomMovement();

    default int getRandomBoolean() {
        return rand.nextBoolean() ? 1 : -1;
    }

}
