import java.sql.*;

import Bean.*;
import DAO.*;

public class PrincipaleTest
{
	public static void main(String[] args)
	{
		Connection connexionBD = null;
		String url = "jdbc:oracle:thin:@"+ "172.16.110.172:2115:o11etu2 "; //url vers la BD comme vu dans la partie JDBC
		String userName = "BD2adam";
		String password = "Dessin1$";

		{
			/*on charge avec class.forName(), puis on obtient la connexion avec this.connexionBD= DriverManager.getConnection(…) */
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
		
		System.out.println("Hello World !");
	}

}
