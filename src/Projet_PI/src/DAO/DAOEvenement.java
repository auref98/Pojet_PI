/*
 * Haute �cole Robert Schuman - Libramont, ann�e scolaire 2017 - 2018
 * Bachelier en informatique de gestion, bloc 2
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



package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import Bean.*;

/**
 * Classe d'acc�s � la base de donn�es avec le param�tre g�n�rique de type <code>Evenement</code>. <br><br>
 * H�rite de la classe abstraite <code>DAO</code> qui fourni une r�f�rence vers l'instance de la classe <code>Connection</code>. <br> 
 * Permet de cr�er, modifier et supprimer une ligne de la table <code>evenement</code>, ainsi que de r�cup�rer les information d'un �v�nement et des objets li�s.
 * @author ludovic
 * @see DAO
 * @see Bean.Evenement
 */
public class DAOEvenement extends DAO<Evenement>
{

//###################################################################################################################################################################
	
	// Conctructeurs
	
//###################################################################################################################################################################

	// Constructeur par d�faut, explicit� pour la javadoc
	/**
	 * Constructeur par d�faut. <br>
	 * Ne fait rien.
	 */
	public DAOEvenement() {}

//###################################################################################################################################################################
	
	// M�thodes
	
//###################################################################################################################################################################

	/**
	 * Permet de r�cup�rer une ligne de la table <code>evenement</code> d'apr�s l'<code>id</code> de la ligne. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>; <br>
	 * r�cup�re les champs nom, nbParticipantRequis, descrition, image de la table. <br><br>
	 * pre: none<br>
	 * post: l'�tat de la base de donn�e est inchang�; <code>id</code> est inchang�
	 * @param	id l'identifiant (BD) de la ligne � r�cup�rer
	 * @return 	un objet de type <code>Evenement</code> si une ligne a �t� trouv�e dans la table <code>evenement</code>; les attributs de cet objet contienent les valeurs contenues dans les 
	 * 			colonnes de la table cit�es plus haut ainsi qu'une r�f�rence vers un objet <code>Adresse</code>li� � l'<code>Evenement</code><br>
	 *  			null dans le cas contraire
	 */
	@Override
	public Evenement find(int id)
	{
		String query = "select * from evenement where id = ?";									// D�finit la requ�te SQL avec un param�tre (id � rechercher)
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour ex�cuter la requ�te
		ResultSet resultSet = null;															// Initialise un objet ResultSet pour r�cup�rer le r�sultat de la requ�te
		Evenement event = null;															// Initialise un objet Evenement qui sera renvoy� par la m�thode
		
		try
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requ�te d�finie plus haut	
			ps.setInt(1, id);																// Assigne l'id � chercher au premier (et seul) param�tre de la requ�te
			resultSet = ps.executeQuery();													// Ex�cute la requ�te			
			if(resultSet.next() == false) throw new SQLException();							// Lance une exception si aucune ligne n'a �t� r�cup�r�e de la base de donn�es
			
			// Initialise les attributs de l'objet � renvoyer
			String nom = resultSet.getString("nom");
			int nbParticipantRequis = resultSet.getInt("nbParticipantRequis");
			String description = resultSet.getString("description");
			String image = resultSet.getString("image");
			Adresse adr = new Adresse();													// Initialise un objet Adresse vide 
			adr.setId(resultSet.getInt("refaddr"));												// Assigne l'identifiant de l'adresse li�e � l'�v�nement dans l'objet adr
			
			event = new Evenement(id, nom, nbParticipantRequis, description, image, adr);		// Affecte un nouvel Evenement � la variable renvoy�e avec les informations r�cup�r�e de la BD et la r�f�rence de l'Adresse
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
		return event;																		// Renvoie la r�f�rence de l'objet Evenement contenant les informations r�cup�r�es dans la base de donn�es
	}
	
