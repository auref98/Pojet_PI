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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.Commentaire;
import Bean.Evenement;
import Bean.Representant;

/** 
 * Classe d'acc�s � la base de donn�es avec le param�tre g�n�rique de type <code>Commentaire</code>. <br><br>
 * H�rite de la classe abstraite <code>DAO</code> qui fourni une r�f�rence vers l'instance de la classe <code>Connection</code>. <br> 
 * Permet de r�cup�rer, cr�er, modifier et supprimer une ligne de la table <code>commentaire</code>.
 * @author Aurelien
 * @see DAO
 * @see Bean.Commentaire
 */
public class DAOCommentaire extends DAO<Commentaire> {
	
	PreparedStatement prStat;													// D�clare un objet PreparedStatement pour ex�cuter les requ�tes
	ResultSet resSet;															// Initialise un objet ResultSet pour r�cup�rer le r�sultat des requ�tes
	
	/**
	 * Permet de r�cup�rer une ligne de la table <code>commentaire</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>; <br>
	 * r�cup�re tous les champs de la table. <br><br>
	 * pre: none
	 * post: l'�tat de la base de donn�e est inchang�
	 * @author Aurelien.
	 * @param	id l'identifiant (BD) de la ligne � r�cup�rer
	 * @return 	un objet de type <code>Commentaire</code> si une ligne a �t� trouv�e dans la table <code>commentaire</code>; les attributs de cet objet contienent les valeurs contenues dans les 
	 * 			colonnes correspondantes de la table<br>
	 *  			null dans le cas contraire
	 */
	@Override
	public Commentaire find(int id) {
		int idCommentaire = id;															// Initialise une variable avec la valeur de l'id de la ligne cherch�e
		String query = "Select * From Commentaire where id=?";								// D�finit la requ�te SQL avec un param�tre (id � rechercher)
		Commentaire com = null;															// Initialise un objet Commentaire qui sera retourn� par la m�thode
		try{	
			prStat = connection.prepareStatement(query);									// Initialise le PreparedStatement avec la requ�te d�finie plus haut	
			prStat.setInt(1, idCommentaire);												// Assigne l'id � chercher au premier (et seul) param�tre de la requ�te
			resSet = prStat.executeQuery();													// Ex�cute la requ�te
			if(resSet.next()){																// Si un r�sultat a �t� trouv�
				Representant rep = new Representant();										// Initialise un objet Repr�sentant
				rep.setId(this.resSet.getInt("REFREPR"));										// Affecte l'id de l 'objet Representant
				Evenement eve = new Evenement();											// Initialise un objet Evenement
				eve.setId(this.resSet.getInt("REFEVEN"));										// Affecte l'id de l 'objet Evenement
				com = new Commentaire(id,resSet.getString("contenu"));						// Initialise la variable "com"
				com.setRep(rep);															// Affecte la r�f�rence de l'objet Representant cr�e ci-dessus � la variable de l'objet Commentaire
				com.setEvenement(eve);													// Affecte la r�f�rence de l'objet Evenement cr�e ci-dessus � la variable de l'objet Commentaire
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
		}
		return com;																		// Renvoie la r�f�rence de l'objet Commentaire contenant les informations r�cup�r�es dans la base de donn�es
	}
	
	/* POUR LUDOVIC
	 * Recherche dans la BD tout les commentaire relier a idEve.
	 * @param idEve
	 * @return LinkedList de commentaire relier a un evenement.
	 
	public LinkedList<Commentaire> findAllEve(int idEve){
		LinkedList<Commentaire> com = null;
		String sql = "SELECT * FROM commentaire WHERE refeven = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, idEve);
			this.resSet = this.prStat.executeQuery();
			while(this.resSet.next())
				com.add(new Commentaire(this.resSet.getInt("id"),this.resSet.getString("contenu")));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			try {
				this.resSet.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			try{
				this.prStat.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		return com;
	}*/
	
	/**
	 * Permet d'ins�rer une ligne dans la table <code>commentaire</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>; <br>
	 * d�finit tous les champs de la table sauf <code>id</code>. <br><br>
	 * pre: none
	 * post: une ligne a �t� ajout�e dans la table <code>commentaire</code> si la requ�te SQL a abouti; l'�tat de la base de donn�es est inchang� sinon
	 * @author Aurelien
	 * @param	com la r�f�rence de l'objet <code>Commentaire</code> contenant les informations � ajouter
	 * @return 	<code>true</code> si la ligne a �t� ajout�e avec succ�s, <code>false</code> sinon
	 */
	@Override
	public boolean create(Commentaire com) {
		int refRepr = com.getRep().getId();													// Initialise une variable avec la valeur de l'id des informations du representant li� � ce commentaire
		int refEvent = com.getEvenement().getId();											// Initialise une variable avec la valeur de l'id des informations de l'�v�nement li� � ce commentaire
		boolean change = false;															// Initialise la variable qui sera retourn�e � false (�chec) par d�faut
		String query = "INSERT INTO commentaire (contenu,refrepr,refeven) VALUES (?,?,?)";		// D�finit la requ�te SQL avec des param�tres
		try{
			this.prStat = connection.prepareStatement(query);								// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			// Assigne les valeurs provenant de l'objet pass� en param�tre de la m�thode aux param�tres de la requ�te
			this.prStat.setString(0, com.getContenu());
			this.prStat.setInt(2, refRepr);
			this.prStat.setInt(3, refEvent);
			int nbChange = this.prStat.executeUpdate();										// Ex�cute la requ�te et r�cup�re le nombre de lignes modifi�es dans une nouvelle variable
			change = (nbChange > 0) ? true : false;											// Met la valeur de retour � true si la requ�te a abouti
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{																			// Bloc finally fermant le PreparedStatement
			try {
				prStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		return change;																	// Renvoie true si la requ�te a abouti, false sinon
	}
	
	/**
	 * Permet de modifier une ligne de la table <code>commentaire</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>; <br>
	 * red�finit le champ <code>contenu</code>. <br><br>
	 * pre: none
	 * post: une ligne de la table  <code>commentaire</code> a �t� modifi�e si la requ�te SQL a abouti; l'�tat de la base de donn�es est inchang� sinon
	 * @param	com la r�f�rence de l'objet <code>Commentaire</code> contenant les informations de la ligne � modifier
	 * @return 	<code>true</code> si la ligne a �t� modifi�e avec succ�s, <code>false</code> sinon
	 */
	@Override
	public boolean update(Commentaire com) {
		String query = "UPDATE commentaire SET contenu = ? where id = ?";					// D�finit la requ�te SQL avec des param�tres
		boolean change = false;															// Initialise la variable qui sera retourn�e � false (�chec) par d�faut
		try{
			this.prStat = connection.prepareStatement(query);								// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			this.prStat.setString(1, com.getContenu());										// Assigne le contenu du commentaire � modifier au premier param�tre de la requ�te
			this.prStat.setInt(2, com.getId());												// Assigne l'id � chercher au deuxi�me param�tre de la requ�te
			change = (this.prStat.executeUpdate()>0) ? true : false;							// Met la valeur de retour � true si la requ�te a abouti
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{																			// Bloc finally fermant le PreparedStatement
			try {
				prStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		return change;																	// Renvoie true si la requ�te a abouti, false sinon
	}
	
	/**
	 * Permet de suprimer une ligne de la table <code>commentaire</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>. <br>
	 * pre: none
	 * post: une ligne de la table  <code>commentaire</code> a �t� supprim�e si la table contenait une ligne dont l'identifiant �tait �gal 
	 * 	     � celui sp�cifi� dans l'attribut <code>id</code> du param�tre <code>com</code>, et si la requ�te SQL a abouti; l'�tat de la base de donn�es est inchang� sinon
	 * @param	com la r�f�rence de l'objet <code>Commentaire</code> contenant l'identifiant BD de la ligne � supprimer
	 * @return 	<code>true</code> si la ligne a �t� supprim�e avec succ�s, <code>false</code> sinon
	 */
	@Override
	public boolean delete(Commentaire com) {
		String query = "DELETE FROM commentaire WHERE id=?";								// D�finit la requ�te SQL avec un param�tre (id � rechercher)
		boolean change = false;															// Initialise la variable qui sera retourn�e � false (�chec) par d�faut
		try{
			this.prStat = connection.prepareStatement(query);								// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			this.prStat.setInt(1, com.getId());												// Assigne l'id � chercher au premier (et seul) param�tre de la requ�te
			change = (this.prStat.executeUpdate()>0)?true:false;								// Met la valeur de retour � true si la requ�te a abouti
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{																			// Bloc finally fermant le PreparedStatement
			try{
				this.prStat.close();
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
		}
		return change;																	// Renvoie true si la requ�te a abouti, false sinon
	}
	
}
