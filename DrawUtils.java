package by.bsuir.igor;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class DrawUtils {
  /**
   * returns width a string
   * 
   * @param message
   * @param font
   * @param g
   * @return
   */
  public static int getMessageWidth(String message, Font font, Graphics2D g) {
    g.setFont(font);
    Rectangle2D bounds = g.getFontMetrics().getStringBounds(message, g);
    return (int) bounds.getWidth();
  }

  /**
   * returns height a string
   * 
   * @param message
   * @param font
   * @param g
   * @return
   */
  public static int getMessageHeight(String message, Font font, Graphics2D g) {
    g.setFont(font);
    Rectangle2D bounds = g.getFontMetrics().getStringBounds(message, g);
    return (int) bounds.getHeight() - 20;
  }
}
