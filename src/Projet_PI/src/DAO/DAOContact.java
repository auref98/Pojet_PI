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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Bean.Contact;
import Bean.Evenement;
import Bean.Section;

/** 
 * Classe d'accès à la base de données avec le paramètre générique de type <code>Contact</code>. <br><br>
 * Hérite de la classe abstraite <code>DAO</code> qui fourni une référence vers l'instance de la classe <code>Connection</code>. <br> 
 * Permet de récupérer, créer, modifier et supprimer une ligne de la table <code>contact</code>.
 * @author Aurelien
 * @see DAO
 * @see Bean.Contact
 */
public class DAOContact extends DAO<Contact>{

//###################################################################################################################################################################
	
	// Attributs
	
//###################################################################################################################################################################

	/**
	 * Référence pour un objet <code>PreparedStatement</code>, utilisée pour exécuter toutes les requêtes SQL de cette classe. 
	 */
	PreparedStatement prStat;																// Déclare un objet PreparedStatement pour exécuter les requêtes
	/**
	 * Référence pour un objet <code>ResultSet</code>, utilisée pour recueillir le résultat de toutes les requêtes SQL de cette classe. 
	 */
	ResultSet resSet;																		// Initialise un objet ResultSet pour récupérer le résultat des requêtes

//###################################################################################################################################################################
	
	// Conctructeurs
	
//###################################################################################################################################################################
	
	// Constructeur par défaut, explicité pour la javadoc
	/**
	 * Constructeur par défaut. <br>
	 * Ne fait rien.
	 */
	public DAOContact() {}
	
//###################################################################################################################################################################
	
	// Méthodes
	
//###################################################################################################################################################################
	
