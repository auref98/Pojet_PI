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


/** Classe repr�sentant une adresse physique; elle est utilis�e pour situer aussi bien le lieu d'un �v�nement que le domicile d'un participant.*/
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
  
  /**Constructeur sans param�tre */
  public Adresse() {}
  
  
  /**
   * Constructeur initialisant tous les champs.</br>
   * </br>
   * Pr�condition: tous les param�tres sont correctement initialis�s.</br>
   * Postcondition: l'objet a �t� initialis�, tous ses champs sont initialis�s avec la valeur des param�tres de m�me nom;</br>
   * 				les deux attributs correspondant � des listes ont �t� initialis�s avec des listes vides de type appropri�.
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
	  listeEvent = new ArrayList<Evenement> ();						// Initialise une liste vide pour l'adresse des �v�nements 
	  listeEtudiant = new ArrayList<Etudiant> ();					// Initialise une liste vide pour l'adresse des �tudiants
	  
  }	
  	
  
//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
  
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
	 * @param localite le nom de la localit�.
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
	 * @param codePostal le code postal correspondant � la localit�.
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
	 * Renvoie le num�ro du b�timent.
	 * @return le num�ro du b�timent.
	 */
	public int getNumero() {
		return numero;
	}
	
	/** 
	 * Affecte le num�ro du b�timent.
	 * @param numero le num�ro du b�timent.
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
	 * @param boite le num�ro de la bo�te.
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
	 * Ajoute un Etudiant � la liste.</br></br>
	 * Pr�condition: l'attribut "listeEtudiant" est initialis� avec un type valide (Etudiant); l'objet Etudiant e est correctement initialis�.</br>
	 * Postcondition: l'Etudiant e a �t� ajoute � la liste "listeEtudiant".</br>
	 * @param l'objet Etudiant � ajouter.
	 */
	public void addEtudiant(Etudiant e) {
		listeEtudiant.add(e);
	}
	
	/**
	 * Ajoute un Evenement � la liste.</br></br>
	 * Pr�condition: l'attribut "listeEvent" est initialis� avec un type valide (Event); l'objet Evenement e est correctement initialis�.</br>
	 * Postcondition: l'Evenement e a �t� ajout� � la liste "listeEvent".</br>
	 * @param l'objet Evenement � ajouter.
	 */
	public void addEvent(Evenement e) {
		listeEvent.add(e);
	}
	
	/**
	 * Supprime un Etudiant de la liste.</br></br>
	 * Pr�condition: l'attribut "listeEtudiant" est initialis�.</br>
	 * Postcondition: si l'Etudiant e se trouvait dans la liste, celui-ci en a �t� supprim�; sinon la liste et l'objet courant sont inchang�s.</br>
	 * @param e l'objet Etudiant � supprimer de la liste.
	 */
	public void delEtudiant(Etudiant e) {
		listeEtudiant.remove(e);
	}
	
	/**
	 * Supprime un Evenement de la liste.</br></br>
	 * Pr�condition: l'attribut "listeEvent" est initialis�.</br>
	 * Postcondition: si l'Evenement e se trouvait dans la liste, celui-ci en a �t� supprim�; sinon la liste et l'objet courant sont inchang�s.</br>
	 * @param e l'objet Evenement � supprimer de la liste.
	 */
	public void delEvent(Evenement e) {
		listeEvent.remove(e);
	}
	
	/**
	 * Affiche tous les �tudiants de la liste "listeEtudiant".</br></br>
	 * Pr�condition: la liste d'Etudiant est initialis�e.</br>
	 * Postcondition: 	les informations de tous les �tudiants de la liste ont �t� affich�es a l'ecran conformement au format defini dans la m�thode toString de la classe Etudiant; 
	 *					l'objet courant est inchang�.</br>
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
	 * Affiche tous les �v�nements de la liste "listeEvent".</br></br>
	 * Pr�condition: la liste d'Evenement est initialis�e.</br>
	 * Postcondition: 	les informations de tous les �v�nements de la liste ont �t� affich�es � l'�cran conform�ment au format defini dans la m�thode toString de la classe Evenement; 
	 *					l'objet courant est inchang�.
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
