package by.bsuir.igor;

import java.awt.Font;

import javax.swing.JLabel;

public class TextLabel extends JLabel{
  
  private static final long serialVersionUID = 1L;

  public TextLabel(String text, int x, int y){    
    setText(text);
    this.setBounds(x, y, 200, 40);
    setFont(new Font("FONT_2", Font.PLAIN, 18));
  }

}
