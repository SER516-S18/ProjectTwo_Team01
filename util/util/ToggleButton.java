package util;

import java.awt.Color;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import util.Constants;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import ser516.project2.team1.server.gui.ServerMainWindow;

public class ToggleButton extends JPanel implements ActionListener, MouseListener {

  private static final long serialVersionUID = 8996294272697598276L;
  public static JLabel startStopLabel;
  public static JButton startStopButton;

  private JFrame callingClass;

  public ToggleButton(JFrame callingClass) {
    this.callingClass = callingClass;
    setBackground(Constants.GRAY);
    setForeground(Constants.BLACK);
    setBorder(new EtchedBorder(EtchedBorder.LOWERED, Constants.BLACK, null));

    setBounds(374, 10, 100, 30);
    setLayout(new GridLayout(1, 2));

    startStopLabel = new JLabel("Start");
    startStopButton = new JButton();
    startStopButton.addActionListener(this);

    startStopLabel.setHorizontalAlignment(SwingConstants.CENTER);
    startStopLabel.addMouseListener(this);
    add(startStopButton);
    add(startStopLabel);

    startStopButton.setBackground(Constants.RED);
    setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { startStopButton, startStopLabel }));
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    removeComponents(this);
    System.out.println("Action is being performed: " + e.getID());
    if (e.getID() == 1001 && startStopLabel.getText() == "Stop") {
      setTogglePanelControl (false);
    } else if (e.getID() == 1001 && startStopLabel.getText() == "Start") {
      System.out.println("else happened");
      setTogglePanelControl (true);
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    System.out.println("Mouse Action is being performed: " + e.getButton());
    removeComponents(this);
    if (e.getButton() == 1 && startStopLabel.getText() == "Stop") {
      addComponents(this, startStopButton, startStopLabel);
      setTogglePanelControl (false);
    } else if (e.getButton() == 1 && startStopLabel.getText() == "Start") {
      addComponents(this, startStopLabel, startStopButton);
      setTogglePanelControl (true);
    }
  }

  private void removeComponents(JPanel panel) {
    for (Component item : panel.getComponents()) {
      panel.remove(item);
    }
  }

  private void addComponents(JPanel panel, Component left, Component right) {
    panel.add(left);
    panel.add(right);
  }

  private void setStartStopAction (boolean isStarted) {
    if (callingClass instanceof ServerMainWindow) {
      ((ServerMainWindow) callingClass).controlStartStopAction (isStarted);
    } else {
      //ServerMainWindow.setStartStopAction(isStarted);
      // Client item can go here

    }
  }
  
  private void setTogglePanelControl(boolean isStarted) {
    if (isStarted) {
      addComponents(this, startStopButton, startStopLabel);
      startStopButton.setBackground(Constants.GREEN);
      startStopLabel.setText("Stop");
    } else {
      addComponents(this, startStopLabel, startStopButton);
      startStopButton.setBackground(Constants.RED);
      startStopLabel.setText("Start");
    }    
    
    System.out.println("Code: " + isStarted);
    
    setStartStopAction (isStarted);
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }
}