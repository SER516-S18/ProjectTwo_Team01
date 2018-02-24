package client.sys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import javax.swing.SwingUtilities;
import client.gui.ClientMainWindow;
import commons.ConsolePanel;

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
	private final String ipAddress = "10.140.202.158";
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
				e.printStackTrace();
			}

		}
	}

	/**
	 * Terminates the connection with server.
	 */
	public void closeConnection() {
		try {
			setConsoleInfo("Client closing connection with server ...");
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
			setConsoleInfo("Client connected to " + socket.getLocalAddress().getHostName());
			receiveFrequency();
			sendNumberOfChannels();
			receiveNumbers();

		} catch (IOException exception) {
			exception.printStackTrace();
		}

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
