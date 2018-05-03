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

public class DAOEvenement extends DAO<Evenement>
{
	/**
	 * 
	 * @param	id correspond � l'id d'un evenement
	 * @return 	si une ligne est trouv� dans la table
	 *  		evenement, elle est retourn�e sous forme d'objet
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
		String query = "select e.* from evenement e, plage p where e.id = p.REFEVEN order by p.DATEPLAGE";
		PreparedStatement ps = null;
		ArrayList<Evenement> listEvent = new ArrayList<Evenement>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			
			for(int i = 0; i < debut; i++) if(resultSet.next() != false) throw new SQLException();
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
				else i--;
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
	 * @param	evenement est initialis�
	 * @return 	si une plusieurs lignes ont �t� trouv�es dans la table
	 *  		plage, elles seront retourn�es sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public ArrayList<Plage> findListePlage(Evenement event)
	{
		String query = "select * from plage where refEvenement = ?";
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
	 * @param	evenement est initialis�
	 * @return 	si une plusieurs lignes ont �t� trouv�es dans la table
	 *  		commentaire, elles seront retourn�es sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public LinkedList<Commentaire> findListeCom(Evenement event)
	{
		String query = "select * from commentaire where refEvenement = ?";
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
	 * @param	evenement est initialis�
	 * @return 	si une plusieurs lignes ont �t� trouv�es dans la table
	 *  		contact, elles seront retourn�es sous forme d'objet de type LinkedList
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
	 * @param	evenement est initialis�
	 * @return 	si une plusieurs lignes ont �t� trouv�es dans la table
	 *  		Section, elles seront retourn�es sous forme d'objet de type ArrayList
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
	 * @param	evenement est initialis�
	 * @return 	true si l'objet event � bien �t� ajout� dans la table evenement
	 * 			false si un probl�me � �t� rencontr�
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
			ps.setInt(2, event.getNbParticipantRequis());
			ps.setString(3, event.getDescription());
			ps.setString(4, event.getImage());
			ps.setInt(5, event.getAdresseEve().getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ResultSet resultSet = ps.getGeneratedKeys();
			if(resultSet.next()) event.setId(resultSet.getInt(1));
			
			if(event.getSection() != null)
			{
				for(int i = 0; i < event.getSection().size(); i++)
				{
					query = "insert into concerne values(?, ?)";
					
					try 
					{
						ps.close();
						ps = connection.prepareStatement(query);
						
						ps.setInt(1, event.getId());
						ps.setInt(2, event.getSection().get(i).getId());
						
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
	 * @param 	event est initialis�
	 * @return 	true si la ligne dans la table event � bien �t� mis � jour
	 * 			false si un probl�me � �t� rencontr�
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
			ps.setInt(2, event.getNbParticipantRequis());
			ps.setString(3, event.getDescription());
			ps.setString(4, event.getImage());
			ps.setInt(5, event.getAdresseEve().getId());
			ps.setInt(6, event.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			if(event.getSection() != null)
			{
				query = "delete concerne where refEven = ?";
				
				try 
				{
					ps.close();
					ps = connection.prepareStatement(query);
					
					ps.setInt(1, event.getId());
					
					if(ps.executeUpdate() == 0) throw new SQLException();
					
					for(int i = 0; i < event.getSection().size(); i++)
					{
						query = "insert into concerne values(?, ?)";
						
						try 
						{
							ps.close();
							ps = connection.prepareStatement(query);
							
							ps.setInt(1, event.getId());
							ps.setInt(2, event.getSection().get(i).getId());
							
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
	 * @param 	event est initialis�
	 * @return 	true si la ligne de la table evenement � bien �t� supprim�
	 * 			false si un probl�me � �t� rencontr�
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
