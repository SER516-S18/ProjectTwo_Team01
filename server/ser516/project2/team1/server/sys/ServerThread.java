package ser516.project2.team1.server.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

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
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	private String message;
	
	ServerThread (Socket socket) {
		this.socket = socket;
	}
	
	public void CheckConnection() throws IOException {
		if (!socket.isConnected()) {
			for (int i = 0; i < Server.clientsArray.size(); i++) {
				if (Server.clientsArray.get(i) == socket) {
					Server.clientsArray.remove(i);
				}
			}
			
			for (int i = 0; i < Server.clientsArray.size(); i++) {
				Socket temp = (Socket)Server.clientsArray.get(i);
				PrintWriter tempOut = new PrintWriter (temp.getOutputStream());
				tempOut.println (temp.getLocalAddress().getHostName() + " disconnected");
				tempOut.flush();			
				
				System.out.println (temp.getLocalAddress().getHostName() + " disconnected");
			}
		}
	}
	
	public void run () {
		try {
			try {
				in = new Scanner (socket.getInputStream());
				out = new PrintWriter (socket.getOutputStream());
				
				while (true) {
					CheckConnection ();					
					if (!in.hasNext()) { return; }
					
					message = in.nextLine();					
					for (int i = 0; i < Server.clientsArray.size(); i++) {
						Socket temp =  (Socket)Server.clientsArray.get(i);
						PrintWriter tempOut = new PrintWriter (temp.getOutputStream());
						tempOut.println(message);
						tempOut.flush();
						System.out.println ("Sent to: " + temp.getLocalAddress().getHostName());
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
