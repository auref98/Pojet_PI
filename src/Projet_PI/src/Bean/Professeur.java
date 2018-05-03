/*
 * Haute �cole Robert Schuman - Libramont, ann�e scolaire 2017 - 2018
 * Bachelier en informatique de gestion, bloc 2		
 * 
 * Projet integr�: r�alisation d'un logiciel de gestion des inscriptions � des �v�nements
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
 * Classe de type "bean" repr�sentant un professeur. <br><br>
 * Etend la classe "Repr�sentant" dont elle h�rite les attributs d'une personne, et ajoute ses propres attributs 
 * sp�cifiques au informations n�cessaires � la mod�lisation d'un professeur.
 * @see Section
 * @see java.util.ArrayList
 */
public class Professeur extends Representant{
	private int id;												// Attribut permettant de recuperer l'id r�f�ren�ant cette adresse dans la base de donnees
	private int nbParticipations;									// Attribut comptabilisant le nombre total de participations du professeur aux �v�nements
	
	private ArrayList<Section> relais;								// Liste des sections dont le professeur est le relais de communication
	private ArrayList<Section> enseigne;							// Liste des sections dans lesquelles le professeur enseigne
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Canstructeur sans param�tre. */
	public Professeur() {
		super();
		
	}
	
	/** Constructeur initialisant tous les attributs (� l'exception des listes). <br><br>
	 * Pr�condition: tous les param�tres sont correctement initialis�s. <br>
	 * Postcondition:  l'objet est initialis�; tous les attributs sont initialis�s avec la valeur du param�tre de m�me nom. <br>
	 * @param nom le nom du professeur
	 * @param prenom le pr�nom du professeur
	 * @param numTel le num�ro de t�l�phone du professeur
	 * @param mail l'adresse email du professeur
	 * @param matricule le matricule identifiant du professeur
	 * @param id l'identifiant (BD) de l'�tudiant
	 * @param nbParticipations le nombre total de participations du professeur aux �v�nements
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
	 * Renvoie le nombre total de participations du professeur aux �v�nements.
	 * @return le nombre total de participations du professeur aux �v�nements
	 */
	public int getNbParticipations() {
		return nbParticipations;
	}

	/**
	 * Affecte le nombre total de participations du professeur aux �v�nements.
	 * @param nbParticipations le nombre total de participations du professeur aux �v�nements
	 */
	public void setNbParticipations(int nbParticipations) {
		this.nbParticipations = nbParticipations;
	}

// Getter et setter pour l'attribut "relais" 
	
	/**
	 * Renvoie la r�f�rence de la liste des sections dont le professeur est le relais de communication.
	 * @return la r�f�rence de la liste des sections dont le professeur est le relais de communication
	 * @see Section
	 * @see java.util.ArrayList
	 */
	public ArrayList<Section> getRelais() {
		return relais;
	}

	/**
	 * Affecte la r�f�rence d'une liste d'objets <code>Section</code> � l'attribut de l'objet courant repr�sentant les sections dont le professeur est le relais de communication.
	 * @param relais la r�f�rence d'une liste d'objets <code>Section</code> � affecter � l'attribut de l'objet courant repr�sentant les sections dont le professeur est le relais de communication
	 * @see Section
	 * @see java.util.ArrayList
	 */
	public void setRelais(ArrayList<Section> relais) {
		this.relais = relais;
	}

// Getter et setter pour l'attribut "enseigne" 
	
	/**
	 * Renvoie la r�f�rence de la liste des sections dans lesquelles le professeur enseigne.
	 * @return la r�f�rence de la liste des sections dans lesquelles le professeur enseigne
	 * @see Section
	 * @see java.util.ArrayList
	 */
	public ArrayList<Section> getEnseigne() {
		return enseigne;
	}

	/**
	 * Affecte la r�f�rence d'une liste d'objets <code>Section</code> � l'attribut de l'objet courant repr�sentant les sections dans lesquelles le professeur enseigne.
	 * @param enseigne la r�f�rence d'une liste d'objets <code>Section</code> � affecter � l'attribut de l'objet courant repr�sentant les sections dans lesquelles le professeur enseigne
	 * @see java.util.ArrayList
	 */
	public void setEnseigne(ArrayList<Section> enseigne) {
		this.enseigne = enseigne;
	}
	
}
