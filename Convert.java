package by.bsuir.igor;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class Convert extends JFrame {
  /**
   * class for display annotation
   */
  private final int WIDTH = 320;
  private final int HEIGHT = 240;

  private final int SCROLL_X = 0;
  private final int SCROLL_Y = 0;
  private final int SCROLL_WIDTH = 305;
  private final int SCROLL_HEIGHT = 201;

  private String path;
  private String result;
  private GameInfo gameInfo;

  public Convert() {
    setSize(WIDTH, HEIGHT);
    setLayout(null);
    setTitle("End Game");

    getGameInfo();
    result = (new AnnotationConvert()).convert(gameInfo);

    JTextArea textArea = new JTextArea(10, 20);
    textArea.setText(result);
    JScrollPane scroll = new JScrollPane(textArea);
    scroll.setBounds(SCROLL_X, SCROLL_Y, SCROLL_WIDTH, SCROLL_HEIGHT);
    this.add(scroll);
    this.add(scroll);


    setVisible(true);

  }

  /**
   * get GameInfo from fileSave
   */
  private void getGameInfo() {
    JFileChooser dialog = new JFileChooser(new File("SAVE"));
    dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
    dialog.setApproveButtonText("Open");
    dialog.setDialogTitle("Open save");
    dialog.setDialogType(JFileChooser.OPEN_DIALOG);
    dialog.setMultiSelectionEnabled(false);

    if (dialog.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      path = dialog.getSelectedFile().getName();
    } else {
      return;
    }
    gameInfo = Files.readSave(path);
  }

}
