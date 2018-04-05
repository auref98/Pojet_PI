/*
 * Haute �cole Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integre: realisation d'un logiciel de gestion des inscriptions � des evenements
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

import DAO.DAO;

import java.io.Serializable;

/** Classe de type "bean" contenant les informations d'un visiteur d'un �v�nement souhaitant recevoir des information sur une ou plusieurs sections de l'�cole.*/
public class Contact implements Serializable {
  private int id;													// Attribut permettant de r�cuperer l'id r�ferencant ce contact dans la base de donn�es
  private String mail;// Attribut contenant l'adresse email du contact
  private DAO contact;
  private Evenement eve;											// R�f�rence l'�v�nement auquel le contact a �t� enregistr�
  private ArrayList<Section> interesse;
 
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
  
  /** Constructeur sans param�tre. */
  public Contact(){}
  
  /**
   * Constructeur initialisant l'adresse email du contact et l'�v�nement o� il a �t� enregistr�.<br/><br/>
   * Pr�condition: les param�tres "mail" et "eve" sont correctement initialis�s.<br/>
   * Postcondition: l'objet est initialis�; les attributs "mail" et "eve" sont initialis�s avec la valeur des param�tres de m�me nom;<br/>
   * 				l'attribut "interesse" est initialis� avec une liste vide de type appropri� (Section).<br/>
   * @param mail l'adresse email du contact.
   * @param eve l'�v�nement o� le contact a �t� enregistr�.
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
	 * @param mail l'adresse email du contact � affecter.
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
// Getter et setter pour l'attribut "eve" 
	
	/**
	 * Renvoie la r�f�rence de l'�v�nement o� le contact a �t� enregistr�.
	 * @return la r�f�rence de l'�v�nement o� le contact a �t� enregistr�.
	 */
	public Evenement getEve() {
		return eve;
	}
	
	/**
	 * Affecte la r�f�rence de l'�v�nement o� le contact a �t� enregistr�.
	 * @param eve la r�f�rence de l'�v�nement � affecter.
	 */
	public void setEve(Evenement eve) {
		this.eve = eve;
	}
	
//###################################################################################################################################################################
	
	// Manipulation des listes
	
//###################################################################################################################################################################	
	
	/**
	 * Ajoute un Contact � la liste.</br></br>
	 * Pr�condition: l'attribut "interesse" est initialis� avec un type valide (Section); l'objet Section s est correctement initialis�.</br>
	 * Postcondition: la Section s a �t� ajout�e � la liste "int�resse".</br>
	 * @param l'objet Section � ajouter � la liste.
	 */
	public void addInteresse(Section s) {
		interesse.add(s);
	}
	
	/**
	 * Supprime une Section de la liste.</br></br>
	 * Pr�condition: l'attribut "interesse" est initialis�.</br>
	 * Postcondition: si la section s se trouvait dans la liste, celle-ci en a �t� supprim�e; sinon la liste et l'objet courant sont inchang�s.</br>
	 * @param s l'objet Section � supprimer de la liste.
	 */
	public void delInteresse(Section s) {
		interesse.remove(s);
	}
	
	public void setInteresse() {
		
	}
}
