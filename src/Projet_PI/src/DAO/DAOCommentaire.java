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

import Bean.Commentaire;
import Bean.Evenement;
import Bean.Representant;

/** 
 * Classe d'accès à la base de données avec le paramètre générique de type <code>Commentaire</code>. <br><br>
 * Hérite de la classe abstraite <code>DAO</code> qui fourni une référence vers l'instance de la classe <code>Connection</code>. <br> 
 * Permet de récupérer, créer, modifier et supprimer une ligne de la table <code>commentaire</code>.
 * @author Aurelien
 * @see DAO
 * @see Bean.Commentaire
 */
public class DAOCommentaire extends DAO<Commentaire> {
	
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
	public DAOCommentaire() {}
	
//###################################################################################################################################################################
	
	// Méthodes
	
//###################################################################################################################################################################
	
	/**
	 * Permet de récupérer une ligne de la table <code>commentaire</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>; <br>
	 * récupère tous les champs de la table. <br><br>
	 * pre: none<br>
	 * post: l'état de la base de donnée est inchangé; <code>id</code> est inchangé
	 * @author Aurelien.
	 * @param	id l'identifiant (BD) de la ligne à récupérer
	 * @return 	un objet de type <code>Commentaire</code> si une ligne a été trouvée dans la table <code>commentaire</code>; les attributs de cet objet contienent les valeurs contenues dans les 
	 * 			colonnes correspondantes de la table<br>
	 *  			<code>null</code> dans le cas contraire
	 */
	@Override
	public Commentaire find(int id) {
		int idCommentaire = id;															// Initialise une variable avec la valeur de l'id de la ligne cherchée
		String query = "Select * From Commentaire where id=?";								// Définit la requête SQL avec un paramètre (id à rechercher)
		Commentaire com = null;															// Initialise un objet Commentaire qui sera renvoyé par la méthode
		try{	
			prStat = connection.prepareStatement(query);									// Initialise le PreparedStatement avec la requête définie plus haut	
			prStat.setInt(1, idCommentaire);												// Assigne l'id à chercher au premier (et seul) paramètre de la requête
			resSet = prStat.executeQuery();													// Exécute la requête
			if(resSet.next()){																// Si un résultat a été trouvé
				Representant rep = new Representant();										// Initialise un objet Représentant
				rep.setId(this.resSet.getInt("REFREPR"));										// Affecte l'id de l 'objet Representant
				Evenement eve = new Evenement();											// Initialise un objet Evenement
				eve.setId(this.resSet.getInt("REFEVEN"));										// Affecte l'id de l 'objet Evenement
				com = new Commentaire(id,resSet.getString("contenu"));						// Initialise la variable "com"
				com.setRep(rep);															// Affecte la référence de l'objet Representant crée ci-dessus à la variable de l'objet Commentaire
				com.setEvenement(eve);													// Affecte la référence de l'objet Evenement crée ci-dessus à la variable de l'objet Commentaire
			}
		}catch (Exception e) {
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
		return com;																		// Renvoie la référence de l'objet Commentaire contenant les informations récupérées dans la base de données
	}
	
	/**
	 * Permet d'insérer une ligne dans la table <code>commentaire</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>; <br>
	 * définit tous les champs de la table sauf <code>id</code>. <br><br>
	 * pre: none<br>
	 * post:<br>
	 * 		une ligne a été ajoutée dans la table <code>commentaire</code> si la requête SQL a abouti; l'état de la base de données est inchangé sinon<br>
	 * 		<code>com</code> est inchangé
	 * @author Aurelien
	 * @param	com la référence de l'objet <code>Commentaire</code> contenant les informations à ajouter
	 * @return 	<code>true</code> si la ligne a été ajoutée avec succès, <code>false</code> sinon
	 */
	@Override
	public boolean create(Commentaire com) {
		int refRepr = com.getRep().getId();													// Initialise une variable avec la valeur de l'id des informations du representant lié à ce commentaire
		int refEvent = com.getEvenement().getId();											// Initialise une variable avec la valeur de l'id des informations de l'événement lié à ce commentaire
		boolean change = false;															// Initialise la variable qui sera retournée à false (échec) par défaut
		String query = "INSERT INTO commentaire (CONTENU,REFREPR,REFEVEN) VALUES (?,?,?)";		// Définit la requête SQL avec des paramètres
		try{
			this.prStat = connection.prepareStatement(query);								// Initialise le PreparedStatement avec la requête définie plus haut
			// Assigne les valeurs provenant de l'objet passé en paramètre de la méthode aux paramètres de la requête
			this.prStat.setString(1, com.getContenu());
			this.prStat.setInt(2, refRepr);
			this.prStat.setInt(3, refEvent);
			int nbChange = this.prStat.executeUpdate();										// Exécute la requête et récupère le nombre de lignes modifiées dans une nouvelle variable
			change = (nbChange > 0) ? true : false;											// Met la valeur de retour à true si au moins une ligne a été ajoutée
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{																			// Bloc finally fermant le PreparedStatement
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
	 * Permet de modifier une ligne de la table <code>commentaire</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>; <br>
	 * redéfinit le champ <code>contenu</code>. <br><br>
	 * pre: none<br>
	 * post:<br>
	 * 		une ligne de la table  <code>commentaire</code> a été modifiée si la requête SQL a abouti; l'état de la base de données est inchangé sinon<br>
	 * 		<code>com</code> est inchangé
	 * @param	com la référence de l'objet <code>Commentaire</code> contenant les informations de la ligne à modifier
	 * @return 	<code>true</code> si la ligne a été modifiée avec succès, <code>false</code> sinon
	 */
	@Override
	public boolean update(Commentaire com) {
		String query = "UPDATE commentaire SET contenu = ? where id = ?";					// Définit la requête SQL avec des paramètres
		boolean change = false;															// Initialise la variable qui sera retournée à false (échec) par défaut
		try{
			this.prStat = connection.prepareStatement(query);								// Initialise le PreparedStatement avec la requête définie plus haut
			this.prStat.setString(1, com.getContenu());										// Assigne le contenu du commentaire à modifier au premier paramètre de la requête
			this.prStat.setInt(2, com.getId());												// Assigne l'id à chercher au deuxième paramètre de la requête
			change = (this.prStat.executeUpdate()>0) ? true : false;							// Exécute la requête et met la valeur de retour à true si au moins une ligne a été modifiée
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{																			// Bloc finally fermant le PreparedStatement
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
	 * Permet de suprimer une ligne de la table <code>commentaire</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>. <br>
	 * pre: none<br>
	 * post:<br>
	 * 		 une ligne de la table  <code>commentaire</code> a été supprimée si la table contenait une ligne dont l'identifiant était égal 
	 * 	     	à celui spécifié dans l'attribut <code>id</code> du paramètre <code>com</code>, et si la requête SQL a abouti; l'état de la base de données est inchangé sinon<br>
	 * 		<code>com</code> est inchangé
	 * @param	com la référence de l'objet <code>Commentaire</code> contenant l'identifiant BD de la ligne à supprimer
	 * @return 	<code>true</code> si la ligne a été supprimée avec succès, <code>false</code> sinon
	 */
	@Override
	public boolean delete(Commentaire com) {
		String query = "DELETE FROM commentaire WHERE id=?";								// Définit la requête SQL avec un paramètre (id à rechercher)
		boolean change = false;															// Initialise la variable qui sera retournée à false (échec) par défaut
		try{
			this.prStat = connection.prepareStatement(query);								// Initialise le PreparedStatement avec la requête définie plus haut
			this.prStat.setInt(1, com.getId());												// Assigne l'id à chercher au premier (et seul) paramètre de la requête
			change = (this.prStat.executeUpdate()>0)?true:false;								// Exécute la requête et met la valeur de retour à true si au moins une ligne a été supprimée
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{																			// Bloc finally fermant le PreparedStatement
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
