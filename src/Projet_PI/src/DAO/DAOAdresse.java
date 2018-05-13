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

/**
 * @author ludovic
 */

package DAO;

import java.sql.*;
import Bean.Adresse;

/** 
 * Classe d'acc�s � la base de donn�es avec le param�tre g�n�rique de type <code>Adresse</code>. <br><br>
 * H�rite de la classe abstraite <code>DAO</code> qui fourni une r�f�rence vers l'instance de la classe <code>Connection</code>. <br> 
 * Permet de r�cup�rer, cr�er, modifier et supprimer une ligne de la table <code>adresse</code>.
 * @see DAO
 * @see Bean.Adresse
 */
public class DAOAdresse extends DAO<Adresse>
{
	/**
	 * Permet de r�cup�rer une ligne de la table <code>adresse</code> d'apr�s l'<code>id</code> de la ligne. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>; <br>
	 * r�cup�re tous les champs de la table. <br><br>
	 * pre: none
	 * post: l'�tat de la base de donn�e est inchang�
	 * @param	id l'identifiant (BD) de la ligne � r�cup�rer
	 * @return 	un objet de type <code>Adresse</code> si une ligne a �t� trouv�e dans la table <code>adresse</code>; les attributs de cet objet contienent les valeurs contenues dans les 
	 * 			colonnes correspondantes de la table<br>
	 *  			null dans le cas contraire
	 */
	@Override
	public Adresse find(int id)
	{
		String query = "select * from adresse where id = ?";									// D�finit la requ�te SQL avec un param�tre (id � rechercher)
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour ex�cuter la requ�te
		ResultSet resultSet = null;															// Initialise un objet ResultSet pour r�cup�rer le r�sultat de la requ�te
		Adresse adr = null;																// Initialise un objet Adresse qui sera retourn� par la m�thode
		
		try																				// Erreurs possibles: acc�s � la base de donn�es
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requ�te d�finie plus haut								
			ps.setInt(1, id);																// Assigne l'id � chercher au premier (et seul) param�tre de la requ�te
			resultSet = ps.executeQuery();													// Ex�cute la requ�te
			
			if(resultSet.next() == false) throw new SQLException();							// Lance une exception si aucune ligne n'a �t� r�cup�r�e de la base de donn�es
			
			// Initialise les attributs de l'objet � renvoyer
			String localite = resultSet.getString("localite");
			int codePostal = resultSet.getInt("codePostal");
			String rue = resultSet.getString("rue");
			int numero = resultSet.getInt("numero");
			String boite = resultSet.getString("Boite");
			String pays = resultSet.getString("pays");
			
			adr = new Adresse(id, localite, codePostal, rue, numero, boite, pays);				// Renvoie l'objet cr��
		}
		catch (SQLException ex)															// Si une erreur SQL a �t� rencontr�e ou si aucun r�sultat n'a �t� trouv�
		{
			System.out.println("Erreur: findAdr failed !");
			System.out.println(ex.getMessage());
		}
		finally																			// Bloc finally fermant le ResultSet et le PreparedStatement
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
		return adr;																		// Renvoie la r�f�rence de l'objet Adresse contenant les informations r�cup�r�es dans la base de donn�es
	}
	
	/**
	 * Permet de r�cup�rer l'identifiant d'une ligne de la table <code>adresse</code>. <br><br>
	 * R�cup�re tous les champs de la table. <br><br>
	 * pre: none
	 * post: l'�tat de la base de donn�e est inchang�
	 * @param	adr la r�f�rence de l'objet <code>Adresse</code> contenant les informations de l'adresse recherch�e
	 */
	public void find(Adresse adr)
	{
		String query;																		// D�clare une variable pour accueillir la formulation de la requ�te
		if(adr.getBoite() != null)
			query = "select * from adresse where codePostal = ? and localite = ? and Rue = ? and Numero = ? and Boite = ? and Pays = ?";// D�finit la requ�te SQL avec un param�tre pour le champ "boite"
		else
			query = "select * from adresse where codePostal = ? and localite = ? and Rue = ? and Numero = ? and Pays = ?";// D�finit la requ�te SQL sans le champ "boite"
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour ex�cuter la requ�te
		ResultSet resultSet = null;															// Initialise un objet ResultSet pour r�cup�rer le r�sultat de la requ�te																// Initialise un objet Adresse qui sera retourn� par la m�thode
		
		try																				// Erreurs possibles: acc�s � la base de donn�es
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			// Assigne les valeurs provenant de l'objet pass� en param�tre de la m�thode aux param�tres de la requ�te
			ps.setInt(1, adr.getCodePostal());
			ps.setString(2, adr.getLocalite());
			ps.setString(3, adr.getRue());
			ps.setInt(4, adr.getNumero());
			if(adr.getBoite() != null)														// Si l'adresse cherch�e dispose d'un num�ro de boite
			{
				ps.setString(5, adr.getBoite());
				ps.setString(6, adr.getPays());
			}
			else ps.setString(5, adr.getPays());
			
			resultSet = ps.executeQuery();													// Ex�cute la requ�te
			
			if(resultSet.next() == false) throw new SQLException();							// Lance une exception si aucune ligne n'a �t� r�cup�r�e de la base de donn�es
			
			int id = resultSet.getInt("id");													// Initialise une variable contenant la valeur de l'id de l'adresse cherch�e
			
			adr.setId(id);																	// Affecte l'id (BD) de l'adresse cherch�e dans le champ id de l'objet Adresse pass� en param�tre
		}
		catch (SQLException ex)															// Si une erreur SQL a �t� rencontr�e ou si aucun r�sultat n'a �t� trouv�
		{
			System.out.println("Erreur: findIdAdr failed !");
			System.out.println(ex.getMessage());
		}
		finally																			// Bloc finally fermant le ResultSet et le PreparedStatement
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
	}
	
	/**
	 * Permet d'ins�rer une ligne dans la table <code>adresse</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>; <br>
	 * d�finit tous les champs de la table sauf <code>id</code>. <br><br>
	 * pre: none
	 * post: une ligne a �t� ajout�e dans la table <code>adresse</code> si la requ�te SQL a abouti; l'�tat de la base de donn�es est inchang� sinon
	 * @param	adr la r�f�rence de l'objet <code>Adresse</code> contenant les informations � ajouter
	 * @return 	<code>true</code> si la ligne a �t� ajout�e avec succ�s, <code>false</code> sinon
	 */
	@Override
	public boolean create(Adresse adr)
	{
		String query = "insert into adresse values(null, ?, ?, ?, ?, ?, ?)";							// D�finit la requ�te SQL avec des param�tres
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour ex�cuter la requ�te
		ResultSet resultSet = null;															// Initialise un objet ResultSet pour r�cup�rer l'identifiant g�n�r� pour la ligne ins�r�e
		boolean changed = false;															// Initialise la variable qui sera retourn�e � false (�chec) par d�faut
		try
		{
			ps = connection.prepareStatement(query, new String[] {"id"});						// Initialise le PreparedStatement avec la requ�te d�finie plus haut	
			// Assigne les valeurs provenant de l'objet pass� en param�tre de la m�thode aux param�tres de la requ�te
			ps.setInt(1, adr.getCodePostal());
			ps.setString(2, adr.getLocalite());
			ps.setString(3, adr.getRue());
			ps.setInt(4, adr.getNumero());
			ps.setString(5, adr.getBoite());
			ps.setString(6, adr.getPays());
			
			if(ps.executeUpdate() == 0) throw new SQLException();							// Ex�cute la requ�te et lance une exception si elle n'aboutit pas
			
			resultSet = ps.getGeneratedKeys();												// Tente de r�cup�rer l'identifiant g�n�r� lors de l'ex�cution de la requ�te
			if(resultSet.next()) adr.setId(resultSet.getInt(1));									// Assigne la valeur g�n�r�e � l'attribut id de l'objet pass� en param�tre, afin qu'il soit accessible hors de la m�thode
			
			changed =  true;																// Met la valeur de retour � true si la requ�te a abouti
		}
		catch (SQLException e)															// Si une erreur SQL a �t� rencontr�e
		{
			System.out.println("Erreur: createAdr failed !");									
			System.out.println(e.getMessage());
		}
		finally																			// Bloc finally fermant  le PreparedStatement
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
				System.out.println("Erreur : " + e.getMessage());
			}
		}
		return changed;																	// Renvoie true si la requ�te a abouti, false sinon
	}

	/**
	 * Permet de modifier une ligne de la table <code>adresse</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>; <br>
	 * red�finit tous les champs de la table sauf <code>id</code>. <br><br>
	 * pre: none
	 * post: une ligne de la table  <code>adresse</code> a �t� modifi�e si la requ�te SQL a abouti; l'�tat de la base de donn�es est inchang� sinon
	 * @param	adr la r�f�rence de l'objet <code>Adresse</code> contenant les informations de la ligne � modifier
	 * @return 	<code>true</code> si la ligne a �t� modifi�e avec succ�s, <code>false</code> sinon
	 */
	@Override
	public boolean update(Adresse adr)
	{
		// D�finit la requ�te SQL avec des param�tres
		String query = "update adresse set CODEPOSTAL = ?, LOCALITE = ?, RUE = ?, NUMERO = ?, BOITE = ?, PAYS = ? where id = ?";
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour ex�cuter la requ�te
		boolean resultat = false;															// Initialise la variable qui sera retourn�e � false (�chec) par d�faut
		
		try
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			// Assigne les valeurs provenant de l'objet pass� en param�tre de la m�thode aux param�tres de la requ�te
			ps.setInt(1, adr.getCodePostal());
			ps.setString(2, adr.getLocalite());
			ps.setString(3, adr.getRue());
			ps.setInt(4, adr.getNumero());
			ps.setString(5, adr.getBoite());
			ps.setString(6, adr.getPays());
			ps.setInt(7, adr.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();							// Ex�cute la requ�te et lance une exception si elle n'aboutit pas
			
			resultat = true;																// Met la valeur de retour � true si la requ�te a abouti
		}
		catch (SQLException e)															// Si une erreur SQL a �t� rencontr�e ou si la ligne cible n'a pas �t� trouv�e
		{
			System.out.println("Erreur: UpdateAdr failed !");
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
	 * Permet de suprimer une ligne de la table <code>adresse</code>. <br><br>
	 * M�thode h�rit�e de la classe abstraite <code>DAO</code>. <br>
	 * pre: none
	 * post: une ligne de la table  <code>adresse</code> a �t� supprim�e si la table contenait une ligne dont l'identifiant �tait �gal 
	 * � celui sp�cifi� dans l'attribut <code>id</code> du param�tre <code>adr</code>, et si la requ�te SQL a abouti; l'�tat de la base de donn�es est inchang� sinon
	 * @param	adr la r�f�rence de l'objet <code>Adresse</code> contenant l'identifiant BD de la ligne � supprimer
	 * @return 	<code>true</code> si la ligne a �t� supprim�e avec succ�s, <code>false</code> sinon
	 */
	@Override
	public boolean delete(Adresse adr)
	{
		String query = "delete from adresse where id = ?";										// D�finit la requ�te SQL avec un param�tre (id � rechercher)
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour ex�cuter la requ�te
		boolean resultat = false;															// Initialise la variable qui sera retourn�e � false (�chec) par d�faut
		
		try
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requ�te d�finie plus haut
			ps.setInt(1, adr.getId());														// Assigne l'id � chercher au premier (et seul) param�tre de la requ�te
			
			if(ps.executeUpdate() == 0) throw new SQLException();							// Ex�cute la requ�te et lance une exception si elle n'aboutit pas
			
			resultat = true;																// Met la valeur de retour � true si la requ�te a abouti
		}
		catch (SQLException e)															// Si une erreur SQL a �t� rencontr�e ou si la ligne cible n'a pas �t� trouv�e
		{
			System.out.println("Erreur: deleteAdr failed !");
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