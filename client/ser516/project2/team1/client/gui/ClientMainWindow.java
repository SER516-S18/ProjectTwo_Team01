package ser516.project2.team1.client.gui;
import javax.swing.*;
import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import org.jfree.chart.ChartPanel;

import util.ConsolePanel;
import util.ToggleButton;

public class ClientMainWindow extends JFrame {

	private static JPanel contentPane;
	private ConsolePanel consolePanel;
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
		JPanel containerPanel = new JPanel();
		containerPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		containerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		containerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		containerPanel.setBounds(12, 72, 958, 645);
		contentPane.add(containerPanel);
		containerPanel.setLayout(null);

		JPanel graphPanel = new JPanel();
		graphPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		graphPanel.setBounds(12, 13, 573, 619);
		containerPanel.add(graphPanel);
		containerPanel.setOpaque(false);

		DisplayGraph displayGraph= new DisplayGraph();
		graphPanel.setLayout(null);
		ChartPanel chartPanel= new ChartPanel(displayGraph.displayGraph);
		chartPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		chartPanel.setLocation(0, 0);
		chartPanel.setSize(new Dimension(573, 619));

		graphPanel.add(chartPanel);

		JPanel highestPanel = new JPanel();
		highestPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		highestPanel.setBounds(623, 57, 100, 55);
		containerPanel.add(highestPanel);
		highestPanel.setLayout(null);

		JLabel highestLbl = new JLabel("<html>Highest<br>Value:</html>");
		highestLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		highestLbl.setBounds(0, 0, 100, 55);
		highestLbl.setAlignmentY(Component.TOP_ALIGNMENT);
		highestLbl.setHorizontalAlignment(SwingConstants.CENTER);
		highestPanel.add(highestLbl);
		highestLbl.setOpaque(true);

		JPanel highestValuePanel = new JPanel();
		highestValuePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		highestValuePanel.setBounds(798, 57, 104, 54);
		containerPanel.add(highestValuePanel);

		JPanel lowestPanel = new JPanel();
		lowestPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lowestPanel.setBounds(623, 160, 100, 55);
		containerPanel.add(lowestPanel);
		lowestPanel.setLayout(null);

		JLabel lowestLbl = new JLabel("<html>Lowest<br>Value:</html>");
		lowestLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		lowestLbl.setAlignmentY(Component.TOP_ALIGNMENT);
		lowestLbl.setBounds(0, 0, 100, 55);
		lowestLbl.setHorizontalAlignment(SwingConstants.CENTER);
		lowestPanel.add(lowestLbl);
		lowestLbl.setOpaque(true);

		JPanel lowestValuePanel = new JPanel();
		lowestValuePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lowestValuePanel.setBounds(798, 160, 100, 55);
		containerPanel.add(lowestValuePanel);
		lowestValuePanel.setLayout(null);

		JPanel averagePanel = new JPanel();
		averagePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		averagePanel.setBounds(623, 268, 100, 55);
		containerPanel.add(averagePanel);
		averagePanel.setLayout(null);

		JLabel averageLbl = new JLabel("Average:");
		averageLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		averageLbl.setAlignmentY(Component.TOP_ALIGNMENT);
		averageLbl.setBounds(0, 0, 100, 55);
		averageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		averagePanel.add(averageLbl);
		averageLbl.setOpaque(true);

		JPanel averageValuePanel = new JPanel();
		averageValuePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		averageValuePanel.setBounds(798, 268, 100, 55);
		containerPanel.add(averageValuePanel);
		averageValuePanel.setLayout(null);

		JPanel numOfChannelsPanel = new JPanel();
		numOfChannelsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		numOfChannelsPanel.setBounds(623, 361, 100, 55);
		containerPanel.add(numOfChannelsPanel);
		numOfChannelsPanel.setLayout(null);

		JLabel numOfChannelsLbl = new JLabel("Channels:");
		numOfChannelsLbl.setAlignmentY(Component.TOP_ALIGNMENT);
		numOfChannelsLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		numOfChannelsLbl.setBounds(0, 0, 100, 55);
		numOfChannelsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		numOfChannelsPanel.add(numOfChannelsLbl);
		numOfChannelsLbl.setOpaque(true);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(798, 361, 104, 22);
		containerPanel.add(comboBox);

		JPanel frequencyPanel = new JPanel();
		frequencyPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frequencyPanel.setBounds(623, 460, 100, 55);
		containerPanel.add(frequencyPanel);
		frequencyPanel.setLayout(null);

		JLabel frequencyLbl = new JLabel("Frequency:");
		frequencyLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		frequencyLbl.setBounds(0, 0, 100, 55);
		frequencyLbl.setAlignmentY(Component.TOP_ALIGNMENT);
		frequencyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		frequencyPanel.add(frequencyLbl);
		frequencyLbl.setOpaque(true);

		JPanel frequencyValuePanel = new JPanel();
		frequencyValuePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frequencyValuePanel.setBounds(798, 460, 100, 55);
		containerPanel.add(frequencyValuePanel);
		frequencyValuePanel.setLayout(null);

		consolePanel = new ConsolePanel();
		consolePanel.setBounds(12, 743, 958, 197);
		contentPane.add(consolePanel);
		consolePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		consolePanel.setLayout(null);
	}

	public void controlStartStopAction (boolean isStarted) {
		if (isStarted) {
			ConsolePanel.updateText ("Client is started");
			// start the client here
		} else {
			//stop the client here
			ConsolePanel.updateText("Client is stopped");
		}
	}
}