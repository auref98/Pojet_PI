/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

/**
 * @author ludovic
 */

package DAO;

import java.sql.*;
import java.util.ArrayList;

import Bean.Adresse;
import Bean.Etudiant;
import Bean.Evenement;

public class DAOAdresse extends DAO<Adresse>
{
	//Return null en cas d'érreur ou si aucune ligne n'a été trouvé
	@Override
	public Adresse find(int id)
	{
		String query = "select * from adresse where id = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			
			String localite = resultSet.getString("localite");
			int codePostal = resultSet.getInt("codePostal");
			String rue = resultSet.getString("rue");
			int numero = resultSet.getInt("numero");
			String boite = resultSet.getString("Boite");
			String pays = resultSet.getString("pays");
			ps.close();
			
			return new Adresse(id, localite, codePostal, rue, numero, boite, pays);
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: find failed !");
			return null;
		}
	}
	
	//Return null en cas d'érreur ou si aucune ligne n'a été trouvé
	public ArrayList<Evenement> findListeEvent(int id)
	{
		ArrayList<Evenement> listeEvent = new ArrayList<Evenement>();
		String query = "select * from evenement where REFADDR = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			do
			{
				String nom = resultSet.getString("nom");
				int nbParticipantRequis = resultSet.getInt("nbParticipantRequis");
				String description = resultSet.getString("description");
				String image = resultSet.getString("image");
				String pays = resultSet.getString("pays");
				ps.close();
				
			} while(resultSet.next());
			
			return new Adresse(id, localite, codePostal, rue, numero, boite, pays);
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeEvent failed !");
			return null;
		}
	}
	
	@Override
	public boolean create(Adresse object)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Adresse object)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Adresse object)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
}
