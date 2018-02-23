package ser516.project2.team1.client.sys;

import java.util.*;

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

	
	
	public static void ComputeNumberStatistics(ArrayList<Integer> numbersList)
	{
		for(int number : numbersList)
		{
			lowestValue = lowestValue < number?lowestValue:number;
			highestValue = highestValue > number ? highestValue : number;
			count++;
			sum+=number;
		}
		averageValue = sum/count;
	}
}
