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
import org.jfree.data.xy.XYSeries;

import client.sys.Channel;

/*This panel represents the graph panel
 * It contains the graph, dataset and other chart related parameters
 * 
 */

public class DisplayGraph extends JPanel {
  static final int TIMING = 1000;
  JFreeChart displayGraph;
  int channel;
  TimeSeriesCollection dataset;
  public TimeSeries series;
  ArrayList<TimeSeries> seriesCollection;

  ArrayList<XYSeries> seriesList = new ArrayList<XYSeries>();
  ChartPanel chartPanel;
  ArrayList<Integer> time = new ArrayList<Integer>();
  client.sys.Channel channelDetails;
  static DisplayThread c;

  public DisplayGraph() {
    super();
    dataset = new TimeSeriesCollection();
    series = new TimeSeries("Channel1", Millisecond.class);
    dataset.addSeries(series);
    displayGraph = createChart(dataset);
  }

  /*
   * This function creates the chart with necessary parameters.
   */
  private JFreeChart createChart(final XYDataset dataset) {
    final JFreeChart result = ChartFactory.createTimeSeriesChart("Display", "Time", "Value", dataset,
        true, true, false);
    final XYPlot plot = result.getXYPlot();
    ValueAxis axis = plot.getDomainAxis();
    axis.setAutoRange(true);
    axis.setFixedAutoRange(60000.0);
    axis = plot.getRangeAxis();
    axis.setRange(0.0, 2500.0);
    return result;
  }

  /**
   * This method starts the thread each time a value is received
   */
  public void updateGraph(int channel, Channel channelDetails) {
    this.channel = channel;
    this.channelDetails = channelDetails;
    if (c == null) {
      c = new DisplayThread();
      new Thread(c).start();
    }
  }

  /**
   * This thread adds each value to the graph dynamically.
   */
  public class DisplayThread implements Runnable {
    @Override
    public void run() {
      while (true) {
        series.add(new Millisecond(), channelDetails.getChannelValue());
        try {
          Thread.sleep(TIMING);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      // System.out.println("Stopped plotting...");
    }
  }
}
