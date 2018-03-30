/*
 * NamingException(Aur�lien, Killian, Robin, Louis, Christophe)
 */

package Bean;

import java.util.*;

public class Evenement {
  private String nom;
  private int nbParticipantRequis;
  private String description;
  private String image;
  private Adresse adresseEve;

  private ArrayList<Plage> listePlage;
  private LinkedList<Commentaire> listeCommentaire;
  private LinkedList<Contact> listeContact;
  private ArrayList<Section> listeSection;
  
	public Evenement(String nom, int nbParticipantRequis, String description, String image, Adresse adresseEve)
	{
		super();
		this.nom = nom;
		this.nbParticipantRequis = nbParticipantRequis;
		this.description = description;
		this.image = image;
		this.adresseEve = adresseEve;
	}
	
	/**
	 * @author Aurelien
	 * @return clone de l'evenement
	 */
	public Evenement clone(){
		return new Evenement(this.nom, this.nbParticipantRequis, this.description,this.image,this.adresseEve.clone());
	}
	
	/**
	 * 
	 * @author Aurelien
	 * @param nom est initialiser
	 * @return true si le nom est �gale au nom de l'�v�nement
	 */
	public boolean equals(String nom){
		return nom.equals(this.nom);
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the nbParticipantRequis
	 */
	public int getNbParticipantRequis() {
		return nbParticipantRequis;
	}

	/**
	 * @param nbParticipantRequis the nbParticipantRequis to set
	 */
	public void setNbParticipantRequis(int nbParticipantRequis) {
		this.nbParticipantRequis = nbParticipantRequis;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the srcImage
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param srcImage the srcImage to set
	 */
	public void setImage(String srcImage) {
		this.image = image;
	}

	/**
	 * @return the adresseEve
	 */
	public Adresse getAdresseEve() {
		return adresseEve;
	}

	/**
	 * @param adresseEve the adresseEve to set
	 */
	public void setAdresseEve(Adresse adresseEve) {
		this.adresseEve = adresseEve;
	}
}
