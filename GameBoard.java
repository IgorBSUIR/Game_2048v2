package by.bsuir.igor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameBoard {

  public static final int LINES = 4;
  public static final int COLUMNS = 4;

  private int startTiles = 2;
  private Tile[][] board;
  private boolean won;
  private boolean dead;
  private BufferedImage gameBoard;
  private BufferedImage finalBoard;
  private int x;
  private int y;

  private static int SPACING = 10;
  public static int BOARD_WIDTH = (COLUMNS + 1) * SPACING + COLUMNS * Tile.SIZE;
  public static int BOARD_HEIGHT = (LINES + 1) * SPACING + COLUMNS * Tile.SIZE;

  public GameBoard(int x, int y, int level) {
    this.x = x;
    this.y = y;
    board = new Tile[LINES][COLUMNS];
    gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
    finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
    createBoardImage();
    start(level);
  }

  private void createBoardImage() {
    Graphics2D graphics = (Graphics2D) gameBoard.getGraphics();
    graphics.setColor(Color.darkGray);
    graphics.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
    graphics.setColor(Color.lightGray);

    for (int line = 0; line < LINES; line++) {
      for (int column = 0; column < COLUMNS; column++) {
        int x = SPACING + SPACING * column + Tile.SIZE * column;
        int y = SPACING + SPACING * line + Tile.SIZE * line;
        graphics.fillRoundRect(x, y, Tile.SIZE, Tile.SIZE, Tile.ARC_SIZE, Tile.ARC_SIZE);
      }
    }
  }

  public void render(Graphics2D graphic) {
    Graphics2D graphic_2 = (Graphics2D) finalBoard.getGraphics();
    graphic_2.drawImage(gameBoard, 0, 0, null);

    for (int line = 0; line < LINES; line++) {
      for (int column = 0; column < COLUMNS; column++) {
        Tile current = board[line][column];
        if (current == null) {
          continue;
        }
        current.render(graphic_2);
      }
    }
    graphic.drawImage(finalBoard, x, y, null);
    graphic_2.dispose();
  }

  public void update() {
    checkKeys();

    for (int line = 0; line < LINES; line++) {
      for (int column = 0; column < COLUMNS; column++) {
        Tile current = board[line][column];
        if (current == null) {
          continue;
        }
        resetPosition(current, line, column);
        if (current.getValue() == 2048) {
          won = true;
        }
      }
    }
  }


  private void resetPosition(Tile current, int line, int column) {
    if (current == null){
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
    }

    if (distY < 0) {
      current.setY(current.getY() + Tile.SPEED);
    }

    if (distX > 0) {
      current.setX(current.getX() - Tile.SPEED);
    }

    if (distY > 0) {
      current.setY(current.getY() - Tile.SPEED);
    }

  }

  private boolean move(int line, int column, int horizontalDirection, int verticalDirection,
      Direction dir) {
    boolean canMove = false;
    Tile current = board[line][column];
    if (current == null){
      return false;
    }
    boolean move = true;

    int newLine = line;
    int newColumn = column;
    while (move) {
      newLine += verticalDirection;
      newColumn += horizontalDirection;
      if (checkOutOfBorder(dir, newLine, newColumn)){
        break;
      }
      if (board[newLine][newColumn] == null) {
        board[newLine][newColumn] = current;
        board[newLine - verticalDirection][newColumn - horizontalDirection] = null;
        board[newLine][newColumn].setSlideTo(new Point(newLine, newColumn));
        canMove = true;
      } else if (board[newLine][newColumn].getValue() == current.getValue()
          && board[newLine][newColumn].CanCombine()) {
        board[newLine][newColumn].setCanCombine(false);
        board[newLine][newColumn].setValue(board[newLine][newColumn].getValue() * 2);
        Game.score+=board[newLine][newColumn].getValue()*10;
        canMove = true;
        board[newLine - verticalDirection][newColumn - horizontalDirection] = null;
        board[newLine][newColumn].setSlideTo(new Point(newLine, newColumn));
        //
        //
      } else {
        move = false;
      }
    }

    return canMove;
  }

  private boolean checkOutOfBorder(Direction dir, int line, int column) {
    if (dir == Direction.LEFT) {
      return column < 0;
    } else if (dir == Direction.RIGHT) {
      return column > COLUMNS - 1;
    } else if (dir == Direction.UP) {
      return line < 0;
    } else if (dir == Direction.DOWN) {
      return line > LINES - 1;
    }
    return false;
  }

  private void movingTiles(Direction dir) {
    boolean canMove = false;
    int horizontalDirection = 0;
    int verticalDirection = 0;

    if (dir == Direction.LEFT) {
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
    }

    else if (dir == Direction.RIGHT) {
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
    }

    else if (dir == Direction.UP) {
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
    }

    else if (dir == Direction.DOWN) {
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
    }

    else {
      System.out.println(dir + "is not a valid direction");
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
      spawnRandom(true);
      checkDead();
    }
  }

  private void checkDead() {
    for (int line = 0; line < LINES; line++) {
      for (int column = 0; column < COLUMNS; column++) {
        if (board[line][column] == null){
          return;
        }
        if (checkSurroundingTile(line, column, board[line][column])) {
          return;
        }
      }
    }
    dead = true;
  }

  private boolean checkSurroundingTile(int line, int column, Tile current) {
    if (line > 0) {
      Tile check = board[line - 1][column];
      if (check == null){
        return true;
      }
      if (current.getValue() == check.getValue()){
        return true;
      }
    }
    if (line < LINES - 1) {
      Tile check = board[line + 1][column];
      if (check == null){
        return true;
      }
      if (current.getValue() == check.getValue()){
        return true;
      }
    }
    if (column > 0) {
      Tile check = board[line][column - 1];
      if (check == null){
        return true;
      }
      if (current.getValue() == check.getValue()){
        return true;
      }
    }
    if (column < COLUMNS - 1) {
      Tile check = board[line][column + 1];
      if (check == null){
        return true;
      }
      if (current.getValue() == check.getValue()){
        return true;
      }
    }
    return false;
  }

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

  private void start(int level) {
    if(level == 1) {
      spawnRandom(false);
    }
    for (int i = 0; i < startTiles; i++) {
      spawnRandom(true);
    }
  }

  private void spawnRandom(boolean flag) {
    Random random = new Random();
    boolean notValid = true;
    while (notValid) {
      int line = random.nextInt(4);
      int column = random.nextInt(4);

      Tile current = board[line][column];
      if (current == null) {
        int value;
        if(flag){
        value = random.nextInt(10) < 9 ? 2 : 4;
        }
        else{
          value = 0;
        }
        Tile tile = new Tile(value, getTileX(column), getTileY(line));
        board[line][column] = tile;
        notValid = false;
      }
    }
  }

  private int getTileX(int column) {
    return SPACING + column * Tile.SIZE + SPACING * column;
  }

  private int getTileY(int line) {
    return SPACING + line * Tile.SIZE + line * SPACING;
  }

  public boolean getDead(){
    return dead;
  }
  
  public boolean getWon(){
    return won;
  }
}
