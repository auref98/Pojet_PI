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

import java.util.*;



import java.io.Serializable;

/** Classe de type "bean" contenant les informations d'un visiteur d'un événement souhaitant recevoir des notifications concernant une ou plusieurs sections de l'école.*/
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
   * Constructeur initialisant tous les attributs de l'objet..<br/><br/>
   * Précondition: les paramètres "id", "mail" et "eve" sont correctement initialisés.<br/>
   * Postcondition: l'objet est initialisé; les attributs "id", "mail" et "eve" sont initialisés avec la valeur des paramètres de même nom;<br/>
   * 				l'attribut "interesse" est initialisé avec une liste vide de type approprié (Section).<br/>
   * @param id l'identifiant (BD) de l'adresse
   * @param mail l'adresse email du contact
   * @param eve l'événement où le contact a été enregistré
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
	 * Renvoie la référence de l'événement où le contact a été enregistré.
	 * @return la référence de l'événement où le contact a été enregistré
	 */
	public Evenement getEve() {
		return eve;
	}
	
	/**
	 * Affecte la référence de l'événement où le contact a été enregistré.
	 * @param eve la référence de l'événement à affecter
	 */
	public void setEve(Evenement eve) {
		this.eve = eve;
	}
	
//###################################################################################################################################################################
	
	// Manipulation des listes
	
//###################################################################################################################################################################	
	
	/**
	 * Ajoute un Contact à la liste.</br></br>
	 * Précondition: l'attribut "interesse" est initialisé avec un type valide (Section); l'objet Section s est correctement initialisé.</br>
	 * Postcondition: la Section s a été ajoutée à la liste "intéresse".</br>
	 * @param s l'objet Section à ajouter à la liste
	 */
	public void addInteresse(Section s) {
		interesse.add(s);
	}
	
	/**
	 * Supprime une Section de la liste référencée pas l'attribut "interesse".</br></br>
	 * Précondition: l'attribut "interesse" est initialisé.</br>
	 * Postcondition: si la Section s se trouvait dans la liste, celle-ci en a été supprimée; sinon la liste et l'objet courant sont inchangés.</br>
	 * @param s l'objet Section à supprimer de la liste
	 */
	public void delInteresse(Section s) {
		interesse.remove(s);
	}
	
	/**
	 * Affecte la référence d'une liste de Section à l'attribut "interesse".</br></br>
	 * Précondition: l'objet tab est correctement initialisé.</br>
	 * Postcondition: la liste tab a été affectée à l'objet courant.</br>
	 * @param tab la liste de Section à affecter à l'objet courant
	 */
	public void setInteresse(ArrayList<Section> tab) {
		interesse = tab;
	}
	
	/**
	 * Renvoie la référence de la liste de Section.</br></br>
	 * Précondition: l'objet courant est correctement initialisé.</br>
	 * Postcondition: l'objet courant est inchangé.</br>
	 * @return la référence de la liste de Section pour lesquelles le contact est intéressé
	 */
	public ArrayList<Section> getInteresse() {
		return interesse;
	}
}
