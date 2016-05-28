package by.bsuir.igor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Files {
  /**
   * this class for work with files
   */
  private static final String SCORE = "SCORE";
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

  /**
   * read save from file
   * 
   * @param name save
   * @return
   */
  public static GameInfo readSave(String name) {
    File file = new File("Save" + File.separator + name);
    GameInfo gameInfo = new GameInfo();
    gameInfo.setName(name);
    if (!file.exists()) {
      return null;
    }
    try {
      BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
      try {
        gameInfo.setScore(Integer.valueOf(in.readLine().replaceAll(";", "")));
        gameInfo.setSteps(in.readLine());
      } finally {
        in.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return gameInfo;
  }

  /**
   * write hightscore in file
   * 
   * @param score
   */
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

  /**
   * read hightscore from file
   * 
   * @return hightscore
   */
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

  /**
   * write step in file
   * 
   * @param dir key
   * @param value value new tile
   * @param line line new tile
   * @param column column new tile
   */
  public void writeSave(String save, int score) {

    File file = new File(nameFile);
    try {
      if (file.exists()) {
        file.delete();
      }
      PrintWriter out = new PrintWriter(file.getAbsoluteFile());
      try {
        out.println(Integer.toString(score) + ";");
        out.print(save);
      } finally {
        out.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * get information about all games from files
   * 
   * @return information about games
   */
  public static GameInfo[] getInfoFiles() {

    File directory = new File("Save");
    File[] files = directory.listFiles();

    GameInfo[] gamesInfo = new GameInfo[files.length];
    for (int i = 0; i < files.length; i++) {
      gamesInfo[i] = new GameInfo();
      gamesInfo[i].setName(files[i].getName());

      File file = new File("Save" + File.separator + gamesInfo[i].getName());
      if (!file.exists()) {
        return null;
      }
      try {
        BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        try {
          gamesInfo[i].setScore(Integer.valueOf(in.readLine().replaceAll(";", "")));
          gamesInfo[i].setSteps(in.readLine());
        } finally {
          in.close();
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    return gamesInfo;
  }
}
