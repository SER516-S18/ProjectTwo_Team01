package ser516.project2.team1.client.sys;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.*;

/**
 * 
 * @author Shilpa Bhat
 * @author Group 1 #001 - #013
 * @since FEB 2018
 * @version 1.0
 * 
 *          Client class used to connect to server, receive numbers on given
 *          number of channels.
 */
public class Client {
	private static Socket socket;
	private final String ipAddress = "127.0.0.1";
	private final int port = 8001;
	private int channels;
	private int frequency;
	public static ArrayList<ArrayList<String>> numberList = new ArrayList<ArrayList<String>>();

	/**
	 * Constructor accepts the number of channels through which data has to be
	 * received from the server.
	 * 
	 * Create lists to store number for each channel.
	 * 
	 * @param numChannels
	 *            - number of channels to receive numbers from server
	 */
	public Client(int numChannels) {
		channels = numChannels;
		for (int channel = 0; channel < channels; channel++) {
			ArrayList<String> channelNumbers = new ArrayList<String>();
			numberList.add(channelNumbers);
		}
	}

	public int getFrequency() {
		return frequency;
	}

	/**
	 * Creates socket and opens connection with the server at ip 'ipAddress' and
	 * port number 'port'.
	 */
	public void createSocket() {
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
	 */
	private void receiveFrequency() {
		try {
			InputStream inputStream = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader bufferReader = new BufferedReader(reader);
			String message = bufferReader.readLine();
			System.out.println(message);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void sendNumberOfChannels() {
		try {

			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write("" + channels);
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void receiveNumbers()
	{
		while (true) {
			InputStream inputStream;
			try {
				inputStream = socket.getInputStream();
				InputStreamReader reader = new InputStreamReader(inputStream);
				BufferedReader bufferReader = new BufferedReader(reader);
				String message = bufferReader.readLine();
			
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
}