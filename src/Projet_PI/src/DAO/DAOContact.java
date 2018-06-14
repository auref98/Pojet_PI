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
import java.util.ArrayList;

import Bean.Contact;
import Bean.Evenement;
import Bean.Section;

/** 
 * Classe d'acc�s � la base de donn�es avec le param�tre g�n�rique de type <code>Contact</code>. <br><br>
 * H�rite de la classe abstraite <code>DAO</code> qui fourni une r�f�rence vers l'instance de la classe <code>Connection</code>. <br> 
 * Permet de r�cup�rer, cr�er, modifier et supprimer une ligne de la table <code>contact</code>.
 * @author Aurelien
 * @see DAO
 * @see Bean.Contact
 */
public class DAOContact extends DAO<Contact>{

//###################################################################################################################################################################
	
	// Attributs
	
//###################################################################################################################################################################

	/**
	 * R�f�rence pour un objet <code>PreparedStatement</code>, utilis�e pour ex�cuter toutes les requ�tes SQL de cette classe. 
	 */
	PreparedStatement prStat;																// D�clare un objet PreparedStatement pour ex�cuter les requ�tes
	/**
	 * R�f�rence pour un objet <code>ResultSet</code>, utilis�e pour recueillir le r�sultat de toutes les requ�tes SQL de cette classe. 
	 */
	ResultSet resSet;																		// Initialise un objet ResultSet pour r�cup�rer le r�sultat des requ�tes

//###################################################################################################################################################################
	
	// Conctructeurs
	
//###################################################################################################################################################################
	
	// Constructeur par d�faut, explicit� pour la javadoc
	/**
	 * Constructeur par d�faut. <br>
	 * Ne fait rien.
	 */
	public DAOContact() {}
	
//###################################################################################################################################################################
	
	// M�thodes
	
//###################################################################################################################################################################
	
