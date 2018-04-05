/*
 * Haute �cole Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integre: realisation d'un logiciel de gestion des inscriptions � des �v�nements
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
 * Classe de type "bean" repr�sentant un �tudiant.<br>
 * Etend la classe "Repr�sentant" dont elle h�rite les attributs d'une personne, et ajoute ses propres attributs 
 * sp�cifiques au informations n�cessaires � la mod�lisation d'un �tudiant.
 */
public class Etudiant extends Representant{
	
	private int id;													// Attribut permettant de recuperer l'id r�f�ren�ant cette adresse dans la base de donnees
	private LocalDate dateNaissance;								// Date de naissance de l'�tudiant
	private String paysDeNaissance;									// Pays de naissance de l'�tudiant
	private String lieuDeNaissance;									// Lieu de naissance de l'�tudiant
	private String numeroNational;									// Num�ro national identifiant l'�tudiant
	private String nationalite;										// Nationalit� actuelle de l'�tudiant
	private String iban;											// Num�ro IBAN du compte bancaire de l'�tudiant (les �tudiants repr�sentant l'�cole � un �v�nement sont r�mun�r�s)
	private boolean conseilSocial;									// Indique si l'�tudiant b�n�ficie d'un soutient du service social de l'�cole (si c'est le cas il b�n�ficie d'une priorit� � l'inscription)
	private String lieuEcole;										// Implantaion (ville) de l'�cole o� l'�tudiant suit les cours
	private String role;											// Attribut renseignant le r�le que l'�tudiant souhaite assumer lors d'un �v�nement (ouvrier, repr�sentant au stand,...).

	private Adresse adr;											// R�f�rence l'adresse de l'�tudiant
	private Section sec;											// Section � laquelle l'�tudiant appartient

//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Constructeur sans param�tre. */
	public Etudiant() {
		super();	
	}
	
	/**
	 * Constructeur initialisant tous les param�tres.<br><br>
	 * Pr�condition: tous les param�tres sont correctement initialis�s.<br>
	 * Postcondition: l'objet est initialis�; tous les attributs (sauf adr et sec) sont initialis�s avec la valeur du param�tre de m�me nom.<br>
	 * @param nom le nom de l'�tudiant.
	 * @param prenom le pr�nom de l'�tudiant.
	 * @param numTel le num�ro de t�l�phone de l'�tudiant.
	 * @param mail l'adresse email de l'�tudiant.
	 * @param matricule le matricule HERS identifiant de l'�tudiant.
	 * @param id l'identifiant (BD) de l'�tudiant.
	 * @param dateNaissance la date de naissance de l'�tudiant (au format LocalDate).
	 * @param paysDeNaissance le pays de naissance de l'�tudiant.
	 * @param lieuDeNaissance le lieu de naissance de l'�tudiant.
	 * @param numeroNational le num�ro national identifiant de l'�tudiant.
	 * @param nationalite la nationalit� de l'�tudiant.
	 * @param iban le num�ro IBAN du compte bancaire de l'�tudiant.
	 * @param conseilSocial <code>true</code> si l'�tudiant b�n�ficie d'un soutient du service social, <code>false</code> sinon.
	 * @param lieuEcole l'implantation (ville) de l'�cole o� l'�tudiant suit les cours.
	 * @param role le r�le que l'�tudiant souhaite assumer lors d'un �v�nement.
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
	 * Renvoie l'identifiant (BD) de l'�tudiant.
	 * @return l'identifiant (BD) de l'�tudiant.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Affecte l'identifiant (BD) de l'�tudiant.
	 * @param id l'identifiant (BD) � affecter.
	 */
	public void setId(int id) {
		this.id = id;
	}

// Getter et setter pour l'attribut "dateNaissance" 
	
	/**
	 * Renvoie la date de naissance de l'�tudiant (au format LocalDate).
	 * @return la date de naissance de l'�tudiant (au format LocalDate).
	 */
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * Affecte la date de naissance de l'�tudiant (au format LocalDate).
	 * @param dateNaissance la date de naissance � affecter (au format LocalDate).
	 */
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

// Getter et setter pour l'attribut "paysDeNaissance" 
	
