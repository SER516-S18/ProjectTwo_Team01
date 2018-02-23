package ser516.project2.team1.client.sys;

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
