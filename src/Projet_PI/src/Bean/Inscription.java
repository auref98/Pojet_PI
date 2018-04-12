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

import java.io.Serializable;

public class Inscription implements Serializable{
	
	private int id;
	private boolean valide;
	private Representant representant;
	private Plage plage;
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Constructeur sans paramètre. */
	public Inscription() {}
	
	public Inscription(int id, boolean valide, Representant representant, Plage plage){
	 	this.id = id;
		this.valide = valide; //en attendant que le charge de comm ne valide la présence l'attribut est a false
	  	this.representant = representant;
	  	this.plage = plage;
	}
	
//###################################################################################################################################################################
	
	// Getters et setters
	
//###################################################################################################################################################################
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the valide
	 */
	public boolean isValide() {
		return valide;
	}
	
	/**
	 * @param valide the valide to set
	 */
	public void setValide(boolean valide) {
		this.valide = valide;
	}
	
	/**
	 * @return the representant
	 */
	public Representant getRepresentant() {
		return representant;
	}
	
	/**
	 * @param representant the representant to set
	 */
	public void setRepresentant(Representant representant) {
		this.representant = representant;
	}
	
	/**
	 * @return the plage
	 */
	public Plage getPlage() {
		return plage;
	}
	
	/**
	 * @param plage the plage to set
	 */
	public void setPlage(Plage plage) {
		this.plage = plage;
	}
	  
  
}
