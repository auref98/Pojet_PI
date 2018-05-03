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

/** 
 * Classe de type "bean" représentant un professeur. <br><br>
 * Etend la classe "Représentant" dont elle hérite les attributs d'une personne, et ajoute ses propres attributs 
 * spécifiques au informations nécessaires à la modélisation d'un professeur.
 * @see Section
 * @see java.util.ArrayList
 */
public class Professeur extends Representant{
	private int id;												// Attribut permettant de recuperer l'id référençant cette adresse dans la base de donnees
	private int nbParticipations;									// Attribut comptabilisant le nombre total de participations du professeur aux événements
	
	private ArrayList<Section> relais;								// Liste des sections dont le professeur est le relais de communication
	private ArrayList<Section> enseigne;							// Liste des sections dans lesquelles le professeur enseigne
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Canstructeur sans paramètre. */
	public Professeur() {
		super();
		
	}
	
	/** Constructeur initialisant tous les attributs (à l'exception des listes). <br><br>
	 * Précondition: tous les paramètres sont correctement initialisés. <br>
	 * Postcondition:  l'objet est initialisé; tous les attributs sont initialisés avec la valeur du paramètre de même nom. <br>
	 * @param nom le nom du professeur
	 * @param prenom le prénom du professeur
	 * @param numTel le numéro de téléphone du professeur
	 * @param mail l'adresse email du professeur
	 * @param matricule le matricule identifiant du professeur
	 * @param id l'identifiant (BD) de l'étudiant
	 * @param nbParticipations le nombre total de participations du professeur aux événements
	 */
	public Professeur(String nom, String prenom, String numTel, String mail, String matricule, int id, int nbParticipations) {
		super(id, nom, prenom, numTel, mail, matricule);				// Appelle le constructeur de la super-classe Representant
		this.id = id;
		this.nbParticipations = nbParticipations;
	}

//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
	
// Getter et setter pour l'attribut "id" 
	
	/**
	 * Renvoie l'identifiant (BD) du professeur.
	 * @return l'identifiant (BD) du professeur
	 */
	public int getId() {
		return id;
	}

	/**
	 * Affecte l'identifiant (BD) du professeur.
	 * @param id l'identifiant (BD) du professeur
	 */
	public void setId(int id) {
		this.id = id;
	}

// Getter et setter pour l'attribut "nbParticipations" 
	
	/**
	 * Renvoie le nombre total de participations du professeur aux événements.
	 * @return le nombre total de participations du professeur aux événements
	 */
	public int getNbParticipations() {
		return nbParticipations;
	}

	/**
	 * Affecte le nombre total de participations du professeur aux événements.
	 * @param nbParticipations le nombre total de participations du professeur aux événements
	 */
	public void setNbParticipations(int nbParticipations) {
		this.nbParticipations = nbParticipations;
	}

// Getter et setter pour l'attribut "relais" 
	
	/**
	 * Renvoie la référence de la liste des sections dont le professeur est le relais de communication.
	 * @return la référence de la liste des sections dont le professeur est le relais de communication
	 * @see Section
	 * @see java.util.ArrayList
	 */
	public ArrayList<Section> getRelais() {
		return relais;
	}

	/**
	 * Affecte la référence d'une liste d'objets <code>Section</code> à l'attribut de l'objet courant représentant les sections dont le professeur est le relais de communication.
	 * @param relais la référence d'une liste d'objets <code>Section</code> à affecter à l'attribut de l'objet courant représentant les sections dont le professeur est le relais de communication
	 * @see Section
	 * @see java.util.ArrayList
	 */
	public void setRelais(ArrayList<Section> relais) {
		this.relais = relais;
	}

// Getter et setter pour l'attribut "enseigne" 
	
	/**
	 * Renvoie la référence de la liste des sections dans lesquelles le professeur enseigne.
	 * @return la référence de la liste des sections dans lesquelles le professeur enseigne
	 * @see Section
	 * @see java.util.ArrayList
	 */
	public ArrayList<Section> getEnseigne() {
		return enseigne;
	}

	/**
	 * Affecte la référence d'une liste d'objets <code>Section</code> à l'attribut de l'objet courant représentant les sections dans lesquelles le professeur enseigne.
	 * @param enseigne la référence d'une liste d'objets <code>Section</code> à affecter à l'attribut de l'objet courant représentant les sections dans lesquelles le professeur enseigne
	 * @see java.util.ArrayList
	 */
	public void setEnseigne(ArrayList<Section> enseigne) {
		this.enseigne = enseigne;
	}
	
}
