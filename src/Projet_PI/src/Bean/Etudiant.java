/*
 * Haute école Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integre: realisation d'un logiciel de gestion des inscriptions à des événements
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

import java.time.LocalDate;

/** 
 * Classe de type "bean" représentant un étudiant.<br>
 * Etend la classe "Représentant" dont elle hérite les attributs d'une personne, et ajoute ses propres attributs 
 * spécifiques au informations nécessaires à la modélisation d'un étudiant.
 */
public class Etudiant extends Representant{
	
	private int id;													// Attribut permettant de recuperer l'id référençant cette adresse dans la base de donnees
	private LocalDate dateNaissance;								// Date de naissance de l'étudiant
	private String paysDeNaissance;									// Pays de naissance de l'étudiant
	private String lieuDeNaissance;									// Lieu de naissance de l'étudiant
	private String numeroNational;									// Numéro national identifiant l'étudiant
	private String nationalite;										// Nationalité actuelle de l'étudiant
	private String iban;											// Numéro IBAN du compte bancaire de l'étudiant (les étudiants représentant l'école à un événement sont rémunérés)
	private boolean conseilSocial;									// Indique si l'étudiant bénéficie d'un soutient du service social de l'école (si c'est le cas il bénéficie d'une priorité à l'inscription)
	private String lieuEcole;										// Implantaion (ville) de l'école où l'étudiant suit les cours
	private String role;											// Attribut renseignant le rôle que l'étudiant souhaite assumer lors d'un événement (ouvrier, représentant au stand,...).

	private Adresse adr;											// Référence l'adresse de l'étudiant
	private Section sec;											// Section à laquelle l'étudiant appartient

//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Constructeur sans paramètre. */
	public Etudiant() {
		super();	
	}
	
	/**
	 * Constructeur initialisant tous les paramètres.<br><br>
	 * Précondition: tous les paramètres sont correctement initialisés.<br>
	 * Postcondition: l'objet est initialisé; tous les attributs (sauf adr et sec) sont initialisés avec la valeur du paramètre de même nom.<br>
	 * @param nom le nom de l'étudiant.
	 * @param prenom le prénom de l'étudiant.
	 * @param numTel le numéro de téléphone de l'étudiant.
	 * @param mail l'adresse email de l'étudiant.
	 * @param matricule le matricule HERS identifiant de l'étudiant.
	 * @param id l'identifiant (BD) de l'étudiant.
	 * @param dateNaissance la date de naissance de l'étudiant (au format LocalDate).
	 * @param paysDeNaissance le pays de naissance de l'étudiant.
	 * @param lieuDeNaissance le lieu de naissance de l'étudiant.
	 * @param numeroNational le numéro national identifiant de l'étudiant.
	 * @param nationalite la nationalité de l'étudiant.
	 * @param iban le numéro IBAN du compte bancaire de l'étudiant.
	 * @param conseilSocial <code>true</code> si l'étudiant bénéficie d'un soutient du service social, <code>false</code> sinon.
	 * @param lieuEcole l'implantation (ville) de l'école où l'étudiant suit les cours.
	 * @param role le rôle que l'étudiant souhaite assumer lors d'un événement.
	 */
	public Etudiant(String nom, String prenom, int numTel, String mail, String matricule, int id,
			LocalDate dateNaissance, String paysDeNaissance, String lieuDeNaissance, String numeroNational,
			String nationalite, String iban, boolean conseilSocial, String lieuEcole, String role) {
		super(id, nom, prenom, numTel, mail, matricule);
		this.id = id;
		this.dateNaissance = dateNaissance;
		this.paysDeNaissance = paysDeNaissance;
		this.lieuDeNaissance = lieuDeNaissance;
		this.numeroNational = numeroNational;
		this.nationalite = nationalite;
		this.iban = iban;
		this.conseilSocial = conseilSocial;
		this.lieuEcole = lieuEcole;
		this.role = role;
	}
	
//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
  
// Getter et setter pour l'attribut "id" 

	/**
	 * Renvoie l'identifiant (BD) de l'étudiant.
	 * @return l'identifiant (BD) de l'étudiant.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Affecte l'identifiant (BD) de l'étudiant.
	 * @param id l'identifiant (BD) à affecter.
	 */
	public void setId(int id) {
		this.id = id;
	}

// Getter et setter pour l'attribut "dateNaissance" 
	
