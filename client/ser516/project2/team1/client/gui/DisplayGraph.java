package ser516.project2.team1.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class DisplayGraph {
	JFreeChart displayGraph;

	public DisplayGraph() {
		this.displayGraph = ChartFactory.createLineChart("Display", "Number", "Value", createDataset(),
				PlotOrientation.VERTICAL, true, true, false);
	}

	private DefaultCategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(15, "values", "1");
		dataset.addValue(30, "values", "2");
		dataset.addValue(60, "values", "3");
		dataset.addValue(120, "values", "4");
		dataset.addValue(240, "values", "5");
		dataset.addValue(300, "values", "6");
		return dataset;
	}
}
