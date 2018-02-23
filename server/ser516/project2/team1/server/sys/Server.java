
package ser516.project2.team1.server.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JTextArea;

import ser516.project2.team1.server.gui.ServerMainWindow;

/**
 * Server class used to bind, listen and accept
 * connections which are then sent onto a separate thread
 *
 * @author  Zelin Bao
 * @author  Cephas Armstrong-Mensah
 * @author  Vihar Bhatt
 * @author  Group 1 #001 - #013
 * @since   02-23-2018
 * @version 1.0
 *
 */
public class Server implements Runnable {

  private static int port = 8001;
  static int frequency = 5;
  static int max = 1024;
  static int min = 0; 
  
  private static Socket socket;
  private static ServerSocket sSocket;
  private static String ip = "localhost";
  private static int random = 0;
  private static Scanner in;
  private static PrintWriter out;  
  public static ArrayList<Socket> clientsArray = new ArrayList<Socket>();
  
  private ServerThread serverThread;
  private int numberOfChannels;

  public Server (String args []) {
    if (args != null && args.length > 1) {
      this.parseArguments(args);
    }
  }

  public Server (int max, int min, int frequency) {
    Server.max = max;
    Server.min = min;
    Server.frequency = frequency;
  }

  @Override
  public void run() {
    try {
      sSocket = new ServerSocket (port);
      setConsoleInfo ("Server is up on port: (" + port + ") and waiting for client connection...");

      while (true) {
        socket = sSocket.accept();
        clientsArray.add(socket);
        setConsoleInfo ("Client connected from " + socket.getLocalAddress().getHostName());

        out = new PrintWriter(socket.getOutputStream());
        out.println("Welcome client, how many channels do you need? frequency="+frequency+";");
        out.flush();

        System.out.println("waiting for channel");
        in = new Scanner (socket.getInputStream());
        String temp = in.nextLine();
        
        setConsoleInfo(temp);
        
        setConsoleInfo(temp.split("channels=")[0]);
        setConsoleInfo(temp.split("channels=")[1]);
        
        numberOfChannels = Integer.parseInt(temp.split("channels=")[1].split(";")[0]);
       //noResponse = false;
        
        setConsoleInfo("Channels: " + numberOfChannels + "\n");
        //boolean noResponse = true;
                  
        for (int x = 0; x < numberOfChannels; x++) {
          serverThread = new ServerThread(clientsArray.get(clientsArray.size() - 1), x + 1);
          new Thread (serverThread).start();  
        }
      }
    } catch (SocketException e1) {
      System.out.println("Socket is closed...");
    }  catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  public void closeConnection () {
    try {
      for (int i = 0; i < clientsArray.size(); i++) {
        setConsoleInfo("Closing: " + (i+1));
        clientsArray.get(i).close();
      }
      
      sSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setArguments (String args []) {
    if (args.length > 1) {
      this.parseArguments(args);
    }
  }

  public String getConsoleInfo() {    
    return "Console: ";
  }


  public void setConsoleInfo(String info) {    
    ServerMainWindow.appendToConsolePanel(info);
  }

  /**
   * 
   * @param args arguments passed at the command line
   */
  private void parseArguments (String args[]) {
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
      case "--port":
      case "--p":
      case "-port":
      case "-p":
        port = Integer.parseInt(args[++i].trim());
        break;        
      case "--ip":
      case "-ip":
        ip = args[++i].trim();
        break;
      case "--frequency":
      case "-frequency":
      case "--f":
      case "-f":
        frequency = Integer.parseInt(args[++i].trim());
        break;
      case "--max":
      case "-max":
        max = Integer.parseInt(args[++i].trim());
        break;
      case "--min":
      case "-min":
        min = Integer.parseInt(args[++i].trim());
        break;
      default:
        System.out.println("Unknown argument switch detected - valid switches below");
        System.out.println("--port <port> --ip <ip> --frequency <frequency> --max <max> --min <min>\n\tOR");
        System.out.println("-port <port> -ip <ip> -frequency <frequency> -max <max> -min <min>\n\tOR");
        System.out.println("-p <port> -ip <ip> -f <frequency> -max <max> -min <min>");
        System.exit (0);
      }

    }
  }
  
  public int getMax() {
    return this.max;
  }
  
  public int getMin() {
    return this.min;
  }
  
  public int getFrequency() {
    return this.frequency;
  }
}
