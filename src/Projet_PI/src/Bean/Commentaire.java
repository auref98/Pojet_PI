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

/** Classe de type bean utilis�e pour reccueillir les commentaires des personnes ayant represent� l'�cole lors de l'�v�nement r�f�rence. */
public class Commentaire {
	private int id;													// Attribut permettant de r�cup�rer l'id r�f�ren�ant cette adresse dans la base de donn�es
	private String contenu;											// Attribut pour le texte du commentaire
	
	private Representant rep;											// R�f�rence vers l'autreur du commentaire
	private Evenement evenement;										// R�f�rence vers l'�v�nement en question
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
  
	/** Constructeur sans param�tre.*/	
	public Commentaire() {

	}
	
	
	/** Constructeur prenant l'identifiant et le texte du commentaire en param�tres.<br/><br/>
	 * Pr�condition: id et contenu sont initialis�s.<br/>
	 * Postcondition: l'objet a �t� initialis�, ses champs "id" et "contenu" sont initialis�s avec la valeur des param�tres de m�me nom.<br/>
	 * @param id l'identifiant (BD) du commentaire
	 * @param contenu le texte du commentaire
	 */
	public Commentaire(int id, String contenu) {
		this.id = id;
		this.contenu = contenu;

	}
	
	/**
	 * Constructeur initialisant tous les champs de l'objet<br/><br/>
	 * Pr�condition: tous les param�tres sont initialis�s.<br/>
	 * Postcondition: l'objet a �t� initialis�, tous ses champs sont initialis�s avec la valeur des param�tres de m�me nom.<br/>
	 * @param id l'identifiant (BD) du commentaire
	 * @param contenu le texte du commentaire
	 * @param rep la r�f�rence de l'objet Representant � affecter
	 * @param e La r�f�rence de l'objet Evenement � affecter
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
	 * @param id l'identifiant (BD) de l'objet � affecter
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
	 * @param contenu le texte du commentaire � affecter
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

// Getter et setter pour l'attribut "rep" 
	
	/**
	 * Renvoie la r�f�rence vers l'objet Repr�sentant associ� � l'objet courant.
	 * @return la r�f�rence vers l'objet Repr�sentant associ� � l'objet courant
	 */
	public Representant getRep() {
		return rep;
	}

	/**
	 * Affecte l'objet Representant � associer � l'objet courant.
	 * @param rep l'objet Representant � associer � l'objet courant
	 */
	public void setRep(Representant rep) {
		this.rep = rep;
	}

// Getter et setter pour l'attribut "evenement" 
	
	/**
	 * Renvoie la r�f�rence vers l'objet Evenement associ� � l'objet courant.
	 * @return la r�f�rence vers l'objet Evenement associ� � l'objet courant
	 */
	public Evenement getEvenement() {
		return evenement;
	}

	/**
	 * Affecte l'objet Evenement � associer � l'objet courant.
	 * @param evenement l'objet Evenement � associer � l'objet courant
	 */
	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}

}
