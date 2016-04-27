package by.bsuir.igor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FinalWindow extends JFrame {

  private JButton okButton;
  private JLabel text;

  private final int WIDTH = 320;
  private final int HEIGHT = 240;

  private int text_X = 100;
  private final int TEXT_Y = 30;
  private final int TEXT_WIDTH = 280;
  private final int TEXT_HEIGHT = 30;

  private final int OK_X = 120;
  private final int OK_Y = 100;
  private final int OK_WIDTH = 80;
  private final int OK_HEIGHT = 30;

  private static final long serialVersionUID = -8801986310339285519L;

  public FinalWindow(String result) {
    setSize(WIDTH, HEIGHT);
    setLayout(null);
    setTitle("End Game");
    setLocationRelativeTo(null);

    text = new JLabel();

    text.setFont(new Font("FONT", Font.PLAIN, 32));
    switch (result) {

      case "won":
        text.setForeground(Color.GREEN);
        text.setText("You won!");
        break;

      case "dead":
        text.setForeground(Color.RED);
        text.setText("You lose!");
        break;

      case "end":
        text_X -= 20;
        text.setForeground(Color.BLACK);
        text.setText("Replay end");
        break;
    }
    text.setBounds(text_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT);
    add(text);

    okButton = new JButton("Ok");
    okButton.setBounds(OK_X, OK_Y, OK_WIDTH, OK_HEIGHT);
    add(okButton);
    setEvent(result);

    setVisible(true);
  }

  private void setEvent(String result) {
    if (result.equals("end")) {
      okButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          setVisible(false);
        }
      });
    } else {
      okButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          setVisible(false);
          Main.exitToMenu();
        }
      });
    }
  }
}
