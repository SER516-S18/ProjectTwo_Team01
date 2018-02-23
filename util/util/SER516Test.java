import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class SER516Test extends JFrame {
	private GridLayout layout = new GridLayout(2,1);
	Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	
	public static void main(String[] args) {
		
		 EventQueue.invokeLater(new Runnable() {
		      public void run() {
		        try {
		        	SER516Test frame = new SER516Test();
		          frame.setVisible(true);
		        } catch (Exception e) {
		          e.printStackTrace();
		        }
		      }
		    });
	}
	
	public SER516Test() {
		setTitle("abc");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 500, 500);
	    this.setLayout(layout);
	    
	    ConsolePanel console = new ConsolePanel();
	    this.add(console);
	    console.setBorder(raisedetched);
	    
	    ConsolePanel.updatetext("Client connected");
	    ConsolePanel.updatetext("Server error!");
	}
}
