import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.*;

import com.toedter.calendar.JDateChooser;


public class VendorDetails extends JFrame {

	private JPanel contentPane, panel;
	private JTextField vName;
	private JTable table;
	private JScrollPane scrollPane;

	private JLabel vname,phno,add,gstno, datel,timel,paymtype, lblDue;
	

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VendorDetails frame = new VendorDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connCustomer=null;
	/**
	 * Create the frame.
	 */
	private String str;
	private JTable table_1;
	 private void printCard(){

		 PrinterJob printjob = PrinterJob.getPrinterJob();
		 printjob.setJobName(" Bill Print ");

		 printjob.setPrintable (new Printable() {      
		      public int print(Graphics pg, PageFormat pf, int pageNum){                  

		          pf.setOrientation(PageFormat.LANDSCAPE);

		          if (pageNum > 0){
		             return Printable.NO_SUCH_PAGE;
		          }

		          Graphics2D g2 = (Graphics2D) pg;
		          g2.translate(pf.getImageableX(), pf.getImageableY());
		          g2.translate( 0f, 0f );
		          panel.print(g2);

		          return Printable.PAGE_EXISTS;
		      }
		 });

		 if (printjob.printDialog() == false)
		    return;

		 try {
		    printjob.print();
		 }
		 catch (PrinterException ex) {
		    System.out.println("NO PAGE FOUND."+ex);
		    }
		 }
	
