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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Bean.Adresse;
import Bean.Etudiant;
import Bean.Representant;
import Bean.Section;

/** 
 * Classe d'acc�s � la base de donn�es avec le param�tre g�n�rique de type <code>Etudiant</code>. <br><br>
 * H�rite de la classe abstraite <code>DAO</code> qui fourni une r�f�rence vers l'instance de la classe <code>Connection</code>. <br> 
 * Permet de r�cup�rer, cr�er, modifier et supprimer une ligne de la table <code>etudiant</code>.
 * @author Aurelien
 * @see DAO
 * @see Bean.Etudiant
 */
public class DAOEtudiant extends DAO<Etudiant>{

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
	public DAOEtudiant() {}
	
//###################################################################################################################################################################
	
	// M�thodes
	
//###################################################################################################################################################################
	
	/**
	 * Permet de r�cup�rer une ligne de la table <code>etudiant</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>; <br>
	 * r�cup�re tous les champs de la table. <br><br>
	 * pre: none<br>
	 * post: l'�tat de la base de donn�e est inchang�; <code>id</code> est inchang�
	 * @author Aurelien.
	 * @param	id l'identifiant (BD) de la ligne � r�cup�rer
	 * @return 	un objet de type <code>Etudiant</code> si une ligne a �t� trouv�e dans la table <code>etudiant</code>; les attributs de cet objet contienent les valeurs contenues dans les 
	 * 			colonnes correspondantes de la table<br>
	 *  			null dans le cas contraire
	 */
	@Override
	public Etudiant find(int id) {
		// TODO Auto-generated method stub
		Etudiant etu = null;																	// Initialise un objet Etudiant qui sera renvoy� par la m�thode
		String sql = "SELECT * FROM etudiant WHERE id = ?";										// D�finit la requ�te SQL avec un param�tre (id � rechercher)
		try {
			this.prStat = connection.prepareStatement(sql);										// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			this.prStat.setInt(1, id);																// Assigne l'id � chercher au premier (et seul) param�tre de la requ�te
			this.resSet = this.prStat.executeQuery();												// Ex�cute la requ�te
			if(this.resSet.next()){
				//Section sect = new DAOSection().find(this.resSet.getInt("REFSECT"));
				//Adresse adr = new DAOAdresse().find(this.resSet.getInt("REFADDR"));
				Representant rep = new DAORepresentant().find(this.resSet.getInt("ID"));			// Instancie un objet Representant via un objet DAORepresentant, avec l'identifiant trouv� dans le ResultSet
				LocalDate date = null;															// D�clare un objet LocalDate
				// Si la ligne lu depuis le ResultSet renseigne une date de naissance, met celle-ci au format LocalDate dans la variable d�clar�e au dessus
				if(this.resSet.getString("DATENAISSANCE") != null)date = LocalDate.parse(this.resSet.getString("DATENAISSANCE").split(" ")[0],DateTimeFormatter.ISO_DATE);
				etu = new Etudiant(rep.getLastName(),											// Instancie un objet Etudiant avec comme informations:	- le nom du repr�sentant dont il est d�riv�
									rep.getFirstName(),										//													- le pr�nom du repr�sentant dont il est d�riv�
									rep.getPhone(),											//													- le num�ro de t�l�phone du repr�sentant dont il est d�riv�
									rep.getMail(),												//													- l'adresse mail du repr�sentant dont il est d�riv�
									rep.getMatricule(),										//													- le matricule du repr�sentant dont il est d�riv�
									this.resSet.getInt("ID"),									//													- l'identifiant r�cup�r� dans le R�sultSet
									date,													//													- la date de naissance au format LocalDate
									this.resSet.getString("PAYSNAISSANCE"),						//													- le pays de naissance r�cup�r� dans le R�sultSet
									this.resSet.getString("LIEUNAISSANCE"),						//													- le lieu de naissance r�cup�r� dans le R�sultSet
									this.resSet.getString("NUMNATIONAL"),						//													- le num�ro national r�cup�r� dans le R�sultSet
									this.resSet.getString("NATIONALITE"),						//													- la nationalit� r�cup�r�e dans le R�sultSet
									this.resSet.getString("NUMBANQUE"),						//													- le num�ro de compte en banque r�cup�r� dans le R�sultSet
									(this.resSet.getInt("SOUTIENSOCIAL")==1)?true:false,			//													- "true" s'il b�n�ficie d'une aide du service social, "false" sinon
									this.resSet.getString("EMPLACEMENTECOLE"),				//													- l'implantation de l'�cole qu'il fr�quente
									this.resSet.getString("ROLE"),								//													- le r�le qu'il souhaite jouer lors d'�v�nements
									null,
									null);
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
		return etu;																			// Renvoie la r�f�rence de l'objet Etudiant contenant les informations r�cup�r�es dans la base de donn�es
	}
	
	/**
	 * Permet de r�cup�rer l'objet <code>Section</code> associ� � un objet <code>Etudiant</code>. <br><br>
	 * R�cup�re un objet <code>Section</code> correspondant � un �tudiant dont on fournit l'identifiant BD. <br><br>
	 * pre: none<br>
	 * post: l'�tat de la base de donn�e est inchang�; <code>id</code> est inchang�
	 * @param	id l'identifiant (BD) de la ligne � r�cup�rer
	 * @return 	un objet de type <code>Etudiant</code> si une ligne a �t� trouv�e dans la table <code>etudiant</code>; les attributs de cet objet contienent les valeurs contenues dans les 
	 * 			colonnes correspondantes de la table<br>
	 *  			null dans le cas contraire
	 */
	public Section findSect(int id){
		Section sec = null;																	// Initialise un objet Section qui sera renvoy� par la m�thode
		String sql = "SELECT refsect FROM etudiant WHERE id = ?";									// D�finit la requ�te SQL avec un param�tre (id � rechercher)
		try {
			this.prStat = connection.prepareStatement(sql);										// Initialise le PreparedStatement avec la requ�te d�finie ci-dessus
			this.prStat.setInt(1, id);																// Assigne l'id � chercher au premier (et seul) param�tre de la requ�te
			this.resSet = this.prStat.executeQuery();												// Ex�cute la requ�te
			if(this.resSet.next()){																// Si le ResultSet contient au moins une ligne
				// Si la ligne contient une valeur non nulle pour la colonne refsect, affecte � sec un objet Section r�cup�r� via le DAOSection avec l'identifiant renseign� par le ResultSet
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
		return sec;																			// Renvoie la r�f�rence de l'objet Section � laquelle appartient l'�tudiant (ou null)
	}
	
	/**
	 * Permet de r�cup�rer l'objet <code>Adresse</code> associ� � un objet <code>Etudiant</code>. <br><br>
	 * R�cup�re un objet <code>Adresse</code> correspondant � un �tudiant dont on fournit l'identifiant BD. <br><br>
	 * pre: none<br>
	 * post: l'�tat de la base de donn�e est inchang�; <code>id</code> est inchang�
	 * @param	id l'identifiant (BD) de la ligne � r�cup�rer
	 * @return 	un objet de type <code>Etudiant</code> si une ligne a �t� trouv�e dans la table <code>etudiant</code>; les attributs de cet objet contienent les valeurs contenues dans les 
	 * 			colonnes correspondantes de la table<br>
	 *  			null dans le cas contraire
	 */
	public Adresse findAddr(int id){
		Adresse adr = null;																	// Initialise un objet Adresse qui sera renvoy� par la m�thode
		String sql = "SELECT refaddr FROM etudiant WHERE id = ?";								// D�finit la requ�te SQL avec un param�tre (id � rechercher)
		try {
			this.prStat = connection.prepareStatement(sql);										// Initialise le PreparedStatement avec la requ�te d�finie ci-dessus
			this.prStat.setInt(1, id);																// Assigne l'id � chercher au premier (et seul) param�tre de la requ�te
			this.resSet = this.prStat.executeQuery();												// Ex�cute la requ�te
			if(this.resSet.next()){																// Si le ResultSet contient au moins une ligne
				adr = new DAOAdresse().find(this.resSet.getInt("REFADDR"));						// Affecte � adr un objet Adresse r�cup�r� via le DAOAdresse avec l'identifiant renseign� par le ResultSet
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
		return adr;																			// Renvoie la r�f�rence de l'objet Adresse � laquelle appartient l'�tudiant
	}
	
	/**
	 * Permet de r�cup�rer un objet <code>Etudiant</code> � partir de son adresse mail et de son mot de passe. <br><br>
	 * pre: none<br>
	 * post: l'�tat de la base de donn�e est inchang�; <code>mail</code> et <code>password</code> sont inchang�s
	 * @param mail l'adresse mail de l'�tudiant dont on veut r�cup�rer les informations
	 * @param password le mot de passe de l'�tudiant dont on veut r�cup�rer les informations
	 * @return 	un objet de type <code>Etudiant</code> si une ligne contenant � la fois <code>mail</code> et <code>password</code> a �t� trouv�e dans la table 
	 * 			<code>representant</code>; cet objet est initialis� conform�ment � la m�thode <code>DAOEtudiant{@link #find(int)}</code><br>
	 *  			null si aucun r�sultat n'a �t� trouv�
	 */	
	public Etudiant find(String mail, String password){
		Representant rep = new DAORepresentant().find(mail, password);							// Tente de r�cup�rer un objet Representant dont l'adresse mail et le mot de passe correspondent aux param�tres
		if(rep != null)return this.find(rep.getId());												// Si un r�sultat a �t� trouv� et affect� � la variable rep, renvoie le r�sultat de la m�thode find(int) avec en param�tre l'identifiant du repr�sentant
		return null;																			// Renvoie null par d�faut
	}

	/**
	 * Permet d'ajouter un �tudiant � la base de donn�es <code>etudiant</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>; <br>
	 * d�finit tous les champs de la table <code>etudiant</code> et ajoute une ligne associ� dans la table <code>representant</code>. <br><br>
	 * pre: none
	 * post:<br>
	 * 		 une ligne a �t� ajout�e dans la table <code>etudiant</code> et une dans la table <code>representant</code> si l'ajout a r�ussi; 
	 * 	     	l'�tat de la base de donn�es est inchang� sinon<br>
	 * 		<code>etu</code> est inchang�
	 * @param	etu la r�f�rence de l'objet <code>Etudiant</code> contenant les informations � ajouter
	 * @return 	<code>true</code> si les lignes ont �t� ajout�es avec succ�s, <code>false</code> sinon
	 */
	@Override
	public boolean create(Etudiant etu) {
		// TODO Auto-generated method stub
		Representant repr = new Representant(etu.getId(),										// Instancie un objet Representant avec comme informations:	- l'identifiant de l'�tudiant
											etu.getLastName(),								//														- le nom de l'�tudiant
											etu.getFirstName(),								//														- le pr�nom de l'�tudiant
											etu.getPhone(),									//														- le num�ro de t�l�phone de l'�tudiant
											etu.getMail(),										//														- L'adresse mail de l'�tudiant
											etu.getMatricule());								//														- le matricule de l'�tudiant
		boolean change = false;																// Initialise la variable qui sera retourn�e � false (�chec) par d�faut
		String sql = "INSERT INTO etudiant ( id,"													// D�finit la requ�te SQL avec des param�tres pour:	- l'identifiant de l'�tudiant
											+ "datenaissance,"								//												- la date de naissance	
											+ "paysnaissance,"								//												- le pays de naissance
											+ "lieunaissance,"									//												- le lieu de naissance
											+ "numnational,"									//												- le num�ro national
											+ "nationalite,"									//												- la nationalit�
											+ "numbanque,"									//												- le num�ro de compte en banque
											+ "soutiensocial,"									//												- le soutien du service social (oui / non)
											+ "emplacementecole,"							//												- la date de naissance	
											+ "role,"											//												- l'implantation de l'�cole fr�quent�e
											+ "refsect,"										//												- l'identifiant BD de la section � laquelle appartient l'�tudiant
											+ "refaddr)"										//												- l'identifiant BD de l'adresse � laquelle r�side l'�tudiant
											+ "VALUES(?, to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?)";
		try {
			connection.setAutoCommit(false);													// D�sactive l'auto-commit pour r�aliser les op�rations en un bloc
			if(!new DAORepresentant().create(repr))throw new SQLException();						// Tente de ajouter la ligne associ�e � l'�tudiant dans la table representant et lance une exception en cas d'�chec
			this.prStat = connection.prepareStatement(sql);										// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			this.prStat.setInt(1, repr.getId());													// Assigne l'id du Representant dont est d�riv� l'Etudiant � cr�er au premier param�tre de la requ�te
			if(etu.getDateNaissance() != null)this.prStat.setString(2, etu.getDateNaissance().toString());	// Si l'objet Etudiant contient une date de naissance, assigne celle-ci au deuxieme param�tre de la requ�te
			else this.prStat.setNull(2, 0);														// Sinon, assigne null au deuxieme param�tre de la requ�te
			// Assigne les autres valeurs des attributs de l'objet Etudiant aux param�tres de la requ�te
			this.prStat.setString(3, etu.getPaysNaissance());
			this.prStat.setString(4, etu.getLieuNaissance());
			this.prStat.setString(5, etu.getNumNational());
			this.prStat.setString(6, etu.getNationalite());
			this.prStat.setString(7, etu.getNumBanque());
			this.prStat.setInt(8, (etu.isSoutienSocial())?1:0);
			this.prStat.setString(9, etu.getEmplacementEcole());
			this.prStat.setString(10, etu.getRole());
			if(etu.getSec() != null)this.prStat.setInt(11, etu.getSec().getId());						// Si une r�f�rence vers un objet Section est renseign�e
			else this.prStat.setNull(11, 0);
			if(etu.getAdr() != null)this.prStat.setInt(12, etu.getAdr().getId());						// Si une r�f�rence vers un objet Adresse est renseign�e
			else this.prStat.setNull(12, 0);
			change = (this.prStat.executeUpdate() > 0)?true:false;								// Ex�cute la requ�te et met la valeur de retour � true si au moins une ligne a �t� ajout�e
			connection.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{																			// Bloc finally r�activant l'auto-commit � la base de donn�es et fermant le PreparedStatement
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
		return change;																	// Renvoie true si la table etudiant a �t� modifi�e, false sinon
	}

	/**
	 * Permet de modifier une ligne de la table <code>etudiant</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>; <br>
	 * red�finit les champs de l'objet <code>Etudiant</code>et ceux de la super-classe <code>Represantant</code>. <br><br>
	 * pre: none<br>
	 * post: <br>
	 * 		une ligne de la table  <code>etudiant</code> et une de la table <code>representant</code> ont �t� modifi�es si la requ�te SQL a abouti; l'�tat de la base de donn�es est inchang� sinon<br>
	 * 		<code>etu</code> est inchang�
	 * @param	etu la r�f�rence de l'objet <code>Etudiant</code> contenant les informations de la ligne � modifier
	 * @return 	<code>true</code> si la ligne a �t� modifi�e avec succ�s, <code>false</code> sinon
	 */
	@Override
	public boolean update(Etudiant etu) {
		// TODO Auto-generated method stub
		boolean change = false;															// Initialise la variable qui sera retourn�e � false (�chec) par d�faut
		String sql = "UPDATE etudiant SET datenaissance = to_date(?,'yyyy-mm-dd'),"			// D�finit la requ�te SQL avec des param�tres pour: 	- la date de naissance
											+ "paysnaissance = ?,"							//												- le pays de naissance
											+ "lieunaissance = ?,"							//												- le lieu de naissance
											+ "numnational = ?,"							//												- le num�ro national
											+ "nationalite = ?,"							//												- la nationalit�
											+ "numbanque = ?,"							//												- le num�ro de compte en banque
											+ "soutiensocial = ?,"							//												- l'aide du service social (oui / non)
											+ "emplacementecole = ?,"					//												- l'implantation de l'�cole fr�quent�e
											+ "role = ?,"									//												- le r�le d�sir� lors d'un �v�nement
											+ "refsect = ?,"								//												- l'id de la section � laquelle appartient l'�tudiant
											+ "refaddr = ? WHERE id = ?";					//												- l'id de l'adresse de l'�tudiant
		try {
			connection.setAutoCommit(false);												// D�sactive l'auto-commit pour r�aliser les op�rations en un bloc
			if(!new DAORepresentant().update((Representant)etu))throw new SQLException();	// Modifie la ligne de la table representant associ�e � l'�tudant et lance une exception en cas d'erreur
			this.prStat = connection.prepareStatement(sql);									// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			// Si une date de naissance est sp�cifi� dans l'attribut de l'objet Etudiant, la formate et l'assigne au premier param�tre de la requ�te
			if(etu.getDateNaissance() != null)this.prStat.setString(1, etu.getDateNaissance().toString().split(" ")[0]);
			else this.prStat.setNull(1, 0);													// sinon, assigne null au premier param�tre
			// assigne les autres valeurs des attribut de l'objet Etudiant pass� en param�tre de la m�thode aux param�tres de la requ�tre
			this.prStat.setString(2, etu.getPaysNaissance());
			this.prStat.setString(3, etu.getLieuNaissance());
			this.prStat.setString(4, etu.getNumNational());
			this.prStat.setString(5, etu.getNationalite());
			this.prStat.setString(6, etu.getNumBanque());
			this.prStat.setInt(7, (etu.isSoutienSocial())?1:0);
			this.prStat.setString(8, etu.getEmplacementEcole());
			this.prStat.setString(9, etu.getRole());
			if(etu.getSec() != null)this.prStat.setInt(10, etu.getSec().getId());						// Si une r�f�rence vers une section est renseign�e
			else this.prStat.setNull(10, 0);
			if(etu.getAdr() != null)this.prStat.setInt(11, etu.getAdr().getId());						// Si une r�f�rence vers une adresse est renseign�e
			else this.prStat.setNull(11, 0);
			this.prStat.setInt(12, etu.getId());
			change = (this.prStat.executeUpdate() > 0)?true:false;								// Ex�cute la requ�te et met la valeur de retour � true si au moins une ligne a �t� modifi�e
			connection.commit();															// Envoie explicitement les modification � effectuer � la base de donn�e
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally{
			try{
				connection.setAutoCommit(true);											// Bloc finally r�activant l'auto-commit � la base de donn�es et fermant le PreparedStatement
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
		return change;																	// Renvoie true si la table etudiant a �t� modifi�e, false sinon
	}

	/**
	 * Permet de suprimer une ligne de la table <code>etudiant</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>. <br>
	 * pre: none<br>
	 * post: <br>
	 * 		une ligne de la table  <code>etudiant</code> a �t� supprim�e si la table contenait une ligne dont l'identifiant �tait �gal 
	 * 	     	� celui sp�cifi� dans l'attribut <code>id</code> du param�tre <code>etu</code>, et si la requ�te SQL a abouti; l'�tat de la base de donn�es est inchang� sinon<br>
	 * 		<code>etu</code> est inchang�
	 * @param	etu la r�f�rence de l'objet <code>Etudiant</code> contenant l'identifiant BD de la ligne � supprimer
	 * @return 	<code>true</code> si la ligne a �t� supprim�e avec succ�s, <code>false</code> sinon
	 */
	@Override
	public boolean delete(Etudiant etu) {
		String query = "DELETE FROM etudiant WHERE id=?";									// D�finit la requ�te SQL avec un param�tre (id � rechercher)
		boolean change = false;															// Initialise la variable qui sera retourn�e � false (�chec) par d�faut
		try{
			this.prStat = connection.prepareStatement(query);								// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			this.prStat.setInt(1, etu.getId());													// Assigne l'id � chercher au premier (et seul) param�tre de la requ�te
			change = (this.prStat.executeUpdate()>0)?true:false;								// Ex�cute la requ�te et met la valeur de retour � true si au moins une ligne a �t� supprim�e
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{																			// Bloc finally fermant le PreparedStatement
			try{
				this.prStat.close();
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
		}
		return change;																	// Renvoie true si la table etudiant a �t� modifi�e, false sinon
	}
}
