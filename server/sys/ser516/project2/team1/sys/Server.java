package ser516.project2.team1.sys;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JTextArea;

/**
 * 
 * @author 	Zelin Bao
 * @author 	Cephas Armstrong-Mensah
 * @author 	Group 1 #001 - #013
 * @since 	FEB 2018
 * @version	1.0
 * 
 * Server class used to bind, listen and accept
 * connections which are then sent onto a separate thread
 *
 */
public class Server implements Runnable {
	private static int port = 8001;
	private static int frequency = 5;
	private static int max = 1024;
	private static int min = 0;	
	private static boolean isRunning = false;
	private static JTextArea txtConsole;
	private static Socket socket;
	private static ServerSocket sSocket;
	private static String ip = "localhost";
	private static int random = 0;
		
	public static List<Socket> clientsArray = new ArrayList<Socket>();
	
	public Server (String args []) {
		if (args != null && args.length > 1) {
			this.parseArguments(args);
		}
	}
	
	public Server (String args [], JTextArea console) {
    if (args != null && args.length > 1) {
      this.parseArguments(args);
    }
    txtConsole = console;
    isRunning = true;
    ValuesThread vt = new ValuesThread();
    new Thread(vt).start();
  }
  
  @Override
	public void run() {
    try {
			sSocket = new ServerSocket (port);
			System.out.println ("Server is up on port: (" + port + ") and ready for client connections!\nWaiting for clients...");	
		
			while (true) {
				socket = sSocket.accept();
				clientsArray.add(socket);
				System.out.println ("Client connected from: " + socket.getLocalAddress().getHostName());
				setConsoleInfo ("Client connected from " + socket.getLocalAddress().getHostName());
				ServerThread server = new ServerThread(socket);			
				new Thread (server).start();
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
			sSocket.close();
			isRunning = false;
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
	  return "\nConsole: ";
	}
	

  public void setConsoleInfo(String info) {    
    while (true) {
      txtConsole.append("\nConsole:\t" + info);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }    
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
					System.exit (0);  // invalid data provided, exiting for a retry
			}
			
		}
	}
	
	private class ValuesThread implements Runnable {
    Random r = new Random(System.currentTimeMillis());
    
    /**
     * Counter constructor to spin off a new thread
     * 
     * @param lblCounter JLabel used for displaying counter
     * @param counter Initial counter value either 0 or 9
     */
    public ValuesThread() {
      
    }

    @Override
    public void run() {
      while (isRunning) {
        random = r.nextInt(max - min) + min;
        try {
          Thread.sleep((int) max / 1000);
          setConsoleInfo ("Sending: " + random);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
