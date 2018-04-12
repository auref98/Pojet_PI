/*
 * Haute école Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integre: realisation d'un logiciel de gestion des inscriptions à des evenements
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
import java.time.*;
import java.util.*;

import Bean.*;

public class DAOPlage extends DAO<Plage>
{

	/**
	 * 
	 * @param	id correspond à l'id d'une plage
	 * @return 	si une ligne est trouvé dans la table
	 *  		plage, elle est retournée sous forme d'objet
	 *  		sinon return null
	 *
	 */
	@Override
	public Plage find(int id)
	{
		String query = "select * from plage where id = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			
			LocalDate datePlage = resultSet.getDate("datePlage").toLocalDate();
			LocalTime heureDeb = resultSet.getTime("heureDeb").toLocalTime();
			LocalTime heureFin = resultSet.getTime("heureFin").toLocalTime();
			Evenement event = new Evenement();
			event.setId(resultSet.getInt("refEven"));
			ps.close();
			
			return new Plage(id, datePlage, heureDeb, heureFin, event);
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findPlage failed !");
			return null;
		}
	}
	
	/**
	 * 
	 * @param	plage est initialisé
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		inscription, elles seront retournées sous forme d'objet de type LinkedList
	 *  		sinon return null
	 *
	 */
	public LinkedList<Inscription> findListeInscription(Plage plage)
	{
		String query = "select * from inscription where refPlage = ?";
		PreparedStatement ps;
		LinkedList<Inscription> listeIncptn = new LinkedList<Inscription>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, plage.getId());
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idIncptn = resultSet.getInt("id");
				Inscription incptn = new DAOInscription().find(idIncptn);
				incptn.setPlage(plage);
				listeIncptn.add(incptn);
				
			} while(resultSet.next());
			
			return listeIncptn;
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeIncptn failed !");
			return null;
		}
	}

	/**
	 * 
	 * @param	plage est initialisé
	 * @return 	true si l'objet plage à bien été ajouté dans la table plage
	 * 			false si un problème à été rencontré
	 *
	 */
	@Override
	public boolean create(Plage plage)
	{
		String query = "insert into plage values(null, ?, ?, ?, ?)";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setDate(1, java.sql.Date.valueOf(plage.getDate()));
			ps.setTime(2, java.sql.Time.valueOf(plage.getHeureDebut()));
			ps.setTime(3, java.sql.Time.valueOf(plage.getHeureFin()));
			ps.setInt(4, plage.getEve().getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ps.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: createPlage failed !");
			return false;
		}
	}

	/**
	 * 
	 * @param 	plage est initialisé
	 * @return 	true si la ligne dans la table plage à bien été mis à jour
	 * 			false si un problème à été rencontré
	 *
	 */
	@Override
	public boolean update(Plage plage)
	{
		String query = "update plage set datePlage = ?, heureDeb = ?, heureFin = ?, refEven = ? where id = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setDate(1, java.sql.Date.valueOf(plage.getDate()));
			ps.setTime(2, java.sql.Time.valueOf(plage.getHeureDebut()));
			ps.setTime(3, java.sql.Time.valueOf(plage.getHeureFin()));
			ps.setInt(4, plage.getEve().getId());
			ps.setInt(5, plage.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ps.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: updatePlage failed !");
			return false;
		}
	}

	/**
	 * 
	 * @param 	plage est initialisé
	 * @return 	true si la ligne de la table plage à bien été supprimé
	 * 			false si un problème à été rencontré
	 *
	 */
	@Override
	public boolean delete(Plage plage)
	{
		String query = "delete from plage where id = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, plage.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ps.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: deletePlage failed !");
			return false;
		}
	}
}
