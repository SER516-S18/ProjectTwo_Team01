package ser516.project2.team1.client.sys;

public class NumberStatistics {
	
	private static int highestValue;
	private static int lowestValue;
	private static int averageValue;
	private static int sum;
	private static int count;
	
	public static int getHighestValue() {
		return highestValue;
	}

	public static int getLowestValue() {
		return lowestValue;
	}

	public static int getAverageValue() {
		return averageValue;
	}

	
	
	public static void ComputeNumberStatistics(Channel channelDetails)
	{
			int channelValue = channelDetails.getChannelValue();
			lowestValue = lowestValue < channelValue?lowestValue:channelValue;
			highestValue = highestValue > channelValue ? highestValue : channelValue;
			count++;
			sum+=channelValue;
			averageValue = sum/count;
			//System.out.println("highest: "+highestValue);
	}
}
