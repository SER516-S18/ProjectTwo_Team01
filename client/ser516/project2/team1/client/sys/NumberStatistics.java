package ser516.project2.team1.client.sys;

/**
 * Receives the channel id and channel value,
 * computes the max , min and average number received so far.
 * @author Shilpa Bhat
 * @author Group 1 #001 - #013
 * @since FEB 2018
 * @version 1.0
 */
public class NumberStatistics {
	
	private static int highestValue;
	private static int lowestValue=Integer.MAX_VALUE;
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

	/**
	 * 
	 * @param channelDetails - Has the channel number and channel value
	 * to compute max,min and average values.
	 */
	public static void ComputeNumberStatistics(Channel channelDetails)
	{
			int channelValue = channelDetails.getChannelValue();
			lowestValue = lowestValue < channelValue?lowestValue:channelValue;
			highestValue = highestValue > channelValue ? highestValue : channelValue;
			count++;
			sum+=channelValue;
			averageValue = sum/count;
	}
}