	/**
	 * Permet de r�cup�rer toutes les lignes de la table <code>evenement</code>. <br><br>
	 * R�cup�re tous les �v�nements enregistr�s. <br><br>
	 * pre: none<br>
	 * post: l'�tat de la base de donn�e est inchang�
	 * @return 	un objet de type <code>ArrayList</code> r�f�ren�ant des objets <code>Evenement</code> contenant les informations de la table <code>evenement</code>; <br>
	 */
	public ArrayList<Evenement> findAll(){
		String query = "select * from evenement";											// D�finit la requ�te SQL
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour ex�cuter la requ�te
		ResultSet resultSet = null;															// Initialise un objet ResultSet pour r�cup�rer le r�sultat de la requ�te
		ArrayList<Evenement> events = new ArrayList<Evenement>();							// Initialise une ArrayList d'objets Evenement qui sera renvoy�e par la m�thode
		try
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requ�te d�finie plus haut		
			resultSet = ps.executeQuery();													// Ex�cute la requ�te
			while(resultSet.next()){														// Tant que le ResultSet fourni un r�sultat
				// Initialise les attributs de l'objet � ajouter � la liste
				int id = resultSet.getInt("id");												
				String nom = resultSet.getString("nom");
				int nbParticipantRequis = resultSet.getInt("nbParticipantRequis");
				String description = resultSet.getString("description");
				String image = resultSet.getString("image");
				Adresse adr = new Adresse();												// Initialise un objet Adresse vide 
				adr.setId(resultSet.getInt("refaddr"));											// Assigne l'identifiant de l'adresse li�e � l'�v�nement dans l'objet adr
				events.add(new Evenement(id, nom, nbParticipantRequis, description, image, adr));		// Ajoute un nouvel Evenement � la liste avec les informations r�cup�r�e de la BD et la r�f�rence de l'Adresse
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
		return events;																	// Renvoie la r�f�rence de la liste contenant les objets Evenement contenant les informations r�cup�r�es dans la base de donn�es
	}
	
	/**
	 * Permet de r�cup�rer dans la table <code>evenement</code>, un intervalle de lignes correspondant � des �v�nement ult�rieurs � la date courante. <br><br>
	 * M�thode utilis�e par exemple pour afficher un nombre donn� d'�venements par page, en sp�cifiant le num�ro de la ligne de d�but dans un ensemble de lignes tri�es par ordre chronologique et le nombre voulu. <br><br>
	 * pre: none<br>
	 * post: l'�tat de la base de donn�e est inchang�
	 * @param debut la position du premier �v�nement � r�cup�rer, dans un ensemble tri� par ordre chronologique
	 * @param cpt le nombre d'�l�ments � r�cup�rer
	 * @return 	un objet de type <code>ArrayList</code> r�f�ren�ant des objets <code>Evenement</code> contenant les informations de la table <code>evenement</code>; <br>
	 */
	public ArrayList<Evenement> find(int debut, int cpt)
	{
		// D�finit la requ�te SQL avec un param�tre
		String query = "select e.* from evenement e, plage p where (e.id = p.REFEVEN and p.datePlage >= to_date(?,'yyyy-mm-dd') ) order by p.DATEPLAGE DESC";
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour ex�cuter la requ�te
		ArrayList<Evenement> listEvent = new ArrayList<Evenement>();						// Initialise une ArrayList d'objets Evenement qui sera renvoy�e par la m�thode
		
		// Obtient une instance de LocalDate dans un format valide pour la requ�te SQL
		LocalDate d = LocalDate.now();
		int jour = d.getDayOfYear();
		int year = (jour - 30 < 0)?1:0;
		jour = (jour - 30 < 0 )?365+(jour-30):jour-30;
		d = LocalDate.ofYearDay(d.getYear()-year, jour);
		try
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			ps.setString(1, d.toString());													// Assigne la date format�e en chaine de caract�res au param�tre de la requ�te
			ResultSet resultSet = ps.executeQuery();											// Ex�cute la requ�te
			
			for(int i = 0; i < debut; i++) if(resultSet.next() == false) throw new SQLException();	// Ignore les "debut" premiers r�sultats et lance une exception si "debut" est sup�rieur au nombre de r�sultat
			for(int i = 0; i < cpt && resultSet.next()  != false; i++)								// Tant que le nombre d'�l�ments trait�s est inf�rieur � cpt et que le ResultSet contient un r�sultat suppl�mentaire
			{
				// Initialise les attributs de l'objet � ajouter � la liste
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				int nbParticipantRequis = resultSet.getInt("nbParticipantRequis");
				String description = resultSet.getString("description");
				String image = resultSet.getString("image");
				Adresse adr = new Adresse();
				adr.setId(resultSet.getInt("refaddr"));
				Evenement event = new Evenement(id, nom, nbParticipantRequis, description, image, adr);			// Ajoute un nouvel Evenement � la liste avec les informations r�cup�r�e de la BD et la r�f�rence de l'Adresse
				boolean add = true;
				for(Evenement eve : listEvent){												// Boucle v�rifiant si l'�l�ment � d�j� �t� ajout� pour �viter les doublons
					if(add)add = (!(eve.getId() == event.getId()))?true:false;
				}
				if(add) listEvent.add(event);												// Si l'�l�ment n'est pas encore pr�sent dans la liste, l'y ajoute
				else i--;																	// Sinon, d�cr�mente le compteur
			}
		}
		catch (SQLException ex)															// Si une erreur SQL a �t� rencontr�e ou si aucun r�sultat n'a �t� trouv�
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
		return listEvent;																	// Renvoie la r�f�rence de la liste contenant les objets Evenement contenant les informations r�cup�r�es dans la base de donn�es
	}

