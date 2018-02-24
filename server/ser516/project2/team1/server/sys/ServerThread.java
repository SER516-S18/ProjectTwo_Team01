package ser516.project2.team1.server.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import util.ConsolePanel;

/**
 * Server class used to bind, listen and accept
 * connections which are then sent onto a separate thread

 * @author 	Zelin Bao
 * @author 	Cephas Armstrong-Mensah
 * @author 	Group 1 #001 - #013
 * @since 	02-22-2018
 * @version	1.0
 * 
 */

public class ServerThread implements Runnable {
	
/**
 * Channel ID.
 * Random number which are sent to client
 */
 private Socket socket;
 private PrintWriter out;
 private String channelID;
 private Random randomGenerator;
 private int randomNumber;
 private final int FREQ_THRESHOLD = Server.frequency;
  
  
  /**
   * Create a new ServerThread with given socket and id.
   * @param socket presents current socket
   * @param id presents current channel ID
   */
  public ServerThread (Socket socket, int id) {
    this.socket = socket;
    this.channelID = "channelID_" + id;  
    randomGenerator = new Random(System.currentTimeMillis());
  }
  
  /**
   * Get channel ID
   * @return Channel ID
   */
  public String getChannelID () {
    return this.channelID;
  }  
  
/**
 * Run method creates several random numbers and send them to client. The console shows current channel ID
 * and random numbers .
 * @param max represents up limitation of random numbers
 * @param min represents bottom limitation of random numbers
 * @param socket
 * @param randomNumber represents random number
 * @param channelID represents channel ID
 */
  
  public void run () {
    try {
      try {
        out = new PrintWriter (socket.getOutputStream());

        while (true) {
          try {
            randomNumber = randomGenerator.nextInt(Server.max - Server.min) + Server.min;
            out.println(this.channelID + "=" + randomNumber + ";");
            out.flush();
            
            ConsolePanel.updateText("sending to " + channelID + " randomNumber " + randomNumber + "\n");
            Thread.sleep(FREQ_THRESHOLD);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }   
      } finally {
        socket.close();
      }
    } catch (IOException e){
      System.out.println (e);
    }
  } 
}