package by.bsuir.igor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class Level extends JFrame {
  private static final long serialVersionUID = -6081335804310794680L;

  private JRadioButton easyRadioButton;
  private JRadioButton hardRadioButton;
  private JButton okButton;
  private int level;

  public final int WIDTH = 320;
  public final int HEIGHT = 320;

  private final int OK_X = 110;
  private final int OK_Y = 200;
  private final int OK_WIDTH = 100;
  private final int OK_HEIGHT = 30;

  private final int EASY_X = 90;
  private final int EASY_Y = 50;

  private final int HARD_X = 90;
  private final int HARD_Y = 85;

  private final int EASY_B_X = 60;
  private final int EASY_B_Y = 60;
  private final int EASY_B_WIDTH = 20;
  private final int EASY_B_HEIGHT = 20;

  private final int hardButtonX = 60;
  private final int hardButtonY = 95;
  private final int hardButtonWight = 20;
  private final int hardButtonHeight = 20;

  public Level() {
    setLocationRelativeTo(null);
    setTitle("Level");
    setSize(WIDTH, HEIGHT);
    setLayout(null);
    setBackground(Color.WHITE);

    okButton = new JButton("Ok");
    okButton.setBounds(OK_X, OK_Y, OK_WIDTH, OK_HEIGHT);
    add(okButton);

    add(new TextLabel("Easy", EASY_X, EASY_Y));
    easyRadioButton = new JRadioButton("easy level");
    easyRadioButton.setBounds(EASY_B_X, EASY_B_Y, EASY_B_WIDTH, EASY_B_HEIGHT);
    easyRadioButton.setSelected(true);
    add(easyRadioButton);

    add(new TextLabel("Hard", HARD_X, HARD_Y));
    hardRadioButton = new JRadioButton("hard level");
    hardRadioButton.setBounds(hardButtonX, hardButtonY, hardButtonWight, hardButtonHeight);
    add(hardRadioButton);

    setEvent();
  }

  private void setEvent() {

    okButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        Main.runGame();
      }
    });

    easyRadioButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        easyRadioButton.setSelected(true);
        hardRadioButton.setSelected(false);
        level = 0;

      }
    });

    hardRadioButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        hardRadioButton.setSelected(true);
        easyRadioButton.setSelected(false);
        level = 1;
      }
    });
  }

  public int getLevel() {
    return level;
  }

}
