import java.sql.*;
import java.util.ArrayList;

import Bean.*;
import DAO.*;

public class PrincipaleTest
{
	public static void main(String[] args)
	{
		ArrayList<Evenement> listEvent = new DAOEvenement().find(0, 2);

		System.out.println(listEvent.get(0).getNom());
		System.out.println(listEvent.get(1).getNom());
	
		System.out.println("Hello World !");
	}

}
