package by.bsuir.igor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

  private static final long serialVersionUID = 6075827594387738441L;
  private JButton resumeGameButton;
  private JButton newGameButton;
  private JButton sittingButton;
  private JButton exitButton;

  private final int WIDTH = 320;
  private final int HEIGHT = 320;

  private final int IMAGE_X = 0;
  private final int IMAGE_Y = 0;
  private final int IMAGE_WIDTH = 320;
  private final int IMAGE_HEIGHT = 320;

  private final int RESUME_B_X = 80;
  private final int RESUME_B_Y = 120;
  private final int RESUME_B_WIDTH = 160;
  private final int RESUME_B_HEIGHT = 30;

  private final int NEW_GMAE_B_X = 80;
  private final int NEW_GMAE_B_Y = 160;
  private final int NEW_GMAE_B_WIDTH = 160;
  private final int NEW_GMAE_B_HEIGHT = 30;

  private final int SETTING_B_X = 80;
  private final int SETTING_B_Y = 200;
  private final int SETTING_B_WIDTH = 160;
  private final int SETTING_B_HEIGHT = 30;

  private final int EXIT_B_X = 80;
  private final int EXIT_B_Y = 240;
  private final int EXIT_B_WIDTH = 160;
  private final int EXIT_B_HEIGHT = 30;


  public Menu() {
    setLayout(null);
    JLabel imageLabel = new JLabel(new ImageIcon("pr_source.png"));
    imageLabel.setBounds(IMAGE_X, IMAGE_Y, IMAGE_WIDTH, IMAGE_HEIGHT);
    setPreferredSize(new Dimension(WIDTH, HEIGHT));

    resumeGameButton = new JButton("Resume Game");
    resumeGameButton.setBounds(RESUME_B_X, RESUME_B_Y, RESUME_B_WIDTH, RESUME_B_HEIGHT);
    add(resumeGameButton);


    newGameButton = new JButton("New Game");
    newGameButton.setBounds(NEW_GMAE_B_X, NEW_GMAE_B_Y, NEW_GMAE_B_WIDTH, NEW_GMAE_B_HEIGHT);
    add(newGameButton);

    sittingButton = new JButton("Setting");
    sittingButton.setBounds(SETTING_B_X, SETTING_B_Y, SETTING_B_WIDTH, SETTING_B_HEIGHT);
    add(sittingButton);

    exitButton = new JButton("Exit");
    exitButton.setBounds(EXIT_B_X, EXIT_B_Y, EXIT_B_WIDTH, EXIT_B_HEIGHT);
    add(exitButton);
    setAction();

    add(imageLabel);

  }

  private void setAction() {

    resumeGameButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Main.resumeGame();
      }
    });
    newGameButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        Main.setLevel();
      }
    });
    sittingButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Main.openSetting();
      }
    });
    exitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Main.exitGame();
      }
    });

  }
}
