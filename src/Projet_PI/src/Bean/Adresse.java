/*
 * Haute école Robert Schuman - Libramont, année scolaire 2017 - 2018
 * Bachelier en informatique de gestion, bloc 2	
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
import java.io.Serializable;

/** 
 * Classe de type "bean" représentant une adresse physique; elle est utilisée pour situer aussi bien le lieu d'un événement que le domicile d'un participant.
 * @see Etudiant
 * @see Evenement
 */ 
public class Adresse implements Serializable {
  private int id;													// Attribut permettant de récuperer l'id référençant cette adresse dans la base de données
  private String localite;
  private int codePostal;
  private String rue;
  private int numero;
  private String boite;
  private String pays;

//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
  
  /**Constructeur sans paramètre. */
  public Adresse() {}
  
  
  /**
   * Constructeur initialisant tous les champs. <br><br>
   * Précondition: tous les paramètres sont correctement initialisés. <br>
   * Postcondition: l'objet a été initialisé, tous ses champs sont initialisés avec la valeur du paramètre de même nom. <br>
   * @param id l'identifiant (BD) de l'adresse
   * @param localite le nom de la localité
   * @param codePostal le code postal correspondant à la localité
   * @param rue le nom de la rue
   * @param numero le numero du bâtiment
   * @param boite le numero de la boîte
   * @param pays le pays
   */
  public Adresse(int id, String localite, int codePostal,String rue,int numero,String boite,String pays){
	  this.id = id;
	  this.localite = localite;
	  this.codePostal = codePostal;
	  this.rue = rue;
	  this.numero = numero;
	  this.boite = boite;
	  this.pays = pays;
	 
  }	
  
//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################

 // Getter et setter pour l'attribut "id" 
  
  	/**
  	 * Renvoie l'identifiant (BD) de l'objet.
	 * @return l'identifiant (BD) de l'objet
  	 */
  	public int getId() {
		return id;
	}
	
	/**
	 * Affecte l'identifiant (BD) de l'objet.
	 * @param id l'identifiant (BD) de l'objet à affecter
	 */
	public void setId(int id) {
		this.id = id;
	} 
  
// Getter et setter pour l'attribut "localite"  

	/** 
	 * Renvoie le nom de la localité.
	 * @return le nom de la localité
	  */
	public String getLocalite() {
		return localite;
	}
	
	/** 
	 * Affecte le nom de la localité.
	 * @param localite le nom de la localité à affecter
	 */
	public void setLocalite(String localite) {
		this.localite = localite;
	}
	
// Getter et setter pour l'attribut "codePostal"
	
	/** 
	 * Renvoie le code postal correspondant à la localité.
	 * @return le code postal correspondant à la localité
	 */
	public int getCodePostal() {
		return codePostal;
	}
	
	/** 
	 * Affecte le code postal correspondant à la localité.<br>
	 * inv: codePostal &gt;  0
	 * @param codePostal le code postal à affecter
	 */
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	
// Getter et setter pour l'attribut "rue"	
	
	/** 
	 * Renvoie le nom de la rue.
	 * @return le nom de la rue
	 */
	public String getRue() {
		return rue;
	}
	
	/** 
	 * Affecte le nom de la rue.
	 * @param rue le nom de la rue à affecter
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	
// Getter et setter pour l'attribut "numero"	
	
	/** 
	 * Renvoie le numéro du bâtiment.
	 * @return le numéro du bâtiment
	 */
	public int getNumero() {
		return numero;
	}
	
	/** 
	 * Affecte le numéro du bâtiment.
	 * @param numero le numéro du bâtiment à affecter
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
// Getter et setter pour l'attribut "boite"
	
	/** 
	 * Renvoie le numéro de la boîte.
	 * @return le numéro de la boîte
	 */
	public String getBoite() {
		return boite;
	}
	
	/** 
	 * Affecte le numéro de la boîte.
	 * @param boite le numéro de la boîte à affecter
	 */
	public void setBoite(String boite) {
		this.boite = boite;
	}
	
// Getter et setter pour l'attribut "pays"
	
	/** 
	 * Renvoie le pays.
	 * @return le pays
	 */
	public String getPays() {
		return pays;
	}
	
	/** 
	 * Affecte le pays.
	 * @param pays le pays à affecter
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}
	

//###################################################################################################################################################################
	
	// Affichage
	
//###################################################################################################################################################################		

	/**Renvoie un objet <code>java.lang.String</code> structurant les information de l'objet courant. (Override la méthode <code>toString</code> héritée de la classe <code>Object</code>). <br><br>
	 * Précondition: les champs <code>rue, numero, codePostal, localite</code> et <code>pays</code> sont initialisés. <br>
	 * Postcondition: l'objet courant est inchangé.
	 * @return objet <code>java.lang.String</code> reprenant les informations de l'adresse selon le format ci-dessous: <br><br>
	 * Rue Rue de la Cite roses, 64 <br>
	 * 6800 Libramont-Chevigny( Belgique ) <br>
	 * @see Object#toString()
	 */
	@Override
	public String toString(){
		return("Rue "+rue+", "+ numero + "/n"+ codePostal + localite + "( " + pays + " )");
	}
}
