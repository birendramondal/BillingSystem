import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicInteger;

import net.proteanit.sql.DbUtils;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JScrollPane;

import java.awt.print.*;
import java.awt.Color;
import javax.swing.UIManager;

public class BillPrintPrinter extends JFrame {

	private JPanel contentPane,panelContent,downPanel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillPrintPrinter frame = new BillPrintPrinter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 Connection connCustomer=null;
	 private JTable table;
	 private JTable table_1;
	 private JButton btnPrint;
	/**
	 * Create the frame.
	 */
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
                  //////////////////
		          double width = 8d * 72d;
	              double height = 5d * 72d;
		          Paper custom = new Paper();
		          custom.setSize(width,height);
		          custom.setImageableArea(0,0,width,height);
		          pf.setPaper(custom);   
		          //////////////////
		          Graphics2D g2 = (Graphics2D) pg;
		          g2.translate(pf.getImageableX(), pf.getImageableY());
		          g2.translate( 0f, 0f );
		          panelContent.print(g2);

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

	 //
	/** public void printJavaComponent() {
		    PrinterJob job = PrinterJob.getPrinterJob();
		    job.setJobName("Print Java Component");
		 
		    job.setPrintable (new Printable() {    
		        public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
		            if (pageIndex > 0) {
		                return(NO_SUCH_PAGE);
		            } 
		            else
		            {
		                Graphics2D g2d = (Graphics2D)g;
		                g2d.translate(pageFormat.getImageableX(), 
		                pageFormat.getImageableY());
		 
		                panelContent.paint(g2d);
		 
		                return(PAGE_EXISTS); 
		            }
		        }
		    });
		         
		    if (job.printDialog()) {
		        try {
		            job.print();
		        } catch (PrinterException e) {
		            System.err.println(e.getMessage()); 
		        }
		    }
		}
		**/
	 //
	 private void tempTableShow1()
	 {
		 try
			{
				//String querry="select * from Temp";
				String querry="select Products, Quantity, Rate,Total, GST,SGST,CGST,Ammount from Temp";
				PreparedStatement pst1= connCustomer.prepareStatement(querry);
				ResultSet rs1= pst1.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs1));
				//table_1.setModel(DbUtils.resultSetToTableModel(rs1));
			   }
			catch(Exception ex)
			   {
				JOptionPane.showMessageDialog(null, ex);
			   }
	 }private void tempTableShow2()
	 {
		 try
			{
				//String querry="select * from Temp";
				String querry="select Products, Quantity, Rate,Total, GST,SGST,CGST,Ammount from Temp";
				PreparedStatement pst1= connCustomer.prepareStatement(querry);
				ResultSet rs1= pst1.executeQuery();
				//table.setModel(DbUtils.resultSetToTableModel(rs1));
				table_1.setModel(DbUtils.resultSetToTableModel(rs1));
			   }
			catch(Exception ex)
			   {
				JOptionPane.showMessageDialog(null, ex);
			   }
	 }
	public BillPrintPrinter() {
		setResizable(false);
		setBackground(UIManager.getColor("Button.shadow"));
		connCustomer= SqlConnection.customerConnection();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setBounds(100, 100, 732, 739);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.shadow"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 23, 594, 689);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panelContent = new JPanel();
		panelContent.setBackground(Color.WHITE);
		panelContent.setLayout(null);
		panelContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelContent.setBounds(0, 0, 596, 689);
		panel.add(panelContent);
		
		JLabel lblShivmaxTreders = new JLabel("SHIVMAX TRADERS");
		lblShivmaxTreders.setFont(new Font("URW Chancery L", Font.BOLD | Font.ITALIC, 24));
		lblShivmaxTreders.setBounds(247, 19, 250, 21);
		panelContent.add(lblShivmaxTreders);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(29, 139, 548, 1);
		panelContent.add(separator);
		
		JLabel customerID = new JLabel("No");
		customerID.setForeground(Color.RED);
		customerID.setFont(new Font("Dialog", Font.BOLD, 11));
		customerID.setBounds(503, 61, 70, 11);
		panelContent.add(customerID);
		
		JLabel label_4 = new JLabel("(Shivmax Group of Company)");
		label_4.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		label_4.setBounds(260, 40, 231, 15);
		panelContent.add(label_4);
		
		JLabel label_5 = new JLabel("Phone no:  9432591539");
		label_5.setFont(new Font("Dialog", Font.BOLD, 11));
		label_5.setBounds(274, 95, 167, 11);
		panelContent.add(label_5);
		
		JLabel label_6 = new JLabel("Address:");
		label_6.setFont(new Font("Dialog", Font.BOLD, 11));
		label_6.setBounds(225, 50, 63, 21);
		panelContent.add(label_6);
		
		JLabel label_7 = new JLabel("702, PARUI DAS PARA ROAD");
		label_7.setFont(new Font("Dialog", Font.BOLD, 11));
		label_7.setBounds(293, 54, 194, 15);
		panelContent.add(label_7);
		
		JLabel label_8 = new JLabel("NABOPALLY");
		label_8.setFont(new Font("Dialog", Font.BOLD, 11));
		label_8.setBounds(317, 69, 88, 15);
		panelContent.add(label_8);
		
		JLabel label_9 = new JLabel("KOLKATA:- 700061");
		label_9.setFont(new Font("Dialog", Font.BOLD, 11));
		label_9.setBounds(300, 83, 138, 15);
		panelContent.add(label_9);
		
		JLabel label_10 = new JLabel("Customer Name:");
		label_10.setFont(new Font("Dialog", Font.BOLD, 10));
		label_10.setBounds(12, 40, 95, 15);
		panelContent.add(label_10);
		
		JLabel label_11 = new JLabel("Phone Number:");
		label_11.setFont(new Font("Dialog", Font.BOLD, 10));
		label_11.setBounds(10, 69, 95, 15);
		panelContent.add(label_11);
		
		JLabel CName = new JLabel("");
		CName.setFont(new Font("Dialog", Font.BOLD, 9));
		CName.setBounds(119, 44, 140, 11);
		panelContent.add(CName);
		
		JLabel PhNo = new JLabel("");
		PhNo.setFont(new Font("Dialog", Font.BOLD, 10));
		PhNo.setBounds(106, 70, 107, 14);
		panelContent.add(PhNo);
		
		JLabel label_14 = new JLabel("Address:");
		label_14.setFont(new Font("Dialog", Font.BOLD, 10));
		label_14.setBounds(12, 120, 63, 15);
		panelContent.add(label_14);
		
		JLabel CAddress = new JLabel("jhbmhv");
		CAddress.setFont(new Font("Dialog", Font.BOLD, 10));
		CAddress.setBounds(87, 123, 367, 11);
		panelContent.add(CAddress);
		
		JLabel label_16 = new JLabel("Total Ammount:");
		label_16.setFont(new Font("Dialog", Font.BOLD, 10));
		label_16.setBounds(374, 344, 117, 15);
		panelContent.add(label_16);
		
		JLabel totAmt = new JLabel("0");
		totAmt.setForeground(Color.RED);
		totAmt.setFont(new Font("Dialog", Font.BOLD, 11));
		totAmt.setBounds(532, 338, 44, 21);
		panelContent.add(totAmt);
		
		JLabel lblIgst = new JLabel("IGST:");
		lblIgst.setFont(new Font("Dialog", Font.BOLD, 10));
		lblIgst.setBounds(359, 365, 42, 15);
		panelContent.add(lblIgst);
		
		JLabel lblCg = new JLabel("CG:");
		lblCg.setFont(new Font("Dialog", Font.BOLD, 10));
		lblCg.setBounds(405, 365, 29, 15);
		panelContent.add(lblCg);
		
		JLabel cGov = new JLabel("None");
		cGov.setFont(new Font("Dialog", Font.BOLD, 10));
		cGov.setBounds(438, 365, 44, 15);
		panelContent.add(cGov);
		
		JLabel label_21 = new JLabel("+");
		label_21.setFont(new Font("Dialog", Font.BOLD, 10));
		label_21.setBounds(484, 365, 10, 15);
		panelContent.add(label_21);
		
		JLabel label_22 = new JLabel("SG:");
		label_22.setFont(new Font("Dialog", Font.BOLD, 10));
		label_22.setBounds(503, 365, 29, 15);
		panelContent.add(label_22);
		
		JLabel sGov = new JLabel("None");
		sGov.setFont(new Font("Dialog", Font.BOLD, 10));
		sGov.setBounds(533, 365, 43, 15);
		panelContent.add(sGov);
		
		JLabel lblInr = new JLabel("INR");
		lblInr.setFont(new Font("Dialog", Font.BOLD, 10));
		lblInr.setBounds(495, 341, 32, 19);
		panelContent.add(lblInr);
		
		JLabel datePrint = new JLabel("");
		datePrint.setFont(new Font("Dialog", Font.BOLD, 10));
		datePrint.setBounds(501, 11, 81, 15);
		panelContent.add(datePrint);
		
		JLabel timePrint = new JLabel("");
		timePrint.setFont(new Font("Dialog", Font.BOLD, 10));
		timePrint.setBounds(503, 30, 70, 15);
		panelContent.add(timePrint);
		
		JLabel lblGstNoOf = new JLabel("GST NO of customer:");
		lblGstNoOf.setFont(new Font("Dialog", Font.BOLD, 9));
		lblGstNoOf.setBounds(23, 345, 107, 15);
		panelContent.add(lblGstNoOf);
		
		JLabel gstNo = new JLabel("None");
		gstNo.setFont(new Font("Dialog", Font.BOLD, 10));
		gstNo.setBounds(12, 374, 118, 11);
		panelContent.add(gstNo);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(12, 145, 572, 194);
		scrollPane.setBackground(Color.WHITE);
		panelContent.add(scrollPane);
		scrollPane.getViewport().setBackground(Color.WHITE);
		table = new JTable();
		table.setFont(new Font("Serif", Font.BOLD, 9));
		
		scrollPane.setViewportView(table);
		
		JPanel creditPanel = new JPanel();
		creditPanel.setBackground(Color.WHITE);
		creditPanel.setBounds(135, 344, 206, 43);
		panelContent.add(creditPanel);
		creditPanel.setLayout(null);
		
		JLabel lblPreviousDueAmmount = new JLabel("Previous Due");
		lblPreviousDueAmmount.setFont(new Font("Dialog", Font.BOLD, 10));
		lblPreviousDueAmmount.setBounds(0, 4, 81, 15);
		creditPanel.add(lblPreviousDueAmmount);
		
		JLabel dueamt = new JLabel("None");
		dueamt.setFont(new Font("Dialog", Font.BOLD, 10));
		dueamt.setForeground(Color.ORANGE);
		dueamt.setBounds(37, 22, 62, 15);
		creditPanel.add(dueamt);
		
		JLabel lblPayAmmount = new JLabel("Pay Ammount");
		lblPayAmmount.setFont(new Font("Dialog", Font.BOLD, 10));
		lblPayAmmount.setBounds(111, 4, 83, 15);
		creditPanel.add(lblPayAmmount);
		
		JLabel paym = new JLabel("None");
		paym.setFont(new Font("Dialog", Font.BOLD, 10));
		paym.setForeground(Color.BLUE);
		paym.setBounds(143, 22, 51, 15);
		creditPanel.add(paym);
		
		JLabel lblInr_2 = new JLabel("INR");
		lblInr_2.setFont(new Font("Dialog", Font.BOLD, 10));
		lblInr_2.setBounds(113, 20, 30, 19);
		creditPanel.add(lblInr_2);
		
		JLabel lblInr_1 = new JLabel("INR");
		lblInr_1.setFont(new Font("Dialog", Font.BOLD, 10));
		lblInr_1.setBounds(10, 22, 28, 19);
		creditPanel.add(lblInr_1);
		
		JLabel typepay = new JLabel("None");
		typepay.setFont(new Font("Dialog", Font.BOLD, 10));
		typepay.setBounds(503, 87, 63, 12);
		panelContent.add(typepay);
		
		JLabel lblIdentifier = new JLabel("Identifier");
		lblIdentifier.setFont(new Font("Dialog", Font.BOLD, 10));
		lblIdentifier.setBounds(12, 95, 70, 15);
		panelContent.add(lblIdentifier);
		
		JLabel identi = new JLabel("");
		identi.setFont(new Font("Dialog", Font.BOLD, 10));
		identi.setBounds(77, 95, 138, 15);
		panelContent.add(identi);
		
		JLabel lblCustomerCopy = new JLabel("Customer Copy");
		lblCustomerCopy.setFont(new Font("Droid Sans Mono", Font.BOLD | Font.ITALIC, 10));
		lblCustomerCopy.setBounds(11, 13, 96, 15);
		panelContent.add(lblCustomerCopy);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 402, 572, 2);
		panelContent.add(separator_1);
		
		JLabel lblOfficialCopy = new JLabel("Official Copy");
		lblOfficialCopy.setFont(new Font("Dialog", Font.BOLD, 9));
		lblOfficialCopy.setBounds(244, 411, 70, 15);
		panelContent.add(lblOfficialCopy);
		
		JLabel Cname2 = new JLabel("Customer Name:");
		Cname2.setFont(new Font("Dialog", Font.BOLD, 10));
		Cname2.setBounds(13, 431, 161, 11);
		panelContent.add(Cname2);
		
		JLabel phno2 = new JLabel("Phone Number:");
		phno2.setFont(new Font("Dialog", Font.BOLD, 10));
		phno2.setBounds(12, 448, 140, 11);
		panelContent.add(phno2);
		
		JLabel idfr2 = new JLabel("Identifier");
		idfr2.setFont(new Font("Dialog", Font.BOLD, 10));
		idfr2.setBounds(12, 465, 140, 11);
		panelContent.add(idfr2);
		
		JLabel add2 = new JLabel("Address:");
		add2.setFont(new Font("Dialog", Font.BOLD, 10));
		add2.setBounds(13, 480, 438, 11);
		panelContent.add(add2);
		
		JLabel time2 = new JLabel("Time");
		time2.setFont(new Font("Dialog", Font.BOLD, 10));
		time2.setBounds(468, 444, 70, 11);
		panelContent.add(time2);
		
		JLabel date2 = new JLabel("Date");
		date2.setFont(new Font("Dialog", Font.BOLD, 10));
		date2.setBounds(468, 431, 81, 11);
		panelContent.add(date2);
		
		JLabel billno2 = new JLabel("Bill Number");
		billno2.setFont(new Font("Dialog", Font.BOLD, 10));
		billno2.setBounds(462, 459, 70, 11);
		panelContent.add(billno2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.getViewport().setBackground(Color.WHITE);
		scrollPane_1.setBackground(Color.WHITE);
		scrollPane_1.setBounds(12, 496, 570, 159);
		panelContent.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setFont(new Font("Serif", Font.BOLD, 9));
		scrollPane_1.setViewportView(table_1);
		
		
		JLabel paytype2 = new JLabel("PayType");
		paytype2.setFont(new Font("Dialog", Font.BOLD, 10));
		paytype2.setBounds(463, 472, 70, 11);
		panelContent.add(paytype2);
		
		JLabel label_12 = new JLabel("GST NO of customer:");
		label_12.setFont(new Font("Dialog", Font.BOLD, 9));
		label_12.setBounds(12, 659, 107, 15);
		panelContent.add(label_12);
		
		JLabel label_13 = new JLabel("Previous Due");
		label_13.setFont(new Font("Dialog", Font.BOLD, 10));
		label_13.setBounds(135, 658, 81, 15);
		panelContent.add(label_13);
		
		JLabel label_15 = new JLabel("Pay Ammount");
		label_15.setFont(new Font("Dialog", Font.BOLD, 10));
		label_15.setBounds(231, 658, 83, 15);
		panelContent.add(label_15);
		
		JLabel label_17 = new JLabel("Total Ammount:");
		label_17.setFont(new Font("Dialog", Font.BOLD, 10));
		label_17.setBounds(370, 654, 95, 15);
		panelContent.add(label_17);
		
		JLabel label_18 = new JLabel("IGST:");
		label_18.setFont(new Font("Dialog", Font.BOLD, 10));
		label_18.setBounds(360, 672, 42, 10);
		panelContent.add(label_18);
		
		JLabel label_19 = new JLabel("CG:");
		label_19.setFont(new Font("Dialog", Font.BOLD, 10));
		label_19.setBounds(406, 671, 29, 15);
		panelContent.add(label_19);
		
		JLabel cgst2 = new JLabel("None");
		cgst2.setFont(new Font("Dialog", Font.BOLD, 10));
		cgst2.setBounds(439, 671, 44, 15);
		panelContent.add(cgst2);
		
		JLabel label_23 = new JLabel("+");
		label_23.setFont(new Font("Dialog", Font.BOLD, 10));
		label_23.setBounds(485, 671, 10, 15);
		panelContent.add(label_23);
		
		JLabel label_24 = new JLabel("SG:");
		label_24.setFont(new Font("Dialog", Font.BOLD, 10));
		label_24.setBounds(504, 671, 29, 15);
		panelContent.add(label_24);
		
		JLabel sgst2 = new JLabel("None");
		sgst2.setFont(new Font("Dialog", Font.BOLD, 10));
		sgst2.setBounds(534, 671, 43, 15);
		panelContent.add(sgst2);
		
		JLabel lblNewLabel_1 = new JLabel("SHIVMAX TRADERS");
		lblNewLabel_1.setBounds(225, 448, 180, 15);
		panelContent.add(lblNewLabel_1);
		
		JLabel gstno2 = new JLabel("");
		gstno2.setFont(new Font("Dialog", Font.BOLD, 9));
		gstno2.setBounds(20, 673, 107, 10);
		panelContent.add(gstno2);
		
		JLabel totAmt2 = new JLabel("0");
		totAmt2.setForeground(Color.RED);
		totAmt2.setFont(new Font("Dialog", Font.BOLD, 9));
		totAmt2.setBounds(468, 658, 117, 11);
		panelContent.add(totAmt2);
		
		JLabel prevdue2 = new JLabel("New label");
		prevdue2.setFont(new Font("Dialog", Font.BOLD, 9));
		prevdue2.setBounds(137, 673, 78, 11);
		panelContent.add(prevdue2);
		
		JLabel payamt2 = new JLabel("New label");
		payamt2.setFont(new Font("Dialog", Font.BOLD, 9));
		payamt2.setBounds(236, 673, 78, 11);
		panelContent.add(payamt2);
		
		JLabel lblGstNumberhidpshzq = new JLabel("GST Number: 19HIDPS9322H1ZQ");
		lblGstNumberhidpshzq.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 9));
		lblGstNumberhidpshzq.setBounds(270, 108, 167, 11);
		panelContent.add(lblGstNumberhidpshzq);
		
		JLabel label = new JLabel("GST Number: 19HIDPS9322H1ZQ");
		label.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 9));
		label.setBounds(211, 465, 167, 11);
		panelContent.add(label);
		creditPanel.setVisible(false);
		
		JButton btnReload = new JButton("Reload");
		btnReload.setFont(new Font("Dialog", Font.BOLD, 9));
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				downPanel.setVisible(true);
				btnPrint.setVisible(true);
				//Temp table show 
				tempTableShow2();
				tempTableShow1();
				//Total
				try
				{
					String querry="select SUM(Ammount),SUM(SGST),Payment from Temp";
					PreparedStatement pst4= connCustomer.prepareStatement(querry);
					ResultSet rs4= pst4.executeQuery();
					while(rs4.next())
					{
						//JOptionPane.showMessageDialog(null, "Total While enter");
						 //String totalammt = rs4.getString("SUM(Ammount)");
						 float totammt = Float.parseFloat(rs4.getString("SUM(Ammount)"));
						 //float sgst = Float.parseFloat(rs4.getString("SUM(SGST)"));
						 float pt = Float.parseFloat(rs4.getString("Payment"));
						 //JOptionPane.showMessageDialog(null, pt);
						 String sgst = rs4.getString("SUM(SGST)");
						
						float grandtotal= totammt-pt;
						String a=String.valueOf(pt);
						String b=String.valueOf(grandtotal);
						 totAmt.setText(b);
						 totAmt2.setText(b);
						 cGov.setText(sgst);
						 cgst2.setText(sgst);
						 sGov.setText(sgst);
						 sgst2.setText(sgst);
					}
				   }
				catch(Exception ex)
				   {
					JOptionPane.showMessageDialog(null, ex);
				   }
				   
				// Bill no
				
				try
				{
					String querry="SELECT * FROM CustomerFinal ";
					PreparedStatement pst2= connCustomer.prepareStatement(querry);
					ResultSet rs= pst2.executeQuery();
					
					while(rs.next())
					    {
						
						  String mat = rs.getString("CustomerID");
						  String name = rs.getString("CNamw");
						  String phno = rs.getString("PhNo");
						  String add = rs.getString("CAddress");
						  String gst = rs.getString("GSTNo");
						  String paytype = rs.getString("PayType");
						  String idnty = rs.getString("Identifier");
						  
						  customerID.setText(mat);
						  billno2.setText(mat);
						  CName.setText(name);
						  Cname2.setText(name);
						  PhNo.setText(phno);
						  phno2.setText(phno);
						  CAddress.setText(add);
						  add2.setText(add);
						  gstNo.setText(gst);
						  gstno2.setText(gst);
						  typepay.setText(paytype);
						  paytype2.setText(paytype);
						  identi.setText(idnty);
						  idfr2.setText(idnty);
						 
					    }
					
				}
				catch(Exception exc1)
				{
					JOptionPane.showMessageDialog(null, exc1);
				}
				//time add to database
				try
				{
					
				}
				catch(Exception exc2)
				{
					JOptionPane.showMessageDialog(null, exc2);
				}
				//time shown in jlabel
				try
				{
					Date mydate= new Date();
					Date time= new Date();
					 final Calendar c = Calendar.getInstance();
					
					timePrint.setText(mydate.getHours()+" : "+mydate.getMinutes()+" : "+mydate.getSeconds());
					time2.setText(mydate.getHours()+" : "+mydate.getMinutes()+" : "+mydate.getSeconds());
					datePrint.setText(c.get(Calendar.DAY_OF_MONTH)+" / "+(c.get(Calendar.MONTH)+1)+" / "+c.get(Calendar.YEAR));
					date2.setText(c.get(Calendar.DAY_OF_MONTH)+" / "+(c.get(Calendar.MONTH)+1)+" / "+c.get(Calendar.YEAR));
			       
			         
				}
				catch(Exception exc3)
				{
					JOptionPane.showMessageDialog(null, exc3);
				}
				
				//show the panel if paytype == credit..
				
				try
				{
					String qry3="SELECT * FROM CustomerFinal WHERE PayType=? AND CNamw=?" ;
					PreparedStatement pst3= connCustomer.prepareStatement(qry3);
					pst3.setString(1, typepay.getText());
					pst3.setString(2, CName.getText());
					//pst3.setString(2, customerID.getText());
					ResultSet rs3= pst3.executeQuery();
					
					while(rs3.next())
					    {
						
						  String paytype = rs3.getString("PayType");
						  String CN = rs3.getString("CNamw");
						  String a= "CREDIT";
						  String b=  CName.getText();
						
						  if( ( new String(a).equals(paytype)) && (new String(b).equals(CN)))
						  {
							  
							  creditPanel.setVisible(true);
							  paym.setText(rs3.getString("Payment"));
							  payamt2.setText(rs3.getString("Payment"));
							  //add the credit ammount
							  //////
							  try
						       {
							     String querry="select SUM(Credit) from CustomerFinal WHERE CNamw=?";
							     PreparedStatement pst1= connCustomer.prepareStatement(querry);
							     pst1.setString(1, CName.getText());
							     ResultSet rs1= pst1.executeQuery();
							     while(rs1.next())
							             {
								float i= Float.parseFloat(rs1.getString("SUM(Credit)"));
								float j=Float.parseFloat(totAmt.getText());
								float p=i+j;
								//String S= String.valueOf(p);
								dueamt.setText(String.valueOf(i));
								prevdue2.setText(String.valueOf(i));
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
							  creditPanel.setVisible(false);
							
						  }
						
					    }
					
				}
				catch(Exception exc1)
				{
					JOptionPane.showMessageDialog(null, exc1);
				}
			
				
				
				
				//store the date time amt to the customerfinal table..
	               ///////////////insert the totalamt to the customertable.../////////
				

					try
					{
						
						//JOptionPane.showMessageDialog(null, "enter try");
						
						
						//vendor data add in the Vendor table
						String querry = "Update CustomerFinal set Totalamt ='"+totAmt.getText()+"',Date='"+datePrint.getText()+"', Time='"+timePrint.getText()+"',Credit='"+totAmt.getText()+"' where CustomerID ='"+customerID.getText()+"' ";
						PreparedStatement pst= connCustomer.prepareStatement(querry);
						
						pst.execute();
						//JOptionPane.showMessageDialog(null, "Data Saved");
						pst.close();
						
						}
						catch(Exception exc)
						{
							JOptionPane.showMessageDialog(null, exc);
						}
					
				
			}
		});
		btnReload.setBounds(627, 333, 71, 15);
		contentPane.add(btnReload);
		
		downPanel = new JPanel();
		downPanel.setBackground(UIManager.getColor("Button.shadow"));
		downPanel.setVisible(false);
		downPanel.setBounds(618, 360, 102, 17);
		contentPane.add(downPanel);
		downPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("Cancle");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				//previous amt should update on creditCustomers////////////////////
				
	    		  try 
	    		  {
	    		    String qrr= "UPDATE creditCustomers SET Credit='"+dueamt.getText()+"' WHERE Identifier='"+identi.getText()+"'";
					PreparedStatement pst16= connCustomer.prepareStatement(qrr);
				  }
	    		  catch (SQLException e1) 
	    		  {
					
					e1.printStackTrace();
				  }
			}
		});
		btnNewButton.setBounds(10, 0, 71, 15);
		downPanel.add(btnNewButton);
		
		 btnPrint = new JButton("Print");
		btnPrint.setVisible(false);
		btnPrint.setFont(new Font("Dialog", Font.BOLD, 10));
		btnPrint.setBounds(627, 306, 71, 15);
		contentPane.add(btnPrint);
		
		JButton btnClose = new JButton("EXIT");
		btnClose.setFont(new Font("Dialog", Font.BOLD, 10));
		btnClose.setBounds(627, 279, 71, 15);
		contentPane.add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				//temp table delete 
				try
				{
				String querry="delete from Temp";
				PreparedStatement pst1= connCustomer.prepareStatement(querry);
				//int rs= pst1.executeUpdate();
				///////////update 
				pst1.execute();
				pst1.close();
				
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
				}
				
				dispose();
			}
		});
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				//store the date time amt to the customerfinal table..
			
				
				
				//update the date time total creditcustomerfinal table 
				
				//Copy the temp table to temp2
				try
				{
					String qry3="SELECT CustomerID FROM Temp2 WHERE CustomerID=?" ;
					PreparedStatement pst3= connCustomer.prepareStatement(qry3);
					pst3.setString(1, customerID.getText());
					ResultSet rs3= pst3.executeQuery();
					
					if(rs3.next())
					{
						System.out.println("dataFound!!");
					}
					else
					{
						try
						{
							String qry= "INSERT INTO Temp2 (Products, Quantity, Rate, GST, Ammount,SGST,CGST,CustomerID,Total) SELECT Products, Quantity, Rate, GST, Ammount, SGST, CGST, CustomerID, Total FROM Temp";
							PreparedStatement pst= connCustomer.prepareStatement(qry);
							pst.execute();
							pst.close();
							//JOptionPane.showMessageDialog(null, "Insert into Temp2");
						}
						catch(Exception exc)
						{
							JOptionPane.showMessageDialog(null, exc);
						}
					}
				}
				catch(Exception excep)
				{
					JOptionPane.showMessageDialog(null, excep);
				}
				
				// update the CustomerFinal table Date, time ,Credit, Total
				// print the panel..
				printCard();
			
				
			}
			
		});
	}
}
// previous bill history add