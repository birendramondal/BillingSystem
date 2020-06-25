import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;


public class Frame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
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
	public Frame() {
		setBackground(UIManager.getColor("Button.select"));
		setResizable(false);
		setTitle("Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 690, 532);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton stockBtn = new JButton("Stock");
		stockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				   {
					Stock stk = new Stock();
					stk.setVisible(true);
					
				   }
				catch(Exception exc)
				   {
					JOptionPane.showMessageDialog(null, exc);
				   }
				
			}
		});
		stockBtn.setBounds(12, 25, 157, 41);
		contentPane.add(stockBtn);
		
		JButton billingbtn = new JButton("Billing");
		billingbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				   {
					Billing bill = new Billing();
					bill.setVisible(true);
					
				   }
				catch(Exception exc)
				   {
					JOptionPane.showMessageDialog(null, exc);
				   }
				
			}
		});
		billingbtn.setBounds(12, 96, 157, 41);
		contentPane.add(billingbtn);
		
		JButton vendorBtn = new JButton("Purchase Vendor");
		vendorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				   {
					Vendor vdr = new Vendor();
					vdr.setVisible(true);
					
				   }
				catch(Exception exc)
				   {
					JOptionPane.showMessageDialog(null, exc);
				   }
				
			}
		});
		vendorBtn.setBounds(12, 165, 157, 41);
		contentPane.add(vendorBtn);
		
		JButton payHistorybtn = new JButton("Pruches History");
		payHistorybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				   {
					VendorDetails VendorDetails = new VendorDetails();
					VendorDetails.setVisible(true);
					
				   }
				catch(Exception exc)
				   {
					JOptionPane.showMessageDialog(null, exc);
				   }
				
			}
		});
		payHistorybtn.setBounds(12, 371, 157, 41);
		contentPane.add(payHistorybtn);
		
		JButton LoginAuthority = new JButton("Login Authority");
		LoginAuthority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				try
				   {
					LogAuth logA = new LogAuth();
					logA.setVisible(true);
					
				   }
				catch(Exception exc)
				   {
					JOptionPane.showMessageDialog(null, exc);
				   }
			}
		});
		LoginAuthority.setBounds(12, 306, 157, 41);
		contentPane.add(LoginAuthority);
		
		JButton btnNewButton = new JButton("Customer Details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				CustomerDetails cD = new CustomerDetails();
				cD.setVisible(true);
			}
		});
		btnNewButton.setBounds(12, 236, 157, 41);
		contentPane.add(btnNewButton);
		
		
		
		JButton btnNewButton_2 = new JButton("Due Payment");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreditPayer cp= new CreditPayer();
				cp.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(12, 444, 157, 37);
		contentPane.add(btnNewButton_2);
		
		JLabel lblShivmax = new JLabel("SHIVMAX TRADERS");
		lblShivmax.setForeground(new Color(70, 130, 180));
		lblShivmax.setFont(new Font("Khmer OS", Font.BOLD, 38));
		lblShivmax.setBounds(207, 38, 469, 78);
		contentPane.add(lblShivmax);
		
		JLabel lblParuiDas = new JLabel("702, PARUI DAS PARA ROAD");
		lblParuiDas.setBounds(309, 128, 261, 15);
		contentPane.add(lblParuiDas);
		
		JLabel lblNabopally = new JLabel("NABOPALLY");
		lblNabopally.setBounds(356, 149, 128, 15);
		contentPane.add(lblNabopally);
		
		
		
	}
}
