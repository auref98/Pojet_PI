/*
 * Haute école Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integre: realisation d'un logiciel de gestion des inscriptions à des evenements
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.io.Serializable;

public class Section implements Serializable {
  private int id;
  private String nom;
  

  private ArrayList<Etudiant> listeEtudiant;
  private ArrayList<Contact> listeContact;
  private Professeur relais;
  private ArrayList<Professeur> listeProf;
  
 //###################################################################################################################################################################
	
  	// Constructeurs
	
//###################################################################################################################################################################
  
  	/** Constructeur sans paramètre. */
	public Representant() {

	}
  	public Section(){}
  	
	public Section(int id, String nom, Professeur relais)
	{
		this.id=id;
		this.nom = nom;
		this.relais = relais;
		listeEtudiant= new ArrayList<Etudiant>();
		listeContact= new ArrayList<Contact>();
		listeProf= new ArrayList<Professeur>();
		
	}

//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
	
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
	 * @return the relais
	 */
	public Professeur getRelais() {
		return relais;
	}

	/** 
	 
	 * */
	public int getId() {
		return id;
	}
	
	/** 
	 
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @param relais the relais to set
	 */
	public void setRelais(Professeur relais) {
		this.relais = relais;
	}

//###################################################################################################################################################################
	
	// Manipulation des listes
	
//###################################################################################################################################################################	
	
	public void addEtudiant(Etudiant e) {
		listeEtudiant.add(e);
	}
	
	public void addContact(Contact c) {
		listeContact.add(c);
	}
	
	public void addProfesseur(Professeur p) {
		listeProf.add(p);
	}
	
	public void delEtudiant(Etudiant e) {
		listeEtudiant.remove(e);
	}
	
	public void delContact(Contact c) {
		listeContact.remove(c);
	}
	
	public void delProfesseur(Professeur p) {
		listeProf.remove(p);
	}
	
	public void setEtudiant(ArrayList<Etudiant> tab) {
		listeEtudiant = tab;
	}
	
	public void setContact(ArrayList<Contact> tab) {
		listeContact = tab;
	}
	
	public void setProf(ArrayList<Professeur> tab) {
		listeProf = tab;
	}
	
	public ArrayList<Etudiant> getEtudiant() {
		return listeEtudiant;
	}
	
	public ArrayList<Contact> getContact() {
		return listeContact;
	}
	
	public ArrayList<Professeur> getProfesseur() {
		return listeProf;
	}

//###################################################################################################################################################################
	
	// Affichage
	
//###################################################################################################################################################################		
	

	public String toString() {
		return("le relais de la section "+ nom+" : "+relais.toString());
	}
}
