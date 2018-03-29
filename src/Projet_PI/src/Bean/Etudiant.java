/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

package Bean;

import java.time.LocalDate;

public class Etudiant extends Representant{
	
	private int id;
	private LocalDate dateNaissance;
	private String paysDeNaissance;
	private String lieuDeNaissance;
	private String numeroNational;
	private String nationalite;
	private String iban;
	private boolean conseilSocial;
	private String lieuEcole;
	private String role;//ouvrier ou presenter au stand

	private Adresse adr;
	private Section sec;

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
	
	public Etudiant() {
		super();
		
	}

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
	 * @return the dateNaissance
	 */
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * @return the paysDeNaissance
	 */
	public String getPaysDeNaissance() {
		return paysDeNaissance;
	}

	/**
	 * @param paysDeNaissance the paysDeNaissance to set
	 */
	public void setPaysDeNaissance(String paysDeNaissance) {
		this.paysDeNaissance = paysDeNaissance;
	}

	/**
	 * @return the lieuDeNaissance
	 */
	public String getLieuDeNaissance() {
		return lieuDeNaissance;
	}

	/**
	 * @param lieuDeNaissance the lieuDeNaissance to set
	 */
	public void setLieuDeNaissance(String lieuDeNaissance) {
		this.lieuDeNaissance = lieuDeNaissance;
	}

	/**
	 * @return the numeroNational
	 */
	public String getNumeroNational() {
		return numeroNational;
	}

	/**
	 * @param numeroNational the numeroNational to set
	 */
	public void setNumeroNational(String numeroNational) {
		this.numeroNational = numeroNational;
	}

	/**
	 * @return the nationalite
	 */
	public String getNationalite() {
		return nationalite;
	}

	/**
	 * @param nationalite the nationalite to set
	 */
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	/**
	 * @return the iban
	 */
	public String getIban() {
		return iban;
	}

	/**
	 * @param iban the iban to set
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}

	/**
	 * @return the conseilSocial
	 */
	public boolean isConseilSocial() {
		return conseilSocial;
	}

	/**
	 * @param conseilSocial the conseilSocial to set
	 */
	public void setConseilSocial(boolean conseilSocial) {
		this.conseilSocial = conseilSocial;
	}

	/**
	 * @return the lieuEcole
	 */
	public String getLieuEcole() {
		return lieuEcole;
	}

	/**
	 * @param lieuEcole the lieuEcole to set
	 */
	public void setLieuEcole(String lieuEcole) {
		this.lieuEcole = lieuEcole;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	
	

  
}
