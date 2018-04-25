/*
 * Haute �cole Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integr�: r�alisation d'un logiciel de gestion des inscriptions � des �v�nements
 * 
 * Groupe: NamingException {
 * 				Adam Ludovic;
 *				Arnould Killian;
 * 				De Bernardi Christophe;
 * 				Fockedey Aurelien;
 * 				Mathieu Robin;
 * 				Modave Louis;
 * 				}
 */

/**
 * @author ludovic
 */

package DAO;

import java.sql.*;
import java.util.*;

import Bean.*;

public class DAOInscription extends DAO<Inscription>
{
	/**
	 * 
	 * @param	id correspond � l'id d'une inscription
	 * @return 	si une ligne est trouv� dans la table
	 *  		inscription, elle est retourn�e sous forme d'objet
	 *  		sinon return null
	 *
	 */
	@Override
	public Inscription find(int id)
	{
		String query = "select * from inscription where id = ?";
		PreparedStatement ps = null;
		Inscription inscpt = null;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next() == false) throw new SQLException();
			
			boolean valide = (resultSet.getInt("valide") == 0) ? false : true;
			Representant representant = new Representant();
			representant.setId(resultSet.getInt("refRepr"));
			Plage plage = new Plage();
			plage.setId(resultSet.getInt("refPlage"));
			
			inscpt = new Inscription(id, valide, representant, plage);
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findIncptn failed !");
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return inscpt;
	}

	/**
	 * 
	 * @param 	incptn est initialis�
	 * @return 	true si l'objet incptn � bien �t� ajout� dans la table inscription
	 * 			false si un probl�me � �t� rencontr�
	 *
	 */
	@Override
	public boolean create(Inscription incptn)
	{
		String query = "insert into inscription values(null, ?, ?, ?)";
		PreparedStatement ps = null;
		boolean resultat = false;
		
		try
		{
			ps = connection.prepareStatement(query, new String[] {"id"});
			int valide = (incptn.isValide() == true) ? 1 : 0;
			ps.setInt(1, valide);
			ps.setInt(2, incptn.getPlage().getId());
			ps.setInt(3, incptn.getRepresentant().getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ResultSet resultSet = ps.getGeneratedKeys();
			if(resultSet.next()) incptn.setId(resultSet.getInt(1));
			
			resultat = true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: createIncptn failed !");
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return resultat;
	}

	/**
	 * 
	 * @param 	incptn est initialis�
	 * @return 	true si la ligne dans la table inscription � bien �t� mis � jour
	 * 			false si un probl�me � �t� rencontr�
	 *
	 */
	@Override
	public boolean update(Inscription incptn)
	{
		String query = "update inscription set valide = ?, refPlage = ?, refRepr = ? where id = ?";
		PreparedStatement ps = null;
		boolean resultat = false;
		
		try
		{
			ps = connection.prepareStatement(query);
			int valide = (incptn.isValide() == true) ? 1 : 0;
			ps.setInt(1, valide);
			ps.setInt(2, incptn.getPlage().getId());
			ps.setInt(3, incptn.getRepresentant().getId());
			ps.setInt(4, incptn.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			resultat = true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: UpdateIncptn failed !");
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return resultat;
	}

	/**
	 * 
	 * @param 	incptn est initialis�
	 * @return 	true si la ligne de la table inscription � bien �t� supprim�
	 * 			false si un probl�me � �t� rencontr�
	 *
	 */
	@Override
	public boolean delete(Inscription incptn)
	{
		String query = "delete from inscription where id = ?";
		PreparedStatement ps = null;
		boolean resultat = false;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, incptn.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			resultat = true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: deleteIncptn failed !");
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return resultat;
	}
}
