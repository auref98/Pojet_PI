/*
 * Haute école Robert Schuman - Libramont, année scolaire 2017 - 2018
 * Bachelier en informatique de gestion, bloc 2
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
import java.time.LocalDate;
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
		PreparedStatement ps = null;
		Evenement event = null;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();			
			if(resultSet.next() == false) throw new SQLException();
			
			String nom = resultSet.getString("nom");
			int nbParticipantRequis = resultSet.getInt("nbParticipantRequis");
			String description = resultSet.getString("description");
			String image = resultSet.getString("image");
			Adresse adr = new Adresse();
			adr.setId(resultSet.getInt("refaddr"));
			
			event = new Evenement(id, nom, nbParticipantRequis, description, image, adr);
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findEvent failed !");
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
		return event;
	}
	
	public ArrayList<Evenement> find(int debut, int cpt)
	{
		String query = "select e.* from evenement e, plage p where (e.id = p.REFEVEN and p.datePlage >= to_date(?,'yyyy-mm-dd') ) order by p.DATEPLAGE";
		PreparedStatement ps = null;
		ArrayList<Evenement> listEvent = new ArrayList<Evenement>();
		
		LocalDate d = LocalDate.now();
		int jour = d.getDayOfYear();
		int year = (jour - 30 < 0)?1:0;
		jour = (jour - 30 < 0 )?365+(jour-30):jour-30;
		d = LocalDate.ofYearDay(d.getYear()-year, jour);
		try
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, d.toString());
			ResultSet resultSet = ps.executeQuery();
			
			for(int i = 0; i < debut; i++) if(resultSet.next() == false) throw new SQLException();
			for(int i = 0; i < cpt && resultSet.next()  != false; i++)
			{
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				int nbParticipantRequis = resultSet.getInt("nbParticipantRequis");
				String description = resultSet.getString("description");
				String image = resultSet.getString("image");
				Adresse adr = new Adresse();
				adr.setId(resultSet.getInt("refaddr"));
				Evenement event = new Evenement(id, nom, nbParticipantRequis, description, image, adr);
				if(!listEvent.contains(event)) listEvent.add(event);
			}
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findEvent failed !");
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
		return listEvent;
	}

	/**
	 * 
	 * @param	event est initialisé
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		plage, elles seront retournées sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public ArrayList<Plage> findListePlage(Evenement event)
	{
		String query = "select * from plage where refEven = ?";
		PreparedStatement ps = null;
		ArrayList<Plage> listePlage = new ArrayList<Plage>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, event.getId());
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idPlage = resultSet.getInt("id");
				Plage plage = new DAOPlage().find(idPlage);
				plage.setEve(event);
				listePlage.add(plage);
				
			} while(resultSet.next());
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListePlage failed !");
			System.out.println(ex.getMessage());
			listePlage = null;
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
		return listePlage;
	}
	
	/**
	 * 
	 * @param	event est initialisé
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		commentaire, elles seront retournées sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public LinkedList<Commentaire> findListeCom(Evenement event)
	{
		String query = "select * from commentaire where refEven = ?";
		PreparedStatement ps = null;
		LinkedList<Commentaire> listeCom = new LinkedList<Commentaire>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, event.getId());
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idCom = resultSet.getInt("id");
				Commentaire com = new DAOCommentaire().find(idCom);
				com.setEvenement(event);
				listeCom.add(com);
				
			} while(resultSet.next());
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeCom failed !");
			listeCom = null;
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
		return listeCom;
	}
	
	/**
	 * 
	 * @param	event est initialisé
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		contact, elles seront retournées sous forme d'objet de type LinkedList
	 *  		sinon return null
	 *
	 */
	public LinkedList<Contact> findListeContact(Evenement event)
	{
		String query = "select * from Contact where refEvenement = ?";
		PreparedStatement ps = null;
		LinkedList<Contact> listeContact = new LinkedList<Contact>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, event.getId());
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idContact = resultSet.getInt("id");
				Contact contact = new DAOContact().find(idContact);
				contact.setEve(event);
				listeContact.add(contact);
				
			} while(resultSet.next());
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeContact failed !");
			listeContact = null;
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
		return listeContact;
	}
	
	/**
	 * 
	 * @param	event est initialisé
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		Section, elles seront retournées sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public ArrayList<Section> findListeSection(Evenement event)
	{
		String query = "select s.* from EVENEMENT e, SECTION s, CONCERNE c where e.id = c.REFEVEN and s.id = c.REFSECT and e.id = ?";
		PreparedStatement ps = null;
		ArrayList<Section> listeSection = new ArrayList<Section>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, event.getId());
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idSection = resultSet.getInt("id");
				listeSection.add(new DAOSection().find(idSection));
				
			} while(resultSet.next());
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeSection failed !");
			listeSection = null;
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
		return listeSection;
	}
	
	/**
	 * 
	 * @param	event est initialisé
	 * @return 	true si l'objet event à bien été ajouté dans la table evenement
	 * 			false si un problème à été rencontré
	 *
	 */
	@Override
	public boolean create(Evenement event)
	{
		String query = "insert into evenement values(null, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		boolean resultat = false;
		
		try
		{
			ps = connection.prepareStatement(query, new String[] {"id"});
			ps.setString(1, event.getNom());
			ps.setInt(2, event.getNbParticipantsRequis());
			ps.setString(3, event.getDescription());
			ps.setString(4, event.getImage());
			ps.setInt(5, event.getAdresseEve().getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ResultSet resultSet = ps.getGeneratedKeys();
			if(resultSet.next()) event.setId(resultSet.getInt(1));
			
			if(event.getListeSection() != null)
			{
				for(int i = 0; i < event.getListeSection().size(); i++)
				{
					query = "insert into concerne values(?, ?)";
					
					try 
					{
						ps.close();
						ps = connection.prepareStatement(query);
						
						ps.setInt(1, event.getId());
						ps.setInt(2, event.getListeSection().get(i).getId());
						
						if(ps.executeUpdate() == 0) throw new SQLException();
					}
					catch (SQLException e)
					{
						System.out.println("Erreur: createConcerne failed !");
						System.out.println(e.getMessage());

					}
				}
			}
			
			resultat = true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: createEvent failed !");
			resultat = false;
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
	 * @param 	event est initialisé
	 * @return 	true si la ligne dans la table event à bien été mis à jour
	 * 			false si un problème à été rencontré
	 *
	 */
	@Override
	public boolean update(Evenement event)
	{
		String query = "update evenement set NOM = ?, NBPARTICIPANTREQUIS = ?, DESCRIPTION = ?, IMAGE = ?, REFADDR = ? where id = ?";
		PreparedStatement ps = null;
		boolean resultat = false;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, event.getNom());
			ps.setInt(2, event.getNbParticipantsRequis());
			ps.setString(3, event.getDescription());
			ps.setString(4, event.getImage());
			ps.setInt(5, event.getAdresseEve().getId());
			ps.setInt(6, event.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			if(event.getListeSection() != null)
			{
				query = "delete concerne where refEven = ?";
				
				try 
				{
					ps.close();
					ps = connection.prepareStatement(query);
					
					ps.setInt(1, event.getId());
					
					if(ps.executeUpdate() == 0) throw new SQLException();
					
					for(int i = 0; i < event.getListeSection().size(); i++)
					{
						query = "insert into concerne values(?, ?)";
						
						try 
						{
							ps.close();
							ps = connection.prepareStatement(query);
							
							ps.setInt(1, event.getId());
							ps.setInt(2, event.getListeSection().get(i).getId());
							
							if(ps.executeUpdate() == 0) throw new SQLException();
						}
						catch (SQLException e)
						{
							System.out.println("Erreur: updateConcerne failed !");
							System.out.println(e.getMessage());

						}
					}
				}
				catch (SQLException e)
				{
					System.out.println("Erreur: deleteConcerne failed !");
					System.out.println(e.getMessage());

				}
				
			}
			
			resultat = true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: updateEvent failed !");
			resultat = false;
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
	 * @param 	event est initialisé
	 * @return 	true si la ligne de la table evenement à bien été supprimé
	 * 			false si un problème à été rencontré
	 *
	 */
	@Override
	public boolean delete(Evenement event)
	{
		String query = "delete from evenement where id = ?";
		PreparedStatement ps = null;
		boolean resultat = false;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, event.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			resultat = true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: deleteEvent failed !");
			resultat =  false;
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
