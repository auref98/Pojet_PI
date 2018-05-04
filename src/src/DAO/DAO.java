/*
 * Haute école Robert Schuman - Libramont, année scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integré: réalisation d'un logiciel de gestion des inscriptions à des événements
 * 
 * Groupe NamingException
 * 			{
 * 			Adam Ludovic;
 *			Arnould Killian;
 * 			De Bernardi Christophe;
 * 			Fockedey Aurelien;
 * 			Mathieu Robin;
 * 			Modave Louis;
 * 			}
 */

package DAO;

import java.sql.*;

/**
 * Classe abstraite définissant les méthodes minimales devant être implémentées dans les classes DAO dérivées. <br><br>
 * Cette classe sert de super-classe à toutes celles utilisée pour interagir avec la base de données. Elle pose la contrainte d'implémenter ou au moins de prendre en considération les quatre opérations fondamentales: <br><br>
 * <ul>
 * 	<li> insérer des données; </li>
 * 	<li> les récupérer;</li>
 * 	<li> les modifier;</li>
 * 	<li>les supprimer.</li>
 * </ul>
 * <br>
 * L'effet de ces méthodes peut varier dans chaque classe dérivée en fonction des besoins de l'application, de la nature des données et de leur lien entre elles. D'autres méthodes peuvent être implémentées pour 
 * répondre à des cas d'utilisation spécifiques. <br><br>
 * Cette classe abstraite contient également la référence de l'objet <code>Connection</code> nécessaire à la connexion à la base de données de l'appication. La référence sera donc accessible depuis les classes dérivées et 
 * il est inutile d'insérer une variable supplémentaire dans celles-ci. <br>
 * @param <T> La classe du package <code>Bean</code> concernée par le DAO. L'usage de la généricité simplifie l'utilisation de classes spécifiques dans l'implémentation des méthodes définies ici. 
 */
public abstract class DAO<T>
	{
	/**
	 * Référence vers la connexion à la base de données. <br><br>
	 * Utilisée pour ouvrir les <code>Statement</code> et / ou <code>PrepardesStatement</code>.
	 */
	public Connection connection = ConnectionOracle.getInstance();									// Initialise la connexion en en récupérant la référence par un appel à la méthode static getInstance() de la classe ConnectionOracle
	
	// Constructeur par défaut, explicité pour la javadoc
	/**
	 * Constructeur par défaut. <br>
	 * Ne fait rien.
	 */
	public DAO() {}
	
	/**
	 * Recherche des informations dans la base de données en exécutant une requête SQL et les renvoie sous forme d'un objet du type défini dans la déclaration de la classe..
	 * @param id l'identifiant de la ligne à récupérer dans la base de données
	 * @return un objet du type défini à la déclaration de la classe contenant les informations de la ligne de la base de données identifiée par le paramètre
	 */
	public abstract T find(int id);
	
	/**
	 * Insère des informations dans la base de données.
	 * @param object un objet du type défini à la déclaration de la classe, contenant les données à ajouter
	 * @return <code>true</code> si la requête SQL a abouti (c'est-à-dire que les informations de l'objet ont été ajoutées avec succès à la base de données); <code>false</code> dans le cas contraire
	 */
	public abstract boolean create(T object);
	
	/**
	 * Modifie des informations dans la base de données.
	 * @param object un objet du type défini à la déclaration de la classe, contenant les données à modifier
	 * @return <code>true</code> si la requête SQL a abouti (c'est-à-dire que les informations ciblées par l'objet ont été modifiées avec succès dans la base de données); <code>false</code> dans le cas contraire
	 */
	public abstract boolean update(T object);
	
	/**
	 * Supprime des informations de la base de données.
	 * @param object un objet du type défini à la déclaration de la classe, contenant les informations nécessaires à la suppression des données ciblées
	 * @return <code>true</code> si la requête SQL a abouti (c'est-à-dire que les informations ciblées par l'objet ont été supprimées avec succès de la bade de données); <code>false</code> dans le cas contraire
	 */
	public abstract boolean delete(T object);
	
	}
