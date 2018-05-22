import java.sql.*;
import java.util.ArrayList;

import Bean.*;
import DAO.*;

public class PrincipaleTest
{
	public static void main(String[] args)
	{
		Section sect = new DAOSection().find(1);
		ArrayList<Etudiant> listEtud = new DAOSection().findListeEtudiant(sect);
		ArrayList<Professeur> listProf = new DAOSection().findListeProfesseur(sect);
		
		for(int i = 0; i < listEtud.size(); i++) System.out.println(listEtud.get(i).getMail());
		for(int i = 0; i < listProf.size(); i++) System.out.println(listProf.get(i).getMail());
		
		System.out.println("Hello World !");
	}

}
