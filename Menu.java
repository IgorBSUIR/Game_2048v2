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

  public Menu() {
    setLayout(null);
    JLabel imageLabel = new JLabel(new ImageIcon("pr_source.png"));
    imageLabel.setBounds(0, 0, 320, 320);
    setPreferredSize(new Dimension(320, 320));

    resumeGameButton = new JButton("Resume Game");
    resumeGameButton.setBounds(80, 120, 160, 30);
    add(resumeGameButton);
   

    newGameButton = new JButton("New Game");
    newGameButton.setBounds(80, 160, 160, 30);
    add(newGameButton);
   

    sittingButton = new JButton("Setting");
    sittingButton.setBounds(80, 200, 160, 30);
    add(sittingButton);

    exitButton = new JButton("Exit");
    exitButton.setBounds(80, 240, 160, 30);
    add(exitButton);
    setAction();
    
    add(imageLabel);
    
  }
  private void setAction(){
    
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