	/**
	 * Renvoie le pays de naissance de l'�tudiant.
	 * @return le pays de naissance de l'�tudiant.
	 */
	public String getPaysDeNaissance() {
		return paysDeNaissance;
	}

	/**
	 * Affecte le pays de naissance de l'�tudiant.
	 * @param paysDeNaissance le pays de naissance � affecter.
	 */
	public void setPaysDeNaissance(String paysDeNaissance) {
		this.paysDeNaissance = paysDeNaissance;
	}

// Getter et setter pour l'attribut "lieuDeNaissance" 
	
	/**
	 * Renvoie le lieu de naissance de l'�tudiant.
	 * @return le lieu de naissance de l'�tudiant.
	 */
	public String getLieuDeNaissance() {
		return lieuDeNaissance;
	}

	/**
	 * Affecte le lieu de naissance de l'�tudiant.
	 * @param lieuDeNaissance le lieu de naissance � affecter.
	 */
	public void setLieuDeNaissance(String lieuDeNaissance) {
		this.lieuDeNaissance = lieuDeNaissance;
	}

// Getter et setter pour l'attribut "numeroNatioanl" 
	
	/**
	 * Renvoie le num�ro national identifiant de l'�tudiant.
	 * @return le num�ro national identifiant de l'�tudiant.
	 */
	public String getNumeroNational() {
		return numeroNational;
	}

	/**
	 * Affecte le num�ro national de l'�tudiant.
	 * @param numeroNational le num�ro national � affecter.
	 */
	public void setNumeroNational(String numeroNational) {
		this.numeroNational = numeroNational;
	}

// Getter et setter pour l'attribut "nationalite" 
	
	/**
	 * Renvoie la nationalit� de l'�tudiant.
	 * @return la nationalit� de l'�tudiant.
	 */
	public String getNationalite() {
		return nationalite;
	}

	/**
	 * Affecte la nationalit� de l'�tudiant.
	 * @param nationalite la nationalit� � affecter.
	 */
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

// Getter et setter pour l'attribut "iban" 
	
	/**
	 * Renvoie le num�ro IBAN du compte bancaire de l'�tudiant.
	 * @return le num�ro IBAN du compte bancaire de l'�tudiant.
	 */
	public String getIban() {
		return iban;
	}

	/**
	 * Affecte le num�ro IBAN du compte bancaire de l'�tudiant.
	 * @param iban le num�ro IBAN du compte bancaire � affecter.
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}

// Getter et setter pour l'attribut "conseilSocial" 
	
	/**
	 * Renvoie <code>true</code> si l'�tudiant b�n�ficie d'un soutient du service social, <code>false</code> sinon.
	 * @return <code>true</code> si l'�tudiant b�n�ficie d'un soutient du service social, <code>false</code> sinon.
	 */
	public boolean isConseilSocial() {
		return conseilSocial;
	}

	/**
	 * Affecte une valeur bool�enne renseignant si l'�tudiant b�n�ficie d'un soutient du service social.
	 * @param conseilSocial <code>true</code> si l'�tudiant b�n�ficie d'un soutient du service social, <code>false</code> sinon.
	 */
	public void setConseilSocial(boolean conseilSocial) {
		this.conseilSocial = conseilSocial;
	}

// Getter et setter pour l'attribut "lieuEcole" 
	
	/**
	 * Renvoie l'implantation (ville) de l'�cole o� l'�tudiant suit les cours.
	 * @return l'implantation (ville) de l'�cole o� l'�tudiant suit les cours.
	 */
	public String getLieuEcole() {
		return lieuEcole;
	}

	/**
	 * Affecte l'implantation (ville) de l'�cole o� l'�tudiant suit les cours.
	 * @param lieuEcole l'implantation (ville) de l'�cole � affecter.
	 */
	public void setLieuEcole(String lieuEcole) {
		this.lieuEcole = lieuEcole;
	}

// Getter et setter pour l'attribut "role" 
	
	/**
	 * Renvoie le r�le que l'�tudiant souhaite assumer lors d'un �v�nement.
	 * @return le r�le que l'�tudiant souhaite assumer lors d'un �v�nement.
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Affecte le r�le que l'�tudiant souhaite assumer lors d'un �v�nement.
	 * @param role le r�le de l'�tudiant � affecter.
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
