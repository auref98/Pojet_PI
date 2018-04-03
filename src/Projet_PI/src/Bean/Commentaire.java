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

// Classe utilisee pour reccueillir les commentaires des personnes ayant represente l'ecole lors de l'evenement reference

public class Commentaire {
	private int id;													// Attribut permettant de recuperer l'id referencant cette adresse dans la base de donnees
	private String contenu;											// Attribut pour le texte du commentaire
	
	private Representant rep;										// Reference vers l'autreur du commentaire
	private Evenement evenement;									// Reference vers l'evenement en question
	
	
/*
 * Constructeurs
 */
  
	// Constructeur sans parametre	
	public Commentaire() {

	}
	
	// Constructeur prenant l'identifiant et le texte du commentaire en parametres
	// Precondition: id et contenu sont initialises
	// Postcondition: l'objet a ete initialise, tous ses champs sont initialises avec la valeur des parametres de meme nom;
	public Commentaire(int id, String contenu) {
		super();
		this.id = id;
		this.contenu = contenu;
	}
	
	
	
	/**
	 * @return l'identifiant (BD) de l'objet
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id l'identifiant (BD) de l'objet
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return le texte du commentaire sous forme d'un objet java.lang.String
	 */
	public String getContenu() {
		return contenu;
	}
	/**
	 * @param contenu le texte du commentaire
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	
	
	
}
