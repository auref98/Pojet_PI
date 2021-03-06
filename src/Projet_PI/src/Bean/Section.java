/*
 * Haute �cole Robert Schuman - Libramont, ann�e scolaire 2017 - 2018
 * Bachelier en informatique de gestion, bloc 2
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

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Classe de type "bean" repr�sentant une section de l'�coled. <br><br>
 * En plus de son nom, elle liste les �tudiants et professeurs qui en font partie, le relais de communication dont elle d�pend ainsi que  les personnes souhaitant �tre tenues au courant des actualit�s la concernant.
 * @see Contact
 * @see Etudiant
 * @see Professeur
 * @see java.util.ArrayList
 */
public class Section implements Serializable {
  private int id;													// Attribut permettant de recuperer l'id r�f�ren�ant cette adresse dans la base de donnees
  private String nom;												// Attribut contenant le nom complet de la section
  

  private ArrayList<Etudiant> listeEtudiant;							// R�f�rence vers une liste d'objets Etudiant faisant partie de la section
  private ArrayList<Contact> listeContact;							// R�f�rence une liste d'objets Constact
  private Professeur relais;											// Attribut r�f�ren�ant le relais de communication de la section
  private ArrayList<Professeur> listeProf;								// R�f�rence vers une liste d'objets Professeur enseignant dans la section
  
 //###################################################################################################################################################################
	
  	// Constructeurs
	
//###################################################################################################################################################################
  
  	/** Constructeur sans param�tre. */
  	public Section(){}
  	
  	/**
	 * Constructeur initialisant tous les param�tres. <br><br>
	 * Pr�condition: tous les param�tres sont correctement initialis�s. <br>
	 * Postcondition: l'objet est initialis�; les attributs <code>id, nom, relais</code> sont initialis�s avec la valeur du param�tre de m�me nom; <br>
	 *				 les champs <code>listeEtudiant, listeContact, listeProf</code> sont initialis�s avec une liste vide de type appropri�. <br>
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
	 * @param nom le nom � affecter
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

// Getter et setter pour l'attribut "relais"
	
	/**
	 * Renvoie la r�f�rence de l'objet <code>Professeur</code> oficiant comme relais de communication pour la section.
	 * @return la r�f�rence de l'objet <code>Professeur</code> oficiant comme relais de communication pour la section
	 * @see Professeur
	 */
	public Professeur getRelais() {
		return relais;
	}

	/**
	 * Affecte la r�f�rence d'un objet <code>Professeur</code> � l'attribut repr�sentant le relais de communication de la section.
	 * @param relais la r�f�rence d'un objet <code>Professeur</code> � affecter  � l'attribut repr�sentant le relais de communication de l'objet courant
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
	 * @param id l'id (BD) � affecter
	 */
	public void setId(int id) {
		this.id = id;
	}

// Getter et setter pour l'attribut "listeEtudiant"
	
	/**
	 * Renvoie la r�f�rence de la liste des <code>Etudiant</code> appartenant � la section.
	 * @return la r�f�rence de la liste des <code>Etudiant</code> appartenant � la section
	 * @see Etudiant
	 */
	public ArrayList<Etudiant> getEtudiant() {
		return listeEtudiant;
	}
	
	/**
	 * Affecte la r�f�rence d'une <code>ArrayList</code> d'objets <code>Etudiant</code> � l'objet courant.
	 * @param tab la r�f�rence d'une <code>ArrayList</code> d'objets <code>Etudiant</code>� affecter � l'objet courant
	 * @see Etudiant
	 */
	public void setListeEtudiant(ArrayList<Etudiant> tab) {
		listeEtudiant = tab;
	}

// Getter et setter pour l'attribut "listeContact"
	
	/**
	 * Renvoie la r�f�rence de la liste des <code>Contact</code> int�ress�s par la section.
	 * @return  la r�f�rence de la liste des <code>Contact</code> int�ress�s par la section
	 * @see Contact
	 */
	public ArrayList<Contact> getContact() {
		return listeContact;
	}
	
	/**
	 * Affecte la r�f�rence d'une <code>ArrayList</code> d'objets <code>Contact</code> � l'objet courant.
	 * @param tab la r�f�rence d'une <code>ArrayList</code> d'objets <code>Contact</code>� affecter � l'objet courant
	 * @see Contact
	 */
	public void setContact(ArrayList<Contact> tab) {
		listeContact = tab;
	}

// Getter et setter pour l'attribut "listeProf"
	
	/**
	 * Renvoie  la r�f�rence de la liste des <code>Professeur</code> enseignant dans la section.
	 * @return la r�f�rence de la liste des <code>Professeur</code> enseignant dans la section
	 * @see Professeur
	 */
	public ArrayList<Professeur> getListeProf() {
		return listeProf;
	}
	
