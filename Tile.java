package by.bsuir.igor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Tile {
  public static final int WIDTH = 60;
  public static final int HEIGHT = 60;
  public static final int SPEED = 5;

  public static final int ARC_SIZE = 15;
  private int value;
  private BufferedImage tileImage;
  private Color background;
  private Font font;
  private Point slideTo;

  private final int RECT_X = 0;
  private final int RECT_Y = 0;
  private int x;
  private int y;

  private boolean canCombine = true;

  public Tile(int value, int x, int y) {
    this.value = value;
    this.x = x;
    this.y = y;
    slideTo = new Point(x, y);
    tileImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    drawImage();
  }

  private void drawImage() {
    Graphics2D g = (Graphics2D) tileImage.getGraphics();
    switch (value) {
      case 0:
        background = Color.BLACK;
        break;
      case 2:
        background = new Color(0xFFE500);
        break;
      case 4:
        background = new Color(0xCCB800);
        break;
      case 8:
        background = new Color(0xFFAE00);
        break;
      case 16:
        background = new Color(0xD99400);
        break;
      case 32:
        background = new Color(0xeFF6200);
        break;
      case 64:
        background = new Color(0xAB4100);
        break;
      case 128:
        background = new Color(0xF71900);
        break;
      case 256:
        background = new Color(0x910F00);
        break;
      case 512:
        background = new Color(0xFF00C8);
        break;
      case 1024:
        background = new Color(0xB108FF);
        break;
      case 2048:
        background = new Color(0x3D08FF);
        break;
      default:
        background = Color.GREEN;
        break;
    }
    g.setColor(Color.DARK_GRAY);
    g.fillRect(RECT_X, RECT_Y, WIDTH, HEIGHT);

    g.setColor(background);
    g.fillRoundRect(RECT_X, RECT_Y, WIDTH, HEIGHT, ARC_SIZE, ARC_SIZE);

    g.setColor(Color.BLACK);

    if (value <= 64) {
      font = Game.main.deriveFont(36f);
    } else {
      font = Game.main;
    }
    g.setFont(font);

    if (value != 0) {
      int DrawX = WIDTH / 2 - DrawUtils.getMessageWidth("" + value, font, g) / 2;
      int DrawY = HEIGHT / 2 + DrawUtils.getMessageHeight("" + value, font, g) / 2;
      g.drawString("" + value, DrawX, DrawY);
    }
    g.dispose();
  }

  public void render(Graphics2D graphic) {
    graphic.drawImage(tileImage, x, y, null);
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
    drawImage();
  }

  public boolean isCanCombine() {
    return canCombine;
  }

  public void setCanCombine(boolean canCombine) {
    this.canCombine = canCombine;
  }

  public Point getSlideTo() {
    return slideTo;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setSlideTo(Point slideTo) {
    this.slideTo = slideTo;

  }
}
