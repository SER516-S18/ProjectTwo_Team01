package ser516.project2.team1.server.gui;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.LineBorder;

import ser516.project2.team1.server.sys.Server;
import util.ConsolePanel;
import util.ToggleButton;
import util.Constants;

import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;

/**
 * The Server Main Window is the complete User Interface of the server. 
 * 
 * @author Chetanya Ahuja
 * @author Debarati Bhattacharyya
 * @version 1.0
 * @since 2018-02-23
 *
 */
public class ServerMainWindow extends JFrame {

	/**
	 * panel shows all information.
	 * panel shows highest value.
	 * panel shows lowest value.
	 * panel shows frequency.
	 * status of server.
	 * 
	 */
	private static JPanel contentPane;
	private static JTextField highestValueTextBox;
	private static JTextField lowestValueTextBox;
	private static JTextField frequencyValueTextBox;
	private static ToggleButton startStopButton;
	private boolean isStarted;
	private static int min, max, frequency;
	private Server server;
	private JLabel indicatorLabel;
	private ConsolePanel consolePanel;

	public static void main(String[] args) {
		ServerMainWindow frame = new ServerMainWindow();
		frame.setVisible(true);
	}

	/**
	 * Creates the main window frame. It displays the highest value, lowest value 
	 * frequency and the console.
	 * 
	 */
	public ServerMainWindow() {
		min = 50;
		max = 2500;
		frequency = 5;
		isStarted = false;
		setBounds(new Rectangle(0, 700, 700, 0));
		setTitle("SERVER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);

		contentPane = new JPanel();
		contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.setBounds(new Rectangle(0, 650, 650, 0));
		contentPane.setForeground(Constants.GREEN);
		contentPane.setBackground(Constants.LIGHTBLUE);
		contentPane.setBorder(new LineBorder(Constants.BLACK, 1, true));
		setContentPane(contentPane);	
		contentPane.setLayout(null);

		startStopButton = new ToggleButton (this);
		startStopButton.setBounds(380, 12, 100, 25);
		contentPane.add(startStopButton);

		JPanel maxMinFrequencyPanel = new JPanel();
		maxMinFrequencyPanel.setBackground(Constants.LIGHTBLUE);
		maxMinFrequencyPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		maxMinFrequencyPanel.setBounds(230, 50, 250, 250);
		contentPane.add(maxMinFrequencyPanel);
		maxMinFrequencyPanel.setLayout(null);

		highestValueTextBox = new JTextField();
		highestValueTextBox.setEditable(false);
		highestValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
		highestValueTextBox.setFont(new Font("Dialog", Font.BOLD, 24));
		highestValueTextBox.setBounds(140, 15, 100, 55);
		highestValueTextBox.setBackground(Constants.PINK);
		highestValueTextBox.setColumns(10);
		highestValueTextBox.setEditable(!isStarted);
		highestValueTextBox.setText(max + "");
		maxMinFrequencyPanel.add(highestValueTextBox);

		lowestValueTextBox = new JTextField();
		lowestValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
		lowestValueTextBox.setFont(new Font("Dialog", Font.BOLD, 24));
		lowestValueTextBox.setColumns(10);
		lowestValueTextBox.setBackground(Constants.LIGHTBLUE);
		lowestValueTextBox.setBounds(140, 85, 100, 55);
		lowestValueTextBox.setEditable(!isStarted);
		lowestValueTextBox.setText(min + "");
		maxMinFrequencyPanel.add(lowestValueTextBox);

		frequencyValueTextBox = new JTextField();
		frequencyValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
		frequencyValueTextBox.setAlignmentY(Component.TOP_ALIGNMENT);
		frequencyValueTextBox.setFont(new Font("Dialog", Font.BOLD, 24));
		frequencyValueTextBox.setColumns(10);
		frequencyValueTextBox.setBackground(Constants.PINK);
		frequencyValueTextBox.setBounds(140, 155, 100, 55);
		frequencyValueTextBox.setEditable(!isStarted);
		frequencyValueTextBox.setText(frequency + "");
		maxMinFrequencyPanel.add(frequencyValueTextBox);

		JLabel lowestValue = new JLabel("<html>Lowest<br>Value:</html>",JLabel.CENTER);
		lowestValue.setFont(new Font("Dialog", Font.BOLD, 16));
		lowestValue.setHorizontalTextPosition(SwingConstants.CENTER);
		lowestValue.setBounds(15, 85, 100, 55);
		maxMinFrequencyPanel.add(lowestValue);

		JLabel highestValue = new JLabel("<html>Highest<br>Value:</html>");
		highestValue.setFont(new Font("Dialog", Font.BOLD, 16));
		highestValue.setHorizontalTextPosition(SwingConstants.CENTER);
		highestValue.setHorizontalAlignment(SwingConstants.CENTER);
		highestValue.setBounds(15, 15, 100, 55);
		highestValue.setText("<html>Highest<br>value:</html>");
		maxMinFrequencyPanel.add(highestValue);
		highestValue.setBackground(Constants.GRAY);

		JLabel lblFrequency = new JLabel("<html>Frequency<br>(Hz):</html>", SwingConstants.CENTER);
		lblFrequency.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFrequency.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFrequency.setBounds(15, 155, 100, 55);
		maxMinFrequencyPanel.add(lblFrequency);

		JPanel statusIndicatorPanel = new JPanel();
		statusIndicatorPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		statusIndicatorPanel.setBounds(10, 50, 215, 250);
		statusIndicatorPanel.setBackground(Constants. LIGHTBLUE);
		contentPane.add(statusIndicatorPanel);
		statusIndicatorPanel.setLayout(null);

		indicatorLabel = new JLabel(Character.toString((char) 0x2022), SwingConstants.CENTER);
		indicatorLabel.setBounds(10, 20, 200, 200);
		statusIndicatorPanel.add(indicatorLabel);
		indicatorLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		indicatorLabel.setForeground(isStarted ? Constants.GREEN : Constants.RED);
		indicatorLabel.setFont(indicatorLabel.getFont().deriveFont(300f));

		consolePanel = new ConsolePanel();
		consolePanel.setForeground(Constants.BLACK);
		consolePanel.setBackground(Constants.WHITE);
		consolePanel.setBounds(10, 316, 476, 132);		
		contentPane.add(consolePanel);
	}

	/**
	 * Displays the message on the console panel.
	 * @param information console displays
	 */
	public static void appendToConsolePanel (String input) {
		ConsolePanel.updateText (input);
	}

	private Server server;
	private JLabel indicatorLabel;
	private ConsolePanel consolePanel;

	public static void main(String[] args) {
		ServerMainWindow frame = new ServerMainWindow();
		frame.setVisible(true);
	}

	/**
	 * Creates the main window frame. It displays the highest value, lowest value 
	 * frequency and the console.
	 * 
	 */
	public ServerMainWindow() {
		min = 50;
		max = 2500;
		frequency = 5;
		isStarted = false;
		setBounds(new Rectangle(0, 700, 700, 0));
		setTitle("SERVER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 500);

		contentPane = new JPanel();
		contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.setBounds(new Rectangle(0, 650, 650, 0));
		contentPane.setForeground(Constants.GREEN);
		contentPane.setBackground(Constants.LIGHTBLUE);
		contentPane.setBorder(new LineBorder(Constants.BLACK, 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		startStopButton = new ToggleButton (this);
		startStopButton.setBounds(380, 12, 100, 25);
		contentPane.add(startStopButton);

		JPanel maxMinFrequencyPanel = new JPanel();
		maxMinFrequencyPanel.setBackground(Constants.LIGHTBLUE);
		maxMinFrequencyPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Constants.BLACK, null));
		maxMinFrequencyPanel.setBounds(230, 50, 250, 250);
		contentPane.add(maxMinFrequencyPanel);
		maxMinFrequencyPanel.setLayout(null);

		highestValueTextBox = new JTextField();
		highestValueTextBox.setEditable(false);
		highestValueTextBox.setBorder(new LineBorder(Constants.BLACK));
		highestValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
		highestValueTextBox.setFont(new Font("Dialog", Font.BOLD, 24));
		highestValueTextBox.setBounds(140, 15, 100, 55);
		highestValueTextBox.setBackground(Constants.PINK);
		highestValueTextBox.setColumns(10);
		highestValueTextBox.setEditable(!isStarted);
		highestValueTextBox.setText(max + "");
		maxMinFrequencyPanel.add(highestValueTextBox);

		lowestValueTextBox = new JTextField();
		lowestValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
		lowestValueTextBox.setBorder(new LineBorder(Constants.BLACK));
		lowestValueTextBox.setFont(new Font("Dialog", Font.BOLD, 24));
		lowestValueTextBox.setColumns(10);
		lowestValueTextBox.setBackground(Constants.LIGHTBLUE);
		lowestValueTextBox.setBounds(140, 85, 100, 55);
		lowestValueTextBox.setEditable(!isStarted);
		lowestValueTextBox.setText(min + "");
		maxMinFrequencyPanel.add(lowestValueTextBox);

		frequencyValueTextBox = new JTextField();
		frequencyValueTextBox.setHorizontalAlignment(SwingConstants.CENTER);
		frequencyValueTextBox.setBorder(new LineBorder(Constants.BLACK));
		frequencyValueTextBox.setAlignmentY(Component.TOP_ALIGNMENT);
		frequencyValueTextBox.setFont(new Font("Dialog", Font.BOLD, 24));
		frequencyValueTextBox.setColumns(10);
		frequencyValueTextBox.setBackground(Constants.PINK);
		frequencyValueTextBox.setBounds(140, 155, 100, 55);
		frequencyValueTextBox.setEditable(!isStarted);
		frequencyValueTextBox.setText(frequency + "");
		maxMinFrequencyPanel.add(frequencyValueTextBox);

		JLabel lowestValue = new JLabel("<html>Lowest<br>Value:</html>",JLabel.CENTER);
		lowestValue.setFont(new Font("Dialog", Font.BOLD, 16));
		lowestValue.setBorder(new LineBorder(Constants.BLACK));
		lowestValue.setBackground(Constants.PINK);
		lowestValue.setHorizontalTextPosition(SwingConstants.CENTER);
		lowestValue.setBounds(15, 85, 100, 55);
		maxMinFrequencyPanel.add(lowestValue);
		lowestValue.setOpaque(true);

		JLabel highestValue = new JLabel("<html>Highest<br>Value:</html>");
		highestValue.setFont(new Font("Dialog", Font.BOLD, 16));
		highestValue.setBorder(new LineBorder(Constants.BLACK));
		highestValue.setHorizontalTextPosition(SwingConstants.CENTER);
		highestValue.setHorizontalAlignment(SwingConstants.CENTER);
		highestValue.setBounds(15, 15, 100, 55);
		highestValue.setText("<html>Highest<br>value:</html>");
		maxMinFrequencyPanel.add(highestValue);
		highestValue.setBackground(Constants.GRAY);

		JLabel lblFrequency = new JLabel("<html>Frequency<br>(Hz):</html>", SwingConstants.CENTER);
		lblFrequency.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFrequency.setBorder(new LineBorder(Constants.BLACK));
		lblFrequency.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFrequency.setBounds(15, 155, 100, 55);
		maxMinFrequencyPanel.add(lblFrequency);

		JPanel statusIndicatorPanel = new JPanel();
		statusIndicatorPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		statusIndicatorPanel.setBounds(10, 50, 215, 250);
		statusIndicatorPanel.setBorder(new LineBorder(Constants.BLACK));
		statusIndicatorPanel.setBackground(Constants. LIGHTBLUE);
		contentPane.add(statusIndicatorPanel);
		statusIndicatorPanel.setLayout(null);

		indicatorLabel = new JLabel(Character.toString((char) 0x2022), SwingConstants.CENTER);
		indicatorLabel.setBounds(10, 20, 200, 200);
		statusIndicatorPanel.add(indicatorLabel);
		indicatorLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		indicatorLabel.setForeground(isStarted ? Constants.GREEN : Constants.RED);
		indicatorLabel.setFont(indicatorLabel.getFont().deriveFont(300f));

		consolePanel = new ConsolePanel();
		consolePanel.setForeground(Constants.BLACK);
		consolePanel.setBackground(Constants.WHITE);
		consolePanel.setBounds(10, 316, 470, 132);
		contentPane.add(consolePanel);
	}

	/**
	 * Displays the message on the console panel.
	 * 
	 */
	public static void appendToConsolePanel (String input) {
		ConsolePanel.updateText (input);
	}
	/** 
	 * Implements the functionality for the start/stop button.
	 * @param status of server, start or close.
	 */
	public void controlStartStopAction (boolean isStarted) {
		if (isStarted) {
			highestValueTextBox.setEditable(false);
			lowestValueTextBox.setEditable(false);
			frequencyValueTextBox.setEditable(false);
			indicatorLabel.setForeground (Constants.GREEN);
			consolePanel.updateText("Awesome"); 
			this.startServer();
		} else {
			consolePanel.updateText("Hello");
			highestValueTextBox.setEditable(true);
			lowestValueTextBox.setEditable(true);
			frequencyValueTextBox.setEditable(true);
			indicatorLabel.setForeground(Constants.RED);
			this.stopServer();
		}
	}	

	/**
	 * Start server and wait clients.
	 */
	private void startServer() {
		int max = Integer.parseInt(highestValueTextBox.getText());
		int min = Integer.parseInt(lowestValueTextBox.getText());
		int frequency = Integer.parseInt(frequencyValueTextBox.getText());

		server = new Server(max, min, frequency);
		new Thread(server).start();
	}

	/**
	 * Close server.
	 */
	private void stopServer() {
		server.closeConnection();
	}
}
