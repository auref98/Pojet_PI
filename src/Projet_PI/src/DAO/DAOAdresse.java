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

/**
 * @author ludovic
 */

package DAO;

import java.sql.*;
import Bean.Adresse;

/** 
 * Classe d'accès à la base de données avec le paramètre générique de type <code>Adresse</code>. <br><br>
 * Hérite de la classe abstraite <code>DAO</code> qui fourni une référence vers l'instance de la classe <code>Connection</code>. <br> 
 * Permet de récupérer, créer, modifier et supprimer une ligne de la table <code>adresse</code>.
 * @see <code>DAO</code>
 * @see <code>Bean.Adresse</code>
 */
public class DAOAdresse extends DAO<Adresse>
{
	/**
	 * Permet de récupérer une ligne de la table <code>adresse</code> d'après l'<code>id</code> de la ligne. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>; <br>
	 * récupère tous les champs de la table. <br><br>
	 * pre: none
	 * post: l'état de la base de donnée est inchangé
	 * @param	id l'identifiant (BD) de la ligne à récupérer
	 * @return 	un objet de type <code>Adresse</code> si une ligne a été trouvée dans la table <code>adresse</code>; les attributs de cet objet contienent les valeurs contenues dans les 
	 * 			colonnes correspondantes de la table<br>
	 *  			null dans le cas contraire
	 */
	@Override
	public Adresse find(int id)
	{
		String query = "select * from adresse where id = ?";									// Définit la requête SQL avec un paramètre (id à rechercher)
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour exécuter la requête
		ResultSet resultSet = null;															// Initialise un objet ResultSet pour récupérer le résultat de la requête
		Adresse adr = null;																// Initialise un objet Adresse qui sera retourné par la méthode
		
		try																				// Erreurs possibles: accès à la base de données
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requête définie plus haut								
			ps.setInt(1, id);																// Assigne l'id à chercher au premier (et seul) paramètre de la requête
			resultSet = ps.executeQuery();													// Exécute la requête
			
			if(resultSet.next() == false) throw new SQLException();							// Lance une exception si aucune ligne n'a été récupérée de la base de données
			
			// Initialise les attributs de l'objet à renvoyer
			String localite = resultSet.getString("localite");
			int codePostal = resultSet.getInt("codePostal");
			String rue = resultSet.getString("rue");
			int numero = resultSet.getInt("numero");
			String boite = resultSet.getString("Boite");
			String pays = resultSet.getString("pays");
			
			adr = new Adresse(id, localite, codePostal, rue, numero, boite, pays);				// Renvoie l'objet créé
		}
		catch (SQLException ex)															// Si une erreur SQL a été rencontrée ou si aucun résultat n'a été trouvé
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
		return adr;																		// Renvoie la référence de l'objet Adresse contenant les informations récupérées dans la base de données
	}
	
	/**
	 * Permet de récupérer l'identifiant d'une ligne de la table <code>adresse</code>. <br><br>
	 * récupère tous les champs de la table. <br><br>
	 * pre: none
	 * post: l'état de la base de donnée est inchangé
	 * @param	adr la référence de l'objet <code>Adresse<code> contenant les informations de l'adresse recherchée
	 */
	public void find(Adresse adr)
	{
		String query;																		// Déclare une variable pour accueillir la formulation de la requête
		if(adr.getBoite() != null)
			query = "select * from adresse where codePostal = ? and localite = ? and Rue = ? and Numero = ? and Boite = ? and Pays = ?";// Définit la requête SQL avec un paramètre pour le champ "boite"
		else
			query = "select * from adresse where codePostal = ? and localite = ? and Rue = ? and Numero = ? and Pays = ?";// Définit la requête SQL sans le champ "boite"
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour exécuter la requête
		ResultSet resultSet = null;															// Initialise un objet ResultSet pour récupérer le résultat de la requête																// Initialise un objet Adresse qui sera retourné par la méthode
		
		try																				// Erreurs possibles: accès à la base de données
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requête définie plus haut
			// Assigne les valeurs provenant de l'objet passé en paramètre de la méthode aux paramètres de la requête
			ps.setInt(1, adr.getCodePostal());
			ps.setString(2, adr.getLocalite());
			ps.setString(3, adr.getRue());
			ps.setInt(4, adr.getNumero());
			if(adr.getBoite() != null)														// Si l'adresse cherchée dispose d'un numéro de boite
			{
				ps.setString(5, adr.getBoite());
				ps.setString(6, adr.getPays());
			}
			else ps.setString(5, adr.getPays());
			
			resultSet = ps.executeQuery();													// Exécute la requête
			
			if(resultSet.next() == false) throw new SQLException();							// Lance une exception si aucune ligne n'a été récupérée de la base de données
			
			int id = resultSet.getInt("id");													// Initialise une variable contenant la valeur de l'id de l'adresse cherchée
			
			adr.setId(id);																	// Affecte l'id (BD) de l'adresse cherchée dans le champ id de l'objet Adresse passé en paramètre
		}
		catch (SQLException ex)															// Si une erreur SQL a été rencontrée ou si aucun résultat n'a été trouvé
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
	 * Permet d'insérer une ligne dans la table <code>adresse</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>; <br>
	 * définit tous les champs de la table sauf <code>id</code>. <br><br>
	 * pre: none
	 * post: une ligne a été ajoutée dans la table <code>adresse</code> si la requête SQL a abouti; l'état de la base de données est inchangé sinon
	 * @param	adr la référence de l'objet <code>Adresse</code> contenant les informations à ajouter
	 * @return 	<code>true</code> si la ligne a été ajoutée avec succès, <code>false</code> sinon
	 */
	@Override
	public boolean create(Adresse adr)
	{
		String query = "insert into adresse values(null, ?, ?, ?, ?, ?, ?)";							// Définit la requête SQL avec des paramètres
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour exécuter la requête
		ResultSet resultSet = null;															// Initialise un objet ResultSet pour récupérer l'identifiant généré pour la ligne insérée
		boolean changed = false;															// Initialise la variable qui sera retournée à false (échec) par défaut
		try
		{
			ps = connection.prepareStatement(query, new String[] {"id"});						// Initialise le PreparedStatement avec la requête définie plus haut	
			// Assigne les valeurs provenant de l'objet passé en paramètre de la méthode aux paramètres de la requête
			ps.setInt(1, adr.getCodePostal());
			ps.setString(2, adr.getLocalite());
			ps.setString(3, adr.getRue());
			ps.setInt(4, adr.getNumero());
			ps.setString(5, adr.getBoite());
			ps.setString(6, adr.getPays());
			
			if(ps.executeUpdate() == 0) throw new SQLException();							// Exécute la requête et lance une exception si elle n'aboutit pas
			
			resultSet = ps.getGeneratedKeys();												// Tente de récupérer l'identifiant généré lors de l'exécution de la requête
			if(resultSet.next()) adr.setId(resultSet.getInt(1));									// Assigne la valeur générée à l'attribut id de l'objet passé en paramètre, afin qu'il soit accessible hors de la méthode
			
			changed =  true;																// Met la valeur de retour à true si la requête a abouti
		}
		catch (SQLException e)															// Si une erreur SQL a été rencontrée
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
		return changed;																	// Renvoie true si la requête a abouti, false sinon
	}

	/**
	 * Permet de modifier une ligne de la table <code>adresse</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>; <br>
	 * redéfinit tous les champs de la table sauf <code>id</code>. <br><br>
	 * pre: none
	 * post: une ligne de la table  <code>adresse</code> a été modifiée si la requête SQL a abouti; l'état de la base de données est inchangé sinon
	 * @param	adr la référence de l'objet <code>Adresse</code> contenant les informations de la ligne à modifier
	 * @return 	<code>true</code> si la ligne a été modifiée avec succès, <code>false</code> sinon
	 */
	@Override
	public boolean update(Adresse adr)
	{
		// Définit la requête SQL avec des paramètres
		String query = "update adresse set CODEPOSTAL = ?, LOCALITE = ?, RUE = ?, NUMERO = ?, BOITE = ?, PAYS = ? where id = ?";
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour exécuter la requête
		boolean resultat = false;															// Initialise la variable qui sera retournée à false (échec) par défaut
		
		try
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requête définie plus haut
			// Assigne les valeurs provenant de l'objet passé en paramètre de la méthode aux paramètres de la requête
			ps.setInt(1, adr.getCodePostal());
			ps.setString(2, adr.getLocalite());
			ps.setString(3, adr.getRue());
			ps.setInt(4, adr.getNumero());
			ps.setString(5, adr.getBoite());
			ps.setString(6, adr.getPays());
			ps.setInt(7, adr.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();							// Exécute la requête et lance une exception si elle n'aboutit pas
			
			resultat = true;																// Met la valeur de retour à true si la requête a abouti
		}
		catch (SQLException e)															// Si une erreur SQL a été rencontrée ou si la ligne cible n'a pas été trouvée
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
		return resultat;																	// Renvoie true si la requête a abouti, false sinon
	}
	
	/**
	 * Permet de suprimer une ligne de la table <code>adresse</code>. <br><br>
	 * Méthode héritée de la classe abstraite <code>DAO</code>. <br>
	 * pre: none
	 * post: une ligne de la table  <code>adresse</code> a été supprimée si la table contenait une ligne dont l'identifiant était égal 
	 * à celui spécifié dans l'attribut <code>id</code> du paramètre <code>adr</code>, et si la requête SQL a abouti; l'état de la base de données est inchangé sinon
	 * @param	adr la référence de l'objet <code>Adresse</code> contenant l'identifiant BD de la ligne à supprimer
	 * @return 	<code>true</code> si la ligne a été supprimée avec succès, <code>false</code> sinon
	 */
	@Override
	public boolean delete(Adresse adr)
	{
		String query = "delete from adresse where id = ?";										// Définit la requête SQL avec un paramètre (id à rechercher)
		PreparedStatement ps = null;														// Initialise un objet PreparedStatement pour exécuter la requête
		boolean resultat = false;															// Initialise la variable qui sera retournée à false (échec) par défaut
		
		try
		{
			ps = connection.prepareStatement(query);										// Initialise le PreparedStatement avec la requête définie plus haut
			ps.setInt(1, adr.getId());														// Assigne l'id à chercher au premier (et seul) paramètre de la requête
			
			if(ps.executeUpdate() == 0) throw new SQLException();							// Exécute la requête et lance une exception si elle n'aboutit pas
			
			resultat = true;																// Met la valeur de retour à true si la requête a abouti
		}
		catch (SQLException e)															// Si une erreur SQL a été rencontrée ou si la ligne cible n'a pas été trouvée
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
		return resultat;																	// Renvoie true si la requête a abouti, false sinon
	}
}