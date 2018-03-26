package client.gui;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import client.sys.Channel;
import client.sys.DisplayThread;

/*This panel represents the graph panel
 * It contains the graph, dataset and other chart related parameters
 * 
 */

public class DisplayGraph extends JPanel {
  public JFreeChart graph;
  public int channel;
  public TimeSeriesCollection dataset;
  public TimeSeries series;
  public TimeSeries graphSeries[];
  public ChartPanel chartPanel;
  ArrayList<Integer> time = new ArrayList<Integer>();
  public Channel channelDetails;
  Thread dt;

  public DisplayGraph(int channels) {
    super();
    channel = channels;
    graphSeries = new TimeSeries[channel];
    System.out.println("The no of channels is: " + channels);
    dataset = new TimeSeriesCollection();
    for (int i = 0; i < channel; i++) {
      int channelno = i + 1;
      graphSeries[i] = new TimeSeries("Channel" + channelno, Millisecond.class);
      dataset.addSeries(graphSeries[i]);
    }
    graph = createChart(dataset);

  }

  /*
   * This function creates the chart with necessary parameters.
   */
  private JFreeChart createChart(final XYDataset dataset) {
    final JFreeChart result = ChartFactory.createTimeSeriesChart("Display", "Time", "Value", dataset, true, true,
        false);
    final XYPlot plot = result.getXYPlot();
    ValueAxis axis = plot.getDomainAxis();
    axis.setAutoRange(true);
    axis.setFixedAutoRange(60000.0);
    axis = plot.getRangeAxis();
    axis.setRange(0.0, 2500.0);
    return result;
  }

  /*
   * This method starts the thread each time a value is received
   */

  public void updateGraph(int channel, Channel channelDetails) {
    this.channel = channel;
    this.channelDetails = channelDetails;
    if (dt == null) {
      dt = new Thread(new DisplayThread(this));
      dt.start();
    }

  }
}