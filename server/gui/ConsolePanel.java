import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



/**
 * The ConsolePanel implements a JPanel which consists of 
 * two JLabels. One displays a fixed label name and other displays any input to the 
 * ConsolePanel.
 * 
 * @author bhangal
 * @version 1.0
 * @since 2017-02-17
 *
 */
public class ConsolePanel extends JPanel{
	
	private JLabel nameLabel,messageLabel;
	private Color lightGrey = Color.LIGHT_GRAY;
	private Color grey = Color.GRAY;
	private Color black = Color.BLACK;
	private Dimension dim = new Dimension(300,100);
	private GridLayout layout = new GridLayout(2,1);
	private Font font = new Font("Papyrus", Font.PLAIN, 15);
	private int alignment = SwingConstants.LEFT;
	private String ConsoleHeader = "Console: ";
	
	public ConsolePanel(String message) {
		ConsoleSettings();
		DisplayMessage(message);
	}
	
	public void DisplayMessage(String message) {
		nameLabel = new JLabel("nameLabel");
		nameLabel.setText(ConsoleHeader);
		nameLabel.setFont(font);
		nameLabel.setForeground(black);
		nameLabel.setBackground(black);
		this.add(nameLabel);
		nameLabel.setHorizontalAlignment(alignment);
		messageLabel = new JLabel("messageLabel");
		messageLabel.setText(message);
		messageLabel.setFont(font);
		messageLabel.setBackground(black);
		messageLabel.setForeground(grey);
		this.add(messageLabel);
		messageLabel.setHorizontalAlignment(alignment);
	}
	
	public void ConsoleSettings() {
		this.setBackground(lightGrey);		
		this.setPreferredSize(dim);		
		this.setLayout(layout);	
	}

}
