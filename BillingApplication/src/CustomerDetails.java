import java.awt.BorderLayout;
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
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;


public class CustomerDetails extends JFrame {

	private JPanel contentPane, panel,panel_1;
	private JTextField billNumber;
	private JTable table;
	private JLabel name,address,date,panNo,adharNo,phoneNo,GSTno,billNo,time,paymentType,email;
	private JScrollPane scrollPane;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerDetails frame = new CustomerDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private String str;
	private JLabel due,pay;
	//print..
		 private void printCard(){

			 PrinterJob printjob = PrinterJob.getPrinterJob();
			 printjob.setJobName(" Bill Print ");

			 printjob.setPrintable (new Printable() {      
			      public int print(Graphics pg, PageFormat pf, int pageNum){                  

			          pf.setOrientation(PageFormat.LANDSCAPE);

			          if (pageNum > 0){
			             return Printable.NO_SUCH_PAGE;
			          }
			          //////////////
			          double width = 8d * 72d;
		              double height = 5d * 72d;
			          Paper custom = new Paper();
			          custom.setSize(width,height);
			          custom.setImageableArea(0,0,width,height);
			          pf.setPaper(custom);  
			          ////////////////
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
	private void showData()
	{
		try
		{
			String a,b;
			DateFormat df= new SimpleDateFormat("dd / M / yyyy");
			String querry="SELECT * FROM CustomerFinal WHERE CustomerID=? OR CNamw=? OR CAddress=? OR PhNo=? OR PayType=? OR EmailID=? OR Time=? OR Date=? OR PanNo=? OR GSTNo=? OR AdharNo=? OR chequenumber=? OR Identifier=?";
			PreparedStatement pst= connCustomer.prepareStatement(querry);
			pst.setString(1, billNumber.getText());
			pst.setString(2, billNumber.getText());
			pst.setString(3, billNumber.getText());
			pst.setString(4, billNumber.getText());
			pst.setString(5, billNumber.getText());
			pst.setString(6, billNumber.getText());
			pst.setString(7, billNumber.getText());
			pst.setString(8, billNumber.getText());
			pst.setString(9, billNumber.getText());
			pst.setString(10, billNumber.getText());
			pst.setString(11, billNumber.getText());
			pst.setString(12, billNumber.getText());
			pst.setString(13, billNumber.getText());
			
			ResultSet rs= pst.executeQuery();
			
			if(rs.next())
			
			{
				a=billNumber.getText();
				b=rs.getString("CustomerID");
				
			
			
				//JOptionPane.showMessageDialog(null, a);
				//JOptionPane.showMessageDialog(null, b);
				if( new String(a).equals(b))
				{
					//setText format
	        	name.setText(rs.getString("CNamw"));
				address.setText(rs.getString("CAddress")); 
				date.setText(rs.getString("GSTNo"));
				panNo.setText(rs.getString("PanNo"));
				adharNo.setText(rs.getString("AdharNo"));
				phoneNo.setText(rs.getString("PhNo"));
				GSTno.setText(rs.getString("GSTNo"));
				billNo.setText(rs.getString("CustomerID"));
				time.setText(rs.getString("Time"));
				paymentType.setText(rs.getString("PayType"));
				email.setText(rs.getString("EmailID"));
				//,,,,,SlNo,,,,,Identifier,Credit,chequenumber,TotalAmt
				//set the down panel/
				
				
				
				// show the temp two table in table
				panel.setVisible(true);
				//show the Temp2 table 
				}
				else
				{
					try
					{
						
						String qry="SELECT * FROM CustomerFinal WHERE CNamw=? OR CAddress=? OR PhNo=? OR PayType=? OR EmailID=? OR Time=? OR Date=? OR PanNo=? OR GSTNo=? OR AdharNo=? OR chequenumber=? OR Identifier=?";
						PreparedStatement pst7= connCustomer.prepareStatement(qry);
						pst7.setString(1, billNumber.getText());
						pst7.setString(2, billNumber.getText());
						pst7.setString(3, billNumber.getText());
						pst7.setString(4, billNumber.getText());
						pst7.setString(5, billNumber.getText());
						pst7.setString(6, billNumber.getText());
						//pst7.setString(7, df.format(dateChooser.getDate()));
						pst7.setString(7, billNumber.getText());
						pst7.setString(8, billNumber.getText());
						pst7.setString(9, billNumber.getText());
						pst7.setString(10, billNumber.getText());
						pst7.setString(11, billNumber.getText());
						pst7.setString(12, billNumber.getText());
						ResultSet rs1= pst7.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs1));
						//
						//SET CUSTOM RENDERER TO TEAMS COLUMN
						 table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());;
						  
						  //SET CUSTOM EDITOR TO TEAMS COLUMN
						  table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JTextField()));
						panel.setVisible(true);
					}
					catch(Exception ecxep)
					{
						JOptionPane.showMessageDialog(null, "Error in else try ");
					}
					
				}
			}
			///////////////////////////////////////////////////////
			else
			{
				JOptionPane.showMessageDialog(null, "Data Not found!");
			}
			
		}
		catch(Exception exc)
		{
			//JOptionPane.showMessageDialog(null, exc);
			JOptionPane.showMessageDialog(null, "Select Date");
		}
	}
	private void creditPanel()
	{
		 //show the panel if paytype == credit..
		
		try
		{
			String qry3="SELECT * FROM CustomerFinal WHERE PhNo=? AND CNamw=?" ;
			PreparedStatement pst3= connCustomer.prepareStatement(qry3);
			pst3.setString(1, phoneNo.getText());
			pst3.setString(2, name.getText());
			//pst3.setString(2, customerID.getText());
			ResultSet rs3= pst3.executeQuery();
			
			while(rs3.next())
			    {
				//JOptionPane.showMessageDialog(null, "enter in the while");
				  String phno = rs3.getString("PhNo");
				  String CN = rs3.getString("CNamw");
				  String a= phoneNo.getText();
				  String b=  name.getText();
				
				  if( ( new String(a).equals(phno)) && (new String(b).equals(CN)))
				  {
					  //JOptionPane.showMessageDialog(null, "enter in the if else");
					  panel_1.setVisible(true);
					  pay.setText(rs3.getString("Payment"));
					  //add the credit ammount
					  //////
					  try
				     {
					     String querry="select SUM(Credit) from CustomerFinal WHERE CNamw=?";
					     PreparedStatement pst1= connCustomer.prepareStatement(querry);
					     pst1.setString(1, name.getText());
					     ResultSet rs1= pst1.executeQuery();
					     while(rs1.next())
					             {
						              float i= Float.parseFloat(rs1.getString("SUM(Credit)"));
						              //float j=Float.parseFloat(totAmt.getText());
						              //float p=i+j;
						              String S= String.valueOf(i);
						              due.setText(S);
						              rs1.close();
						              pst1.close();
					             }
					  }
				
				catch(Exception ex)
				   {
					JOptionPane.showMessageDialog(null, "No previous Due!!");
				   }
					 //////
				  }
				  else
				  {
					  panel_1.setVisible(false);
					
				  }
				
			    }
			
		}
		catch(Exception exc1)
		{
			JOptionPane.showMessageDialog(null, exc1);
		}
	}
	Connection connCustomer=null;
	private JTable table_1;
	private JButton button;
	private JButton btnNewButton_2;
	public CustomerDetails() {
		setBackground(new Color(255, 228, 225));
		connCustomer= SqlConnection.customerConnection();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 875, 528);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		billNumber = new JTextField();
		billNumber.setOpaque(false);
		billNumber.setBounds(35, 10, 175, 19);
		contentPane.add(billNumber);
		billNumber.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// get the date of the enter vender name
				showData();
                
				
			}
		});
		btnNewButton.setBounds(208, 10, 83, 19);
		contentPane.add(btnNewButton);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setVisible(false);
		panel.setBounds(12, 48, 849, 420);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setBounds(12, 0, 53, 25);
		panel.add(lblNewLabel_1);
		
		name = new JLabel("none");
		name.setBounds(94, 5, 200, 15);
		panel.add(name);
		
		JLabel lblNewLabel_3 = new JLabel("Address:");
		lblNewLabel_3.setBounds(12, 37, 70, 15);
		panel.add(lblNewLabel_3);
		
		address = new JLabel("none");
		address.setBounds(94, 37, 478, 15);
		panel.add(address);
		
		JLabel lblPhoneNo = new JLabel("Phone No.");
		lblPhoneNo.setBounds(301, 5, 81, 15);
		panel.add(lblPhoneNo);
		
		phoneNo = new JLabel("none");
		phoneNo.setBounds(383, 5, 127, 15);
		panel.add(phoneNo);
		
		JLabel lblNewLabel_6 = new JLabel("GST No.:");
		lblNewLabel_6.setBounds(301, 64, 70, 15);
		panel.add(lblNewLabel_6);
		
		GSTno = new JLabel("none");
		GSTno.setBounds(383, 64, 161, 15);
		panel.add(GSTno);
		
		JLabel lblPanNo = new JLabel("PAN No:");
		lblPanNo.setBounds(12, 91, 61, 15);
		panel.add(lblPanNo);
		
		 panNo = new JLabel("none");
		panNo.setBounds(94, 91, 165, 15);
		panel.add(panNo);
		
		JLabel lblNewLabel_12 = new JLabel("Adhar No:");
		lblNewLabel_12.setBounds(301, 91, 70, 15);
		panel.add(lblNewLabel_12);
		
		adharNo = new JLabel("none");
		adharNo.setBounds(383, 91, 161, 15);
		panel.add(adharNo);
		
		JLabel lblNewLabel_14 = new JLabel("Payment Type:");
		lblNewLabel_14.setBounds(584, 91, 110, 15);
		panel.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Bill No:");
		lblNewLabel_15.setBounds(584, 5, 70, 15);
		panel.add(lblNewLabel_15);
		
	    billNo = new JLabel("none");
		billNo.setBounds(666, 2, 150, 20);
		panel.add(billNo);
		
	    paymentType = new JLabel("none");
		paymentType.setBounds(706, 91, 110, 15);
		panel.add(paymentType);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 126, 825, 127);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setVisible(false);
		panel_1.setBounds(32, 392, 512, 16);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblPreviousDue = new JLabel("Due:");
		lblPreviousDue.setBounds(0, 0, 56, 15);
		panel_1.add(lblPreviousDue);
		lblPreviousDue.setForeground(new Color(255, 0, 0));
		
		due = new JLabel("None");
		due.setBounds(68, 0, 114, 15);
		panel_1.add(due);
		
		JLabel lblPreviousPayment = new JLabel("Last  Payment:");
		lblPreviousPayment.setBounds(217, 0, 130, 15);
		panel_1.add(lblPreviousPayment);
		lblPreviousPayment.setForeground(new Color(255, 0, 0));
		
		pay = new JLabel("None");
		pay.setBounds(359, 0, 130, 15);
		panel_1.add(pay);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(584, 64, 53, 15);
		panel.add(lblTime);
		
		time = new JLabel("none");
		time.setBounds(666, 64, 110, 15);
		panel.add(time);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setBounds(12, 64, 53, 15);
		panel.add(lblNewLabel_2);
		
		email = new JLabel("none");
		email.setBounds(93, 64, 201, 15);
		panel.add(email);
		
		JLabel lblNewLabel_8 = new JLabel("Date:");
		lblNewLabel_8.setBounds(584, 37, 53, 15);
		panel.add(lblNewLabel_8);
		
		date = new JLabel("none");
		date.setBounds(666, 37, 100, 15);
		panel.add(date);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 126, 825, 242);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblShivmaxTreders = new JLabel("SHIVMAX TREDERS");
		lblShivmaxTreders.setBounds(672, 388, 132, 15);
		panel.add(lblShivmaxTreders);
		
		JButton btnNewButton_1 = new JButton("Print");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				printCard();
			
				
			}
		});
		btnNewButton_1.setBounds(708, 470, 83, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					connCustomer.close();
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnClose.setBounds(413, 470, 83, 25);
		contentPane.add(btnClose);
		
		btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				CustomerDetails cd = new CustomerDetails();
				cd.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(97, 470, 83, 25);
		contentPane.add(btnNewButton_2);
		
		dateChooser = new JDateChooser();
		dateChooser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
			}
		});
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{/**
				**/
			}
		});
		dateChooser.setBounds(303, 10, 114, 19);
		dateChooser.setDateFormatString("dd / M / yyyy");
		contentPane.add(dateChooser);
		
		JButton btnNewButton_3 = new JButton("Go");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					DateFormat df= new SimpleDateFormat("dd / M / yyyy");
					String qry="SELECT * FROM CustomerFinal WHERE Date=?";
					PreparedStatement pst7= connCustomer.prepareStatement(qry);
					
					pst7.setString(1, df.format(dateChooser.getDate()));
					
					
					ResultSet rs1= pst7.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs1));
					//
					//SET CUSTOM RENDERER TO TEAMS COLUMN
					 table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());;
					  
					  //SET CUSTOM EDITOR TO TEAMS COLUMN
					  table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JTextField()));
					panel.setVisible(true);
				}
				catch(Exception ecxep)
				{
					JOptionPane.showMessageDialog(null, "select date");
					//JOptionPane.showMessageDialog(null, ecxep);
				}
			}
		});
		btnNewButton_3.setFont(new Font("Dialog", Font.BOLD, 10));
		btnNewButton_3.setBounds(422, 10, 49, 18);
		contentPane.add(btnNewButton_3);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("custoDet.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 873, 502);
		contentPane.add(label);
		
		
		
		
		
		
		
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
			    	 JOptionPane.showMessageDialog(btn, lbl+" Clicked");
			    	 str=lbl;
			    	 
			    	 
			    	
			    	 //settext
			    	//////////////////////////////
			    	 try{

							String qrry="select * from CustomerFinal WHERE CustomerID=?";
							PreparedStatement pst9= connCustomer.prepareStatement(qrry);
							pst9.setString(1, str);
							ResultSet rs9= pst9.executeQuery();
						name.setText(rs9.getString("CNamw"));
						address.setText(rs9.getString("CAddress")); 
						date.setText(rs9.getString("Date"));
						panNo.setText(rs9.getString("PanNo"));
						adharNo.setText(rs9.getString("AdharNo"));
						phoneNo.setText(rs9.getString("PhNo"));
						GSTno.setText(rs9.getString("GSTNo"));
						billNo.setText(rs9.getString("CustomerID"));
						time.setText(rs9.getString("Time"));
						paymentType.setText(rs9.getString("PayType"));
						email.setText(rs9.getString("EmailID"));
						table.setVisible(false);
						scrollPane.setVisible(false);
						
						}
						catch(Exception xc)
						{
							JOptionPane.showMessageDialog(null, xc);
						}
			    	 ///////////////////////////////
			    	 //show the Temp2 table with same id no
			    	 try
						{
			    		 
							String qry="select * from Temp2 WHERE CustomerID=?";
							//String querry="select Products, Quantity, Rate,Total, GST,SGST,CGST,Ammount from Temp";
							PreparedStatement pst8= connCustomer.prepareStatement(qry);
							pst8.setString(1, str);
							ResultSet rs8= pst8.executeQuery();
							
							table_1.setModel(DbUtils.resultSetToTableModel(rs8));
						    //
							
							//////set the strings to the lebel
							
						}
						catch(Exception ex)
						   {
							JOptionPane.showMessageDialog(null, ex);
						   }
			    	 creditPanel();
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
// credit table with name 
//sell report graph monthly sell 
