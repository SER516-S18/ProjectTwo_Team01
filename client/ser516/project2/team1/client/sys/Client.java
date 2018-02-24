package ser516.project2.team1.client.sys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import javax.swing.SwingUtilities;
import ser516.project2.team1.client.gui.ClientMainWindow;

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
	private static PrintWriter out;
	private final String ipAddress = "127.0.0.1";
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
	 *            - number of channels to receive numbers from server
	 */
	public Client(int numChannels, ClientMainWindow clientWindow) {
		channels = numChannels;
		this.clientWindow = clientWindow;
	}

	public int getFrequency() {
		return frequency;
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
			System.out.println(message);
			frequency = Integer.parseInt(message.split("frequency=")[1].split(";")[0]);
			clientWindow.setFrequency(frequency);
			System.out.println("frequency is " + frequency);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * The number received in the form channelID_id=value; is 
	 * split and added to channeletails.
	 */
	private void receiveNumbers() {
		while (true) {
			InputStream inputStream;
			try {
				inputStream = socket.getInputStream();
				InputStreamReader reader = new InputStreamReader(inputStream);
				BufferedReader bufferReader = new BufferedReader(reader);
				String message = bufferReader.readLine();
				System.out.println(message);
				int channelId = Integer.parseInt(message.split("channelID_")[1].split("=")[0]);
				int channelValue = Integer.parseInt(message.split("channelID_")[1].split("=")[1].split(";")[0]);
				Channel channelDetails = new Channel(channelId, channelValue);
				UpdateClientWindow(channelDetails);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * Terminates the connection with server.
	 */
	public void closeConnection() {
		try {
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Has calls to receive frequency,send channels and receive numbers.
	 */
	@Override
	public void run() {
		try {
			socket = new Socket(ipAddress, port);
			receiveFrequency();
			sendNumberOfChannels();
			receiveNumbers();

		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}
	
	/**
	 * 
	 * @param channelDetails - The channel id and value 
	 * to update client window.
	 * 
	 */
	public void UpdateClientWindow(Channel channelDetails) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				NumberStatistics.ComputeNumberStatistics(channelDetails);
				clientWindow.refreshWindow();
			}
		});
	}
}