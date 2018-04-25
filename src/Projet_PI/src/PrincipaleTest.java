import java.sql.*;
import java.util.ArrayList;

import Bean.*;
import DAO.*;

public class PrincipaleTest
{
	public static void main(String[] args)
	{
		Professeur prof = new Professeur("ADAM", "Ludovic", "+32.493.70.46.57", "ludovic.adam@student.hers.be", "e162821", 0, 0);
		
		new DAOProfesseur().create(prof);
	
		System.out.println("Hello World !");
	}

}
