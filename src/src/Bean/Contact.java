/*
 * Haute école Robert Schuman - Libramont, année scolaire 2017 - 2018
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

import java.util.*;



import java.io.Serializable;

/** 
 * Classe de type "bean" contenant les informations d'un visiteur d'un événement souhaitant recevoir des notifications concernant une ou plusieurs sections de l'école.
 * @see Evenement
 * @see Section
 * @see java.util.ArrayList
 */
public class Contact implements Serializable {
  private int id;													// Attribut permettant de récuperer l'id réferencant ce contact dans la base de données
  private String mail;												// Attribut contenant l'adresse email du contact

  private Evenement eve;											// Référence l'événement auquel le contact a été enregistré
  private ArrayList<Section> interesse;								// Référence une liste d'objets Section par lesquelles le contact est intéréssé
 
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
  
  /** Constructeur sans paramètre. */
  public Contact(){}
  
  /**
   * Constructeur initialisant tous les attributs de l'objet. <br><br>
   * Précondition: les paramètres "<code>id</code>", "<code>mail</code>" et "<code>eve</code>" sont correctement initialisés. <br>
   * Postcondition: l'objet est initialisé; les attributs "<code>id</code>", "<code>mail</code>" et "<code>eve</code>" sont initialisés avec la valeur des paramètres de même nom; <br>
   * 				l'attribut "<code>interesse</code>" est initialisé avec une <code>ArrayList</code> vide de type approprié (<code>Section</code>). <br>
   * @param id l'identifiant (BD) de l'adresse
   * @param mail l'adresse email du contact
   * @param eve la référence de l'<code>Evenement</code> où le contact a été enregistré
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
	 * @param id l'identifiant (BD) de l'adresse à affecter
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
	 * @param mail l'adresse email du contact à affecter
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
// Getter et setter pour l'attribut "eve" 
	
	/**
	 * Renvoie la référence de l'objet <code>Evenement</code> où le contact a été enregistré.
	 * @return la référence de l'<code>Evenement</code> lié à l'objet courant
	 * @see Evenement
	 */
	public Evenement getEve() {
		return eve;
	}
	
	/**
	 * Affecte la référence de l'<code>Evenement</code> où le contact a été enregistré.
	 * @param eve la référence de l'objet<code>Evenement</code>à  lier à l'objet courant
	 * @see Evenement
	 */
	public void setEve(Evenement eve) {
		this.eve = eve;
	}

// Getter et setter pour l'attribut "interesse"
	
	/**
	 * Renvoie la référence de l'<code>ArrayList</code> de <code>Section</code> pour lesquelles le contact est intéressé.
	 * @return la référence de l'<code>ArrayList</code> de <code>Section</code> liée à l'objet courant
	 * @see Section
	 * @see java.util.ArrayList
	 */
	public ArrayList<Section> getInteresse() {
		return interesse;
	}
	
	/**
	 * Affecte la référence d'une <code>ArrayList</code> de <code>Section</code> à l'objet courant.
	 * @param tab la liste de <code>Section</code> à affecter à l'objet courant
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
	 * Ajoute un objet <code>Contact</code> à l'objet courant. <br><br>
	 * Précondition: l'attribut "<code>interesse</code>" est initialisé avec un type valide (<code>Section</code>); l'objet <code>Section s</code> est correctement initialisé. <br>
	 * Postcondition: l'objet <code>Section s</code> a été ajouté à l'objet courant. <br>
	 * @param s l'objet <code>Section</code> à ajouter à l'objet courant
	 * @see Section
	 * @see ArrayList#add(Object o)
	 */
	public void addInteresse(Section s) {
		interesse.add(s);
	}
	
	/**
	 * Supprime un objet <code>Section</code> de l'objet courant. <br><br>
	 * Précondition: l'attribut "<code>interesse</code>" est initialisé. <br>
	 * Postcondition: si l'objet <code>Section s</code> se trouvait dans l'objet courant, il en a été supprimé; sinon l'objet courant est inchangé. <br>
	 * @param s l'objet <code>Section</code> à supprimer de l'objet courant
	 * @see Section
	 * @see java.util.ArrayList#remove(Object o)
	 */
	public void delInteresse(Section s) {
		interesse.remove(s);
	}
	
}
