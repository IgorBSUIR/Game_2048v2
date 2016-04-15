package by.bsuir.igor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class LookSetting extends JFrame {

  private static final long serialVersionUID = -5646130581691880031L;
  private JRadioButton metalRadioButton;
  private JRadioButton systemRadioButton;
  private JButton okButton;
  
  
  public LookSetting() {
    this.setTitle("Sitting");
    setSize(320, 320);
    setLayout(null);
    setBackground(Color.WHITE);
    setLocationRelativeTo(null);
    okButton = new JButton("Ok");
    okButton.setBounds(110, 200, 100, 30);
    add(okButton);

    add(new TextLabel("Metal LookAndFeel", 90, 50));
    metalRadioButton = new JRadioButton("Metal");
    metalRadioButton.setBounds(60, 60, 20, 20);
    metalRadioButton.setSelected(true);
    add(metalRadioButton);

    add(new TextLabel("System LookAndFeel", 90, 85));
    systemRadioButton = new JRadioButton("System");
    systemRadioButton.setBounds(60, 95, 20, 20);
    add(systemRadioButton);

    setEvent();
  }
  
  private void setEvent(){
    
    okButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Main.closeSetting();
      }
    });
    
  metalRadioButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      metalRadioButton.setSelected(true);
      systemRadioButton.setSelected(false);
      Main.initMetalLook();
    }
  });

  systemRadioButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      systemRadioButton.setSelected(true);
      metalRadioButton.setSelected(false);
      Main.initSystemLook();
    }
  });
  }
}
