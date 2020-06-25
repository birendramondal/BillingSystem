import java.sql.*;
import javax.swing.*;
public class SqlConnection {
	Connection conn = null;
	public static Connection dbConnection()
	    {
		   try
		   {
			   Class.forName("org.sqlite.JDBC");
			  Connection conn= DriverManager.getConnection("jdbc:sqlite:/home/akash/workspace/BillingApplication/DB/final.sqlite");
			  // Connection conn= DriverManager.getConnection("jdbc:sqlite://D://Billing//data//final.sqlite");
			   //Connection conn= DriverManager.getConnection("jdbc:sqlite::resource:final.sqlite");
			   JOptionPane.showMessageDialog(null, "connect with database!");
			   return conn;
		   }
		   catch(Exception exc)
		   {
			   JOptionPane.showMessageDialog(null, exc);
			   return null;
		   }
	    }
	public static Connection stockConnection()
    {
	   try
	   {
		   Class.forName("org.sqlite.JDBC");
		   Connection connStock= DriverManager.getConnection("jdbc:sqlite:/home/akash/workspace/BillingApplication/DB/StockFinal.sqlite");
		  // Connection connStock= DriverManager.getConnection("jdbc:sqlite://D://Billing//data//StockFinal.sqlite");
		   //Connection connStock= DriverManager.getConnection("jdbc:sqlite::resource:StockFinal.sqlite");
		  // JOptionPane.showMessageDialog(null, "connect with Stock database!");
		   return connStock;
		   
	   }
	   catch(Exception exc)
	   {
		   JOptionPane.showMessageDialog(null, exc);
		   return null;
	   }
    }
	public static Connection customerConnection()
    {
	   try
	   {
		   Class.forName("org.sqlite.JDBC");
		   Connection connCustomer= DriverManager.getConnection("jdbc:sqlite:/home/akash/workspace/BillingApplication/DB/CustomerFinal.sqlite");
		 // Connection connCustomer= DriverManager.getConnection("jdbc:sqlite://D://Billing//data//CustomerFinal.sqlite");
		   //Connection connCustomer= DriverManager.getConnection("jdbc:sqlite::resource:CustomerFinal.sqlite");
		  // JOptionPane.showMessageDialog(null, "connect with Customer database!");
		   return connCustomer;
	   }
	   catch(Exception exc)
	   {
		   JOptionPane.showMessageDialog(null, exc);
		   return null;
	   }
    }


}
