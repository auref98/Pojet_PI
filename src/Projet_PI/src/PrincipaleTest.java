import java.sql.*;

import Bean.*;

public class PrincipaleTest
{
	public static void main(String[] args)
	{
		Connection connexionBD = null;
		String url = "jdbc:oracle:thin:@"+ "127.0.0.1:12115:o11etu2 "; //url vers la BD comme vu dans la partie JDBC
		String userName = "BDNamingException";
		String password = "12345";
		
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver ok !");
		} 
		catch (ClassNotFoundException e1) 
		{
			System.out.println("Erreur: Chargement driver failed !");
		}
		
		try
		{
			connexionBD = DriverManager.getConnection(url, userName, password);
			System.out.println("Connection ok !");
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: Connextion failed !");
		}
	}

}
