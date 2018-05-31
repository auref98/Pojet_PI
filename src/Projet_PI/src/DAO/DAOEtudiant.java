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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Bean.Adresse;
import Bean.Etudiant;
import Bean.Professeur;
import Bean.Representant;
import Bean.Section;

/** 
 * Classe d'accès à la base de données avec le paramètre générique de type <code>Etudiant</code>. <br><br>
 * Hérite de la classe abstraite <code>DAO</code> qui fourni une référence vers l'instance de la classe <code>Connection</code>. <br> 
 * Permet de récupérer, créer, modifier et supprimer une ligne de la table <code>etudiant</code>.
 * @author Aurelien
 * @see DAO
 * @see Bean.Etudiant
 */
public class DAOEtudiant extends DAO<Etudiant>{

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
	public DAOEtudiant() {}
	
//###################################################################################################################################################################
	
	// Méthodes
	
//###################################################################################################################################################################
	
	/**
	 * Permet de récupérer une ligne de la table <code>etudiant</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>; <br>
	 * récupère tous les champs de la table. <br><br>
	 * pre: none<br>
	 * post: l'état de la base de donnée est inchangé; <code>id</code> est inchangé
	 * @author Aurelien.
	 * @param	id l'identifiant (BD) de la ligne à récupérer
	 * @return 	un objet de type <code>Etudiant</code> si une ligne a été trouvée dans la table <code>etudiant</code>; les attributs de cet objet contienent les valeurs contenues dans les 
	 * 			colonnes correspondantes de la table<br>
	 *  			null dans le cas contraire
	 */
	@Override
	public Etudiant find(int id) {
		// TODO Auto-generated method stub
		Etudiant etu = null;																	// Initialise un objet Etudiant qui sera renvoyé par la méthode
		String sql = "SELECT * FROM etudiant WHERE id = ?";										// Définit la requête SQL avec un paramètre (id à rechercher)
		try {
			this.prStat = connection.prepareStatement(sql);										// Initialise le PreparedStatement avec la requête définie plus haut
			this.prStat.setInt(1, id);																// Assigne l'id à chercher au premier (et seul) paramètre de la requête
			this.resSet = this.prStat.executeQuery();												// Exécute la requête
			if(this.resSet.next()){
				//Section sect = new DAOSection().find(this.resSet.getInt("REFSECT"));
				//Adresse adr = new DAOAdresse().find(this.resSet.getInt("REFADDR"));
				Representant rep = new DAORepresentant().find(this.resSet.getInt("ID"));			// Instancie un objet Representant via un objet DAORepresentant, avec l'identifiant trouvé dans le ResultSet
				LocalDate date = null;															// Déclare un objet LocalDate
				// Si la ligne lu depuis le ResultSet renseigne une date de naissance, met celle-ci au format LocalDate dans la variable déclarée au dessus
				Section sec = new Section();
				sec.setId(this.resSet.getInt("refsect"));
				Adresse adr = new Adresse();
				adr.setId(this.resSet.getInt("refaddr"));
				if(this.resSet.getString("DATENAISSANCE") != null)date = LocalDate.parse(this.resSet.getString("DATENAISSANCE").split(" ")[0],DateTimeFormatter.ISO_DATE);
				etu = new Etudiant(rep.getLastName(),											// Instancie un objet Etudiant avec comme informations:	- le nom du représentant dont il est dérivé
									rep.getFirstName(),										//													- le prénom du représentant dont il est dérivé
									rep.getPhone(),											//													- le numéro de téléphone du représentant dont il est dérivé
									rep.getMail(),												//													- l'adresse mail du représentant dont il est dérivé
									rep.getMatricule(),										//													- le matricule du représentant dont il est dérivé
									this.resSet.getInt("ID"),									//													- l'identifiant récupéré dans le RésultSet
									date,													//													- la date de naissance au format LocalDate
									this.resSet.getString("PAYSNAISSANCE"),						//													- le pays de naissance récupéré dans le RésultSet
									this.resSet.getString("LIEUNAISSANCE"),						//													- le lieu de naissance récupéré dans le RésultSet
									this.resSet.getString("NUMNATIONAL"),						//													- le numéro national récupéré dans le RésultSet
									this.resSet.getString("NATIONALITE"),						//													- la nationalité récupérée dans le RésultSet
									this.resSet.getString("NUMBANQUE"),						//													- le numéro de compte en banque récupéré dans le RésultSet
									(this.resSet.getInt("SOUTIENSOCIAL")==1)?true:false,			//													- "true" s'il bénéficie d'une aide du service social, "false" sinon
									this.resSet.getString("EMPLACEMENTECOLE"),				//													- l'implantation de l'école qu'il fréquente
									this.resSet.getString("ROLE"),								//													- le rôle qu'il souhaite jouer lors d'événements
									adr,
									sec);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{																				// Bloc finally fermant le ResultSet et le PreparedStatement
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
		return etu;																			// Renvoie la référence de l'objet Etudiant contenant les informations récupérées dans la base de données
	}
	
	/**
	 * Permet de récupérer l'objet <code>Section</code> associé à un objet <code>Etudiant</code>. <br><br>
	 * Récupère un objet <code>Section</code> correspondant à un étudiant dont on fournit l'identifiant BD. <br><br>
	 * pre: none<br>
	 * post: l'état de la base de donnée est inchangé; <code>id</code> est inchangé
	 * @param	id l'identifiant (BD) de la ligne à récupérer
	 * @return 	un objet de type <code>Etudiant</code> si une ligne a été trouvée dans la table <code>etudiant</code>; les attributs de cet objet contienent les valeurs contenues dans les 
	 * 			colonnes correspondantes de la table<br>
	 *  			null dans le cas contraire
	 */
	public Section findSect(int id){
		Section sec = null;																	// Initialise un objet Section qui sera renvoyé par la méthode
		String sql = "SELECT refsect FROM etudiant WHERE id = ?";									// Définit la requête SQL avec un paramètre (id à rechercher)
		try {
			this.prStat = connection.prepareStatement(sql);										// Initialise le PreparedStatement avec la requête définie ci-dessus
			this.prStat.setInt(1, id);																// Assigne l'id à chercher au premier (et seul) paramètre de la requête
			this.resSet = this.prStat.executeQuery();												// Exécute la requête
			if(this.resSet.next()){																// Si le ResultSet contient au moins une ligne
				// Si la ligne contient une valeur non nulle pour la colonne refsect, affecte à sec un objet Section récupéré via le DAOSection avec l'identifiant renseigné par le ResultSet
				if(this.resSet.getInt("REFSECT") != 0)sec = new DAOSection().find(this.resSet.getInt("REFSECT"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{																				// Bloc finally fermant le ResultSet et le PreparedStatement
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
		return sec;																			// Renvoie la référence de l'objet Section à laquelle appartient l'étudiant (ou null)
	}
	
	/**
	 * Permet de récupérer l'objet <code>Adresse</code> associé à un objet <code>Etudiant</code>. <br><br>
	 * Récupère un objet <code>Adresse</code> correspondant à un étudiant dont on fournit l'identifiant BD. <br><br>
	 * pre: none<br>
	 * post: l'état de la base de donnée est inchangé; <code>id</code> est inchangé
	 * @param	id l'identifiant (BD) de la ligne à récupérer
	 * @return 	un objet de type <code>Etudiant</code> si une ligne a été trouvée dans la table <code>etudiant</code>; les attributs de cet objet contienent les valeurs contenues dans les 
	 * 			colonnes correspondantes de la table<br>
	 *  			null dans le cas contraire
	 */
	public Adresse findAddr(int id){
		Adresse adr = null;																	// Initialise un objet Adresse qui sera renvoyé par la méthode
		String sql = "SELECT refaddr FROM etudiant WHERE id = ?";								// Définit la requête SQL avec un paramètre (id à rechercher)
		try {
			this.prStat = connection.prepareStatement(sql);										// Initialise le PreparedStatement avec la requête définie ci-dessus
			this.prStat.setInt(1, id);																// Assigne l'id à chercher au premier (et seul) paramètre de la requête
			this.resSet = this.prStat.executeQuery();												// Exécute la requête
			if(this.resSet.next()){																// Si le ResultSet contient au moins une ligne
				adr = new DAOAdresse().find(this.resSet.getInt("REFADDR"));						// Affecte à adr un objet Adresse récupéré via le DAOAdresse avec l'identifiant renseigné par le ResultSet
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{																				// Bloc finally fermant le ResultSet et le PreparedStatement
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
		return adr;																			// Renvoie la référence de l'objet Adresse à laquelle appartient l'étudiant
	}
	
	/**
	 * Permet de récupérer un objet <code>Etudiant</code> à partir de son adresse mail et de son mot de passe. <br><br>
	 * pre: none<br>
	 * post: l'état de la base de donnée est inchangé; <code>mail</code> et <code>password</code> sont inchangés
	 * @param mail l'adresse mail de l'étudiant dont on veut récupérer les informations
	 * @param password le mot de passe de l'étudiant dont on veut récupérer les informations
	 * @return 	un objet de type <code>Etudiant</code> si une ligne contenant à la fois <code>mail</code> et <code>password</code> a été trouvée dans la table 
	 * 			<code>representant</code>; cet objet est initialisé conformément à la méthode <code>DAOEtudiant{@link #find(int)}</code><br>
	 *  			null si aucun résultat n'a été trouvé
	 */	
	public Etudiant find(String mail, String password){
		Representant rep = new DAORepresentant().find(mail, password);							// Tente de récupérer un objet Representant dont l'adresse mail et le mot de passe correspondent aux paramètres
		if(rep != null)return this.find(rep.getId());												// Si un résultat a été trouvé et affecté à la variable rep, renvoie le résultat de la méthode find(int) avec en paramètre l'identifiant du représentant
		return null;																			// Renvoie null par défaut
	}

	public ArrayList<Etudiant> findAll()
	{
		ArrayList<Representant> tabRep = new DAORepresentant().findAll();
		ArrayList<Etudiant> tabEtud = new ArrayList<Etudiant>();
		
		if(tabRep != null)
		{
			for(Representant rep : tabRep)
			{
				Etudiant etud = find(rep.getId());
				if(etud != null) tabEtud.add(etud);
			}
		}
		else tabEtud = null;
		
		/*if(tabEtud != null)
			Collections.sort(tabEtud, new Comparator<Etudiant>() {
	
				@Override
				public int compare(Etudiant o1, Etudiant o2) {
					return o1.compareTo(o2);
				}
			});*/
		
		return tabEtud;
	}
	
	/**
	 * Permet d'ajouter un étudiant à la base de données <code>etudiant</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>; <br>
	 * définit tous les champs de la table <code>etudiant</code> et ajoute une ligne associé dans la table <code>representant</code>. <br><br>
	 * pre: none
	 * post:<br>
	 * 		 une ligne a été ajoutée dans la table <code>etudiant</code> et une dans la table <code>representant</code> si l'ajout a réussi; 
	 * 	     	l'état de la base de données est inchangé sinon<br>
	 * 		<code>etu</code> est inchangé
	 * @param	etu la référence de l'objet <code>Etudiant</code> contenant les informations à ajouter
	 * @return 	<code>true</code> si les lignes ont été ajoutées avec succès, <code>false</code> sinon
	 */
	@Override
	public boolean create(Etudiant etu) {
		// TODO Auto-generated method stub
		Representant repr = new Representant(etu.getId(),										// Instancie un objet Representant avec comme informations:	- l'identifiant de l'étudiant
											etu.getLastName(),								//														- le nom de l'étudiant
											etu.getFirstName(),								//														- le prénom de l'étudiant
											etu.getPhone(),									//														- le numéro de téléphone de l'étudiant
											etu.getMail(),										//														- L'adresse mail de l'étudiant
											etu.getMatricule());								//														- le matricule de l'étudiant
		boolean change = false;																// Initialise la variable qui sera retournée à false (échec) par défaut
		String sql = "INSERT INTO etudiant ( id,"													// Définit la requête SQL avec des paramètres pour:	- l'identifiant de l'étudiant
											+ "datenaissance,"								//												- la date de naissance	
											+ "paysnaissance,"								//												- le pays de naissance
											+ "lieunaissance,"									//												- le lieu de naissance
											+ "numnational,"									//												- le numéro national
											+ "nationalite,"									//												- la nationalité
											+ "numbanque,"									//												- le numéro de compte en banque
											+ "soutiensocial,"									//												- le soutien du service social (oui / non)
											+ "emplacementecole,"							//												- la date de naissance	
											+ "role,"											//												- l'implantation de l'école fréquentée
											+ "refsect,"										//												- l'identifiant BD de la section à laquelle appartient l'étudiant
											+ "refaddr)"										//												- l'identifiant BD de l'adresse à laquelle réside l'étudiant
											+ "VALUES(?, to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?)";
		try {
			connection.setAutoCommit(false);													// Désactive l'auto-commit pour réaliser les opérations en un bloc
			if(!new DAORepresentant().create(repr))throw new SQLException();						// Tente de ajouter la ligne associée à l'étudiant dans la table representant et lance une exception en cas d'échec
			etu.setPassword(repr.getPassword());
			this.prStat = connection.prepareStatement(sql);										// Initialise le PreparedStatement avec la requête définie plus haut
			this.prStat.setInt(1, repr.getId());													// Assigne l'id du Representant dont est dérivé l'Etudiant à créer au premier paramètre de la requête
			if(etu.getDateNaissance() != null)this.prStat.setString(2, etu.getDateNaissance().toString());	// Si l'objet Etudiant contient une date de naissance, assigne celle-ci au deuxieme paramètre de la requête
			else this.prStat.setNull(2, 0);														// Sinon, assigne null au deuxieme paramètre de la requête
			// Assigne les autres valeurs des attributs de l'objet Etudiant aux paramètres de la requête
			this.prStat.setString(3, etu.getPaysNaissance());
			this.prStat.setString(4, etu.getLieuNaissance());
			this.prStat.setString(5, etu.getNumNational());
			this.prStat.setString(6, etu.getNationalite());
			this.prStat.setString(7, etu.getNumBanque());
			this.prStat.setInt(8, (etu.isSoutienSocial())?1:0);
			this.prStat.setString(9, etu.getEmplacementEcole());
			this.prStat.setString(10, etu.getRole());
			if(etu.getSec() != null)this.prStat.setInt(11, etu.getSec().getId());						// Si une référence vers un objet Section est renseignée
			else this.prStat.setNull(11, 0);
			if(etu.getAdr() != null)this.prStat.setInt(12, etu.getAdr().getId());						// Si une référence vers un objet Adresse est renseignée
			else this.prStat.setNull(12, 0);
			change = (this.prStat.executeUpdate() > 0)?true:false;								// Exécute la requête et met la valeur de retour à true si au moins une ligne a été ajoutée
			connection.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{																			// Bloc finally réactivant l'auto-commit à la base de données et fermant le PreparedStatement
			try {
				connection.setAutoCommit(true);
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println("Erreur, auto comit(Etudiant)");
			}
			try {
				this.prStat.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		return change;																	// Renvoie true si la table etudiant a été modifiée, false sinon
	}

	/**
	 * Permet de modifier une ligne de la table <code>etudiant</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>; <br>
	 * redéfinit les champs de l'objet <code>Etudiant</code>et ceux de la super-classe <code>Represantant</code>. <br><br>
	 * pre: none<br>
	 * post: <br>
	 * 		une ligne de la table  <code>etudiant</code> et une de la table <code>representant</code> ont été modifiées si la requête SQL a abouti; l'état de la base de données est inchangé sinon<br>
	 * 		<code>etu</code> est inchangé
	 * @param	etu la référence de l'objet <code>Etudiant</code> contenant les informations de la ligne à modifier
	 * @return 	<code>true</code> si la ligne a été modifiée avec succès, <code>false</code> sinon
	 */
	@Override
	public boolean update(Etudiant etu) {
		// TODO Auto-generated method stub
		boolean change = false;															// Initialise la variable qui sera retournée à false (échec) par défaut
		String sql = "UPDATE etudiant SET datenaissance = to_date(?,'yyyy-mm-dd'),"			// Définit la requête SQL avec des paramètres pour: 	- la date de naissance
											+ "paysnaissance = ?,"							//												- le pays de naissance
											+ "lieunaissance = ?,"							//												- le lieu de naissance
											+ "numnational = ?,"							//												- le numéro national
											+ "nationalite = ?,"							//												- la nationalité
											+ "numbanque = ?,"							//												- le numéro de compte en banque
											+ "soutiensocial = ?,"							//												- l'aide du service social (oui / non)
											+ "emplacementecole = ?,"					//												- l'implantation de l'école fréquentée
											+ "role = ?,"									//												- le rôle désiré lors d'un événement
											+ "refsect = ?,"								//												- l'id de la section à laquelle appartient l'étudiant
											+ "refaddr = ? WHERE id = ?";					//												- l'id de l'adresse de l'étudiant
		try {
			connection.setAutoCommit(false);												// Désactive l'auto-commit pour réaliser les opérations en un bloc
			if(!new DAORepresentant().update((Representant)etu))throw new SQLException();	// Modifie la ligne de la table representant associée à l'étudant et lance une exception en cas d'erreur
			this.prStat = connection.prepareStatement(sql);									// Initialise le PreparedStatement avec la requête définie plus haut
			// Si une date de naissance est spécifié dans l'attribut de l'objet Etudiant, la formate et l'assigne au premier paramètre de la requête
			if(etu.getDateNaissance() != null)this.prStat.setString(1, etu.getDateNaissance().toString().split(" ")[0]);
			else this.prStat.setNull(1, 0);													// sinon, assigne null au premier paramètre
			// assigne les autres valeurs des attribut de l'objet Etudiant passé en paramètre de la méthode aux paramètres de la requêtre
			this.prStat.setString(2, etu.getPaysNaissance());
			this.prStat.setString(3, etu.getLieuNaissance());
			this.prStat.setString(4, etu.getNumNational());
			this.prStat.setString(5, etu.getNationalite());
			this.prStat.setString(6, etu.getNumBanque());
			this.prStat.setInt(7, (etu.isSoutienSocial())?1:0);
			this.prStat.setString(8, etu.getEmplacementEcole());
			this.prStat.setString(9, etu.getRole());
			if(etu.getSec() != null)this.prStat.setInt(10, etu.getSec().getId());						// Si une référence vers une section est renseignée
			else this.prStat.setNull(10, 0);
			if(etu.getAdr() != null)this.prStat.setInt(11, etu.getAdr().getId());						// Si une référence vers une adresse est renseignée
			else this.prStat.setNull(11, 0);
			this.prStat.setInt(12, etu.getId());
			change = (this.prStat.executeUpdate() > 0)?true:false;								// Exécute la requête et met la valeur de retour à true si au moins une ligne a été modifiée
			connection.commit();															// Envoie explicitement les modification à effectuer à la base de donnée
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally{
			try{
				connection.setAutoCommit(true);											// Bloc finally réactivant l'auto-commit à la base de données et fermant le PreparedStatement
			}catch(Exception e){
				System.out.println("Erreur, auto comit (Etudiant)");
			}
			try {
				this.prStat.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		return change;																	// Renvoie true si la table etudiant a été modifiée, false sinon
	}

	/**
	 * Permet de suprimer une ligne de la table <code>etudiant</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>. <br>
	 * pre: none<br>
	 * post: <br>
	 * 		une ligne de la table  <code>etudiant</code> a été supprimée si la table contenait une ligne dont l'identifiant était égal 
	 * 	     	à celui spécifié dans l'attribut <code>id</code> du paramètre <code>etu</code>, et si la requête SQL a abouti; l'état de la base de données est inchangé sinon<br>
	 * 		<code>etu</code> est inchangé
	 * @param	etu la référence de l'objet <code>Etudiant</code> contenant l'identifiant BD de la ligne à supprimer
	 * @return 	<code>true</code> si la ligne a été supprimée avec succès, <code>false</code> sinon
	 */
	@Override
	public boolean delete(Etudiant etu) {
		String query = "DELETE FROM etudiant WHERE id=?";									// Définit la requête SQL avec un paramètre (id à rechercher)
		boolean change = false;															// Initialise la variable qui sera retournée à false (échec) par défaut
		try{
			this.prStat = connection.prepareStatement(query);								// Initialise le PreparedStatement avec la requête définie plus haut
			this.prStat.setInt(1, etu.getId());													// Assigne l'id à chercher au premier (et seul) paramètre de la requête
			change = (this.prStat.executeUpdate()>0)?true:false;								// Exécute la requête et met la valeur de retour à true si au moins une ligne a été supprimée
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{																			// Bloc finally fermant le PreparedStatement
			try{
				this.prStat.close();
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
		}
		return change;																	// Renvoie true si la table etudiant a été modifiée, false sinon
	}
}
