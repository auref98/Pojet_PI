/*
 * Haute �cole Robert Schuman - Libramont, ann�e scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integr�: r�alisation d'un logiciel de gestion des inscriptions � des �v�nements
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
 * Classe de type "bean" repr�sentant un �tudiant.  <br><br>
 * Etend la classe "<code>Repr�sentant</code>" dont elle h�rite les attributs d'une personne, et ajoute ses propres attributs 
 * sp�cifiques au informations n�cessaires � la mod�lisation d'un �tudiant.
 * @see Adresse
 * @see Section
 */
public class Etudiant extends Representant{
	
	private int id;												// Attribut permettant de recuperer l'id r�f�ren�ant cette adresse dans la base de donnees
	private LocalDate dateNaissance;								// Date de naissance de l'�tudiant
	private String paysNaissance;									// Pays de naissance de l'�tudiant
	private String lieuNaissance;									// Lieu de naissance de l'�tudiant
	private String numNational;									// Num�ro national identifiant l'�tudiant
	private String nationalite;										// Nationalit� actuelle de l'�tudiant
	private String numBanque;									// Num�ro numBanque du compte bancaire de l'�tudiant (les �tudiants repr�sentant l'�cole � un �v�nement sont r�mun�r�s)
	private boolean soutienSocial;									// Indique si l'�tudiant b�n�ficie d'un soutient du service social de l'�cole (si c'est le cas il b�n�ficie d'une priorit� � l'inscription)
	private String emplacementEcole;								// Implantaion (ville) de l'�cole o� l'�tudiant suit les cours
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
	 * Constructeur initialisant tous les param�tres. <br><br>
	 * Pr�condition: tous les param�tres sont correctement initialis�s. <br>
	 * Postcondition: l'objet est initialis�; tous les attributs sont initialis�s avec la valeur du param�tre de m�me nom.<br>
	 * @param lastName le nom de l'�tudiant
	 * @param firstName le pr�nom de l'�tudiant
	 * @param phone le num�ro de t�l�phone de l'�tudiant
	 * @param mail l'adresse email de l'�tudiant
	 * @param matricule le matricule HERS identifiant de l'�tudiant
	 * @param id l'identifiant (BD) de l'�tudiant
	 * @param dateNaissance la date de naissance de l'�tudiant (au format <code>LocalDate</code>)
	 * @param paysNaissance le pays de naissance de l'�tudiant
	 * @param lieuNaissance le lieu de naissance de l'�tudiant
	 * @param numNational le num�ro national identifiant de l'�tudiant
	 * @param nationalite la nationalit� de l'�tudiant
	 * @param numBanque le num�ro numBanque du compte bancaire de l'�tudiant
	 * @param soutienSocial <code>true</code> si l'�tudiant b�n�ficie d'un soutient du service social, <code>false</code> sinon
	 * @param emplacementEcole l'implantation (ville) de l'�cole o� l'�tudiant suit les cours
	 * @param role le r�le que l'�tudiant souhaite assumer lors d'un �v�nement
	 * @param adr la r�f�rence de l'<code>Adresse</code> de l'�tudiant
	 * @param sec la r�f�rence de la <code>Section</code> � laquelle l'�tudiant appartient
	 */
	public Etudiant(String lastName, String firstName, int phone, String mail, String matricule, int id,
			LocalDate dateNaissance, String paysNaissance, String lieuNaissance, String numNational,
			String nationalite, String numBanque, boolean soutienSocial, String emplacementEcole, String role, Adresse adr, Section sec) {
		super(id, lastName, firstName, phone, mail, matricule);
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
		this.adr = adr;
		this.sec = sec;
	}
	
//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
  
// Getter et setter pour l'attribut "id" 

	/**
	 * Renvoie l'identifiant (BD) de l'�tudiant.
	 * @return l'identifiant (BD) de l'�tudiant
	 */
	public int getId() {
		return id;
	}

	/**
	 * Affecte l'identifiant (BD) de l'�tudiant.
	 * @param id l'identifiant (BD) � affecter
	 */
	public void setId(int id) {
		this.id = id;
	}

// Getter et setter pour l'attribut "dateNaissance" 
	
	/**
	 * Renvoie la date de naissance de l'�tudiant (au format <code>LocalDate</code>).
	 * @return la date de naissance de l'�tudiant (au format <code>LocalDate</code>)
	 */
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * Affecte la date de naissance de l'�tudiant (au format <code>LocalDate</code>).
	 * @param dateNaissance la date de naissance � affecter (au format <code>LocalDate</code>)
	 */
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

// Getter et setter pour l'attribut "paysNaissance" 
	
	/**
	 * Renvoie le pays de naissance de l'�tudiant.
	 * @return le pays de naissance de l'�tudiant
	 */
	public String getPaysNaissance() {
		return paysNaissance;
	}

