
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.border.Border;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

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
	 * Adds start/stop button and two panels to form a layout.
	 * @param springLayout
	 */
	private void addComponentsToFrame(SpringLayout springLayout)
	{
		JButton btnNewButton = new JButton("Start/Stop");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 10, SpringLayout.NORTH, frmClient.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, -231, SpringLayout.EAST, frmClient.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, 56, SpringLayout.NORTH, frmClient.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, frmClient.getContentPane());
		frmClient.getContentPane().add(btnNewButton);
		
		JPanel graphPanel = new JPanel();
		graphPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		springLayout.putConstraint(SpringLayout.NORTH, graphPanel, 17, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.SOUTH, graphPanel, 623, SpringLayout.SOUTH, btnNewButton);
		frmClient.getContentPane().add(graphPanel);
		
		JPanel centerPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, graphPanel, -26, SpringLayout.WEST, centerPanel);
		springLayout.putConstraint(SpringLayout.WEST, centerPanel, 538, SpringLayout.WEST, frmClient.getContentPane());
		centerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		springLayout.putConstraint(SpringLayout.NORTH, centerPanel, 17, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.SOUTH, centerPanel, 623, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, centerPanel, 0, SpringLayout.EAST, btnNewButton);
		frmClient.getContentPane().add(centerPanel);
		centerPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel consolePanel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, graphPanel, 0, SpringLayout.WEST, consolePanel);
		consolePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		springLayout.putConstraint(SpringLayout.NORTH, consolePanel, -257, SpringLayout.SOUTH, frmClient.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, consolePanel, 10, SpringLayout.WEST, frmClient.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, consolePanel, -10, SpringLayout.SOUTH, frmClient.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, consolePanel, 0, SpringLayout.EAST, btnNewButton);
		frmClient.getContentPane().add(consolePanel);
		

	}
	
	
	}


