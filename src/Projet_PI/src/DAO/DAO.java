/*
 * Haute �cole Robert Schuman - Libramont, ann�e scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
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

import java.sql.*;

/**
 * Classe abstraite d�finissant les m�thodes minimales devant �tre impl�ment�es dans les classes DAO d�riv�es. <br><br>
 * Cette classe sert de super-classe � toutes celles utilis�e pour interagir avec la base de donn�es. Elle pose la contrainte d'impl�menter ou au moins de prendre en consid�ration les quatre op�rations fondamentales: <br><br>
 * <ul>
 * 	<li> ins�rer des donn�es; </li>
 * 	<li> les r�cup�rer;</li>
 * 	<li> les modifier;</li>
 * 	<li>les supprimer.</li>
 * </ul>
 * <br>
 * L'effet de ces m�thodes peut varier dans chaque classe d�riv�e en fonction des besoins de l'application, de la nature des donn�es et de leur lien entre elles. D'autres m�thodes peuvent �tre impl�ment�es pour 
 * r�pondre � des cas d'utilisation sp�cifiques. <br><br>
 * Cette classe abstraite contient �galement la r�f�rence de l'objet <code>Connection</code> n�cessaire � la connexion � la base de donn�es de l'appication. La r�f�rence sera donc accessible depuis les classes d�riv�es et 
 * il est inutile d'ins�rer une variable suppl�mentaire dans celles-ci.
 * @param <T> La classe du package <code>Bean</code> concern�e par le DAO. L'usage de la g�n�ricit� simplifie l'utilisation de classes sp�cifiques dans l'impl�mentation des m�thodes d�finies ici. 
 */
public abstract class DAO<T>
	{
	/**
	 * R�f�rence vers la connexion � la base de donn�es. <br><br>
	 * Utilis�e pour ouvrir les <code>Statement</code> et / ou <code>PrepardesStatement</code>.
	 */
	public Connection connection = ConnectionOracle.getInstance();									// Initialise la connexion en en r�cup�rant la r�f�rence par un appel � la m�thode static getInstance() de la classe ConnectionOracle
	
	// Constructeur par d�faut, explicit� pour la javadoc
	/**
	 * Constructeur par d�faut. <br>
	 * Ne fait rien.
	 */
	public DAO() {}
	
	/**
	 * Recherche des informations dans la base de donn�es en ex�cutant une requ�te SQL et les renvoie sous forme d'un objet du type d�fini dans la d�claration de la classe..
	 * @param id l'identifiant de la ligne � r�cup�rer dans la base de donn�es
	 * @return un objet du type d�fini � la d�claration de la classe contenant les informations de la ligne de la base de donn�es identifi�e par le param�tre
	 */
	public abstract T find(int id);
	
	/**
	 * Ins�re des informations dans la base de donn�es.
	 * @param object un objet du type d�fini � la d�claration de la classe, contenant les donn�es � ajouter
	 * @return <code>true</code> si la requ�te SQL a abouti (c'est-�-dire que les informations de l'objet ont �t� ajout�es avec succ�s � la base de donn�es); <code>false</code> dans le cas contraire
	 */
	public abstract boolean create(T object);
	
	/**
	 * Modifie des informations dans la base de donn�es.
	 * @param object un objet du type d�fini � la d�claration de la classe, contenant les donn�es � modifier
	 * @return <code>true</code> si la requ�te SQL a abouti (c'est-�-dire que les informations cibl�es par l'objet ont �t� modifi�es avec succ�s dans la base de donn�es); <code>false</code> dans le cas contraire
	 */
	public abstract boolean update(T object);
	
	/**
	 * Supprime des informations de la base de donn�es.
	 * @param object un objet du type d�fini � la d�claration de la classe, contenant les informations n�cessaires � la suppression des donn�es cibl�es
	 * @return <code>true</code> si la requ�te SQL a abouti (c'est-�-dire que les informations cibl�es par l'objet ont �t� supprim�es avec succ�s de la bade de donn�es); <code>false</code> dans le cas contraire
	 */
	public abstract boolean delete(T object);
	
	}
