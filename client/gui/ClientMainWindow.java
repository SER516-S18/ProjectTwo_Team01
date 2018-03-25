package client.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartPanel;

import client.sys.Client;
import client.sys.NumberStatistics;
import util.ConsolePanel;
import util.Constants;
import util.ToggleButton;

/**
 * The Client Main Window is the complete User Interface of the client.
 * 
 * @author Shilpa Bhat
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 2017-02-23
 *
 **/
public class ClientMainWindow extends JFrame {
  private static JPanel contentPane;
  private Thread clientThread;
  private Client client;
  private JLabel actualLowLabel;
  private JLabel actualHighLabel;
  private JLabel actualAverageLabel;
  private JLabel actualFrequencyLabel;
  private JPanel containerPanel;

  JComboBox<String> numberOfChannelsComboBox;
  static ConsolePanel consolePanel;

  public DisplayGraph displayGraph;

  /**
   * Main method to create client window
   */
  public static void main(String[] args) {
    ClientMainWindow window = new ClientMainWindow();
    window.setVisible(true);
  }

  /**
   * Function to set the layout for main client frame
   */
  public ClientMainWindow() {
    contentPane = new JPanel();
    setTitle("Client");
    setBounds(100, 100, 1000, 1000);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setContentPane(contentPane);
    contentPane.setLayout(null);
    contentPane.setBackground(Constants.LIGHTBLUE);

    ToggleButton btnStartStop = new ToggleButton(this);
    btnStartStop.setBounds(850, 30, 100, 25);

    contentPane.add(btnStartStop);
    addGraphPanel();
  }

