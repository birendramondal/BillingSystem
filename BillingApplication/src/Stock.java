import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.Font;
import java.sql.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
public class Stock extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnReloadTable;
	private JLabel lblMa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock frame = new Stock();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
     Connection connStock=null;
     private JTextField SerialField;
     private JLabel lblManuallyAddData;
     private JButton btnUpdate;
     private JButton btnDelete;
     private JLabel lblRate;
     private JTextField gstPlane;
     private JTextField rateField;
     private JTextField vendorField;
     private JLabel lblVendor;
     private String Ammount;
     private JTextField Quant;
     private JTextField MatField;
     private JTextField spField;
     private JLabel lblNewLabel_1;
	/**
	 * Create the frame.
	 */
     private void showTable()
     {
    	 try
		   {
			String querry="select * from StockFinal order by Serial_No ASC";
			PreparedStatement pst= connStock.prepareStatement(querry);
			ResultSet rs= pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		   }
		catch(Exception exc)
		   {
			JOptionPane.showMessageDialog(null, exc);
		   }
     }
     private String sum()
	 {
		
		 float qty = Float.parseFloat(Quant.getText());
		 
		 float rte = Float.parseFloat(rateField.getText());
		 
		 float gstp = Float.parseFloat(gstPlane.getText());
		 float a,b;
		 double pro;
		 a=(qty*rte);
	     b=(a*gstp)/100;
	     pro=a+b;
	     
		
		 
		 Ammount=String.valueOf(pro);
		 return Ammount;
	 }
	public Stock() {
		setBackground(UIManager.getColor("Button.select"));
		setTitle("Stock");
		setResizable(false);
		connStock= SqlConnection.stockConnection();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 780, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStock = new JLabel("Stock Details");
		lblStock.setFont(new Font("FreeSans", Font.BOLD, 17));
		lblStock.setBounds(35, 0, 163, 25);
		contentPane.add(lblStock);
		
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBounds(0, 37, 778, 103);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnReloadTable = new JButton("Reload");
		btnReloadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showTable();
				
				
			}
		});
		btnReloadTable.setBounds(686, 12, 84, 25);
		contentPane.add(btnReloadTable);
		
		lblMa = new JLabel("Material name");
		lblMa.setBounds(23, 258, 120, 15);
		contentPane.add(lblMa);
		
		JLabel lblNewLabel = new JLabel("Quantity");
		lblNewLabel.setBounds(23, 290, 70, 15);
		contentPane.add(lblNewLabel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				sum();
				
				try
				{
					String querry = "insert into StockFinal (Serial_No ,Materials, Quantity,Purchase_Rate,GST,Vendor_Name,Total_Ammount,Selling_Price) values(?,?,?,?,?,?,?,?) ";
				PreparedStatement pst= connStock.prepareStatement(querry);
				pst.setString(1, SerialField.getText());
				pst.setString(2, MatField.getText() );
				pst.setString(3, Quant.getText());
				pst.setString(4, rateField.getText());
				pst.setString(5, gstPlane.getText());
				pst.setString(6, vendorField.getText());
				pst.setString(7, Ammount);
				pst.setString(8, spField.getText());
				pst.execute();
				pst.close();
				JOptionPane.showMessageDialog(null, "Data Saved");
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, "Material already Exist");
				}
				showTable();
			}
		});
		btnAdd.setBounds(368, 242, 117, 25);
		contentPane.add(btnAdd);
		
		SerialField = new JTextField();
		SerialField.setOpaque(false);
		SerialField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//all data should selected 
				try
				   {
					String querry="select * from StockFinal WHERE Serial_No=?";
					PreparedStatement pst= connStock.prepareStatement(querry);
					pst.setString(1, SerialField.getText());
					ResultSet rs= pst.executeQuery();
					while(rs.next())
				    {
					//JOptionPane.showMessageDialog(null, "enter");
				    SerialField.setText(rs.getString("Serial_No"));
				    MatField.setText(rs.getString("Materials")); 
				    vendorField.setText(rs.getString("Vendor_Name"));
				    rateField.setText(rs.getString("Purchase_Rate"));
				    gstPlane.setText(rs.getString("GST"));
				    Quant.setText(rs.getString("Quantity"));
					
				    }
				pst.close();
				rs.close();
				   }
				catch(Exception exc)
				   {
					JOptionPane.showMessageDialog(null, exc);
				   }
				
				
			}
		});
		SerialField.setBounds(160, 215, 137, 25);
		contentPane.add(SerialField);
		SerialField.setColumns(10);
		
		JLabel lblSerialNo = new JLabel("Serial No.");
		lblSerialNo.setBounds(23, 220, 70, 15);
		contentPane.add(lblSerialNo);
		
		lblManuallyAddData = new JLabel("Manually Add data to Stock");
		lblManuallyAddData.setForeground(UIManager.getColor("OptionPane.errorDialog.titlePane.shadow"));
		lblManuallyAddData.setFont(new Font("Dialog", Font.BOLD, 16));
		lblManuallyAddData.setBounds(35, 165, 243, 25);
		contentPane.add(lblManuallyAddData);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
                sum();
               
				try
				{
					String querry = "Update StockFinal set Serial_No ='"+SerialField.getText()+"',Materials='"+MatField.getText()+"', Quantity='"+Quant.getText()+"',Vendor_Name='"+vendorField.getText()+"',Purchase_Rate='"+rateField.getText()+"',GST='"+gstPlane.getText()+"',Total_Ammount='"+Ammount+"',Selling_Price='"+spField.getText()+"' where Serial_No ='"+SerialField.getText()+"' ";
				PreparedStatement pst= connStock.prepareStatement(querry);
				
				pst.execute();
				
				JOptionPane.showMessageDialog(null, "Data Update...!!!");
				pst.close();
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, exc);
				}
				showTable();
				
			}
		});
		btnUpdate.setBounds(368, 279, 117, 25);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String querry = "Delete from StockFinal where Serial_No ='"+SerialField.getText()+"'";
				PreparedStatement pst= connStock.prepareStatement(querry);
				
				pst.execute();
				pst.close();
				JOptionPane.showMessageDialog(null, "Data Deleted...!!!");
				}
				catch(Exception exc)
				{
					JOptionPane.showMessageDialog(null, exc);
				}
				showTable();
				
			}
		});
		btnDelete.setBounds(368, 316, 117, 25);
		contentPane.add(btnDelete);
		
		lblRate = new JLabel("Purchase Rate");
		lblRate.setBounds(33, 354, 110, 25);
		contentPane.add(lblRate);
		
		gstPlane = new JTextField();
		gstPlane.setOpaque(false);
		gstPlane.setBounds(160, 389, 137, 25);
		contentPane.add(gstPlane);
		gstPlane.setColumns(10);
		
		JLabel lblGst = new JLabel("GST(%)");
		lblGst.setBounds(29, 389, 55, 25);
		contentPane.add(lblGst);
		
		rateField = new JTextField();
		rateField.setOpaque(false);
		rateField.setBounds(160, 356, 137, 25);
		contentPane.add(rateField);
		rateField.setColumns(10);
		
		vendorField = new JTextField();
		vendorField.setOpaque(false);
		vendorField.setBounds(160, 323, 137, 25);
		contentPane.add(vendorField);
		vendorField.setColumns(10);
		
		lblVendor = new JLabel("Vendor");
		lblVendor.setBounds(29, 322, 70, 27);
		contentPane.add(lblVendor);
		
		Quant = new JTextField();
		Quant.setOpaque(false);
		Quant.setBounds(160, 289, 137, 25);
		contentPane.add(Quant);
		Quant.setColumns(10);
		
		MatField = new JTextField();
		MatField.setOpaque(false);
		MatField.setBounds(161, 253, 137, 25);
		contentPane.add(MatField);
		MatField.setColumns(10);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					connStock.close();
				} catch (SQLException e1)
				{
					
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnClose.setBounds(368, 421, 117, 25);
		contentPane.add(btnClose);
		
		spField = new JTextField();
		spField.setOpaque(false);
		spField.setBounds(160, 422, 137, 25);
		contentPane.add(spField);
		spField.setColumns(10);
		
		JLabel lblSellingPrice = new JLabel("Selling Price");
		lblSellingPrice.setBounds(23, 427, 103, 15);
		contentPane.add(lblSellingPrice);
		
		JButton btnNewButton = new JButton("Cancel Bills");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				CancelBills cb= new CancelBills();
				cb.setVisible(true);
			}
		});
		btnNewButton.setBounds(368, 381, 117, 25);
		contentPane.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("stockproperone.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img));
		lblNewLabel_1.setBounds(0, -21, 778, 479);
		contentPane.add(lblNewLabel_1);
		
		
	}
}
