import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Color;


public class CancelBills extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField CustomerID;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelBills frame = new CancelBills();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connCustomer= null;

	/**
	 * Create the frame.
	 */
	private void Reload()
	{
		try
		{
			{
				String querry="select * from CancelBill";
				PreparedStatement pst= connCustomer.prepareStatement(querry);
				ResultSet rs= pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
			    pst.close();
			    rs.close();
			   }
		}
		catch(Exception xcp)
		{
			JOptionPane.showMessageDialog(null, xcp);
		}
	}
	public CancelBills() {
		setTitle("Cancel Bill");
		setResizable(false);
		setBackground(new Color(152, 251, 152));
		connCustomer= SqlConnection.customerConnection();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 725, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Reload");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				panel.setVisible(true);
				Reload();
				
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 11));
		btnNewButton_1.setBounds(625, 221, 81, 25);
		contentPane.add(btnNewButton_1);
		
		panel = new JPanel();
		panel.setVisible(false);
		panel.setOpaque(false);
		panel.setBounds(12, 248, 699, 182);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(false);
		scrollPane.setBounds(0, 30, 600, 149);
		panel.add(scrollPane);
		
		table = new JTable();
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Cancel Bills :");
		lblNewLabel.setBounds(0, 12, 164, 15);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(245, 222, 179));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("cancelBill.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 723, 441);
		contentPane.add(label);
		
		CustomerID = new JTextField();
		CustomerID.setForeground(new Color(255, 255, 0));
		CustomerID.setText("jgc");
		CustomerID.setFont(new Font("Dialog", Font.PLAIN, 12));
		CustomerID.setOpaque(false);
		CustomerID.setBounds(612, 26, 81, 25);
		panel.add(CustomerID);
		CustomerID.setColumns(10);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.setBounds(612, 63, 81, 25);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String querry ="DELETE FROM CancelBill WHERE CustomerID='"+CustomerID.getText()+"'";
				PreparedStatement pst= connCustomer.prepareStatement(querry);
				
				pst.execute();
				pst.close();
				JOptionPane.showMessageDialog(null, "Data Deleted...!!!");
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, exc);
				}
				Reload();
			}
		});
		
		
	}
}
