import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class Statistic extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statistic frame = new Statistic();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connCustomer= null;
	/**
	 * Create the frame.
	 */
	  
	private void setString(String a)
	{
		lblNewLabel_2.setText(a);
	}
	public Statistic() {
		connCustomer= SqlConnection.customerConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("GRAPH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					
					String Query="Select Date,TotalAmt FROM CustomerFinal";
					JDBCCategoryDataset dataset = new JDBCCategoryDataset(SqlConnection.customerConnection(), Query);	
					JOptionPane.showMessageDialog(null, "come here");
				    JFreeChart chart = ChartFactory.createLineChart("Query Chart","Date", "TotalAmt", dataset, PlotOrientation.VERTICAL, false, true, true);
				    BarRenderer  renderer =null;
				    CategoryPlot plot=null;
				    renderer = new BarRenderer();
				    ChartFrame frame= new ChartFrame("Query Chart",chart);
				    frame.setVisible(true);
				    frame.setSize(400,650);
				}
				catch(Exception xc)
				{JOptionPane.showMessageDialog(null, xc);
					
				}
			}
		});
		btnNewButton.setBounds(316, 273, 117, 25);
		contentPane.add(btnNewButton);
		
		
		
	    
	   
	}
}
