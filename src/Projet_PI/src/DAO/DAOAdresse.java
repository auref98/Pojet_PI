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
	@Override
	public Adresse find(int id)
	{
		Adresse adresse = new Adresse();
		String query = "select * from adresse where id = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) return null;
			
			String localite = resultSet.getString("localite");
			int cp = resultSet.getInt("cp");
			String rue = resultSet.getString("rue");
			int numero = resultSet.getInt("numero");
			String boite = resultSet.getString("Boite");
			String pays = resultSet.getString("pays");
			
			return new Adresse(id, localite, cp, rue, numero, boite, pays);
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: create failed !");
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
