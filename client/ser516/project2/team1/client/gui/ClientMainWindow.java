package ser516.project2.team1.client.gui;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import org.jfree.chart.ChartPanel;
import ser516.project2.team1.client.sys.Client;
import ser516.project2.team1.client.sys.NumberStatistics;
import util.ConsolePanel;
import util.Constants;
import util.ToggleButton;

/**
 * The Client Main Window is the complete User Interface of the client. 
 * 
 * @author team 1
 * @version 1.0
 * @since 2017-02-23
 *
**/

public class ClientMainWindow extends JFrame {

	private static JPanel contentPane;
	private ConsolePanel consolePanel;
	private Thread clientThread;
	private Client client;
	JComboBox<String> comboBox;
	private JLabel actualLowLabel;
	private JLabel actualHighLabel;
	private JLabel actualAverageLabel;
	private JLabel actualFrequencyLabel;
	private JPanel containerPanel;
	public DisplayGraph displayGraph;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		ClientMainWindow window = new ClientMainWindow();
		window.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public ClientMainWindow() {
		// clientJFrame = new JFrame();
		contentPane = new JPanel();
		setTitle("Client");
		setBounds(100, 100, 1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Constants.LIGHTBLUE);

		ToggleButton btnStartStop = new ToggleButton(this);
		btnStartStop.setBounds(846, 13, 100, 25);

		contentPane.add(btnStartStop);
		addGraphPanel();
	}

	/**
	 * adds labels to show channel, highest, lowest and average values
	 * to center panel
	 */

	private void addGraphPanel()
	{

		//This is the other container panel. This holds the graph window and the buttons.
		containerPanel = new JPanel();
		containerPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		containerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		containerPanel.setBorder(new LineBorder(Constants.BLACK));
		containerPanel.setBackground(Constants.LIGHTBLUE);
		containerPanel.setBounds(12, 72, 958, 645);
		contentPane.add(containerPanel);
		containerPanel.setLayout(null);

		JPanel graphPanel = new JPanel();
		graphPanel.setBorder(new LineBorder(Constants.BLACK));
		graphPanel.setBounds(12, 13, 573, 619);
		containerPanel.add(graphPanel);
		containerPanel.setOpaque(false);

		 displayGraph= new DisplayGraph();
		displayGraph.chartPanel= new ChartPanel(displayGraph.displayGraph);
		displayGraph.chartPanel.setLocation(12, 26);
		displayGraph.chartPanel.setSize(new Dimension(478, 567));
		graphPanel.setLayout(null);
		ChartPanel chartPanel= new ChartPanel(displayGraph.displayGraph);
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
		highestLbl.setBorder(new LineBorder(Constants.BLACK));
		highestLbl.setBounds(0, 0, 100, 55);
		highestLbl.setAlignmentY(Component.TOP_ALIGNMENT);
		highestLbl.setHorizontalAlignment(SwingConstants.CENTER);
		highestPanel.add(highestLbl);
		highestLbl.setOpaque(true);
		highestLbl.setBackground(Constants.LIGHTBLUE);

		JPanel highestValuePanel = new JPanel();
		highestValuePanel.setBorder(new LineBorder(Constants.BLACK));
		highestValuePanel.setBounds(798, 57, 104, 54);
		highestValuePanel.setBackground(Constants.PINK);
		containerPanel.add(highestValuePanel);
		
		actualHighLabel = new JLabel("");
		highestValuePanel.add(actualHighLabel);

		JPanel lowestPanel = new JPanel();
		lowestPanel.setBorder(new LineBorder(Constants.BLACK));
		lowestPanel.setBounds(623, 160, 100, 55);
		containerPanel.add(lowestPanel);
		lowestPanel.setLayout(null);

		JLabel lowestLbl = new JLabel("<html>Lowest<br>Value:</html>");
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
		actualLowLabel.setBounds(0, 0, 85, 55);
		lowestValuePanel.add(actualLowLabel);

		JPanel averagePanel = new JPanel();
		averagePanel.setBorder(new LineBorder(Constants.BLACK));
		averagePanel.setBounds(623, 268, 100, 55);
		containerPanel.add(averagePanel);
		averagePanel.setLayout(null);
		averagePanel.setBackground(Constants.PINK);

		JLabel averageLbl = new JLabel("Average:");
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
		actualAverageLabel.setBounds(0, 0, 100, 55);
		averageValuePanel.add(actualAverageLabel);

		JPanel numOfChannelsPanel = new JPanel();
		numOfChannelsPanel.setBorder(new LineBorder(Constants.BLACK));
		numOfChannelsPanel.setBounds(623, 361, 100, 55);
		containerPanel.add(numOfChannelsPanel);
		numOfChannelsPanel.setLayout(null);

		JLabel numOfChannelsLbl = new JLabel("Channels:");
		numOfChannelsLbl.setAlignmentY(Component.TOP_ALIGNMENT);
		numOfChannelsLbl.setBorder(new LineBorder(Constants.BLACK));
		numOfChannelsLbl.setBounds(0, 0, 100, 55);
		numOfChannelsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		numOfChannelsPanel.add(numOfChannelsLbl);
		numOfChannelsLbl.setOpaque(true);
		numOfChannelsLbl.setBackground(Constants.PINK);
		
		String[] values = {"1","2","3","4"};
		comboBox = new JComboBox<String>(values);
		comboBox.setBounds(798, 361, 104, 22);
		containerPanel.add(comboBox);

		JPanel frequencyPanel = new JPanel();
		frequencyPanel.setBorder(new LineBorder(Constants.BLACK));
		frequencyPanel.setBounds(623, 460, 100, 55);
		containerPanel.add(frequencyPanel);
		frequencyPanel.setLayout(null);

		JLabel frequencyLbl = new JLabel("Frequency:");
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
		actualFrequencyLabel.setBounds(15, 16, 69, 20);
		frequencyValuePanel.add(actualFrequencyLabel);

		consolePanel = new ConsolePanel();
		consolePanel.setBounds(12, 743, 958, 197);
		contentPane.add(consolePanel);
		consolePanel.setBorder(new LineBorder(Constants.BLACK));
		consolePanel.setLayout(null);
	}

	public void controlStartStopAction (boolean isStarted) {
		if (isStarted) {
			client = new Client(Integer.parseInt((String)(comboBox.getSelectedItem())),this);
			clientThread = new Thread(client);
			clientThread.start();
			ConsolePanel.updateText ("Client is started");
			// start the client here
		} else {
			//stop the client here
			ConsolePanel.updateText("Client is stopped");
		}
	}
	
	public void setFrequency(int frequency)
	{
		this.actualFrequencyLabel.setText(frequency+"");
		this.containerPanel.repaint();
	}
	
	
	public void refreshWindow()
	{
		this.actualHighLabel.setText(NumberStatistics.getHighestValue()+"");
		this.actualLowLabel.setText(NumberStatistics.getLowestValue()+"");
		this.actualAverageLabel.setText(NumberStatistics.getAverageValue()+"");
		this.containerPanel.repaint();
	}
}
