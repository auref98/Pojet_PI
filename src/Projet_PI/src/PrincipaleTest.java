import java.sql.*;

import Bean.*;
import DAO.*;

public class PrincipaleTest
{
	public static void main(String[] args)
	{
		Adresse adrTest = new Adresse(0, "Libramont", 6800, "Pie Martin", 60, "D", "Belgique");
		DAOAdresse daoTest = new DAOAdresse();
		
		daoTest.create(adrTest);
		
		System.out.println("Hello World !");
	}

}
