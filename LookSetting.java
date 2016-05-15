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

  public final int WIDTH = 320;
  public final int HEIGHT = 320;

  private final int OK_X = 110;
  private final int OK_Y = 200;
  private final int OK_WIDTH = 100;
  private final int OK_HEIGHT = 30;

  private final int METAL_X = 90;
  private final int METAL_Y = 50;

  private final int SYSTEM_X = 90;
  private final int SYSTEM_Y = 85;

  private final int METAL_B_X = 60;
  private final int METAL_B_Y = 60;
  private final int METAL_B_WIDTH = 20;
  private final int METAL_B_HEIGHT = 20;

  private final int SYSTEM_B_X = 60;
  private final int SYSTEM_B_Y = 95;
  private final int SYSTEM_B_WIDTH = 20;
  private final int SYSTEM_B_HEIGHT = 20;

  /**
   * panel choosing look style
   */
  public LookSetting() {
    this.setTitle("Setting");
    setSize(WIDTH, HEIGHT);
    setLayout(null);
    setBackground(Color.WHITE);
    setLocationRelativeTo(null);

    okButton = new JButton("Ok");
    okButton.setBounds(OK_X, OK_Y, OK_WIDTH, OK_HEIGHT);
    add(okButton);

    add(new TextLabel("Metal LookAndFeel", METAL_X, METAL_Y));
    metalRadioButton = new JRadioButton("Metal");
    metalRadioButton.setBounds(METAL_B_X, METAL_B_Y, METAL_B_WIDTH, METAL_B_HEIGHT);
    metalRadioButton.setSelected(true);
    add(metalRadioButton);

    add(new TextLabel("System LookAndFeel", SYSTEM_X, SYSTEM_Y));
    systemRadioButton = new JRadioButton("System");
    systemRadioButton.setBounds(SYSTEM_B_X, SYSTEM_B_Y, SYSTEM_B_WIDTH, SYSTEM_B_HEIGHT);
    add(systemRadioButton);

    setEvent();
  }

  /**
   * set Event for button
   */
  private void setEvent() {

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
