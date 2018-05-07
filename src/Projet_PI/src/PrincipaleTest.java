import java.sql.*;
import java.util.ArrayList;

import Bean.*;
import DAO.*;

public class PrincipaleTest
{
	public static void main(String[] args)
	{
		Adresse adr = new Adresse(-1, "Libramont-Chevigny", 6800, "Rue de la Cité", 64, null, "Belgique");
		System.out.println(adr.getId());
		new DAOAdresse().find(adr);
		if(adr.getId() != -1) System.out.println("C'est pas null " + adr.getId());
		else System.out.println("c'est null " + adr);

		/*Etudiant etu = new Etudiant();
		etu.setMail("auref@student.hers.be");
		new DAOEtudiant().create(etu);*/
		
		
		System.out.println("Hello World !");
	}

}
