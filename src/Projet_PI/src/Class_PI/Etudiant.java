package Class_PI;

import java.time.LocalDate;

class Etudiant extends Representant{
  private LocalDate dateNaissance;
  private String paysDeNaissance;
  private String lieuDeNaissance;
  private String numeroNational;
  private String nationalite;
  private String iban;
  private boolean conseilSocial;
  private String lieuEcole;
  private String role;//ouvrier ou presenter au stand

  private Adresse reside;
  private Section etudie;

  public Etudiant(String nom, String prenom, int numTel, String mail, String matricule, LocalDate dateNaissance,
	String paysDeNaissance, String lieuDeNaissance, String numeroNational, String nationalite, String iban,
	boolean conseilSocial, String lieuEcole, String role, Adresse reside, Section etudie) {
	
	super(nom, prenom, numTel, mail, matricule);
	this.dateNaissance = dateNaissance;
	this.paysDeNaissance = paysDeNaissance;
	this.lieuDeNaissance = lieuDeNaissance;
	this.numeroNational = numeroNational;
	this.nationalite = nationalite;
	this.iban = iban;
	this.conseilSocial = conseilSocial;
	this.lieuEcole = lieuEcole;
	this.role = role;
	this.reside = reside;
	this.etudie = etudie;
}

  
}
