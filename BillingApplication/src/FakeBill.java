import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.JScrollPane;

import java.awt.print.*;
import java.awt.Color;
import javax.swing.UIManager;

public class FakeBill extends JFrame {

	private JPanel contentPane,panelContent;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FakeBill frame = new FakeBill();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 Connection connCustomer=null;
	 private JTable table;
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
		          ///////////////////////////
		          double width = 8d * 72d;
	              double height = 5d * 72d;
		          Paper custom = new Paper();
		          custom.setSize(width,height);
		          custom.setImageableArea(0,0,width,height);
		          pf.setPaper(custom);         
		  
		          ///////////////////////////

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

	 
	public FakeBill() {
		setResizable(false);
		setBackground(UIManager.getColor("Button.select"));
		connCustomer= SqlConnection.customerConnection();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	//	yourframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setBounds(100, 100, 732, 739);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("RadioButton.shadow"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 0, 602, 712);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panelContent = new JPanel();
		panelContent.setBackground(Color.WHITE);
		panelContent.setLayout(null);
		panelContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelContent.setBounds(0, 0, 594, 712);
		panel.add(panelContent);
		
		JLabel lblShivmaxTreders = new JLabel("SHIVMAX TRADERS");
		lblShivmaxTreders.setFont(new Font("URW Chancery L", Font.BOLD | Font.ITALIC, 25));
		lblShivmaxTreders.setBounds(167, 15, 260, 24);
		panelContent.add(lblShivmaxTreders);
		
		JLabel label_2 = new JLabel("Bill NO:");
		label_2.setFont(new Font("Dialog", Font.BOLD, 10));
		label_2.setBounds(418, 55, 51, 26);
		panelContent.add(label_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(37, 124, 500, 11);
		panelContent.add(separator);
		
		JLabel customerID = new JLabel("FAKEBILL");
		customerID.setForeground(Color.RED);
		customerID.setFont(new Font("Dialog", Font.BOLD, 10));
		customerID.setBounds(481, 56, 51, 26);
		panelContent.add(customerID);
		
		JLabel label_4 = new JLabel("(Shivmax Group of Company)");
		label_4.setFont(new Font("Dialog", Font.BOLD, 10));
		label_4.setBounds(177, 37, 167, 15);
		panelContent.add(label_4);
		
		JLabel label_5 = new JLabel("Phone no:  9432591539");
		label_5.setFont(new Font("Dialog", Font.BOLD, 10));
		label_5.setBounds(225, 86, 142, 11);
		panelContent.add(label_5);
		
		JLabel label_6 = new JLabel("Address:");
		label_6.setFont(new Font("Dialog", Font.BOLD, 10));
		label_6.setBounds(200, 51, 51, 11);
		panelContent.add(label_6);
		
		JLabel label_7 = new JLabel("702, PARUI DAS PARA ROAD");
		label_7.setFont(new Font("Dialog", Font.BOLD, 10));
		label_7.setBounds(247, 49, 167, 15);
		panelContent.add(label_7);
		
		JLabel label_8 = new JLabel("NABOPALLY");
		label_8.setFont(new Font("Dialog", Font.BOLD, 10));
		label_8.setBounds(257, 63, 73, 11);
		panelContent.add(label_8);
		
		JLabel label_9 = new JLabel("KOLKATA:- 700061");
		label_9.setFont(new Font("Dialog", Font.BOLD, 10));
		label_9.setBounds(244, 73, 100, 11);
		panelContent.add(label_9);
		
		JLabel label_10 = new JLabel("Customer Name:");
		label_10.setFont(new Font("Dialog", Font.BOLD, 10));
		label_10.setBounds(35, 47, 100, 15);
		panelContent.add(label_10);
		
		JLabel label_11 = new JLabel("Phone Number:");
		label_11.setFont(new Font("Dialog", Font.BOLD, 10));
		label_11.setBounds(37, 86, 87, 11);
		panelContent.add(label_11);
		
		JLabel CName = new JLabel("None");
		CName.setFont(new Font("Dialog", Font.BOLD, 10));
		CName.setBounds(137, 47, 51, 14);
		panelContent.add(CName);
		
		JLabel PhNo = new JLabel("None");
		PhNo.setFont(new Font("Dialog", Font.BOLD, 10));
		PhNo.setBounds(129, 82, 63, 14);
		panelContent.add(PhNo);
		
		JLabel label_14 = new JLabel("Address:");
		label_14.setFont(new Font("Dialog", Font.BOLD, 10));
		label_14.setBounds(37, 107, 73, 15);
		panelContent.add(label_14);
		
		JLabel CAddress = new JLabel("None");
		CAddress.setFont(new Font("Dialog", Font.BOLD, 10));
		CAddress.setBounds(102, 109, 394, 11);
		panelContent.add(CAddress);
		
		JLabel label_16 = new JLabel("Total Ammount:");
		label_16.setFont(new Font("Dialog", Font.BOLD, 10));
		label_16.setBounds(359, 354, 117, 15);
		panelContent.add(label_16);
		
		JLabel totAmt = new JLabel("No");
		totAmt.setForeground(Color.RED);
		totAmt.setFont(new Font("Dialog", Font.BOLD, 10));
		totAmt.setBounds(501, 352, 73, 16);
		panelContent.add(totAmt);
		
		JLabel lblIgst = new JLabel("IGST:");
		lblIgst.setFont(new Font("Dialog", Font.BOLD, 10));
		lblIgst.setBounds(359, 372, 42, 15);
		panelContent.add(lblIgst);
		
		JLabel lblCg = new JLabel("CG:");
		lblCg.setFont(new Font("Dialog", Font.BOLD, 10));
		lblCg.setBounds(401, 373, 29, 15);
		panelContent.add(lblCg);
		
		JLabel cGov = new JLabel("None");
		cGov.setFont(new Font("Dialog", Font.BOLD, 10));
		cGov.setBounds(434, 373, 42, 15);
		panelContent.add(cGov);
		
		JLabel label_21 = new JLabel("+");
		label_21.setFont(new Font("Dialog", Font.BOLD, 10));
		label_21.setBounds(481, 373, 10, 15);
		panelContent.add(label_21);
		
		JLabel label_22 = new JLabel("SG:");
		label_22.setFont(new Font("Dialog", Font.BOLD, 10));
		label_22.setBounds(508, 373, 29, 15);
		panelContent.add(label_22);
		
		JLabel sGov = new JLabel("None");
		sGov.setFont(new Font("Dialog", Font.BOLD, 10));
		sGov.setBounds(535, 373, 42, 15);
		panelContent.add(sGov);
		
		JLabel lblInr_2 = new JLabel("INR");
		lblInr_2.setFont(new Font("Dialog", Font.BOLD, 10));
		lblInr_2.setBounds(463, 350, 30, 19);
		panelContent.add(lblInr_2);
		
		JLabel datePrint = new JLabel("Date");
		datePrint.setFont(new Font("Dialog", Font.BOLD, 10));
		datePrint.setBounds(418, 19, 44, 13);
		panelContent.add(datePrint);
		
		JLabel timePrint = new JLabel("Time");
		timePrint.setFont(new Font("Dialog", Font.BOLD, 10));
		timePrint.setBounds(418, 45, 51, 11);
		panelContent.add(timePrint);
		
		JLabel lblGstNoOf = new JLabel("GST NO of customer:");
		lblGstNoOf.setFont(new Font("Dialog", Font.BOLD, 9));
		lblGstNoOf.setBounds(12, 354, 117, 11);
		panelContent.add(lblGstNoOf);
		
		JLabel gstNo = new JLabel("None");
		gstNo.setFont(new Font("Dialog", Font.BOLD, 10));
		gstNo.setBounds(48, 377, 51, 11);
		panelContent.add(gstNo);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(25, 143, 547, 196);
		panelContent.add(scrollPane);
		scrollPane.getViewport().setBackground(Color.WHITE);
		table = new JTable();
		
		scrollPane.setViewportView(table);
		
		JPanel creditPanel = new JPanel();
		creditPanel.setBackground(Color.WHITE);
		creditPanel.setBounds(129, 349, 224, 43);
		panelContent.add(creditPanel);
		creditPanel.setLayout(null);
		
		JLabel lblPreviousDueAmmount = new JLabel("Total Due ammount:");
		lblPreviousDueAmmount.setFont(new Font("Dialog", Font.BOLD, 10));
		lblPreviousDueAmmount.setBounds(0, 4, 143, 15);
		creditPanel.add(lblPreviousDueAmmount);
		
		JLabel dueamt = new JLabel("None");
		dueamt.setFont(new Font("Dialog", Font.BOLD, 10));
		dueamt.setForeground(Color.ORANGE);
		dueamt.setBounds(46, 22, 34, 15);
		creditPanel.add(dueamt);
		
		JLabel lblPayAmmount = new JLabel("Pay Ammount");
		lblPayAmmount.setFont(new Font("Dialog", Font.BOLD, 10));
		lblPayAmmount.setBounds(131, 2, 87, 15);
		creditPanel.add(lblPayAmmount);
		
		JLabel paym = new JLabel("None");
		paym.setFont(new Font("Dialog", Font.BOLD, 10));
		paym.setForeground(Color.BLUE);
		paym.setBounds(169, 22, 48, 15);
		creditPanel.add(paym);
		
		JLabel lblInr = new JLabel("INR");
		lblInr.setFont(new Font("Dialog", Font.BOLD, 10));
		lblInr.setBounds(133, 18, 34, 19);
		creditPanel.add(lblInr);
		
		JLabel lblInr_1 = new JLabel("INR");
		lblInr_1.setFont(new Font("Dialog", Font.BOLD, 10));
		lblInr_1.setBounds(10, 22, 34, 19);
		creditPanel.add(lblInr_1);
		
		JLabel lblPaymentType = new JLabel("Pay Type:");
		lblPaymentType.setFont(new Font("Dialog", Font.BOLD, 10));
		lblPaymentType.setBounds(418, 86, 73, 15);
		panelContent.add(lblPaymentType);
		
		JLabel typepay = new JLabel("FAKE");
		typepay.setFont(new Font("Dialog", Font.BOLD, 10));
		typepay.setBounds(481, 87, 56, 13);
		panelContent.add(typepay);
		
		JLabel label = new JLabel("Official Copy");
		label.setFont(new Font("Dialog", Font.BOLD, 9));
		label.setBounds(244, 425, 70, 15);
		panelContent.add(label);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 416, 572, 2);
		panelContent.add(separator_1);
		
		JLabel label_1 = new JLabel("Customer Name:");
		label_1.setFont(new Font("Dialog", Font.BOLD, 10));
		label_1.setBounds(13, 445, 161, 11);
		panelContent.add(label_1);
		
		JLabel label_3 = new JLabel("Phone Number:");
		label_3.setFont(new Font("Dialog", Font.BOLD, 10));
		label_3.setBounds(12, 462, 140, 11);
		panelContent.add(label_3);
		
		JLabel label_12 = new JLabel("SHIVMAX TRADERS");
		label_12.setBounds(225, 462, 180, 15);
		panelContent.add(label_12);
		
		JLabel label_13 = new JLabel("Date");
		label_13.setFont(new Font("Dialog", Font.BOLD, 10));
		label_13.setBounds(468, 445, 81, 11);
		panelContent.add(label_13);
		
		JLabel label_15 = new JLabel("Time");
		label_15.setFont(new Font("Dialog", Font.BOLD, 10));
		label_15.setBounds(468, 458, 70, 11);
		panelContent.add(label_15);
		
		JLabel label_17 = new JLabel("Bill Number");
		label_17.setFont(new Font("Dialog", Font.BOLD, 10));
		label_17.setBounds(462, 473, 70, 11);
		panelContent.add(label_17);
		
		JLabel label_18 = new JLabel("PayType");
		label_18.setFont(new Font("Dialog", Font.BOLD, 10));
		label_18.setBounds(463, 486, 70, 11);
		panelContent.add(label_18);
		
		JLabel label_19 = new JLabel("Identifier");
		label_19.setFont(new Font("Dialog", Font.BOLD, 10));
		label_19.setBounds(12, 479, 107, 11);
		panelContent.add(label_19);
		
		JLabel label_20 = new JLabel("Address:");
		label_20.setFont(new Font("Dialog", Font.BOLD, 10));
		label_20.setBounds(13, 494, 438, 11);
		panelContent.add(label_20);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 510, 570, 159);
		scrollPane_1.getViewport().setBackground(Color.WHITE);
		scrollPane_1.setBackground(Color.WHITE);
		panelContent.add(scrollPane_1);
		
