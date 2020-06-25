import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.Color;


public class Billing extends JFrame {

	private JPanel contentPane,panel;
	private JTextField CName;
	private JTextField CAddress;
	private JCheckBox chBoxCredit;
	private JTextField QuantText;
	private JTextField RateText;
	private JTextField PhNo;
    private JComboBox comboBox;
    private JPanel metpanel,ePayPanel,creditPanel,pvusCustoPanel,fillCusto,printbillPanel,chqPanel;
    private JLabel lblNo,splbl,creditlbl;
    private String creditval;
    private float ii;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Billing frame = new Billing();
					frame.setVisible(true);
				    } 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
    Connection connStock= null;
    Connection connCustomer= null;
    private String PayType,tot;
    
    private String CustomerID,Ammount,GSTamt,TotalAmt;
    private int SlNo;
    private JTextField gstField;
    private JTextField gstNoField;
    private JTextField panNoField;
    private JTable table;
    private JTextField AdharField;
    private JTextField pvsBillID;
    private JTextField payment;
    private JTextField EmailID;
    private JTextField identifier;
    private JTextField chqno;
    private JPanel panel_1;
    private  JLabel CId,label;
    private JCheckBox chckbxNewCheckBox,chBoxCard,chBoxNEFT,chBoxCash;
    
	/**
	 * Create the frame.
	 */
     
	 private void fillCombo()
	 {
		 try
			{
				String querry="select Materials from StockFinal";
				PreparedStatement pst= connStock.prepareStatement(querry);
				ResultSet rs= pst.executeQuery();
				//JOptionPane.showMessageDialog(null, "ADD");
				while(rs.next())
				    {
					
					String mat = rs.getString("Materials");
					comboBox.addItem(mat);
					//JOptionPane.showMessageDialog(null, "ADD");
				    }
				
				
			}
			catch(Exception exc)
			{
				
				JOptionPane.showMessageDialog(null, exc);
			}
	 }
	 private void unitleft()
	 {
		 try
			{
			 
				String qry="select Quantity,Selling_Price from StockFinal WHERE Materials=?";
				PreparedStatement pst6= connStock.prepareStatement(qry);
				pst6.setString(1, (String) comboBox.getSelectedItem());
				ResultSet rs6= pst6.executeQuery();
				//JOptionPane.showMessageDialog(null, (String) stockCombo.getSelectedItem());
				while(rs6.next())
				    {
					
					//JOptionPane.showMessageDialog(null, "ADD");
					lblNo.setText(rs6.getString("Quantity"));
					splbl.setText(rs6.getString("Selling_Price"));
				    }
				
				
			}
			catch(Exception exc)
			{
				
				JOptionPane.showMessageDialog(null, exc);
			}
	 }
	 private void credit()
	 {
		 try{
		 String qry="select SUM(Credit) from CustomerFinal WHERE CNamw=? OR Identifier=?";
			PreparedStatement pst6= connCustomer.prepareStatement(qry);
			pst6.setString(1, pvsBillID.getText());
			pst6.setString(2, pvsBillID.getText());
			ResultSet rs6= pst6.executeQuery();
			while(rs6.next())
			{
				creditval=rs6.getString("SUM(Credit)");
			}
			
		 }
		 catch(Exception x)
		 {
			 JOptionPane.showMessageDialog(null, x);
		 }
	 }
	 private void deltable()
		{
		 
	     connCustomer= SqlConnection.customerConnection();
		try
		{
			String querry = "Delete from CustomerFinal where CNamw='"+CName.getText()+"' AND CustomerID='"+CId.getText()+"'";
		PreparedStatement pst= connCustomer.prepareStatement(querry);
		
		pst.execute();
		pst.close();
		//JOptionPane.showMessageDialog(null, "Data Deleted...!!!");
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(null, "error in delete customerFinal");
			JOptionPane.showMessageDialog(null, exc);
		}
		//temp table delete 
		try
		{
		String querry="delete from Temp";
		PreparedStatement pst1= connCustomer.prepareStatement(querry);
		//int rs= pst1.executeUpdate();
		pst1.execute();
		pst1.close();
		
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "error in delete customerFinal");
			JOptionPane.showMessageDialog(null, ex);
		}
		}
	 private void subUnit()
	 {
		 try
		 {
			 String query = "select Quantity from StockFinal WHERE Materials=?";
			 PreparedStatement pst = connStock.prepareStatement(query);
			 pst.setString(1, (String)comboBox.getSelectedItem());
			 ResultSet rs = pst.executeQuery();
			 while (rs.next()) 
			    {   String s=QuantText.getText();
		            int a= Integer.parseInt(s);
		           String q= rs.getString("Quantity");
		           int b= Integer.parseInt(q);
		           int c=b-a;
		           
		          // JOptionPane.showMessageDialog(null, c);
		          // JOptionPane.showMessageDialog(null, b);
		          // JOptionPane.showMessageDialog(null, a);
		           ///////
		           try
					{
						
					String querry = "Update StockFinal set Quantity='"+c+"' where Materials='"+(String)comboBox.getSelectedItem()+"' ";
					PreparedStatement pst3= connStock.prepareStatement(querry);
					
					
					pst3.execute();
					
				//	JOptionPane.showMessageDialog(null, "Data Update...!!!");
					pst3.close();
					}
					catch(Exception exc)
					{
						JOptionPane.showMessageDialog(null, exc);
					}
		           ///////
		        }
			
			 
			 
		 }
		 catch(Exception exc)
		 {
			 JOptionPane.showMessageDialog(null, exc); 
		 }
	 }
	 private String sum()
	 {
		 
		 float qty = Float.parseFloat(QuantText.getText());
		 
		 float rte = Float.parseFloat(RateText.getText());
		 
		 float gstp = Float.parseFloat(gstField.getText());
		 float a,b;
		 double pro;
		 a=(qty*rte);
	     b=(a*gstp)/100;
	     pro=a+b;
	    
		
		 
		 Ammount=String.valueOf(pro);
		 return Ammount;
	 }
	 private String total()
	 {
		 
		 float qty = Float.parseFloat(QuantText.getText());
		 
		 float rte = Float.parseFloat(RateText.getText());
		 
		
		 
		 double pro;
		 pro=(qty*rte);
		 
		tot=String.valueOf(pro);
		 return tot;
	 }
	
	
	 
	 private String gst()
	 {
		 
		 float qty = Float.parseFloat(QuantText.getText());
		 
		 float rte = Float.parseFloat(RateText.getText());
		 
		 float gstp = Float.parseFloat(gstField.getText());
		 float a,b;
		 a=(qty*rte);
	     b=(a*gstp)/100;
	     
	     GSTamt=String.valueOf(b/2);
		 return GSTamt;
	 }
	 private void showTable()
	 {
		 try
	       {
  	 
			String querry="select Products, Quantity, Rate, GST, Ammount,SGST,CGST from Temp";
			//String querry="select* from Temp";
			PreparedStatement pst1= connCustomer.prepareStatement(querry);
			ResultSet rs1= pst1.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs1));
		   }
		catch(Exception ex)
		   {
			JOptionPane.showMessageDialog(null, ex);
		   }
	 }
	 
	 private String idGen()
	 {
		 try
		 {
			 String query = "select SlNo from CustomerFinal order by SlNo";
			 PreparedStatement pst = connCustomer.prepareStatement(query);
			 ResultSet rs = pst.executeQuery();
			 while (rs.next()) 
			    {
		            SlNo = rs.getInt(1);
		           int inc = SlNo + 1;
		            CustomerID= PayType+inc; 
		            SlNo++;
		        }
			
			 
			 
		 }
		 catch(Exception exc)
		 {
			 JOptionPane.showMessageDialog(null, exc); 
		 }
		return CustomerID;
	 }
	 
	 
	public Billing()
	{
		setResizable(false);
		setBackground(new Color(255, 228, 181));
		
		
		connStock= SqlConnection.stockConnection();
		connCustomer= SqlConnection.customerConnection();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 858, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ButtonGroup pay= new ButtonGroup();
		pay.add(chBoxCard);
		pay.add(chBoxNEFT);
		pay.add(chBoxCash);
		pay.add(chckbxNewCheckBox);
		pay.add(chBoxCredit);
		
		 pay.clearSelection();
	        
	        
		chBoxCash = new JCheckBox("Cash");
		
		chBoxCash.setOpaque(false);
		chBoxCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				metpanel.setVisible(true);
				PayType="CASH";
				ePayPanel.setVisible(true);
				idGen();
				
				//showtable
				showTable();
				panel.setVisible(true);
				CId.setText(CustomerID);
			}
			
		});
		chBoxCash.setBounds(22, 35, 60, 28);
		contentPane.add(chBoxCash);
		
		chBoxCredit = new JCheckBox("Credit");
		chBoxCredit.setOpaque(false);
		chBoxCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				metpanel.setVisible(true);
				PayType="CREDIT";
				creditPanel.setVisible(true);
				idGen();
				showTable();
				panel.setVisible(true);
				CId.setText(CustomerID);
				
			}
		});
		chBoxCredit.setBounds(22, 63, 68, 23);
		contentPane.add(chBoxCredit);
		
		JLabel lblCustomerId = new JLabel("Customer payment Type");
		lblCustomerId.setForeground(Color.WHITE);
		lblCustomerId.setBounds(12, 9, 193, 15);
		contentPane.add(lblCustomerId);
		
		metpanel = new JPanel();
		metpanel.setOpaque(false);
		metpanel.setVisible(false);
		metpanel.setBounds(12, 192, 832, 242);
		contentPane.add(metpanel);
		metpanel.setLayout(null);
		
		JLabel lblSelectItem = new JLabel("Materials");
		lblSelectItem.setBounds(69, 11, 112, 15);
		metpanel.add(lblSelectItem);
		
		JLabel lblQuantityweight = new JLabel("Quantity/Weight");
		lblQuantityweight.setBounds(254, 11, 136, 15);
		metpanel.add(lblQuantityweight);
		
		QuantText = new JTextField();
		QuantText.setOpaque(false);
		QuantText.setBounds(254, 40, 136, 34);
		metpanel.add(QuantText);
		QuantText.setColumns(10);
		
		JLabel lblRate = new JLabel("Selling Price per product");
		lblRate.setFont(new Font("Dialog", Font.BOLD, 11));
		lblRate.setBounds(407, 13, 162, 15);
		metpanel.add(lblRate);
		
		RateText = new JTextField();
		RateText.setOpaque(false);
		RateText.setBounds(443, 40, 78, 34);
		metpanel.add(RateText);
		RateText.setColumns(10);
		
		JLabel lblGst = new JLabel("GST (%)");
		lblGst.setBounds(597, 12, 53, 15);
		metpanel.add(lblGst);
		
		gstField = new JTextField();
		gstField.setOpaque(false);
		gstField.setBounds(581, 40, 90, 34);
		metpanel.add(gstField);
		gstField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(713, 10, 90, 25);
		metpanel.add(btnAdd);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.setBounds(713, 36, 90, 23);
		metpanel.add(updateBtn);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//delete raow from temp table
				try
				{
					String querry ="DELETE FROM Temp WHERE Products='"+comboBox.getSelectedItem().toString()+"'";
				PreparedStatement pst= connCustomer.prepareStatement(querry);
				
				pst.execute();
				pst.close();
				JOptionPane.showMessageDialog(null, "Data Deleted...!!!");
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, exc);
				}
				showTable();
			}
		});
		btnNewButton_1.setBounds(713, 60, 90, 25);
		metpanel.add(btnNewButton_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.getViewport().setOpaque(false);
		scrollPane_1.setOpaque(false);
		scrollPane_1.setBounds(3, 97, 752, 116);
		metpanel.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				printbillPanel.setVisible(true);
				
               //Store the data to the Customer database
			//	Total();

				try
				{
					int i=0;
					// insert the value to the Customer table..
					String query="insert into CustomerFinal (CNamw,CAddress,PhNo,PayType,CustomerID,SlNo,EmailID,PanNo,GSTNo,AdharNo,Identifier,Payment,chequenumber,Credit) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
					PreparedStatement pst= connCustomer.prepareStatement(query);
					pst.setString(1, CName.getText());
					pst.setString(2, CAddress.getText());
					pst.setString(3, PhNo.getText() );
					pst.setString(4, PayType);
					pst.setString(5, CustomerID);
					pst.setInt(6, SlNo);
					pst.setString(7, EmailID.getText());
					pst.setString(8, panNoField.getText());
					pst.setString(9, gstNoField.getText());
					pst.setString(10, AdharField.getText());
					pst.setString(11, identifier.getText());
					pst.setString(12, payment.getText());
					pst.setString(13, chqno.getText());
					pst.setString(14, String.valueOf(i));
					pst.execute();
					pst.close();
					JOptionPane.showMessageDialog(null, "Data Saved");
					}
					catch(Exception exc)
					{
						JOptionPane.showMessageDialog(null, "Data Already Save! Click 'Print Bill'");
					}
				//insert into table if paytype=credit and if customer already exist then update the table................ 
				////////////////
				
				if(new String("CREDIT").equals(PayType))
				{
					try
					{
						String qrry="SELECT Name,Identifier FROM creditCustomers WHERE Name=? AND Identifier=?";
					      PreparedStatement pst= connCustomer.prepareStatement(qrry);
					      pst.setString(1, CName.getText());
					      pst.setString(2, identifier.getText());
					      ResultSet rs= pst.executeQuery();
					      if(rs.next())
					      {
					    	//update into creditCustomers is cancel btn press//////////// 
					    	  ///////////////////////problem///////////////////////////////////////////////////////////////////////////////////////

					 		 try
					    	  {
					    		  float crlbl=Float.parseFloat(creditlbl.getText());
					    		  float lbl=Float.parseFloat(label.getText());
					    		   ii=crlbl+lbl;
					    		  JOptionPane.showMessageDialog(null, ii);
					    		  ///////upade date//////
					    		  String qrr= "UPDATE creditCustomers SET Credit='"+ii+"' WHERE Identifier='"+identifier.getText()+"'";
					    		  PreparedStatement pst16= connCustomer.prepareStatement(qrr);
					 		      pst16.execute();
					 		      pst16.close();
					 		      JOptionPane.showMessageDialog(null, "execute qry ");
					    	  }

					    	  catch(Exception exc)
					    	  {
					    		  JOptionPane.showMessageDialog(null, exc);
					    	  }
					    	  
					      }
					      
					      /////////////////////////////else///////////////////////////
					      else
					      {
					    	  //insert into creditCustomer
					    	  try
								{
					    		  float crlbl=Float.parseFloat(creditlbl.getText());
					    		  float lbl=Float.parseFloat(label.getText());
					    		  float i=crlbl+lbl;
								  int j=0;
									// insert the value to the Customer table..
									String query="insert into creditCustomers (Name, Identifier,Credit, Payment, CustomerID) values(?,?,?,?,?) ";
									PreparedStatement pst18= connCustomer.prepareStatement(query);
									pst18.setString(1, CName.getText());
									pst18.setString(2, identifier.getText());
									pst18.setString(3, String.valueOf(i));
									pst18.setString(4, String.valueOf(j));
								    pst18.setString(5, CId.getText());
									pst18.execute();
									pst18.close();

									JOptionPane.showMessageDialog(null, "insert '"+CName.getText()+"' into Credit Customer's data");
									
									}
									catch(Exception exc)
									{
										JOptionPane.showMessageDialog(null, exc);
									}
					      }
					      
					}
					catch(Exception x)
					{
						JOptionPane.showMessageDialog(null, x);
					}
				}
				
			}
		});
		btnSave.setBounds(597, 213, 78, 25);
		metpanel.add(btnSave);
		
		printbillPanel = new JPanel();
		printbillPanel.setOpaque(false);
		printbillPanel.setVisible(false);
		printbillPanel.setBounds(684, 213, 136, 25);
		metpanel.add(printbillPanel);
		printbillPanel.setLayout(null);
		
		JButton btnPrintBill = new JButton("Print Bill");
		btnPrintBill.setBounds(12, 0, 112, 25);
		printbillPanel.add(btnPrintBill);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 39, 230, 35);
		metpanel.add(scrollPane);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		scrollPane.setViewportView(comboBox);
		
		JButton btnCancle = new JButton("Exit");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//delete the Temp table 
				try
				{
					connStock= SqlConnection.stockConnection();
					connCustomer= SqlConnection.customerConnection();
				String querry="delete from Temp";
				PreparedStatement pst1= connCustomer.prepareStatement(querry);
				pst1.execute();
				pst1.close();
				//delete from creditCustomers
				connCustomer.close();
				connStock.close();
				
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
				}
				// dispose the frame
				dispose();
			}
		});
		btnCancle.setBounds(162, 213, 85, 25);
		metpanel.add(btnCancle);
		
		JButton btnFakeBill = new JButton("FAKE BILL");
		btnFakeBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				FakeBill fb = new FakeBill();
				fb.setVisible(true);
			}
		});
		btnFakeBill.setBounds(361, 213, 117, 25);
		metpanel.add(btnFakeBill);
		
		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setVisible(false);
		panel_1.setBounds(12, 213, 149, 29);
		metpanel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnCancle_1 = new JButton("Cancle Bill");
		btnCancle_1.setBounds(12, 0, 112, 25);
		panel_1.add(btnCancle_1);
		
		label = new JLabel("");
		label.setBounds(764, 154, 56, 15);
		metpanel.add(label);
		btnCancle_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				connStock= SqlConnection.stockConnection();
				connCustomer= SqlConnection.customerConnection();
				//copy the Temp data to cancle bill
				try
				{
					String qry= "INSERT INTO CancelBill (Products, Quantity, Rate, GST, Ammount,SGST,CGST,CustomerID,Total) SELECT Products, Quantity, Rate, GST, Ammount, SGST, CGST, CustomerID, Total FROM Temp";
					PreparedStatement pst= connCustomer.prepareStatement(qry);
					pst.execute();
					pst.close();
					
					//JOptionPane.showMessageDialog(null, "Insert into Temp2");
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, "error in insert");
					JOptionPane.showMessageDialog(null, exc);
				}
				///////////////////////////////////////////////deduct stock add with stock/////////////////////////////////////////////////////
				//Temp data store into canclebill table 
				////////////////////////////////////////////update table///////////////////////////////////////
			   try
		    	  {
		    		  float crlbl=Float.parseFloat(creditlbl.getText());
		    		  float lbl=Float.parseFloat(label.getText());
		    		  float i=ii-lbl;
		    		 // JOptionPane.showMessageDialog(null, i);
		    		  ///////upade date//////
		    		  String qrr= "UPDATE creditCustomers SET Credit='"+i+"' WHERE Identifier='"+identifier.getText()+"'";
		    		  PreparedStatement pst16= connCustomer.prepareStatement(qrr);
		 		      pst16.execute();
		 		      pst16.close();
		 		     // JOptionPane.showMessageDialog(null, "execute qry ");
		    	  }

		    	  catch(Exception ex)
		    	  {
		    		  JOptionPane.showMessageDialog(null, "update creditCustomer");
		    		  JOptionPane.showMessageDialog(null, ex);
		    	  }
				 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				deltable();
				try
				{
				
				connCustomer.close();
				connStock.close();
				dispose();
				}
				catch(Exception xcep)
				{
					
				}
			}
		});
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

				unitleft();
				
			}
		});
		btnPrintBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				//BillPrintPrinter Page
				    
					BillPrintPrinter bP = new BillPrintPrinter();
					bP.setVisible(true);
					
					try
					{
						connCustomer.close();
						connStock.close();
					} 
					catch (SQLException e1)
					
					{
						
						e1.printStackTrace();
					}
					panel_1.setVisible(true);
			}
		});
		
		fillCusto = new JPanel();
		fillCusto.setOpaque(false);
		fillCusto.setVisible(false);
		fillCusto.setBounds(397, 0, 447, 191);
		contentPane.add(fillCusto);
		fillCusto.setLayout(null);
		
		CName = new JTextField();
		CName.setOpaque(false);
		CName.setBounds(117, 12, 113, 28);
		fillCusto.add(CName);
		CName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	
			}
		});
		CName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Customer Name");
		lblNewLabel.setBounds(0, 18, 119, 15);
		fillCusto.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblNewLabel_1 = new JLabel("Address");
		lblNewLabel_1.setBounds(0, 58, 70, 15);
		fillCusto.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(0, 96, 109, 15);
		fillCusto.add(lblPhoneNumber);
		lblPhoneNumber.setFont(new Font("Dialog", Font.BOLD, 12));
		
		CAddress = new JTextField();
		CAddress.setOpaque(false);
		CAddress.setBounds(117, 52, 113, 28);
		fillCusto.add(CAddress);
		CAddress.setColumns(10);
		
		PhNo = new JTextField();
		PhNo.setOpaque(false);
		PhNo.setBounds(117, 96, 113, 30);
		fillCusto.add(PhNo);
		PhNo.setColumns(10);
		
		JLabel lblGstNo = new JLabel("GST No.");
		lblGstNo.setBounds(248, 18, 61, 15);
		fillCusto.add(lblGstNo);
		lblGstNo.setFont(new Font("Dialog", Font.BOLD, 12));
		
		gstNoField = new JTextField();
		gstNoField.setOpaque(false);
		gstNoField.setBounds(316, 12, 106, 28);
		fillCusto.add(gstNoField);
		gstNoField.setColumns(10);
		
		JLabel lblPanNo = new JLabel("PAN No.");
		lblPanNo.setBounds(248, 58, 61, 15);
		fillCusto.add(lblPanNo);
		lblPanNo.setFont(new Font("Dialog", Font.BOLD, 12));
		
		panNoField = new JTextField();
		panNoField.setOpaque(false);
		panNoField.setBounds(316, 52, 106, 28);
		fillCusto.add(panNoField);
		panNoField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Adhar No");
		lblNewLabel_2.setBounds(242, 101, 70, 15);
		fillCusto.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 12));
		
		AdharField = new JTextField();
		AdharField.setOpaque(false);
		AdharField.setBounds(316, 96, 106, 30);
		fillCusto.add(AdharField);
		AdharField.setColumns(10);
		
		JLabel lblEmailId = new JLabel("Email ID");
		lblEmailId.setFont(new Font("Dialog", Font.BOLD, 12));
		lblEmailId.setBounds(0, 131, 70, 15);
		fillCusto.add(lblEmailId);
		
		EmailID = new JTextField();
		EmailID.setOpaque(false);
		EmailID.setBounds(117, 134, 113, 30);
		fillCusto.add(EmailID);
		EmailID.setColumns(10);
		
		identifier = new JTextField();
		identifier.setOpaque(false);
		identifier.setBounds(316, 134, 106, 29);
		fillCusto.add(identifier);
		identifier.setColumns(10);
		
		JLabel lblIdentifier = new JLabel("Identifier");
		lblIdentifier.setBounds(239, 141, 70, 15);
		fillCusto.add(lblIdentifier);
		
		 creditlbl = new JLabel("0");
		 creditlbl.setForeground(Color.RED);
		creditlbl.setBounds(182, 176, 70, 15);
		fillCusto.add(creditlbl);
		
		ePayPanel = new JPanel();
		ePayPanel.setOpaque(false);
		ePayPanel.setVisible(false);
		ePayPanel.setBounds(117, 35, 162, 23);
		contentPane.add(ePayPanel);
		ePayPanel.setLayout(null);
		
		chBoxCard = new JCheckBox("Card");
		
		chBoxCard.setOpaque(false);
		chBoxCard.setBounds(0, 0, 60, 23);
		ePayPanel.add(chBoxCard);
		
		chBoxNEFT = new JCheckBox("NEFT");
		
		chBoxNEFT.setOpaque(false);
		chBoxNEFT.setBounds(86, 0, 68, 23);
		ePayPanel.add(chBoxNEFT);
		chBoxNEFT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				metpanel.setVisible(true);
				PayType="NEFT";
				idGen();
				CId.setText(CustomerID);
			}
		});
		
		pvusCustoPanel = new JPanel();
		pvusCustoPanel.setOpaque(false);
		pvusCustoPanel.setVisible(false);
		pvusCustoPanel.setBounds(171, 138, 214, 34);
		contentPane.add(pvusCustoPanel);
		pvusCustoPanel.setLayout(null);
		
		JLabel lblEnterPriviousBill = new JLabel("Bill Number or Identifier");
		lblEnterPriviousBill.setFont(new Font("Dialog", Font.BOLD, 11));
		lblEnterPriviousBill.setBounds(12, 0, 180, 15);
		pvusCustoPanel.add(lblEnterPriviousBill);
		
		pvsBillID = new JTextField();
		pvsBillID.setOpaque(false);
		pvsBillID.setBounds(12, 15, 99, 19);
		pvusCustoPanel.add(pvsBillID);
		pvsBillID.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				credit();
				// get the data from customer table with cid or identifier
				try
				{
					String querry="select * from CustomerFinal where CustomerID=? OR Identifier=?";
					
					
					PreparedStatement pst2= connCustomer.prepareStatement(querry);
					pst2.setString(1, pvsBillID.getText());
					pst2.setString(2, pvsBillID.getText());
					ResultSet rs= pst2.executeQuery();
					
					while(rs.next())
					    {
						
						  String name = rs.getString("CNamw");
						  String add = rs.getString("CAddress");
						  String phone = rs.getString("PhNo");
						  String email = rs.getString("EmailID");
						  String gstno = rs.getString("GSTNo");
						  String panno = rs.getString("PanNo");
						  String adhar = rs.getString("AdharNo");
						  String ifr = rs.getString("Identifier");
						 // String cr = rs.getString("SUM(Credit)");
						      CName.setText(name);
						      CAddress.setText(add);
						      PhNo.setText(phone);
						      EmailID.setText(email);
						      gstNoField.setText(gstno);
						      panNoField.setText(panno);
						      AdharField.setText(adhar);
						      identifier.setText(ifr);
						      creditlbl.setText(creditval);
					    }
					
				}
				catch(Exception exc1)
				{
					JOptionPane.showMessageDialog(null, exc1);
				}
			}
		});
		btnSearch.setBounds(123, 16, 83, 15);
		pvusCustoPanel.add(btnSearch);
		
		JLabel lblCustomerType = new JLabel("Customer Type");
		lblCustomerType.setBounds(12, 121, 162, 15);
		contentPane.add(lblCustomerType);
		ButtonGroup cust= new ButtonGroup();
		
		
		JCheckBox prevCusto = new JCheckBox("Previous Customer");
		prevCusto.setOpaque(false);
		prevCusto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				pvusCustoPanel.setVisible(true);
				fillCusto.setVisible(true);
				showTable();
			}
		});
		prevCusto.setFont(new Font("Dialog", Font.BOLD, 10));
		prevCusto.setBounds(22, 135, 132, 23);
		contentPane.add(prevCusto);
		
		JCheckBox newCusto = new JCheckBox("New Customer");
		newCusto.setOpaque(false);
		newCusto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillCusto.setVisible(true);
			}
		});
		newCusto.setFont(new Font("Dialog", Font.BOLD, 10));
		newCusto.setBounds(22, 155, 132, 23);
		contentPane.add(newCusto);
		cust.add(prevCusto);
		cust.add(newCusto);
		creditPanel = new JPanel();
		creditPanel.setOpaque(false);
		creditPanel.setVisible(false);
		creditPanel.setBounds(117, 60, 162, 28);
		contentPane.add(creditPanel);
		creditPanel.setLayout(null);
		
		payment = new JTextField();
		payment.setOpaque(false);
		payment.setText("0");
		payment.setBounds(86, 0, 66, 28);
		creditPanel.add(payment);
		payment.setColumns(10);
		
		JLabel lblPayAmmount = new JLabel("Pay Ammount");
		lblPayAmmount.setFont(new Font("Dialog", Font.BOLD, 10));
		lblPayAmmount.setBounds(0, 7, 92, 15);
		creditPanel.add(lblPayAmmount);
		
		chckbxNewCheckBox = new JCheckBox("Cheque");
		
		chckbxNewCheckBox.setOpaque(false);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metpanel.setVisible(true);
				chqPanel.setVisible(true);
				PayType="CHEQUE";
				//creditPanel.setVisible(true);
				idGen();
				showTable();
				panel.setVisible(true);
				CId.setText(CustomerID);
			}
		});
		chckbxNewCheckBox.setBounds(22, 90, 89, 23);
		contentPane.add(chckbxNewCheckBox);
		
		chqPanel = new JPanel();
		chqPanel.setOpaque(false);
		chqPanel.setVisible(false);
		chqPanel.setBounds(117, 90, 162, 23);
		contentPane.add(chqPanel);
		chqPanel.setLayout(null);
		
		JLabel lChqNo = new JLabel("CHQ No");
		lChqNo.setBounds(0, 3, 57, 15);
		chqPanel.add(lChqNo);
		
		chqno = new JTextField();
		chqno.setOpaque(false);
		chqno.setBounds(86, 3, 66, 19);
		chqPanel.add(chqno);
		chqno.setColumns(10);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setVisible(false);
		panel.setBounds(291, 25, 88, 105);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUnitLeft = new JLabel("Unit Left");
		lblUnitLeft.setForeground(new Color(0, 0, 255));
		lblUnitLeft.setBounds(12, 12, 70, 15);
		panel.add(lblUnitLeft);
		
		lblNo = new JLabel("no");
		lblNo.setForeground(new Color(255, 0, 0));
		lblNo.setBounds(14, 36, 70, 15);
		panel.add(lblNo);
		
		JLabel lblSp = new JLabel("SP");
		lblSp.setBounds(24, 54, 33, 15);
		panel.add(lblSp);
		
		splbl = new JLabel("no");
		splbl.setForeground(new Color(123, 104, 238));
		splbl.setBounds(12, 81, 70, 15);
		panel.add(splbl);
		
		CId = new JLabel("");
		CId.setForeground(Color.BLUE);
		CId.setBounds(209, 5, 123, 15);
		contentPane.add(CId);
		
		JLabel labelimg =  new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("billing10.jpg")).getImage();
		labelimg.setIcon(new ImageIcon(img));
		labelimg.setBounds(0, 0, 856, 444);
		contentPane.add(labelimg);
		
		
		
		
		
		
		
		
		chBoxCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				metpanel.setVisible(true);
				PayType="CARD";
				idGen();
				CId.setText(CustomerID);
			}
		});
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				sum();
				//printbillPanel.setVisible(true);
				try
				{
					
					String querry = "Update Temp set Products='"+comboBox.getSelectedItem().toString()+"',Quantity='"+QuantText.getText()+"', Rate='"+RateText.getText()+"', GST ='"+gstField.getText()+"', Ammount="+Ammount+", SGST="+GSTamt+",CGST="+GSTamt+"  where Products='"+comboBox.getSelectedItem().toString()+"'";
				PreparedStatement pst= connCustomer.prepareStatement(querry);
				
				pst.execute();
				
				JOptionPane.showMessageDialog(null, "Data Update...!!!");
				pst.close();
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, exc);
				}
				//customer table
				try
				{
					String querry = "Update CustomerFinal set CNamw='"+CName.getText()+"',CAddress='"+CAddress.getText()+"', PhNo='"+PhNo.getText()+"', EmailID='"+EmailID.getText()+"',GSTNo='"+gstNoField.getText()+"',PanNo='"+panNoField.getText()+"',AdharNo='"+AdharField.getText()+"',Identifier='"+identifier.getText()+"' where CNamw ='"+CName.getText()+"' ";
				PreparedStatement pst= connCustomer.prepareStatement(querry);
				
				pst.execute();
				
			//	JOptionPane.showMessageDialog(null, "Data Update...!!!");
				pst.close();
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, exc);
				}
				
				showTable();
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//visible print panel
				
				try
				    {
					int i=0;
					
					total();
					sum();
					gst();
					String query="insert into Temp (Products, Quantity, Rate, GST, CustomerID,Ammount,SGST,CGST,Payment,Total) values(?,?,?,?,?,?,?,?,?,?) ";
					PreparedStatement pst= connCustomer.prepareStatement(query);
					pst.setString(1, comboBox.getSelectedItem().toString());
					pst.setString(2, QuantText.getText());
					pst.setString(3, RateText.getText());
					pst.setString(4, gstField.getText());
					pst.setString(5, CustomerID );
					pst.setString(6, Ammount);
					pst.setString(7, GSTamt);
					pst.setString(8, GSTamt);
					pst.setString(9, payment.getText());
					pst.setString(10, tot);
					//pst.setString(11, String.valueOf(i));
					pst.execute();
					pst.close();
					JOptionPane.showMessageDialog(null, "Data Saved");
					
				    }
					catch(Exception exc)
				
					{
						JOptionPane.showMessageDialog(null, exc);
					}
				///////////////////////////////////////////////////////
				try
				{
					String qry="SELECT SUM(Ammount) from Temp";
					PreparedStatement pst15=connCustomer.prepareStatement(qry);
					ResultSet rs15= pst15.executeQuery();
					while(rs15.next())
					{
				    label.setText(rs15.getString("SUM(Ammount)"));
				     }
				}catch(Exception x)
				{
					JOptionPane.showMessageDialog(null, x);
				}
				//substract data from stock with selected quantity... 
			
				subUnit();
				unitleft();
				
				//showtable
				showTable();
			     
				
			}
		});
		fillCombo();
		
	}
	
	
	
	
	
}
// previous due
// gst no
//pan no
//first select the cash or credit type the if credit type then previous due, pan or gst no else same bill type
//sum
//total the colum