	/**
	 * 
	 * @param	event est initialis�
	 * @return 	si une plusieurs lignes ont �t� trouv�es dans la table
	 *  		plage, elles seront retourn�es sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public ArrayList<Plage> findListePlage(Evenement event)
	{
		String query = "select * from plage where refEven = ?";									// D�finit la requ�te SQL avec un param�tre
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour ex�cuter la requ�te
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
	 * @param	event est initialis�
	 * @return 	si une plusieurs lignes ont �t� trouv�es dans la table
	 *  		commentaire, elles seront retourn�es sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public LinkedList<Commentaire> findListeCom(Evenement event)
	{
		String query = "select * from commentaire where refEven = ? order by id";				// D�finit la requ�te SQL avec des param�tres
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour ex�cuter la requ�te
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
	 * @param	event est initialis�
	 * @return 	si une plusieurs lignes ont �t� trouv�es dans la table
	 *  		contact, elles seront retourn�es sous forme d'objet de type LinkedList
	 *  		sinon return null
	 *
	 */
	public LinkedList<Contact> findListeContact(Evenement event)
	{
		String query = "select * from Contact where refEvenement = ?";							// D�finit la requ�te SQL avec des param�tres
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour ex�cuter la requ�te
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
	 * @param	event est initialis�
	 * @return 	si une plusieurs lignes ont �t� trouv�es dans la table
	 *  		Section, elles seront retourn�es sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public ArrayList<Section> findListeSection(Evenement event)
	{
		// D�finit la requ�te SQL avec des param�tres
		String query = "select s.* from EVENEMENT e, SECTION s, CONCERNE c where e.id = c.REFEVEN and s.id = c.REFSECT and e.id = ?";
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour ex�cuter la requ�te
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
	 * Permet d'ins�rer une ligne de la table <code>evenement</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>; <br>
	 * d�finit les champs <code>nom</code>, <code>nbParticipantRequis</code>, <code>description</code>, <code>image</code>, <code>refaddr</code> 
	 * de la table. Met �galement � jour la table "concerne" liant les sections aux �v�nements. <br><br>
	 * pre: none<br>
	 * post:<br>
	 * 		une ligne a �t� ajout�e � la table  <code>evenement</code> et plusieurs lignes ont pu �tre ajout�es � la table <code>concerne</code> si la requ�te SQL a abouti; l'�tat de la base de donn�es est inchang� sinon<br>
	 * 		<code>event</code> est inchang�
	 * @param	event la r�f�rence de l'objet <code>Evenement</code> contenant les informations de la ligne � ajouter
	 * @return 	<code>true</code> si les informations ont �t� ajout�es avec succ�s, <code>false</code> sinon
	 */
	@Override
	public boolean create(Evenement event)
	{
		String query = "insert into evenement values(null, ?, ?, ?, ?, ?)";							// D�finit la requ�te SQL avec des param�tres
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour ex�cuter la requ�te
		boolean resultat = false;															// Initialise la variable qui sera retourn�e � false (�chec) par d�faut
		
		try
		{
			ps = connection.prepareStatement(query, new String[] {"id"});						// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			// Assigne les valeurs provenant de l'objet pass� en param�tre de la m�thode aux param�tres de la requ�te
			ps.setString(1, event.getNom());
			ps.setInt(2, event.getNbParticipantsRequis());
			ps.setString(3, event.getDescription());
			ps.setString(4, event.getImage());
			ps.setInt(5, event.getAdresseEve().getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();							// Ex�cute la requ�te et lance une exception si elle n'aboutit pas	
			
			ResultSet resultSet = ps.getGeneratedKeys();
			if(resultSet.next()) event.setId(resultSet.getInt(1));
			
			if(event.getListeSection() != null)												// Si des objets Section sont li�es � l'objet Evenement
			{
				for(int i = 0; i < event.getListeSection().size(); i++)							// Pour toutes les sections concern�es par l'�v�nement
				{
					query = "insert into concerne values(?, ?)";								// D�finit une requ�te SQL pour ajouter les liens entre l'�v�nement et la section dans la table "concernce"
					
					try 
					{
						ps.close();
						ps = connection.prepareStatement(query);							// Affecte la requ�te d'insertion au PreparedStatement
						
						ps.setInt(1, event.getId());											// Assigne l'id de l'Evenement � la requ�te
						ps.setInt(2, event.getListeSection().get(i).getId());						// Assigne l'id de la section concern�e � la requ�te
						
						if(ps.executeUpdate() == 0) throw new SQLException();				// Ex�cute la requ�te et lance une exception si elle n'aboutit pas	
					}
					catch (SQLException e)
					{
						System.out.println("Erreur: createConcerne failed !");
						System.out.println(e.getMessage());

					}
				}
			}
			
			resultat = true;																// Met la valeur de retour � true si toutes les requ�tes ont abouti
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
		return resultat;																	// Renvoie true si la requ�te a abouti, false sinon
	}
	
	/**
	 * Permet de modifier une ligne de la table <code>evenement</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>; <br>
	 * red�finit les champs <code>nom</code>, <code>nbParticipantRequis</code>, <code>description</code>, <code>image</code>, <code>refaddr</code> 
	 * de la table. Met �galement � jour la table "concerne" liant les sections aux �v�nements. <br><br>
	 * pre: none<br>
	 * post:<br>
	 * 		une ligne de la table  <code>evenement</code> et plusieurs lignes de la table <code>concerne</code> ont pu �tre modifi�es si la requ�te SQL a abouti; l'�tat de la base de donn�es est inchang� sinon<br>
	 * 		<code>event</code> est inchang�
	 * @param	event la r�f�rence de l'objet <code>Evenement</code> contenant les informations de la ligne � modifier
	 * @return 	<code>true</code> si la ligne a �t� modifi�e avec succ�s, <code>false</code> sinon
	 */
	@Override
	public boolean update(Evenement event)
	{
		// D�finit la requ�te SQL avec des param�tres
		String query = "update evenement set NOM = ?, NBPARTICIPANTREQUIS = ?, DESCRIPTION = ?, IMAGE = ?, REFADDR = ? where id = ?";
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour ex�cuter la requ�te
		boolean resultat = false;															// Initialise la variable qui sera retourn�e � false (�chec) par d�faut
		
		try
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			// Assigne les valeurs provenant de l'objet pass� en param�tre de la m�thode aux param�tres de la requ�te
			ps.setString(1, event.getNom());
			ps.setInt(2, event.getNbParticipantsRequis());
			ps.setString(3, event.getDescription());
			ps.setString(4, event.getImage());
			ps.setInt(5, event.getAdresseEve().getId());
			ps.setInt(6, event.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();							// Ex�cute la requ�te et lance une exception si elle n'aboutit pas
			
			if(event.getListeSection() != null)												// Si des Section sont li�es � l'Evenement
			{
				query = "delete concerne where refEven = ?";									// D�finit une requ�te SQL pour supprimer les liens entre l'�v�nement et les sections
				
				try 
				{
					ps.close();
					ps = connection.prepareStatement(query);								// Affecte la requ�te de suppression au PreparedStatement
					
					ps.setInt(1, event.getId());												// Assigne l'id de l'Evenement � la requ�te
					
					if(ps.executeUpdate() == 0) throw new SQLException();					// Ex�cute la requ�te et lance une exception si elle n'aboutit pas					
					
					for(int i = 0; i < event.getListeSection().size(); i++)						// Pour toutes les sections concern�es par l'�v�nement modifi�
					{
						query = "insert into concerne values(?, ?)";							// D�finit une requ�te SQL pour ajouter les liens entre l'�v�nement et la section dans la table "concernce"
						
						try 
						{
							ps.close();
							ps = connection.prepareStatement(query);						// Affecte la requ�te d'insertion au PreparedStatement
							
							ps.setInt(1, event.getId());										// Assigne l'id de l'Evenement � la requ�te
							ps.setInt(2, event.getListeSection().get(i).getId());					// Assigne l'id de la section concern�e � la requ�te
							
							if(ps.executeUpdate() == 0) throw new SQLException();			// Ex�cute la requ�te et lance une exception si elle n'aboutit pas	
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
			
			resultat = true;																// Met la valeur de retour � true si toutes les requ�tes ont abouti
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
		return resultat;																	// Renvoie true si la requ�te a abouti, false sinon
	}
	
	/**
	 * Permet de suprimer une ligne de la table <code>evenement</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>. <br>
	 * pre: none<br>
	 * post: <br>
	 * 		une ligne de la table  <code>etudiant</code> a �t� supprim�e si la table contenait une ligne dont l'identifiant �tait �gal 
	 * 	     	� celui sp�cifi� dans l'attribut <code>id</code> du param�tre <code>event</code>, et si la requ�te SQL a abouti; l'�tat de la base de donn�es est inchang� sinon<br>
	 * 		<code>event</code> est inchang�
	 * @param	event la r�f�rence de l'objet <code>Evenement</code> contenant l'identifiant BD de la ligne � supprimer
	 * @return 	<code>true</code> si la ligne a �t� supprim�e avec succ�s, <code>false</code> sinon
	 */
	@Override
	public boolean delete(Evenement event)
	{
		String query = "delete from evenement where id = ?";									// D�finit la requ�te SQL avec un param�tre (id � rechercher)
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour ex�cuter la requ�te
		boolean resultat = false;															// Initialise la variable qui sera retourn�e � false (�chec) par d�faut
		
		try
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			ps.setInt(1, event.getId());														// Assigne l'id � chercher au premier (et seul) param�tre de la requ�te
			
			if(ps.executeUpdate() == 0) throw new SQLException();							// Ex�cute la requ�te et lance une exception si elle n'aboutit pas
			
			resultat = true;																// Met la valeur de retour � true si la requ�te a abouti
		}
		catch (SQLException e)															// Si une erreur SQL a �t� rencontr�e ou si la ligne cible n'a pas �t� trouv�e
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
		return resultat;																	// Renvoie true si la requ�te a abouti, false sinon
	}
}
