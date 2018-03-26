package server.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Random;

import util.ConsolePanel;

/**
 * Implements a new server thread when user changes channel.
 * 
 * @author Zelin Bao
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 2018-02-23
 * 
 */
public class ServerThread implements Runnable {

  /**
   * Channel ID. Random number which are sent to client
   */
  protected Socket socket;
  private PrintWriter out;
  protected String channelID;
  private Random randomGenerator;
  private int randomNumber;
  private final int FREQ_THRESHOLD = Server.frequency;
  private InputThread inputThread;
  public volatile boolean isConnected;

  /**
   * Create a new ServerThread with given socket and id.
   * 
   * @param socket
   *          presents current socket
   * @param id
   *          presents current channel ID
   */
  public ServerThread(Socket socket, int id) {
    this.socket = socket;
    this.channelID = "channelID_" + id;
    randomGenerator = new Random(System.currentTimeMillis());
    inputThread = new InputThread(this);
    new Thread(inputThread).start();

    isConnected = true;
  }

  /**
   * Ensures after client is disconnected, their socket is removed from array
   * 
   * @throws IOException
   */
  public void closeConnection() {
    int me = -1;
    Server.serverThreadList.remove(Server.serverThreadList.indexOf(this));
    System.out.println(socket.getLocalAddress().getHostName() + " disconnected");
  }

  /**
   * Get channel ID
   * 
   * @return Channel ID
   */
  public String getChannelID() {
    return this.channelID;
  }

  /**
   * Run method starts the thread to randomize the number being sent to the client
   */
  public void run() {
    try {
      try {
        out = new PrintWriter(socket.getOutputStream());
        while (isConnected) {
          try {
            randomNumber = randomGenerator.nextInt(Server.max - Server.min) + Server.min;
            out.println(this.channelID + "=" + randomNumber + ";");
            out.flush();

            ConsolePanel.updateText("Sending to channel (" + channelID.split("_")[1] + ") randomNumber "
                + randomNumber + "&emsp;&emsp&lt;" + LocalTime.now() + "&gt;");
            Thread.sleep(FREQ_THRESHOLD);

          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      } finally {
        socket.close();
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}