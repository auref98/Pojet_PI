import java.sql.*;
import java.util.ArrayList;

import Bean.*;
import DAO.*;

public class PrincipaleTest
{
	public static void main(String[] args)
	{
		Adresse adr = new Adresse(0, "Libramont", 6800, "vraiment pas loin d ici", 5, "R", "Belgique");
		System.out.println(adr.getId());
		new DAOAdresse().find(adr);
		System.out.println(adr.getId());
		
		System.out.println("Hello World !");
	}

}
