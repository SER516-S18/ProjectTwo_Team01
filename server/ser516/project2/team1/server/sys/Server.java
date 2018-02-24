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
 * Implements a server which can accept several clients connection, and send random
 * to these numbers.
 * 
 * @author  Zelin Bao
 * @author  Cephas Armstrong-Mensah
 * @author  Vihar Bhatt
 * @author  Group 1 #001 - #013
 * @version 1.0
 * @since   2018-02-23
 *
 */
public class Server implements Runnable {
	
	/**
	 * Value of port server uses.
	 * Value of frequency.
	 * Represents up limitation of random numbers.
	 * Represents bottom limitation of random numbers.
	 * Ip of localhost.
	 * An Arraylist stores clients
	 */
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

	/**
	 * Create a new server with a string input.
	 * @param args a string of port, ip, frequency, max, min.
	 */
	public Server (String args []) {
		if (args != null && args.length > 1) {
			this.parseArguments(args);
		}
	}
	
	/**
	 * Create a new server with integer inputs.
	 * @param max represents up limitation of random numbers
	 * @param min represents bottom limitation of random numbers
	 * @param frequency server frequency
	 */
	public Server (int max, int min, int frequency) {
		Server.max = max;
		Server.min = min;
		Server.frequency = frequency;
	}

    /**
     * Create a new server socket.
     * Displays information of port and server is starting.
     * Displays client information, when a client connect with server.
     * Parse the value of frequency
     * Send random numbers created to the client.
     */
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
				setConsoleInfo("Channels: " + numberOfChannels + "\n");
                  
				for (int x = 0; x < numberOfChannels; x++) {
					serverThread = new ServerThread(clientsArray.get(clientsArray.size() - 1), x + 1);
					new Thread (serverThread).start();  
				}
			}
		} catch (SocketException e1) {
			System.out.println("Socket is closed...");
		}  catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Close the already connection between clinet with server.
	 */
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
	
	/**
	 * Parse the string input, get value of port, ip, and frequency, max, min
	 * @param args a string of port, ip, frequency, max, min
	 */
	public void setArguments (String args []) {
		if (args.length > 1) {
			this.parseArguments(args);
		}
	}
	
	/**
	 * Get information
	 * @return a string
	 */
	public String getConsoleInfo() {    
		return "Console: ";
	}

	/**
	 * Set values and information which the console display.
	 * @param info presents information which the console displays.
	 */
	public void setConsoleInfo(String info) {    
		ServerMainWindow.appendToConsolePanel(info);
	}

  /**
   * Parse the string input, get value of port, ip, and frequency, max, min.
   * @param args a string of port, ip, frequency, max, min.
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
				System.out.println("--port <port> --ip <ip> --frequency <frequency> --max <max> "
						+ "--min <min>\n\tOR");
				System.out.println("-port <port> -ip <ip> -frequency <frequency> -max <max> -min "
						+ "<min>\n\tOR");
				System.out.println("-p <port> -ip <ip> -f <frequency> -max <max> -min <min>");
				System.exit (0);
			}
		}
	}
	
	/**
	 * Get max number
	 * @return represents up limitation of random numbers
	 */
	public int getMax() {
		return this.max;
	}
	
	/**
	 * Get min number
	 * @return represents bottom limitation of random numbers
	 */
	public int getMin() {
		return this.min;
	}
	
	/**
	 * Get frequency
	 * @return frequency
	 */
	public int getFrequency() {
		return this.frequency;
	}
}
