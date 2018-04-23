/*
 * Haute école Robert Schuman - Libramont, année scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilisée pour établir une connexion client serveur entre un représentant et la base de données de l'application. <br><br>
 * Cette classe est appropriée dans le cadre d'une interaction entre un programme java et une base de données Oracle. <br>
 * Elle initialise une unique instance de la classe <code>java.sql.Connection</code> et retient sa référence. <br>
 * En effet il n'est pas utilie ni souhaitable de créer une nouvelle connection avec la base de données pour chaque opération; c'est pourquoi le constructeur de cette classe n'est pas accessible. 
 * Aussi, pour accéder à la référence de la <code>Connection</code>, on appellera la methode <code>getInstance()</code>; celle-ci renvoie ladite référence, après l'avoir initialisée si nécessaire 
 * (c'est-à-dire lors du premier appel de cette méthode).
 * @see java.sql
 */
public class ConnectionOracle
	{
	private static Connection connection = null;													// Référence vers l'instance de la Connection; est déclarée "static" puisque quelle que soit l'instance de la ConnectionOracle considérée, elle référencera la même instance de Connection
	private static ConnectionOracle instance = null;												// Référence vers la seule instance de cette classe créée; sert à indiquer si la connection a déjà été initialisée (auquel cas cette variable contient une référence) ou pas (cette variable est à null
	
	
	private String url = "jdbc:oracle:thin:@"+ "172.16.110.172:2115:o11etu2"; 							// Initialise une variable avec l'url de la base de données à joindre 
	private String username = "BDNamingException";											// Déclare une variable pour le nom d'utilisateur du représentant essayant de se connecter
	private String password = "12345";															// Déclare une variable pour le mot de passe du représentant essayant de se connecter
	
	/**
	 * Constructeur privé, utilisé pour créer une unique instance d'une classe <code>Connection</code>. <br><br>
	 * 
	 */
	private ConnectionOracle()
		{
		try																					// Risques: driver manquant, échec de connexion
			{
			Class.forName("oracle.jdbc.driver.OracleDriver");										// Charge le driver spécifique à Oracle
			connection = DriverManager.getConnection(url, username, password);					// Tente de se connecter à la base de données avec les informations fournies
			}
		catch(ClassNotFoundException e)														// Driver manquant
			{
			System.out.println(e.getLocalizedMessage());
			}
		catch(SQLException e)																	// Echec de connexion (erreur SQL)
			{
			System.out.println(e.getLocalizedMessage());
			}
		}
	
	/**
	 * Methode <code>static</code> permettant d'obtenir l'instance de la classe <code>Connection</code>. <br><br>
	 * Initialise cette instance si elle ne l'était pas encore lors de l'appel de la méthode..
	 * @return l'unique instance de la classe <code>Connection</code>
	 * @see java.sql.Connection
	 */
	public static Connection getInstance()
		{
		if(instance == null) instance = new ConnectionOracle();									// Si la connection n'a pas encore été créée (instance == null), appelle le constructeur qui va l'initialiser et affecte une valeur à la variable "instance" pour modifier le résultat de ce test lors des futurs appel à cette méthode
		return connection;																	// Renvoie la référence de la Connection contenur dans la variable "connection", après qu'elle ait été créée dans cette méthode le cas échéant
		}
	}
