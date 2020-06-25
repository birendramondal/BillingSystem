import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import com.toedter.calendar.JDateChooser;

import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.Color;

import com.toedter.components.JSpinField;
import com.toedter.components.JLocaleChooser;
import javax.swing.UIManager;


public class CreditPayer extends JFrame {

	private JPanel contentPane,panel_1,printPanel;
	private JTextField src;
	private JTextField amt;
	private JTextField textField_2;
	private JLabel namelbl,addlbl,panlbl,gstlbl,paylbl,phnolbl,duelbl,iden;
	private String PayType;
	private JDateChooser dateChooser;
	private String DueID;
	private int SlNo;
	private JButton btnSearch,btnPay ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditPayer frame = new CreditPayer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connCustomer= null;
	private JLabel lblAdhar;
	private JLabel lblEmail;
	/**
	 * Create the frame.
	 */
	
	
	
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
		          //////////////
		          Graphics2D g2 = (Graphics2D) pg;
		          g2.translate(pf.getImageableX(), pf.getImageableY());
		          g2.translate( 0f, 0f );
		          printPanel.print(g2);

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
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private String idGen()
	 {
		 try
		 {
			 String query = "select Due from CustomerFinal order by Due";
			 PreparedStatement pst = connCustomer.prepareStatement(query);
			 ResultSet rs = pst.executeQuery();
			 while (rs.next()) 
			    {
		            SlNo = rs.getInt(1);
		           int inc = SlNo + 1;
		           DueID= "Due"+inc; 
		            SlNo++;
		        }
			
			 
			 
		 }
		 catch(Exception exc)
		 {
			 JOptionPane.showMessageDialog(null, exc); 
		 }
		return DueID;
	 }
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public CreditPayer() {
		setBackground(UIManager.getColor("textInactiveText"));
		connCustomer= SqlConnection.customerConnection();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 603, 482);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					
					try
					{
					     String qrry="SELECT Identifier from CustomerFinal WHERE CNamw=? OR PhNo=? OR CustomerID=? OR PanNo=? OR GSTNo=? OR AdharNo=? OR chequenumber=? OR Identifier=?";
					     PreparedStatement pst20= connCustomer.prepareStatement(qrry);
					     pst20.setString(1, src.getText());
					     pst20.setString(2, src.getText());
					     pst20.setString(3, src.getText());
					     pst20.setString(4, src.getText());
					     pst20.setString(5, src.getText());
					     pst20.setString(6, src.getText());
					     pst20.setString(7, src.getText());
					     pst20.setString(8, src.getText());
					     ResultSet rs20= pst20.executeQuery();
					     if(rs20.next())
					     {
					    	 String idfr=rs20.getString("Identifier");
				      String qry= "SELECT CNamw,CAddress,PanNo,GSTNo,PayType,PhNo,EmailID,AdharNo,SUM(Credit) FROM CustomerFinal WHERE Identifier=?";
				     
				      PreparedStatement pst= connCustomer.prepareStatement(qry);
				    
				      pst.setString(1, idfr);
				     
				      ResultSet rs= pst.executeQuery();
				       while(rs.next())
				         {
					        namelbl.setText(rs.getString("CNamw"));
					        addlbl.setText(rs.getString("CAddress"));
					        panlbl.setText(rs.getString("PanNo"));
					        gstlbl.setText(rs.getString("GSTNo"));
					        paylbl.setText(rs.getString("PayType"));
					        phnolbl.setText(rs.getString("PhNo"));
					        iden.setText(idfr);
					        duelbl.setText(rs.getString("SUM(Credit)"));
					        lblAdhar.setText(rs.getString("AdharNo"));
					        lblEmail.setText(rs.getString("EmailID"));
					        btnPay.setVisible(true);
				         }
				      }
					     
					     else
					     {
					    	 JOptionPane.showMessageDialog(null, "No data found");
					    	 btnPay.setVisible(false);
					     }
					}
					catch(Exception ecxep)
					    {
						JOptionPane.showMessageDialog(null, ecxep);
					    }
				}
				catch(Exception x)
				{
					JOptionPane.showMessageDialog(null, x);
				}
			}
		});
		btnSearch.setBounds(180, 3, 83, 18);
		contentPane.add(btnSearch);
		
		src = new JTextField();
		src.setBounds(20, 3, 161, 19);
		src.setOpaque(false);
		contentPane.add(src);
		src.setColumns(10);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				 try
				   {
					   
						String querry = "Update CustomerFinal set Due='"+SlNo+"' where CustomerID='"+DueID+"'";
						PreparedStatement pst20= connCustomer.prepareStatement(querry);
						
						pst20.execute();
				   }
				   catch(Exception exc)
				   {
					   JOptionPane.showMessageDialog(null, exc);
				   }
				
				
				
				try {
					connCustomer.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnClose.setBounds(374, 422, 83, 22);
		contentPane.add(btnClose);
		
		 printPanel = new JPanel();
		 printPanel.setBackground(Color.WHITE);
		printPanel.setBounds(4, 33, 590, 322);
		contentPane.add(printPanel);
		printPanel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(336, 52, 242, 223);
		printPanel.add(panel_1);
		panel_1.setVisible(false);
		panel_1.setLayout(null);
		
		JLabel lblAmmount = new JLabel("Ammount:");
		lblAmmount.setFont(new Font("Dialog", Font.BOLD, 10));
		lblAmmount.setBounds(12, 14, 76, 15);
		panel_1.add(lblAmmount);
		
		amt = new JTextField();
		amt.setBounds(95, 12, 114, 19);
		panel_1.add(amt);
		amt.setColumns(10);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Dialog", Font.BOLD, 10));
		lblDate.setBounds(12, 49, 70, 15);
		panel_1.add(lblDate);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(95, 45, 113, 19);
		panel_1.add(dateChooser);
		
		JCheckBox chckbxCash = new JCheckBox("CASH");
		chckbxCash.setBackground(Color.WHITE);
		chckbxCash.setFont(new Font("Dialog", Font.BOLD, 10));
		chckbxCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				PayType="CASH";
			}
		});
		chckbxCash.setBounds(12, 83, 62, 23);
		panel_1.add(chckbxCash);
		
		JCheckBox chckbxCheque = new JCheckBox("CHEQUE");
		chckbxCheque.setBackground(Color.WHITE);
		chckbxCheque.setFont(new Font("Dialog", Font.BOLD, 10));
		chckbxCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				PayType="CHEQUE";
			}
		});
		chckbxCheque.setBounds(88, 83, 85, 23);
		panel_1.add(chckbxCheque);
		
		JCheckBox chckbxNeft = new JCheckBox("NEFT");
		chckbxNeft.setBackground(Color.WHITE);
		chckbxNeft.setFont(new Font("Dialog", Font.BOLD, 10));
		chckbxNeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				PayType="NEFT";
			}
		});
		chckbxNeft.setBounds(177, 83, 62, 23);
		panel_1.add(chckbxNeft);
		
		textField_2 = new JTextField();
		textField_2.setBounds(96, 106, 70, 15);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnPrint = new JButton("Print Bill");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				/////////////////////////////////////////update the credit value in creditCustomers////////////////////////////////////////
				 idGen();
			try
				{
					String qrr= "UPDATE creditCustomers SET Credit='"+amt.getText()+"' WHERE Identifier='"+iden.getText()+"'";
					PreparedStatement pst16= connCustomer.prepareStatement(qrr);
					pst16.execute();
				} 
			catch (Exception e1) 
				{
					
				 JOptionPane.showMessageDialog(null, e1);
				}
				/////////////////////////////////////////insert  the credit value in customerFinal table//////////////////////////////////////////////////
			   try 
			   {
				  
				   Date mydate= new Date();
				   String minus = amt.getText();
				   DateFormat df= new SimpleDateFormat("dd / M / yyyy");
				   float f=Float.valueOf(minus);
				   float m=-f;
				   String qry = "insert into CustomerFinal (CNamw, CAddress, PhNo, GSTNo, PanNo, PayType, Identifier,Credit,Date,Time,CustomerID) values(?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement pst= connCustomer.prepareStatement(qry);
					pst.setString(1, namelbl.getText());
					pst.setString(2, addlbl.getText());
					pst.setString(3, phnolbl.getText());
					pst.setString(4, gstlbl.getText());
					pst.setString(5, panlbl.getText());
					pst.setString(6, "(DUEpay)"+PayType);
					pst.setString(7, iden.getText());
					pst.setFloat(8, m);
					pst.setString(9, df.format(dateChooser.getDate()));
					pst.setString(10, (mydate.getHours()+" : "+mydate.getMinutes()+" : "+mydate.getSeconds()));
					pst.setString(11, DueID);
					pst.execute();
					pst.close();
			   }
			   catch (Exception xc)
			   {
				   JOptionPane.showMessageDialog(null, xc);
			   }
			
			////////////////////////////
			   btnPrint.setVisible(false);
			   printCard();
			  
			
			}
		});
		btnPrint.setBounds(73, 144, 93, 23);
		panel_1.add(btnPrint);
		
		JLabel lblShivmaxTreders = new JLabel("SHIVMAX TREDERS");
		lblShivmaxTreders.setBounds(377, 12, 133, 15);
		printPanel.add(lblShivmaxTreders);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 20, 318, 290);
		printPanel.add(panel);
		panel.setLayout(null);
		
		namelbl = new JLabel("Name:");
		namelbl.setFont(new Font("Dialog", Font.BOLD, 10));
		namelbl.setBounds(12, 12, 254, 15);
		panel.add(namelbl);
		
		addlbl = new JLabel("Address:");
		addlbl.setFont(new Font("Dialog", Font.BOLD, 10));
		addlbl.setBounds(12, 39, 254, 15);
		panel.add(addlbl);
		
	    panlbl = new JLabel("PAN No:");
	    panlbl.setFont(new Font("Dialog", Font.BOLD, 10));
	    panlbl.setBounds(12, 66, 260, 15);
	    panel.add(panlbl);
	    
	    gstlbl = new JLabel("GST No:");
	    gstlbl.setFont(new Font("Dialog", Font.BOLD, 10));
	    gstlbl.setBounds(12, 93, 260, 15);
	    panel.add(gstlbl);
	    
	    paylbl = new JLabel("PayType:");
	    paylbl.setFont(new Font("Dialog", Font.BOLD, 10));
	    paylbl.setBounds(12, 120, 260, 15);
	    panel.add(paylbl);
	    
	    phnolbl = new JLabel("Phone No:");
	    phnolbl.setFont(new Font("Dialog", Font.BOLD, 10));
	    phnolbl.setBounds(12, 147, 260, 15);
	    panel.add(phnolbl);
	    
	    duelbl = new JLabel("DUE Ammout:");
	    duelbl.setFont(new Font("Dialog", Font.BOLD, 10));
	    duelbl.setForeground(Color.RED);
	    duelbl.setBounds(12, 254, 189, 15);
	    panel.add(duelbl);
	    
	    iden = new JLabel("Identifier:");
	    iden.setFont(new Font("Dialog", Font.BOLD, 10));
	    iden.setBounds(12, 172, 189, 15);
	    panel.add(iden);
	    
	    lblAdhar = new JLabel("Adhar:");
	    lblAdhar.setFont(new Font("Dialog", Font.BOLD, 10));
	    lblAdhar.setBounds(12, 200, 189, 15);
	    panel.add(lblAdhar);
	    
	    lblEmail = new JLabel("Email:");
	    lblEmail.setFont(new Font("Dialog", Font.BOLD, 10));
	    lblEmail.setBounds(12, 227, 189, 15);
	    panel.add(lblEmail);
		
		btnPay = new JButton("Pay");
		btnPay.setVisible(false);
		btnPay.setBounds(129, 419, 83, 25);
		contentPane.add(btnPay);
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				panel_1.setVisible(true);
			}
		});
		btnPay.setFont(new Font("Dialog", Font.BOLD, 11));
		
		
	}
}
