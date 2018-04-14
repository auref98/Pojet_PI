/*
 * Haute �cole Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integr�: r�alisation d'un logiciel de gestion des inscritsptions � des �v�nements
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
 * Classe mod�lisant un membre de la HERS � enregistrer dans le planning de l'application.<br><br>
 * Ceci est une classe repr�santant un membre de l'�cole en toute g�n�ralit�; pour repr�senter sp�cifiquement un �tudiant ou un professeur, utiliser les classes sp�cialis�es d�riv�es de celle-ci.
 * @see Commentaire
 * @see Etudiant
 * @see Inscription
 * @see Professeur
 * @see java.util.LinkedList
 */
public class Representant {
	private int id;													// Attribut permettant de recuperer l'id r�f�ren�ant cette adresse dans la base de donnees	
	private String lastName;											// Attribut pour le nom du repr�sentant
	private String firstName;											// Attribut pour le pr�nom du repr�sentant
	private int phone;												// Attribut pour le num�ro de t�l�phone du repr�sentant
	private String mail;												// Attribut pour l'adresse email du repr�sentant
	private String matricule;											//Attribut pour l'identifiant -  propre � l'�cole -  du repr�sentant (e...... pour un �tudiant, h...... pour un professeur)

	private LinkedList<Commentaire> posts;							// R�f�rence vers une liste de Commentaire (voir classe �ponyme)
	private LinkedList<Inscription> inscrits;								// R�f�rence vers une liste d'Inscription (voir classe �ponyme)
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Constructeur sans param�tre. */
	public Representant() {

	}
	
	/**
	 * Constructeur initialisant tous les param�tres.<br><br>
	 * Pr�condition: tous les param�tres sont correctement initialis�s.<br>
	 * Postcondition: l'objet est initialis�; les attributs <code>id, lastName, firstName, phone, mail</code> et <code>matricule</code>" sont initialis�s avec la valeur du param�tre de m�me nom;<br>
	 * 				les listes <code>posts</code> et <code>inscrits> ne sont pas initialis�s.<br>
	 * @param id l'identifiant (BD) de l'�v�nement
	 * @param lastname le nom du repr�sentant
	 * @param firstname  le pr�nom du repr�sentant
	 * @param phone le num�ro de t�l�phone du repr�sentant
	 * @param mail l'adresse email du repr�sentant
	 * @param matricule l'identifiant -  propre � l'�cole -  du repr�sentant
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

// getter et setter pour l'attribut "id"
	
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

// getter et setter pour l'attribut "lastName"
	
	/**
	 * @return the lastname
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstname to set
	 */
	public void setFirstname(String firstName) {
		this.firstName = firstName;
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
