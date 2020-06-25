import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import com.toedter.calendar.JDateChooser;

import java.awt.Font;
import java.awt.Color;

import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;


public class Vendor extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtVenderName;
	private JTextField vendorName;
	private JTextField PhNo;
	private JTextField GST;
	private JLabel lblGstNo;
	private JTextField Adhar;
	private JLabel lblAdharNo;
	private JTextField Address;
	private JLabel lblAddress;
	private JTextField pro;
	private JLabel lblProducts;
	private JPanel existVen;
	private JTextField quantity;
	private JTextField rate;
	private JTextField gst;
	private JLabel lblPayType;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxCredit;
	private JCheckBox chckbxNewCheckBox_1;
	private JPanel chqPanel,addPanel, panelSave,venDetPanel;
	private JLabel lblChequeNo,totalAmt  ;
	private JTextField chqTxtFld;
	private JScrollPane scrollPane_3;
	private String PayType,Ammount;
	private JComboBox stockCombo, venCombo;
	private int serialNo;
	private String timeset,dateset,tot;
	private int SlNo,VID;
	private boolean set,CR;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vendor frame = new Vendor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private int serialNumber()
	{
		 
	        try
		    	 {
	        	
				 String query = "select Serial_No from stockFinal order by Serial_No";
				 PreparedStatement pst = connStock.prepareStatement(query);
				 ResultSet rs = pst.executeQuery();
				 while (rs.next()) 
				    {
			           serialNo = rs.getInt(1);
			          
			           serialNo++;
			        }
				
				 
				 
			 }
			 catch(Exception exc)
			 {
				 JOptionPane.showMessageDialog(null, exc); 
			 }
			
		 
		return serialNo;
	}
	
	private void totalSet()
	{
		
			try{
			String qry="SELECT SUM(Rate),SUM(Quantity) FROM venpro WHERE Products=?";
			PreparedStatement pst= connCustomer.prepareStatement(qry);
			pst.setString(1,  pro.getText());
			ResultSet rs= pst.executeQuery();
			while(rs.next())
			{
				String q = rs.getString("SUM(Quantity)");
				String r= rs.getString("SUM(Rate)");
				String gstd=gst.getText();
				String adv=cRtextField.getText();
				int q1= Integer.parseInt(q);
				int r1= Integer.parseInt(r);
				int ad= Integer.parseInt(adv);
				int gst= Integer.parseInt(gstd);
				 int i=(q1*r1);
				 int per=(i*gst)/100;
				 int amt=(i+per)-ad;
				 tot= String.valueOf(amt);
				
				 totalAmt.setText(tot);
			}
			}
			catch(Exception x)
			{
				JOptionPane.showMessageDialog(null, x);
				JOptionPane.showMessageDialog(null, "error in totalSet()");
			}
			//String qrryy= "UPDATE venproFinal SET Credit= ";
			
		
		
	}
	////////////////////////////////////////////////////////ID gen///////////////////////////////////////////////////////
	private int idGen()
	 {
		 try
		 {
			 String query = "select Serial_No from VenInvo  order by Serial_No";
			 PreparedStatement pst = connCustomer.prepareStatement(query);
			 ResultSet rs = pst.executeQuery();
			 while (rs.next()) 
			    {
		            SlNo = rs.getInt(1);
		           int inc = SlNo + 1;
		           VID= inc; 
		            SlNo++;
		        }
			
			 
			 
		 }
		 catch(Exception exc)
		 {
			 JOptionPane.showMessageDialog(null, exc); 
		 }
		return VID;
	 }
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void time()
	{
		try
		{
	         final Calendar c = Calendar.getInstance();
	       
	            
			Date mydate= new Date();
			timeset=(mydate.getHours()+" : "+mydate.getMinutes()+" : "+mydate.getSeconds());
			dateset=(c.get(Calendar.DAY_OF_MONTH)+" / "+c.get(Calendar.MONTH)+" / "+c.get(Calendar.YEAR));
			
		}
		catch(Exception exc3)
		{
			JOptionPane.showMessageDialog(null, exc3);
		}
	}
	private void showTable()
	{
		try
	       {
 	         
			String qrry="select VendorName,Products,Quantity,GST,Rate,PayType,ChequeNo,Credit,date,Serial_No FROM venproFinal";
			PreparedStatement pst1= connCustomer.prepareStatement(qrry);
			ResultSet rs1= pst1.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs1));
			pst1.close();
			  rs1.close();
		   }
		catch(Exception ex)
		   {
			JOptionPane.showMessageDialog(null, ex);
		   }
	}
	private void showVenproTable()
	{
		try
	       {
 	         
			String qrry="select VendorName,Products,Quantity,GST,Rate,PayType,ChequeNo FROM venpro";
			PreparedStatement pst1= connCustomer.prepareStatement(qrry);
			ResultSet rs1= pst1.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs1));
			pst1.close();
			  rs1.close();
		   }
		catch(Exception ex)
		   {
			JOptionPane.showMessageDialog(null, ex);
		   }
	}
	
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
					
					
					stockCombo.addItem(rs.getString("Materials"));
					
					//JOptionPane.showMessageDialog(null, "ADD");
					
				    }
				
				
			}
			catch(Exception exc)
			{
				
				JOptionPane.showMessageDialog(null, exc);
			}
		
	 }
	
	private String sum()
	 {
		 
		 float qty = Float.parseFloat(quantity.getText());
		 
		 float rte = Float.parseFloat(rate.getText());
		 
		 float gstp = Float.parseFloat(gst.getText());
		 float a,b;
		 double pro;
		 a=(qty*rte);
	     b=(a*gstp)/100;
	     pro=a+b;
	     
		
		 
		 Ammount=String.valueOf(pro);
		 return Ammount;
	 }
	private void vendorCombo()
	{
		
		try
		{
		 
			String querry="select VName from Vendor";
			PreparedStatement pst= connCustomer.prepareStatement(querry);
			ResultSet rs= pst.executeQuery();
			//JOptionPane.showMessageDialog(null, "ADD");
			while(rs.next())
			    {
				
				//JOptionPane.showMessageDialog(null, "enter vendor table in vendorCombo");
				venCombo.addItem(rs.getString("VName"));
				
				//JOptionPane.showMessageDialog(null, "ADD");
			    }
			pst.close();
			  rs.close();
			
		}
		catch(Exception exc)
		{
			
			JOptionPane.showMessageDialog(null, exc);
		}
		
	}

	  Connection connStock= null;
	  Connection connCustomer= null;
	  private JTable table_1;
	  private JScrollPane scrollPane_1;
	  private JButton btnCancle;
	  private JButton btnNew;
	  private JLabel lblPreviousRate;
	  private JLabel prRate;
	  private JLabel label_1;
	  private JLabel label_2;
	  private JTextField cRtextField;
	  private JLabel lblDown;
	  private JLabel lblSl;
	/**
	 * Create the frame.
	 */
	public Vendor() {
		setBackground(UIManager.getColor("Button.select"));
		setResizable(false);
		setTitle("Vendor");
		
		
		connStock= SqlConnection.stockConnection();
		connCustomer= SqlConnection.customerConnection();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 884, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(false);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 294, 858, 135);
		contentPane.add(scrollPane);
	
		table = new JTable();
		table.setOpaque(false);
		showTable();
		//
		//SET CUSTOM RENDERER TO TEAMS COLUMN
		 table.getColumnModel().getColumn(9).setCellRenderer(new ButtonRenderer());;
		  
		  //SET CUSTOM EDITOR TO TEAMS COLUMN
		  table.getColumnModel().getColumn(9).setCellEditor(new ButtonEditor(new JTextField()));
		
		scrollPane.setViewportView(table);
		
		txtVenderName = new JTextField();
		txtVenderName.setOpaque(false);
		txtVenderName.setText("Vender Name");
		txtVenderName.setBounds(12, 267, 180, 27);
		contentPane.add(txtVenderName);
		txtVenderName.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//search the vendor details...
				try
				{
					//JOptionPane.showMessageDialog(null, "enter");
					//String querry="select * from venproFinal where VendorName=?";
				    String querry="select VendorName,Products,Quantity,GST,Rate,PayType,ChequeNo,Credit,date,Serial_No FROM venproFinal where VendorName=?";
					PreparedStatement pst= connCustomer.prepareStatement(querry);
					pst.setString(1,  txtVenderName.getText());
					ResultSet rs= pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					// RENDRE And CREATION OF Button
					 table.getColumnModel().getColumn(9).setCellRenderer(new ButtonRenderer());;
					 table.getColumnModel().getColumn(9).setCellEditor(new ButtonEditor(new JTextField()));
					pst.close();
					rs.close();
					  
				}
				catch(Exception exc)
				{
					
					JOptionPane.showMessageDialog(null, exc);
				}
			}
		});
		btnSearch.setBounds(203, 268, 83, 25);
		contentPane.add(btnSearch);
		
		pro = new JTextField();
		pro.setOpaque(false);
		pro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		pro.setBounds(546, 23, 151, 32);
		contentPane.add(pro);
		pro.setColumns(10);
		
		lblProducts = new JLabel("Products");
		lblProducts.setBounds(455, 31, 70, 15);
		contentPane.add(lblProducts);
		
		existVen = new JPanel();
		existVen.setOpaque(false);
		existVen.setVisible(false);
		
		
		
		existVen.setBounds(107, 7, 227, 27);
		contentPane.add(existVen);
		existVen.setLayout(null);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_3.setBounds(0, 0, 225, 24);
		existVen.add(scrollPane_3);
		
		venCombo = new JComboBox();
		venCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED) 
				{
		           
			    }
			}
		});
		vendorCombo();
		venCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//call vencombo
				
				
				try
				{
					
					String querry="select * from Vendor where VName=?";
					PreparedStatement pst= connCustomer.prepareStatement(querry);
					pst.setString(1, (String) venCombo.getSelectedItem());
					ResultSet rs= pst.executeQuery();
					
					while(rs.next())
					    {
						//JOptionPane.showMessageDialog(null, "enter");
						vendorName.setText(rs.getString("VName"));
						PhNo.setText(rs.getString("PhoneNo")); 
						GST.setText(rs.getString("GSTNo"));
						Adhar.setText(rs.getString("AdharNo"));
						Address.setText(rs.getString("Address"));
						
					    }
					pst.close();
					rs.close();
					
					  
				}
				catch(Exception exc)
				{
					
					JOptionPane.showMessageDialog(null, exc);
				}
				
				
			}

		});
		
		
		
		
		scrollPane_3.setViewportView(venCombo);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(455, 73, 70, 15);
		contentPane.add(lblQuantity);
		
		quantity = new JTextField();
		quantity.setOpaque(false);
		quantity.setBounds(546, 65, 87, 32);
		contentPane.add(quantity);
		quantity.setColumns(10);
		
		rate = new JTextField();
		rate.setOpaque(false);
		rate.setBounds(546, 105, 87, 32);
		contentPane.add(rate);
		rate.setColumns(10);
		
		JLabel lblPrice = new JLabel("Purchase Rate");
		lblPrice.setBounds(437, 113, 104, 15);
		contentPane.add(lblPrice);
		
		gst = new JTextField();
		gst.setOpaque(false);
		gst.setBounds(546, 145, 87, 32);
		contentPane.add(gst);
		gst.setColumns(10);
		
		JLabel lblGst = new JLabel("GST(%)");
		lblGst.setBounds(455, 153, 49, 15);
		contentPane.add(lblGst);
		
		addPanel = new JPanel();
		addPanel.setOpaque(false);
		addPanel.setVisible(false);
		addPanel.setBounds(750, 54, 95, 92);
		contentPane.add(addPanel);
		addPanel.setLayout(null);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBounds(0, 18, 89, 25);
		addPanel.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(0, 55, 89, 25);
		addPanel.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					String querry = "Delete from venpro where VendorName='"+vendorName.getText()+"' AND Products='"+pro.getText()+"'";
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
				btnCancle.setVisible(true);
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//insert value to the vendor product table 
				
				// add the the stock database with vendor name 
				serialNumber();
					sum();
					time();
					//////////////////////////////insert in VenInvo/////////////////////////
					try
					{
						String querry6="INSERT INTO VenInvo (Vendor_Name,Date,Serial_No) VALUES(?,?,?)";
						PreparedStatement pst6= connCustomer.prepareStatement(querry6);
						pst6.setString(1, vendorName.getText());
						pst6.setString(2, dateset);
						pst6.setString(3, lblSl.getText());
						pst6.execute();
						//JOptionPane.showMessageDialog(null, "done venInvo");
					}
					catch(Exception xcp)
					{
						JOptionPane.showMessageDialog(null, "error in VenInvo");
						JOptionPane.showMessageDialog(null, xcp);
					}
					
					try
					   {
						
						//insert date into temporury venpro table
						String querry5="INSERT INTO venpro (Products,Quantity,Rate,GST,VendorName,PayType,ChequeNo,DownPay,time,date,Serial_No) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
						PreparedStatement pst1= connCustomer.prepareStatement(querry5);	
						pst1.setString(1, pro.getText());
						pst1.setString(2, quantity.getText() );
						pst1.setString(3, rate.getText());
						pst1.setString(4, gst.getText());
						pst1.setString(5, vendorName.getText());
						pst1.setString(6, PayType);
						pst1.setString(7, chqTxtFld.getText());
						pst1.setString(8, cRtextField.getText());
						pst1.setString(9, timeset);
						pst1.setString(10, dateset);
						pst1.setString(11, lblSl.getText());
			
						pst1.execute();
						JOptionPane.showMessageDialog(null, "done");
						pst1.close();
					   }
					catch(Exception exc)
					   {
						JOptionPane.showMessageDialog(null, exc);
					   }
					
					
					
					//if materials name is already exist then update and add the quantity 
					try
					   {
						//generate serial no...
						// 
						
						try
						{
						
							
							String querry="SELECT Materials,Quantity FROM StockFinal WHERE Materials=?";
							PreparedStatement pst= connStock.prepareStatement(querry);
							pst.setString(1,  pro.getText());
							ResultSet rs= pst.executeQuery();
							if(rs.next())
						    {
								
							String mat = rs.getString("Materials");
							String nq= quantity.getText();
							 //quty 
							 int dqty=Integer.parseInt(rs.getString("Quantity"));
							 int newq=Integer.parseInt(quantity.getText());
							 int qt=dqty+newq;
							 
							 //amt 
							 float amt = Float.parseFloat(rate.getText());
							 float gstd = Float.parseFloat(gst.getText());
							 float b=amt*qt;
							 float a=(b*gstd)/100;
							 float ammount=a+b;
							 String str = String.valueOf(qt);
							 String amot = String.valueOf(ammount);
							try
							{
								
							String qrry = "Update StockFinal set Materials='"+pro.getText()+"',Quantity='"+str+"', Purchase_Rate='"+rate.getText()+"',GST='"+gst.getText()+"',Total_Ammount='"+amot+"' where Materials='"+pro.getText()+"' ";
							PreparedStatement pst3= connStock.prepareStatement(qrry);
							pst3.execute();
							//JOptionPane.showMessageDialog(null, "data update");
							
							}
							catch(Exception xcep)
							{
								JOptionPane.showMessageDialog(null,"Vendor Name is not matching with Materials Name!!Select 'Exist'!");
							}
						    }
							else 
							{
								String qry="INSERT INTO StockFinal (Materials,Quantity,Purchase_Rate,GST, Vendor_Name,Total_Ammount,Serial_No) VALUES(?,?,?,?,?,?,?)";
								PreparedStatement pst1= connStock.prepareStatement(qry);
								
								pst1.setString(1, pro.getText());
								pst1.setString(2, quantity.getText() );
								pst1.setString(3, rate.getText());
								pst1.setString(4, gst.getText());
								pst1.setString(5, vendorName.getText());
								pst1.setString(6, Ammount);
								pst1.setString(7, String.valueOf(serialNo));
								pst1.execute();
							//	JOptionPane.showMessageDialog(null, "done");
								pst1.close();
							}
							
						}
						catch(Exception xcp)
						{
							JOptionPane.showMessageDialog(null, xcp);
						}
					
				   }
				catch(Exception exc)
				   {
					JOptionPane.showMessageDialog(null, exc);
				   }
					showVenproTable();
					btnCancle.setVisible(true);
					showTable();
					totalSet();	
			}
			
			
		});
		
		lblPayType = new JLabel("Pay Type");
		lblPayType.setBounds(353, 45, 70, 15);
		contentPane.add(lblPayType);
		
		chckbxNewCheckBox = new JCheckBox("Cash");
		chckbxNewCheckBox.setOpaque(false);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				addPanel.setVisible(true);
				PayType="CASH";
			}
		});
		chckbxNewCheckBox.setBounds(353, 74, 83, 23);
		contentPane.add(chckbxNewCheckBox);
		
		chckbxCredit = new JCheckBox("Credit");
		chckbxCredit.setOpaque(false);
		chckbxCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				lblDown.setVisible(true);
				cRtextField.setVisible(true);
				addPanel.setVisible(true);
				PayType="CREDIT";
				CR=true;
			}
		});
		chckbxCredit.setBounds(353, 96, 70, 23);
		contentPane.add(chckbxCredit);
		
		chckbxNewCheckBox_1 = new JCheckBox("Cheque");
		chckbxNewCheckBox_1.setOpaque(false);
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//chQtextField.setVisible(true);
				chqPanel.setVisible(true);
				addPanel.setVisible(true);
				PayType="CHEQUE";
			}
		});
		chckbxNewCheckBox_1.setBounds(353, 116, 90, 23);
		contentPane.add(chckbxNewCheckBox_1);
		
		chqPanel = new JPanel();
		chqPanel.setOpaque(false);
		chqPanel.setVisible(false);
		chqPanel.setBounds(645, 154, 206, 23);
		contentPane.add(chqPanel);
		chqPanel.setLayout(null);
		
		lblChequeNo = new JLabel("Cheque No.");
		lblChequeNo.setBounds(12, 0, 104, 23);
		chqPanel.add(lblChequeNo);
		
		chqTxtFld = new JTextField();
		chqTxtFld.setOpaque(false);
		chqTxtFld.setBounds(99, 2, 104, 19);
		chqPanel.add(chqTxtFld);
		chqTxtFld.setColumns(10);
		
		JButton btnNewButton = new JButton("Exist");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//only vendro table data save...
				
				existVen.setVisible(true);
				venDetPanel.setVisible(true);
			}
		});
		btnNewButton.setBounds(12, 7, 70, 25);
		contentPane.add(btnNewButton);
		
		stockCombo = new JComboBox();
		stockCombo.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				if(e.getStateChange() == ItemEvent.SELECTED) 
				{

		            pro.setText((String) stockCombo.getSelectedItem());

			    }
				
		    }
	    });
		
		stockCombo.setBounds(709, 23, 161, 27);
		contentPane.add(stockCombo);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.getViewport().setOpaque(false);
		scrollPane_1.setOpaque(false);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(340, 186, 530, 70);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setRowSelectionAllowed(false);
		scrollPane_1.setViewportView(table_1);
		
		btnCancle = new JButton("Cancle");
		btnCancle.setVisible(false);
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dispose
				
				//delete the venpro table 
				try
				{
				String querry="delete from venpro";
				PreparedStatement pst1= connCustomer.prepareStatement(querry);
				//int rs= pst1.executeUpdate();
				pst1.execute();
				pst1.close();
				
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "error 2");
				}
				/////////////////////////////
				try
				{
				String querry="delete from VenInvo WHERE Serial_No='"+lblSl.getText()+"'";
				PreparedStatement pst1= connCustomer.prepareStatement(querry);
				//int rs= pst1.executeUpdate();
				pst1.execute();
				pst1.close();
				
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "error venInvo delete");
				}
				/////////////////////////////
				//delete the recent vendor data... 
				try
				{
					try
					{
					String querry="SELECT VName from Vendor where VName= ?";
					PreparedStatement pst1= connCustomer.prepareStatement(querry);
				    pst1.setString(1, vendorName.getText());
				    ResultSet rs= pst1.executeQuery();
				    if(rs.next() && set==true)
				    {
				    	///////
				    	String qry="delete from Vendor where VName= '"+vendorName.getText()+"'";
						PreparedStatement pst2= connCustomer.prepareStatement(qry);
						JOptionPane.showMessageDialog(null, "Delete New Vendor's Data and Remove Material from Stock");
						//int rs= pst1.executeUpdate();
						pst2.execute();
						pst2.close();
						dispose();
				    	//////
				    	
				    }
				    else
				    {
				    	JOptionPane.showMessageDialog(null, "Delete Vendor Data");
				    	dispose();
						
				    }
				    
					
					
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, "error 1");
					}
					
					////////delete VenInvo/////////////
					try
					{
					String querry="delete from VenInvo WHERE Vendor_Name= '"+vendorName.getText()+"' AND Serial_No='"+lblSl.getText()+"'";
					PreparedStatement pst1= connCustomer.prepareStatement(querry);
					//int rs= pst1.executeUpdate();
					pst1.execute();
					pst1.close();
					dispose();
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, "Vendor Name Not Matching!!");
					}
					
					
					
				
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "error");
				}
			}
		});
		btnCancle.setBounds(699, 459, 81, 25);
		contentPane.add(btnCancle);
		
		btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				panelSave.setVisible(true);
				venDetPanel.setVisible(true);
			}
		});
		btnNew.setBounds(12, 40, 70, 25);
		contentPane.add(btnNew);
		
		venDetPanel = new JPanel();
		venDetPanel.setOpaque(false);
		venDetPanel.setVisible(false);
		venDetPanel.setBounds(12, 76, 322, 180);
		contentPane.add(venDetPanel);
		venDetPanel.setLayout(null);
		
		JLabel lblVendorName = new JLabel("Vendor Name");
		lblVendorName.setBounds(12, 26, 101, 15);
		venDetPanel.add(lblVendorName);
		
		vendorName = new JTextField();
		vendorName.setOpaque(false);
		vendorName.setBounds(120, 22, 197, 24);
		venDetPanel.add(vendorName);
		vendorName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		vendorName.setColumns(10);
		
		JLabel lblPhoneNo = new JLabel("Phone No.");
		lblPhoneNo.setBounds(22, 57, 90, 15);
		venDetPanel.add(lblPhoneNo);
		
		PhNo = new JTextField();
		PhNo.setOpaque(false);
		PhNo.setBounds(120, 53, 197, 24);
		venDetPanel.add(PhNo);
		PhNo.setColumns(10);
		
		lblGstNo = new JLabel("GST No.");
		lblGstNo.setBounds(43, 90, 70, 15);
		venDetPanel.add(lblGstNo);
		
		GST = new JTextField();
		GST.setOpaque(false);
		GST.setBounds(120, 84, 197, 24);
		venDetPanel.add(GST);
		GST.setColumns(10);
		
		lblAdharNo = new JLabel("PAN NO.");
		lblAdharNo.setBounds(43, 117, 70, 15);
		venDetPanel.add(lblAdharNo);
		
		Adhar = new JTextField();
		Adhar.setOpaque(false);
		Adhar.setBounds(120, 113, 197, 24);
		venDetPanel.add(Adhar);
		Adhar.setColumns(10);
		
		lblAddress = new JLabel("Address");
		lblAddress.setBounds(43, 151, 70, 15);
		venDetPanel.add(lblAddress);
		
		Address = new JTextField();
		Address.setOpaque(false);
		Address.setBounds(120, 144, 197, 24);
		venDetPanel.add(Address);
		Address.setColumns(10);
		
		panelSave = new JPanel();
		panelSave.setOpaque(false);
		panelSave.setVisible(false);
		panelSave.setBounds(84, 455, 104, 32);
		contentPane.add(panelSave);
		panelSave.setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(12, 0, 70, 25);
		panelSave.add(btnSave);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(CR==true)
				{
					try
					{
						
					String qrry = "Update venpro set Credit='"+totalAmt.getText()+"' where Serial_No='"+lblSl.getText()+"' ";
					PreparedStatement pst3= connCustomer.prepareStatement(qrry);
					pst3.execute();
					//JOptionPane.showMessageDialog(null, "data update");
					
					}
					catch(Exception xcep)
					{
						JOptionPane.showMessageDialog(null,"Vendor Name is not matching with Materials Name!!Select 'Exist'!");
					}
				}
                 //copy the venpro value to the venproFinal table 
				
				try
				{
					String qry= "INSERT INTO venproFinal (Products,Quantity,Rate,GST,VendorName,PayType,ChequeNo,DownPay,time,date,Serial_No,Credit) SELECT Products,Quantity,Rate,GST,VendorName,PayType,ChequeNo,DownPay,time,date,Serial_No,Credit FROM venpro WHERE Products= Products";
					PreparedStatement pst= connCustomer.prepareStatement(qry);
					pst.execute();
					pst.close();
					
					
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, exc);
				}
				//delete the venprodata
				try
				{
				String querry="delete from venpro";
				PreparedStatement pst1= connCustomer.prepareStatement(querry);
				pst1.execute();
				pst1.close();
				connCustomer.close();
				connStock.close();
				dispose();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
				}
				
				
					
				
				
				
			}
		});
		btnExit.setBounds(296, 459, 83, 25);
		contentPane.add(btnExit);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("NEFT");
		chckbxNewCheckBox_2.setOpaque(false);
		chckbxNewCheckBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				addPanel.setVisible(true);
				PayType="NEFT";
			}
		});
		chckbxNewCheckBox_2.setBounds(353, 138, 70, 23);
		contentPane.add(chckbxNewCheckBox_2);
		
		lblPreviousRate = new JLabel("Previous Rate");
		lblPreviousRate.setFont(new Font("Dialog", Font.BOLD, 11));
		lblPreviousRate.setBounds(645, 68, 95, 27);
		contentPane.add(lblPreviousRate);
		
		prRate = new JLabel("null");
		prRate.setForeground(Color.ORANGE);
		prRate.setBounds(651, 110, 66, 21);
		contentPane.add(prRate);
		
		JLabel label = new JLabel("₹");
		label.setBounds(720, 113, 20, 15);
		contentPane.add(label);
		
		
		cRtextField = new JTextField();
		cRtextField.setOpaque(false);
		cRtextField.setVisible(false);
		cRtextField.setText("0");
		cRtextField.setBounds(431, 95, 55, 15);
		contentPane.add(cRtextField);
		cRtextField.setColumns(10);
		

		lblDown = new JLabel("down");
		lblDown.setVisible(false);
		lblDown.setFont(new Font("Dialog", Font.BOLD, 10));
		lblDown.setBounds(492, 95, 49, 15);
		contentPane.add(lblDown);
		
		lblSl = new JLabel("sl");
		idGen();
		lblSl.setText(String.valueOf(VID));
		lblSl.setBounds(180, 46, 106, 15);
		contentPane.add(lblSl);
		
		totalAmt = new JLabel("0");
		totalAmt.setBounds(758, 267, 70, 15);
		contentPane.add(totalAmt);
		
		JButton btnNewButton_1 = new JButton("Details");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				VendorDetails vd= new VendorDetails();
				vd.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(482, 459, 87, 25);
		contentPane.add(btnNewButton_1);
		
		JLabel lblTotalAmmount = new JLabel("total Ammount");
		lblTotalAmmount.setBounds(634, 267, 106, 15);
		contentPane.add(lblTotalAmmount);
		
		label_2 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("vframe4.png")).getImage();
		label_2.setIcon(new ImageIcon(img));
		label_2.setBounds(0, -11, 882, 506);
		contentPane.add(label_2);
		
	
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				// insert vendor details to the Vendor table 
				set=true;
				try
				{
					//JOptionPane.showMessageDialog(null, "enter try");
					
					time();
					//vendor data add in the Vendor table
					String query="insert into Vendor (Vname,Address,PhoneNo,AdharNo,GSTNo,Time,Date) values(?,?,?,?,?,?,?) ";
					PreparedStatement pst= connCustomer.prepareStatement(query);
					pst.setString(1, vendorName.getText());
					pst.setString(2, Address.getText());
					pst.setString(3, PhNo.getText() );
					pst.setString(4, Adhar.getText());
					pst.setString(5, GST.getText());
					pst.setString(6, timeset);
					pst.setString(7, dateset);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Data Saved");
					pst.close();
					
					}
					catch(Exception exc)
					{
						JOptionPane.showMessageDialog(null, "Vendor Already exist!! Change the Vendor Name");
					}
					
					
				

				
				
				
				
			}
		});
		stockCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
				 
					String qry="select Purchase_Rate from StockFinal WHERE Materials=?";
					PreparedStatement pst6= connStock.prepareStatement(qry);
					pst6.setString(1, (String) stockCombo.getSelectedItem());
					ResultSet rs6= pst6.executeQuery();
					//JOptionPane.showMessageDialog(null, (String) stockCombo.getSelectedItem());
					while(rs6.next())
					    {
						
						//JOptionPane.showMessageDialog(null, "ADD");
						 prRate.setText(rs6.getString("Purchase_Rate"));
					    }
					
					
				}
				catch(Exception exc)
				{
					
					JOptionPane.showMessageDialog(null, exc);
				}
			}
			
		});
		fillCombo();
	}
	
	
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
		  public Component getTableCellEditorComponent(JTable table, Object obj,
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
		    	
		    	    
					String msg= lbl;
					VendorDetails vD = new VendorDetails();
					vD.getData(msg);
					vD.setVisible(true);
					
					
					//JOptionPane.showMessageDialog(btn, lbl+" Clicked");
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
		    // TODO Auto-generated method stub
		    super.fireEditingStopped();
		  }
		}
}

