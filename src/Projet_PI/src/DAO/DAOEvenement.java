/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

/**
 * @author ludovic
 */

package DAO;

import java.sql.*;
import java.util.*;

import Bean.Adresse;
import Bean.Evenement;

public class DAOEvenement extends DAO<Evenement>
{

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
			System.out.println("Erreur: findAdr failed !");
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
