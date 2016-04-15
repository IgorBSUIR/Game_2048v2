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

public class Game extends JPanel implements /* KeyListener, */ Runnable {

  public Keyboard key = new Keyboard();
  public static int score = 0;
  public static int hightScore = 0;
  public int level;
  private final int TIME=50;
  private static final long serialVersionUID = 1L;
  public static final int WIDTH = 450;
  public static final int HEIGHT = 320;
  private static final int imageX = 10;
  private static final int imageY = 10;
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
    this.level=level;
    setPreferredSize(new Dimension(WIDTH, HEIGHT));

    board = new GameBoard(0, 0, level);

    setBackground(Color.WHITE);
    menuButton = new JButton("Menu");
    menuButton.setBounds(320, 50, 100, 30);
    add(menuButton);
    setAction();

    add(new TextLabel("Score: ", 320, 90));

    add(new TextLabel("Hight Score:", 320, 150));

    add(new TextLabel("2048", 340, 10));

    scoreLabel = new TextLabel(Integer.toString(score), 320, 110);
    add(scoreLabel);

    hightScoreLabel = new TextLabel(Integer.toString(hightScore), 320, 170);
    add(hightScoreLabel);

    addKeyListener(new Keyboard());
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    
    
  }


  private void update() {
    board.update();
    updateScore();
  }

  private void render() {
    Graphics2D graphic = (Graphics2D) image.getGraphics();
    board.render(graphic);

    Graphics2D g2d = (Graphics2D) this.getGraphics();
    g2d.drawImage(image, imageX, imageY, null);
    g2d.dispose();
  }

  private void updateScore(){
    scoreLabel.setText(Integer.toString(score));
    if(hightScore < score){
      hightScore = score;
      hightScoreLabel.setText(Integer.toString(hightScore));
    }
  }
  
  @Override
  public void run() {
    int time = TIME;
    while (running) {
      if(autoGame){
        if(time == 50){
          getKeyAuto();
        }
        time--;
        if(time == 0){
          time = TIME;
        }
      }
      if(board.getWon()){
        new FinalWindow("won");
        running =false;
      }
      if(board.getDead()){
        new FinalWindow("dead");
        running =false;
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

  private void getKeyAuto(){
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
    }
  }
}
