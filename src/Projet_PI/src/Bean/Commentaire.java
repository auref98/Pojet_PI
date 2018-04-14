/*
 * Haute école Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integré: realisation d'un logiciel de gestion des inscriptions à des événements
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

/** 
 * Classe de type bean utilisée pour reccueillir les commentaires des personnes ayant representé l'école lors de l'événement référencé.
 * @see Evenement
 * @see Representant
 */
public class Commentaire {
	private int id;													// Attribut permettant de récupérer l'id référençant cette adresse dans la base de données
	private String contenu;											// Attribut pour le texte du commentaire
	
	private Representant rep;											// Référence vers l'autreur du commentaire
	private Evenement evenement;										// Référence vers l'événement en question
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
  
	/** Constructeur sans paramètre.*/	
	public Commentaire() {

	}
	
	
	/** Constructeur prenant l'identifiant et le texte du commentaire en paramètres.<br><br>
	 * Précondition: id et contenu sont initialisés.<br>
	 * Postcondition: l'objet a été initialisé, ses champs "id" et "contenu" sont initialisés avec la valeur des paramètres de même nom.<br>
	 * @param id l'identifiant (BD) du commentaire
	 * @param contenu le texte du commentaire
	 */
	public Commentaire(int id, String contenu) {
		this.id = id;
		this.contenu = contenu;

	}
	
	/**
	 * Constructeur initialisant tous les champs de l'objet<br><br>
	 * Précondition: tous les paramètres sont initialisés.<br>
	 * Postcondition: l'objet a été initialisé, tous ses champs sont initialisés avec la valeur des paramètres de même nom.<br>
	 * @param id l'identifiant (BD) du commentaire
	 * @param contenu le texte du commentaire
	 * @param rep la référence de l'objet Representant à affecter
	 * @param e La référence de l'objet Evenement à affecter
	 */
	public Commentaire(int id, String contenu,Representant rep,Evenement e) {
		this.id = id;
		this.contenu = contenu;
		this.rep = rep;
		this.evenement = e;
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
	
// Getter et setter pour l'attribut "contenu" 
	
	/**
	 * Renvoie le texte du commentaire sous forme d'un objet java.lang.String.
	 * @return le texte du commentaire sous forme d'un objet java.lang.String
	 */
	public String getContenu() {
		return contenu;
	}
	/**
	 * Affecte le texte du commentaire.
	 * @param contenu le texte du commentaire à affecter
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

// Getter et setter pour l'attribut "rep" 
	
	/**
	 * Renvoie la référence vers l'objet Représentant associé à l'objet courant.
	 * @return la référence vers l'objet Représentant associé à l'objet courant
	 * @see Representant
	 */
	public Representant getRep() {
		return rep;
	}

	/**
	 * Affecte l'objet Representant à associer à l'objet courant.
	 * @param rep l'objet Representant à associer à l'objet courant
	 * @see Representant
	 */
	public void setRep(Representant rep) {
		this.rep = rep;
	}

// Getter et setter pour l'attribut "evenement" 
	
	/**
	 * Renvoie la référence vers l'objet Evenement associé à l'objet courant.
	 * @return la référence vers l'objet Evenement associé à l'objet courant
	 * @see Evenement
	 */
	public Evenement getEvenement() {
		return evenement;
	}

	/**
	 * Affecte l'objet Evenement à associer à l'objet courant.
	 * @param evenement l'objet Evenement à associer à l'objet courant
	 * @see Evenement
	 */
	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}

}