	/**
	 * Affecte le pays de naissance de l'�tudiant.
	 * @param paysNaissance le pays de naissance � affecter
	 */
	public void setPaysNaissance(String paysNaissance) {
		this.paysNaissance = paysNaissance;
	}

// Getter et setter pour l'attribut "lieuNaissance" 
	
	/**
	 * Renvoie le lieu de naissance de l'�tudiant.
	 * @return le lieu de naissance de l'�tudiant
	 */
	public String getLieuNaissance() {
		return lieuNaissance;
	}

	/**
	 * Affecte le lieu de naissance de l'�tudiant.
	 * @param lieuNaissance le lieu de naissance � affecter
	 */
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

// Getter et setter pour l'attribut "numeroNatioanl" 
	
	/**
	 * Renvoie le num�ro national identifiant de l'�tudiant.
	 * @return le num�ro national identifiant de l'�tudiant
	 */
	public String getNumNational() {
		return numNational;
	}

	/**
	 * Affecte le num�ro national de l'�tudiant.
	 * @param numNational le num�ro national � affecter
	 */
	public void setNumNational(String numNational) {
		this.numNational = numNational;
	}

// Getter et setter pour l'attribut "nationalite" 
	
	/**
	 * Renvoie la nationalit� de l'�tudiant.
	 * @return la nationalit� de l'�tudiant
	 */
	public String getNationalite() {
		return nationalite;
	}

	/**
	 * Affecte la nationalit� de l'�tudiant.
	 * @param nationalite la nationalit� � affecter
	 */
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

// Getter et setter pour l'attribut "numBanque" 
	
	/**
	 * Renvoie le num�ro numBanque du compte bancaire de l'�tudiant.
	 * @return le num�ro numBanque du compte bancaire de l'�tudiant
	 */
	public String getNumBanque() {
		return numBanque;
	}

	/**
	 * Affecte le num�ro numBanque du compte bancaire de l'�tudiant.
	 * @param numBanque le num�ro numBanque du compte bancaire � affecter
	 */
	public void setNumBanque(String numBanque) {
		this.numBanque = numBanque;
	}

// Getter et setter pour l'attribut "soutienSocial" 
	
	/**
	 * Renvoie <code>true</code> si l'�tudiant b�n�ficie d'un soutient du service social, <code>false</code> sinon.
	 * @return <code>true</code> si l'�tudiant b�n�ficie d'un soutient du service social, <code>false</code> sinon
	 */
	public boolean isSoutienSocial() {
		return soutienSocial;
	}

	/**
	 * Affecte une valeur bool�enne renseignant si l'�tudiant b�n�ficie d'un soutient du service social.
	 * @param soutienSocial <code>true</code> si l'�tudiant b�n�ficie d'un soutient du service social, <code>false</code> sinon
	 */
	public void setSoutienSocial(boolean soutienSocial) {
		this.soutienSocial = soutienSocial;
	}

// Getter et setter pour l'attribut "emplacementEcole" 
	
	/**
	 * Renvoie l'implantation (ville) de l'�cole o� l'�tudiant suit les cours.
	 * @return l'implantation (ville) de l'�cole o� l'�tudiant suit les cours
	 */
	public String getEmplacementEcole() {
		return emplacementEcole;
	}

	/**
	 * Affecte l'implantation (ville) de l'�cole o� l'�tudiant suit les cours.
	 * @param emplacementEcole l'implantation (ville) de l'�cole � affecter
	 */
	public void setEmplacementEcole(String emplacementEcole) {
		this.emplacementEcole = emplacementEcole;
	}

// Getter et setter pour l'attribut "role" 
	
	/**
	 * Renvoie le r�le que l'�tudiant souhaite assumer lors d'un �v�nement.
	 * @return le r�le que l'�tudiant souhaite assumer lors d'un �v�nement
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Affecte le r�le que l'�tudiant souhaite assumer lors d'un �v�nement.
	 * @param role le r�le de l'�tudiant � affecter
	 */
	public void setRole(String role) {
		this.role = role;
	}

// Getter et setter pour l'attribut "adr" 
	
	/**
	 * Renvoie l'adresse de l'�tudiant.
	 * @return l'adresse de l'�tudiant
	 * @see Adresse
	 */
	public Adresse getAdr() {
		return adr;
	}

	/**
	 * Affecte l'adresse de l'�tudiant.
	 * @param adr l'adresse de l'�tudiant
	 * @see Adresse
	 */
	public void setAdr(Adresse adr) {
		this.adr = adr;
	}

// Getter et setter pour l'attribut "sec" 
	
	/**
	 * Renvoie la section � laquelle l'�tudiant appartient.
	 * @return la section � laquelle l'�tudiant appartient
	 * @see Section
	 */
	public Section getSec() {
		return sec;
	}

	/** Renvoie la section � laquelle l'�tudiant appartient.
	 * @param sec la section � laquelle l'�tudiant appartient
	 * @see Section
	 */
	public void setSec(Section sec) {
		this.sec = sec;
	}
	
}
