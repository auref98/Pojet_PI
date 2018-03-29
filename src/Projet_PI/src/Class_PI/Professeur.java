/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

package Class_PI;

import java.util.ArrayList;

public class Professeur extends Representant{
	private int nbParticipations;
	
	private ArrayList<Section> relais;
	private ArrayList<Section> enseigne;
	/**
	 * @param nom
	 * @param prenom
	 * @param numTel
	 * @param mail
	 * @param matricule
	 * @param nbParticipations
	 */
	public Professeur(String nom, String prenom, int numTel, String mail, String matricule, int nbParticipations) {
		super(nom, prenom, numTel, mail, matricule);
		this.nbParticipations = nbParticipations;
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
