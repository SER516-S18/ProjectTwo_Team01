package ser516.project2.team1.client.gui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import org.jfree.chart.ChartPanel;

public class ClientWindow {

	private JFrame frmClient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindow window = new ClientWindow();
					window.frmClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClient = new JFrame();
		frmClient.setTitle("Client");
		frmClient.setBounds(100, 100, 1000, 1000);
		frmClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClient.setLocationRelativeTo(null);
		SpringLayout springLayout = new SpringLayout();
		frmClient.getContentPane().setLayout(springLayout);
		
		addComponentsToFrame(springLayout);
	}
	
	/**
	 * Adds start/stop button, tow panels for displaying Graph and Console, to form a layout.
	 * @param springLayout
	 */
	private void addComponentsToFrame(SpringLayout springLayout)
	{
		JToggleButton btnNewButton = new JToggleButton("");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 10, SpringLayout.NORTH, frmClient.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, -231, SpringLayout.EAST, frmClient.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, 56, SpringLayout.NORTH, frmClient.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, frmClient.getContentPane());
		frmClient.getContentPane().add(btnNewButton);
		JLabel lblToggle = new JLabel("Start");
	    lblToggle.addMouseListener(new MouseAdapter() {
	      @Override
	      public void mouseClicked(MouseEvent e) {
	        if (e.getButton() == 1 && lblToggle.getText() == "Stop") {
	          btnNewButton.setBackground(Color.RED);
	          lblToggle.setText("Start");
	          //closeConnection(); 
	        } 
	        else if (e.getButton() == 1 && lblToggle.getText() == "Start") {
	          btnNewButton.setBackground(Color.GREEN);
	          lblToggle.setText("Stop");
	          //createSocket();
	        }
	      }
	    });	
		DisplayGraph DG= new DisplayGraph();
		ChartPanel chartPanel= new ChartPanel(DG.displayGraph);
		chartPanel.setLocation(12, 26);
		chartPanel.setSize(new Dimension(478, 567));
		JPanel graphPanel = new JPanel();
		graphPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		springLayout.putConstraint(SpringLayout.NORTH, graphPanel, 17, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.SOUTH, graphPanel, 623, SpringLayout.SOUTH, btnNewButton);
		frmClient.getContentPane().add(graphPanel);
		graphPanel.setLayout(null);
		
		graphPanel.add(chartPanel);
		
		JPanel centerPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, graphPanel, -26, SpringLayout.WEST, centerPanel);
		springLayout.putConstraint(SpringLayout.WEST, centerPanel, 538, SpringLayout.WEST, frmClient.getContentPane());
		centerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		springLayout.putConstraint(SpringLayout.NORTH, centerPanel, 17, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.SOUTH, centerPanel, 623, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, centerPanel, 0, SpringLayout.EAST, btnNewButton);
		frmClient.getContentPane().add(centerPanel);
		centerPanel.setLayout(new GridLayout(5, 2, 0, 0));
		addComponentsToCenterPanel(centerPanel);
		
		
		
		JPanel consolePanel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, graphPanel, 0, SpringLayout.WEST, consolePanel);
		consolePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		springLayout.putConstraint(SpringLayout.NORTH, consolePanel, -257, SpringLayout.SOUTH, frmClient.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, consolePanel, 10, SpringLayout.WEST, frmClient.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, consolePanel, -10, SpringLayout.SOUTH, frmClient.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, consolePanel, 0, SpringLayout.EAST, btnNewButton);
		frmClient.getContentPane().add(consolePanel);
		

	}
	
	/**
	 * adds labels to show channel, highest, lowest and average values
	 * to center panel
	 */
	private void addComponentsToCenterPanel(JPanel centerPanel) {
		JLabel highestValueLabel = new JLabel("<html>Highest<br>Value:</html>");
		highestValueLabel.setBackground(SystemColor.activeCaption);
		highestValueLabel.setOpaque(true);
		centerPanel.add(highestValueLabel);
		
		JLabel actualHighValueLabel = new JLabel("");
		actualHighValueLabel.setBackground(Color.PINK);
		centerPanel.add(actualHighValueLabel);
		actualHighValueLabel.setOpaque(true);
		
		JLabel lowestValueLabel = new JLabel("<html>Lowest<br>Value:</html>");
		lowestValueLabel.setBackground(Color.PINK);
		centerPanel.add(lowestValueLabel);
		lowestValueLabel.setOpaque(true);
		
		JLabel actualLowestValueLabel = new JLabel("");
		actualLowestValueLabel.setBackground(SystemColor.activeCaption);
		centerPanel.add(actualLowestValueLabel);
		actualLowestValueLabel.setOpaque(true);
		
		JLabel averageLabel= new JLabel("Average:");
		averageLabel.setBackground(SystemColor.activeCaption);
		centerPanel.add(averageLabel);
		averageLabel.setOpaque(true);
		
		JLabel actualAverageLabel = new JLabel("");
		actualAverageLabel.setBackground(Color.PINK);
		centerPanel.add(actualAverageLabel);
		actualAverageLabel.setOpaque(true);
		
		JLabel numOfChannelsLabel = new JLabel("Channels:");
		numOfChannelsLabel.setBackground(Color.PINK);
		centerPanel.add(numOfChannelsLabel);
		numOfChannelsLabel.setOpaque(true);
		
		String[] channelDropdownValues = {"1", "2", "3", "4", "5"};
		JComboBox<String> channelDropdown = new JComboBox<>(channelDropdownValues);
		channelDropdown.setSelectedIndex(2);
		centerPanel.add(channelDropdown);
				
		JLabel frequencyLabel= new JLabel("Frequency(Hz):");
		frequencyLabel.setBackground(SystemColor.activeCaption);
		centerPanel.add(frequencyLabel);
		frequencyLabel.setOpaque(true);
		
		JLabel actualFrequencyLabel = new JLabel("");
		actualFrequencyLabel.setBackground(Color.PINK);
		centerPanel.add(actualFrequencyLabel);
		actualFrequencyLabel.setOpaque(true);
		
	}
	}