  /**
   * Function to add the different components to the main frame
   */
  private void addGraphPanel() {
    containerPanel = new JPanel();
    containerPanel.setAlignmentY(Component.TOP_ALIGNMENT);
    containerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    containerPanel.setBorder(new LineBorder(Constants.BLACK));
    containerPanel.setBackground(Constants.LIGHTBLUE);
    containerPanel.setBounds(24, 67, 946, 637);
    contentPane.add(containerPanel);
    containerPanel.setLayout(null);

    JPanel graphPanel = new JPanel();
    graphPanel.setBorder(new LineBorder(Constants.BLACK));
    graphPanel.setBounds(12, 13, 573, 619);
    containerPanel.add(graphPanel);
    containerPanel.setOpaque(false);

    displayGraph = new DisplayGraph();
    displayGraph.chartPanel = new ChartPanel(displayGraph.displayGraph);
    displayGraph.chartPanel.setLocation(12, 26);
    displayGraph.chartPanel.setSize(new Dimension(478, 567));
    graphPanel.setLayout(null);
    ChartPanel chartPanel = new ChartPanel(displayGraph.displayGraph);
    chartPanel.setBorder(new LineBorder(Constants.BLACK));
    chartPanel.setLocation(0, 0);
    chartPanel.setSize(new Dimension(573, 619));
    chartPanel.setBackground(Constants.LIGHTBLUE);

    graphPanel.add(displayGraph.chartPanel);

    JPanel highestPanel = new JPanel();
    highestPanel.setBorder(new LineBorder(Constants.BLACK));
    highestPanel.setBounds(623, 57, 100, 55);
    containerPanel.add(highestPanel);
    highestPanel.setLayout(null);

    JLabel highestLbl = new JLabel("<html>Highest<br>Value:</html>");
    highestLbl.setFont(new Font("Dialog", Font.BOLD, 14));
    highestLbl.setHorizontalTextPosition(SwingConstants.CENTER);
    highestLbl.setBorder(new LineBorder(Constants.BLACK));
    highestLbl.setBounds(0, 0, 100, 55);
    highestLbl.setAlignmentY(Component.TOP_ALIGNMENT);
    highestLbl.setHorizontalAlignment(SwingConstants.CENTER);
    highestPanel.add(highestLbl);
    highestLbl.setOpaque(true);
    highestLbl.setBackground(Constants.LIGHTBLUE);

    JPanel highestValuePanel = new JPanel();
    highestValuePanel.setBorder(new LineBorder(Constants.BLACK));
    highestValuePanel.setBounds(798, 57, 100, 55);
    highestValuePanel.setBackground(Constants.PINK);
    containerPanel.add(highestValuePanel);
    highestValuePanel.setLayout(null);

    actualHighLabel = new JLabel("");
    actualHighLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    actualHighLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    actualHighLabel.setHorizontalAlignment(SwingConstants.CENTER);
    actualHighLabel.setFont(new Font("Lucida Grande", Font.BOLD, 24));
    actualHighLabel.setBounds(0, 0, 100, 55);
    highestValuePanel.add(actualHighLabel);

    JPanel lowestPanel = new JPanel();
    lowestPanel.setBorder(new LineBorder(Constants.BLACK));
    lowestPanel.setBounds(623, 160, 100, 55);
    containerPanel.add(lowestPanel);
    lowestPanel.setLayout(null);

    JLabel lowestLbl = new JLabel("<html>Lowest<br>Value:</html>");
    lowestLbl.setFont(new Font("Dialog", Font.BOLD, 14));
    lowestLbl.setHorizontalTextPosition(SwingConstants.CENTER);
    lowestLbl.setBorder(new LineBorder(Constants.BLACK));
    lowestLbl.setAlignmentY(Component.TOP_ALIGNMENT);
    lowestLbl.setBounds(0, 0, 100, 55);
    lowestLbl.setHorizontalAlignment(SwingConstants.CENTER);
    lowestPanel.add(lowestLbl);
    lowestLbl.setOpaque(true);
    lowestLbl.setBackground(Constants.PINK);

    JPanel lowestValuePanel = new JPanel();
    lowestValuePanel.setBorder(new LineBorder(Constants.BLACK));
    lowestValuePanel.setBounds(798, 160, 100, 55);
    containerPanel.add(lowestValuePanel);
    lowestValuePanel.setLayout(null);
    lowestValuePanel.setBackground(Constants.LIGHTBLUE);

    actualLowLabel = new JLabel("");
    actualLowLabel.setFont(new Font("Lucida Grande", Font.BOLD, 24));
    actualLowLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    actualLowLabel.setHorizontalAlignment(SwingConstants.CENTER);
    actualLowLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    actualLowLabel.setBounds(0, 0, 100, 55);
    lowestValuePanel.add(actualLowLabel);

    JPanel averagePanel = new JPanel();
    averagePanel.setAlignmentY(Component.TOP_ALIGNMENT);
    averagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    averagePanel.setBorder(new LineBorder(Constants.BLACK));
    averagePanel.setBounds(623, 268, 100, 55);
    containerPanel.add(averagePanel);
    averagePanel.setLayout(null);
    averagePanel.setBackground(Constants.PINK);

    JLabel averageLbl = new JLabel("Average:");
    averageLbl.setFont(new Font("Dialog", Font.BOLD, 14));
    averageLbl.setHorizontalTextPosition(SwingConstants.CENTER);
    averageLbl.setBorder(new LineBorder(Constants.BLACK));
    averageLbl.setAlignmentY(Component.TOP_ALIGNMENT);
    averageLbl.setBounds(0, 0, 100, 55);
    averageLbl.setHorizontalAlignment(SwingConstants.CENTER);
    averagePanel.add(averageLbl);
    averageLbl.setOpaque(true);
    averageLbl.setBackground(Constants.LIGHTBLUE);

    JPanel averageValuePanel = new JPanel();
    averageValuePanel.setBorder(new LineBorder(Constants.BLACK));
    averageValuePanel.setBounds(798, 268, 100, 55);
    containerPanel.add(averageValuePanel);
    averageValuePanel.setLayout(null);
    averageValuePanel.setBackground(Constants.PINK);

    actualAverageLabel = new JLabel("");
    actualAverageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    actualAverageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    actualAverageLabel.setFont(new Font("Dialog", Font.BOLD, 24));
    actualAverageLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    actualAverageLabel.setBounds(0, 0, 100, 55);
    averageValuePanel.add(actualAverageLabel);

    JPanel numOfChannelsPanel = new JPanel();
    numOfChannelsPanel.setBorder(new LineBorder(Constants.BLACK));
    numOfChannelsPanel.setBounds(623, 361, 100, 55);
    containerPanel.add(numOfChannelsPanel);
    numOfChannelsPanel.setLayout(null);

    JLabel numOfChannelsLbl = new JLabel("Channels:");
    numOfChannelsLbl.setFont(new Font("Dialog", Font.BOLD, 14));
    numOfChannelsLbl.setHorizontalTextPosition(SwingConstants.CENTER);
    numOfChannelsLbl.setAlignmentY(Component.TOP_ALIGNMENT);
    numOfChannelsLbl.setBorder(new LineBorder(Constants.BLACK));
    numOfChannelsLbl.setBounds(0, 0, 100, 55);
    numOfChannelsLbl.setHorizontalAlignment(SwingConstants.CENTER);
    numOfChannelsPanel.add(numOfChannelsLbl);
    numOfChannelsLbl.setOpaque(true);
    numOfChannelsLbl.setBackground(Constants.PINK);

    String[] values = { "1", "2", "3", "4" };
    numberOfChannelsComboBox = new JComboBox<String>(values);
    numberOfChannelsComboBox.setAlignmentY(Component.TOP_ALIGNMENT);
    numberOfChannelsComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
    numberOfChannelsComboBox.setFont(new Font("Dialog", Font.BOLD, 24));
    numberOfChannelsComboBox.setBounds(798, 361, 100, 35);
    numberOfChannelsComboBox.setBackground(Constants.LIGHTBLUE);
    containerPanel.add(numberOfChannelsComboBox);

    JPanel frequencyPanel = new JPanel();
    frequencyPanel.setBorder(new LineBorder(Constants.BLACK));
    frequencyPanel.setBounds(623, 460, 100, 55);
    containerPanel.add(frequencyPanel);
    frequencyPanel.setLayout(null);

    JLabel frequencyLbl = new JLabel("Frequency:");
    frequencyLbl.setFont(new Font("Dialog", Font.BOLD, 14));
    frequencyLbl.setHorizontalTextPosition(SwingConstants.CENTER);
    frequencyLbl.setBorder(new LineBorder(Constants.BLACK));
    frequencyLbl.setBounds(0, 0, 100, 55);
    frequencyLbl.setAlignmentY(Component.TOP_ALIGNMENT);
    frequencyLbl.setHorizontalAlignment(SwingConstants.CENTER);
    frequencyPanel.add(frequencyLbl);
    frequencyLbl.setOpaque(true);
    frequencyLbl.setBackground(Constants.LIGHTBLUE);

    JPanel frequencyValuePanel = new JPanel();
    frequencyValuePanel.setBorder(new LineBorder(Constants.BLACK));
    frequencyValuePanel.setBounds(798, 460, 100, 55);
    containerPanel.add(frequencyValuePanel);
    frequencyValuePanel.setLayout(null);
    frequencyValuePanel.setBackground(Constants.PINK);

    actualFrequencyLabel = new JLabel("");
    actualFrequencyLabel.setFont(new Font("Dialog", Font.BOLD, 24));
    actualFrequencyLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    actualFrequencyLabel.setHorizontalAlignment(SwingConstants.CENTER);
    actualFrequencyLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    actualFrequencyLabel.setBounds(0, 0, 100, 55);
    frequencyValuePanel.add(actualFrequencyLabel);

    consolePanel = new ConsolePanel();
    consolePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    consolePanel.setAlignmentY(Component.TOP_ALIGNMENT);
    consolePanel.setBounds(15, 743, 958, 200);
    contentPane.add(consolePanel);
    consolePanel.setBorder(new LineBorder(Constants.BLACK));
    consolePanel.setBackground(Constants.GRAY);
  }

  /**
   * 
   * Display the message on the console panel
   */
  public static void appendToConsole(String input) {
    ConsolePanel.updateText(input);
  }

  public void controlStartStopAction(boolean isStarted) {
    if (isStarted) {
      client = new Client(Integer.parseInt((String) (numberOfChannelsComboBox.getSelectedItem())), this);
      clientThread = new Thread(client);
      clientThread.start();
    } else {
      try {
        client.closeConnection();
        clientThread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /* Function to set the frequency from the server */
  public void setFrequency(int frequency) {
    this.actualFrequencyLabel.setText(frequency + "");
    this.containerPanel.repaint();
  }

  public void refreshWindow() {
    this.actualHighLabel.setText(NumberStatistics.getHighestValue() + "");
    this.actualLowLabel.setText(NumberStatistics.getLowestValue() + "");
    this.actualAverageLabel.setText(NumberStatistics.getAverageValue() + "");
    this.containerPanel.repaint();
  }
}