		JLabel label_23 = new JLabel("GST NO of customer:");
		label_23.setFont(new Font("Dialog", Font.BOLD, 9));
		label_23.setBounds(12, 673, 107, 15);
		panelContent.add(label_23);
		
		JLabel label_24 = new JLabel("Previous Due");
		label_24.setFont(new Font("Dialog", Font.BOLD, 10));
		label_24.setBounds(135, 672, 81, 15);
		panelContent.add(label_24);
		
		JLabel label_25 = new JLabel("New label");
		label_25.setFont(new Font("Dialog", Font.BOLD, 9));
		label_25.setBounds(137, 687, 78, 11);
		panelContent.add(label_25);
		
		JLabel label_26 = new JLabel("Pay Ammount");
		label_26.setFont(new Font("Dialog", Font.BOLD, 10));
		label_26.setBounds(231, 672, 83, 15);
		panelContent.add(label_26);
		
		JLabel label_27 = new JLabel("New label");
		label_27.setFont(new Font("Dialog", Font.BOLD, 9));
		label_27.setBounds(236, 687, 78, 11);
		panelContent.add(label_27);
		
		JLabel label_28 = new JLabel("Total Ammount:");
		label_28.setFont(new Font("Dialog", Font.BOLD, 10));
		label_28.setBounds(370, 668, 95, 15);
		panelContent.add(label_28);
		
