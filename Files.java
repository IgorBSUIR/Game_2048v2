package by.bsuir.igor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Files {
  private final String SCORE = "SCORE";
  private String nameFile;

  public Files() {
    StringBuilder string = new StringBuilder();
    string.append(new SimpleDateFormat().format(Calendar.getInstance().getTime()));

    nameFile = "Save" + File.separator + string.toString().replaceAll("\\:|\\.|\\ |", "") + ".save";
    File file = new File("Save");

    if (!file.exists()) {
      file.mkdirs();
    }
  }

  public static String[] readSave(String path) {
    File file = new File("Save" + File.separator + path);
    StringBuilder string = new StringBuilder();
    String save;
    int amount = 0;
    if (!file.exists()) {
      return null;
    }
    try {
      BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
      try {
        while ((save = in.readLine()) != null) {
          amount++;
          string.append(save);
        }
      } finally {
        in.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    GameBoardReplay.amount = amount;
    return string.toString().split(";");
  }

  public static void writeScore(int score) {
    File file = new File(SCORE);
    try {
      if (file.exists()) {
        file.delete();
      }
      file.createNewFile();

      PrintWriter out = new PrintWriter(file.getAbsoluteFile());
      try {
        out.print(Integer.toString(score));
      } finally {
        out.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static int readScore() {
    String score;
    File file = new File(SCORE);
    if (!file.exists()) {
      return 0;
    }
    try {
      BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
      try {
        score = in.readLine();
      } finally {
        in.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return Integer.valueOf(score);
  }

  public void writeSave(Direction dir, int value, int line, int column) {
    File file = new File(nameFile);
    try {
      if (!file.exists()) {
        file.createNewFile();
      }
      PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
      try {
        out.println(convertToString(dir, value, line, column));
      } finally {
        out.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

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

    return string.toString();
  }
}
