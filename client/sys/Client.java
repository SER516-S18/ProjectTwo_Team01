package client.sys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import client.gui.ClientMainWindow;

/**
 * Client class used to connect to server, receive numbers on given number of
 * channels.
 * 
 * @author Shilpa Bhat
 * @author Group 1 #001 - #013
 * @since FEB 2018
 * @version 1.0
 */
public class Client implements Runnable {
  private static Socket socket;
  private static volatile boolean isConnected = false;
  private static PrintWriter out;
  private final String ipAddress = "localhost";
  private final int port = 8001;
  private int channels;
  private int frequency;
  public ClientMainWindow clientWindow;
  public static ArrayList<ArrayList<String>> numberList = new ArrayList<ArrayList<String>>();

  /**
   * Constructor accepts the number of channels through which data has to be
   * received from the server.
   * 
   * @param numChannels
   *          - number of channels to receive numbers from server
   */
  public Client(int numChannels, ClientMainWindow clientWindow) {
    channels = numChannels;
    this.clientWindow = clientWindow;
    isConnected = true;
    connectSocket();
  }

  private void connectSocket() {
    try {
      socket = new Socket(ipAddress, port);
    } catch (UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public int getFrequency() {
    return frequency;
  }

  public void setConsoleInfo(String info) {
    ClientMainWindow.appendToConsole(info);
  }

  /**
   * Welcome message from server has frequency in it, split message to get the
   * frequency.
   */
  private void receiveFrequency() {
    try {
      InputStream inputStream = socket.getInputStream();
      InputStreamReader reader = new InputStreamReader(inputStream);
      BufferedReader bufferReader = new BufferedReader(reader);
      String message = bufferReader.readLine();
      setConsoleInfo(message);
      frequency = Integer.parseInt(message.split("frequency=")[1].split(";")[0]);
      clientWindow.setFrequency(frequency);
      setConsoleInfo("frequency is " + frequency);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  /**
   * Channels is required by server to send the appropriate numbers.
   */
  private void sendNumberOfChannels() {
    try {
      String channelsMessage = "channels=" + channels + ";";
      out = new PrintWriter(socket.getOutputStream());
      out.println(channelsMessage);
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * The number received in the form channelID_id=value; is split and added to
   * channeletails.
   */
  private void receiveNumbers() {
    InputStreamReader reader;
    while (isConnected) {
      try {
        reader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferReader = new BufferedReader(reader);
        String message = bufferReader.readLine();
        int channelId = Integer.parseInt(message.split("channelID_")[1].split("=")[0]);
        int channelValue = Integer.parseInt(message.split("channelID_")[1].split("=")[1].split(";")[0]);
        setConsoleInfo("Channel (" + channelId + ") received value: " + channelValue + "&emsp;&emsp&lt;"
            + LocalTime.now() + "&gt;");
        Channel channelDetails = new Channel(channelId, channelValue);
        UpdateClientWindow(channelDetails);
      } catch (IOException e) {
        System.out.println("Server closed connection...");
        isConnected = false;
      }
    }
    setConsoleInfo("Not receiving numbers from server, connection closed by client...");
  }

  /**
   * Terminates the connection with server.
   */
  public void closeConnection() {
    try {
      isConnected = false;
      out = new PrintWriter(socket.getOutputStream());
      out.println("closing");
      out.flush();
      socket.shutdownInput();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Has calls to receive frequency,send channels and receive numbers.
   */
  @Override
  public void run() {
    while (isConnected) {
      setConsoleInfo("Client connected to " + socket.getLocalAddress().getHostName());
      receiveFrequency();
      sendNumberOfChannels();
      receiveNumbers();
    }
    setConsoleInfo("Client closing connection...");
  }

  public void UpdateClientWindow(Channel channelDetails) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        clientWindow.displayGraph.updateGraph(channels, channelDetails);
        NumberStatistics.ComputeNumberStatistics(channelDetails);
        clientWindow.refreshWindow();
      }
    });
  }
}