		JLabel label_29 = new JLabel("IGST:");
		label_29.setFont(new Font("Dialog", Font.BOLD, 10));
		label_29.setBounds(360, 686, 42, 10);
		panelContent.add(label_29);
		
		JLabel label_30 = new JLabel("CG:");
		label_30.setFont(new Font("Dialog", Font.BOLD, 10));
		label_30.setBounds(406, 685, 29, 15);
		panelContent.add(label_30);
		
		JLabel label_31 = new JLabel("None");
		label_31.setFont(new Font("Dialog", Font.BOLD, 10));
		label_31.setBounds(439, 685, 44, 15);
		panelContent.add(label_31);
		
		JLabel label_32 = new JLabel("0");
		label_32.setForeground(Color.RED);
		label_32.setFont(new Font("Dialog", Font.BOLD, 9));
		label_32.setBounds(468, 672, 117, 11);
		panelContent.add(label_32);
		
		JLabel label_33 = new JLabel("+");
		label_33.setFont(new Font("Dialog", Font.BOLD, 10));
		label_33.setBounds(485, 685, 10, 15);
		panelContent.add(label_33);
		
		JLabel label_34 = new JLabel("SG:");
		label_34.setFont(new Font("Dialog", Font.BOLD, 10));
		label_34.setBounds(504, 685, 29, 15);
		panelContent.add(label_34);
		
		JLabel label_35 = new JLabel("None");
		label_35.setFont(new Font("Dialog", Font.BOLD, 10));
		label_35.setBounds(534, 685, 43, 15);
		panelContent.add(label_35);
		
		JLabel label_36 = new JLabel("GST Number: 19HIDPS9322H1ZQ");
		label_36.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 9));
		label_36.setBounds(205, 98, 167, 11);
		panelContent.add(label_36);
		creditPanel.setVisible(true);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Dialog", Font.BOLD, 10));
		btnPrint.setBounds(626, 209, 81, 16);
		contentPane.add(btnPrint);
		
		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Dialog", Font.BOLD, 10));
		btnClose.setBounds(626, 181, 81, 16);
		contentPane.add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				dispose();
			}
		});
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				printCard();
			}
			
		});
	}
}
// previous bill history add