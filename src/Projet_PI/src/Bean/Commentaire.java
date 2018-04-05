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

/** Classe de type bean utilisée pour reccueillir les commentaires des personnes ayant representé l'école lors de l'événement référence. */
public class Commentaire {
	private int id;													// Attribut permettant de recuperer l'id referencant cette adresse dans la base de donnees
	private String contenu;											// Attribut pour le texte du commentaire
	
	private Representant rep;										// Reference vers l'autreur du commentaire
	private Evenement evenement;									// Reference vers l'evenement en question
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
  
	/** Constructeur sans paramètre.*/	
	public Commentaire() {

	}
	
	/** Constructeur prenant l'identifiant et le texte du commentaire en paramètres.<br/><br/>
	 * Précondition: id et contenu sont initialisés.<br/>
	 * Postcondition: l'objet a été initialisé, tous ses champs sont initialisés avec la valeur des paramètres de même nom.<br/>
	 * @param id l'identifiant (BD) du commentaire
	 * @param contenu le texte du commentaire
	 */
	public Commentaire(int id, String contenu) {
		super();
		this.id = id;
		this.contenu = contenu;
	}
	
//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
	
// Getter et setter pour l'attribut "id"  
	
	/**
	 * Renvoie l'identifiant (BD) de l'objet.
	 * @return l'identifiant (BD) de l'objet.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Affecte l'identifiant (BD) de l'objet.
	 * @param id l'identifiant (BD) de l'objet à affecter.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
// Getter et setter pour l'attribut "contenu" 
	
	/**
	 * Renvoie le texte du commentaire sous forme d'un objet java.lang.String.
	 * @return le texte du commentaire sous forme d'un objet java.lang.String.
	 */
	public String getContenu() {
		return contenu;
	}
	/**
	 * Affecte le texte du commentaire.
	 * @param contenu le texte du commentaire à affecter.
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

}
