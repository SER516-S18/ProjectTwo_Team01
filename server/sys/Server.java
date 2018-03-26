package server.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

import server.gui.ServerMainWindow;

/**
 * Implements a server which can accept several clients connection, and send
 * random to these numbers.
 * 
 * @author Zelin Bao
 * @author Cephas Armstrong-Mensah
 * @author Vihar Bhatt
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 2018-02-23
 *
 */
public class Server implements Runnable {
  private static volatile boolean isStarted;
  static int frequency = 5;
  static int max = 1024;
  static int min = 0;
  static ArrayList<Socket> clientsArray;
  static ArrayList<ServerThread> serverThreadList;

  private static int port = 8001;
  private static Socket socket;
  private static ServerSocket sSocket;
  private static Scanner in;
  private static String ip;
  private static PrintWriter out;

  private int numberOfChannels;

  /**
   * Create a new server with a string input.
   * 
   * @param args
   *          a string of port, ip, frequency, max, min.
   */
  public Server(String args[]) {
    if (args != null && args.length > 1) {
      this.parseArguments(args);
    }
  }

  /**
   * Create a new server with integer inputs.
   * 
   * @param max
   *          represents up limitation of random numbers
   * @param min
   *          represents bottom limitation of random numbers
   * @param frequency
   *          server frequency
   */
  public Server(int max, int min, int frequency) {
    Server.max = max;
    Server.min = min;
    Server.frequency = frequency;
    isStarted = true;
    serverThreadList = new ArrayList<ServerThread>();
    clientsArray = new ArrayList<Socket>();
  }

  /**
   * Create a new server socket. Displays information of port and server is
   * starting. Displays client information, when a client connect with Parse the
   * value of frequency Send random numbers created to the client.
   */
  public void run() {
    try {
      sSocket = new ServerSocket(port);
      setConsoleInfo("Server is up on port: (" + port + ") and waiting for client connection...");

      while (isStarted) {
        socket = sSocket.accept();
        clientsArray.add(socket);
        setConsoleInfo("Client connected from " + socket.getLocalAddress().getHostName());

        out = new PrintWriter(socket.getOutputStream());
        out.println("Welcome client, how many channels do you need? frequency=" + frequency + ";");
        out.flush();

        in = new Scanner(socket.getInputStream());
        String temp = in.nextLine();

        setConsoleInfo(temp);
        setConsoleInfo(temp.split("channels=")[0]);
        setConsoleInfo(temp.split("channels=")[1]);
        numberOfChannels = Integer.parseInt(temp.split("channels=")[1].split(";")[0]);
        setConsoleInfo("Number of channels: " + numberOfChannels);

        for (int x = 0; x < numberOfChannels; x++) {
          serverThreadList.add(new ServerThread(socket, x + 1));
          new Thread(serverThreadList.get(serverThreadList.size() - 1)).start();
        }
      }
    } catch (SocketException e1) {
      setConsoleInfo("Socket is closed...");
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }

  /**
   * Close the connection between client with server
   */
  public void closeServerConnection() {
    try {
      for (int i = 0; i < serverThreadList.size(); i++) {
        closeConnectedSocket(serverThreadList.get(i).socket);
      }
      isStarted = false;
      sSocket.close();
      setConsoleInfo("Socket is closed...");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void closeConnectedSocket(Socket s) {
    int index = Server.clientsArray.indexOf(s);
    try {
      if (index > -1) {
        out = new PrintWriter(s.getOutputStream());
        out.println("closing");
        out.flush();

        for (int i = 0; i < serverThreadList.size(); i++) {
          if (serverThreadList.get(i).socket == s) {
            setConsoleInfo("Closing channel: " + serverThreadList.get(i).channelID.split("_")[1]);
            serverThreadList.get(i).isConnected = false;
            serverThreadList.remove(s);
          }
        }
        setConsoleInfo("Closing client socket: " + clientsArray.get(index).getInetAddress().getHostAddress());
        clientsArray.get(index).close();
        clientsArray.remove(index);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Parse the string input, get value of port, ip, and frequency, max, min
   * 
   * @param args
   *          a string of port, ip, frequency, max, min
   */
  public void setArguments(String args[]) {
    if (args.length > 1) {
      this.parseArguments(args);
    }
  }

  /**
   * Get information
   * 
   * @return a string
   */
  public String getConsoleInfo() {
    return "Console: ";
  }

  /**
   * Set values and information which the console display.
   * 
   * @param info
   *          presents information which the console displays.
   */
  public static void setConsoleInfo(String info) {
    ServerMainWindow.appendToConsolePanel(info);
  }

  /**
   * Parse the string input, get value of port, ip, and frequency, max, min.
   * 
   * @param args
   *          a string of port, ip, frequency, max, min.
   */
  private void parseArguments(String args[]) {
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
      }
    }
  }

  /**
   * Get max number
   * 
   * @return represents up limitation of random numbers
   */
  public int getMax() {
    return max;
  }

  /**
   * Get min number
   * 
   * @return represents bottom limitation of random numbers
   */
  public int getMin() {
    return min;
  }

  /**
   * Get frequency
   * 
   * @return frequency
   */
  public int getFrequency() {
    return frequency;
  }
}
