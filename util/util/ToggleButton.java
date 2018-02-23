package util;

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

public class ToggleButton extends JPanel implements ActionListener, MouseListener {

  private static final long serialVersionUID = 8996294272697598276L;

  private JLabel startStopLabel;
  private JButton startStopButton;
  private boolean isStarted;

  private final static Color RED = new Color(220, 20, 60);
  private final static Color GREEN = new Color(0, 128, 0);
  private final static Color BLACK = new Color(0, 0, 0);
  private final static Color GRAY = new Color(238, 238, 238);

  public ToggleButton() {
    setBackground(GRAY);
    setForeground(BLACK);
    setBorder(new EtchedBorder(EtchedBorder.LOWERED, BLACK, null));
    
    setBounds(374, 10, 100, 30);
    setLayout(new GridLayout(1, 2));

    startStopLabel = new JLabel("Start");
    startStopButton = new JButton();

    startStopLabel.setHorizontalAlignment(SwingConstants.CENTER);
    add(startStopButton);
    add(startStopLabel);
    
    startStopButton.setBackground(RED);
    setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { startStopButton, startStopLabel }));
    isStarted = false;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    removeComponents(this);
    if (e.getID() == 1001 && startStopLabel.getText() == "Stop") {
      addComponents(this, startStopButton, startStopLabel);
      startStopButton.setBackground(RED);
      startStopLabel.setText("Start");
      isStarted = true;
    } else if (e.getID() == 1001 && startStopLabel.getText() == "Start") {
      addComponents(this, startStopLabel, startStopButton);
      startStopButton.setBackground(GREEN);
      startStopLabel.setText("Stop");
      isStarted = false;
    }
  }

  public boolean getIsStarted () {
    return isStarted;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    removeComponents(this);
    if (e.getButton() == 1 && startStopLabel.getText() == "Stop") {
      addComponents(this, startStopButton, startStopLabel);
      startStopButton.setBackground(RED);
      startStopLabel.setText("Start");
      isStarted = true;
    } else if (e.getButton() == 1 && startStopLabel.getText() == "Start") {
      addComponents(this, startStopLabel, startStopButton);
      startStopButton.setBackground(GREEN);
      startStopLabel.setText("Stop");
      isStarted = false;
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