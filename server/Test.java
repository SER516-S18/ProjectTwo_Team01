import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import ser516.project2.team1.server.sys.Server;
/**
 * This class is used to implement the GUI for server.
 * @author SER516-TeamOne
 * @version 1.00
 * @since 02-22-2018
 */

public class Test extends JFrame {

  private static final long serialVersionUID = 1L;
  private final static Color RED = new Color(220, 20, 60);
  private final static Color GREEN = new Color(0, 128, 0);

  private JPanel contentPane;
  private Server server;
  private Thread serverThread;
  private static String arrArgs[];
  private final JPanel pnlStatusButton = new JPanel();

  private JTextField txtFrequency;
  private JTextField txtLowest;
  private JTextField txtHighest;
  private static JTextArea txtConsole;

  private static String strConsole;

  public static void main(String[] args) {
          Test frame = new Test();
          frame.setVisible(true);
  }

  /**
   * Different GUI components will be added to the frame using the constructor
   */
  public Test() {
    setTitle("Server");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 500, 500);

    contentPane = new JPanel();
    contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    pnlStatusButton.setBackground(new Color(238, 238, 238));
    pnlStatusButton.setForeground(new Color(0, 0, 0));
    pnlStatusButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null));
    pnlStatusButton.setBounds(374, 11, 100, 25);
    contentPane.add(pnlStatusButton);
    pnlStatusButton.setLayout(new GridLayout(1, 2));

    JLabel lblToggle = new JLabel("Start");
    JButton btnToggle = new JButton("");

    lblToggle.setHorizontalAlignment(SwingConstants.CENTER);

    lblToggle.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        removeComponents(pnlStatusButton);
        if (e.getButton() == 1 && lblToggle.getText() == "Stop") {
          addComponents(pnlStatusButton, btnToggle, lblToggle);
          btnToggle.setBackground(RED);
          lblToggle.setText("Start");
          setInputFields (true);
          stopServer();
        } else if (e.getButton() == 1 && lblToggle.getText() == "Start") {
          addComponents(pnlStatusButton, lblToggle, btnToggle);
          btnToggle.setBackground(GREEN);
          lblToggle.setText("Stop");
          setInputFields (false);
          startServer();
        }
      }
    });

    btnToggle.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        removeComponents(pnlStatusButton);
        if (e.getID() == 1001 && lblToggle.getText() == "Stop") {
          addComponents(pnlStatusButton, btnToggle, lblToggle);
          btnToggle.setBackground(RED);
          lblToggle.setText("Start");
          setInputFields (true);
          stopServer();
        } else if (e.getID() == 1001 && lblToggle.getText() == "Start") {
          addComponents(pnlStatusButton, lblToggle, btnToggle);
          btnToggle.setBackground(GREEN);
          lblToggle.setText("Stop");
          setInputFields (false);
          startServer();
        }
      }
    });

    pnlStatusButton.add(btnToggle);
    pnlStatusButton.add(lblToggle);
    btnToggle.setBackground(RED);
    pnlStatusButton.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { btnToggle, lblToggle }));

    JPanel pnlDisplay = new JPanel();
    pnlDisplay.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    pnlDisplay.setBounds(10, 44, 464, 284);
    contentPane.add(pnlDisplay);
    pnlDisplay.setLayout(null);

    JPanel pnlStatusIndicator = new JPanel();
    pnlStatusIndicator.setBorder(new LineBorder(Color.LIGHT_GRAY));
    pnlStatusIndicator.setBounds(10, 7, 242, 266);
    pnlDisplay.add(pnlStatusIndicator);

    JPanel pnlValues = new JPanel();
    pnlValues.setBorder(null);
    pnlValues.setBounds(262, 7, 192, 266);
    pnlDisplay.add(pnlValues);
    pnlValues.setLayout(null);

    JLabel lblHighest = new JLabel("<html>Highest<br>value:</html>");
    lblHighest.setHorizontalTextPosition(SwingConstants.CENTER);
    lblHighest.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblHighest.setBorder(new LineBorder(new Color(0, 0, 0)));
    lblHighest.setHorizontalAlignment(SwingConstants.CENTER);
    lblHighest.setBounds(10, 10, 75, 45);
    pnlValues.add(lblHighest);

    JLabel lblLowest = new JLabel("<html>Lowest<br>value:</html>");
    lblLowest.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblLowest.setHorizontalTextPosition(SwingConstants.CENTER);
    lblLowest.setHorizontalAlignment(SwingConstants.CENTER);
    lblLowest.setBorder(new LineBorder(new Color(0, 0, 0)));
    lblLowest.setBounds(10, 70, 75, 45);
    pnlValues.add(lblLowest);

    JLabel lblFrequency = new JLabel("<html>Frequency<br>(Hz):</html>");
    lblFrequency.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblFrequency.setHorizontalAlignment(SwingConstants.CENTER);
    lblFrequency.setHorizontalTextPosition(SwingConstants.CENTER);
    lblFrequency.setBorder(new LineBorder(new Color(0, 0, 0)));
    lblFrequency.setBounds(10, 130, 75, 45);
    pnlValues.add(lblFrequency);

    txtFrequency = new JTextField();
    txtFrequency.setFont(new Font("Tahoma", Font.PLAIN, 26));
    txtFrequency.setHorizontalAlignment(SwingConstants.CENTER);
    txtFrequency.setBounds(99, 130, 80, 45);
    pnlValues.add(txtFrequency);
    txtFrequency.setColumns(10);

    txtLowest = new JTextField();
    txtLowest.setHorizontalAlignment(SwingConstants.CENTER);
    txtLowest.setFont(new Font("Tahoma", Font.PLAIN, 26));

    txtLowest.setColumns(10);
    txtLowest.setBounds(99, 70, 80, 45);
    pnlValues.add(txtLowest);

    txtHighest = new JTextField();
    txtHighest.setFont(new Font("Tahoma", Font.PLAIN, 26));
    txtHighest.setHorizontalAlignment(SwingConstants.CENTER);
    txtHighest.setText("1024");
    txtHighest.setColumns(10);
    txtHighest.setBounds(99, 10, 80, 45);
    pnlValues.add(txtHighest);

    JPanel pnlTextPanel = new JPanel();
    pnlTextPanel.setBounds(10, 339, 464, 111);
    contentPane.add(pnlTextPanel);
    pnlTextPanel.setLayout(null);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(0, 0, 464, 111);
    pnlTextPanel.add(scrollPane);

    txtConsole = new JTextArea();
    txtConsole.setTabSize(2);
    scrollPane.setViewportView(txtConsole);
    txtConsole.setText(strConsole);
    setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { btnToggle, lblToggle, lblHighest, txtHighest,
        lblLowest, txtLowest, lblFrequency, txtFrequency, txtConsole }));
  }
