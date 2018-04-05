/*
 * Haute �cole Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integre: realisation d'un logiciel de gestion des inscriptions � des �v�nements
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

/** Classe de type "bean" repr�sentant une adresse physique; elle est utilis�e pour situer aussi bien le lieu d'un �v�nement que le domicile d'un participant.*/
public class Adresse implements Serializable {
  private int id;													// Attribut permettant de r�cuperer l'id r�f�ren�ant cette adresse dans la base de donn�es
  private String localite;
  private int codePostal;
  private String rue;
  private int numero;
  private String boite;
  private String pays;

//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
  
  /**Constructeur sans param�tre */
  public Adresse() {}
  
  
  /**
   * Constructeur initialisant tous les champs.<br/>
   * </br>
   * Pr�condition: tous les param�tres sont correctement initialis�s.<br/>
   * Postcondition: l'objet a �t� initialis�, tous ses champs sont initialis�s avec la valeur des param�tres de m�me nom;<br/>
   * 				les deux attributs correspondant � des listes ont �t� initialis�s avec des listes vides de type appropri�.<br/>
   * @param id l'identifiant (BD) de l'adresse
   * @param localite le nom de la localit�
   * @param codePostal le code postal correspondant � la localit�
   * @param rue le nom de la rue
   * @param numero le numero du b�timent
   * @param boite le numero de la bo�te
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
  
  
// Getter et setter pour l'attribut "localite"  

	/** 
	 * Renvoie le nom de la localit�.
	 * @return le nom de la localit�.
	 * */
	public String getLocalite() {
		return localite;
	}
	
	/** 
	 * Affecte le nom de la localit�.
	 * @param localite le nom de la localit� � affecter.
	 */
	public void setLocalite(String localite) {
		this.localite = localite;
	}
	
// Getter et setter pour l'attribut "codePostal"
	
	/** 
	 * Renvoie le code postal correspondant � la localit�.
	 * @return le code postal correspondant � la localit�.
	 */
	public int getCodePostal() {
		return codePostal;
	}
	
	/** 
	 * Affecte le code postal correspondant � la localit�.
	 * @param codePostal le code postal correspondant � la localit� � affecter.
	 */
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	
// Getter et setter pour l'attribut "rue"	
	
	/** 
	 * Renvoie le nom de la rue.
	 * @return le nom de la rue.
	 */
	public String getRue() {
		return rue;
	}
	
	/** 
	 * Affecte le nom de la rue.
	 * @param rue le nom de la rue � affecter.
	 * */
	public void setRue(String rue) {
		this.rue = rue;
	}
	
// Getter et setter pour l'attribut "numero"	
	
	/** 
	 * Renvoie le num�ro du b�timent.
	 * @return le num�ro du b�timent.
	 */
	public int getNumero() {
		return numero;
	}
	
	/** 
	 * Affecte le num�ro du b�timent.
	 * @param numero le num�ro du b�timent � affecter.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
// Getter et setter pour l'attribut "boite"
	
	/** 
	 * Renvoie le num�ro de la bo�te.
	 * @return le num�ro de la bo�te.
	 * */
	public String getBoite() {
		return boite;
	}
	
	/** 
	 * Affecte le num�ro de la bo�te.
	 * @param boite le num�ro de la bo�te � affecter.
	 */
	public void setBoite(String boite) {
		this.boite = boite;
	}
	
// Getter et setter pour l'attribut "pays"
	
	/** 
	 * Renvoie le pays.
	 * @return le pays.
	 * */
	public String getPays() {
		return pays;
	}
	
	/** 
	 * Affecte le pays.
	 * @param pays le pays � affecter.
	 * */
	public void setPays(String pays) {
		this.pays = pays;
	}
	

//###################################################################################################################################################################
	
	// Affichage
	
//###################################################################################################################################################################		

	/**Affiche l'adresse a l'�cran (override la m�thode toString h�rit�e de la classe Object).</br></br>
	 * Pr�condition: les champs rue, numero, codePostal, localite et pays sont initialis�s.</br>
	 * Postcondition: l'objet courant est inchang�.
	 * @return objet java.lang.String reprenant les informations de l'adresse selon le format ci-dessous:</br></br>
	 * Rue Rue de la Cite roses, 64</br>
	 * 6800 Libramont-Chevigny( Belgique )</br>
	 */
	@Override
	public String toString(){
		return("Rue "+rue+", "+ numero + "/n"+ codePostal + localite + "( " + pays + " )");
	}
}
