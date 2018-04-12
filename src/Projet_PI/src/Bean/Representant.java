/*
 * Haute �cole Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integre: realisation d'un logiciel de gestion des inscriptions � des evenements
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

public class Representant {
	private int id;
	private String lastname;
	private String firstname;
	private int phone;
	private String mail;
	private String matricule;//matricule -> e16****

	private LinkedList<Commentaire> poste;
	private LinkedList<Inscription> inscri;
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Constructeur sans param�tre. */
	public Representant() {

	}
	
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

//###################################################################################################################################################################
	
	// Manipulation des listes
	
//###################################################################################################################################################################	
	
	/**
	 * @return the poste
	 */
	public LinkedList<Commentaire> getPoste() {
		return poste;
	}

	/**
	 * @param poste the poste to set
	 */
	public void setPoste(LinkedList<Commentaire> poste) {
		this.poste = poste;
	}

	/**
	 * @return the inscri
	 */
	public LinkedList<Inscription> getInscri() {
		return inscri;
	}

	/**
	 * @param inscri the inscri to set
	 */
	public void setInscri(LinkedList<Inscription> inscri) {
		this.inscri = inscri;
	}
	
	
  
	
}
