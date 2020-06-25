import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JRadioButton;


public class LogAuth extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField newpass;
	private JLabel lblNewLabel;
	private JPasswordField confopass;
	private boolean SET;
	private JPanel panel; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogAuth frame = new LogAuth();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	Connection connection=null;
	private JRadioButton rdbtnAdmin;
	private JLabel label;
	/**
	 * Create the frame.
	 */
	public LogAuth() {
		connection = SqlConnection.dbConnection();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 674, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnUser = new JRadioButton("User");
		rdbtnUser.setOpaque(false);
		rdbtnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				SET=true;
				panel.setVisible(true);
			}
		});
		rdbtnUser.setBounds(218, 33, 73, 25);
		contentPane.add(rdbtnUser);
		
	    panel = new JPanel();
	    panel.setOpaque(false);
		panel.setVisible(false);
		panel.setBounds(125, 148, 443, 166);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(83, 36, 87, 15);
		panel.add(lblUserName);
		
		username = new JTextField();
		username.setOpaque(false);
		username.setBounds(243, 32, 148, 19);
		panel.add(username);
		username.setColumns(10);
		
		lblNewLabel = new JLabel("New Password");
		lblNewLabel.setBounds(69, 67, 117, 15);
		panel.add(lblNewLabel);
		
		newpass = new JPasswordField();
		newpass.setOpaque(false);
		newpass.setBounds(243, 63, 148, 19);
		panel.add(newpass);
		
		JLabel lblConformPassword = new JLabel("Conform Password");
		lblConformPassword.setBounds(55, 96, 147, 15);
		panel.add(lblConformPassword);
		
		confopass = new JPasswordField();
		confopass.setOpaque(false);
		confopass.setBounds(243, 92, 148, 19);
		panel.add(confopass);
		
		JButton btnReset = new JButton("Login");
		btnReset.setBounds(164, 129, 73, 25);
		panel.add(btnReset);
		
		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setOpaque(false);
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				panel.setVisible(true);
			}
		});
		rdbtnAdmin.setBounds(342, 33, 73, 25);
		contentPane.add(rdbtnAdmin);
		
		label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("logauth1.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 672, 398);
		contentPane.add(label);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(SET==true)
				{
					String a,b;
					a=newpass.getText();
					b=confopass.getText();
					if(new String(a).equals(b))
					{
				try
				{
					
					String querry = "Update final set username ='"+username.getText()+"' ,password='"+newpass.getText()+"'";
				PreparedStatement pst= connection.prepareStatement(querry);
				
				pst.execute();
				
				JOptionPane.showMessageDialog(null, "User's Password and Username Chanege successfully!");
				pst.close();
				
				Frame fm = new Frame();
				fm.setVisible(true);
				dispose();
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, exc);
				}
				   }
					else
					{
						JOptionPane.showMessageDialog(null, "New passwornd and Conform password is not matching!!");
					}
					
				}
				else
				{
					String a,b;
					a=newpass.getText();
					b=confopass.getText();
					if(new String(a).equals(b))
					{
					try
					{
						String querry = "Update Admin set username ='"+username.getText()+"' ,password='"+newpass.getText()+"'";
					PreparedStatement pst= connection.prepareStatement(querry);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Admin's Password and Username Chanege successfully!");
					pst.close();
					Frame fm = new Frame();
					fm.setVisible(true);
					dispose();
					}
					catch(Exception exc)
					{
						JOptionPane.showMessageDialog(null, "admin");
					}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "New passwornd and Conform password is not matching!!");
					}
				}
				
				
			}
		});
	}
}