	/**
	 * Affecte la r�f�rence d'une <code>ArrayList</code> d'objets <code>Professeur</code> � l'objet courant.
	 * @param tab la r�f�rence d'une <code>ArrayList</code> d'objets <code>Professeur</code>� affecter � l'objet courant
	 * @see Professeur
	 */
	public void setProf(ArrayList<Professeur> tab) {
		listeProf = tab;
	}

//###################################################################################################################################################################
	
	// Manipulation des listes
	
//###################################################################################################################################################################	

// M�thodes add (ajouter un abjet � une liste)
	
	/**
	 * Ajoute un objet <code>Etudiant</code> � l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>listeEtudiant</code>" est initialis� avec un type valide (<code>Etudiant</code>); l'objet <code>Etudiant e</code> est correctement initialis�. <br>
	 * Postcondition: l'objet <code>Etudiant e</code> a �t� ajout� � l'objet courant. <br>
	 * @param e l'objet <code>Etudiant</code> � ajouter � l'objet courant
	 * @see Etudiant
	 * @see java.util.ArrayList#add(Object o)
	 */
	public void addEtudiant(Etudiant e) {
		listeEtudiant.add(e);
	}
	
	/**
	 * Ajoute un objet <code>Contact</code> � l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>listeContact</code>" est initialis� avec un type valide (<code>Contact</code>); l'objet <code>Contact c</code> est correctement initialis�. <br>
	 * Postcondition: l'objet <code>Contact c</code> a �t� ajout� � l'objet courant. <br>
	 * @param c l'objet <code>Contact</code> � ajouter � l'objet courant
	 * @see Contact
	 * @see java.util.ArrayList#add(Object o)
	 */
	public void addContact(Contact c) {
		listeContact.add(c);
	}
	
	/**
	 * Ajoute un objet <code>Professeur</code> � l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>listeProfesseur</code>" est initialis� avec un type valide (<code>Professeur</code>); l'objet <code>Professeur p</code> est correctement initialis�. <br>
	 * Postcondition: l'objet <code>Professeur p</code> a �t� ajout� � l'objet courant. <br>
	 * @param p l'objet <code>Professeur</code> � ajouter � l'objet courant
	 * @see Professeur
	 * @see java.util.ArrayList#add(Object o)
	 */
	public void addProfesseur(Professeur p) {
		listeProf.add(p);
	}

// Methodes del (Supprimer un objet d'une liste)
	
	/**
	 * Supprime un objet <code>Etudiant</code> de l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>listeEtudiant</code>" est initialis�. <br>
	 * Postcondition: si l'objet <code>Etudiant e</code> se trouvait dans l'objet courant, il en a �t� supprim�; sinon l'objet courant est inchang�. <br>
	 * @param e l'objet <code>Etudiant</code> � supprimer de l'objet courant
	 * @see Etudiant
	 * @see java.util.ArrayList#remove(Object o)
	 */
	public void delEtudiant(Etudiant e) {
		listeEtudiant.remove(e);
	}
	
	/**
	 * Supprime un objet <code>Contact</code> de l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>listeContact</code>" est initialis�. <br>
	 * Postcondition: si l'objet <code>Contact c</code> se trouvait dans l'objet courant, il en a �t� supprim�; sinon l'objet courant est inchang�. <br>
	 * @param c l'objet <code>Contact</code> � supprimer de l'objet courant
	 * @see Contact
	 * @see java.util.ArrayList#remove(Object o)
	 */
	public void delContact(Contact c) {
		listeContact.remove(c);
	}
	
	/**
	 * Supprime un objet <code>Professeur</code> de l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>listeProf</code>" est initialis�. <br>
	 * Postcondition: si l'objet <code>Professeur p</code> se trouvait dans l'objet courant, il en a �t� supprim�; sinon l'objet courant est inchang�. <br>
	 * @param p l'objet <code>Professeur</code> � supprimer de l'objet courant
	 * @see Professeur
	 * @see java.util.ArrayList#remove(Object o)
	 */
	public void delProfesseur(Professeur p) {
		listeProf.remove(p);
	}

//###################################################################################################################################################################
	
	// Affichage
	
//###################################################################################################################################################################		
	
	/**
	 * Renvoie un objet <code>java.lang.String</code> correspondant au nom de la section suivi du relais de communication correspondant. <br><br>
	 * Pr�condition: les champs <code>nom, relais</code> de l'objet courant sont initialis�s. <br>
	 * Postcondition: l'objet courant est inchang�. <br>
	 * @return un objet <code>java.lang.String</code> correspondant au nom de la section suivi du relais de communication correspondant (Appelle la methode toString de la classe Professeur)
	 * @see Professeur#toString()
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return("le relais de la section "+ nom+" : "+relais.toString());
	}
}
