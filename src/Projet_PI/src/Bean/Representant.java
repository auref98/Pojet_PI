/*
 * Haute école Robert Schuman - Libramont, année scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integré: réalisation d'un logiciel de gestion des inscritsptions à des événements
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

package Bean;

import java.util.LinkedList;

/**
 * Classe modélisant un membre de la HERS à enregistrer dans le planning de l'application. <br><br>
 * Ceci est une classe représantant un membre de l'école en toute généralité; pour représenter spécifiquement un étudiant ou un professeur, utiliser les classes spécialisées dérivées de celle-ci.
 * @see Commentaire
 * @see Etudiant
 * @see Inscription
 * @see Professeur
 * @see java.util.LinkedList
 */
public class Representant {
	private int id;													// Attribut permettant de recuperer l'id référençant cette adresse dans la base de donnees	
	private String lastName;											// Attribut pour le nom du représentant
	private String firstName;											// Attribut pour le prénom du représentant
	private int phone;												// Attribut pour le numéro de téléphone du représentant
	private String mail;												// Attribut pour l'adresse email du représentant
	private String matricule;											//Attribut pour l'identifiant -  propre à l'école -  du représentant (e...... pour un étudiant, h...... pour un professeur)

	private LinkedList<Commentaire> posts;							// Référence vers une liste de Commentaire (voir classe éponyme)
	private LinkedList<Inscription> inscrits;								// Référence vers une liste d'Inscription (voir classe éponyme)
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Constructeur sans paramètre. */
	public Representant() {

	}
	
	/**
	 * Constructeur initialisant tous les paramètres. <br><br>
	 * Précondition: tous les paramètres sont correctement initialisés. <br>
	 * Postcondition: l'objet est initialisé; les attributs <code>id, lastName, firstName, phone, mail</code> et <code>matricule</code>" sont initialisés avec la valeur du paramètre de même nom; <br>
	 * 				les listes <code>posts</code> et <code>inscrits</code> ne sont pas initialisées. <br>
	 * @param id l'identifiant (BD) de l'événement
	 * @param lastName le nom du représentant
	 * @param firstName  le prénom du représentant
	 * @param phone le numéro de téléphone du représentant
	 * @param mail l'adresse email du représentant
	 * @param matricule l'identifiant -  propre à l'école -  du représentant
	 */
	public Representant(int id, String lastName, String firstName, int phone, String mail, String matricule) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phone = phone;
		this.mail = mail;
		this.matricule = matricule;
	}
	
//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################

// Getter et setter pour l'attribut "id"
	
	/**
	 * Renvoie l'identifiant (BD) du représentant.
	 * @return l'identifiant (BD) du représentant
	 */
	public int getId() {
		return id;
	}

	/**
	 * Affecte l'identifiant (BD) du représentant.
	 * @param id l'identifiant (BD) à affecter
	 */
	public void setId(int id) {
		this.id = id;
	}

// Getter et setter pour l'attribut "lastName"
	
	/**
	 * Renvoie le nom du représentant.
	 * @return le nom du représentant
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Affecte le nom du représentant.
	 * @param lastName le nom à affecter.
	 */
	public void setLastname(String lastName) {
		this.lastName = lastName;
	}

// Getter et setter pour l'attribut "firstName"
	
	/**
	 * Renvoie le prénom du représentant.
	 * @return le prénom du représentant
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Affecte le prénom du représentant
	 * @param firstName le prénom à affecter
	 */
	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}

// Fetter et setter pour l'attribut "phone"
	
	/**
	 * Renvoie le numero de téléphone du représentant.
	 * @return le numero de téléphone du représentant
	 */
	public int getPhone() {
		return phone;
	}

	/**
	 * Affecte e numero de téléphone du représentant.
	 * @param phone le numero de téléphone à affecter
	 */
	public void setPhone(int phone) {
		this.phone = phone;
	}

// Getter et setter pour l'attribut "mail"
	
	/**
	 * Renvoie l'adresse email du représentant.
	 * @return l'adresse email du représentant
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Affecte l'adresse email du représentant.
	 * @param mail l'adresse email à affecter
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

// Getter et setter pour l'attribut "matricule"
	
	/**
	 * Renvoie l'identifiant -  propre à l'école -  du représentant.
	 * @return l'identifiant -  propre à l'école -  du représentant
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * Affecte l'identifiant -  propre à l'école -  du représentant.
	 * @param matricule l'identifiant -  propre à l'école - à affecter
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

// getter et setter pour l'attribut "posts"
	
	/**
	 * Renvoie la référence de la liste des <code>Commentaire</code> postés par le représentant.
	 * @return la référence de la liste des <code>Commentaire</code> postés par le représentant
	 * @see Commentaire
	 * @see java.util.LinkedList
	 */
	public LinkedList<Commentaire> getPosts() {
		return posts;
	}

	/**
	 * Affecte la référence d'une liste de <code>Commentaire</code>.
	 * @param posts la référence d'une liste de <code>Commentaire</code> à affecter
	 * @see Commentaire
	 * @see java.util.LinkedList
	 */
	public void setPosts(LinkedList<Commentaire> posts) {
		this.posts = posts;
	}

// Getter et setter pour l'attribut "inscrits"
	
	/**
	 * Renvoie la référence de la liste des <code>Inscription</code> du représentant.
	 * @return la référence de la liste des <code>Inscription</code> du représentant.
	 * @see Inscription
	 * @see java.util.LinkedList
	 */
	public LinkedList<Inscription> getInscrits() {
		return inscrits;
	}

	/**
	 * Affecte la référence d'une  liste d'<code>Inscription</code>.
	 * @param inscrits la référence d'une  liste d'<code>Inscription</code> à affecter
	 * @see Inscription
	 * @see java.util.LinkedList
	 */
	public void setInscrits(LinkedList<Inscription> inscrits) {
		this.inscrits = inscrits;
	}
	
}
