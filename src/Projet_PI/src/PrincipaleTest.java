import java.sql.*;
import java.util.ArrayList;

import Bean.*;
import DAO.*;

public class PrincipaleTest
{
	public static void main(String[] args)
	{
		Professeur prof = new Professeur(null,null,null,"petitRoux@hers.be",null,0,0);
		//rep.setMail("ludovic.adam@hers.be");
		new DAOProfesseur().create(prof);
		System.out.println(prof.getPassword());
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
