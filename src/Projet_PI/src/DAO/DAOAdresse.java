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
			ResultSet resulSet = ps.executeQuery(query);
			
			if(resulSet.next() == false) return null;
			
			private int id;
			private String localite;
			  private int codePostal;
			  private String rue;
			  private int numero;
			  private String boit;
			  private String pays;
	
			  private Evenement eve;
			  private ArrayList<Etudiant> reside;

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
