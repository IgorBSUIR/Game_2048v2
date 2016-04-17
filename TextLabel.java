package by.bsuir.igor;

import java.awt.Font;

import javax.swing.JLabel;

public class TextLabel extends JLabel {

  private static final long serialVersionUID = 1L;

  private final int HEIGHT = 40;
  private final int WIDTH = 200;

  public TextLabel(String text, int x, int y) {
    setText(text);
    setBounds(x, y, WIDTH, HEIGHT);
    setFont(new Font("FONT_2", Font.PLAIN, 18));
  }

}
