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

/** Classe de type "bean" contenant les informations d'un visiteur d'un événement souhaitant recevoir des information sur une ou plusieurs sections de l'école.*/
public class Contact implements Serializable {
  private int id;
  private String mail;

  private Evenement eve;
  private ArrayList<Section> interesse;
 
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
  //constructeur contact
  //pré:la variable mail est initialisé
  //post:mail contient maintenant le nouveau mail  
  
  /** Constructeur sans paramètre. */
  public Contact(){}
  
  /**
   * Constructeur initialisant l'adresse email du contact et l'événement où il a été enregistré.
   * Précondition: les paramètres "mail" et "eve" sont correctement initialisés.
   * Postcondition: les attributs "mail" et "eve" sont initialisés avec la valeur des paramètres de même nom; 
   * 				l'attribut "interesse" est initialisé avec une liste vide de type approprié (Section).
   * @param mail l'adresse email du contact.
   * @param eve l'événement où le contact a été enregistré.
   */
  public Contact(String mail, Evenement eve){
	  this.mail=mail;
	  this.eve = eve;
	  interesse = new ArrayList<Section>();
  }
  
//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
	
	/**
	 * Renvoie l'adresse email du contact.
	 * @return l'adresse email du contact.
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * Affecte l'adresse email du contact
	 * @param mail l'adresse email du contact à affecter.
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * Renvoie la référence de l'événement où le contact a été enregistré.
	 * @return la référence de l'événement où le contact a été enregistré.
	 */
	public Evenement getEve() {
		return eve;
	}
	
	/**
	 * Affecte la référence de l'événement où le contact a été enregistré.
	 * @param eve la référence de l'événement à affecter.
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
	 * @param l'objet Section à ajouter à la liste.
	 */
	public void addInteresse(Section s) {
		interesse.add(s);
	}
	
	/**
	 * Supprime une Section de la liste.</br></br>
	 * Précondition: l'attribut "interesse" est initialisé.</br>
	 * Postcondition: si la section s se trouvait dans la liste, celle-ci en a été supprimée; sinon la liste et l'objet courant sont inchangés.</br>
	 * @param s l'objet Section à supprimer de la liste.
	 */
	public void delInteresse(Section s) {
		interesse.remove(s);
	}
}
