import java.sql.*;
import java.util.ArrayList;

import Bean.*;
import DAO.*;

public class PrincipaleTest
{
	public static void main(String[] args)
	{
		Adresse adr = new DAOAdresse().find(1);
		
		Evenement event = new Evenement(9, "TestEvent2", 3, "Je suis une description", "Je suis une image", adr);
		
		ArrayList<Section> listSect = new ArrayList<Section>();
		listSect.add(new DAOSection().find(2));
		listSect.add(new DAOSection().find(3));
		
		event.setSection(listSect);
		
		new DAOEvenement().update(event);
		System.out.println(event.getId());
	
		System.out.println("Hello World !");
	}

}
