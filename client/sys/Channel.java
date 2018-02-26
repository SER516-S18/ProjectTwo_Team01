package client.sys;

/**
 *  This class is used to keep track and control of Channel object
 *  @author Group 1 #001 - #013
 *  @since FEB 2018
 *  @version 1.0
 */
public class Channel {
	private int channelId;
	public int getChannelId() {
		return channelId;
	}

	public int getChannelValue() {
		return channelValue;
	}

	private int channelValue;

	public Channel(int id, int value) {
		channelId=id;
		channelValue=value;
	}
}
