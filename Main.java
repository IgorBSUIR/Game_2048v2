package by.bsuir.igor;

import java.io.File;

import javax.swing.JFileChooser;
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

  /**
   * constructor
   * 
   * @param args
   */
  public static void main(String[] args) {
    mainWindow = new JFrame("2048");
    menu = new Menu();
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainWindow.setLocationRelativeTo(null);
    mainWindow.add(menu);
    mainWindow.pack();
    mainWindow.setVisible(true);

  }

  /**
   * function call new Game
   */
  public static void runGame() {
    menu.setVisible(false);
    if (game != null) {
      game.stop();
      game = null;
    }
    game = new Game(level.getLevel(), null);
    level = null;
    mainWindow.setVisible(false);
    mainWindow.add(game);
    game.start();
    mainWindow.pack();
    mainWindow.setVisible(true);
  }

  /**
   * function opens a window game
   */
  public static void exitToMenu() {
    game.stop();
    game.setVisible(false);
    menu.setVisible(true);
    mainWindow.add(menu);
    mainWindow.pack();
    mainWindow.repaint();
  }

  /**
   * function opens a window level
   */
  public static void setLevel() {
    level = new Level();
    level.setVisible(true);
  }

  /**
   * function opens a setting
   */
  public static void openSetting() {
    if (setting == null) {
      setting = new LookSetting();
    }
    setting.setVisible(true);
  }

  /**
   * function pressed button exit
   */
  public static void exitGame() {
    System.exit(0);
  }

  /**
   * function opens a window game again
   */
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

  /**
   * function sets metal style
   */
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

  /**
   * function sets system style
   */
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

  /**
   * function close a window setting
   */
  public static void closeSetting() {
    setting.setVisible(false);
  }

  public static void startReplay() {
    String path;

    JFileChooser dialog = new JFileChooser(new File("SAVE"));
    dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
    dialog.setApproveButtonText("Open");
    dialog.setDialogTitle("Open save");
    dialog.setDialogType(JFileChooser.OPEN_DIALOG);
    dialog.setMultiSelectionEnabled(false);

    if (dialog.showOpenDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
      path = dialog.getSelectedFile().getName();
    } else {
      return;
    }
    menu.setVisible(false);
    if (game != null) {
      game.stop();
      game = null;
    }
    game = new Game(-1, path);
    mainWindow.setVisible(false);
    mainWindow.add(game);
    game.start();
    mainWindow.pack();
    mainWindow.setVisible(true);
  }

}
