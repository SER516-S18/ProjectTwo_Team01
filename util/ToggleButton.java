import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class ToggleButton extends JPanel implements ActionListener, MouseListener{
	
	JLabel lblToggle;
	JButton btnToggle;
	
	private final static Color RED = new Color(220, 20, 60);
	private final static Color GREEN = new Color(0, 128, 0);
	
	public ToggleButton() {
		
		setBackground(new Color(238, 238, 238));
	    setForeground(new Color(0, 0, 0));
	    setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null));
	    setBounds(374, 11, 100, 25);
	    setLayout(new GridLayout(1, 2));

	    lblToggle = new JLabel("Start");
	    btnToggle = new JButton("");

	    lblToggle.setHorizontalAlignment(SwingConstants.CENTER);
	    add(btnToggle);
	    add(lblToggle);
	    btnToggle.setBackground(RED);
	    setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { btnToggle, lblToggle }));

	    
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		removeComponents(this);
        if (e.getID() == 1001 && lblToggle.getText() == "Stop") {
          addComponents(this, btnToggle, lblToggle);
          btnToggle.setBackground(RED);
          lblToggle.setText("Start");
//          setInputFields (true);
//          stopServer();
        } else if (e.getID() == 1001 && lblToggle.getText() == "Start") {
          addComponents(this, lblToggle, btnToggle);
          btnToggle.setBackground(GREEN);
          lblToggle.setText("Stop");
//          setInputFields (false);
//          startServer();
	}

}



	@Override
	public void mouseClicked(MouseEvent e) {
		removeComponents(this);
        if (e.getButton() == 1 && lblToggle.getText() == "Stop") {
          addComponents(this, btnToggle, lblToggle);
          btnToggle.setBackground(RED);
          lblToggle.setText("Start");
//          setInputFields (true);
//          stopServer();
        } else if (e.getButton() == 1 && lblToggle.getText() == "Start") {
          addComponents(this, lblToggle, btnToggle);
          btnToggle.setBackground(GREEN);
          lblToggle.setText("Stop");
//          setInputFields (false);
//          startServer();
        }
		
	}
	
	private void removeComponents(JPanel panel) {
	    for (Component item : panel.getComponents()) {
	      panel.remove(item);
	    }
	  }

	  private void addComponents(JPanel panel, Component one, Component two) {
	    panel.add(one);
	    panel.add(two);
	  }



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}