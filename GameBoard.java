package by.bsuir.igor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameBoard {
  private Files file = new Files();

  public static final int LINES = 4;
  public static final int COLUMNS = 4;

  protected int score = 0;
  private StringBuilder save = new StringBuilder();
  protected int startTiles = 2;
  protected Tile[][] board;
  protected boolean won = false;
  protected boolean dead = false;
  protected BufferedImage gameBoard;
  protected BufferedImage finalBoard;
  protected final int X = 0;
  protected final int Y = 0;

  protected static int SPACING = 10;
  boolean canMove;
  public static int BOARD_WIDTH = (COLUMNS + 1) * SPACING + COLUMNS * Tile.WIDTH;
  public static int BOARD_HEIGHT = (LINES + 1) * SPACING + COLUMNS * Tile.HEIGHT;

  protected GameBoard() {}

  public GameBoard(int level) {
    board = new Tile[LINES][COLUMNS];
    gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
    finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
    createBoardImage();
    start(level);
  }

  /**
   * function creates an image of the board
   */
  protected void createBoardImage() {
    Graphics2D graphics = (Graphics2D) gameBoard.getGraphics();
    graphics.setColor(Color.darkGray);
    graphics.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
    graphics.setColor(Color.lightGray);

    for (int line = 0; line < LINES; line++) {
      for (int column = 0; column < COLUMNS; column++) {
        int x = SPACING + SPACING * column + Tile.WIDTH * column;
        int y = SPACING + SPACING * line + Tile.HEIGHT * line;
        graphics.fillRoundRect(x, y, Tile.WIDTH, Tile.HEIGHT, Tile.ARC_SIZE, Tile.ARC_SIZE);
      }
    }
  }

  /**
   * function rendering board
   * 
   * @param graphic element 2d graphics, where the board will be located
   */
  public void render(Graphics2D graphic) {
    Graphics2D graphic_2 = (Graphics2D) finalBoard.getGraphics();
    graphic_2.drawImage(gameBoard, X, Y, null);

    for (int line = 0; line < LINES; line++) {
      for (int column = 0; column < COLUMNS; column++) {
        Tile current = board[line][column];
        if (current == null) {
          continue;
        }
        current.render(graphic_2);
      }
    }
    graphic.drawImage(finalBoard, X, Y, null);
    graphic_2.dispose();

    for (int line = 0; line < LINES; line++) {
      for (int column = 0; column < COLUMNS; column++) {
        Tile current = board[line][column];
        if (current == null) {
          continue;
        }
        resetPosition(current, line, column);
      }
    }
  }

  /**
   * function update board and check whether the player has won
   */

  void update() {
    checkKeys();

    for (int line = 0; line < LINES; line++) {
      for (int column = 0; column < COLUMNS; column++) {
        Tile current = board[line][column];
        if (current == null) {
          continue;
        }
        if (current.getValue() == 2048) {
          won = true;
        }
      }
    }
  }

  /**
   * function function produces displacement of the tiles
   * 
   * @param current current tile
   * @param line line, which is located tiles
   * @param column column, which is located tiles
   */
  protected void resetPosition(Tile current, int line, int column) {
    if (current == null) {
      return;
    }

    int x = getTileX(column);
    int y = getTileY(line);

    int distX = current.getX() - x;
    int distY = current.getY() - y;

    if (Math.abs(distX) < Tile.SPEED) {
      current.setX(current.getX() - distX);
    }
    if (Math.abs(distY) < Tile.SPEED) {
      current.setY(current.getY() - distY);
    }

    if (distX < 0) {
      current.setX(current.getX() + Tile.SPEED);
    } else if (distX > 0) {
      current.setX(current.getX() - Tile.SPEED);
    }

    if (distY < 0) {
      current.setY(current.getY() + Tile.SPEED);
    } else if (distY > 0) {
      current.setY(current.getY() - Tile.SPEED);
    }

  }

  /**
   * function produces the offset tile in the right direction
   * 
   * @param line
   * @param column
   * @param horizontalDirection
   * @param verticalDirection
   * @param dir - direction
   * @return can move tile
   */
  protected boolean move(int line, int column, int horizontalDirection, int verticalDirection,
      Direction dir) {
    boolean canMove = false;
    Tile current = board[line][column];
    if (current == null) {
      return false;
    }
    boolean move = true;

    int newLine = line;
    int newColumn = column;
    while (move) {
      newLine += verticalDirection;
      newColumn += horizontalDirection;
      if (checkOutOfBorder(dir, newLine, newColumn)) {
        break;
      }
      if (board[newLine][newColumn] == null) {
        board[newLine][newColumn] = current;
        board[newLine - verticalDirection][newColumn - horizontalDirection] = null;
        board[newLine][newColumn].setSlideTo(new Point(newLine, newColumn));
        canMove = true;

      } else if (board[newLine][newColumn].getValue() == current.getValue()
          && board[newLine][newColumn].isCanCombine()) {
        board[newLine][newColumn].setCanCombine(false);

        board[newLine][newColumn].setValue(board[newLine][newColumn].getValue() * 2);
        score += board[newLine][newColumn].getValue() * 10;

        canMove = true;
        board[newLine - verticalDirection][newColumn - horizontalDirection] = null;
        board[newLine][newColumn].setSlideTo(new Point(newLine, newColumn));

      } else {
        move = false;
      }
    }

    return canMove;
  }

  /**
   * Checking the location on the border of the board tiles
   * 
   * @param dir the direction of move
   * @param line position of tile
   * @param column position of tile
   * @return true or false
   */
  protected boolean checkOutOfBorder(Direction dir, int line, int column) {
    switch (dir) {
      case LEFT:
        return column < 0;
      case RIGHT:
        return column > COLUMNS - 1;
      case UP:
        return line < 0;
      case DOWN:
        return line > LINES - 1;
      default:
        break;
    }
    return false;
  }

  /**
   * offset function tiles on the board in a certain direction
   * 
   * @param dir - direction
   */

  private void movingTiles(Direction dir) {
    canMove = false;
    int horizontalDirection = 0;
    int verticalDirection = 0;

    switch (dir) {

      case LEFT:
        horizontalDirection = -1;
        for (int line = 0; line < LINES; line++) {
          for (int column = 0; column < COLUMNS; column++) {
            if (!canMove) {
              canMove = move(line, column, horizontalDirection, verticalDirection, dir);
            } else {
              move(line, column, horizontalDirection, verticalDirection, dir);
            }
          }
        }
        break;

      case RIGHT:
        horizontalDirection = 1;
        for (int line = 0; line < LINES; line++) {
          for (int column = COLUMNS - 1; column >= 0; column--) {
            if (!canMove) {
              canMove = move(line, column, horizontalDirection, verticalDirection, dir);
            } else {
              move(line, column, horizontalDirection, verticalDirection, dir);
            }
          }
        }
        break;

      case UP:
        verticalDirection = -1;
        for (int line = 0; line < LINES; line++) {
          for (int column = 0; column < COLUMNS; column++) {
            if (!canMove) {
              canMove = move(line, column, horizontalDirection, verticalDirection, dir);
            } else {
              move(line, column, horizontalDirection, verticalDirection, dir);
            }
          }
        }
        break;

      case DOWN:
        verticalDirection = 1;
        for (int line = LINES - 1; line >= 0; line--) {
          for (int column = 0; column < COLUMNS; column++) {
            if (!canMove) {
              canMove = move(line, column, horizontalDirection, verticalDirection, dir);
            } else {
              move(line, column, horizontalDirection, verticalDirection, dir);
            }
          }
        }
        break;
      default:
        break;
    }

    for (int line = 0; line < LINES; line++) {
      for (int column = 0; column < COLUMNS; column++) {
        Tile current = board[line][column];
        if (current != null) {
          current.setCanCombine(true);
        }
      }
    }

    if (canMove) {
      spawnRandom(true, dir);
      checkDead();
    }
  }

  /**
   * function check on possible moves
   */
  protected void checkDead() {
    for (int line = 0; line < LINES; line++) {
      for (int column = 0; column < COLUMNS; column++) {
        if (board[line][column] == null) {
          return;
        }
        if (checkSurroundingTile(line, column, board[line][column])) {
          return;
        }
      }
    }
    dead = true;
  }

  /**
   * the function checks whether the tile is somewhere will move
   * 
   * @param line
   * @param column
   * @param current
   * @return
   */
  protected boolean checkSurroundingTile(int line, int column, Tile current) {
    if (line > 0) {
      Tile check = board[line - 1][column];
      if (check == null) {
        return true;
      }
      if (current.getValue() == check.getValue()) {
        return true;
      }
    }
    if (line < LINES - 1) {
      Tile check = board[line + 1][column];
      if (check == null) {
        return true;
      }
      if (current.getValue() == check.getValue()) {
        return true;
      }
    }
    if (column > 0) {
      Tile check = board[line][column - 1];
      if (check == null) {
        return true;
      }
      if (current.getValue() == check.getValue()) {
        return true;
      }
    }
    if (column < COLUMNS - 1) {
      Tile check = board[line][column + 1];
      if (check == null) {
        return true;
      }
      if (current.getValue() == check.getValue()) {
        return true;
      }
    }
    return false;
  }

  /**
   * check pressed key
   */
  private void checkKeys() {

    if (Keyboard.getPressed(KeyEvent.VK_LEFT)) {
      Keyboard.setPressedFalse(KeyEvent.VK_LEFT);
      movingTiles(Direction.LEFT);
    }
    if (Keyboard.getPressed(KeyEvent.VK_RIGHT)) {
      Keyboard.setPressedFalse(KeyEvent.VK_RIGHT);
      movingTiles(Direction.RIGHT);
    }
    if (Keyboard.getPressed(KeyEvent.VK_UP)) {
      Keyboard.setPressedFalse(KeyEvent.VK_UP);
      movingTiles(Direction.UP);
    }
    if (Keyboard.getPressed(KeyEvent.VK_DOWN)) {
      Keyboard.setPressedFalse(KeyEvent.VK_DOWN);
      movingTiles(Direction.DOWN);
    }
  }

  /**
   * initial filling of the board
   * 
   * @param level
   */
  private void start(int level) {
    if (level == 1) {
      spawnRandom(false, Direction.NULL);
    }
    for (int i = 0; i < startTiles; i++) {
      spawnRandom(true, Direction.NULL);
    }
  }

  /**
   * randomly creating tiles
   * 
   * @param flag
   * @param direction press key
   */
  private void spawnRandom(boolean flag, Direction dir) {
    Random random = new Random();
    boolean notValid = true;
    while (notValid) {
      int line = random.nextInt(4);
      int column = random.nextInt(4);

      Tile current = board[line][column];
      if (current == null) {
        int value;
        if (flag) {
          value = random.nextInt(10) < 9 ? 2 : 4;
        } else {
          value = 0;
        }
        Tile tile = new Tile(value, getTileX(column), getTileY(line));
        board[line][column] = tile;
        notValid = false;
        save.append(convertToString(dir, value, line, column));
      }

    }
  }

  /**
   * convert information to String for files
   * 
   * @param dir
   * @param value
   * @param line
   * @param column
   * @return step
   */
  private String convertToString(Direction dir, int value, int line, int column) {
    String string = new String();
    switch (dir) {

      case LEFT:
        string =
            "1" + Integer.toString(value) + Integer.toString(line) + Integer.toString(column) + ';';
        break;
      case RIGHT:
        string =
            "2" + Integer.toString(value) + Integer.toString(line) + Integer.toString(column) + ';';
        break;
      case UP:
        string =
            "3" + Integer.toString(value) + Integer.toString(line) + Integer.toString(column) + ';';
        break;
      case DOWN:
        string =
            "4" + Integer.toString(value) + Integer.toString(line) + Integer.toString(column) + ';';
        break;
      default:
        string =
            "0" + Integer.toString(value) + Integer.toString(line) + Integer.toString(column) + ';';
        break;
    }

    return string;
  }

  /**
   * obtaining coordinates of column
   * 
   * @param column
   * @return
   */
  protected int getTileX(int column) {
    return SPACING + column * Tile.WIDTH + SPACING * column;
  }

  /**
   * obtaining coordinates of line
   * 
   * @param line
   * @return
   */
  protected int getTileY(int line) {
    return SPACING + line * Tile.HEIGHT + line * SPACING;
  }

  /**
   * returns value of dead
   * 
   * @return
   */
  public boolean getDead() {
    return dead;
  }

  /**
   * returns value of won
   * 
   * @return
   */
  public boolean getWon() {
    return won;
  }

  /**
   * score
   * 
   * @return
   */
  public int getScore() {
    return score;
  }

  void incCount() {}

  /**
   * Calling the file recording
   */
  public void writeInFile() {
    file.writeSave(save.toString(), score);
  }

}
