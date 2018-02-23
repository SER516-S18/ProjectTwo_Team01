import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JSplitPane;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.ShapeGraphicAttribute;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import javafx.scene.text.Font;

import javax.swing.JRadioButton;

public class ServerMainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField highestInput;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
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
	 * Create the frame.
	 */
	public ServerMainWindow() {
		setTitle("SERVER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
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
		startStopButton.setBounds(333, 6, 161, 29);
		contentPane.add(startStopButton);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		statusPanel.setBounds(16, 42, 249, 292);
		contentPane.add(statusPanel);
		statusPanel.setLayout(null);
		
		JLabel statusIndicator = new JLabel("•", SwingConstants.CENTER);
		statusIndicator.setForeground(new Color(50, 205, 50));
		statusIndicator.setFont(statusIndicator.getFont().deriveFont(350f));
		
		statusIndicator.setBounds(6, 6, 237, 201);
		statusPanel.add(statusIndicator);
		
		JPanel consolePanel = new JPanel();
		consolePanel.setBackground(new Color(0, 0, 0));
		consolePanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		consolePanel.setBounds(6, 358, 488, 114);
		contentPane.add(consolePanel);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(238, 238, 238));
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_5.setBounds(6, 32, 488, 314);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel lowPanel = new JPanel();
		lowPanel.setBounds(270, 105, 100, 50);
		panel_5.add(lowPanel);
		lowPanel.setBackground(new Color(255, 228, 225));
		lowPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel lowestValue = new JLabel("<html>Lowest<br>Value:</html>",JLabel.CENTER);
		lowPanel.add(lowestValue);
		
		highestInput = new JTextField();
		highestInput.setBounds(384, 18, 100, 56);
		panel_5.add(highestInput);
		highestInput.setBackground(new Color(255, 228, 225));
		highestInput.setColumns(10);
		
		JPanel highPanel = new JPanel();
		highPanel.setBounds(270, 21, 100, 50);
		panel_5.add(highPanel);
		highPanel.setBackground(new Color(135, 206, 250));
		highPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel highestValue = new JLabel("<html>Highest<br>Value:</html>");
		highPanel.add(highestValue);
		highestValue.setBackground(Color.LIGHT_GRAY);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBackground(new Color(135, 206, 250));
		textField.setBounds(382, 102, 100, 56);
		panel_5.add(textField);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(270, 189, 100, 50);
		panel_5.add(panel);
		
		JLabel lblFrequency = new JLabel("<html>Frequency<br>(Hz):</html>", SwingConstants.CENTER);
		panel.add(lblFrequency);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(255, 228, 225));
		textField_1.setBounds(384, 186, 100, 56);
		panel_5.add(textField_1);
		
		
		
	}
}