	public void showTable()
	{
		try
		{
			String qry= "SELECT * FROM venproFinal WHERE Serial_No=?";
			PreparedStatement pst2= connCustomer.prepareStatement(qry);
			pst2.setString(1,  str);
			
			ResultSet rs2= pst2.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs2));
		}
		catch(Exception x)
		{
			JOptionPane.showMessageDialog(null, x);
		}
	}
	public void showTable1()
	{
		try
		{
			String qry= "SELECT * FROM venproFinal WHERE Serial_No=?";
			PreparedStatement pst2= connCustomer.prepareStatement(qry);
			pst2.setString(1, vName.getText());
			
			ResultSet rs2= pst2.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs2));
		}
		catch(Exception x)
		{
			JOptionPane.showMessageDialog(null, x);
		}
	}
	public void showData()
	{
		try
		{
			String a,b,c;
			
			String querry="SELECT VendorName,Serial_No,PayType,time,date FROM venproFinal WHERE  Serial_No=? OR VendorName=?";
			PreparedStatement pst= connCustomer.prepareStatement(querry);
			pst.setString(1, vName.getText());
			pst.setString(2, vName.getText());
			
		
			
			ResultSet rs= pst.executeQuery();
			
			if(rs.next())
			
			{
			//	JOptionPane.showMessageDialog(null, "data found!! :o ");
				a=vName.getText();
				b=rs.getString("Serial_No");
				c=rs.getString("VendorName");
			
			
				//JOptionPane.showMessageDialog(null, a);
				//JOptionPane.showMessageDialog(null, b);
				if( new String(a).equals(b))
				{
					//setText format
					String S=rs.getString("VendorName");
					vname.setText(S);
					paymtype.setText(rs.getString("PayType"));
					timel.setText(rs.getString("time"));
					datel.setText(rs.getString("date"));
					
				try
				{
					String qry="SELECT GSTNo,PhoneNo,Address FROM Vendor WHERE VName=?";
					PreparedStatement pst1= connCustomer.prepareStatement(qry);
					pst1.setString(1, S);
					ResultSet rs2= pst1.executeQuery();
					while(rs2.next())
					{
						gstno.setText(rs2.getString("GSTNo"));
						phno.setText(rs2.getString("PhoneNo"));
						add.setText(rs2.getString("Address"));
						/////////////////////
						
						
						try
						{
							 panel.setVisible(true);
							String qrrry= "SELECT VendorName,Products,Quantity,Rate FROM venproFinal WHERE Serial_No=?";
							PreparedStatement pst2= connCustomer.prepareStatement(qrrry);
							pst2.setString(1, vName.getText() );
							ResultSet rs3= pst2.executeQuery();
							showTable1();
							while(rs3.next())
							{
								String qy= "SELECT SUM(Credit) FROM venproFinal WHERE VendorName=?";
								PreparedStatement pst9= connCustomer.prepareStatement(qy);
								pst9.setString(1, S);
								ResultSet rs9= pst9.executeQuery();
								lblDue.setText(rs9.getString("SUM(Credit)"));
							}
							
							
							panel.setVisible(true);
						}
						catch(Exception x)
						{
							JOptionPane.showMessageDialog(null, x);
						}
						/////////////////////
						
					}
				}
				catch(Exception xcp)
				{
					JOptionPane.showMessageDialog(null, xcp);
				}
				
				
				
				// show the temp two table in table
				    
				//show the Temp2 table 
				}
				else if( new String(a).equals(c))
				{
					try
					{
						 //panel.setVisible(true);
						String qy= "SELECT * FROM venproFinal WHERE VendorName=?";
						PreparedStatement pst3= connCustomer.prepareStatement(qy);
						pst3.setString(1, vName.getText() );
						ResultSet rs4= pst3.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs4));
						 table.getColumnModel().getColumn(10).setCellRenderer(new ButtonRenderer());;
						  table.getColumnModel().getColumn(10).setCellEditor(new ButtonEditor(new JTextField()));
						panel.setVisible(true);
						
					}
					catch(Exception x)
					{
						JOptionPane.showMessageDialog(null, "error");
					}
				}
				
				
			}
			
			else
			{
				JOptionPane.showMessageDialog(null, "Data Not found!");
			}
			
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(null, exc);
		}
	}
	public void getData(String st)
	{
		vName.setText(st);
	}
	public VendorDetails() {
		connCustomer= SqlConnection.customerConnection();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 878, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setVisible(false);
		panel.setBounds(12, 69, 852, 329);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblVenderName = new JLabel("Vender Name:");
		lblVenderName.setBounds(12, 12, 107, 15);
		panel.add(lblVenderName);
		
		JLabel lblNewLabel = new JLabel("Address:");
		lblNewLabel.setBounds(12, 39, 70, 15);
		panel.add(lblNewLabel);
		
		vname = new JLabel("New label");
		vname.setFont(new Font("Dialog", Font.BOLD, 14));
		vname.setBounds(131, 12, 208, 15);
		panel.add(vname);
		
		 add = new JLabel("New label");
		add.setFont(new Font("Dialog", Font.BOLD, 11));
		add.setBounds(131, 39, 672, 15);
		panel.add(add);
		
		JLabel lblGstNo = new JLabel("GST No.");
		lblGstNo.setBounds(12, 66, 70, 15);
		panel.add(lblGstNo);
		
		 gstno = new JLabel("New label");
		gstno.setBounds(128, 66, 195, 23);
		panel.add(gstno);
		
		JLabel lblPhoneNo = new JLabel("Phone No.");
		lblPhoneNo.setBounds(346, 12, 96, 15);
		panel.add(lblPhoneNo);
		
		 phno = new JLabel("New label");
		phno.setBounds(450, 12, 156, 15);
		panel.add(phno);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 128, 791, 170);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblVendorName = new JLabel("Vendor Name");
		lblVendorName.setBounds(24, 33, 95, 15);
		contentPane.add(lblVendorName);
		
		vName = new JTextField();
		vName.setBounds(129, 31, 144, 26);
		contentPane.add(vName);
		vName.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{ 
				showData();
				
			
			}
		});
		btnSearch.setBounds(280, 30, 83, 26);
		contentPane.add(btnSearch);
		
		JLabel lblPaymentType = new JLabel("Payment Type:");
		lblPaymentType.setBounds(12, 101, 107, 15);
		panel.add(lblPaymentType);
		
		paymtype = new JLabel("New label");
		paymtype.setBounds(131, 101, 70, 15);
		panel.add(paymtype);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(346, 66, 49, 15);
		panel.add(lblDate);
		
		 datel = new JLabel("New label");
		datel.setBounds(450, 66, 114, 15);
		panel.add(datel);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(346, 101, 49, 15);
		panel.add(lblTime);
		
		timel = new JLabel("New label");
		timel.setBounds(450, 101, 114, 15);
		panel.add(timel);
		
		lblDue = new JLabel("Due Ammount");
		lblDue.setForeground(Color.RED);
		lblDue.setBounds(105, 302, 107, 15);
		panel.add(lblDue);
		
		JLabel lblDue_1 = new JLabel("Total Due:");
		lblDue_1.setBounds(12, 302, 81, 15);
		panel.add(lblDue_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 128, 791, 170);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				try {
					connCustomer.close();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(70, 425, 83, 25);
		contentPane.add(btnNewButton);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				printCard();
			}
		});
		btnPrint.setBounds(407, 425, 68, 25);
		contentPane.add(btnPrint);
		
		JLabel lblNewLabel_1 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("vendet1.jpg")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img));
		lblNewLabel_1.setBounds(0, 0, 876, 86);
		contentPane.add(lblNewLabel_1);
		
	}
	
	
	
	
