package ser516.project2.team1.server.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class MainWindow extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 5435926730917296851L;
  private JPanel contentPane;
  private JTextField highestInput;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          MainWindow frame = new MainWindow();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Creates the frame and add functionalities like Highest Values and different Panels for them.
   */
  public MainWindow() {
    setTitle("SERVER");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setForeground(new Color(50, 205, 50));
    contentPane.setBackground(new Color(135, 206, 250));
    contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JToggleButton startStopButton = new JToggleButton("Start/Stop");
    startStopButton.setBackground(new Color(255, 228, 225));
    startStopButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
      }
    });
    startStopButton.setBounds(283, 6, 161, 29);
    contentPane.add(startStopButton);

    highestInput = new JTextField();
    highestInput.setBackground(new Color(255, 228, 225));
    highestInput.setBounds(314, 53, 130, 26);
    contentPane.add(highestInput);
    highestInput.setColumns(10);

    JLabel console = new JLabel("Console:");
    console.setBounds(16, 184, 64, 26);
    contentPane.add(console);

    JPanel highPanel = new JPanel();
    highPanel.setBackground(new Color(135, 206, 250));
    highPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
    highPanel.setBounds(209, 53, 98, 26);
    contentPane.add(highPanel);

    JLabel highestValue = new JLabel("Highest Value:");
    highPanel.add(highestValue);
    highestValue.setBackground(Color.LIGHT_GRAY);

    JPanel statusPanel = new JPanel();
    statusPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
    statusPanel.setBounds(16, 42, 179, 143);
    contentPane.add(statusPanel);
    statusPanel.setLayout(null);

    JLabel statusIndicator = new JLabel(" *");
    statusIndicator.setForeground(new Color(50, 205, 50));
    statusIndicator.setFont(statusIndicator.getFont().deriveFont(250.0f));

    statusIndicator.setBounds(-54, 16, 233, 184);
    statusPanel.add(statusIndicator);

    JPanel middleDivision = new JPanel();
    middleDivision.setBackground(new Color(238, 238, 238));
    middleDivision.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
    middleDivision.setBounds(6, 32, 438, 178);
    contentPane.add(middleDivision);



  }
}
