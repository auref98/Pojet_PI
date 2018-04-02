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

import java.util.ArrayList;
import java.io.Serializable;

// Classe representant une adresse physique; elle est utilisee pour situer aussi bien le lieu d'un evenement que le domicile d'un participant.

public class Adresse implements Serializable {
  private int id;													// Attribut permettant de recuperer l'id referencant cette adresse dans la base de donnees
  private String localite;
  private int codePostal;
  private String rue;
  private int numero;
  private String boite;
  private String pays;

  private ArrayList<Evenement> listeEvent;							// Attribut permettant d'accueillir si necessaire une liste referencant les evenements ayant (eu) lieu à cette adresse
  private ArrayList<Etudiant> listeEtudiant;						// Attribut permettant d'accueillir si necessaire une liste referencant les etudiant residant a cette adresse

/*
 * Constructeurs
 */
  
  // Constructeur sans parametre
  public Adresse() {}
  
  
  // Constructeur initialisant tous les champs
  // Precondition: tous les parametres sont correctement initialises
  // Postcondition: l'objet a ete initialise, tous ses champs sont initialises avec la valeur des parametres de meme nom; 
  // les deux attributs correspondant a des listes ont ete initialises avec des listes vides de type approprie
  public Adresse(int id, String localite, int codePostal,String rue,int numero,String boite,String pays){
	  this.id = id;
	  this.localite = localite;
	  this.codePostal = codePostal;
	  this.rue = rue;
	  this.numero = numero;
	  this.boite = boite;
	  this.pays = pays;
	  listeEvent = new ArrayList<Evenement> ();						// Initialise une liste vide pour l'adresse des evenements 
	  listeEtudiant = new ArrayList<Etudiant> ();					// Initialise une liste vide pour l'adresse des etudiants
	  
  }	
  	
  
//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
  
// Getter et setter pour l'attribut "localite"  

	/**
	 * @return the localite
	 */
	public String getLocalite() {
		return localite;
	}
	
	/**
	 * @param localite the localite to set
	 */
	public void setLocalite(String localite) {
		this.localite = localite;
	}
	
// Getter et setter pour l'attribut "codePostal"
	
	/**
	 * @return the codePostal
	 */
	public int getCodePostal() {
		return codePostal;
	}
	
	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	
// Getter et setter pour l'attribut "rue"	
	
	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}
	
	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	
// Getter et setter pour l'attribut "numero"	
	
	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
// Getter et setter pour l'attribut "boite"
	
	/**
	 * @return the boite
	 */
	public String getBoite() {
		return boite;
	}
	
	/**
	 * @param boit the boite to set
	 */
	public void setBoite(String boite) {
		this.boite = boite;
	}
	
// Getter et setter pour l'attribut "pays"
	
	/**
	 * @return the pays
	 */
	public String getPays() {
		return pays;
	}
	
	/**
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}
	
//###################################################################################################################################################################
	
	// Manipulation des listes
	
//###################################################################################################################################################################	
	
	// Ajouter un etudiant a la liste
	// Precondition: L'attribut "listeEtudiant" est initialise avec un type valide (Etudiant); l'objet Etudiant e est correctement initialise
	// Postcondition: l'Etudiant e a ete ajoute a la liste "listeEtudiant"
	// Resultat: neant
	public void addEtudiant(Etudiant e) {
		listeEtudiant.add(e);
	}
	
	// Ajouter un evenement a la liste
	// Precondition: L'attribut "listeEvent" est initialise avec un type valide (Event); l'objet Evenement e est correctement initialise
	// Postcondition: l'Evenement e a ete ajoute a la liste "listeEvent"
	// Resultat: neant
	public void addEvent(Evenement e) {
		listeEvent.add(e);
	}
	
	// Supprimer un etudiant de la liste
	// Precondition: L'attribut "listeEtudiant" est initialise;
	// Postcondition: si l'Etudiant e se trouvait dans la liste, celui-ci en a ete supprime; sinon la liste et l'objet courant sont inchanges 
	// Resultat: neant
	public void delEtudiant(Etudiant e) {
		listeEtudiant.remove(e);
	}
	
	// Supprimer un evenement de la liste
	// Precondition: L'attribut "listeEvent" est initialise;
	// Postcondition: si l'Evenement e se trouvait dans la liste, celui-ci en a ete supprime; sinon la liste et l'objet courant sont inchanges 
	// Resultat: neant
	public void delEvent(Evenement e) {
		listeEvent.remove(e);
	}
	
	// Afficher tous les etudiant de la liste "listeEtudiant"
	// Precondition: la liste d'Etudiant est initialisee
	// Postcondition: 	les informations de tous les etudiants de la liste ont ete affichees a l'ecran conformement au format defini dans la methode toString de la classe Etudiant;
	//					l'objet courant est inchange
	// Resulat: neant
	public void getEtudiant() {
		int taille = listeEtudiant.size();							// Stocke la taille de la liste dans une variable
		int i = 0;													// Declare et initialise a 0 un indice
		while (i<taille) {											// Tant que l'indice est inferieur a la taille de la liste,
			Etudiant e = listeEtudiant.get(i);						// obtient l'Etudiant place a l'indice i dans la liste...
			System.out.println(e.toString());						// ... et affiche ses information a l'ecran
			i++;													// Incremente l'indice
		}
	}
	
	// Afficher tous les evenements de la liste "listeEvent"
	// Precondition: la liste d'Evenement est initialisee
	// Postcondition: 	les informations de tous les evenements de la liste ont ete affichees a l'ecran conformement au format defini dans la methode toString de la classe Evenement;
	//					l'objet courant est inchange
	// Resulat: neant
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

	// Afficher l'adresse a l'ecran (override de la methode toString)
	// Exemple:		
	/* 
	 * Rue Rue de la Cite roses, 64
	 * 6800 Libramont-Chevigny( Belgique )
	 */
	// Precondition: les champs rue, numero, codePostal, localite et pays sont initialises
	// Postcondition: l'objet courant est inchange
	// Resultat; String reprenant les informations de l'adresse selon le format ci-dessus
	@Override
	public String toString(){
		return("Rue "+rue+", "+ numero + "/n"+ codePostal + localite + "( " + pays + " )");
	}
}
