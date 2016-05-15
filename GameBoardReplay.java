package by.bsuir.igor;

import java.awt.image.BufferedImage;

public class GameBoardReplay extends GameBoard {

  String[] save;
  public static int amount;
  private int count;
  private int prev = 0;

  /**
   * 
   * @param path the name of save
   */
  public GameBoardReplay(String path) {
    super();
    board = new Tile[LINES][COLUMNS];
    gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
    finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
    createBoardImage();
    save = Files.readSave(path).split(";");
    count = 1;
    while (save[count].charAt(0) == '0') {
      spawn(Integer.valueOf(save[count].charAt(1) + ""),
          Integer.valueOf(save[count].charAt(2) + ""),
          Integer.valueOf(save[count].charAt(3) + ""));

      count++;
      if (count == amount) {
        break;
      }
    }
  }

  /**
   * created tile
   * 
   * @param value tile
   * @param line, where tail is located
   * @param column
   */
  private void spawn(int value, int line, int column) {
    board[line][column] = new Tile(value, getTileX(column), getTileY(line));
  }

  /**
   * function update board and check whether the replay has end
   */
  void update() {
    if (count >= save.length) {
      won = true;
      dead = true;
      return;
    }
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
   * get a pressed keys
   */
  private void checkKeys() {

    if (count == prev) {
      return;
    }
    switch (save[count].charAt(0)) {
      case '1':
        movingTiles(Direction.LEFT);
        break;
      case '2':
        movingTiles(Direction.RIGHT);
        break;
      case '3':
        movingTiles(Direction.UP);
        break;
      case '4':
        movingTiles(Direction.DOWN);
        break;
    }
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
      spawn(Integer.valueOf(save[count].charAt(1) + ""),
          Integer.valueOf(save[count].charAt(2) + ""), Integer.valueOf(save[count].charAt(3) + ""));
      prev = count;
      checkDead();
    }
  }

  /**
   * go to the next step
   */
  public void incCount() {
    count++;
  }
}