/**
 * Used to start the server.
 */
  private void startServer() {
    server = new Server(arrArgs);
    serverThread = new Thread(server);
    serverThread.start();
    arrArgs = null;
  }
/**
 * Used to stop the server and remove connection to the client.
 */
  private void stopServer() {
    server.closeConnection();
  }
/**
 * 
 * @param panel
 * Used to remove components from the panel.
 */
  private void removeComponents(JPanel panel) {
    for (Component item : panel.getComponents()) {
      panel.remove(item);
    }
  }
/**
 * 
 * @param panel
 * @param one
 * @param two
 * Used to add components to the panel.
 */
  private void addComponents(JPanel panel, Component one, Component two) {
    panel.add(one);
    panel.add(two);
  }
/**
 * 
 * @param output
 * Used to make the console display a message.
 */
  public void setConsole (String output) {
    txtConsole.setText("\n" + output);
  }
/**
 * 
 * @param isEnabled
 * When the the server is stopped, isEnabled contains 'true',
 * then it allows the user to set a highest value, lowest value to be sent to client.
 * Also allows to set a frequency at which values can be sent.
 */
  private void setInputFields(boolean isEnabled) {
    this.txtHighest.setEditable(isEnabled);
    this.txtLowest.setEditable(isEnabled);
    this.txtFrequency.setEditable(isEnabled);
  }
}
