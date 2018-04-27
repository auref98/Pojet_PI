import java.sql.*;
import java.util.ArrayList;

import Bean.*;
import DAO.*;

public class PrincipaleTest
{
	public static void main(String[] args)
	{
		Representant rep = new DAOProfesseur().find("ludovic.adam@hers.be", "password");


		if(rep == null) System.out.println("Erreur !");
		else
		{
			//System.out.println(rep.getLastName() + " " + rep.getFirstName());
			System.out.println(rep.getLastName() + " " + rep.getFirstName());
		}
	
		System.out.println("Hello World !");
	}

}
