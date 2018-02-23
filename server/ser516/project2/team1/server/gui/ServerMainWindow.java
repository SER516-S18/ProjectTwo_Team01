package ser516.project2.team1.server.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.LineBorder;

import util.ToggleButton;

import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Font;

/**
 * The Server Main Window is the complete User Interface of the server. 
 * 
 * @author Chetanya
 * @version 1.0
 * @since 2017-02-22
 *
 */
public class ServerMainWindow extends JFrame {

  private static final Color GREEN = new Color(50, 205, 50);

  private JPanel contentPane;
  private JTextField highestValueTextBox;
  private JTextField lowestValueTextBox;
  private JTextField frequencyValueTextBox;

  public static void main(String[] args) {
    ServerMainWindow frame = new ServerMainWindow();
    frame.setVisible(true);
  }

  /**
   * Create the frame.
   */
  public ServerMainWindow() {
    setBounds(new Rectangle(0, 700, 700, 0));
    setTitle("SERVER");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 500, 500);
    contentPane = new JPanel();
    contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
    contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
    contentPane.setBounds(new Rectangle(0, 650, 650, 0));
    contentPane.setForeground(new Color(50, 205, 50));
    contentPane.setBackground(new Color(135, 206, 250));
    contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    ToggleButton startStopButton = new ToggleButton ();
    startStopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    startStopButton.setBackground(new Color(255, 228, 225));
    startStopButton.setBounds(325, 10, 100, 25);
    contentPane.add(startStopButton);
    
    
    

    contentPane.add(startStopButton);

    JPanel consolePanel = new JPanel();
    consolePanel.setAlignmentY(Component.TOP_ALIGNMENT);
    consolePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    consolePanel.setBackground(new Color(0, 0, 0));
    consolePanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
    consolePanel.setBounds(6, 346, 488, 114);
    contentPane.add(consolePanel);

    JPanel maxMinFrequencyPanel = new JPanel();
    maxMinFrequencyPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    maxMinFrequencyPanel.setAlignmentY(Component.TOP_ALIGNMENT);
    maxMinFrequencyPanel.setBackground(new Color(238, 238, 238));
    maxMinFrequencyPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
    maxMinFrequencyPanel.setBounds(230, 50, 250, 250);
    contentPane.add(maxMinFrequencyPanel);
    maxMinFrequencyPanel.setLayout(null);

    highestValueTextBox = new JTextField();
    highestValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
    highestValueTextBox.setAlignmentY(Component.TOP_ALIGNMENT);
    highestValueTextBox.setAlignmentX(Component.LEFT_ALIGNMENT);
    highestValueTextBox.setText("1024");
    highestValueTextBox.setFont(new Font("Dialog", Font.BOLD, 24));
    highestValueTextBox.setBounds(140, 15, 100, 56);
    maxMinFrequencyPanel.add(highestValueTextBox);
    highestValueTextBox.setBackground(new Color(255, 228, 225));
    highestValueTextBox.setColumns(10);

    lowestValueTextBox = new JTextField();
    lowestValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
    lowestValueTextBox.setAlignmentY(Component.TOP_ALIGNMENT);
    lowestValueTextBox.setAlignmentX(Component.LEFT_ALIGNMENT);
    lowestValueTextBox.setFont(new Font("Dialog", Font.BOLD, 24));
    lowestValueTextBox.setColumns(10);
    lowestValueTextBox.setBackground(new Color(135, 206, 250));
    lowestValueTextBox.setBounds(140, 85, 100, 55);
    maxMinFrequencyPanel.add(lowestValueTextBox);

    frequencyValueTextBox = new JTextField();
    frequencyValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
    frequencyValueTextBox.setAlignmentY(Component.TOP_ALIGNMENT);
    frequencyValueTextBox.setAlignmentX(Component.LEFT_ALIGNMENT);
    frequencyValueTextBox.setFont(new Font("Dialog", Font.BOLD, 24));
    frequencyValueTextBox.setColumns(10);
    frequencyValueTextBox.setBackground(new Color(255, 228, 225));
    frequencyValueTextBox.setBounds(140, 155, 100, 55);
    maxMinFrequencyPanel.add(frequencyValueTextBox);

    JLabel lowestValue = new JLabel("<html>Lowest<br>Value:</html>",JLabel.CENTER);
    lowestValue.setAlignmentY(Component.TOP_ALIGNMENT);
    lowestValue.setFont(new Font("Dialog", Font.BOLD, 16));
    lowestValue.setHorizontalTextPosition(SwingConstants.CENTER);
    lowestValue.setBounds(15, 85, 100, 55);
    maxMinFrequencyPanel.add(lowestValue);

    JLabel highestValue = new JLabel("<html>Highest<br>Value:</html>");
    highestValue.setFont(new Font("Dialog", Font.BOLD, 16));
    highestValue.setAlignmentY(Component.TOP_ALIGNMENT);
    highestValue.setHorizontalTextPosition(SwingConstants.CENTER);
    highestValue.setHorizontalAlignment(SwingConstants.CENTER);
    highestValue.setBounds(15, 15, 100, 55);
    maxMinFrequencyPanel.add(highestValue);
    highestValue.setBackground(Color.LIGHT_GRAY);

    JLabel lblFrequency = new JLabel("<html>Frequency<br>(Hz):</html>", SwingConstants.CENTER);
    lblFrequency.setAlignmentY(Component.TOP_ALIGNMENT);
    lblFrequency.setFont(new Font("Dialog", Font.BOLD, 16));
    lblFrequency.setHorizontalTextPosition(SwingConstants.CENTER);
    lblFrequency.setBounds(15, 155, 100, 55);
    maxMinFrequencyPanel.add(lblFrequency);

    JPanel statusIndicatorPanel = new JPanel();
    statusIndicatorPanel.setBounds(10, 50, 215, 250);
    contentPane.add(statusIndicatorPanel);
    statusIndicatorPanel.setLayout(null);

    JLabel indicatorLabel = new JLabel("•", SwingConstants.CENTER);
    indicatorLabel.setBounds(10, 20, 200, 200);
    indicatorLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    statusIndicatorPanel.add(indicatorLabel);
    indicatorLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    indicatorLabel.setForeground(GREEN);
    indicatorLabel.setFont(indicatorLabel.getFont().deriveFont(250f));
  }
  
  private void controlInputValues() {
    //if ()
  }
}
