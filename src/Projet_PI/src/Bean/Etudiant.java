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
	private String paysNaissance;									// Pays de naissance de l'étudiant
	private String lieuNaissance;									// Lieu de naissance de l'étudiant
	private String numNational;										// Numéro national identifiant l'étudiant
	private String nationalite;										// Nationalité actuelle de l'étudiant
	private String numBanque;											// Numéro numBanque du compte bancaire de l'étudiant (les étudiants représentant l'école à un événement sont rémunérés)
	private boolean soutienSocial;									// Indique si l'étudiant bénéficie d'un soutient du service social de l'école (si c'est le cas il bénéficie d'une priorité à l'inscription)
	private String emplacementEcole;										// Implantaion (ville) de l'école où l'étudiant suit les cours
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
	 * @param paysNaissance le pays de naissance de l'étudiant.
	 * @param lieuNaissance le lieu de naissance de l'étudiant.
	 * @param numNational le numéro national identifiant de l'étudiant.
	 * @param nationalite la nationalité de l'étudiant.
	 * @param numBanque le numéro numBanque du compte bancaire de l'étudiant.
	 * @param soutienSocial <code>true</code> si l'étudiant bénéficie d'un soutient du service social, <code>false</code> sinon.
	 * @param emplacementEcole l'implantation (ville) de l'école où l'étudiant suit les cours.
	 * @param role le rôle que l'étudiant souhaite assumer lors d'un événement.
	 */
	public Etudiant(String nom, String prenom, int numTel, String mail, String matricule, int id,
			LocalDate dateNaissance, String paysNaissance, String lieuNaissance, String numNational,
			String nationalite, String numBanque, boolean soutienSocial, String emplacementEcole, String role) {
		super(id, nom, prenom, numTel, mail, matricule);
		this.id = id;
		this.dateNaissance = dateNaissance;
		this.paysNaissance = paysNaissance;
		this.lieuNaissance = lieuNaissance;
		this.numNational = numNational;
		this.nationalite = nationalite;
		this.numBanque = numBanque;
		this.soutienSocial = soutienSocial;
		this.emplacementEcole = emplacementEcole;
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

// Getter et setter pour l'attribut "paysNaissance" 
	
	/**
	 * Renvoie le pays de naissance de l'étudiant.
	 * @return le pays de naissance de l'étudiant.
	 */
	public String getPaysNaissance() {
		return paysNaissance;
	}

	/**
	 * Affecte le pays de naissance de l'étudiant.
	 * @param paysNaissance le pays de naissance à affecter.
	 */
	public void setPaysNaissance(String paysNaissance) {
		this.paysNaissance = paysNaissance;
	}

// Getter et setter pour l'attribut "lieuNaissance" 
	
	/**
	 * Renvoie le lieu de naissance de l'étudiant.
	 * @return le lieu de naissance de l'étudiant.
	 */
	public String getLieuNaissance() {
		return lieuNaissance;
	}

	/**
	 * Affecte le lieu de naissance de l'étudiant.
	 * @param lieuNaissance le lieu de naissance à affecter.
	 */
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

// Getter et setter pour l'attribut "numeroNatioanl" 
	
	/**
	 * Renvoie le numéro national identifiant de l'étudiant.
	 * @return le numéro national identifiant de l'étudiant.
	 */
	public String getNumNational() {
		return numNational;
	}

	/**
	 * Affecte le numéro national de l'étudiant.
	 * @param numNational le numéro national à affecter.
	 */
	public void setNumNational(String numNational) {
		this.numNational = numNational;
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

// Getter et setter pour l'attribut "numBanque" 
	
	/**
	 * Renvoie le numéro numBanque du compte bancaire de l'étudiant.
	 * @return le numéro numBanque du compte bancaire de l'étudiant.
	 */
	public String getNumBanque() {
		return numBanque;
	}

	/**
	 * Affecte le numéro numBanque du compte bancaire de l'étudiant.
	 * @param numBanque le numéro numBanque du compte bancaire à affecter.
	 */
	public void setNumBanque(String numBanque) {
		this.numBanque = numBanque;
	}

// Getter et setter pour l'attribut "soutienSocial" 
	
	/**
	 * Renvoie <code>true</code> si l'étudiant bénéficie d'un soutient du service social, <code>false</code> sinon.
	 * @return <code>true</code> si l'étudiant bénéficie d'un soutient du service social, <code>false</code> sinon.
	 */
	public boolean isSoutienSocial() {
		return soutienSocial;
	}

	/**
	 * Affecte une valeur booléenne renseignant si l'étudiant bénéficie d'un soutient du service social.
	 * @param soutienSocial <code>true</code> si l'étudiant bénéficie d'un soutient du service social, <code>false</code> sinon.
	 */
	public void setSoutienSocial(boolean soutienSocial) {
		this.soutienSocial = soutienSocial;
	}

// Getter et setter pour l'attribut "emplacementEcole" 
	
	/**
	 * Renvoie l'implantation (ville) de l'école où l'étudiant suit les cours.
	 * @return l'implantation (ville) de l'école où l'étudiant suit les cours.
	 */
	public String getEmplacementEcole() {
		return emplacementEcole;
	}

	/**
	 * Affecte l'implantation (ville) de l'école où l'étudiant suit les cours.
	 * @param emplacementEcole l'implantation (ville) de l'école à affecter.
	 */
	public void setEmplacementEcole(String emplacementEcole) {
		this.emplacementEcole = emplacementEcole;
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

	/**
	 * @return the adr
	 */
	public Adresse getAdr() {
		return adr;
	}

	/**
	 * @param adr the adr to set
	 */
	public void setAdr(Adresse adr) {
		this.adr = adr;
	}

	/**
	 * @return the sec
	 */
	public Section getSec() {
		return sec;
	}

	/**
	 * @param sec the sec to set
	 */
	public void setSec(Section sec) {
		this.sec = sec;
	}
	
	

}
