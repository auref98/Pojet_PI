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

import java.util.*;



import java.io.Serializable;

/** 
 * Classe de type "bean" contenant les informations d'un visiteur d'un �v�nement souhaitant recevoir des notifications concernant une ou plusieurs sections de l'�cole.
 * @see Evenement
 * @see Section
 * @see java.util.ArrayList
 */
public class Contact implements Serializable {
  private int id;													// Attribut permettant de r�cuperer l'id r�ferencant ce contact dans la base de donn�es
  private String mail;												// Attribut contenant l'adresse email du contact

  private Evenement eve;											// R�f�rence l'�v�nement auquel le contact a �t� enregistr�
  private ArrayList<Section> interesse;								// R�f�rence une liste d'objets Section par lesquelles le contact est int�r�ss�
 
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
  
  /** Constructeur sans param�tre. */
  public Contact(){}
  
  /**
   * Constructeur initialisant tous les attributs de l'objet. <br><br>
   * Pr�condition: les param�tres "<code>id</code>", "<code>mail</code>" et "<code>eve</code>" sont correctement initialis�s. <br>
   * Postcondition: l'objet est initialis�; les attributs "<code>id</code>", "<code>mail</code>" et "<code>eve</code>" sont initialis�s avec la valeur des param�tres de m�me nom; <br>
   * 				l'attribut "<code>interesse</code>" est initialis� avec une <code>ArrayList</code> vide de type appropri� (<code>Section</code>). <br>
   * @param id l'identifiant (BD) de l'adresse
   * @param mail l'adresse email du contact
   * @param eve la r�f�rence de l'<code>Evenement</code> o� le contact a �t� enregistr�
   */
  public Contact(int id, String mail, Evenement eve){
	  this.id=id;
	  this.mail=mail;
	  this.eve = eve;
	  interesse = new ArrayList<Section>();							// Initialise l'attribut avec une liste vide
  }
  
//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################

 // Getter et setter pour l'attribut "id" 
  
  	/**
	 * Renvoie l'identifiant (BD) de l'adresse.
	 * @return l'identifiant (BD) de l'adresse
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Affecte  l'identifiant (BD) de l'adresse.
	 * @param id l'identifiant (BD) de l'adresse � affecter
	 */
	public void setId(int id) {
		this.id = id;
	}
	
// Getter et setter pour l'attribut "mail" 
  
	/**
	 * Renvoie l'adresse email du contact.
	 * @return l'adresse email du contact
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * Affecte l'adresse email du contact.
	 * @param mail l'adresse email du contact � affecter
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
// Getter et setter pour l'attribut "eve" 
	
	/**
	 * Renvoie la r�f�rence de l'objet <code>Evenement</code> o� le contact a �t� enregistr�.
	 * @return la r�f�rence de l'<code>Evenement</code> li� � l'objet courant
	 * @see Evenement
	 */
	public Evenement getEve() {
		return eve;
	}
	
	/**
	 * Affecte la r�f�rence de l'<code>Evenement</code> o� le contact a �t� enregistr�.
	 * @param eve la r�f�rence de l'objet<code>Evenement</code>�  lier � l'objet courant
	 * @see Evenement
	 */
	public void setEve(Evenement eve) {
		this.eve = eve;
	}

// Getter et setter pour l'attribut "interesse"
	
	/**
	 * Renvoie la r�f�rence de l'<code>ArrayList</code> de <code>Section</code> pour lesquelles le contact est int�ress�.
	 * @return la r�f�rence de l'<code>ArrayList</code> de <code>Section</code> li�e � l'objet courant
	 * @see Section
	 * @see java.util.ArrayList
	 */
	public ArrayList<Section> getInteresse() {
		return interesse;
	}
	
	/**
	 * Affecte la r�f�rence d'une <code>ArrayList</code> de <code>Section</code> � l'objet courant.
	 * @param tab la liste de <code>Section</code> � affecter � l'objet courant
	 * @see Section
	 * @see java.util.ArrayList
	 */
	public void setInteresse(ArrayList<Section> tab) {
		interesse = tab;
	}
	
//###################################################################################################################################################################
	
	// Manipulation des listes
	
//###################################################################################################################################################################	
	
	/**
	 * Ajoute un objet <code>Contact</code> � l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>interesse</code>" est initialis� avec un type valide (<code>Section</code>); l'objet <code>Section s</code> est correctement initialis�. <br>
	 * Postcondition: l'objet <code>Section s</code> a �t� ajout� � l'objet courant. <br>
	 * @param s l'objet <code>Section</code> � ajouter � l'objet courant
	 * @see Section
	 * @see ArrayList#add(Object o)
	 */
	public void addInteresse(Section s) {
		interesse.add(s);
	}
	
	/**
	 * Supprime un objet <code>Section</code> de l'objet courant. <br><br>
	 * Pr�condition: l'attribut "<code>interesse</code>" est initialis�. <br>
	 * Postcondition: si l'objet <code>Section s</code> se trouvait dans l'objet courant, il en a �t� supprim�; sinon l'objet courant est inchang�. <br>
	 * @param s l'objet <code>Section</code> � supprimer de l'objet courant
	 * @see Section
	 * @see java.util.ArrayList#remove(Object o)
	 */
	public void delInteresse(Section s) {
		interesse.remove(s);
	}
	
}
