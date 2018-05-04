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

import java.io.Serializable;

/**
 * Classe de type "bean" repr�sentant une "inscription", c'est-�-dire le lien entre un repr�sentant de l'�cole et une plage horaire li�e � un �v�nement. <br><br>
 * Elle indique �galemetn si le repr�sentant s'est effectivement pr�sent� � l'�v�nement.
 * @see Representant
 * @see Plage
 */
public class Inscription implements Serializable{
	
	private int id;												// Attribut permettant de r�cuperer l'id r�f�ren�ant cette adresse dans la base de donnees
	private boolean valide;										// Attribut indiquant si le repr�sentant a bien effectu� sa t�che
	private Representant representant;								// R�f�rence vers l'objet Repr�sentant li� � cette inscription
	private Plage plage;											// R�f�rence vers l'objet Plage correspondant � la date de cette inscription (date de la prestation � effectuer)
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Constructeur sans param�tre. */
	public Inscription() {}
	
	/**
	 * Constructeur initialisant tous les param�tres. <br><br>
	 * Pr�condition:  tous les param�tres sont correctement initialis�s. <br>
	 * Postcondition:  l'objet est initialis�; tous les attributs sont initialis�s avec la valeur du param�tre de m�me nom. <br>
	 * @param id l'identifiant (BD) de l'�tudiant
	 * @param valide apr�s l'�v�nement, indique si le repr�sentant s'est pr�sent� � l'�v�nement ou non
	 * @param representant r�f�rence vers l'objet repr�sentant li�
	 * @param plage r�f�rence vers la plage horaire li�e
	 */
	public Inscription(int id, boolean valide, Representant representant, Plage plage){
	 	this.id = id;
		this.valide = valide; //en attendant que le charge de comm ne valide la pr�sence l'attribut est a false
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
	 * Renvoie une valeur booleenne indiquand si le repr�sentant s'est pr�sent� � l'�v�nement.
	 * @return <code>true</code> si la pr�sence du repr�senatant ait �t� valid�e,  <code>false</code> sinon 
	 */
	public boolean isValide() {
		return valide;
	}
	
	/**
	 * Affecte une valeur booleenne indiquand si le repr�sentant s'est pr�sent� � l'�v�nement. <br>
	 * (Par d�faut la valeur est initialis�e � <code>false</code>.
	 * @param valide <code>true</code> pour valider la pr�sence du repr�sentant
	 */
	public void setValide(boolean valide) {
		this.valide = valide;
	}

// Getter et setter pour l'attribut "representant" 
	
	/**
	 * Renvoie la r�f�rence vers l'objet <code>Representant</code> li� � cette inscription.
	 * @return la r�f�rence vers l'objet <code>Representant</code> li� � cette inscription
	 * @see Representant
	 */
	public Representant getRepresentant() {
		return representant;
	}
	
	/**
	 * Affecte la r�f�rence vers l'objet Repr�sentant li� � cette inscription.
	 * @param representant la r�f�rence vers l'objet Repr�sentant li� � cette inscription.
	 * @see Representant
	 */
	public void setRepresentant(Representant representant) {
		this.representant = representant;
	}
	
// Getter et setter pour l'attribut "plage" 
	
	/**
	 * Renvoie la r�f�rence vers l'objet <code>Plage</code> correspondant � la date de cette inscription.
	 * @return la r�f�rence vers l'objet <code>Plage</code> correspondant � la date de cette inscription
	 * @see Plage
	 */
	public Plage getPlage() {
		return plage;
	}
	
	/**
	 * Affecte la r�f�rence vers l'objet <code>Plage</code> correspondant � la date de cette inscription.
	 * @param plage la r�f�rence vers l'objet <code>Plage</code> correspondant � la date de cette inscription
	 * @see Plage
	 */
	public void setPlage(Plage plage) {
		this.plage = plage;
	}
	  
}
