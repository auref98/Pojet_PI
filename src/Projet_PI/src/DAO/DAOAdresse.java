/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

/**
 * @author ludovic
 */

package DAO;

import java.sql.*;

import Bean.Adresse;

public class DAOAdresse extends DAO<Adresse>
{
	@Override
	public Adresse find(String champ, String value)
	{
		Adresse adresse = new Adresse();
		String query = "select * from adresse where ? like ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, champ);
			ps.setInt(2, );
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: create failed !");
		}
		return null;
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
