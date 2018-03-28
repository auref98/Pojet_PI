package Class_PI;

import java.util.LinkedList;

abstract class Representant {
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
}