	/**
	 * Permet de récupérer une ligne de la table <code>contact</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>; <br>
	 * récupère tous les champs de la table. <br><br>
	 * pre: none<br>
	 * post: l'état de la base de donnée est inchangé; <code>id</code> est inchangé
	 * @param	id l'identifiant (BD) de la ligne à récupérer
	 * @return 	un objet de type <code>Contact</code> si une ligne a été trouvée dans la table <code>contact</code>; les attributs de cet objet contienent les valeurs contenues dans les 
	 * 			colonnes correspondantes de la table<br>
	 *  			null dans le cas contraire
	 */
	@Override
	public Contact find(int id) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM contact where id = ?";									// Définit la requête SQL avec un paramètre (id à rechercher)
		Contact cont = null;																// Initialise un objet Contact qui sera renvoyé par la méthode
		try{	
			this.prStat = connection.prepareStatement(query);								// Initialise le PreparedStatement avec la requête définie plus haut	
			this.prStat.setInt(1, id);															// Assigne l'id à chercher au premier (et seul) paramètre de la requête
			this.resSet = this.prStat.executeQuery();											// Exécute la requête
			if(this.resSet.next()){															// Si un résultat a été trouvé
				Evenement eve = new Evenement();											// Initialise un objet Evenement
				eve.setId(this.resSet.getInt("REFEVEN"));										// Affecte l'id de l 'objet Evenement
				cont = new Contact(this.resSet.getInt("ID"),this.resSet.getString("MAIL"),eve);	// Initialise la variable "com" en lui passant en paramètres son id, son adresse mail et l'événement qui lui est lié
				cont.setId(id);															// affecte la valeur du paramètre reçu par la méthode au champ "id" de l'objet cont 
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{																			// Bloc finally fermant le ResultSet et le PreparedStatement
			try {
				resSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			try {
				prStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return cont;																		// Renvoie la référence de l'objet Contact contenant les informations récupérées dans la base de données
	}

	/**
	 * Permet de récupérer une liste d'objets <code>Section</code> associées à une ligne de la table <code>contact</code>. <br><br>
	 * pre: none<br>
	 * post: l'état de la base de donnée est inchangé; <code>id</code> est inchangé
	 * @param	id l'identifiant (BD) du contact dont on souhaite récupérer les sections associées
	 * @return 	un objet de type <code>ArrayList</code> de <code>Section</code>:<br>
	 * 			référençant les sections liées au contact si une ligne dont l'id est égale au paramètre a été trouvée dans la table <code>contact</code> et qu'au moins une section est lié au contact; 
	 *  			vide dans le cas contraire
	 *  @see Bean.Section
	 */
	public ArrayList<Section> findAvecSection(int id){
		ArrayList<Section> section = new ArrayList<Section>();								// Instancie une ArrayList de Section vide
		String sql = "SELECT * FROM interesse WHERE id = ?";									// Définit la requête SQL avec un paramètre (id à rechercher)
		int tab[] = null;																	// Déclare un tableau d'entier pour recueillir l'identifiant des section liées au contact
		try {
			this.prStat = connection.prepareStatement(sql);									// Initialise le PreparedStatement avec la requête définie plus haut	
			this.prStat.setInt(1,id);															// Assigne l'id à chercher au premier (et seul) paramètre de la requête
			this.resSet = this.prStat.executeQuery();											// Exécute la requête
			tab = new int[this.resSet.getFetchSize()];											// Initialise le tableau en lui donnant une taille égale au nombre de résultats renvoyés par la requête
			int i = 0;																		// Initialise un indice à 0
			while(this.resSet.next() && i < tab.length){										// Tant qu'une ligne est lue depuis le ResultSet et que l'indice est strictement inférieur à la taille du tableau
				tab[i] = this.resSet.getInt("refsect");											// Affecte l'id de la section de la ligne du ResultSet lue dans le tableau à l'indice i
				i++;																		// Incrémente l'indice
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{																			// Bloc finally fermant le ResultSet et le PreparedStatement
			try {
				this.resSet.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			try {
				this.prStat.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		DAOSection daoSec = new DAOSection();											// Instancie un objet DAOSection pour récupérer les objets Sections
		for(int i = 0; i < tab.length;i++){													// Pour toutes les valeurs contenues dans le tableau tab,
			section.add(daoSec.find(tab[i]));												// Ajoute à la liste section un objet Section récupéré depuis la table section et dont l'identifiant est égal à la valeur contenue dans le tableau
		}
		return section;																	// Renvoie l'objet ArrayList de Section liées au contact dont l'identifiant à été passé en paramètre de la méthode
	}
	
	/**
	 * Permet d'insérer une ligne dans la table <code>contact</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>; <br>
	 * définit les champs <code>mail</code> et <code>refeven</code> de la table. <br><br>
	 * pre: none<br>
	 * post:<br>
	 * 		 une ligne a été ajoutée dans la table <code>contact</code> et des lignes ont pu être ajoutée dans la table <code>interesse</code> si la requête SQL a abouti; 
	 * 	     	l'état de la base de données est inchangé sinon<br>
	 * 		<code>cont</code> est inchangé
	 * @param	cont la référence de l'objet <code>Contact</code> contenant les informations à ajouter
	 * @return 	<code>true</code> si la ligne a été ajoutée avec succès, <code>false</code> sinon
	 */
	@Override
	public boolean create(Contact cont) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO contact (mail,refeven) VALUES (?,?)";							// Définit la requête SQL avec des paramètres
		boolean change = false;															// Initialise la variable qui sera retournée à false (échec) par défaut
		try {
			this.prStat = connection.prepareStatement(sql);									// Initialise le PreparedStatement avec la requête définie plus haut
			this.prStat.setString(1, cont.getMail());											// Assigne l'adresse mail du contact à modifier au premier paramètre de la requête
			this.prStat.setInt(2, cont.getEve().getId());										// Assigne l'id de l'événement lié au contact au deuxième paramètre de la requête
			change = (this.prStat.executeUpdate()>0)? true:false;								// Exécute la requête et met la valeur de retour à true si au moins une ligne a été ajoutée
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{																			// Bloc finally fermant  le PreparedStatement
			try {
				this.prStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(cont.getInteresse() != null){														// Si l'objet Contact référence une liste de Section
			sql = "INSERT INTO interesse (REFCONTACT, REFSECT) VALUES (?,?)";				// Définit une nouvelle requête SQL avec des paramètres	
			try{
				this.prStat = connection.prepareStatement(sql);								// Initialise le PreparedStatement avec la requête définie plus haut
				for(Section sect : cont.getInteresse()){										// pour tous les objets Section dans la liste des Section de l'objet cont passé en paramètre de la méthode
					try{
						this.prStat.setInt(1, cont.getId());									// Assigne l'id du contact au premier paramètre de la requête
						this.prStat.setInt(2, sect.getId());									// Assigne l'id de la section au premier paramètre de la requête
						this.prStat.executeUpdate();										// Exécute la requête
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{																		// Bloc finally fermant  le PreparedStatement
				try {
					this.prStat.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				try
				{
					connection.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return change;																	// Renvoie true si la table contact a été modifiée, false sinon
	}

	/**
	 * Permet de modifier une ligne de la table <code>contact</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>; <br>
	 * redéfinit le champ <code>mail</code>. <br><br>
	 * pre: none<br>
	 * post:<br>
	 * 		 une ligne de la table  <code>contact</code> a été modifiée si la requête SQL a abouti; l'état de la base de données est inchangé sinon<br>
	 * 		<code>cont</code> est inchangé
	 * @param	cont la référence de l'objet <code>Contact</code> contenant les informations de la ligne à modifier
	 * @return 	<code>true</code> si la ligne a été modifiée avec succès, <code>false</code> sinon
	 */
	@Override
	public boolean update(Contact cont) {
		// TODO Auto-generated method stub
		boolean change = false;															// Initialise la variable qui sera retournée à false (échec) par défaut
		String sql = "UPDATE contact SET mail = ? WHERE id = ?";								// Définit la requête SQL avec des paramètres
		try {
			this.prStat = connection.prepareStatement(sql);									// Initialise le PreparedStatement avec la requête définie plus haut
			this.prStat.setString(1, cont.getMail());											// Assigne l'adresse mail du contact à modifier au premier paramètre de la requête
			this.prStat.setInt(2, cont.getId());												// Assigne l'id à chercher au deuxième paramètre de la requête
			change = (this.prStat.executeUpdate()>0)?true:false;								// Exécute la requête et met la valeur de retour à true si au moins une ligne a été modifiée
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{																			// Bloc finally fermant  le PreparedStatement
			try {
				prStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return change;																	// Renvoie true si la requête a abouti, false sinon
	}

	/**
	 * Permet de suprimer une ligne de la table <code>contact</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>. <br>
	 * pre: none<br>
	 * post:<br>
	 * 		une ligne de la table  <code>contact</code> a été supprimée si la table contenait une ligne dont l'identifiant était égal 
	 * 	     	à celui spécifié dans l'attribut <code>id</code> du paramètre <code>cont</code>, et si la requête SQL a abouti; l'état de la base de données est inchangé sinon<br>
	 * 		<code>cont</code> est inchangé
	 * @param	cont la référence de l'objet <code>Contact</code> contenant l'identifiant BD de la ligne à supprimer
	 * @return 	<code>true</code> si la ligne a été supprimée avec succès, <code>false</code> sinon
	 */
	@Override
	public boolean delete(Contact cont) {
		// TODO Auto-generated method stub
		boolean change = false;															// Initialise la variable qui sera retournée à false (échec) par défaut
		String sql = "DELETE FROM contact where id = ?";										// Définit la requête SQL avec un paramètre (id à rechercher)
		try {
			this.prStat = connection.prepareStatement(sql);									// Initialise le PreparedStatement avec la requête définie plus haut
			this.prStat.setInt(1, cont.getId());												// Assigne l'id à chercher au premier (et seul) paramètre de la requête
			change = (this.prStat.executeUpdate()>0)?true:false;								// Exécute la requête et met la valeur de retour à true si au moins une ligne a été supprimée
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{																			// Bloc finally fermant  le PreparedStatement
			try{
				this.prStat.close();
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return change;																	// Renvoie true si la requête a abouti, false sinon
	}

}
