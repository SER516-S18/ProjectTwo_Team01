package ser516.project2.team1.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.SeriesChangeEvent;
import org.jfree.data.general.SeriesChangeListener;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DisplayGraph extends JPanel {
	JFreeChart displayGraph;
	int channel;
	TimeSeriesCollection dataset ;
	public TimeSeries series;
	ArrayList<TimeSeries> seriesCollection;

	ArrayList<XYSeries> seriesList = new ArrayList<XYSeries>();
	ChartPanel chartPanel ;
	List<Integer>time= new ArrayList<Integer>();
	ser516.project2.team1.client.sys.Channel channelDetails;
	static Counter c;
	

	public DisplayGraph() {
		super();
		dataset= new TimeSeriesCollection();
		series = new TimeSeries("series",Millisecond.class);
	//	seriesCollection.add(series);
		dataset.addSeries(series);
		displayGraph= createChart(dataset);

	//	this.displayGraph = ChartFactory.createXYLineChart("Display", "Number", "Value", dataset,
	//			PlotOrientation.VERTICAL, true, true, false);
		//chartPanel= new ChartPanel(this.displayGraph);
	//	this.add(chartPanel);
		
		
		

	}
	private JFreeChart createChart(final XYDataset dataset) {
		final JFreeChart result = ChartFactory.createTimeSeriesChart(
				"Display", 
				"Time", 
				"Value",
				dataset, 
				true, 
				true, 
				false
				);
		final XYPlot plot = result.getXYPlot();
		ValueAxis axis = plot.getDomainAxis();
		axis.setAutoRange(true);
		axis.setFixedAutoRange(60000.0);  // 60 seconds
		axis = plot.getRangeAxis();
		axis.setRange(0.0,2500.0); 
		return result;
	}


	public void updateGraph(int channel,ser516.project2.team1.client.sys.Channel channelDetails) {

		this.channel=channel;

		 createDataset(channelDetails);
		System.out.print("Hi in update");

		 if (c == null) {
			 c = new Counter();
			 new Thread(c).start();
		 }

	}
	

	private void createDataset(ser516.project2.team1.client.sys.Channel channelDetails) {
		this.channelDetails=channelDetails;
/*		XYSeries series1 = new XYSeries("Channel 1");
		XYSeries series2 = new XYSeries("Chanel2");
		series1.add(2,3);
		series1.add(6,9);
		series2.add(2,9);
		series2.add(3, 6);
		dataset.addSeries(series1);
		dataset.addSeries(series2);*/
//		
//		if(dataset.getSeriesCount()==0)
//		{
//			XYSeries series= new XYSeries("Channel"+channelDetails.getChannelId());
//			series.add(1,channelDetails.getChannelValue());
//			dataset.addSeries(series);
//		}
		//new Thread(new Counter()).start();

				

	/*		
			XYSeries series= new XYSeries("Channel"+channel);
			
			series.add(1,2);
			series.add(3,5);
			series.add(6,5);
			dataset.addSeries(series);

			seriesList.add(series);
		
		seriesList.get(0).add(2,3);
		seriesList.get(1).add(6,7);
		
		for(XYSeries series:seriesList)
		{
			dataset.addSeries(series);
		}
			*/


	}


/*	@Override
	public void seriesChanged(SeriesChangeEvent arg0) {
		// TODO Auto-generated method stub
		int count= dataset.getSeriesCount();
		for(int i=0;i<count;i++)
		{
			//XYSeries series= dataset.getSeries(i);
			//series.add(3,channelDetails.getChannelValue());
		}
		
		
	}*/
	public class Counter implements Runnable {

		@Override
		public void run() {
			while (true) {
				final double factor = 0.90 + 0.2 * Math.random();
				//int i = channelDetails.getChannelValue();

				//lastValue = lastValue * factor;
				final Millisecond now = new Millisecond();
				System.out.println("Now = " + now.toString());
				series.add(new Millisecond(), channelDetails.getChannelValue());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}

