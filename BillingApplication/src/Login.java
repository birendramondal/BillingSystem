import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.sql.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;


public class Login {

	private JFrame frmLogin;
	private boolean SET;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	Connection connection= null;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPanel panel;
	/**
	 * Create the application.
	 */
	public Login()
	
	{
		initialize();
		connection = SqlConnection.dbConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBackground(new Color(127, 255, 212));
		frmLogin.setResizable(false);
		frmLogin.setBounds(100, 100, 487, 301);
		frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setVisible(false);
		panel.setBounds(12, 71, 461, 192);
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(0, 23, 109, 15);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setOpaque(false);
		textField.setBounds(127, 18, 169, 25);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setOpaque(false);
		passwordField.setBounds(127, 55, 169, 25);
		panel.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(0, 60, 70, 15);
		panel.add(lblPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 9));
		btnNewButton.setBounds(127, 119, 61, 25);
		panel.add(btnNewButton);
		
		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setOpaque(false);
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
			}
		});
		rdbtnAdmin.setBounds(255, 26, 68, 23);
		frmLogin.getContentPane().add(rdbtnAdmin);
		
		JRadioButton rdbtnUser = new JRadioButton("User");
		rdbtnUser.setOpaque(false);
		rdbtnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SET=true;
				panel.setVisible(true);
			}
		});
		rdbtnUser.setBounds(151, 26, 68, 23);
		frmLogin.getContentPane().add(rdbtnUser);
		ButtonGroup bg =new ButtonGroup();
		bg.add(rdbtnUser);
		bg.add(rdbtnAdmin);
		JLabel lblNewLabel_1 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("login3.jpg")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img));
		lblNewLabel_1.setBounds(0, 0, 485, 275);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(SET==true){
				try
				{
					String querry = "select * from final where username=? and password=?";
					PreparedStatement pst = connection.prepareStatement(querry);
					pst.setString(1, textField.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rs = pst.executeQuery();
					int count=0;
					while(rs.next())
					{
						count++;
					}
					if(count==1)
					{
						JOptionPane.showMessageDialog(null, "Username and password correct");
						frmLogin.dispose();
						fram2 frm = new fram2();
						frm.setVisible(true);
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Username and password NOT correct");
					}
					rs.close();
					pst.close();
					
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, exc);
				}
				}
				else
				{
					try
					{
						String querry = "select * from Admin where username=? and password=?";
						PreparedStatement pst = connection.prepareStatement(querry);
						pst.setString(1, textField.getText());
						pst.setString(2, passwordField.getText());
						ResultSet rs = pst.executeQuery();
						int count=0;
						while(rs.next())
						{
							count++;
						}
						if(count==1)
						{
							JOptionPane.showMessageDialog(null, "Username and password correct");
							frmLogin.dispose();
							Frame frm = new Frame();
							frm.setVisible(true);
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "Username and password NOT correct");
						}
						rs.close();
						pst.close();
						
					}
					catch(Exception exc)
					{
						JOptionPane.showMessageDialog(null, exc);
					}
				}
			}
		});
	}
}
