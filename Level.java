package by.bsuir.igor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class Level extends JFrame{
  private static final long serialVersionUID = -6081335804310794680L;
  
  private JRadioButton easyRadioButton;
  private JRadioButton hardRadioButton;
  private JButton okButton;
  private int level;        //true - easy, false - hard;
  
  public Level(){
    setLocationRelativeTo(null);
    setTitle("Level");
    setSize(320, 320);
    setLayout(null);
    setBackground(Color.WHITE);

    okButton = new JButton("Ok");
    okButton.setBounds(110, 200, 100, 30);
    add(okButton);

    add(new TextLabel("Easy", 90, 50));
    easyRadioButton = new JRadioButton("easy level");
    easyRadioButton.setBounds(60, 60, 20, 20);
    easyRadioButton.setSelected(true);
    add(easyRadioButton);

    add(new TextLabel("Hard", 90, 85));
    hardRadioButton = new JRadioButton("hard level");
    hardRadioButton.setBounds(60, 95, 20, 20);
    add(hardRadioButton);
    
    setEvent();
  }

 private void setEvent(){
    
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
