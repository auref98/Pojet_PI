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

import java.io.Serializable;

/**
 * Classe de type "bean" représentant une "inscription", c'est-à-dire le lien entre un représentant de l'école et une plage horaire liée à un événement. <br><br>
 * Elle indique égalemetn si le représentant s'est effectivement présenté à l'événement.
 * @see Representant
 * @see Plage
 */
public class Inscription implements Serializable{
	
	private int id;												// Attribut permettant de récuperer l'id référençant cette adresse dans la base de donnees
	private boolean valide;										// Attribut indiquant si le représentant a bien effectué sa tâche
	private Representant representant;								// Référence vers l'objet Représentant lié à cette inscription
	private Plage plage;											// Référence vers l'objet Plage correspondant à la date de cette inscription (date de la prestation à effectuer)
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Constructeur sans paramètre. */
	public Inscription() {}
	
	/**
	 * Constructeur initialisant tous les paramètres. <br><br>
	 * Précondition:  tous les paramètres sont correctement initialisés. <br>
	 * Postcondition:  l'objet est initialisé; tous les attributs sont initialisés avec la valeur du paramètre de même nom. <br>
	 * @param id l'identifiant (BD) de l'étudiant
	 * @param valide après l'événement, indique si le représentant s'est présenté à l'événement ou non
	 * @param representant référence vers l'objet représentant lié
	 * @param plage référence vers la plage horaire liée
	 */
	public Inscription(int id, boolean valide, Representant representant, Plage plage){
	 	this.id = id;
		this.valide = valide; //en attendant que le charge de comm ne valide la présence l'attribut est a false
	  	this.representant = representant;
	  	this.plage = plage;
	}
	
//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
	
// Getter et setter pour l'attribut "id" 	

	/**
	 * Renvoie l'identifiant (BD) de l'inscription.
	 * @return l'identifiant (BD) de l'inscription
	 */
	public int getId() {
		return id;
	}

	/**
	 * Affecte l'identifiant (BD) de l'inscription
	 * @param id l'identifiant (BD) de l'inscription.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
// Getter et setter pour l'attribut "valide" 
	
	/**
	 * Renvoie une valeur booleenne indiquand si le représentant s'est présenté à l'événement.
	 * @return <code>true</code> si la présence du représenatant ait été validée,  <code>false</code> sinon 
	 */
	public boolean isValide() {
		return valide;
	}
	
	/**
	 * Affecte une valeur booleenne indiquand si le représentant s'est présenté à l'événement. <br>
	 * (Par défaut la valeur est initialisée à <code>false</code>.
	 * @param valide <code>true</code> pour valider la présence du représentant
	 */
	public void setValide(boolean valide) {
		this.valide = valide;
	}

// Getter et setter pour l'attribut "representant" 
	
	/**
	 * Renvoie la référence vers l'objet <code>Representant</code> lié à cette inscription.
	 * @return la référence vers l'objet <code>Representant</code> lié à cette inscription
	 * @see Representant
	 */
	public Representant getRepresentant() {
		return representant;
	}
	
	/**
	 * Affecte la référence vers l'objet Représentant lié à cette inscription.
	 * @param representant la référence vers l'objet Représentant lié à cette inscription.
	 * @see Representant
	 */
	public void setRepresentant(Representant representant) {
		this.representant = representant;
	}
	
// Getter et setter pour l'attribut "plage" 
	
	/**
	 * Renvoie la référence vers l'objet <code>Plage</code> correspondant à la date de cette inscription.
	 * @return la référence vers l'objet <code>Plage</code> correspondant à la date de cette inscription
	 * @see Plage
	 */
	public Plage getPlage() {
		return plage;
	}
	
	/**
	 * Affecte la référence vers l'objet <code>Plage</code> correspondant à la date de cette inscription.
	 * @param plage la référence vers l'objet <code>Plage</code> correspondant à la date de cette inscription
	 * @see Plage
	 */
	public void setPlage(Plage plage) {
		this.plage = plage;
	}
	  
}
