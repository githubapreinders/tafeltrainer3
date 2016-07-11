package afr.tafeltrainer3.widgets;

import afr.tafeltrainer3.main.tafeltrainer3messages;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.Gauge;
 
public class GaugeWidget extends Composite 
{
HorizontalPanel hpanel  = new HorizontalPanel();
Gauge.Options options;

	public GaugeWidget(final int tablenumber, final int percentage,tafeltrainer3messages messages)
		{
			final String tableof = messages.gaugewidget_tableof();
			initWidget(hpanel);
			Runnable onLoadCallback = new Runnable()
			{
				public void run()
				{
					Gauge gauge = new Gauge();
					options = Gauge.Options.create();
					options.setWidth(150);
					options.setHeight(150);
					options.setGaugeRange(0, 100);
					options.setMajorTicks("0%","50%","100%");
					options.setMinorTicks(10);
					options.setGreenRange(90, 100);
					options.setYellowRange(70, 90);
					options.setRedRange(0, 70);
					gauge.draw(createGauge(), options);
					hpanel.add(gauge);
				}
				private AbstractDataTable createGauge() {
					DataTable data = DataTable.create();
					data.addColumn(ColumnType.NUMBER, tableof + " " + String.valueOf(tablenumber));
					data.addRows(1);
					data.setValue(0, 0, percentage);
					return data;
				}
			};
		VisualizationUtils.loadVisualizationApi(onLoadCallback, Gauge.PACKAGE);
		}

	public GaugeWidget( final int percentage,tafeltrainer3messages messages)
	{
		final String score = messages.gaugewidget_score(); 
		initWidget(hpanel);
		Runnable onLoadCallback = new Runnable()
		{
			public void run()
			{
				Gauge gauge = new Gauge();
				options = Gauge.Options.create();
				options.setWidth(150);
				options.setHeight(150);
				options.setGaugeRange(0, 100);
				options.setMajorTicks("0%","50%","100%");
				options.setMinorTicks(10);
				options.setGreenRange(90, 100);
				options.setYellowRange(70, 90);
				options.setRedRange(0, 70);
				gauge.draw(createGauge(), options);
				hpanel.add(gauge);
			}
			private AbstractDataTable createGauge() {
				DataTable data = DataTable.create();
				data.addColumn(ColumnType.NUMBER,score);
				data.addRows(1);
				data.setValue(0, 0, percentage);
				return data;
			}
		};
	VisualizationUtils.loadVisualizationApi(onLoadCallback, Gauge.PACKAGE);
	}

	
	
}
