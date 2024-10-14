import bagel.Font;
import bagel.Window;
/**
 * A utility class for handling text operations specifically for drawing centered text on the screen.
 */
public class TextUtility {

    /**
     * Draws a given string of text centered horizontally on the screen.
     * This method calculates the horizontal centering based on the width of the text
     * and the current width of the window, and then draws the text at the specified vertical position.
     *
     * @param text The string text to be drawn.
     * @param font The font object used to draw the text, which contains the text styling and size.
     * @param y The y-coordinate at which to draw the text, specifying the vertical position.
     */
    public static void drawCentred (String text, Font font, int y) {
        double textWidth = font.getWidth(text);
        double x = (Window.getWidth() - textWidth) / 2 ;
        font.drawString(text, x, y);
    }
}
