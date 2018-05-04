import java.sql.*;
import java.util.ArrayList;

import Bean.*;
import DAO.*;

public class PrincipaleTest
{
	public static void main(String[] args)
	{
		Representant rep = new Representant(0," "," ","+00.000.00.00.00","ludovic.adam@hers.be","e000000");
		//rep.setMail("ludovic.adam@hers.be");
		new DAORepresentant().create(rep);
		//Representant rep = new DAOProfesseur().create("ludovic.adam@hers.be", "password");


		/*if(rep == null) System.out.println("Erreur !");
		else
		{
			//System.out.println(rep.getLastName() + " " + rep.getFirstName());
			System.out.println(rep.getLastName() + " " + rep.getFirstName());
		}
		*/
		System.out.println("Hello World !");
	}

}