////////////////////////////////////////////////////////////xxxxxxxxxxxxxxxxxxxxxx////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
//BUTTON RENDERER CLASS
class ButtonRenderer extends JButton implements  TableCellRenderer
{
//CONSTRUCTOR
public ButtonRenderer() {
//SET BUTTON PROPERTIES
setOpaque(true);
}
@Override
public Component getTableCellRendererComponent(JTable table, Object obj,
boolean selected, boolean focused, int row, int col) {

//SET PASSED OBJECT AS BUTTON TEXT
setText((obj==null) ? "":obj.toString());

return this;
}

}
//BUTTON EDITOR CLASS
class ButtonEditor extends DefaultCellEditor
{
protected JButton btn;

private String lbl;
private Boolean clicked;
// private JTable tbl;

public ButtonEditor(JTextField txt) {
super(txt);

btn=new JButton();
btn.setOpaque(true);

//WHEN BUTTON IS CLICKED
btn.addActionListener(new ActionListener() {

@Override
public void actionPerformed(ActionEvent e) {

fireEditingStopped();
}
});
}

//OVERRIDE A COUPLE OF METHODS
@Override
public Component getTableCellEditorComponent(JTable table1, Object obj,
boolean selected, int row, int col) {

//SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
lbl=(obj==null) ? "":obj.toString();
btn.setText(lbl);
clicked=true;
return btn;
}

//IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
@Override
public Object getCellEditorValue() {
if(clicked)
{
////////////////////////////////////////////////////////////xxxxxxxxxxxxxxx///////////////////////////
//JOptionPane.showMessageDialog(btn, lbl+" Clicked");
str=lbl;
                            
try
    {
	String querry="SELECT VendorName,Serial_No,PayType,time,date FROM venproFinal WHERE  Serial_No=?";
	PreparedStatement pst= connCustomer.prepareStatement(querry);
	pst.setString(1, str);
	ResultSet rs= pst.executeQuery();
	if(rs.next())
	{
		String S=rs.getString("VendorName");
		vname.setText(S);
		paymtype.setText(rs.getString("PayType"));
		timel.setText(rs.getString("time"));
		datel.setText(rs.getString("date"));
		//lblDue.setText(rs.getString("SUM(Credit)"));
		try
		{
			String qry="SELECT GSTNo,PhoneNo,Address FROM Vendor WHERE VName=?";
			PreparedStatement pst1= connCustomer.prepareStatement(qry);
			pst1.setString(1, S);
			ResultSet rs2= pst1.executeQuery();
			while(rs2.next())
			{
				gstno.setText(rs2.getString("GSTNo"));
				phno.setText(rs2.getString("PhoneNo"));
				add.setText(rs2.getString("Address"));
				/////////////////////
				
				
				try
				{
					
					String qrrry= "SELECT VendorName,Products,Quantity,Rate,SUM(Credit) FROM venproFinal WHERE VendorName=?";
					PreparedStatement pst2= connCustomer.prepareStatement(qrrry);
					pst2.setString(1, vName.getText() );
					ResultSet rs3= pst2.executeQuery();
					while(rs3.next())
					{
						
						lblDue.setText(rs3.getString("SUM(Credit)"));

					}
					showTable();
					//table.setModel(DbUtils.resultSetToTableModel(rs3));
					panel.setVisible(true);
					table.setVisible(false);
					scrollPane.setVisible(false);
				}
				catch(Exception x)
				{
					JOptionPane.showMessageDialog(null, "error");
				}
				/////////////////////
				
			}
		}
		catch(Exception xcp)
		{
			JOptionPane.showMessageDialog(null, xcp);
		}
	}
	
	}
catch(Exception xc)
      {
	
      }


}
//SET IT TO FALSE NOW THAT ITS CLICKED
clicked=false;
return new String(lbl);
}

@Override
public boolean stopCellEditing() {
//SET CLICKED TO FALSE FIRST
clicked=false;
return super.stopCellEditing();
}

@Override
protected void fireEditingStopped() {

super.fireEditingStopped();
}
}
}
