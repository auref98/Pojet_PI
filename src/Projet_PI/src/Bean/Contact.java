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

import DAO.DAOContact;

import java.io.Serializable;

/** Classe de type "bean" contenant les informations d'un visiteur d'un événement souhaitant recevoir des information sur une ou plusieurs sections de l'école.*/
public class Contact implements Serializable {
  private int id;													// Attribut permettant de récuperer l'id réferencant ce contact dans la base de données
  private String mail;// Attribut contenant l'adresse email du contact
  private DAOContact bdContact = new DAOContact;
  private Evenement eve;											// Référence l'événement auquel le contact a été enregistré
  private ArrayList<Section> interesse;
 
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
  
  /** Constructeur sans paramètre. */
  public Contact(){}
  
  /**
   * Constructeur initialisant l'adresse email du contact et l'événement où il a été enregistré.<br/><br/>
   * Précondition: les paramètres "mail" et "eve" sont correctement initialisés.<br/>
   * Postcondition: l'objet est initialisé; les attributs "mail" et "eve" sont initialisés avec la valeur des paramètres de même nom;<br/>
   * 				l'attribut "interesse" est initialisé avec une liste vide de type approprié (Section).<br/>
   * @param mail l'adresse email du contact.
   * @param eve l'événement où le contact a été enregistré.
   */
  public Contact(int id,String mail, Evenement eve){
	  this.id=id;
	  this.mail=mail;
	  this.eve = eve;
	  interesse = new ArrayList<Section>();							// Initialise l'attribut avec une liste vide
  }
  
//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
	
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
	
// Getter et setter pour l'attribut "mail" 
  
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
	
// Getter et setter pour l'attribut "eve" 
	
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
	
	public void setInteresse() {
		
	}
}
