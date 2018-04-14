/*
 * Haute école Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integré: réalisation d'un logiciel de gestion des inscriptions à des événements
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
	 * @param	evenement est initialisé
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		plage, elles seront retournées sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public ArrayList<Plage> findListePlage(Evenement event)
	{
		String query = "select * from plage where refEvenement = ?";
		PreparedStatement ps;
		ArrayList<Plage> listePlage = new ArrayList<Plage>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, event.getId());
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idPlage = resultSet.getInt("id");
				Plage plage = new DAOPlage().find(idPlage);
				plage.setEve(event);
				listePlage.add(plage);
				
			} while(resultSet.next());
			
			return listePlage;
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListePlage failed !");
			return null;
		}
	}
	
	/**
	 * 
	 * @param	evenement est initialisé
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		commentaire, elles seront retournées sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public LinkedList<Commentaire> findListeCom(Evenement event)
	{
		String query = "select * from commentaire where refEvenement = ?";
		PreparedStatement ps;
		LinkedList<Commentaire> listeCom = new LinkedList<Commentaire>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, event.getId());
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idCom = resultSet.getInt("id");
				Commentaire com = new DAOCommentaire().find(idCom);
				com.setEvenement(event);
				listeCom.add(com);
				
			} while(resultSet.next());
			
			return listeCom;
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeCom failed !");
			return null;
		}
	}
	
	/**
	 * 
	 * @param	evenement est initialisé
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		contact, elles seront retournées sous forme d'objet de type LinkedList
	 *  		sinon return null
	 *
	 */
	public LinkedList<Contact> findListeContact(Evenement event)
	{
		String query = "select * from Contact where refEvenement = ?";
		PreparedStatement ps;
		LinkedList<Contact> listeContact = new LinkedList<Contact>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, event.getId());
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idContact = resultSet.getInt("id");
				Contact contact = new DAOContact().find(idContact);
				contact.setEve(event);
				listeContact.add(contact);
				
			} while(resultSet.next());
			
			return listeContact;
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeContact failed !");
			return null;
		}
	}
	
	/**
	 * 
	 * @param	evenement est initialisé
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		Section, elles seront retournées sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public ArrayList<Section> findListeSection(Evenement event)
	{
		String query = "select s.* from EVENEMENT e, SECTION s, CONCERNE c where e.id = c.REFEVEN and s.id = c.REFSECT and e.id = ?";
		PreparedStatement ps;
		ArrayList<Section> listeSection = new ArrayList<Section>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, event.getId());
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idSection = resultSet.getInt("id");
				listeSection.add(new DAOSection().find(idSection));
				
			} while(resultSet.next());
			
			return listeSection;
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeSection failed !");
			return null;
		}
	}
	
	/**
	 * 
	 * @param	evenement est initialisé
	 * @return 	true si l'objet event à bien été ajouté dans la table evenement
	 * 			false si un problème à été rencontré
	 *
	 */
	@Override
	public boolean create(Evenement event)
	{
		String query = "insert into evenement values(null, ?, ?, ?, ?, ?)";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, event.getNom());
			ps.setInt(2, event.getNbParticipantRequis());
			ps.setString(3, event.getDescription());
			ps.setString(4, event.getImage());
			ps.setInt(5, event.getAdresseEve().getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ps.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: createEvent failed !");
			return false;
		}
	}
	
	/**
	 * 
	 * @param 	event est initialisé
	 * @return 	true si la ligne dans la table event à bien été mis à jour
	 * 			false si un problème à été rencontré
	 *
	 */
	@Override
	public boolean update(Evenement event)
	{
		String query = "update evenement set NOM = ?, NBPARTICIPANTREQUIS = ?, DESCRIPTION = ?, IMAGE = ?, REFADDR = ? where id = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, event.getNom());
			ps.setInt(2, event.getNbParticipantRequis());
			ps.setString(3, event.getDescription());
			ps.setString(4, event.getImage());
			ps.setInt(5, event.getAdresseEve().getId());
			ps.setInt(6, event.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ps.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: updateEvent failed !");
			return false;
		}
	}

	/**
	 * 
	 * @param 	event est initialisé
	 * @return 	true si la ligne de la table evenement à bien été supprimé
	 * 			false si un problème à été rencontré
	 *
	 */
	@Override
	public boolean delete(Evenement event)
	{
		String query = "delete from evenement where id = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, event.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ps.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: deleteEvent failed !");
			return false;
		}
	}

}
