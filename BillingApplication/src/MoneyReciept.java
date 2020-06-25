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

public class MoneyReciept extends JFrame {

	private JPanel contentPane,panelContent;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 MoneyReciept frame = new MoneyReciept();
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

	 
	public MoneyReciept() {
		connCustomer= SqlConnection.customerConnection();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	//	yourframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setBounds(100, 100, 866, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 0, 840, 432);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panelContent = new JPanel();
		panelContent.setBackground(Color.WHITE);
		panelContent.setLayout(null);
		panelContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelContent.setBounds(0, 0, 840, 432);
		panel.add(panelContent);
		
		JLabel lblShivmaxTreders = new JLabel("SHIVMAX TREDERS");
		lblShivmaxTreders.setFont(new Font("URW Chancery L", Font.BOLD | Font.ITALIC, 29));
		lblShivmaxTreders.setBounds(312, 1, 275, 27);
		panelContent.add(lblShivmaxTreders);
		
		JLabel label_1 = new JLabel("Date:");
		label_1.setBounds(675, 21, 42, 14);
		panelContent.add(label_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(70, 254, 695, 11);
		panelContent.add(separator);
		
		JLabel label_4 = new JLabel("(Shivmax Group of Company)");
		label_4.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		label_4.setBounds(346, 30, 231, 15);
		panelContent.add(label_4);
		
		JLabel label_5 = new JLabel("Phone no:  9432591539");
		label_5.setBounds(368, 102, 167, 11);
		panelContent.add(label_5);
		
		JLabel label_6 = new JLabel("Address:");
		label_6.setBounds(335, 57, 63, 21);
		panelContent.add(label_6);
		
		JLabel label_7 = new JLabel("702, PARUI DAS PARA ROAD");
		label_7.setFont(new Font("Dialog", Font.BOLD, 11));
		label_7.setBounds(403, 61, 194, 15);
		panelContent.add(label_7);
		
		JLabel label_8 = new JLabel("NABOPALLY");
		label_8.setFont(new Font("Dialog", Font.BOLD, 11));
		label_8.setBounds(411, 76, 88, 15);
		panelContent.add(label_8);
		
		JLabel label_9 = new JLabel("KOLKATA:- 700061");
		label_9.setFont(new Font("Dialog", Font.BOLD, 11));
		label_9.setBounds(394, 90, 138, 15);
		panelContent.add(label_9);
		
		JLabel label_10 = new JLabel("Customer Name:");
		label_10.setBounds(12, 37, 128, 15);
		panelContent.add(label_10);
		
		JLabel label_11 = new JLabel("Phone Number:");
		label_11.setBounds(12, 103, 110, 24);
		panelContent.add(label_11);
		
		JLabel CName = new JLabel("None");
		CName.setFont(new Font("Khmer Mondulkiri", Font.BOLD | Font.ITALIC, 16));
		CName.setBounds(146, 30, 194, 27);
		panelContent.add(CName);
		
		JLabel PhNo = new JLabel("None");
		PhNo.setFont(new Font("Khmer Mondulkiri", Font.ITALIC, 14));
		PhNo.setBounds(146, 102, 167, 27);
		panelContent.add(PhNo);
		
		JLabel label_14 = new JLabel("Address:");
		label_14.setBounds(12, 189, 73, 15);
		panelContent.add(label_14);
		
		JLabel CAddress = new JLabel("None");
		CAddress.setFont(new Font("Khmer Mondulkiri", Font.ITALIC, 14));
		CAddress.setBounds(97, 186, 706, 21);
		panelContent.add(CAddress);
		
		JLabel label_26 = new JLabel("Time:");
		label_26.setBounds(675, 44, 42, 15);
		panelContent.add(label_26);
		
		JLabel datePrint = new JLabel("None");
		datePrint.setBounds(718, 18, 81, 15);
		panelContent.add(datePrint);
		
		JLabel timePrint = new JLabel("None");
		timePrint.setBounds(718, 44, 107, 15);
		panelContent.add(timePrint);
		
		JLabel lblGstNoOf = new JLabel("GST NO of customer:");
		lblGstNoOf.setBounds(12, 381, 153, 15);
		panelContent.add(lblGstNoOf);
		
		JLabel gstNo = new JLabel("None");
		gstNo.setBounds(15, 399, 125, 15);
		panelContent.add(gstNo);
		
		JLabel lblPaymentType = new JLabel("Pay Type:");
		lblPaymentType.setBounds(608, 98, 73, 15);
		panelContent.add(lblPaymentType);
		
		JLabel typepay = new JLabel("Money Reciept");
		typepay.setBounds(690, 101, 135, 13);
		panelContent.add(typepay);
		
		JLabel lblPreviousDueAmmount = new JLabel("Total Due ammount:");
		lblPreviousDueAmmount.setBounds(320, 378, 143, 15);
		panelContent.add(lblPreviousDueAmmount);
		
		JLabel dueamt = new JLabel("None");
		dueamt.setBounds(349, 395, 103, 15);
		panelContent.add(dueamt);
		dueamt.setForeground(Color.ORANGE);
		
		JLabel label_12 = new JLabel("₹");
		label_12.setBounds(330, 395, 17, 19);
		panelContent.add(label_12);
		
		JLabel lblPayAmmount = new JLabel("Pay Ammount");
		lblPayAmmount.setBounds(663, 379, 114, 15);
		panelContent.add(lblPayAmmount);
		
		JLabel label_3 = new JLabel("₹");
		label_3.setBounds(665, 395, 17, 19);
		panelContent.add(label_3);
		
		JLabel paym = new JLabel("None");
		paym.setBounds(685, 397, 70, 15);
		panelContent.add(paym);
		paym.setForeground(Color.BLUE);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(749, 444, 89, 25);
		contentPane.add(btnPrint);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(65, 444, 81, 25);
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