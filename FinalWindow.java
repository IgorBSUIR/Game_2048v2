package by.bsuir.igor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FinalWindow extends JFrame {

  JButton okButton;
  JLabel text;
  
  private static final long serialVersionUID = -8801986310339285519L;

  public FinalWindow(String result) {
    setSize(320, 240);
    setLayout(null);
    this.setTitle("End Game");
    setLocationRelativeTo(null);
    text  = new JLabel();
    text.setBounds(70, 30, 280, 60);
    text.setFont(new Font("FONT", Font.PLAIN, 32));
    if(result.equals("won")){
      text.setForeground(Color.GREEN);
      text.setText("You won!");
    }
    else{
      text.setForeground(Color.RED);
      text.setText("You lose!");
    }
    add(text);
    
    okButton = new JButton("Ok");
    okButton.setBounds(100, 120, 80, 30);
    add(okButton);
    setEvent();
    setVisible(true);
    
  }
  
private void setEvent(){
    
    okButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        Main.exitToMenu();
      }
    });
}
}
