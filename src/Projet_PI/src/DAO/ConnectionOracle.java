/*
 * Haute �cole Robert Schuman - Libramont, ann�e scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integr�: r�alisation d'un logiciel de gestion des inscriptions � des �v�nements
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilis�e pour �tablir une connexion client serveur entre un repr�sentant et la base de donn�es de l'application. <br><br>
 * Cette classe est appropri�e dans le cadre d'une interaction entre un programme java et une base de donn�es Oracle. <br><br>
 * Elle initialise une unique instance de la classe <code>java.sql.Connection</code> et retient sa r�f�rence. <br><br>
 * En effet il n'est pas utilie ni souhaitable de cr�er une nouvelle connection avec la base de donn�es pour chaque op�ration; c'est pourquoi le constructeur de cette classe n'est pas accessible. 
 * Aussi, pour acc�der � la r�f�rence de la <code>Connection</code>, on appellera la methode <code>getInstance()</code>; celle-ci renvoie ladite r�f�rence, apr�s l'avoir initialis�e si n�cessaire 
 * (c'est-�-dire lors du premier appel de cette m�thode).
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 */
public class ConnectionOracle
	{
	
//###################################################################################################################################################################
	
	// Attributs
	
//###################################################################################################################################################################
	
	/**
	 *  R�f�rence vers l'instance de la Connection. <br><br> 
	 *  Est d�clar�e "static" puisque quelle que soit l'instance de la <code>ConnectionOracle</code> consid�r�e, elle r�f�rencera la m�me instance de <code>Connection</code>.
	 */
	private static Connection connection = null;		
	/**
	 * R�f�rence vers la seule instance de cette classe cr��e. <br><br>
	 * Sert � indiquer si la connection a d�j� �t� initialis�e (auquel cas cette variable contient une r�f�rence) ou pas (cette variable est � null).
	 */
	private static ConnectionOracle instance = null;												
	
	/**
	 * L'url de la base de donn�es � joindre. 
	 */
	private String url = "jdbc:oracle:thin:@"+ "127.0.0.1:12115:o11etu2";//172.16.110.172:2115 	127.0.0.1:12115					// Initialise une variable avec l'url de la base de donn�es � joindre 
	/**
	 * Le nom d'utilisateur du repr�sentant essayant de se connecter.
	 */
	private String username = "BDNamingException";											// D�clare une variable pour le nom d'utilisateur du repr�sentant essayant de se connecter
	/**
	 * Le mot de passe du repr�sentant essayant de se connecter.
	 */
	private String password = "12345";															// D�clare une variable pour le mot de passe du repr�sentant essayant de se connecter
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/**
	 * Constructeur priv�, utilis� pour cr�er une unique instance de la classe <code>Connection</code>. <br><br>
	 * <b>context</b> <code>ConnectionOracle :: ConnectionOracle()</code><br>
	 * <b>pre: </b><br>
	 * <code>connection = null</code>;<br>
	 * <code>instance = null</code>;<br>
	 * <code>url</code> r�f�rence un objet <code>java.lang.String</code> contenant l'url compl�te et valide de la base de donn�es � joindre; <br>
	 * <code>username</code> r�f�rence un objet <code>java.lang.String</code> repr�sentant le nom d'utilisateur valide (base de donn�es) du repr�sentant essayant de se connecter; <br>
	 * <code>password</code> r�f�rence un objet <code>java.lang.String</code> repr�sentant le mot de passe valide et associ� au nom d'utilisateur (base de donn�es) du repr�sentant essayant de se connecter;<br>
	 * le SGBD cible est Oracle. <br>
	 * <b>post:</b><br>
	 * <code>connection</code> r�f�rence un objet de type <code>java.sql.Connection</code> repr�sentant une connection ouverte avec un SGBD Oracle; <br>
	 * le driver <code>oracle.jdbc</code> a �t� charg�. <br><br>
	 */
	private ConnectionOracle()
		{
		try																					// Risques: driver manquant, �chec de connexion
			{
			Class.forName("oracle.jdbc.driver.OracleDriver");										// Charge le driver sp�cifique � Oracle
			connection = DriverManager.getConnection(url, username, password);					// Tente de se connecter � la base de donn�es avec les informations fournies
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

//###################################################################################################################################################################
	
	// M�thodes
	
//###################################################################################################################################################################
	
	/**
	 * Methode <code>static</code> permettant d'obtenir l'instance de la classe <code>Connection</code>. <br><br>
	 * Initialise cette instance si elle ne l'�tait pas encore lors de l'appel de la m�thode. <br><br>
	 * <b>context</b> ConnectionOracle :: getInstance() <br>
	 * <b>pre:</b><br>
	 * 	none. <br>
	 * <b>post:</b><br>
	 * 	(<code>instance@pre = null and instance != null</code>)<br>
	 * 	or <br>
	 * 	(<code>instance@pre != null and self = self@pre</code>). <br><br>
	 * @return la r�f�rence de l'unique instance de la classe <code>Connection</code>
	 * @see java.sql.Connection
	 */
	public static Connection getInstance()
		{
		if(instance == null) instance = new ConnectionOracle();									// Si la connection n'a pas encore �t� cr��e (instance == null), appelle le constructeur qui va l'initialiser et affecte une valeur � la variable "instance" pour modifier le r�sultat de ce test lors des futurs appel � cette m�thode
		return connection;																	// Renvoie la r�f�rence de la Connection contenur dans la variable "connection", apr�s qu'elle ait �t� cr��e dans cette m�thode le cas �ch�ant
		}
	}
