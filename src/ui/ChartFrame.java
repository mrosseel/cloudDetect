/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: Sep 3, 2003
 * Time: 11:25:26 PM
 * To change this template use Options | File Templates.
 */
package ui;

import org.jCharts.chartData.ChartDataException;
import org.jCharts.chartData.PieChartDataSet;
import org.jCharts.properties.PropertyException;
import org.jCharts.properties.PieChart2DProperties;
import org.jCharts.properties.LegendProperties;
import org.jCharts.properties.ChartProperties;
import org.jCharts.nonAxisChart.PieChart2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowEvent;

public class ChartFrame extends JFrame {

      private JPanel panel;
    private PieChart2DProperties pieChart2DProperties;
    private LegendProperties legendProperties;
    private ChartProperties chartProperties;

    public ChartFrame() throws org.jCharts.chartData.ChartDataException, org.jCharts.properties.PropertyException
      {
        initComponents();
      }


      private void initComponents() throws org.jCharts.chartData.ChartDataException,org.jCharts.properties.PropertyException
      {
        this.setSize( 500, 500 );
        this.panel = new JPanel( true );
        this.panel.setSize( 500, 500 );
        this.getContentPane().add( this.panel );

        this.pieChart2DProperties = new org.jCharts.properties.PieChart2DProperties();
        this.legendProperties= new org.jCharts.properties.LegendProperties();
        this.chartProperties= new org.jCharts.properties.ChartProperties();

        this.setVisible( true );


        addWindowListener( new java.awt.event.WindowAdapter()
        {
          public void windowClosing( WindowEvent windowEvent )
          {
            exitForm( windowEvent );
          }
        }
        );
      }


      /************************************************************************
        *
        * @param graphics
        ***********************************************************************/
      public void paint( Graphics graphics )
      {
        try {
          String[] labels = {"BMW", "Audi", "Lexus"};
          String title = "Cars that Own";
          Paint[] paints = {Color.blue, Color.gray, Color.red};
          double[] data = {50d, 30d, 20d};
          PieChartDataSet pieChartDataSet = new PieChartDataSet( title, data,
                                   labels, paints, this.pieChart2DProperties );
          Dimension dimension= this.panel.getSize();
          PieChart2D pieChart2D = new PieChart2D( pieChartDataSet,
                                                  this.legendProperties,
                                                  this.chartProperties,
                                                  (int) dimension.getWidth(),
                                                  (int) dimension.getHeight() );

           //***** BEGIN SWING SPECIFIC CODE *******************************
           pieChart2D.setGraphics2D( (Graphics2D) this.panel.getGraphics() );
           pieChart2D.render();
           //***** END SWING SPECIFIC CODE *********************************
        }
        catch( ChartDataException chartDataException ) {
          chartDataException.printStackTrace();
        }
        catch( PropertyException propertyException ) {
          propertyException.printStackTrace();
        }
      }


      private void exitForm( WindowEvent windowEvent )
      {
        System.exit( 0 );
      }


      public static void main( String args[] ) throws ChartDataException,
                                                            PropertyException
      {
        new ChartFrame();
      }

}
