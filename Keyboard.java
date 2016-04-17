package by.bsuir.igor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

  private static boolean[] pressed = new boolean[4];

  @Override
  public void keyTyped(KeyEvent arg0) {}

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
      if (!Game.autoGame) {
        Game.autoGame = true;
      } else {
        Game.autoGame = false;
      }
      return;
    }
    if (e.getKeyCode() >= KeyEvent.VK_LEFT && e.getKeyCode() <= KeyEvent.VK_DOWN) {
      pressed[e.getKeyCode() - KeyEvent.VK_LEFT] = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {}

  public static boolean getPressed(int key) {
    return pressed[key - KeyEvent.VK_LEFT];
  }

  public static void setPressedFalse(int key) {
    pressed[key - KeyEvent.VK_LEFT] = false;
  }

  public static void setPressedTrue(int key) {
    pressed[key - KeyEvent.VK_LEFT] = true;
  }

}
