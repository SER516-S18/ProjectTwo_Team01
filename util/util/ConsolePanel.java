import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

/**
 * The ConsolePanel implements a JPanel to create a console. The console will
 * be able to display any message passed to it.
 * 
 * @author bhangal
 * @version 1.0
 * @since 2017-02-17
 *
 */
public class ConsolePanel extends JPanel{
	
	private static JScrollPane consoleScrollPane;
	private JLabel consoleHeaderLabel;
	private static JLabel consoleMessageLabel;
	
	private static String completeMessage = "<html>";
	private static String messageDisplay;
	private int horizontalScrollValue = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
	private int verticalScrollValue = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
	private int labelVerticalAlignment = SwingConstants.TOP;
	private GridLayout layout = new GridLayout(1,1);
	
	
	/**
	 * This function is the constructor of the console. It generates the 
	 * header and message label of the JScrollPane. It also sets the 
	 * properties of the JScrollPane.
	 * 
	 */
	public ConsolePanel() {
		
		consoleScrollPane = new JScrollPane();
		consoleScrollPane.setHorizontalScrollBarPolicy(horizontalScrollValue);
		consoleScrollPane.setVerticalScrollBarPolicy(verticalScrollValue);
		
		consoleHeaderLabel = new JLabel("Console :");
		consoleScrollPane.setColumnHeaderView(consoleHeaderLabel);
		
		consoleMessageLabel = new JLabel("");
		consoleMessageLabel.setVerticalAlignment(labelVerticalAlignment);
		consoleScrollPane.setViewportView(consoleMessageLabel);
		
		this.add(consoleScrollPane);
		this.setLayout(layout);
	}
	
	
	/**
	 * This function is used to add new message in the console and update the 
	 * position of vertical scroll to the bottom.
	 * 
	 * @param message. This is the message that needs to be displayed on the console.
	 * @return void.
	 */
	public static void updatetext(String message) {
		completeMessage = completeMessage + message + "<br/>" ;
		messageDisplay = completeMessage + "</html>";
		consoleMessageLabel.setText(messageDisplay);
		
		int ht= consoleMessageLabel.getSize().height;
		consoleScrollPane.getViewport().setViewPosition(new Point(0, ht));
	}

}
