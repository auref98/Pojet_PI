import java.sql.*;
import java.util.ArrayList;

import Bean.*;
import DAO.*;

public class PrincipaleTest
{
	public static void main(String[] args)
	{
		ArrayList<Evenement> listEvent = new DAOEvenement().find(1, 2);

		for(int i = 0; i < listEvent.size(); i++)
			System.out.println(listEvent.get(i).getNom());
		
	
		System.out.println("Hello World !");
	}

}
