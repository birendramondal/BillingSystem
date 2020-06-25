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


public class fram2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fram2 frame = new fram2();
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
	public fram2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 667, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		billingbtn.setBounds(12, 35, 157, 41);
		contentPane.add(billingbtn);
		
		JButton vendorBtn = new JButton("Vendor");
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
		vendorBtn.setBounds(12, 105, 157, 41);
		contentPane.add(vendorBtn);
		
		JButton btnNewButton = new JButton("Customer Details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				CustomerDetails cD = new CustomerDetails();
				cD.setVisible(true);
			}
		});
		btnNewButton.setBounds(12, 176, 157, 41);
		contentPane.add(btnNewButton);
		
		
		
		JButton btnNewButton_1 = new JButton("Due Payment");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreditPayer cp=new CreditPayer();
				cp.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(12, 244, 157, 41);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("frameset.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 0, 665, 486);
		contentPane.add(lblNewLabel);
	}
}
