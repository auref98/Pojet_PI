/*
 * Haute école Robert Schuman - Libramont, annee scolaire 2017 - 2018
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
 * Classe modélisant un membre de la HERS à enregistrer dans le planning de l'application.<br><br>
 * Ceci est une classe représantant un membre de l'école en toute généralité; pour représenter spécifiquement un étudiant ou un professeur, utiliser les classes spécialisées dérivées de celle-ci.
 * @see Commentaire
 * @see Etudiant
 * @see Inscription
 * @see Professeur
 * @see java.util.LinkedList
 */
public class Representant {
	private int id;													// Attribut permettant de recuperer l'id référençant cette adresse dans la base de donnees	
	private String lastname;											// Attribut pour le nom du représentant
	private String firstname;											// Attribut pour le prénom du représentant
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
	 * Constructeur initialisant tous les paramètres.<br><br>
	 * Précondition: tous les paramètres sont correctement initialisés.<br>
	 * Postcondition: l'objet est initialisé; les attributs <code>id, lastName, firstName, phone, mail</code> et <code>matricule</code>" sont initialisés avec la valeur du paramètre de même nom;
	 * @param id l'identifiant (BD) de l'événement
	 * @param lastname
	 * @param firstname
	 * @param phone
	 * @param mail
	 * @param matricule
	 */
	public Representant(int id, String lastname, String firstname, int phone, String mail, String matricule) {
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.phone = phone;
		this.mail = mail;
		this.matricule = matricule;
	}
	
//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the phone
	 */
	public int getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(int phone) {
		this.phone = phone;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * @return the posts
	 * @see java.util.LinkedList
	 */
	public LinkedList<Commentaire> getPoste() {
		return posts;
	}

	/**
	 * @param posts the posts to set
	 * @see java.util.LinkedList
	 */
	public void setPoste(LinkedList<Commentaire> posts) {
		this.posts = posts;
	}

	/**
	 * @return the inscrits
	 * @see java.util.LinkedList
	 */
	public LinkedList<Inscription> getInscrits() {
		return inscrits;
	}

	/**
	 * @param inscrits the inscrits to set
	 * @see java.util.LinkedList
	 */
	public void setInscrits(LinkedList<Inscription> inscrits) {
		this.inscrits = inscrits;
	}
	
	
  
	
}
