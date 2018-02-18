import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import ser516.project2.team1.sys.*;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class Test extends JFrame {

	private final static Color RED = new Color(220, 20, 60);
	private final static Color GREEN = new Color(0, 128, 0);
	private JPanel contentPane;
	private Server server;
	private Thread serverThread;
	private static String arrArgs [];
	private final JPanel panel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		arrArgs = args;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel.setBackground(new Color(238, 238, 238));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null));
		panel.setBounds(100, 10, 100, 25);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 2));
		
		JLabel lblToggle = new JLabel("Start");
		JButton toggleStart = new JButton("");
		
		lblToggle.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblToggle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeComponents(panel);
				if (e.getButton() == 1 && lblToggle.getText() == "Stop") {
					addComponents (panel, toggleStart, lblToggle);
					toggleStart.setBackground(RED);					
					lblToggle.setText("Start");
					stopServer();
				} else if (e.getButton() == 1 && lblToggle.getText() == "Start") {
					addComponents (panel, lblToggle, toggleStart);
					toggleStart.setBackground(GREEN);
					lblToggle.setText("Stop");
					startServer();				
				}
			}
		});		
		
		toggleStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeComponents(panel);
				if (e.getID() == 1001 && lblToggle.getText() == "Stop") {
					addComponents (panel, toggleStart, lblToggle);
					toggleStart.setBackground(RED);					
					lblToggle.setText("Start");	
					stopServer();	
				} else if (e.getID() == 1001 && lblToggle.getText() == "Start") {
					addComponents (panel, lblToggle, toggleStart);
					toggleStart.setBackground(GREEN);
					lblToggle.setText("Stop");		
					startServer();	
				}				
			}			
		});
		
		panel.add(toggleStart);
		panel.add(lblToggle);		
		toggleStart.setBackground(RED);		
	}
	
	private void startServer () {
		server = new Server(arrArgs);
		serverThread = new Thread (server);
		serverThread.start();
	}
	
	private void stopServer () {
		server.closeConnection();
	}
	
	private void removeComponents (JPanel panel) {
		for (Component item : panel.getComponents()) {
			panel.remove(item);
		}
	}
		
	private void addComponents (JPanel panel, Component one, Component two) {
		panel.add(one);
		panel.add(two);
	}
}
