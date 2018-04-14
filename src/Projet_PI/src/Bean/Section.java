/*
 * Haute école Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integré: réalisation d'un logiciel de gestion des inscriptions à des événements
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

/**
 * Classe de type "bean" représentant une section de l'écoled.<br><br>
 * En plus de son nom, elle liste les étudiants et professeurs qui en font partie, le relais de communication dont elle dépend ainsi que  les personnes souhaitant être tenues au courant des actualités la concernant.
 * @see Contact
 * @see Etudiant
 * @see Professeur
 * @see java.util.ArrayList
 */
public class Section implements Serializable {
  private int id;													// Attribut permettant de recuperer l'id référençant cette adresse dans la base de donnees
  private String nom;												// Attribut contenant le nom complet de la section
  

  private ArrayList<Etudiant> listeEtudiant;							// Référence vers une liste d'objets Etudiant faisant partie de la section
  private ArrayList<Contact> listeContact;							// Référence une liste d'objets Constact
  private Professeur relais;											// Attribut référençant le relais de communication de la section
  private ArrayList<Professeur> listeProf;								// Référence vers une liste d'objets Professeur enseignant dans la section
  
 //###################################################################################################################################################################
	
  	// Constructeurs
	
//###################################################################################################################################################################
  
  	/** Constructeur sans paramètre. */
  	public Section(){}
  	
  	/**
  	 * 	/**
	 * Constructeur initialisant tous les paramètres.<br><br>
	 * Précondition: tous les paramètres sont correctement initialisés.<br>
	 * Postcondition: l'objet est initialisé; les attributs <code>id, nom, relais</code> sont initialisés avec la valeur du paramètre de même nom;<br>
	 *				 les champs <code>listeEtudiant, listeContact, listeProf</code> sont initialisés avec une liste vide de type approprié.<br>
  	 * @param id l'identifiant (BD) de la section
  	 * @param nom le nom de la section
  	 * @param relais le <code>Professeur</code> oficiant comme relais de communication pour la section
  	 * @see Professeur
  	 */
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

// Getter et setter pour l'attribut "nom"
	
	/**
	 * Rnvoie le nom de la section.
	 * @return le nom de la section
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Affecte le nom de la section.
	 * @param nom le nom à affecter
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

// Getter et setter pour l'attribut "relais"
	
	/**
	 * Renvoie la référence de l'objet <code>Professeur</code> oficiant comme relais de communication pour la section.
	 * @return la référence de l'objet <code>Professeur</code> oficiant comme relais de communication pour la section
	 * @see Professeur
	 */
	public Professeur getRelais() {
		return relais;
	}

	/**
	 * Affecte la référence d'un objet <code>Professeur</code> à l'attribut représentant le relais de communication de la section.
	 * @param relais la référence d'un objet <code>Professeur</code> à affecter  à l'attribut représentant le relais de communication de l'objet courant
	 * @see Professeur
	 */
	public void setRelais(Professeur relais) {
		this.relais = relais;
	}
	
// Getter et setter pour l'attribut "id"
	
	/**
	 * Rnvoie l'id (BD) de la section.
	 * @return l'id (BD) de la section
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Affecte l'id de (BD) de la section.
	 * @param id l'id (BD) à affecter
	 */
	public void setId(int id) {
		this.id = id;
	}

// Getter et setter pour l'attribut "listeEtudiant"
	
	/**
	 * Renvoie la référence de la liste des <code>Etudiant</code> appartenant à la section.
	 * @return la référence de la liste des <code>Etudiant</code> appartenant à la section
	 * @see Etudiant
	 */
	public ArrayList<Etudiant> getEtudiant() {
		return listeEtudiant;
	}
	
	/**
	 * Affecte la référence d'une <code>ArrayList</code> d'objets <code>Etudiant</code> à l'objet courant.
	 * @param tab la référence d'une <code>ArrayList</code> d'objets <code>Etudiant</code>à affecter à l'objet courant
	 * @see Etudiant
	 */
	public void setListeEtudiant(ArrayList<Etudiant> tab) {
		listeEtudiant = tab;
	}

// Getter et setter pour l'attribut "listeContact"
	
	/**
	 * Renvoie la référence de la liste des <code>Contact</code> intéressés par la section.
	 * @return  la référence de la liste des <code>Contact</code> intéressés par la section
	 * @see Contact
	 */
	public ArrayList<Contact> getContact() {
		return listeContact;
	}
	
	/**
	 * Affecte la référence d'une <code>ArrayList</code> d'objets <code>Contact</code> à l'objet courant.
	 * @param tab la référence d'une <code>ArrayList</code> d'objets <code>Contact</code>à affecter à l'objet courant
	 * @see Contact
	 */
	public void setContact(ArrayList<Contact> tab) {
		listeContact = tab;
	}

// Getter et setter pour l'attribut "listeProf"
	
	/**
	 * Renvoie  la référence de la liste des <code>Professeur</code> enseignant dans la section.
	 * @return la référence de la liste des <code>Professeur</code> enseignant dans la section
	 * @see Professeur
	 */
	public ArrayList<Professeur> getProfesseur() {
		return listeProf;
	}
	
	/**
	 * Affecte la référence d'une <code>ArrayList</code> d'objets <code>Professeur</code> à l'objet courant.
	 * @param tab la référence d'une <code>ArrayList</code> d'objets <code>Professeur</code>à affecter à l'objet courant
	 * @see Professeur
	 */
	public void setProf(ArrayList<Professeur> tab) {
		listeProf = tab;
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

//###################################################################################################################################################################
	
	// Affichage
	
//###################################################################################################################################################################		
	
	/**
	 * Renvoie un objet <code>java.lang.String</code> correspondant au nom de la section suivi du relais de communication correspondant.<br><br>
	 * Précondition: les champs <code>nom, relais</code> de l'objet courant sont initialisés.<br>
	 * Postcondition: l'objet courant est inchangé.<br>
	 * @return un objet <code>java.lang.String</code> correspondant au nom de la section suivi du relais de communication correspondant (Appelle la methode toString de la classe Professeur)
	 * @see Professer#toString()
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return("le relais de la section "+ nom+" : "+relais.toString());
	}
}
