/*
 * Haute �cole Robert Schuman - Libramont, ann�e scolaire 2017 - 2018
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
 * Classe mod�lisant un membre de la HERS � enregistrer dans le planning de l'application. <br><br>
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
	private String phone;												// Attribut pour le num�ro de t�l�phone du repr�sentant
	private String mail;												// Attribut pour l'adresse email du repr�sentant
	private String matricule;											//Attribut pour l'identifiant -  propre � l'�cole -  du repr�sentant (e...... pour un �tudiant, h...... pour un professeur)
	private String password;

	private LinkedList<Commentaire> posts;							// R�f�rence vers une liste de Commentaire (voir classe �ponyme)
	private LinkedList<Inscription> inscrits;								// R�f�rence vers une liste d'Inscription (voir classe �ponyme)
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Constructeur sans param�tre. */
	public Representant() {

	}
	
	/**
	 * Constructeur initialisant tous les param�tres. <br><br>
	 * Pr�condition: tous les param�tres sont correctement initialis�s. <br>
	 * Postcondition: l'objet est initialis�; les attributs <code>id, lastName, firstName, phone, mail</code> et <code>matricule</code>" sont initialis�s avec la valeur du param�tre de m�me nom; <br>
	 * 				les listes <code>posts</code> et <code>inscrits</code> ne sont pas initialis�es. <br>
	 * @param id l'identifiant (BD) de l'�v�nement
	 * @param lastName le nom du repr�sentant
	 * @param firstName  le pr�nom du repr�sentant
	 * @param phone le num�ro de t�l�phone du repr�sentant
	 * @param mail l'adresse email du repr�sentant
	 * @param matricule l'identifiant -  propre � l'�cole -  du repr�sentant
	 */
	public Representant(int id, String lastName, String firstName, String phone, String mail, String matricule) {
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		if(password != null)this.password = password;
	}
	
	/**
	 * Renvoie l'identifiant (BD) du repr�sentant.
	 * @return l'identifiant (BD) du repr�sentant
	 */
	public int getId() {
		return id;
	}

	/**
	 * Affecte l'identifiant (BD) du repr�sentant.
	 * @param id l'identifiant (BD) � affecter
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public void setId(long id){
		this.id = (int)id;
	}

// Getter et setter pour l'attribut "lastName"
	
	/**
	 * Renvoie le nom du repr�sentant.
	 * @return le nom du repr�sentant
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Affecte le nom du repr�sentant.
	 * @param lastName le nom � affecter.
	 */
	public void setLastname(String lastName) {
		if(lastName != null)this.lastName = lastName;
	}

// Getter et setter pour l'attribut "firstName"
	
	/**
	 * Renvoie le pr�nom du repr�sentant.
	 * @return le pr�nom du repr�sentant
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Affecte le pr�nom du repr�sentant
	 * @param firstName le pr�nom � affecter
	 */
	public void setFirstname(String firstName) {
		if(firstName != null)this.firstName = firstName;
	}

// Fetter et setter pour l'attribut "phone"
	
	/**
	 * Renvoie le numero de t�l�phone du repr�sentant.
	 * @return le numero de t�l�phone du repr�sentant
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Affecte e numero de t�l�phone du repr�sentant.
	 * @param phone le numero de t�l�phone � affecter
	 */
	public void setPhone(String phone) {
		if(phone != null)this.phone = phone;
	}

// Getter et setter pour l'attribut "mail"
	
	/**
	 * Renvoie l'adresse email du repr�sentant.
	 * @return l'adresse email du repr�sentant
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Affecte l'adresse email du repr�sentant.
	 * @param mail l'adresse email � affecter
	 */
	public void setMail(String mail) {
		if(mail != null)this.mail = mail;
	}

// Getter et setter pour l'attribut "matricule"
	
	/**
	 * Renvoie l'identifiant -  propre � l'�cole -  du repr�sentant.
	 * @return l'identifiant -  propre � l'�cole -  du repr�sentant
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * Affecte l'identifiant -  propre � l'�cole -  du repr�sentant.
	 * @param matricule l'identifiant -  propre � l'�cole - � affecter
	 */
	public void setMatricule(String matricule) {
		if(matricule != null)this.matricule = matricule;
	}

// getter et setter pour l'attribut "posts"
	
	/**
	 * Renvoie la r�f�rence de la liste des <code>Commentaire</code> post�s par le repr�sentant.
	 * @return la r�f�rence de la liste des <code>Commentaire</code> post�s par le repr�sentant
	 * @see Commentaire
	 * @see java.util.LinkedList
	 */
	public LinkedList<Commentaire> getPosts() {
		return posts;
	}

	/**
	 * Affecte la r�f�rence d'une liste de <code>Commentaire</code>.
	 * @param posts la r�f�rence d'une liste de <code>Commentaire</code> � affecter
	 * @see Commentaire
	 * @see java.util.LinkedList
	 */
	public void setPosts(LinkedList<Commentaire> posts) {
		if(posts != null)this.posts = posts;
	}

// Getter et setter pour l'attribut "inscrits"
	
	/**
	 * Renvoie la r�f�rence de la liste des <code>Inscription</code> du repr�sentant.
	 * @return la r�f�rence de la liste des <code>Inscription</code> du repr�sentant.
	 * @see Inscription
	 * @see java.util.LinkedList
	 */
	public LinkedList<Inscription> getInscrits() {
		return inscrits;
	}

	/**
	 * Affecte la r�f�rence d'une  liste d'<code>Inscription</code>.
	 * @param inscrits la r�f�rence d'une  liste d'<code>Inscription</code> � affecter
	 * @see Inscription
	 * @see java.util.LinkedList
	 */
	public void setInscrits(LinkedList<Inscription> inscrits) {
		if(inscrits != null)this.inscrits = inscrits;
	}
	
}
