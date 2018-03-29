/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

package Bean;

import java.util.LinkedList;

public abstract class Representant {
	private int id;
	private String nom;
	private String prenom;
	private int numTel;
	private String mail;
	private String matricule;//matricule -> e16****

	private LinkedList<Commentaire> poste;
	private LinkedList<Inscription> inscri;
  
	/**
	 * @param nom
	 * @param prenom
	 * @param numTel
	 * @param mail
	 * @param matricule
	 */
	public Representant(String nom, String prenom, int numTel, String mail, String matricule) {
		this.nom = nom;
		this.prenom = prenom;
		this.numTel = numTel;
		this.mail = mail;
		this.matricule = matricule;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the numTel
	 */
	public int getNumTel() {
		return numTel;
	}

	/**
	 * @param numTel the numTel to set
	 */
	public void setNumTel(int numTel) {
		this.numTel = numTel;
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
}
