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



package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import Bean.*;

/**
 * Classe d'accès à la base de données avec le paramètre générique de type <code>Evenement</code>. <br><br>
 * Hérite de la classe abstraite <code>DAO</code> qui fourni une référence vers l'instance de la classe <code>Connection</code>. <br> 
 * Permet de créer, modifier et supprimer une ligne de la table <code>evenement</code>, ainsi que de récupérer les information d'un événement et des objets liés.
 * @author ludovic
 * @see DAO
 * @see Bean.Evenement
 */
public class DAOEvenement extends DAO<Evenement>
{

//###################################################################################################################################################################
	
	// Conctructeurs
	
//###################################################################################################################################################################

	// Constructeur par défaut, explicité pour la javadoc
	/**
	 * Constructeur par défaut. <br>
	 * Ne fait rien.
	 */
	public DAOEvenement() {}

//###################################################################################################################################################################
	
	// Méthodes
	
//###################################################################################################################################################################

	/**
	 * Permet de récupérer une ligne de la table <code>evenement</code> d'après l'<code>id</code> de la ligne. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>; <br>
	 * récupère les champs nom, nbParticipantRequis, descrition, image de la table. <br><br>
	 * pre: none<br>
	 * post: l'état de la base de donnée est inchangé; <code>id</code> est inchangé
	 * @param	id l'identifiant (BD) de la ligne à récupérer
	 * @return 	un objet de type <code>Evenement</code> si une ligne a été trouvée dans la table <code>evenement</code>; les attributs de cet objet contienent les valeurs contenues dans les 
	 * 			colonnes de la table citées plus haut ainsi qu'une référence vers un objet <code>Adresse</code>lié à l'<code>Evenement</code><br>
	 *  			null dans le cas contraire
	 */
	@Override
	public Evenement find(int id)
	{
		String query = "select * from evenement where id = ?";									// Définit la requête SQL avec un paramètre (id à rechercher)
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour exécuter la requête
		ResultSet resultSet = null;															// Initialise un objet ResultSet pour récupérer le résultat de la requête
		Evenement event = null;															// Initialise un objet Evenement qui sera renvoyé par la méthode
		
		try
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requête définie plus haut	
			ps.setInt(1, id);																// Assigne l'id à chercher au premier (et seul) paramètre de la requête
			resultSet = ps.executeQuery();													// Exécute la requête			
			if(resultSet.next() == false) throw new SQLException();							// Lance une exception si aucune ligne n'a été récupérée de la base de données
			
			// Initialise les attributs de l'objet à renvoyer
			String nom = resultSet.getString("nom");
			int nbParticipantRequis = resultSet.getInt("nbParticipantRequis");
			String description = resultSet.getString("description");
			String image = resultSet.getString("image");
			Adresse adr = new Adresse();													// Initialise un objet Adresse vide 
			adr.setId(resultSet.getInt("refaddr"));												// Assigne l'identifiant de l'adresse liée à l'événement dans l'objet adr
			
			event = new Evenement(id, nom, nbParticipantRequis, description, image, adr);		// Affecte un nouvel Evenement à la variable renvoyée avec les informations récupérée de la BD et la référence de l'Adresse
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findEvent failed !");
		}
	finally																				// Bloc finally fermant le ResultSet et le PreparedStatement
		{
			try
			{
				resultSet.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return event;																		// Renvoie la référence de l'objet Evenement contenant les informations récupérées dans la base de données
	}
	
	/**
	 * Permet de récupérer toutes les lignes de la table <code>evenement</code>. <br><br>
	 * Récupère tous les événements enregistrés. <br><br>
	 * pre: none<br>
	 * post: l'état de la base de donnée est inchangé
	 * @return 	un objet de type <code>ArrayList</code> référençant des objets <code>Evenement</code> contenant les informations de la table <code>evenement</code>; <br>
	 */
	public ArrayList<Evenement> findAll(){
		String query = "select * from evenement";											// Définit la requête SQL
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour exécuter la requête
		ResultSet resultSet = null;															// Initialise un objet ResultSet pour récupérer le résultat de la requête
		ArrayList<Evenement> events = new ArrayList<Evenement>();							// Initialise une ArrayList d'objets Evenement qui sera renvoyée par la méthode
		try
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requête définie plus haut		
			resultSet = ps.executeQuery();													// Exécute la requête
			while(resultSet.next()){														// Tant que le ResultSet fourni un résultat
				// Initialise les attributs de l'objet à ajouter à la liste
				int id = resultSet.getInt("id");												
				String nom = resultSet.getString("nom");
				int nbParticipantRequis = resultSet.getInt("nbParticipantRequis");
				String description = resultSet.getString("description");
				String image = resultSet.getString("image");
				Adresse adr = new Adresse();												// Initialise un objet Adresse vide 
				adr.setId(resultSet.getInt("refaddr"));											// Assigne l'identifiant de l'adresse liée à l'événement dans l'objet adr
				events.add(new Evenement(id, nom, nbParticipantRequis, description, image, adr));		// Ajoute un nouvel Evenement à la liste avec les informations récupérée de la BD et la référence de l'Adresse
			}
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findEvent failed !");
		}
		finally																			// Bloc finally fermant le ResultSet et le PreparedStatement
		{
			try{
				resultSet.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			try
			{
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return events;																	// Renvoie la référence de la liste contenant les objets Evenement contenant les informations récupérées dans la base de données
	}
	
	/**
	 * Permet de récupérer dans la table <code>evenement</code>, un intervalle de lignes correspondant à des événement ultérieurs à la date courante. <br><br>
	 * Méthode utilisée par exemple pour afficher un nombre donné d'évenements par page, en spécifiant le numéro de la ligne de début dans un ensemble de lignes triées par ordre chronologique et le nombre voulu. <br><br>
	 * pre: none<br>
	 * post: l'état de la base de donnée est inchangé
	 * @param debut la position du premier événement à récupérer, dans un ensemble trié par ordre chronologique
	 * @param cpt le nombre d'éléments à récupérer
	 * @return 	un objet de type <code>ArrayList</code> référençant des objets <code>Evenement</code> contenant les informations de la table <code>evenement</code>; <br>
	 */
	public ArrayList<Evenement> find(int debut, int cpt)
	{
		// Définit la requête SQL avec un paramètre
		String query = "select e.* from evenement e, plage p where (e.id = p.REFEVEN and p.datePlage >= to_date(?,'yyyy-mm-dd') ) order by p.DATEPLAGE DESC";
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour exécuter la requête
		ArrayList<Evenement> listEvent = new ArrayList<Evenement>();						// Initialise une ArrayList d'objets Evenement qui sera renvoyée par la méthode
		
		// Obtient une instance de LocalDate dans un format valide pour la requête SQL
		LocalDate d = LocalDate.now();
		int jour = d.getDayOfYear();
		int year = (jour - 30 < 0)?1:0;
		jour = (jour - 30 < 0 )?365+(jour-30):jour-30;
		d = LocalDate.ofYearDay(d.getYear()-year, jour);
		try
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requête définie plus haut
			ps.setString(1, d.toString());													// Assigne la date formatée en chaine de caractères au paramètre de la requête
			ResultSet resultSet = ps.executeQuery();											// Exécute la requête
			
			for(int i = 0; i < debut; i++) if(resultSet.next() == false) throw new SQLException();	// Ignore les "debut" premiers résultats et lance une exception si "debut" est supérieur au nombre de résultat
			for(int i = 0; i < cpt && resultSet.next()  != false; i++)								// Tant que le nombre d'éléments traités est inférieur à cpt et que le ResultSet contient un résultat supplémentaire
			{
				// Initialise les attributs de l'objet à ajouter à la liste
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				int nbParticipantRequis = resultSet.getInt("nbParticipantRequis");
				String description = resultSet.getString("description");
				String image = resultSet.getString("image");
				Adresse adr = new Adresse();
				adr.setId(resultSet.getInt("refaddr"));
				Evenement event = new Evenement(id, nom, nbParticipantRequis, description, image, adr);			// Ajoute un nouvel Evenement à la liste avec les informations récupérée de la BD et la référence de l'Adresse
				boolean add = true;
				for(Evenement eve : listEvent){												// Boucle vérifiant si l'élément à déjà été ajouté pour éviter les doublons
					if(add)add = (!(eve.getId() == event.getId()))?true:false;
				}
				if(add) listEvent.add(event);												// Si l'élément n'est pas encore présent dans la liste, l'y ajoute
				else i--;																	// Sinon, décrémente le compteur
			}
		}
		catch (SQLException ex)															// Si une erreur SQL a été rencontrée ou si aucun résultat n'a été trouvé
		{
			System.out.println("Erreur: findEvent failed !");
		}
		finally																			// Bloc finally fermant  le PreparedStatement
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
		return listEvent;																	// Renvoie la référence de la liste contenant les objets Evenement contenant les informations récupérées dans la base de données
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
		String query = "select * from plage where refEven = ?";									// Définit la requête SQL avec un paramètre
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour exécuter la requête
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
		finally																			// Bloc finally fermant  le PreparedStatement
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
		String query = "select * from commentaire where refEven = ? order by id";				// Définit la requête SQL avec des paramètres
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour exécuter la requête
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
		finally																			// Bloc finally fermant  le PreparedStatement
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
		String query = "select * from Contact where refEvenement = ?";							// Définit la requête SQL avec des paramètres
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour exécuter la requête
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
		finally																			// Bloc finally fermant  le PreparedStatement
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
		// Définit la requête SQL avec des paramètres
		String query = "select s.* from EVENEMENT e, SECTION s, CONCERNE c where e.id = c.REFEVEN and s.id = c.REFSECT and e.id = ?";
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour exécuter la requête
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
		finally																			// Bloc finally fermant  le PreparedStatement
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
	 * Permet d'insérer une ligne de la table <code>evenement</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>; <br>
	 * définit les champs <code>nom</code>, <code>nbParticipantRequis</code>, <code>description</code>, <code>image</code>, <code>refaddr</code> 
	 * de la table. Met également à jour la table "concerne" liant les sections aux événements. <br><br>
	 * pre: none<br>
	 * post:<br>
	 * 		une ligne a été ajoutée à la table  <code>evenement</code> et plusieurs lignes ont pu être ajoutées à la table <code>concerne</code> si la requête SQL a abouti; l'état de la base de données est inchangé sinon<br>
	 * 		<code>event</code> est inchangé
	 * @param	event la référence de l'objet <code>Evenement</code> contenant les informations de la ligne à ajouter
	 * @return 	<code>true</code> si les informations ont été ajoutées avec succès, <code>false</code> sinon
	 */
	@Override
	public boolean create(Evenement event)
	{
		String query = "insert into evenement values(null, ?, ?, ?, ?, ?)";							// Définit la requête SQL avec des paramètres
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour exécuter la requête
		boolean resultat = false;															// Initialise la variable qui sera retournée à false (échec) par défaut
		
		try
		{
			ps = connection.prepareStatement(query, new String[] {"id"});						// Initialise le PreparedStatement avec la requête définie plus haut
			// Assigne les valeurs provenant de l'objet passé en paramètre de la méthode aux paramètres de la requête
			ps.setString(1, event.getNom());
			ps.setInt(2, event.getNbParticipantsRequis());
			ps.setString(3, event.getDescription());
			ps.setString(4, event.getImage());
			ps.setInt(5, event.getAdresseEve().getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();							// Exécute la requête et lance une exception si elle n'aboutit pas	
			
			ResultSet resultSet = ps.getGeneratedKeys();
			if(resultSet.next()) event.setId(resultSet.getInt(1));
			
			if(event.getListeSection() != null)												// Si des objets Section sont liées à l'objet Evenement
			{
				for(int i = 0; i < event.getListeSection().size(); i++)							// Pour toutes les sections concernées par l'événement
				{
					query = "insert into concerne values(?, ?)";								// Définit une requête SQL pour ajouter les liens entre l'événement et la section dans la table "concernce"
					
					try 
					{
						ps.close();
						ps = connection.prepareStatement(query);							// Affecte la requête d'insertion au PreparedStatement
						
						ps.setInt(1, event.getId());											// Assigne l'id de l'Evenement à la requête
						ps.setInt(2, event.getListeSection().get(i).getId());						// Assigne l'id de la section concernée à la requête
						
						if(ps.executeUpdate() == 0) throw new SQLException();				// Exécute la requête et lance une exception si elle n'aboutit pas	
					}
					catch (SQLException e)
					{
						System.out.println("Erreur: createConcerne failed !");
						System.out.println(e.getMessage());

					}
				}
			}
			
			resultat = true;																// Met la valeur de retour à true si toutes les requêtes ont abouti
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: createEvent failed !");
			resultat = false;
		}
		finally																			// Bloc finally fermant  le PreparedStatement
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
		return resultat;																	// Renvoie true si la requête a abouti, false sinon
	}
	
	/**
	 * Permet de modifier une ligne de la table <code>evenement</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>; <br>
	 * redéfinit les champs <code>nom</code>, <code>nbParticipantRequis</code>, <code>description</code>, <code>image</code>, <code>refaddr</code> 
	 * de la table. Met également à jour la table "concerne" liant les sections aux événements. <br><br>
	 * pre: none<br>
	 * post:<br>
	 * 		une ligne de la table  <code>evenement</code> et plusieurs lignes de la table <code>concerne</code> ont pu être modifiées si la requête SQL a abouti; l'état de la base de données est inchangé sinon<br>
	 * 		<code>event</code> est inchangé
	 * @param	event la référence de l'objet <code>Evenement</code> contenant les informations de la ligne à modifier
	 * @return 	<code>true</code> si la ligne a été modifiée avec succès, <code>false</code> sinon
	 */
	@Override
	public boolean update(Evenement event)
	{
		// Définit la requête SQL avec des paramètres
		String query = "update evenement set NOM = ?, NBPARTICIPANTREQUIS = ?, DESCRIPTION = ?, IMAGE = ?, REFADDR = ? where id = ?";
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour exécuter la requête
		boolean resultat = false;															// Initialise la variable qui sera retournée à false (échec) par défaut
		
		try
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requête définie plus haut
			// Assigne les valeurs provenant de l'objet passé en paramètre de la méthode aux paramètres de la requête
			ps.setString(1, event.getNom());
			ps.setInt(2, event.getNbParticipantsRequis());
			ps.setString(3, event.getDescription());
			ps.setString(4, event.getImage());
			ps.setInt(5, event.getAdresseEve().getId());
			ps.setInt(6, event.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();							// Exécute la requête et lance une exception si elle n'aboutit pas
			
			if(event.getListeSection() != null)												// Si des Section sont liées à l'Evenement
			{
				query = "delete concerne where refEven = ?";									// Définit une requête SQL pour supprimer les liens entre l'événement et les sections
				
				try 
				{
					ps.close();
					ps = connection.prepareStatement(query);								// Affecte la requête de suppression au PreparedStatement
					
					ps.setInt(1, event.getId());												// Assigne l'id de l'Evenement à la requête
					
					if(ps.executeUpdate() == 0) throw new SQLException();					// Exécute la requête et lance une exception si elle n'aboutit pas					
					
					for(int i = 0; i < event.getListeSection().size(); i++)						// Pour toutes les sections concernées par l'événement modifié
					{
						query = "insert into concerne values(?, ?)";							// Définit une requête SQL pour ajouter les liens entre l'événement et la section dans la table "concernce"
						
						try 
						{
							ps.close();
							ps = connection.prepareStatement(query);						// Affecte la requête d'insertion au PreparedStatement
							
							ps.setInt(1, event.getId());										// Assigne l'id de l'Evenement à la requête
							ps.setInt(2, event.getListeSection().get(i).getId());					// Assigne l'id de la section concernée à la requête
							
							if(ps.executeUpdate() == 0) throw new SQLException();			// Exécute la requête et lance une exception si elle n'aboutit pas	
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
			
			resultat = true;																// Met la valeur de retour à true si toutes les requêtes ont abouti
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: updateEvent failed !");
			resultat = false;
		}
		finally																			// Bloc finally fermant  le PreparedStatement
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
		return resultat;																	// Renvoie true si la requête a abouti, false sinon
	}
	
	/**
	 * Permet de suprimer une ligne de la table <code>evenement</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>. <br>
	 * pre: none<br>
	 * post: <br>
	 * 		une ligne de la table  <code>etudiant</code> a été supprimée si la table contenait une ligne dont l'identifiant était égal 
	 * 	     	à celui spécifié dans l'attribut <code>id</code> du paramètre <code>event</code>, et si la requête SQL a abouti; l'état de la base de données est inchangé sinon<br>
	 * 		<code>event</code> est inchangé
	 * @param	event la référence de l'objet <code>Evenement</code> contenant l'identifiant BD de la ligne à supprimer
	 * @return 	<code>true</code> si la ligne a été supprimée avec succès, <code>false</code> sinon
	 */
	@Override
	public boolean delete(Evenement event)
	{
		String query = "delete from evenement where id = ?";									// Définit la requête SQL avec un paramètre (id à rechercher)
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour exécuter la requête
		boolean resultat = false;															// Initialise la variable qui sera retournée à false (échec) par défaut
		
		try
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requête définie plus haut
			ps.setInt(1, event.getId());														// Assigne l'id à chercher au premier (et seul) paramètre de la requête
			
			if(ps.executeUpdate() == 0) throw new SQLException();							// Exécute la requête et lance une exception si elle n'aboutit pas
			
			resultat = true;																// Met la valeur de retour à true si la requête a abouti
		}
		catch (SQLException e)															// Si une erreur SQL a été rencontrée ou si la ligne cible n'a pas été trouvée
		{
			System.out.println("Erreur: deleteEvent failed !");
			resultat =  false;
		}
		finally																			// Bloc finally fermant  le PreparedStatement
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
		return resultat;																	// Renvoie true si la requête a abouti, false sinon
	}
}
