/*
 * NamingException(Aur�lien, Killian, Robin, Louis, Christophe)
 */

package Bean;

import java.util.ArrayList;

public class Professeur extends Representant{
	private int id;
	private int nbParticipations;
	
	private ArrayList<Section> relais;
	private ArrayList<Section> enseigne;
	
	public Professeur(String nom, String prenom, int numTel, String mail, String matricule, int id,
			int nbParticipations) {
		super(id, nom, prenom, numTel, mail, matricule);
		this.id = id;
		this.nbParticipations = nbParticipations;
	}
	
	public Professeur() {
		super();
		
	}

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
	
	
	
	
	
	
	
  
}
