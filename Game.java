package by.bsuir.igor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {

  private int hightScore = 0;
  public int level;

  private final int TIME = 50;
  private static final long serialVersionUID = 1L;

  public static final int WIDTH = 450;
  public static final int HEIGHT = 320;
  private static final int IMAGE_X = 10;
  private static final int IMAGE_Y = 10;

  private final int TEXT_SCORE_X = 320;
  private final int TEXT_SCORE_Y = 90;

  private final int TEXT_H_SCORE_X = 320;
  private final int TEXT_H_SCORE_Y = 150;

  private final int H_SCORE_LBL_X = 320;
  private final int H_SCORE_LBL_Y = 170;

  private final int SCORE_LBL_X = 320;
  private final int SCORE_LBL_Y = 110;

  private final int TEXT_NAME_X = 340;
  private final int TEXT_NAME_Y = 10;

  private final int BUTTON_X = 320;
  private final int BUTTON_Y = 50;
  private final int BUTTON_WIDTH = 100;
  private final int BUTTON_HEIGHT = 30;

  public static final Font main = new Font("FONT", Font.PLAIN, 28);
  private Thread game;
  private TextLabel scoreLabel;
  private TextLabel hightScoreLabel;
  private boolean running;
  private BufferedImage image =
      new BufferedImage(WIDTH - 160, HEIGHT - 30, BufferedImage.TYPE_INT_RGB);
  private GameBoard board;
  private JButton menuButton;
  public static boolean autoGame;

  public Game(int level) {
    setLayout(null);
    this.level = level;
    setPreferredSize(new Dimension(WIDTH, HEIGHT));

    board = new GameBoard(level);

    setBackground(Color.WHITE);
    menuButton = new JButton("Menu");
    menuButton.setBounds(BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
    add(menuButton);
    setAction();

    add(new TextLabel("Score: ", TEXT_SCORE_X, TEXT_SCORE_Y));

    add(new TextLabel("Hight Score:", TEXT_H_SCORE_X, TEXT_H_SCORE_Y));

    add(new TextLabel("2048", TEXT_NAME_X, TEXT_NAME_Y));

    scoreLabel = new TextLabel(Integer.toString(board.getScore()), SCORE_LBL_X, SCORE_LBL_Y);
    add(scoreLabel);

    hightScoreLabel = new TextLabel(Integer.toString(hightScore), H_SCORE_LBL_X, H_SCORE_LBL_Y);
    add(hightScoreLabel);

    addKeyListener(new Keyboard());
    setFocusable(true);
  }


  private void update() {
    board.update();
    updateScore();
  }

  private void render() {
    Graphics2D graphic = (Graphics2D) image.getGraphics();
    board.render(graphic);

    Graphics2D g2d = (Graphics2D) this.getGraphics();
    g2d.drawImage(image, IMAGE_X, IMAGE_Y, null);
    g2d.dispose();
  }

  private void updateScore() {
    scoreLabel.setText(Integer.toString(board.getScore()));
    if (hightScore < board.getScore()) {
      hightScore = board.getScore();
      hightScoreLabel.setText(Integer.toString(hightScore));
    }
  }

  @Override
  public void run() {
    int time = TIME;
    while (running) {
      if (autoGame) {
        if (time == TIME) {
          getKeyAuto();
        }
        time--;
        if (time == 0) {
          time = TIME;
        }
      }
      if (board.getWon()) {
        new FinalWindow("won");
        running = false;
      }
      if (board.getDead()) {
        new FinalWindow("dead");
        running = false;
      }
      update();
      render();
    }
  }

  public synchronized void start() {
    if (running) {
      return;
    }
    running = true;
    game = new Thread(this, "Game 2048");
    game.start();

  }

  public synchronized void stop() {
    if (!running) {
      return;
    }
    running = false;
    game = null;
  }

  private void setAction() {

    menuButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Main.exitToMenu();
      }
    });
  }

  private void getKeyAuto() {
    Random random = new Random();
    switch (random.nextInt(4)) {
      case 0:
        Keyboard.setPressedTrue(KeyEvent.VK_LEFT);
        break;
      case 1:
        Keyboard.setPressedTrue(KeyEvent.VK_RIGHT);
        break;
      case 2:
        Keyboard.setPressedTrue(KeyEvent.VK_UP);
        break;
      case 3:
        Keyboard.setPressedTrue(KeyEvent.VK_DOWN);
        break;
      default:
        break;
    }
  }
}