	/**
	 * Permet de r�cup�rer une ligne de la table <code>contact</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>; <br>
	 * r�cup�re tous les champs de la table. <br><br>
	 * pre: none<br>
	 * post: l'�tat de la base de donn�e est inchang�; <code>id</code> est inchang�
	 * @param	id l'identifiant (BD) de la ligne � r�cup�rer
	 * @return 	un objet de type <code>Contact</code> si une ligne a �t� trouv�e dans la table <code>contact</code>; les attributs de cet objet contienent les valeurs contenues dans les 
	 * 			colonnes correspondantes de la table<br>
	 *  			null dans le cas contraire
	 */
	@Override
	public Contact find(int id) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM contact where id = ?";									// D�finit la requ�te SQL avec un param�tre (id � rechercher)
		Contact cont = null;																// Initialise un objet Contact qui sera renvoy� par la m�thode
		try{	
			this.prStat = connection.prepareStatement(query);								// Initialise le PreparedStatement avec la requ�te d�finie plus haut	
			this.prStat.setInt(1, id);															// Assigne l'id � chercher au premier (et seul) param�tre de la requ�te
			this.resSet = this.prStat.executeQuery();											// Ex�cute la requ�te
			if(this.resSet.next()){															// Si un r�sultat a �t� trouv�
				Evenement eve = new Evenement();											// Initialise un objet Evenement
				eve.setId(this.resSet.getInt("REFEVEN"));										// Affecte l'id de l 'objet Evenement
				cont = new Contact(this.resSet.getInt("ID"),this.resSet.getString("MAIL"),eve);	// Initialise la variable "com" en lui passant en param�tres son id, son adresse mail et l'�v�nement qui lui est li�
				cont.setId(id);															// affecte la valeur du param�tre re�u par la m�thode au champ "id" de l'objet cont 
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
		return cont;																		// Renvoie la r�f�rence de l'objet Contact contenant les informations r�cup�r�es dans la base de donn�es
	}

	/**
	 * Permet de r�cup�rer une liste d'objets <code>Section</code> associ�es � une ligne de la table <code>contact</code>. <br><br>
	 * pre: none<br>
	 * post: l'�tat de la base de donn�e est inchang�; <code>id</code> est inchang�
	 * @param	id l'identifiant (BD) du contact dont on souhaite r�cup�rer les sections associ�es
	 * @return 	un objet de type <code>ArrayList</code> de <code>Section</code>:<br>
	 * 			r�f�ren�ant les sections li�es au contact si une ligne dont l'id est �gale au param�tre a �t� trouv�e dans la table <code>contact</code> et qu'au moins une section est li� au contact; 
	 *  			vide dans le cas contraire
	 *  @see Bean.Section
	 */
	public ArrayList<Section> findAvecSection(int id){
		ArrayList<Section> section = new ArrayList<Section>();								// Instancie une ArrayList de Section vide
		String sql = "SELECT * FROM interesse WHERE id = ?";									// D�finit la requ�te SQL avec un param�tre (id � rechercher)
		int tab[] = null;																	// D�clare un tableau d'entier pour recueillir l'identifiant des section li�es au contact
		try {
			this.prStat = connection.prepareStatement(sql);									// Initialise le PreparedStatement avec la requ�te d�finie plus haut	
			this.prStat.setInt(1,id);															// Assigne l'id � chercher au premier (et seul) param�tre de la requ�te
			this.resSet = this.prStat.executeQuery();											// Ex�cute la requ�te
			tab = new int[this.resSet.getFetchSize()];											// Initialise le tableau en lui donnant une taille �gale au nombre de r�sultats renvoy�s par la requ�te
			int i = 0;																		// Initialise un indice � 0
			while(this.resSet.next() && i < tab.length){										// Tant qu'une ligne est lue depuis le ResultSet et que l'indice est strictement inf�rieur � la taille du tableau
				tab[i] = this.resSet.getInt("refsect");											// Affecte l'id de la section de la ligne du ResultSet lue dans le tableau � l'indice i
				i++;																		// Incr�mente l'indice
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
		DAOSection daoSec = new DAOSection();											// Instancie un objet DAOSection pour r�cup�rer les objets Sections
		for(int i = 0; i < tab.length;i++){													// Pour toutes les valeurs contenues dans le tableau tab,
			section.add(daoSec.find(tab[i]));												// Ajoute � la liste section un objet Section r�cup�r� depuis la table section et dont l'identifiant est �gal � la valeur contenue dans le tableau
		}
		return section;																	// Renvoie l'objet ArrayList de Section li�es au contact dont l'identifiant � �t� pass� en param�tre de la m�thode
	}
	
	/**
	 * Permet d'ins�rer une ligne dans la table <code>contact</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>; <br>
	 * d�finit les champs <code>mail</code> et <code>refeven</code> de la table. <br><br>
	 * pre: none<br>
	 * post:<br>
	 * 		 une ligne a �t� ajout�e dans la table <code>contact</code> et des lignes ont pu �tre ajout�e dans la table <code>interesse</code> si la requ�te SQL a abouti; 
	 * 	     	l'�tat de la base de donn�es est inchang� sinon<br>
	 * 		<code>cont</code> est inchang�
	 * @param	cont la r�f�rence de l'objet <code>Contact</code> contenant les informations � ajouter
	 * @return 	<code>true</code> si la ligne a �t� ajout�e avec succ�s, <code>false</code> sinon
	 */
	@Override
	public boolean create(Contact cont) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO contact (mail,refeven) VALUES (?,?)";							// D�finit la requ�te SQL avec des param�tres
		boolean change = false;															// Initialise la variable qui sera retourn�e � false (�chec) par d�faut
		try {
			this.prStat = connection.prepareStatement(sql);									// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			this.prStat.setString(1, cont.getMail());											// Assigne l'adresse mail du contact � modifier au premier param�tre de la requ�te
			this.prStat.setInt(2, cont.getEve().getId());										// Assigne l'id de l'�v�nement li� au contact au deuxi�me param�tre de la requ�te
			change = (this.prStat.executeUpdate()>0)? true:false;								// Ex�cute la requ�te et met la valeur de retour � true si au moins une ligne a �t� ajout�e
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
		if(cont.getInteresse() != null){														// Si l'objet Contact r�f�rence une liste de Section
			sql = "INSERT INTO interesse (REFCONTACT, REFSECT) VALUES (?,?)";				// D�finit une nouvelle requ�te SQL avec des param�tres	
			try{
				this.prStat = connection.prepareStatement(sql);								// Initialise le PreparedStatement avec la requ�te d�finie plus haut
				for(Section sect : cont.getInteresse()){										// pour tous les objets Section dans la liste des Section de l'objet cont pass� en param�tre de la m�thode
					try{
						this.prStat.setInt(1, cont.getId());									// Assigne l'id du contact au premier param�tre de la requ�te
						this.prStat.setInt(2, sect.getId());									// Assigne l'id de la section au premier param�tre de la requ�te
						this.prStat.executeUpdate();										// Ex�cute la requ�te
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
		return change;																	// Renvoie true si la table contact a �t� modifi�e, false sinon
	}

	/**
	 * Permet de modifier une ligne de la table <code>contact</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>; <br>
	 * red�finit le champ <code>mail</code>. <br><br>
	 * pre: none<br>
	 * post:<br>
	 * 		 une ligne de la table  <code>contact</code> a �t� modifi�e si la requ�te SQL a abouti; l'�tat de la base de donn�es est inchang� sinon<br>
	 * 		<code>cont</code> est inchang�
	 * @param	cont la r�f�rence de l'objet <code>Contact</code> contenant les informations de la ligne � modifier
	 * @return 	<code>true</code> si la ligne a �t� modifi�e avec succ�s, <code>false</code> sinon
	 */
	@Override
	public boolean update(Contact cont) {
		// TODO Auto-generated method stub
		boolean change = false;															// Initialise la variable qui sera retourn�e � false (�chec) par d�faut
		String sql = "UPDATE contact SET mail = ? WHERE id = ?";								// D�finit la requ�te SQL avec des param�tres
		try {
			this.prStat = connection.prepareStatement(sql);									// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			this.prStat.setString(1, cont.getMail());											// Assigne l'adresse mail du contact � modifier au premier param�tre de la requ�te
			this.prStat.setInt(2, cont.getId());												// Assigne l'id � chercher au deuxi�me param�tre de la requ�te
			change = (this.prStat.executeUpdate()>0)?true:false;								// Ex�cute la requ�te et met la valeur de retour � true si au moins une ligne a �t� modifi�e
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
		return change;																	// Renvoie true si la requ�te a abouti, false sinon
	}

	/**
	 * Permet de suprimer une ligne de la table <code>contact</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>. <br>
	 * pre: none<br>
	 * post:<br>
	 * 		une ligne de la table  <code>contact</code> a �t� supprim�e si la table contenait une ligne dont l'identifiant �tait �gal 
	 * 	     	� celui sp�cifi� dans l'attribut <code>id</code> du param�tre <code>cont</code>, et si la requ�te SQL a abouti; l'�tat de la base de donn�es est inchang� sinon<br>
	 * 		<code>cont</code> est inchang�
	 * @param	cont la r�f�rence de l'objet <code>Contact</code> contenant l'identifiant BD de la ligne � supprimer
	 * @return 	<code>true</code> si la ligne a �t� supprim�e avec succ�s, <code>false</code> sinon
	 */
	@Override
	public boolean delete(Contact cont) {
		// TODO Auto-generated method stub
		boolean change = false;															// Initialise la variable qui sera retourn�e � false (�chec) par d�faut
		String sql = "DELETE FROM contact where id = ?";										// D�finit la requ�te SQL avec un param�tre (id � rechercher)
		try {
			this.prStat = connection.prepareStatement(sql);									// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			this.prStat.setInt(1, cont.getId());												// Assigne l'id � chercher au premier (et seul) param�tre de la requ�te
			change = (this.prStat.executeUpdate()>0)?true:false;								// Ex�cute la requ�te et met la valeur de retour � true si au moins une ligne a �t� supprim�e
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
		return change;																	// Renvoie true si la requ�te a abouti, false sinon
	}

}
