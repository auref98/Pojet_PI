/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

/**
 * @author ludovic
 */

package DAO;

import java.sql.*;
import java.util.*;

import Bean.*;

public class DAOEvenement extends DAO<Evenement>
{
	/**
	 * 
	 * @param	id correspond à l'id d'un evenement
	 * @return 	si une ligne est trouvé dans la table
	 *  		evenement, elle est retournée sous forme d'objet
	 *  		sinon return null
	 *
	 */
	@Override
	public Evenement find(int id)
	{
		String query = "select * from evenement where id = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			
			String nom = resultSet.getString("nom");
			int nbParticipantRequis = resultSet.getInt("nbParticipantRequis");
			String description = resultSet.getString("description");
			String image = resultSet.getString("image");
			Adresse adr = new Adresse();
			adr.setId(resultSet.getInt("refaddr"));
			ps.close();
			
			return new Evenement(id, nom, nbParticipantRequis, description, image, adr);
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findEvent failed !");
			return null;
		}
	}

	/**
	 * 
	 * @param	id correspond à l'id d'un evenement
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		plage, elles seront retournées sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public ArrayList<Plage> findListePlage(int id)
	{
		String query = "select * from plage where refEvenement = ?";
		PreparedStatement ps;
		ArrayList<Plage> listePlage = new ArrayList<Plage>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idPlage = resultSet.getInt("id");
				listePlage.add(new DAOPlage().find(idPlage));
				
			} while(resultSet.next());
			
			return listePlage;
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListePlage failed !");
			return null;
		}
	}
	
	@Override
	public boolean create(Evenement object)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Evenement object)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Evenement object)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
