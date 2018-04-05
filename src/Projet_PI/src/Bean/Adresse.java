/*
 * Haute école Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integre: realisation d'un logiciel de gestion des inscriptions à des événements
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


/** Classe de type "bean" représentant une adresse physique; elle est utilisée pour situer aussi bien le lieu d'un événement que le domicile d'un participant.*/
public class Adresse implements Serializable {
  private int id;
  private String localite;
  private int codePostal;
  private String rue;
  private int numero;
  private String boite;
  private String pays;

  private ArrayList<Evenement> listeEvent;							
  private ArrayList<Etudiant> listeEtudiant;						

/*
 * Constructeurs
 */
  
  /**Constructeur sans paramètre */
  public Adresse() {}
  
  
  /**
   * Constructeur initialisant tous les champs.</br>
   * </br>
   * Précondition: tous les paramètres sont correctement initialisés.</br>
   * Postcondition: l'objet a été initialisé, tous ses champs sont initialisés avec la valeur des paramètres de même nom;</br>
   * 				les deux attributs correspondant à des listes ont été initialisés avec des listes vides de type approprié.
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
	  listeEvent = new ArrayList<Evenement> ();						// Initialise une liste vide pour l'adresse des événements 
	  listeEtudiant = new ArrayList<Etudiant> ();					// Initialise une liste vide pour l'adresse des étudiants
	  
  }	
  	
  
//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
  
// Getter et setter pour l'attribut "localite"  

	/** 
	 * Renvoie le nom de la localité.
	 * @return le nom de la localité.
	 * */
	public String getLocalite() {
		return localite;
	}
	
	/** 
	 * Affecte le nom de la localité.
	 * @param localite le nom de la localité.
	 */
	public void setLocalite(String localite) {
		this.localite = localite;
	}
	
// Getter et setter pour l'attribut "codePostal"
	
	/** 
	 * Renvoie le code postal correspondant à la localité.
	 * @return le code postal correspondant à la localité.
	 */
	public int getCodePostal() {
		return codePostal;
	}
	
	/** 
	 * Affecte le code postal correspondant à la localité.
	 * @param codePostal le code postal correspondant à la localité.
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
	 * @param rue le nom de la rue.
	 * */
	public void setRue(String rue) {
		this.rue = rue;
	}
	
// Getter et setter pour l'attribut "numero"	
	
	/** 
	 * Renvoie le numéro du bâtiment.
	 * @return le numéro du bâtiment.
	 */
	public int getNumero() {
		return numero;
	}
	
	/** 
	 * Affecte le numéro du bâtiment.
	 * @param numero le numéro du bâtiment.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
// Getter et setter pour l'attribut "boite"
	
	/** 
	 * Renvoie le numéro de la boîte.
	 * @return le numéro de la boîte.
	 * */
	public String getBoite() {
		return boite;
	}
	
	/** 
	 * Affecte le numéro de la boîte.
	 * @param boite le numéro de la boîte.
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
	 * @param pays le pays.
	 * */
	public void setPays(String pays) {
		this.pays = pays;
	}
	
//###################################################################################################################################################################
	
	// Manipulation des listes
	
//###################################################################################################################################################################	
	
	/**
	 * Ajoute un Etudiant à la liste.</br></br>
	 * Précondition: l'attribut "listeEtudiant" est initialisé avec un type valide (Etudiant); l'objet Etudiant e est correctement initialisé.</br>
	 * Postcondition: l'Etudiant e a été ajoute à la liste "listeEtudiant".</br>
	 * @param l'objet Etudiant à ajouter.
	 */
	public void addEtudiant(Etudiant e) {
		listeEtudiant.add(e);
	}
	
	/**
	 * Ajoute un Evenement à la liste.</br></br>
	 * Précondition: l'attribut "listeEvent" est initialisé avec un type valide (Event); l'objet Evenement e est correctement initialisé.</br>
	 * Postcondition: l'Evenement e a été ajouté à la liste "listeEvent".</br>
	 * @param l'objet Evenement à ajouter.
	 */
	public void addEvent(Evenement e) {
		listeEvent.add(e);
	}
	
	/**
	 * Supprime un Etudiant de la liste.</br></br>
	 * Précondition: l'attribut "listeEtudiant" est initialisé.</br>
	 * Postcondition: si l'Etudiant e se trouvait dans la liste, celui-ci en a été supprimé; sinon la liste et l'objet courant sont inchangés.</br>
	 * @param e l'objet Etudiant à supprimer de la liste.
	 */
	public void delEtudiant(Etudiant e) {
		listeEtudiant.remove(e);
	}
	
	/**
	 * Supprime un Evenement de la liste.</br></br>
	 * Précondition: l'attribut "listeEvent" est initialisé.</br>
	 * Postcondition: si l'Evenement e se trouvait dans la liste, celui-ci en a été supprimé; sinon la liste et l'objet courant sont inchangés.</br>
	 * @param e l'objet Evenement à supprimer de la liste.
	 */
	public void delEvent(Evenement e) {
		listeEvent.remove(e);
	}
	
	/**
	 * Affiche tous les étudiants de la liste "listeEtudiant".</br></br>
	 * Précondition: la liste d'Etudiant est initialisée.</br>
	 * Postcondition: 	les informations de tous les étudiants de la liste ont été affichées a l'ecran conformement au format defini dans la méthode toString de la classe Etudiant; 
	 *					l'objet courant est inchangé.</br>
	 */
	public void getEtudiant() {
		int taille = listeEtudiant.size();							// Stocke la taille de la liste dans une variable
		int i = 0;													// Declare et initialise a 0 un indice
		while (i<taille) {											// Tant que l'indice est inferieur a la taille de la liste,
			Etudiant e = listeEtudiant.get(i);						// obtient l'Etudiant place a l'indice i dans la liste...
			System.out.println(e.toString());						// ... et affiche ses information a l'ecran
			i++;													// Incremente l'indice
		}
	}
	
	/**
	 * Affiche tous les événements de la liste "listeEvent".</br></br>
	 * Précondition: la liste d'Evenement est initialisée.</br>
	 * Postcondition: 	les informations de tous les événements de la liste ont été affichées à l'écran conformément au format defini dans la méthode toString de la classe Evenement; 
	 *					l'objet courant est inchangé.
	 */
	public void getEvent() {
		int taille = listeEvent.size();								// Stocke la taille de la liste dans une variable
		int i = 0;													// Declare et initialise a 0 un indice
		while (i<taille) {											// Tant que l'indice est inferieur a la taille de la liste,
			Evenement e = listeEvent.get(i);						// obtient l'Etudiant place a l'indice i dans la liste...
			System.out.println(e.toString());						// ... et affiche ses information a l'ecran
			i++;													// Incremente l'indice
		}
	}

//###################################################################################################################################################################
	
	// Affichage
	
//###################################################################################################################################################################		

	/**Affiche l'adresse a l'écran (override la méthode toString héritée de la classe Object).</br></br>
	 * Précondition: les champs rue, numero, codePostal, localite et pays sont initialisés.</br>
	 * Postcondition: l'objet courant est inchangé.
	 * @return objet java.lang.String reprenant les informations de l'adresse selon le format ci-dessous:</br></br>
	 * Rue Rue de la Cite roses, 64</br>
	 * 6800 Libramont-Chevigny( Belgique )</br>
	 */
	@Override
	public String toString(){
		return("Rue "+rue+", "+ numero + "/n"+ codePostal + localite + "( " + pays + " )");
	}
}