	/**
	 * Renvoie la date de naissance de l'étudiant (au format LocalDate).
	 * @return la date de naissance de l'étudiant (au format LocalDate).
	 */
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * Affecte la date de naissance de l'étudiant (au format LocalDate).
	 * @param dateNaissance la date de naissance à affecter (au format LocalDate).
	 */
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

// Getter et setter pour l'attribut "paysDeNaissance" 
	
	/**
	 * Renvoie le pays de naissance de l'étudiant.
	 * @return le pays de naissance de l'étudiant.
	 */
	public String getPaysDeNaissance() {
		return paysDeNaissance;
	}

	/**
	 * Affecte le pays de naissance de l'étudiant.
	 * @param paysDeNaissance le pays de naissance à affecter.
	 */
	public void setPaysDeNaissance(String paysDeNaissance) {
		this.paysDeNaissance = paysDeNaissance;
	}

// Getter et setter pour l'attribut "lieuDeNaissance" 
	
	/**
	 * Renvoie le lieu de naissance de l'étudiant.
	 * @return le lieu de naissance de l'étudiant.
	 */
	public String getLieuDeNaissance() {
		return lieuDeNaissance;
	}

	/**
	 * Affecte le lieu de naissance de l'étudiant.
	 * @param lieuDeNaissance le lieu de naissance à affecter.
	 */
	public void setLieuDeNaissance(String lieuDeNaissance) {
		this.lieuDeNaissance = lieuDeNaissance;
	}

// Getter et setter pour l'attribut "numeroNatioanl" 
	
	/**
	 * Renvoie le numéro national identifiant de l'étudiant.
	 * @return le numéro national identifiant de l'étudiant.
	 */
	public String getNumeroNational() {
		return numeroNational;
	}

	/**
	 * Affecte le numéro national de l'étudiant.
	 * @param numeroNational le numéro national à affecter.
	 */
	public void setNumeroNational(String numeroNational) {
		this.numeroNational = numeroNational;
	}

// Getter et setter pour l'attribut "nationalite" 
	
	/**
	 * Renvoie la nationalité de l'étudiant.
	 * @return la nationalité de l'étudiant.
	 */
	public String getNationalite() {
		return nationalite;
	}

	/**
	 * Affecte la nationalité de l'étudiant.
	 * @param nationalite la nationalité à affecter.
	 */
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

// Getter et setter pour l'attribut "iban" 
	
	/**
	 * Renvoie le numéro IBAN du compte bancaire de l'étudiant.
	 * @return le numéro IBAN du compte bancaire de l'étudiant.
	 */
	public String getIban() {
		return iban;
	}

	/**
	 * Affecte le numéro IBAN du compte bancaire de l'étudiant.
	 * @param iban le numéro IBAN du compte bancaire à affecter.
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}

// Getter et setter pour l'attribut "conseilSocial" 
	
	/**
	 * Renvoie <code>true</code> si l'étudiant bénéficie d'un soutient du service social, <code>false</code> sinon.
	 * @return <code>true</code> si l'étudiant bénéficie d'un soutient du service social, <code>false</code> sinon.
	 */
	public boolean isConseilSocial() {
		return conseilSocial;
	}

	/**
	 * Affecte une valeur booléenne renseignant si l'étudiant bénéficie d'un soutient du service social.
	 * @param conseilSocial <code>true</code> si l'étudiant bénéficie d'un soutient du service social, <code>false</code> sinon.
	 */
	public void setConseilSocial(boolean conseilSocial) {
		this.conseilSocial = conseilSocial;
	}

// Getter et setter pour l'attribut "lieuEcole" 
	
	/**
	 * Renvoie l'implantation (ville) de l'école où l'étudiant suit les cours.
	 * @return l'implantation (ville) de l'école où l'étudiant suit les cours.
	 */
	public String getLieuEcole() {
		return lieuEcole;
	}

	/**
	 * Affecte l'implantation (ville) de l'école où l'étudiant suit les cours.
	 * @param lieuEcole l'implantation (ville) de l'école à affecter.
	 */
	public void setLieuEcole(String lieuEcole) {
		this.lieuEcole = lieuEcole;
	}

// Getter et setter pour l'attribut "role" 
	
	/**
	 * Renvoie le rôle que l'étudiant souhaite assumer lors d'un événement.
	 * @return le rôle que l'étudiant souhaite assumer lors d'un événement.
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Affecte le rôle que l'étudiant souhaite assumer lors d'un événement.
	 * @param role le rôle de l'étudiant à affecter.
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
