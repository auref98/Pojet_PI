package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionOracle
	{
	private static Connection connection = null;
	private static ConnectionOracle instance = null;
	
	
	private String url = "jdbc:oracle:thin:@"+ "172.16.110.172:2115:o11etu2"; 
	private String username = "BDNamingException";
	private String password = "12345";
	
	private ConnectionOracle()
		{
		try
			{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, username, password);
			}
		catch(ClassNotFoundException e)
			{
			System.out.println(e.getLocalizedMessage());
			}
		catch(SQLException e)
			{
			System.out.println(e.getLocalizedMessage());
			}
		}
	
	
	public static Connection getInstance()
		{
		if(instance == null) instance = new ConnectionOracle();
		return connection;
		}
	}
