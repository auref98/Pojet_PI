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

public class Professeur extends Representant{
	private int id;
	private int nbParticipations;
	
	private ArrayList<Section> relais;
	private ArrayList<Section> enseigne;
	
//###################################################################################################################################################################
	
	// Constructeurs
	
//###################################################################################################################################################################
	
	/** Canstructeur sans paramètre. */
	public Professeur() {
		super();
		
	}

	public Professeur(String nom, String prenom, int numTel, String mail, String matricule, int id, int nbParticipations) {
		super(id, nom, prenom, numTel, mail, matricule);
		this.id = id;
		this.nbParticipations = nbParticipations;
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
	 * @return the nbParticipations
	 */
	public int getNbParticipations() {
		return nbParticipations;
	}

	/**
	 * @param nbParticipations the nbParticipations to set
	 */
	public void setNbParticipations(int nbParticipations) {
		this.nbParticipations = nbParticipations;
	}

//###################################################################################################################################################################
	
	// Manipulation des listes
	
//###################################################################################################################################################################		
	
	/**
	 * @return the relais
	 */
	public ArrayList<Section> getRelais() {
		return relais;
	}

	/**
	 * @param relais the relais to set
	 */
	public void setRelais(ArrayList<Section> relais) {
		this.relais = relais;
	}

	/**
	 * @return the enseigne
	 */
	public ArrayList<Section> getEnseigne() {
		return enseigne;
	}

	/**
	 * @param enseigne the enseigne to set
	 */
	public void setEnseigne(ArrayList<Section> enseigne) {
		this.enseigne = enseigne;
	}
	
	
	
	
	
	
	
  
}
