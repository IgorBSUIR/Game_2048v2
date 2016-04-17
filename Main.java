package by.bsuir.igor;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class Main {
  private static Game game;
  private static LookSetting setting;
  private static Menu menu;
  private static JFrame mainWindow;
  private static Level level;

  public static void main(String[] args) {
    mainWindow = new JFrame("2048");
    menu = new Menu();
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainWindow.setLocationRelativeTo(null);
    mainWindow.add(menu);
    mainWindow.pack();
    mainWindow.setVisible(true);

  }

  public static void runGame() {
    menu.setVisible(false);
    if (game != null) {
      game.stop();
      game = null;
    }
    game = new Game(level.getLevel());
    level = null;
    mainWindow.setVisible(false);
    mainWindow.add(game);
    game.start();
    mainWindow.pack();
    mainWindow.setVisible(true);
  }

  public static void exitToMenu() {
    game.stop();
    game.setVisible(false);
    menu.setVisible(true);
    mainWindow.add(menu);
    mainWindow.pack();
    mainWindow.repaint();
  }

  public static void setLevel() {
    level = new Level();
    level.setVisible(true);
  }

  public static void openSetting() {
    if (setting == null) {
      setting = new LookSetting();
    }
    setting.setVisible(true);
  }

  public static void exitGame() {
    System.exit(0);
  }

  public static void resumeGame() {
    if (game == null) {
      return;
    }
    menu.setVisible(false);
    game.setVisible(true);
    mainWindow.add(game);
    mainWindow.pack();
    mainWindow.setVisible(true);
    game.start();

  }

  public static void initMetalLook() {
    try {
      UIManager.setLookAndFeel(new MetalLookAndFeel());
      SwingUtilities.updateComponentTreeUI(mainWindow);
      SwingUtilities.updateComponentTreeUI(setting);
    } catch (UnsupportedLookAndFeelException e) {
      System.err.println("Can't use this LookAndFell");
    } catch (Exception e) {
      System.err.println("Error");
    }
    mainWindow.repaint();
    setting.repaint();
  }

  public static void initSystemLook() {
    try {
      String systemLook = UIManager.getSystemLookAndFeelClassName();
      UIManager.setLookAndFeel(systemLook);
      SwingUtilities.updateComponentTreeUI(mainWindow);
      SwingUtilities.updateComponentTreeUI(setting);
    } catch (UnsupportedLookAndFeelException e) {
      System.err.println("Can't use this LookAndFell");
    } catch (Exception e) {
      System.err.println("Error");
    }
    mainWindow.repaint();
    setting.repaint();

  }

  public static void closeSetting() {
    setting.setVisible(false);
  }

}
